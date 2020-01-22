package database.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;

import database.dto.Reklama;

public class ReklamaDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_SELECT_REKLAME_ZA_DANAS = "SELECT id, sadrzaj, slika_path FROM reklama WHERE datum_pocetka <= now() AND datum_zavrsetka >= now()";
	private static final String SQL_INSERT = "INSERT INTO reklama (sadrzaj, slika_path, e_mail_korisnika, datum_pocetka, datum_zavrsetka, cijena_po_danu) values(?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE reklama SET slika_path = ? where id = ?";
	
	public static ArrayList<Reklama> selectReklameZaDanas() {
		
		ArrayList<Reklama> reklame = new ArrayList<>();
		Object[] values = {};
		
		Connection conn = null;
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_SELECT_REKLAME_ZA_DANAS, false, values);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Reklama reklama = new Reklama();
				
				reklama.setId(resultSet.getInt("id"));
				reklama.setSadrzaj(resultSet.getString("sadrzaj"));
				String slikaPath = resultSet.getString("slika_path");
				String slikaString = null;
				String nazivFajla = null;
				
				if (slikaPath != null) {
					BufferedImage bImage = null;
			        ByteArrayOutputStream bos = null;
					try {
						File file = new File(slikaPath);
						nazivFajla = file.getName();
						if (file.exists()) {
							String ekstenzija = nazivFajla.split("\\.")[1];
							bImage = ImageIO.read(file);
							bos = new ByteArrayOutputStream();
					        ImageIO.write(bImage, ekstenzija, bos);
					        byte [] fileBytes = bos.toByteArray();						      
						    slikaString = Base64.getEncoder().encodeToString(fileBytes);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			        
				}
				reklama.setSlikaString(slikaString);
				reklama.setNaziv(nazivFajla);
				reklame.add(reklama);
			}
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return reklame;
		
	}
	
	
	
	public static int insert(Reklama reklama) {
		
		int generatedKey = 0;
		Connection conn = null;
		Object[] values = {reklama.getSadrzaj(), reklama.getSlikaPath(), reklama.getEmailKorisnika(), reklama.getDatumPocetka(), reklama.getDatumZavrsetka(), reklama.getCijenaPoDanu()};
		
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_INSERT, true, values);
			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			generatedKey = resultSet.getInt(1);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return generatedKey;
	}
	
	
	
	public static boolean updateSlikaPath(String slikaPath, int id) {
		
		boolean uspjesno = false;
		Connection conn = null;
		Object[] values = {slikaPath, id};
		
		try {
			
			conn = connectionPool.checkOut();
			PreparedStatement preparedStatement = DAOUtil.prepareStatement(conn, SQL_UPDATE, false, values);
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				uspjesno = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return uspjesno;
	}
	
}
