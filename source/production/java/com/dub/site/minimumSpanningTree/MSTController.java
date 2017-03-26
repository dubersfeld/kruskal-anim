package com.dub.site.minimumSpanningTree;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dub.config.annotation.WebController;
import com.dub.site.minimumSpanningTree.StepResult.Status;

@WebController
public class MSTController {
	
	// using a service layer
	@Inject 
	private GraphServices graphServices;
	
	/** Initialize graph for both automatic and stepwise search */
	@RequestMapping(value="initGraph")
	@ResponseBody
	public MSTResponse initGraph(@RequestBody GraphInitRequest message, 
				HttpServletRequest request) 
	{	
		List<JSONEdge> jsonEdges = message.getJsonEdges();
		List<JSONVertex> jsonVertices = message.getJsonVertices();
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("graph") != null) {
			session.removeAttribute("graph");
		}
	
		DFSGraph graph = graphServices.jsonToDFS(jsonEdges, jsonVertices);
			
		session.setAttribute("graph", graph);
			
		MSTResponse mstResponse = new MSTResponse();
		mstResponse.setStatus(MSTResponse.Status.OK);
	
		System.out.println("graph constructed");
		
		graph.display2();
			
		// here the graph is ready for the search loop
	
		System.out.println("initGraph completed");
			
		return mstResponse;
	}
	
	@RequestMapping(value="findComp")
	@ResponseBody
	public StepResult findComp(@RequestBody SearchRequest message, 
				HttpServletRequest request) 
	{	
		System.out.println("findComp begin");
		
		// retrieve graph from session context
		HttpSession session = request.getSession();
		DFSGraph graph = (DFSGraph)session.getAttribute("graph");
		
		if (session.getAttribute("comp") != null) {
			session.removeAttribute("comp");
		}
		
		MSTGraph comp = graph.getComp();
		
		JSONSnapshot snapshot = graphServices.GraphToJSON(comp);
		
		snapshot.displayVertices();
		snapshot.displayAdj();
		
		// check graph
		graph.display();
		
		// find the largest component
	   
		StepResult result = new StepResult();
		result.setStatus(StepResult.Status.INIT);
		
		result.setSnapshot(snapshot);
		
		// attach component to session context
		
		session.setAttribute("comp", comp);
		
		// return to the browser a weighted undirected connected graph
		System.out.println("findComp return");
		return result;
	
		
	}
	
	@RequestMapping(value="search")
	@ResponseBody
	public MSTResponse search(@RequestBody SearchRequest message, 
				HttpServletRequest request) 
	{	
		// retrieve component
		HttpSession session = request.getSession();
		
		/** retrieve component */
		MSTGraph comp = (MSTGraph)session.getAttribute("comp");
		MSTResponse result = new MSTResponse();
		
		result.setStatus(MSTResponse.Status.OK);
		int count = 0;
		
		List<JSONSnapshot> snapshots = new ArrayList<>();
		
		while (!comp.isFinished()) {
			count++;
			comp.searchStep();
			snapshots.add(graphServices.GraphToJSON(comp));
		}
		
		result.setSnapshots(snapshots);
		result.setStatus(MSTResponse.Status.OK);
		
		/*
		comp.searchStep();
		
		JSONSnapshot snapshot = graphServices.GraphToJSON(comp);
		StepResult result = new StepResult();
		result.setSnapshot(snapshot);
		if (comp.isFinished()) {
			result.setStatus(Status.FINISHED);// search completed
		} else {
			result.setStatus(Status.STEP);// one more step
		}
		
		snapshot.displayVertices();
		
		*/
		return result;
	}
	
	
	@RequestMapping(value="searchStep")
	@ResponseBody
	public StepResult searchStep(@RequestBody SearchRequest message, 
				HttpServletRequest request) 
	{	
		// retrieve component
		HttpSession session = request.getSession();
		MSTGraph comp = (MSTGraph)session.getAttribute("comp");
		
		comp.searchStep();
		
		JSONSnapshot snapshot = graphServices.GraphToJSON(comp);
		StepResult result = new StepResult();
		result.setSnapshot(snapshot);
		if (comp.isFinished()) {
			result.setStatus(Status.FINISHED);// search completed
		} else {
			result.setStatus(Status.STEP);// one more step
		}
		
		snapshot.displayVertices();
		
		return result;
	}

}
