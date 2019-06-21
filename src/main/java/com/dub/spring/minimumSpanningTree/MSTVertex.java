package com.dub.spring.minimumSpanningTree;


public class MSTVertex extends Vertex {

	/**
	 * MSTVertex subclasses Vertex for MSTSearch.
	 * It contains all additional fields specific to Depth First Search
	 */

	private int tree = 0;// only additional field
	
	public MSTVertex() {
		super();
	}
	
	public MSTVertex(Vertex source) {
		super(source);
		this.tree = 0;
	}
	
	public MSTVertex(MSTVertex source) {
		super(source);
		this.tree = source.tree;
	}
	
	
	
	public int getTree() {
		return tree;
	}

	public void setTree(int tree) {
		this.tree = tree;
	}

	

	public String toString() {
		return name + " " + tree;
	}

}
