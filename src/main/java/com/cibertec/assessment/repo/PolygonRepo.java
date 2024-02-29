package com.cibertec.assessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.assessment.model.Polygon;

public interface PolygonRepo extends JpaRepository<Polygon, Integer>{

}
