package model;

public enum Prevoz {
	
	autobus, avion, voz ;
	
	@Override
	public String toString() {
		
		switch(this) {
		case autobus:
			return "Autobus";
		case avion:
			return "Avion";
		case voz:
			return "Voz";
		default:
			return "";
		}
		
	}

}
