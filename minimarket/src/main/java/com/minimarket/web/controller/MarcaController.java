package com.minimarket.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.minimarket.web.DAO.CategoriaDAO;
import com.minimarket.web.DAO.MarcaDAO;
import com.minimarket.web.entity.Categoria;
import com.minimarket.web.entity.Marca;


@Controller
@RequestMapping("/marca")
public class MarcaController {

	@Autowired
	private MarcaDAO mDAO;
	
	@GetMapping("/listarMarca")
	public String listar(Model model) {
		
		model.addAttribute("marcas",mDAO.crud().findAll());
		return "listarMarca.html";
	}
	
	@GetMapping("/eliminarMarca")
	public String eliminar(Model model, RedirectAttributes ra, @RequestParam("id") int id) {
		
		String mensaje = "";
		
		try {
		
			mDAO.crud().deleteById(id);
			mensaje="Eliminado correctamente";
			
		} catch (Exception ex) {
			mensaje="error al eliminar";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listarMarca";
	}
	
	@PostMapping("/almacenarMarca")
	public String almacenar(Model model, RedirectAttributes ra,
			@RequestParam("txtNombre")String nombre,
			@RequestParam("txtDescripcion")String descripcion) {
		
		String mensaje = "";
		
		Marca marca = new Marca();
		marca.setNombre(nombre);
		marca.setDescripcion(descripcion);
		
		Marca mg = mDAO.crud().save(marca);
		
		if (mg!=null) {
			mensaje="Agregado correctamente";
		} else {
			mensaje="No pudo ser agregado";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:agregarMarca";
	}
	
	@GetMapping("/modificarMarca")
	public String modificar(Model model, @RequestParam("txtId")int id,RedirectAttributes ra) {
		
		Marca m = null;
		try {
			m = mDAO.crud().findById(id).get();
		} catch (Exception ex) {
			ra.addFlashAttribute("mensaje", "La marca no existe");
			return "redirect:listarMarca";
		}
		 
		model.addAttribute("m", m);
		
		
		return "modificarMarca.html";
	}
	
	@PostMapping("/actualizarMarca")
	public String actualizar(Model model, RedirectAttributes ra,
			@RequestParam("txtId")int id,
			@RequestParam("txtNombre")String nombre,
			@RequestParam("txtDescripcion") String  descripcion) {
		
		String mensaje = "";
		
		
		Marca marca = mDAO.crud().findById(id).get();
		marca.setId(id);
		marca.setNombre(nombre);
		marca.setDescripcion(descripcion);
		
		Marca mg = mDAO.crud().save(marca);
		
		if (mg!=null) {
			mensaje="modificado correctamente";
		} else {
			mensaje="No pudo ser modificado";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listarMarca";
	}
}
