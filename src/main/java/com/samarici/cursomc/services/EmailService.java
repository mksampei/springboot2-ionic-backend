package com.samarici.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.samarici.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
