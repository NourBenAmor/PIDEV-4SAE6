package tn.Pi.Service;

import java.awt.image.BufferedImage;


import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import tn.Pi.Repository.UserRepository;
import tn.Pi.entities.ConfirmationToken;
import tn.Pi.entities.User;
import tn.Pi.entities.UserRole;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceUser implements IServiceUser,UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Autowired
    PasswordEncoder encoder;
	@Autowired
	ConfirmationTokenService confirmationTokenService;
	private final JavaMailSender mailSender;
	

	@Override
	@SneakyThrows
	public String addUser(User u) {
		Date date = new Date(System.currentTimeMillis());
		
		String token=null;
		
			u.setCreatedAt(date);
			Period period = Period.between(u.getBirthDate()
					                        .toInstant()
					                        .atZone(ZoneId.systemDefault())
					                        .toLocalDate(),
					                       date
					                        .toInstant()
					                        .atZone(ZoneId.systemDefault())
					                        .toLocalDate());
			u.setAge(period.getYears());
			u.setPassword(encoder.encode(u.getPassword()));
			
			//tache sanka7
			String path = "C:/UserA/" +u.getLastName();
	        File pathAsFile = new File(path);

	        if (!Files.exists(Paths.get(path)))
	        {
	            pathAsFile.mkdir();
	        }
	       
	        PdfReader pdfReader =
	                new PdfReader("C:/USER.pdf");
	        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("C:/UserA/" + u.getLastName() +"/ "+ u.getFirstName() +".pdf"));
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
	            String info = "FirstName:"+ u.getFirstName()+ "\n" +"LastName:" + u.getLastName()  ;
	            BitMatrix bitMatrix = barcodeWriter.encode(info, BarcodeFormat.QR_CODE, 200, 200);
	            BufferedImage I = MatrixToImageWriter.toBufferedImage(bitMatrix);


	            File outputfile = new File("image.jpg");
	            ImageIO.write(I, "jpg", outputfile);

	            Image QR = Image.getInstance("image.jpg");
	            QR.setAbsolutePosition(10  , 10);
	            pageContentByte.addImage(QR);
	            pageContentByte.showText("Email: " + u.getEmail() + "\n");
	            pageContentByte.moveText(0, 15);
	            pageContentByte.showText("UserName: " + u.getUsername() + "\n");
	            pageContentByte.moveText(0, 15);
	            pageContentByte.showText("FirstName: " + u.getFirstName() + "\n");
	            pageContentByte.moveText(0, 15);
	            pageContentByte.showText("LastName: " + u.getLastName() +"\n");
	            pageContentByte.moveText(0, 15);
	            pageContentByte.showText("age: "+ u.getAge()+ "\n");
	            pageContentByte.moveText(0, 15);
	            pageContentByte.showText("Created: " + u.getCreatedAt() +"\n" );
	            pageContentByte.moveText(0, 20);
	   


	            pageContentByte.endText();
	        }
	        pdfStamper.close();
			//tache sanka7
			userRepository.save(u);
			 token =UUID.randomUUID().toString();
			ConfirmationToken confirmationToken = new ConfirmationToken(
					token,
					LocalDateTime.now(),
					LocalDateTime.now().plusMinutes(15),
					u
					);
			confirmationTokenService.saveConfirmationToken(confirmationToken);
			String link="http://127.0.0.1:8089/SpringMVC/AppUser/confirm/"+token;
			send(u.getEmail(),buildEmail(u.getUsername(),link));
			
			
			
		return token;
	}

	public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
//------------
	
	//---userDetailsService--------------------------
@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		return userRepository.findOneByUserName(username);
	}
//---------------userDetailsServiceEnd--------------------------

@Override
public void send(String to, String email) {
	 try {
         MimeMessage mimeMessage = mailSender.createMimeMessage();
           MimeMessageHelper helper =
                 new MimeMessageHelper(mimeMessage, "utf-8");
         helper.setText(email, true);
         helper.setTo(to);
         helper.setSubject("Confirm your email");
         helper.setFrom("btravel381@gmail.com");
         mailSender.send(mimeMessage);
     } catch (MessagingException e) {
         log.error("failed to send email", e);
         throw new IllegalStateException("failed to send email");
     }
	
}
@Override
@Transactional
public String confirmToken(String token) {
    ConfirmationToken confirmationToken = confirmationTokenService
            .getToken(token)
            .orElseThrow(() ->
                    new IllegalStateException("token not found"));

    if (confirmationToken.getConfirmedAt() != null) {
        throw new IllegalStateException("email already confirmed");
    }

    LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    if (expiredAt.isBefore(LocalDateTime.now())) {
        throw new IllegalStateException("token expired");
    }

    confirmationTokenService.setConfirmedAt(token);
    enableAppUser(
            confirmationToken.getUser().getUsername());
    return "confirmed";
}
public String register(RegistrationRequest request) {
   

    String token = addUser(
            new User(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getUserName(),
                    request.getPassword(),
                    UserRole.USER

            )
    );

    String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
    send(
            request.getEmail(),
            buildEmail(request.getFirstName(), link));

    return token;
}
private String buildEmail(String name, String link) {
    return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
            "\n" +
            "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
            "\n" +
            "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "    <tbody><tr>\n" +
            "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
            "        \n" +
            "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
            "          <tbody><tr>\n" +
            "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
            "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                  <tbody><tr>\n" +
            "                    <td style=\"padding-left:10px\">\n" +
            "                  \n" +
            "                    </td>\n" +
            "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
            "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </tbody></table>\n" +
            "              </a>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </tbody></table>\n" +
            "        \n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </tbody></table>\n" +
            "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
            "    <tbody><tr>\n" +
            "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
            "      <td>\n" +
            "        \n" +
            "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                  <tbody><tr>\n" +
            "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
            "                  </tr>\n" +
            "                </tbody></table>\n" +
            "        \n" +
            "      </td>\n" +
            "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
            "    </tr>\n" +
            "  </tbody></table>\n" +
            "\n" +
            "\n" +
            "\n" +
            "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
            "    <tbody><tr>\n" +
            "      <td height=\"30\"><br></td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
            "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
            "        \n" +
            "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
            "        \n" +
            "      </td>\n" +
            "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td height=\"30\"><br></td>\n" +
            "    </tr>\n" +
            "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
            "\n" +
            "</div></div>";
}
@Override
public List<User> getAllUser() {

	return userRepository.findAll();
}

@Override
public List<User> getUndeletedUser() {
	return userRepository.getUndeletedUser();
}

@Override
public void deletedUser(Long id) {

	Date date = new Date(System.currentTimeMillis());
	User deletedUser = userRepository.findById(id).orElse(null);
	deletedUser.setDeletedAt(date);
	deletedUser.setDeleted(true);
	userRepository.save(deletedUser);

}


public void updateUser(User u)
{
	
	Date date = new Date(System.currentTimeMillis());
	u.setCreatedAt(date);
	Period period = Period.between(u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
			date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	u.setAge(period.getYears());
	u.setModifiedAt(date);
	userRepository.save(u);
	
}



}
