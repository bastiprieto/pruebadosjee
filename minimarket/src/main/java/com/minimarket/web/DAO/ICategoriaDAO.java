package com.minimarket.web.DAO;

import org.springframework.data.repository.CrudRepository;

import com.minimarket.web.entity.Categoria;

public interface ICategoriaDAO extends CrudRepository<Categoria, Integer> {

}
