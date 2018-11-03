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
import com.minimarket.web.entity.Categoria;


@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaDAO cDAO;
	
	@GetMapping("/listarCategoria")
	public String listar(Model model) {
		
		model.addAttribute("categorias",cDAO.crud().findAll());
		return "listarCategoria.html";
	}
	
	@GetMapping("/eliminarCategoria")
	public String eliminar(Model model, RedirectAttributes ra, @RequestParam("idCategoria") int idCategoria) {
		
		String mensaje = "";
		
		try {
		
			cDAO.crud().deleteById(idCategoria);
			mensaje="Eliminado correctamente";
			
		} catch (Exception ex) {
			mensaje="error al eliminar";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listarCategoria";
	}
	
	@PostMapping("/almacenarCategoria")
	public String almacenar(Model model, RedirectAttributes ra,
			@RequestParam("txtNombre")String nombre,
			@RequestParam("txtDescripcion")String descripcion) {
		
		String mensaje = "";
		
		Categoria categoria = new Categoria();
		categoria.setNombreCategoria(nombre);
		categoria.setDescripcionCategoria(descripcion);
		
		Categoria pg = cDAO.crud().save(categoria);
		
		if (pg!=null) {
			mensaje="Agregado correctamente";
		} else {
			mensaje="No pudo ser agregado";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:agregarCategoria";
	}
	
	@GetMapping("/modificarCategoria")
	public String modificar(Model model, @RequestParam("txtIdCategoria")int idCategoria,RedirectAttributes ra) {
		
		Categoria c = null;
		try {
			c = cDAO.crud().findById(idCategoria).get();
		} catch (Exception ex) {
			ra.addFlashAttribute("mensaje", "La categoria no existe");
			return "redirect:listarCategoria";
		}
		 
		model.addAttribute("c", c);
		
		
		return "modificarCategoria.html";
	}
	
	@PostMapping("/actualizarCategoria")
	public String actualizar(Model model, RedirectAttributes ra,
			@RequestParam("txtIdCategoria")int idCategoria,
			@RequestParam("txtNombre")String nombre,
			@RequestParam("txtDescripcion") String  descripcion) {
		
		String mensaje = "";
		
		
		Categoria categoria = cDAO.crud().findById(idCategoria).get();
		categoria.setIdCategoria(idCategoria);
		categoria.setNombreCategoria(nombre);
		categoria.setDescripcionCategoria(descripcion);
		
		Categoria cg = cDAO.crud().save(categoria);
		
		if (cg!=null) {
			mensaje="modificado correctamente";
		} else {
			mensaje="No pudo ser modificado";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listarCategoria";
	}

}
