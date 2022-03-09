package tn.Pi.Service;

import java.time.LocalDateTime;


import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.Pi.JwtAndAuthConf.ConfirmationToken;
import tn.Pi.Repository.UserRepository;
import tn.Pi.entities.User;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceUser implements IServiceUser {
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ConfirmationTokenService confirmationTokenService;
	private final JavaMailSender mailSender;
	@Autowired
	ServiceConstants serviceConstants;

	@Override
	public String addUser(User u) {
		Date date = new Date(System.currentTimeMillis());
		User searchedUser = null;
		String token = null;
		try {
			log.info("in method addUser");
			searchedUser = userRepository.findOneByUserName(u.getUserName());
			if (searchedUser == null) {
				log.info("userName dont exist");
				u.setCreatedAt(date);
				Period period = Period.between(
						u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				u.setAge(period.getYears());
				u.setPassword(encoder.encode(u.getPassword()));

				userRepository.save(u);
				token = UUID.randomUUID().toString();
				ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
						LocalDateTime.now().plusMinutes(15), u);
				confirmationTokenService.saveConfirmationToken(confirmationToken);
				String link = "http://127.0.0.1:8089/SpringMVC/AppUser/confirm/" + token;
				send(u.getEmail(), buildEmail(u.getUserName(), link));

			} else {
				log.info("userName already exist!!");
			}
			log.info("out of method addUser without errors");
			// return token;
		} catch (Exception e) {
			log.error("error in method addUser" + e);
		}
		return token;
	}

	@Override
	public List<User> getUndeletedUser() {
		List<User> undeletedUsers = null;
		try {
			log.info("in method getUndeletedUsers");
			undeletedUsers = userRepository.getUndeletedUser();
			log.info("out of  method getUndeletedUsers without errors");
		} catch (Exception e) {
			log.error("error in method getUndeletedUsers" + e);
		}
		return undeletedUsers;
	}

	@Override
	public void deletedUser(Long id) {
		Date date = new Date(System.currentTimeMillis());
		try {
			log.info("in method deleteUser");
			User deletedUser = userRepository.findById(id).orElse(null);
			deletedUser.setDeleted(true);
			deletedUser.setDeletedAt(date);
			userRepository.save(deletedUser);
			log.info("out of  method deleteUser without errors");
		} catch (Exception e) {
			log.error("error in method deleteUser" + e);
		}

	}

	@Override
	public void updateUser(User u) {
		Date date = new Date(System.currentTimeMillis());
		try {
			log.info("in method updateUser");
			Period period = Period.between(u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			u.setAge(period.getYears());
			u.setModifiedAt(date);
			u.setPassword(encoder.encode(u.getPassword()));
			userRepository.save(u);
			log.info("out of  method updateUser without errors");
		} catch (Exception e) {
			log.error("error in method upadateUser" + e);
		}

	}

	public int enableAppUser(String email) {
		return userRepository.enableAppUser(email);
	}

	@Override
	public void send(String to, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("spacepro711@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			log.error("failed to send email", e);
			throw new IllegalStateException("failed to send email");
		}

	}

	@Override
	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
				.orElseThrow(() -> new IllegalStateException("token not found"));

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed");
		}

		LocalDateTime expiredAt = confirmationToken.getExpiresAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}

		confirmationTokenService.setConfirmedAt(token);
		enableAppUser(confirmationToken.getUser().getUserName());
		return "confirmed";
	}

	private String buildEmail(String name, String link) {
		return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" + "\n"
				+ "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" + "\n"
				+ "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
				+ "    <tbody><tr>\n" + "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" + "        \n"
				+ "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
				+ "          <tbody><tr>\n" + "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n"
				+ "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                  <tbody><tr>\n" + "                    <td style=\"padding-left:10px\">\n"
				+ "                  \n" + "                    </td>\n"
				+ "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
				+ "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
				+ "                    </td>\n" + "                  </tr>\n" + "                </tbody></table>\n"
				+ "              </a>\n" + "            </td>\n" + "          </tr>\n" + "        </tbody></table>\n"
				+ "        \n" + "      </td>\n" + "    </tr>\n" + "  </tbody></table>\n"
				+ "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
				+ "    <tbody><tr>\n" + "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n"
				+ "      <td>\n" + "        \n"
				+ "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                  <tbody><tr>\n"
				+ "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n"
				+ "                  </tr>\n" + "                </tbody></table>\n" + "        \n" + "      </td>\n"
				+ "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" + "    </tr>\n"
				+ "  </tbody></table>\n" + "\n" + "\n" + "\n"
				+ "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
				+ "    <tbody><tr>\n" + "      <td height=\"30\"><br></td>\n" + "    </tr>\n" + "    <tr>\n"
				+ "      <td width=\"10\" valign=\"middle\"><br></td>\n"
				+ "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
				+ "        \n"
				+ "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name
				+ ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
				+ link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>"
				+ "        \n" + "      </td>\n" + "      <td width=\"10\" valign=\"middle\"><br></td>\n"
				+ "    </tr>\n" + "    <tr>\n" + "      <td height=\"30\"><br></td>\n" + "    </tr>\n"
				+ "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" + "\n" + "</div></div>";
	}

	@Override
	public String forgotPassword(String userName, HttpServletRequest request) {
		User user = null;
		try {
			log.info("in method fogotPassword");
			user = userRepository.findOneByUserName(userName);
			if (user == null) {
				return "user not found";
			} else {

				user.setResettoken(UUID.randomUUID().toString());
				userRepository.save(user);
				String appUrl = request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

				String link = "http://" + appUrl + "/AppUser/reset/" + user.getResettoken();
				log.info(link);
				sendForgotPassworMail(user.getEmail(), buildResetPasswordEmail(user.getUserName(), link));

			}
			log.info("out of method fogotPassword without errors");
		} catch (Exception e) {
			log.error("error in method forgotPassword" + e);
		}

		return "";
	}

	public void sendForgotPassworMail(String to, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Reset your password");
			helper.setFrom("spacepro711@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			log.error("failed to send email", e);
			throw new IllegalStateException("failed to send email");
		}

	}

	private String buildResetPasswordEmail(String name, String link) {
		return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" + "\n"
				+ "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" + "\n"
				+ "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
				+ "    <tbody><tr>\n" + "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" + "        \n"
				+ "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
				+ "          <tbody><tr>\n" + "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n"
				+ "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                  <tbody><tr>\n" + "                    <td style=\"padding-left:10px\">\n"
				+ "                  \n" + "                    </td>\n"
				+ "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
				+ "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Reset your Password</span>\n"
				+ "                    </td>\n" + "                  </tr>\n" + "                </tbody></table>\n"
				+ "              </a>\n" + "            </td>\n" + "          </tr>\n" + "        </tbody></table>\n"
				+ "        \n" + "      </td>\n" + "    </tr>\n" + "  </tbody></table>\n"
				+ "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
				+ "    <tbody><tr>\n" + "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n"
				+ "      <td>\n" + "        \n"
				+ "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                  <tbody><tr>\n"
				+ "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n"
				+ "                  </tr>\n" + "                </tbody></table>\n" + "        \n" + "      </td>\n"
				+ "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" + "    </tr>\n"
				+ "  </tbody></table>\n" + "\n" + "\n" + "\n"
				+ "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
				+ "    <tbody><tr>\n" + "      <td height=\"30\"><br></td>\n" + "    </tr>\n" + "    <tr>\n"
				+ "      <td width=\"10\" valign=\"middle\"><br></td>\n"
				+ "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
				+ "        \n"
				+ "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name
				+ ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Please click on the below link to reset your password: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
				+ link + "\">Reset Now</a>  <p>See you soon</p>" + "        \n" + "      </td>\n"
				+ "      <td width=\"10\" valign=\"middle\"><br></td>\n" + "    </tr>\n" + "    <tr>\n"
				+ "      <td height=\"30\"><br></td>\n" + "    </tr>\n"
				+ "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" + "\n" + "</div></div>";
	}

	@Override
	public String fogetPasswordSetting(String token, String newPass) {
		User user = userRepository.findUserByresettoken(token);
		if (user != null) {
			user.setPassword(encoder.encode(newPass));
			user.setResettoken(null);
			userRepository.save(user);
			return "passwored reseted";

		} else {
			return "operation regected";
		}

	}

	@Override
	public List<User> orderUser(Integer pageNo, Integer pageSize, String sortBy, String Type) {
		System.out.println(Type);

		if (Type.equals("DESC")) {
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
			Page<User> pagedResult = userRepository.findAll(paging);
			return pagedResult.getContent();
		} else {
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			Page<User> pagedResult = userRepository.findAll(paging);
			return pagedResult.getContent();
		}

	}

}
