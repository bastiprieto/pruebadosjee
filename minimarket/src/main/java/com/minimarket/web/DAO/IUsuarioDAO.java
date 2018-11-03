package com.minimarket.web.DAO;

import org.springframework.data.repository.CrudRepository;

import com.minimarket.web.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Integer> {

	
}
