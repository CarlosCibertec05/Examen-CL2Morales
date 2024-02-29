package com.cibertec.assessment.service;

import java.util.List;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.model.Polygon;

public interface PolygonService {

	public Polygon create(Polygon p);
	public void create(List<Polygon> lp);
    public Polygon update(Polygon p);
    public void delete(Integer id);
    public List<Polygon> list();
	public List<PolygonBean> listPolygonBeans();
}
