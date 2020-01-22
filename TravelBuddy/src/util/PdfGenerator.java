package util;

import java.io.File;
import java.util.Calendar;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import model.Karta;
import model.KlijentBanke;
import net.glxn.qrgen.QRCode;

public class PdfGenerator {
	
	public static final String dir = "C:/Users/HP KORISNIK/Downloads/workspace/TravelBuddy/WebContent/resources/karte/";
	
	public static File generisiKartu(KlijentBanke klijent, Karta karta) {
		
		File file = null;
	
		try {
			
			long timeInMillis =  Calendar.getInstance().getTimeInMillis();
			String fullPath = dir + timeInMillis + ".pdf";
	        PdfWriter writer = new PdfWriter(fullPath);
	        PdfDocument pdf = new PdfDocument(writer);
	        Document document = new Document(pdf);
	 
	        document.add(new Paragraph("Travel Tickets"));
	        
	        QRCode qrCode = QRGenerator.getQR(timeInMillis);
	        byte[] imageBytes = qrCode.stream().toByteArray();
	        Image qrCodeImage = new Image(ImageDataFactory.create(imageBytes));
	        document.add(qrCodeImage);
	        
	        float[] columnWidths = {5, 5};
	        Table table = new Table(columnWidths);
	        table.setWidthPercent(100);
	        
	        Cell cellHeaderPutnik = new Cell();
	        cellHeaderPutnik.add("Podaci o putniku");
	        
	        Cell cellHeaderKarta = new Cell();
	        cellHeaderKarta.add("Podaci o karti");

	        Cell cellPutnik = new Cell(4,1);
	        cellPutnik.add(new Paragraph(klijent.getIme() + " " + klijent.getPrezime()));
	        cellPutnik.add(new Paragraph(klijent.getEmail()));
	       
	        
	        String vrijemePolaskaFormatirano = Util.parseDate(karta.getVrijemePolaska(), "dd/MM/yyy HH:mm");
	        String vrijemeDolaskaFormatirano = Util.parseDate(karta.getVrijemeDolaska(), "dd/MM/yyy HH:mm");
	        
	        Cell cellKarta = new Cell(4,1);
	        cellKarta.add(new Paragraph(karta.getPolaziste() + " -> " + karta.getDestinacija()));
	        cellKarta.add(new Paragraph(vrijemePolaskaFormatirano + " -> " + vrijemeDolaskaFormatirano));
	        cellKarta.add(new Paragraph(karta.getPrevoz().toString()));
	        cellKarta.add(new Paragraph("" + karta.getCijena() + "KM"));
	        
	        table.addCell(cellHeaderPutnik);table.addCell(cellHeaderKarta);table.addCell(cellPutnik);table.addCell(cellKarta);
	        
	        document.add(table);
	        
	        document.close();
			
	        file = new File(fullPath);

			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
		
	}
	
	
	public static void main(String[] args) {
		
		
		try {

			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
