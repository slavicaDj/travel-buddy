package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dto.Oglas;

public class OglasDAO {

private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT = "INSERT INTO oglas (naziv,col,vrijeme_polaska,lokacija_polazak,lokacija_dolazak,broj_osoba,korisnik_id,mapa_polazak,mapa_dolazak) values(?,?,?,?,?,?,?,?,?);";
	private static final String SQL_SELECT_BY_KAT = "SELECT * FROM oglas WHERE aktivan = 1 AND zatvoren = 0 AND col = ?";
	private static final String SQL_SELECT_BY_ID_ALL = "SELECT * FROM oglas WHERE id = ?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM oglas WHERE id = ? AND aktivan = 1";
	private static final String SQL_SELECT_BY_KORISNIK_ID = "SELECT * FROM oglas WHERE korisnik_id = ? AND aktivan = 1";
	private static final String SQL_UPDATE = "UPDATE oglas SET naziv = ?, col = ?, vrijeme_polaska = ?, lokacija_polazak = ?, lokacija_dolazak = ?, broj_osoba = ?, mapa_polazak = ?, mapa_dolazak = ?, zatvoren = ? WHERE id = ?";
	private static final String SQL_DELETE = "UPDATE oglas SET aktivan = 0 WHERE id = ?";
	private static final String SQL_SELECT_BY_PERIOD = "SELECT * FROM oglas WHERE vrijeme_kreiranja > ? AND vrijeme_kreiranja < ?";

	public static ArrayList<Oglas> selectOglasiTrazimNudim(String kriterijum, Object parametar, String kategorija) {
		
		ArrayList<Oglas> oglasi = new ArrayList<>();
		Object[] values = {};
		String sqlSelectBy = "";
		
		if (kategorija != null) {
			if (!"".equals(parametar)) {
				if (parametar instanceof Date) {
					sqlSelectBy = "SELECT * FROM oglas WHERE aktivan = 1 and zatvoren = 0 and DATE(" + kriterijum + ") = ? and col = ?";
				}
				else {
					System.out.println("yep 1");
					sqlSelectBy = "SELECT * FROM oglas WHERE aktivan = 1 and zatvoren = 0 and " + kriterijum + " = ? and col = ?";
				}
				System.out.println("yep 2");
				values = new Object[2];
				values[0] = parametar;
				values[1] = kategorija;
			}
			else {
				sqlSelectBy = "SELECT * FROM oglas WHERE aktivan = 1 and zatvoren = 0 and col = ?";
				values = new Object[1];
				values[0] = kategorija;
			}
		}
		else {
			if (!"".equals(parametar)) {
				if (parametar instanceof Date) {
					sqlSelectBy = "SELECT * FROM oglas WHERE aktivan = 1 and zatvoren = 0 and DATE(" + kriterijum + ") = ?";
				}
				else {
					sqlSelectBy = "SELECT * FROM oglas WHERE aktivan = 1 and zatvoren = 0 and " + kriterijum + " = ?";
				}
				values = new Object[1];
				values[0] = parametar;
			}
			else {
				sqlSelectBy = "SELECT * FROM oglas WHERE aktivan = 1 and zatvoren = 0";
			}
		}
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, sqlSelectBy, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Oglas oglas = new Oglas();
				
				oglas.setId(resultSet.getInt("id"));
				oglas.setNaziv(resultSet.getString("naziv"));
				oglas.setVrijemeKreiranja(resultSet.getTimestamp("vrijeme_kreiranja"));
				oglas.setVrijemePolaska(resultSet.getTimestamp("vrijeme_polaska"));
				oglas.setKategorija(resultSet.getString("col"));
				oglas.setLokacijaDolazak(resultSet.getString("lokacija_dolazak"));
				oglas.setLokacijaPolazak(resultSet.getString("lokacija_polazak"));
				oglas.setBrojOsoba(resultSet.getInt("broj_osoba"));
				oglas.setZatvoren(resultSet.getBoolean("zatvoren"));
				int korisnikId = resultSet.getInt("korisnik_id");
				oglas.setKorisnik(KorisnikDAO.selectById(korisnikId));
				
				oglasi.add(oglas);
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return oglasi;
		
	}
	
	
	
	public static boolean insert(Oglas oglas) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {
						   oglas.getNaziv(), oglas.getKategorija(), oglas.getVrijemePolaska(), oglas.getLokacijaPolazak(), 
				           oglas.getLokacijaDolazak(), oglas.getBrojOsoba(), oglas.getKorisnik().getId(), 
				           oglas.getMapaPolazak(), oglas.getMapaDolazak()
				          };
		
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_INSERT, true, values);
			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			rezultat = resultSet.next();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return rezultat;
	}



	public static ArrayList<Oglas> selectOglasiTrazimNudim(String kategorija) {
		ArrayList<Oglas> oglasi = new ArrayList<>();
		Object[] values = {kategorija};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_KAT, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Oglas oglas = new Oglas();
				
				oglas.setId(resultSet.getInt("id"));
				oglas.setNaziv(resultSet.getString("naziv"));
				oglas.setVrijemeKreiranja(resultSet.getTimestamp("vrijeme_kreiranja"));
				oglas.setVrijemePolaska(resultSet.getTimestamp("vrijeme_polaska"));
				oglas.setKategorija(resultSet.getString("col"));
				oglas.setLokacijaDolazak(resultSet.getString("lokacija_dolazak"));
				oglas.setLokacijaPolazak(resultSet.getString("lokacija_polazak"));
				oglas.setBrojOsoba(resultSet.getInt("broj_osoba"));
				oglas.setZatvoren(resultSet.getBoolean("zatvoren"));

				int korisnikId = resultSet.getInt("korisnik_id");
				oglas.setKorisnik(KorisnikDAO.selectById(korisnikId));
				
				oglasi.add(oglas);
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return oglasi;
	}



	public static Oglas selectById(int id) {
		
		Oglas oglas = null;
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_ID, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				oglas = new Oglas();
				oglas.setId(resultSet.getInt("id"));
				oglas.setNaziv(resultSet.getString("naziv"));
				oglas.setVrijemeKreiranja(resultSet.getTimestamp("vrijeme_kreiranja"));
				oglas.setVrijemePolaska(resultSet.getTimestamp("vrijeme_polaska"));
				oglas.setKategorija(resultSet.getString("col"));
				oglas.setLokacijaDolazak(resultSet.getString("lokacija_dolazak"));
				oglas.setLokacijaPolazak(resultSet.getString("lokacija_polazak"));
				oglas.setBrojOsoba(resultSet.getInt("broj_osoba"));
				oglas.setZatvoren(resultSet.getBoolean("zatvoren"));

				int korisnikId = resultSet.getInt("korisnik_id");
				oglas.setKorisnik(KorisnikDAO.selectById(korisnikId));
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return oglas;
	}
	
	public static Oglas selectByIdAll(int id) {
		
		Oglas oglas = null;
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_ID_ALL, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				oglas = new Oglas();
				oglas.setId(resultSet.getInt("id"));
				oglas.setNaziv(resultSet.getString("naziv"));
				oglas.setVrijemeKreiranja(resultSet.getTimestamp("vrijeme_kreiranja"));
				oglas.setVrijemePolaska(resultSet.getTimestamp("vrijeme_polaska"));
				oglas.setKategorija(resultSet.getString("col"));
				oglas.setLokacijaDolazak(resultSet.getString("lokacija_dolazak"));
				oglas.setLokacijaPolazak(resultSet.getString("lokacija_polazak"));
				oglas.setBrojOsoba(resultSet.getInt("broj_osoba"));
				oglas.setZatvoren(resultSet.getBoolean("zatvoren"));

				int korisnikId = resultSet.getInt("korisnik_id");
				oglas.setKorisnik(KorisnikDAO.selectById(korisnikId));
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return oglas;
	}
	
	
	public static ArrayList<Oglas> selectByKorisnikId(int id) {
		
		ArrayList<Oglas> oglasi = new ArrayList<>();
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_KORISNIK_ID, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
								
				Oglas oglas = new Oglas();
				oglas.setId(resultSet.getInt("id"));
				oglas.setNaziv(resultSet.getString("naziv"));
				oglas.setVrijemeKreiranja(resultSet.getTimestamp("vrijeme_kreiranja"));
				oglas.setVrijemePolaska(resultSet.getTimestamp("vrijeme_polaska"));
				oglas.setKategorija(resultSet.getString("col"));
				oglas.setLokacijaDolazak(resultSet.getString("lokacija_dolazak"));
				oglas.setLokacijaPolazak(resultSet.getString("lokacija_polazak"));
				oglas.setBrojOsoba(resultSet.getInt("broj_osoba"));
				oglas.setZatvoren(resultSet.getBoolean("zatvoren"));

				int korisnikId = resultSet.getInt("korisnik_id");
				oglas.setKorisnik(KorisnikDAO.selectById(korisnikId));
				
				oglasi.add(oglas);
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return oglasi;
	}


	public static boolean update(Oglas oglas) {
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {
						   oglas.getNaziv(), oglas.getKategorija(), oglas.getVrijemePolaska(), oglas.getLokacijaPolazak(), 
				           oglas.getLokacijaDolazak(), oglas.getBrojOsoba(), 
				           oglas.getMapaPolazak(), oglas.getMapaDolazak(), oglas.isZatvoren(), oglas.getId()
				          };
		
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_UPDATE, true, values);

			if (ps.executeUpdate() != 0) {
				rezultat = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return rezultat;
	}
	
	public static boolean izbrisi(int id) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {id};
		
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_DELETE, true, values);

			if (ps.executeUpdate() != 0) {
				rezultat = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return rezultat;
	}
	
	
	public static ArrayList<Oglas> selectByPeriod(Date datumOd, Date datumDo) {
		
		ArrayList<Oglas> oglasi = new ArrayList<>();
		Object[] values = {datumOd, datumDo};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_PERIOD, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Oglas oglas = new Oglas();
				oglas.setId(resultSet.getInt("id"));
				oglas.setNaziv(resultSet.getString("naziv"));
				oglas.setVrijemeKreiranja(resultSet.getTimestamp("vrijeme_kreiranja"));
				oglas.setVrijemePolaska(resultSet.getTimestamp("vrijeme_polaska"));
				oglas.setKategorija(resultSet.getString("col"));
				oglas.setLokacijaDolazak(resultSet.getString("lokacija_dolazak"));
				oglas.setLokacijaPolazak(resultSet.getString("lokacija_polazak"));
				oglas.setBrojOsoba(resultSet.getInt("broj_osoba"));
				oglas.setZatvoren(resultSet.getBoolean("zatvoren"));

				int korisnikId = resultSet.getInt("korisnik_id");
				oglas.setKorisnik(KorisnikDAO.selectById(korisnikId));
				
				oglasi.add(oglas);
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		System.out.println("*******oglasi******** " + oglasi.size());
		return oglasi;
	}
	
}
