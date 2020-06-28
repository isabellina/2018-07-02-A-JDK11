package it.polito.tdp.extflightdelays.model;

public class AirportDistance implements Comparable<AirportDistance> {
	
	private Airport aereoportoPartenza;
	private double peso;
	
	
	
	public AirportDistance(Airport aereoportoPartenza, double peso) {
		super();
		this.aereoportoPartenza = aereoportoPartenza;
		this.peso = peso;
	}

    

	public Airport getA() {
		return aereoportoPartenza;
	}



	public void setA(Airport a) {
		this.aereoportoPartenza = a;
	}



	public double getPeso() {
		return peso;
	}



	public void setPeso(double peso) {
		this.peso = peso;
	}

    

	@Override
	public String toString() {
		return "AirportDistance [a=" + aereoportoPartenza + ", peso=" + peso + "]";
	}



	@Override
	public int compareTo(AirportDistance o) {
		// TODO Auto-generated method stub
		
		//return (this.peso.compareTo(o.getPeso())*-1);
		return 0;
	} 
	
	

}
