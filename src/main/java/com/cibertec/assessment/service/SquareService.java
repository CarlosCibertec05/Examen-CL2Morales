package com.cibertec.assessment.service;

import java.util.List;

import com.cibertec.assessment.model.Square;

public interface SquareService {

	Square create(Square s);
	List<Square> list();
	Square update(Square s);
	void delete(Integer id);
	
	
}
