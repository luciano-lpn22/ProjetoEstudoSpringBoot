package br.com.compra.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.compra.domain.Cliente;
import br.com.compra.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage mailMessage);
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	void sendHtmlEmail(MimeMessage mailMessage);
	void sendNewPassWordEmail(Cliente cliente, String newPas);

}
