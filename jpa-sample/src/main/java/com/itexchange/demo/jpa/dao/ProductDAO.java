package com.itexchange.demo.jpa.dao;

import com.itexchange.demo.jpa.domain.Product;
import com.itexchange.demo.jpa.exception.ObjectNotFoundException;

public interface ProductDAO {
	Product findById(Integer id) throws ObjectNotFoundException;
}
