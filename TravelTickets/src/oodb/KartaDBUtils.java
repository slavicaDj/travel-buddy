package oodb;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import model.Karta;

public class KartaDBUtils {

	private static final String dbName = "C:/Users/HP KORISNIK/Downloads/workspace/TravelTickets/karte.oodb";
	
	public static void insert(Karta karta){
		
		ObjectContainer db = Db4oEmbedded.openFile(dbName);
		db.store(karta);
		db.close();
		
	}
	
	public static ArrayList<Karta> selectAll(){
		
		ArrayList<Karta> karte = new ArrayList<>();
		ObjectContainer db = Db4oEmbedded.openFile(dbName);
		
		ObjectSet<Karta> driveSet = db.queryByExample(new Karta(null, null, null, null, null, null));
		while (driveSet.hasNext()) {
			karte.add(driveSet.next());
		}
		
		db.close();
		return karte;
		
	}
	
	public static ArrayList<Karta> selectPoDestinaciji(String destinacija) {
		
		ArrayList<Karta> karte = new ArrayList<>();
		ObjectContainer db = Db4oEmbedded.openFile(dbName);
		
		ObjectSet<Karta> driveSet = db.queryByExample(new Karta(null, destinacija, null, null, null, null));
		while (driveSet.hasNext()) {
			karte.add(driveSet.next());
		}
		
		db.close();
		return karte;
		
	}
	
	/*
	public static void main (String args[]) {
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(2018, 8, 20, 15, 30);
		Date polazak1 = cal.getTime();
		cal.add(Calendar.HOUR, 3);
		Date dolazak1 = cal.getTime();
		
		cal.set(2018, 8, 30, 20, 00);
		Date polazak2 = cal.getTime();
		cal.add(Calendar.HOUR, 1);
		Date dolazak2 = cal.getTime();
		
		Karta karta1 = new Karta("Banja Luka", "Novi Sad", Prevoz.autobus, 22.50, polazak1, dolazak1);
		Karta karta2 = new Karta("Bijeljina", "Novi Sad", Prevoz.autobus, 10.50, polazak2, dolazak2);
		Karta karta3 = new Karta("Niš", "Beograd", Prevoz.autobus, 10.00 , polazak1, dolazak2);
		Karta karta4 = new Karta("Banja Luka", "Zagreb", Prevoz.autobus, 19.90, polazak2, dolazak2);
		
		KartaDBUtils.insert(karta1);
		KartaDBUtils.insert(karta2);
		KartaDBUtils.insert(karta3);
		KartaDBUtils.insert(karta4);
		
//		ArrayList<Karta> karte = KartaDBUtils.selectAll();
//		for (Karta k : karte) {
//			System.out.println(k);
//		}
	}
	*/
}
