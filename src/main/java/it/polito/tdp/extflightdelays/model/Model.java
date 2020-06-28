package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	
	private ExtFlightDelaysDAO dao ;
	private Map<Integer,Airport> mappaA;
	private Graph<Airport,DefaultWeightedEdge> grafo;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
		this.mappaA = new HashMap<Integer,Airport>();
		}
	
	public List<Airport> getAeroporti(){
		List<Airport> listaAereoporti = new LinkedList<Airport>(this.dao.loadAllAirports());
		return listaAereoporti;
		
	} 
	
	
	public void creaGrafo(double d) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		System.out.println(this.dao.loadAllAirports());
		Graphs.addAllVertices(this.grafo, this.getAeroporti());
		this.dao.loadAllAirports2(mappaA);
		for(Arco a : this.dao.getArchi(d, this.mappaA)) {
			if(this.grafo.containsVertex(a.getA1()) && this.grafo.containsVertex(a.getA2())) {
				DefaultWeightedEdge edge = this.grafo.getEdge(a.getA1(), a.getA2());
				if(edge==null) {
					Graphs.addEdgeWithVertices(this.grafo,a.getA1(), a.getA2() ,a.getPeso());
					
			}
				else {
					double v = this.grafo.getEdgeWeight(edge);
					this.grafo.setEdgeWeight(edge, (v+a.getPeso())/2);
				}
			}
		}
		System.out.println(this.grafo);
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<AirportDistance> getAdiacenti(Airport a){
		List<AirportDistance> lAdiacenti = new LinkedList<AirportDistance>();
		
		for(Airport r: Graphs.neighborListOf(this.grafo, a)) {
			double p = this.grafo.getEdgeWeight(this.grafo.getEdge(a, r));
			AirportDistance flughafen = new AirportDistance(r,p);
			lAdiacenti.add(flughafen);
			
		}
		
		return lAdiacenti;
		
	}

}
