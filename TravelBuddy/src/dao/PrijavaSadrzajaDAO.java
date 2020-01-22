package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PrijavaSadrzaja;

public class PrijavaSadrzajaDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT = "INSERT INTO prijava_sadrzaja (oglas_id, komentar_id, poruka_id, korisnik_id, napomena) VALUES(?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ALL = "SELECT * FROM prijava_sadrzaja WHERE aktivan = 1";
	private static final String SQL_IZBRISI_PRIJAVU = "UPDATE prijava_sadrzaja SET aktivan = 0 WHERE id = ?";

	
	public static ArrayList<PrijavaSadrzaja> selectAll() {
		
		ArrayList<PrijavaSadrzaja> prijave = new ArrayList<>();
		Object[] values = {};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_ALL, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				PrijavaSadrzaja prijava = new PrijavaSadrzaja();
				
				Integer oglasId = resultSet.getInt("oglas_id");
				if (oglasId != null) {
					prijava.setOglas(OglasDAO.selectByIdAll(oglasId));
				}
				Integer komentarId = resultSet.getInt("komentar_id");
				if (komentarId != null) {
					prijava.setKomentar(KomentarDAO.selectByIdAll(komentarId));
				}
				Integer porukaId = resultSet.getInt("poruka_id");
				if (oglasId != null) {
					prijava.setPoruka(PorukaDAO.selectByIdAll(porukaId));
				}
				
				int korisnikId = resultSet.getInt("korisnik_id");
				prijava.setKorisnik(KorisnikDAO.selectById(korisnikId));
				prijava.setAktivan(resultSet.getBoolean("aktivan"));
				prijava.setVrijeme(resultSet.getTimestamp("vrijeme"));
				prijava.setNapomena(resultSet.getString("napomena"));
				prijava.setId(resultSet.getInt("id"));
				prijave.add(prijava);
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return prijave;
		
	}
	
	
	public static boolean insert(PrijavaSadrzaja prijava) {
		
		boolean rezultat = false;
		Connection conn = null;
		
		Integer oglasId = (prijava.getOglas() != null) ? prijava.getOglas().getId() : null;
		Integer komentarId = (prijava.getKomentar() != null) ? prijava.getKomentar().getId() : null;
		Integer porukaId = (prijava.getPoruka() != null) ? prijava.getPoruka().getId() : null;

		Object[] values = {oglasId, komentarId, porukaId, prijava.getKorisnik().getId(), prijava.getNapomena()};
		
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
	
	
	public static boolean zatvoriPrijavu(PrijavaSadrzaja prijava) {
		
		boolean rezultat1 = false;
		boolean rezultat2 = false;
		Connection conn = null;
		Object[] values = {prijava.getId()};
		
		try {
			
			if (prijava.getKomentar() != null) {
				rezultat1 = KomentarDAO.izbrisi(prijava.getKomentar().getId());
			}
			else if (prijava.getOglas() != null) {
				rezultat1 = OglasDAO.izbrisi(prijava.getOglas().getId());
			}
			else {
				rezultat1 = PorukaDAO.izbrisi(prijava.getPoruka().getId());
			}
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_IZBRISI_PRIJAVU, true, values);

			if (ps.executeUpdate() != 0) {
				rezultat2 = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return rezultat1 && rezultat2;
	}
	
	public static boolean izbrisi(int id) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {id};
		
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_IZBRISI_PRIJAVU, true, values);

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

