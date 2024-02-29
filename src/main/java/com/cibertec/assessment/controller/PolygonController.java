package com.cibertec.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.service.PolygonService;

@RestController
@RequestMapping("/api/polygons")
public class PolygonController {
	
	  @Autowired
	    private PolygonService polygonService;
	    
	    @GetMapping
	    public List<Polygon> getAllPolygons() {
	        return polygonService.list();
	    }
	    
	    @PostMapping
	    public Polygon createPolygon(@RequestBody Polygon polygon) {
	        return polygonService.create(polygon);
	    }
	    
	    @PutMapping("/{id}")
	    public Polygon updatePolygon(@PathVariable("id") Integer id, @RequestBody Polygon updatedPolygon) {
	        updatedPolygon.setId(id);
	        return polygonService.update(updatedPolygon);
	    }

	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable("id") Integer id) {
	        polygonService.delete(id);
	    }

}
