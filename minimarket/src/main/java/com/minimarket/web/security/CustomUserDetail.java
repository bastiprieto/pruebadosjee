package com.minimarket.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.minimarket.web.DAO.UsuarioDAO;
import com.minimarket.web.entity.Usuario;

@Component
public class CustomUserDetail {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDAO.buscarPorNombreUsuario(username);
		
		//crearemos un Userbuilder para crear un usuario de
		//Spring
		
		UserBuilder user = null;
		
		if(usuario!=null) {
			//si existe el usuario lo transformo en un usuario
			//de Spring
			
			user = org.springframework.security.core.userdetails.User.withUsername(username);
			user.password(new BCryptPasswordEncoder().encode(usuario.getPassword()));
			
			
			
		} else {
			//si el usuario no existe mandaremos una excepcion
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return user.build();
	}

}
