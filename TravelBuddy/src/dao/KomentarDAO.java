package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Komentar;

public class KomentarDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT = "INSERT INTO komentar (autor_id, oglas_id, vrijeme, sadrzaj) values(?, ?, ?, ?)";
	private static final String SQL_SELECT_BY_OGLAS_ID = "SELECT * FROM komentar WHERE oglas_id = ? and aktivan = 1";
	private static final String SQL_SELECT_BY_ID_ALL = "SELECT * FROM komentar WHERE id = ?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM komentar WHERE id = ? and aktivan=1";
	private static final String SQL_DELETE = "UPDATE komentar set aktivan = 0 WHERE id = ? and aktivan=1";

	
	public static Komentar selectById(int id) {
		
		Komentar komentar = null;
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_ID, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				komentar = new Komentar();
				komentar.setId(resultSet.getInt("id"));
				komentar.setSadrzaj(resultSet.getString("sadrzaj"));
				komentar.setVrijeme(resultSet.getTimestamp("vrijeme"));
				int korisnikId = resultSet.getInt("autor_id");
				komentar.setKorisnik(KorisnikDAO.selectById(korisnikId));
				int oglasId = resultSet.getInt("oglas_id");				
				komentar.setOglas(OglasDAO.selectById(oglasId));
				komentar.setAktivan(resultSet.getBoolean("aktivan"));
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return komentar;
		
	}
	
	public static Komentar selectByIdAll(int id) {
		
		Komentar komentar = null;
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_ID_ALL, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				komentar = new Komentar();
				komentar.setId(resultSet.getInt("id"));
				komentar.setSadrzaj(resultSet.getString("sadrzaj"));
				komentar.setVrijeme(resultSet.getTimestamp("vrijeme"));
				int korisnikId = resultSet.getInt("autor_id");
				komentar.setKorisnik(KorisnikDAO.selectById(korisnikId));
				int oglasId = resultSet.getInt("oglas_id");				
				komentar.setOglas(OglasDAO.selectById(oglasId));
				komentar.setAktivan(resultSet.getBoolean("aktivan"));
				
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return komentar;
		
	}

	public static ArrayList<Komentar> selectByOglasId(int id) {
		
		ArrayList<Komentar> komentari = new ArrayList<>();
		Object[] values = {id};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_OGLAS_ID, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Komentar komentar = new Komentar();
				komentar.setId(resultSet.getInt("id"));
				komentar.setSadrzaj(resultSet.getString("sadrzaj"));
				komentar.setVrijeme(resultSet.getTimestamp("vrijeme"));
				int korisnikId = resultSet.getInt("autor_id");
				komentar.setKorisnik(KorisnikDAO.selectById(korisnikId));
				int oglasId = resultSet.getInt("oglas_id");
				komentar.setOglas(OglasDAO.selectById(oglasId));
				komentar.setAktivan(resultSet.getBoolean("aktivan"));

				komentari.add(komentar);
			}
			
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return komentari;
		
	}
	
	
	public static boolean insert(Komentar komentar) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {komentar.getKorisnik().getId(), komentar.getOglas().getId(), komentar.getVrijeme(), komentar.getSadrzaj()};
		
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


