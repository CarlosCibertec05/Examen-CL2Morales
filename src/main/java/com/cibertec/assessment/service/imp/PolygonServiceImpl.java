package com.cibertec.assessment.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.repo.PolygonRepo;
import com.cibertec.assessment.service.PolygonService;

@Service
public  class PolygonServiceImpl implements PolygonService {

	@Autowired
	PolygonRepo polygonRepo;

	@Override
	public Polygon create(Polygon p) {
		return polygonRepo.save(p);
	}

	@Override
	public void create(List<Polygon> lp) {
		polygonRepo.saveAll(lp);
	}

	@Override
	public List<PolygonBean> listPolygonBeans() {
		List<Polygon> list = polygonRepo.findAll();
		List<PolygonBean> listPolygonBeans = new ArrayList<>();
		list.forEach(p -> {
			Integer[] intArrayX = new Integer[5];
			Integer[] intArrayY = new Integer[5];

			convertStringInIntegerArray(p.getXPoints(), p.getYPoints(), intArrayX, intArrayY);

			PolygonBean polygonBean = new PolygonBean();
			polygonBean.setId(p.getId());
			polygonBean.setName(p.getName());
			polygonBean.setXPoints(intArrayX);
			polygonBean.setYPoints(intArrayY);
			polygonBean.setNpoints(p.getNpoints());

			listPolygonBeans.add(polygonBean);
		});

		return listPolygonBeans;
	}

	private void convertStringInIntegerArray(String xPoints, String yPoints, Integer[] intArrayX, Integer[] intArrayY) {

		String cleanedXPoints = xPoints.substring(1, xPoints.length() - 1);
		String cleanedYPoints = yPoints.substring(1, yPoints.length() - 1);

		// Split the string by commas
		String[] partsX = cleanedXPoints.split(", ");
		String[] partsY = cleanedYPoints.split(", ");

		for (int i = 0; i < partsX.length; i++) {
			intArrayX[i] = Integer.parseInt(partsX[i]);
		}
		
		for (int i = 0; i < partsY.length; i++) {
			intArrayY[i] = Integer.parseInt(partsY[i]);
		}
	}

	@Override
	public Polygon update(Polygon p) {
		 return polygonRepo.save(p);
	}

	@Override
	public void delete(Integer id) {
		 polygonRepo.deleteById(id);
    }

	@Override
	public List<Polygon> list() {
		return polygonRepo.findAll();
    }

	}

		


	
	


