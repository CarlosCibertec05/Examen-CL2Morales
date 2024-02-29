package com.cibertec.assessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.assessment.model.Square;

public interface SquareRepo extends JpaRepository<Square, Integer>{

}
