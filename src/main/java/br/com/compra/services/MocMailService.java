package br.com.compra.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MocMailService extends AbstractEmailService{
	
	private static final Logger LOG= LoggerFactory.getLogger(MocMailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de e-mail");
		LOG.info(msg.toString());
		LOG.info("Email Invalido");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando envio de e-mail HTML");
		LOG.info(msg.toString());
		LOG.info("Email HTML Invalido");
		
	}

}
