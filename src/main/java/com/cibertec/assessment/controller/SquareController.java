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

import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.service.SquareService;

@RestController
@RequestMapping("/api/squares")
public class SquareController {
    
    @Autowired
    private final SquareService squareService;

    public SquareController(SquareService squareService) {
        this.squareService = squareService;
    }
        
    @GetMapping
    public List<Square> getAllSquares() {
        return squareService.list();
    }
        
    @PostMapping
    public Square createSquare(@RequestBody Square square) {
        return squareService.create(square);
    }
        
    @PutMapping("/{id}")
    public Square updateSquare(@PathVariable Integer id, @RequestBody Square updatedSquare) {
        updatedSquare.setId(id);
        return squareService.update(updatedSquare);
    }
        
    @DeleteMapping("/{id}")
    public void deleteSquare(@PathVariable Integer id) {
        squareService.delete(id);
    }
}
