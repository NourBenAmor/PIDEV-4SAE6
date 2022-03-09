package tn.Pi.Service;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.entities.BestWorstTrainig;
import tn.Pi.entities.Certifact;
import tn.Pi.entities.Training;
import tn.Pi.entities.UserTraining;
import tn.Pi.Repository.RepoCertificat;
import tn.Pi.Repository.RepoTrainig;
@Service
public class ServiceFormationImpl implements IServiceFormation {

	@Autowired
	RepoTrainig tr;
	@Autowired
	RepoCertificat cr;
	@Autowired
	EmailSenderService service;
	
	@Override
	public void ajouterFormation(Training formation) {
		tr.save(formation);
		/*List<User> users = new ArrayList<>();
		users = ur.findAll();
		for(User u: users)
		{
			service.sendSimpleEmail(u.getEmail(), "bonjour", "teseiufgalfyulaft ");
		}*/
			}

	@Override
	public Training ModifierFormation(Training formation, Long id) {
		long idd= formation.getIdFormation();
		if(id==idd)
		{
			Optional<Training> formations = tr.findById(id);

			if(formations!=null)
				tr.save(formation);
		}
		return formation;
	}

	@Override
	public void SupprimerFormation(Long id) {
	    
		tr.deleteById(id);
		
	}

	@Override
	public List<Training> ListedesFormation() {
		
		return (List<Training>)tr.findAll();
	}

	@Override
	public void ajouterCertificat(Certifact c) {
		cr.save(c);
		
	}
	

	@Override
	public Certifact ModifierCertificat(Certifact c, Long id) {
		long idd= c.getId();
		if(id==idd)
		{
			Optional<Certifact> certificats = cr.findById(id);

			if(certificats!=null)
			cr.save(c);
		}
		return c;
	}

	@Override
	public void supprimerCertificat(Long id) {
		cr.deleteById(id);
		
	}

	@Override
	public List<Certifact> ListedesCertificat() {
		return (List<Certifact>)cr.findAll();
	}
/*
	@Override
	public void dislikeTraining(Long idFormation, Long id) {
		Training t =tr.findById(idFormation).orElseGet(null);
		User u = ur.findById(id).orElseGet(null);
		Set<User> l = t.getUserdeslike();
		if(t.getUserlike().contains(u))
		{
			t.getUserlike().remove(u);
			l.add(u);
			t.setUserdeslike(l);
			}
		else
		{	l.add(u);}
		tr.save(t);
		
	}*/

	/*@Override
	public void likeAtraining(Long idFormation, Long id) {
		Training t = tr.findById(idFormation).orElseGet(null);
		User u = ur.findById(id).orElseGet(null);
		Set<User> l = t.getUserlike();
		if(t.getUserdeslike().contains(u))
		{
			t.getUserdeslike().remove(u);
			l.add(u);
			t.setUserlike(l); 
			}
		else
		{	if(t.getUserlike().contains(u)) {
			t.getUserlike().remove(u);	
		}
		else {l.add(u);}
		}
		tr.save(t);
	 
		}*/
		
	

	@Override
	public BestWorstTrainig besttraining() {
		// TODO Auto-generated method stub
		return tr.BestTraining();
	}

	@Override
	public BestWorstTrainig worsttraining() {
		// TODO Auto-generated method stub
		return tr.WorstTraining();
	}

	@Override
	public List<UserTraining> searchbyname(String name) {
		
		return tr.trainingtitle(name);
		//return null;
	}
	@Override
	@SneakyThrows
	public void pdf(Long id) {
		Certifact c = cr.findById(id).get();
		String path = "C:/CertificatP/" +c.getTraining().getTitel();
	       File pathAsFile = new File(path);

	       if (!Files.exists(Paths.get(path)))
	       {
	           pathAsFile.mkdir();
	       }
	     
	       PdfReader pdfReader =
	               new PdfReader("C:/Certificat.pdf");
	       PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("C:/CertificatP/" + c.getTraining().getTitel() +".pdf"));
	       BaseFont baseFont = BaseFont.createFont(
	               BaseFont.TIMES_ROMAN,
	               BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	      int pages = pdfReader.getNumberOfPages();

	       for(int i=1; i<=pages; i++) {
	           //Contain the pdf data.
	           PdfContentByte pageContentByte =
	                   pdfStamper.getOverContent(i);

	           pageContentByte.beginText();
	           //Set text font and size.
	           pageContentByte.setFontAndSize(baseFont, 14);

	           pageContentByte.setTextMatrix(50, 740);

	           //Write text
	          QRCodeWriter barcodeWriter = new QRCodeWriter();
	           String info = "Titre de la Formation:\t"+ c.getTraining().getTitel()+"\tDescription:\t"+ c.getDescription()+"\tCachet\t"+ c.getCachet()+"\tDate:\t"+ c.getDateCertif();
	           BitMatrix bitMatrix = barcodeWriter.encode(info, BarcodeFormat.QR_CODE, 200, 200);
	           BufferedImage I = MatrixToImageWriter.toBufferedImage(bitMatrix);


	           File outputfile = new File("image.jpg");
	           ImageIO.write(I, "jpg", outputfile);

	           Image QR = Image.getInstance("image.jpg");
	           QR.setAbsolutePosition(10  , 10);
	           pageContentByte.addImage(QR);
	           pageContentByte.showText("Description:\t " + c.getDescription() + "\n");
	           pageContentByte.moveText(0, 15);
	           pageContentByte.showText("Cachet: \t" + c.getCachet() + "\n");
	           pageContentByte.moveText(0, 15);
	           pageContentByte.showText("Date: \t" + c.getDateCertif() + "\n");
	           pageContentByte.moveText(0, 15);
	           pageContentByte.endText();
	       }
	       pdfStamper.close();
	}
	@Override
	public List<Training> searchTraining(String keyword) {
		List<Training> listForums = new ArrayList<Training>();
		
			listForums = (List<Training>) tr.search(keyword);
			
		 
			return listForums;
	}
	
}