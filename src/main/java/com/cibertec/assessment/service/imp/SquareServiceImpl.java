package com.cibertec.assessment.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.repo.SquareRepo;
import com.cibertec.assessment.service.PolygonService;
import com.cibertec.assessment.service.SquareService;

@Service
public class SquareServiceImpl implements SquareService {
	
	 private static final Logger logger = LoggerFactory.getLogger(SquareServiceImpl.class);
	    private static final String INVALID_COORDINATES_FORMAT = "Las coordenadas X e Y tienen un formato inválido";

	  @Autowired
	    private SquareRepo squareRepo;

	    @Autowired
	    private PolygonService polygonService;
	    
	    	   @Override
	   	    public Square create(Square s) {
	   	        try {
	   	            // Guardar el cuadrado en la base de datos
	   	            Square savedSquare = squareRepo.save(s);
	   	            logger.info("Cuadrado guardado exitosamente: " + savedSquare.getId());
	   	            
	   	            // Actualizar el campo polygons del cuadrado con los IDs de los polígonos intersectados
	   	            List<Polygon> polygons = polygonService.list();
	   	            List<Integer> intersectedPolygonIds = findIntersectedPolygons(savedSquare, polygons);
	   	            if (!intersectedPolygonIds.isEmpty()) {
	   	                savedSquare.setPolygons(intersectedPolygonIds.stream().map(Object::toString).collect(Collectors.joining(",")));
	   	                logger.info("Polígonos intersectados IDs: " + intersectedPolygonIds.toString());
	   	                // Guardar el cuadrado actualizado
	   	                return squareRepo.save(savedSquare);
	   	            } else {
	   	                savedSquare.setPolygons("");
	   	                logger.info("No se encontraron polígonos intersecados.");
	   	                return savedSquare;
	   	            }
	   	        } catch (Exception e) {
	   	            logger.error("Error al guardar en el cuadrado: " + e.getMessage());
	   	            throw new RuntimeException("Error al guardar en el cuadrado: " + e.getMessage());
	   	        }
	   	    }
	   	
	   	 
	   	    private List<Integer> findIntersectedPolygons(Square square, List<Polygon> polygons) {
	   	        List<Integer> intersectedPolygonIds = new ArrayList<>();
	   	        
	   	        // Convertir los puntos del cuadrado a arreglos de enteros
	   	        int[] xPoints = Arrays.stream(square.getXPoints().replaceAll("[^\\d,]", "").split(","))
	   	                              .mapToInt(Integer::parseInt).toArray();
	   	        int[] yPoints = Arrays.stream(square.getYPoints().replaceAll("[^\\d,]", "").split(","))
	   	                              .mapToInt(Integer::parseInt).toArray();
	   	        
	   	        for (Polygon polygon : polygons) {
	   	            int[] polyXPoints = Arrays.stream(polygon.getXPoints().replaceAll("[^\\d,]", "").split(","))
	   	                                     .mapToInt(Integer::parseInt).toArray();
	   	            int[] polyYPoints = Arrays.stream(polygon.getYPoints().replaceAll("[^\\d,]", "").split(","))
	   	                                     .mapToInt(Integer::parseInt).toArray();
	   	            
	   	            // Verificar intersección con cada vértice del polígono
	   	            for (int i = 0; i < polyXPoints.length; i++) {
	   	                if (isInside(polyXPoints[i], polyYPoints[i], xPoints, yPoints, xPoints.length)) {
	   	                    intersectedPolygonIds.add(polygon.getId());
	   	                    break; // Si un vértice del polígono está dentro del cuadrado, agregamos el polígono y pasamos al siguiente
	   	                }
	   	            }
	   	        }
	   	        
	   	        return intersectedPolygonIds;
	   	    }

	   	    private boolean isInside(int testX, int testY, int[] xPoints, int[] yPoints, int numPoints) {
	   	        // Algoritmo de ray casting para determinar si un punto está dentro de un polígono
	   	        boolean inside = false;
	   	        int j = numPoints - 1;
	   	        for (int i = 0; i < numPoints; i++) {
	   	            if ((yPoints[i] < testY && yPoints[j] >= testY || yPoints[j] < testY && yPoints[i] >= testY) &&
	   	                (xPoints[i] + (testY - yPoints[i]) / (yPoints[j] - yPoints[i]) * (xPoints[j] - xPoints[i]) < testX)) {
	   	                inside = !inside;
	   	            }
	   	            j = i;
	   	        }
	   	        return inside;
		}

	@Override
    public List<Square> list() {
        return squareRepo.findAll();
    }

    @Override
    public Square update(Square s) {
        return squareRepo.save(s);
    }

    @Override
    public void delete(Integer id) {
        squareRepo.deleteById(id);
    }
}


