package model;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertexs;
	private ArrayList<Edge> edges;
	private ArrayList<ArrayList<Integer>> mtkArrayList;
	private int numOfVertex=0;

	public Graph() {
		vertexs = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		mtkArrayList = new ArrayList<ArrayList<Integer>>();
	}

	public ArrayList<Vertex> getVertexs() {
		return vertexs;
	}

	public void setVertexs(ArrayList<Vertex> vertexs) {
		this.vertexs = vertexs;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public ArrayList<ArrayList<Integer>> getMtkArrayList() {
		return mtkArrayList;
	}

	public void setMtkArrayList(ArrayList<ArrayList<Integer>> mtkArrayList) {
		this.mtkArrayList = mtkArrayList;
	}

	public void addVertex(Ellipse2D el) {
		if (mtkArrayList.size() == 0) {
			mtkArrayList.add(new ArrayList<Integer>());
			mtkArrayList.get(0).add(0);
			vertexs.add(new Vertex(mtkArrayList.size() - 1, mtkArrayList.size() - 1, new ArrayList<Vertex>(), el));
			numOfVertex++;
			return;
		}
		for (int i = 0; i < mtkArrayList.size(); i++) {
			mtkArrayList.get(i).add(0);
		}
		ArrayList<Integer> dongMoIntegers = new ArrayList<Integer>();
		for (int i = 0; i < mtkArrayList.size() + 1; i++) {
			dongMoIntegers.add(0);
			vertexs.add(new Vertex(mtkArrayList.size() - 1, mtkArrayList.size() - 1, new ArrayList<Vertex>(), el));
		}
		mtkArrayList.add(dongMoIntegers);
		numOfVertex++;
	}

	public void addDerectedEdge(Vertex diemdau, Vertex diemcuoi) {
		mtkArrayList.get(diemdau.index).set(diemcuoi.index, 1);
		edges.add(new Edge(diemdau, diemcuoi));
		diemdau.dsKe.add(diemcuoi);
	}
	
	
	public void addUnderectedEdge(Vertex diemdau, Vertex diemcuoi) {
		mtkArrayList.get(diemdau.index).set(diemcuoi.index, 1);
		mtkArrayList.get(diemcuoi.index).set(diemdau.index, 1);
		edges.add(new Edge(diemdau, diemcuoi));
		diemdau.dsKe.add(diemcuoi);
	}
	
	public void delVertex() {
		
	}
}
