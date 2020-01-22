package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dto.Korisnik;
import dto.Poruka;

public class PorukaDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	
	private static final String SQL_INSERT = "INSERT INTO poruka (posiljalac_id, primalac_id, sadrzaj) values(?, ?, ?)";
	private static final String SQL_SELECT_BY_ID_ALL = "SELECT * FROM poruka WHERE id = ?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM poruka WHERE id = ? and aktivan=1";
	private static final String SQL_SELECT_BY_UCESNICI_ID = "SELECT * FROM poruka WHERE ((posiljalac_id = ? AND primalac_id = ?) OR (posiljalac_id = ? AND primalac_id = ?)) and aktivan=1";
	private static final String SQL_SELECT_MOJI_KONTAKTI = "SELECT k.* FROM poruka p INNER JOIN korisnik k ON " + 
															"(k.id = posiljalac_id OR k.id = primalac_id) " +
															"WHERE (posiljalac_id = ? OR primalac_id = ? AND k.aktivan = ? AND k.odobren = ?) " +
															"GROUP BY k.ime, k.prezime " +
															"HAVING k.id != ?";
	private static final String SQL_NAJNOVIJA_PORUKA_KONTAKTA = "SELECT * FROM poruka WHERE ((posiljalac_id = ? AND primalac_id = ?) OR (posiljalac_id = ? AND primalac_id = ?)) and aktivan=1 ORDER BY vrijeme DESC LIMIT 1";
	private static final String SQL_DELETE = "UPDATE poruka SET aktivan = 0 WHERE id = ?";
	
	public static ArrayList<Poruka> selectByUcesniciId(int id1, int id2) {
		
		ArrayList<Poruka> poruke = new ArrayList<>();
		Object[] values = {id1, id2, id2, id1};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_UCESNICI_ID, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Poruka poruka = new Poruka();
				
				poruka.setId(resultSet.getInt("id"));
				
				int primalacId = resultSet.getInt("primalac_id");
				Korisnik primalac = KorisnikDAO.selectById(primalacId);
				poruka.setPrimalac(primalac);
				
				int posiljalacId = resultSet.getInt("posiljalac_id");
				Korisnik posiljalac = KorisnikDAO.selectById(posiljalacId);
				poruka.setPosiljalac(posiljalac);
				
				poruka.setSadrzaj(resultSet.getString("sadrzaj"));
				poruka.setVrijeme(resultSet.getTimestamp("vrijeme"));
				
				poruka.setAktivan(resultSet.getBoolean("aktivan"));
				
				poruke.add(poruka);
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return poruke;
		
	}
	
	public static ArrayList<Korisnik> selectMojiKontakti(int mojId) {
		
		ArrayList<Korisnik> kontakti = new ArrayList<>();
		Object[] values = {mojId, mojId, mojId, mojId, mojId};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_MOJI_KONTAKTI, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Korisnik korisnik = new Korisnik();
				korisnik.setId(resultSet.getInt("id"));
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				korisnik.setEmail(resultSet.getString("e_mail"));
				korisnik.setDatumRodjenja(resultSet.getTimestamp("datum_rodjenja"));
				korisnik.setAdmin(resultSet.getBoolean("admin"));
				
				kontakti.add(korisnik);
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return kontakti;
		
	}
	
	
	public static Poruka selectNajnovijaPorukaOdKontakta(int mojId, int kontaktId) {
		
		Poruka poruka = null;
		Object[] values = {mojId, kontaktId, kontaktId, mojId};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_NAJNOVIJA_PORUKA_KONTAKTA, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
						
				poruka = new Poruka();
				
				poruka.setId(resultSet.getInt("id"));
				
				int primalacId = resultSet.getInt("primalac_id");
				Korisnik primalac = KorisnikDAO.selectById(primalacId);
				poruka.setPrimalac(primalac);
				
				int posiljalacId = resultSet.getInt("posiljalac_id");
				Korisnik posiljalac = KorisnikDAO.selectById(posiljalacId);
				poruka.setPosiljalac(posiljalac);
				
				poruka.setSadrzaj(resultSet.getString("sadrzaj"));
				
				Date vrijeme = resultSet.getTimestamp("vrijeme");
				
				poruka.setAktivan(resultSet.getBoolean("aktivan"));

				poruka.setVrijeme(vrijeme);
				
				
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return poruka;
		
	}
	
	public static Poruka selectById(int id) {
		
		Poruka poruka = null;
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_ID, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
						
				poruka = new Poruka();
				poruka.setId(resultSet.getInt("id"));
				
				int primalacId = resultSet.getInt("primalac_id");
				Korisnik primalac = KorisnikDAO.selectById(primalacId);
				poruka.setPrimalac(primalac);
				
				int posiljalacId = resultSet.getInt("posiljalac_id");
				Korisnik posiljalac = KorisnikDAO.selectById(posiljalacId);
				poruka.setPosiljalac(posiljalac);
				
				poruka.setSadrzaj(resultSet.getString("sadrzaj"));
				
				Date vrijeme = resultSet.getTimestamp("vrijeme");
				
				poruka.setAktivan(resultSet.getBoolean("aktivan"));

				poruka.setVrijeme(vrijeme);
				
				
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return poruka;
		
	}
	
public static Poruka selectByIdAll(int id) {
		
		Poruka poruka = null;
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_ID_ALL, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
						
				poruka = new Poruka();
				poruka.setId(resultSet.getInt("id"));
				
				int primalacId = resultSet.getInt("primalac_id");
				Korisnik primalac = KorisnikDAO.selectById(primalacId);
				poruka.setPrimalac(primalac);
				
				int posiljalacId = resultSet.getInt("posiljalac_id");
				Korisnik posiljalac = KorisnikDAO.selectById(posiljalacId);
				poruka.setPosiljalac(posiljalac);
				
				poruka.setSadrzaj(resultSet.getString("sadrzaj"));
				
				Date vrijeme = resultSet.getTimestamp("vrijeme");
				
				poruka.setAktivan(resultSet.getBoolean("aktivan"));

				poruka.setVrijeme(vrijeme);
				
				
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return poruka;
		
	}
	
	
	
	public static boolean insert(Poruka poruka) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {poruka.getPosiljalac().getId(), poruka.getPrimalac().getId(), poruka.getSadrzaj()};
		
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
	
}



