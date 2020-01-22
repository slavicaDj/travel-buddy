package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dto.Korisnik;

public class KorisnikDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT = "INSERT INTO korisnik (ime, prezime, korisnicko_ime, lozinka, e_mail, datum_rodjenja, aktivan) values(?, ?, ?, ?, ?, ?, 1)";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM korisnik WHERE korisnicko_ime = ?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM korisnik WHERE id = ?";
	private static final String SQL_SELECT_NEODOBRENI_NALOZI =  "SELECT * FROM korisnik where odobren = 0 and aktivan = 1";
	private static final String SQL_UPDATE_ODOBRI_NALOG =  "UPDATE korisnik SET odobren = 1 WHERE id = ?";
	private static final String SQL_UPDATE_BLOKIRAJ_NALOG =  "UPDATE korisnik SET aktivan = 0 WHERE id = ?";
	private static final String SQL_SELECT_REGISTRACIJE_PERIOD =  "SELECT * FROM korisnik WHERE datum_registracije >= ? and datum_registracije <= ?";


	public static Korisnik selectKorisnik(String username) {
		
		Korisnik korisnik = null;
		Object[] values = {username};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_USERNAME, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				korisnik = new Korisnik();
				korisnik.setId(resultSet.getInt("id"));
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				korisnik.setLozinka(resultSet.getString("lozinka"));
				korisnik.setEmail(resultSet.getString("e_mail"));
				korisnik.setDatumRodjenja(resultSet.getTimestamp("datum_rodjenja"));
				korisnik.setAdmin(resultSet.getBoolean("admin"));
				korisnik.setAktivan(resultSet.getBoolean("aktivan"));
				korisnik.setOdobren(resultSet.getBoolean("odobren"));
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return korisnik;
		
	}
	
	
	public static boolean insert(Korisnik korisnik) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {korisnik.getIme(), korisnik.getPrezime(), korisnik.getKorisnickoIme(), korisnik.getLozinka(), korisnik.getEmail(), korisnik.getDatumRodjenja()};
		
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



	public static Korisnik selectById(int id) {
		
		Korisnik korisnik = null;
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_ID, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				korisnik = new Korisnik();
				korisnik.setId(resultSet.getInt("id"));
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				korisnik.setLozinka("");
				korisnik.setEmail(resultSet.getString("e_mail"));
				korisnik.setDatumRodjenja(resultSet.getTimestamp("datum_rodjenja"));
				korisnik.setAdmin(resultSet.getBoolean("admin"));
				korisnik.setAktivan(resultSet.getBoolean("aktivan"));
				korisnik.setOdobren(resultSet.getBoolean("odobren"));
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return korisnik;
		
	}
	
	public static ArrayList<Korisnik> selectNeodobreniNalozi() {
		
		ArrayList<Korisnik> korisnici = new ArrayList<>();
		Object[] values = {};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_NEODOBRENI_NALOZI, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Korisnik korisnik = new Korisnik();
				korisnik.setId(resultSet.getInt("id"));
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				korisnik.setLozinka("");
				korisnik.setEmail(resultSet.getString("e_mail"));
				korisnik.setDatumRodjenja(resultSet.getTimestamp("datum_rodjenja"));
				korisnik.setAdmin(resultSet.getBoolean("admin"));
				korisnik.setAktivan(resultSet.getBoolean("aktivan"));
				korisnik.setOdobren(resultSet.getBoolean("odobren"));
				
				korisnici.add(korisnik);
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return korisnici;
		
	}
	
	
	public static boolean odobriNalog(int id) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {id};
		
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_UPDATE_ODOBRI_NALOG, true, values);

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
	
	public static boolean deaktivirajNalog(int id) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {id};
		
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_UPDATE_BLOKIRAJ_NALOG, true, values);

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
	
	
	public static ArrayList<Korisnik> selectRegistracijePeriod(Date datumOd, Date datumDo) {
		
		ArrayList<Korisnik> korisnici = new ArrayList<>();
		Object[] values = {datumOd, datumDo};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_REGISTRACIJE_PERIOD, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Korisnik korisnik = new Korisnik();
				korisnik.setId(resultSet.getInt("id"));
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				korisnik.setLozinka("");
				korisnik.setEmail(resultSet.getString("e_mail"));
				korisnik.setDatumRodjenja(resultSet.getTimestamp("datum_rodjenja"));
				korisnik.setAdmin(resultSet.getBoolean("admin"));
				korisnik.setAktivan(resultSet.getBoolean("aktivan"));
				korisnik.setOdobren(resultSet.getBoolean("odobren"));
				
				korisnici.add(korisnik);
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return korisnici;
		
	}
}

