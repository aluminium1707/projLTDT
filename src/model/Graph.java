package model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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
			vertexs.add(new Vertex(1, mtkArrayList.size() - 1, new ArrayList<Vertex>(), el));
			return;
		}
		for (int i = 0; i < mtkArrayList.size(); i++) {
			mtkArrayList.get(i).add(0);
		}
		ArrayList<Integer> dongMoIntegers = new ArrayList<Integer>();
		for (int i = 0; i < mtkArrayList.size() + 1; i++) {
			dongMoIntegers.add(0);
			vertexs.add(new Vertex(dongMoIntegers.size(), mtkArrayList.size() - 1, new ArrayList<Vertex>(), el));
		}
		mtkArrayList.add(dongMoIntegers);
	}

	public void addDerectedEdge(Vertex diemdau, Vertex diemcuoi, Line2D line2d) {
		mtkArrayList.get(diemdau.index).set(diemcuoi.index, 1);
		edges.add(new Edge(diemdau, diemcuoi, line2d));
		diemdau.dsKe.add(diemcuoi);
		diemcuoi.dsKe.add(diemdau);
	}

	public void addUnderectedEdge(Vertex diemdau, Vertex diemcuoi, Line2D line2d) {
		mtkArrayList.get(diemdau.index).set(diemcuoi.index, 1);
		mtkArrayList.get(diemcuoi.index).set(diemdau.index, 1);
		edges.add(new Edge(diemdau, diemcuoi, line2d));
		diemdau.dsKe.add(diemcuoi);
		diemcuoi.dsKe.add(diemdau);
	}

	public void delVertex(Vertex vertex) {
		int num1, num2;
		for (int i = 0; i < mtkArrayList.size(); i++) {
			for (int j = 0; j < mtkArrayList.size(); j++) {
				num1 = mtkArrayList.get(i).get(vertex.getNameVeretex() - 1);
				num2 = mtkArrayList.get(i).get(mtkArrayList.size() - 1);
				mtkArrayList.get(i).set(vertex.getNameVeretex() - 1, num2);
				mtkArrayList.get(i).set(mtkArrayList.size() - 1, num1);
			}
		}
		mtkArrayList.remove(vertex.getNameVeretex() - 1);
		vertexs.remove(vertex);
	}

	public void delDirectedsEdge(Edge edge) {
		mtkArrayList.get(edge.getNode1().index).set(edge.getNode2().index, 0);
		edges.remove(edge);
		edge.getNode1().dsKe.remove(edge.getNode2());
		edge.getNode2().dsKe.remove(edge.getNode1());
		System.out.println("dellllll");
	}

	public void delUndirectedsEdge(Edge edge) {
		mtkArrayList.get(edge.getNode1().index).set(edge.getNode2().index, 0);
		mtkArrayList.get(edge.getNode2().index).set(edge.getNode1().index, 0);
		edge.getNode1().dsKe.remove(edge.getNode2());
		edges.remove(edge);
		edge.getNode2().dsKe.remove(edge.getNode1());
		System.out.println("dellllll");

	}
}
