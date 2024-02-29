package com.cibertec.assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.service.PolygonService;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

	@Autowired
	PolygonService polygonService;

	@PostConstruct
	public void initializeData() {
		List<Polygon> list = new ArrayList<>();

		String[] xpoint1 = { "10", "205", "305", "405", "500" };
		String[] ypoint1 = { "10", "501", "506", "107", "30" };

		String[] xpoint2 = { "100", "605", "305", "405", "500" };
		String[] ypoint2 = { "100", "601", "506", "337", "300" };
		
		Polygon polygon1 = new Polygon().builder().name("Poligon 01").xPoints(Arrays.toString(xpoint1)).yPoints(Arrays.toString(ypoint1)).build();
		Polygon polygon2 = new Polygon().builder().name("Poligon 01").xPoints(Arrays.toString(xpoint2)).yPoints(Arrays.toString(ypoint2)).build();
		list.add(polygon1);
		list.add(polygon2);
		
		polygonService.create(list);
		
		//polygonService.list();
	}
}
