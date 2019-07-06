package br.com.compra.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.compra.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage mailMessage);

}
