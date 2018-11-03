package com.minimarket.web.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

// Generated 01-nov-2018 0:13:25 by Hibernate Tools 5.3.1.Final

/**
 * Categoria generated by hbm2java
 */

@Table(name="Categoria")
public class Categoria implements java.io.Serializable {

	private Integer idCategoria;
	private String nombreCategoria;
	private String descripcionCategoria;

	public Categoria() {
	}

	public Categoria(String nombreCategoria, String descripcionCategoria) {
		this.nombreCategoria = nombreCategoria;
		this.descripcionCategoria = descripcionCategoria;
	}

	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getDescripcionCategoria() {
		return this.descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

}