package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.KlijentBanke;

public class KorisnikDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT = "INSERT INTO korisnik (e_mail, ime, prezime, aktivan) values(?, ?, ?, 1)";
	private static final String SQL_SELECT_BY_ID = "SELECT COUNT(*) FROM korisnik WHERE e_mail = ? and aktivan = 1";
	
	public static boolean selectKorisnik(String email) {
		
		boolean rezultat = false;
		Object[] values = {email};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_BY_ID, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			rezultat = resultSet.getInt(1) == 1;
			preparedStatement.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		connectionPool.checkIn(conn);

		return rezultat;
		
	}
	
	
	
	public static boolean insert(KlijentBanke klijentBanke) {
		
		boolean rezultat = false;
		Connection conn = null;
		Object[] values = {klijentBanke.getEmail(), klijentBanke.getIme(), klijentBanke.getPrezime()};
		
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
	
}

