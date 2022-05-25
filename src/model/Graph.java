package model;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Set;

public class Graph {
	private ArrayList<Vertex> vertexs;
	private ArrayList<Edge> edges;
	private ArrayList<ArrayList<Integer>> mtkArrayList;

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
			vertexs.get(0).setNameInteger(0);
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
		for (Vertex vertex : vertexs) {
			vertex.setNameInteger(dongMoIntegers.size() - 1);
		}
		mtkArrayList.add(dongMoIntegers);
	}

	public void addDerectedEdge(Vertex diemdau, Vertex diemcuoi) {
		mtkArrayList.get(diemdau.index).set(diemcuoi.index, 1);
		edges.add(new Edge(diemdau, diemcuoi));
		diemdau.dsKe.add(diemcuoi);
		System.out.println(diemdau.index);
		System.out.println(diemcuoi.index);
	}

	public void addUnderectedEdge(Vertex diemdau, Vertex diemcuoi) {
		mtkArrayList.get(diemdau.index).set(diemcuoi.index, 1);
		mtkArrayList.get(diemcuoi.index).set(diemdau.index, 1);
		edges.add(new Edge(diemdau, diemcuoi));
		diemdau.dsKe.add(diemcuoi);
	}

	private void themDinh(ArrayList<ArrayList<Integer>> mtkArrayList) {
		if (mtkArrayList.size() == 0) {
			mtkArrayList.add(new ArrayList<Integer>());
			mtkArrayList.get(0).add(0);
//			count++;
			return;
		}
		for (int i = 0; i < mtkArrayList.size(); i++) {
			mtkArrayList.get(i).add(0);
		}
		ArrayList<Integer> dongMoIntegers = new ArrayList<Integer>();
		for (int i = 0; i < mtkArrayList.size() + 1; i++) {
			dongMoIntegers.add(0);
		}
		mtkArrayList.add(dongMoIntegers);
		for (int i = 1; i < mtkArrayList.size(); i++) {
//			count++;
		}
	}

	public void delVertex(Vertex vertex) {
		ArrayList<ArrayList<Integer>> cloneArrayList = (ArrayList<ArrayList<Integer>>) mtkArrayList.clone();
		int count = 0;
		int tmpX = -1;
		int tmpY = -1;
		for (int i = 0; i < mtkArrayList.size(); i++) {
			tmpX++;
			if (i == vertex.getNameInteger()) {
				tmpX--;
			}
			tmpY = -1;
			for (int j = 0; j < mtkArrayList.size(); j++) {
				tmpY++;
				if (j == vertex.getNameInteger()) {
					tmpY--;
				}
				if (i != vertex.getNameInteger() && j != vertex.getNameInteger()) {
					count++;
					int n = cloneArrayList.get(i).get(j);
					cloneArrayList.get(tmpX).set(tmpY, n);
				}
			}
		}
		mtkArrayList = cloneArrayList;
	}
}
