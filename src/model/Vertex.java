package model;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Vertex {
	Integer nameInteger;
	Integer index;
	ArrayList<Vertex> dsKe= new ArrayList<Vertex>();
	Ellipse2D ellipse2d;
	public Vertex(Integer nameInteger, Integer index, ArrayList<Vertex> dsKe, Ellipse2D ellipse2d) {
		super();
		this.nameInteger = nameInteger;
		this.index = index;
		this.dsKe = dsKe;
		this.ellipse2d = ellipse2d;
	}
	public Integer getNameInteger() {
		return nameInteger;
	}
	public void setNameInteger(Integer nameInteger) {
		this.nameInteger = nameInteger;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public ArrayList<Vertex> getDsKe() {
		return dsKe;
	}
	public void setDsKe(ArrayList<Vertex> dsKe) {
		this.dsKe = dsKe;
	}
	public Ellipse2D getEllipse2d() {
		return ellipse2d;
	}
	public void setEllipse2d(Ellipse2D ellipse2d) {
		this.ellipse2d = ellipse2d;
	}
	
}
