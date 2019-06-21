package com.dub.spring.minimumSpanningTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/** Graph has Vertices and Adjacency Lists */
public class MSTGraph extends Graph {
	
	/**
	 * This subclass of Graph implements a Depth First Search algorithm
	 */
	private static final long serialVersionUID = 1L;
	
	private int N;
	
	private boolean finished = false;
	
	private int index;// main search loop current index
	
	private int cost;// partial cost
	private boolean accept;// for display only

	@JsonIgnore
	private List<UWEdge> edges = new ArrayList<>();
	
	int time = 0;
	
	
	public MSTGraph(int N) {
		super(N);
		this.N = N;
		index = 0;
		cost = 0;
		accept = false;
	}
	
	public MSTGraph(MSTGraph source) {// deep copy c'tor
		super(source.N);
		this.N = source.N;
		this.finished = false;
		
		edges = new ArrayList<>();
	 		
		for (int i = 0; i < source.N; i++) {
			MSTVertex dfsVertex = (MSTVertex)source.getVertices()[i];
			this.getVertices()[i] = new MSTVertex(dfsVertex);
		}
	
	}
	
	
	
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void display() {// used for debugging only
		for (int i = 0; i < N; i++) {
			MSTVertex mstv = (MSTVertex)vertices[i];
			System.out.println(mstv);
		}
		System.out.println();
	}
	
	public void displayWeights() {
		System.out.println("displayWeights");
		for (Vertex v : vertices) {// for each vertex
			for (WeightedEdge edge : v.getAdjacency()) {
				System.out.println(v.getName() + " "  
							+ this.vertices[edge.getTo()] + " " 
							+ edge.getWeight() + " "
							+ edge.isTree());
			}// for
		}// for
	}
	
	/*
	public void search() {
		System.out.println("search begin");
		int count = 0;
		
		while (!finished && count < 40) {
			searchStep();
			count++;
		}
		System.out.println("search completed");
	}// search
	*/
	
	public void searchStep() {
		System.out.println("searchStep begin");
		
		accept = false;
		UWEdge edge = edges.get(index); // current edge with least weight
		MSTVertex u1 = (MSTVertex)this.vertices[edge.getI1()];
		MSTVertex u2 = (MSTVertex)this.vertices[edge.getI2()];
		
		int tmin = 0;
		int tmax = 0;
		if (u1.getTree() != u2.getTree()) {
			accept = true;
			cost += edge.getWeight();
	
			tmin = Math.min(u1.getTree(), u2.getTree());
			tmax = Math.max(u1.getTree(), u2.getTree());
		
			// UNION operation
			for (int k = 0; k < this.N; k++) {// all vertices
				MSTVertex v = (MSTVertex)this.vertices[k];
				if (v.getTree() == tmax) {
					v.setTree(tmin);
				}//if
			}//for
	
			// retrieve edge from adjacency list
			int k1 = u1.getAdjIndex(edge.getI2());
			int k2 = u2.getAdjIndex(edge.getI1());
			u1.getAdjacency().get(k1).setTree(true);
			u2.getAdjacency().get(k2).setTree(true);
		}// if
		
		// check before return
		this.displayWeights();
			
		// prepare next step
		if (index < edges.size()-1) {
			index++;
		} else {
			this.finished = true;
		}
		
		//JSONSnapshot snapshot = new JSONSnapshot(this.vertices, this.adjacencies);
		
	
	}
	
	
	public void initEdges() {
		
		int[][] check = new int[this.N][this.N];
			
		// also initialize edges
		for (int i1 = 0; i1 < this.N; i1++) {// for each vertex
			Vertex u = this.vertices[i1];
			List<WeightedEdge> conn = u.getAdjacency();
			for (int k = 0; k < conn.size(); k++) {
				if (check[i1][conn.get(k).getTo()] == 0) {
					edges.add(new UWEdge(i1, 
							conn.get(k).getTo(), 
							conn.get(k).getWeight())
					);
					check[i1][conn.get(k).getTo()] = 1;
					check[conn.get(k).getTo()][i1] = 1;
					// only one undirected edge is added
				}// if
			}// for
		}// for
		
		// sort edges by nondecreasing weight
		Collections.sort(edges);
			
		for (int i1 = 0; i1 < this.N; i1++) {// set each vertex as a separate tree
			((MSTVertex)this.vertices[i1]).setTree(i1);
		}

	}// initEdges
	
	
}
