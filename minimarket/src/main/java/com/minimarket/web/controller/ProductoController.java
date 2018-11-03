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
import com.minimarket.web.DAO.ProductoDAO;
import com.minimarket.web.entity.Categoria;
import com.minimarket.web.entity.Marca;
import com.minimarket.web.entity.Producto;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoDAO pDAO;
	
	@Autowired
	private MarcaDAO mDAO;
	
	@Autowired
	private CategoriaDAO cDAO;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("productos",pDAO.crud().findAll());
		return "listar.html";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(Model model, RedirectAttributes ra, @RequestParam("sku") int sku) {
		
		String mensaje = "";
		
		try {
		
			pDAO.crud().deleteById(sku);
			mensaje="Eliminado correctamente";
			
		} catch (Exception ex) {
			mensaje="error al eliminar";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listar";
	}
	
	@GetMapping("/agregar")
	public String agregar(Model model) {
		
		model.addAttribute("categorias", cDAO.crud().findAll());
		model.addAttribute("marcas", mDAO.crud().findAll());
		
		return "agregar.html";
	}
	
	@PostMapping("/almacenar")
	public String almacenar(Model model, RedirectAttributes ra,
			@RequestParam("txtSku")int sku,
			@RequestParam("txtNombre")String nombre,
			@RequestParam("txtPrecio")int precio,
			@RequestParam("txtStock")int stock,
			@RequestParam("cboCategoria")int idCategoria,
			@RequestParam("cboMarca")int idMarca,
			@RequestParam("txtDescripcion") String  descripcion) {
		
		String mensaje = "";
		
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		Marca marca = new Marca();
		marca.setId(idMarca);
		Producto producto = new Producto();
		producto.setSku(sku);
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setStock(stock);
		producto.setCategoria(idCategoria);
		producto.setMarca(idMarca);
		producto.setDescripcion(descripcion);
		
		Producto pg = pDAO.crud().save(producto);
		
		if (pg!=null) {
			mensaje="Agregado correctamente";
		} else {
			mensaje="No pudo ser agregado";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:agregar";
	}
	
	@GetMapping("/modificar")
	public String modificar(Model model, @RequestParam("txtSku")int sku,RedirectAttributes ra) {
		
		Producto p = null;
		try {
			p = pDAO.crud().findById(sku).get();
		} catch (Exception ex) {
			ra.addFlashAttribute("mensaje", "el producto no existe");
			return "redirect:listar";
		}
		 
		model.addAttribute("p", p);
		model.addAttribute("categorias", cDAO.crud().findAll());
		model.addAttribute("marcas", mDAO.crud().findAll());
		
		return "modificar.html";
	}

	@PostMapping("/actualizar")
	public String actualizar(Model model, RedirectAttributes ra,
			@RequestParam("txtSku")int sku,
			@RequestParam("txtNombre")String nombre,
			@RequestParam("txtPrecio")int precio,
			@RequestParam("txtStock")int stock,
			@RequestParam("cboCategoria")int idCategoria,
			@RequestParam("cboMarca")int idMarca,
			@RequestParam("txtDescripcion") String  descripcion) {
		
		String mensaje = "";
		
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		Marca marca = new Marca();
		marca.setId(idMarca);
		Producto producto = pDAO.crud().findById(sku).get();
		producto.setSku(sku);
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setStock(stock);
		producto.setCategoria(idCategoria);
		producto.setMarca(idMarca);
		producto.setDescripcion(descripcion);
		
		Producto pg = pDAO.crud().save(producto);
		
		if (pg!=null) {
			mensaje="modificado correctamente";
		} else {
			mensaje="No pudo ser modificado";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listar";
	}
}
