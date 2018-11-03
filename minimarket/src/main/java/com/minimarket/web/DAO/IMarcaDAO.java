package com.minimarket.web.DAO;

import org.springframework.data.repository.CrudRepository;

import com.minimarket.web.entity.Marca;

public interface IMarcaDAO extends CrudRepository<Marca, Integer> {

}
