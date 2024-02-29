package com.cibertec.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Polygon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
	private int npoints;
	
	@JsonProperty("xpoints")
	private String xPoints;// se debe guardar un array de 5 puntos como string "
	@JsonProperty("ypoints")
	private String yPoints;// se debe guardar un array de 5 puntos como string
	

	
}
