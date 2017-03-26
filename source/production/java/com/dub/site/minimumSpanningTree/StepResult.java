package com.dub.site.minimumSpanningTree;

/** container for AJAX response */
public class StepResult {
	
	private Graph graph;
	private Status status;
	private JSONSnapshot snapshot;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public JSONSnapshot getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(JSONSnapshot snapshot) {
		this.snapshot = snapshot;
	}





	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}





	enum Status {
		STEP, FINISHED, INIT
	}
}
