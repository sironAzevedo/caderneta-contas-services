package com.caderneta.kafka.consumer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.caderneta.model.dto.ContaDTO;
import com.caderneta.service.IContaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Profile("docker")
public class AccountConsumer {

	@Autowired
	private IContaService service;
	
	@KafkaListener(topics = "stream_new_account", groupId = "caderneta_account", containerFactory = "accountListenerContainerFactory")
	public void consume(ContaDTO message) throws IOException {
		log.info(String.format("Consuming stream_new_account, message: %s", message.toString()));
		service.create(message);
	}
}
