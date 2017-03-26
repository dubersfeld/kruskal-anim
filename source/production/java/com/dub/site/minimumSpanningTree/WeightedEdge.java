package com.dub.site.minimumSpanningTree;

/** this class encapsulates a weighted edge 
 * in an adjacency list representation
 * */
public class WeightedEdge {
	
	private int to; // adjacent vertex index
	private int weight = 0;
	private boolean tree;// additional field
	
	public WeightedEdge(int to) {
		this.to = to;
		this.tree = false;
	}

	public WeightedEdge(WeightedEdge source) {
		this.to = source.to;
		this.weight = source.weight;
		this.tree = source.tree;
	}
	
	public WeightedEdge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
	
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public boolean isTree() {
		return tree;
	}

	public void setTree(boolean tree) {
		this.tree = tree;
	}

	public String toString() {
		return to + " " + weight;
	}
	
	
	

}
