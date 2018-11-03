package com.minimarket.web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MarcaDAO {

	@Autowired
	private IMarcaDAO crud;
	
	public IMarcaDAO crud() {
		return this.crud; 
	}
}
