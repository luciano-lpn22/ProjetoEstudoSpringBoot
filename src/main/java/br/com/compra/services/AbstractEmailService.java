package br.com.compra.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.compra.domain.Cliente;
import br.com.compra.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender  javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);
	}
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage sm= new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado: "+pedido.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		return sm;
	}	
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido",obj);
		return templateEngine.process("email/confirmacaoPedido.html", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
	
		try {
			MimeMessage mime = prepareMimeMessageFromPedido(pedido);
			sendHtmlEmail(mime);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(pedido);
		}
		
	}
	private MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper  helper=new MimeMessageHelper(mimeMessage,true);
		helper.setTo(pedido.getCliente().getEmail());
		helper.setFrom(sender);
		helper.setSubject("Pedido confirmado! Código: "+pedido.getId());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplatePedido(pedido),true);
		return mimeMessage;
	}
	
	@Override
	public void sendNewPassWordEmail(Cliente cliente, String newPas) {
		SimpleMailMessage sm=prepareNewPassWordEmail(cliente,newPas);
		sendEmail(sm);
	}
	protected SimpleMailMessage prepareNewPassWordEmail(Cliente cliente, String newPas) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova Senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova Senha :"+ newPas);
		return sm;
	}
}
