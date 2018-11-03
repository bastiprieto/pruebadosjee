package com.minimarket.web.DAO;

import org.springframework.data.repository.CrudRepository;

import com.minimarket.web.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Integer> {

}
