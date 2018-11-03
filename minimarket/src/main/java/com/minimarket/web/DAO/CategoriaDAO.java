package com.minimarket.web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoriaDAO {
	
	@Autowired
	private ICategoriaDAO crud;
	
	public ICategoriaDAO crud() {
		return this.crud;
	}

}
