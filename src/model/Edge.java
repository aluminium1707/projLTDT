package model;

public class Edge {
	Vertex node1;
	Vertex node2;
	public Edge(Vertex node1, Vertex node2) {
		super();
		this.node1 = node1;
		this.node2 = node2;
	}
	public Vertex getNode1() {
		return node1;
	}
	public void setNode1(Vertex node1) {
		this.node1 = node1;
	}
	public Vertex getNode2() {
		return node2;
	}
	public void setNode2(Vertex node2) {
		this.node2 = node2;
	}
	
}
