package com.caderneta.kafka.consumer;

import org.springframework.stereotype.Service;

@Service
public class AccountConsumer {

//	private final Logger logger = LoggerFactory.getLogger(AccountConsumer.class);
//
//	@Autowired
//	private IContaService service;
//	
//	@KafkaListener(topics = "stream_new_account", groupId = "caderneta_account", containerFactory = "accountListenerContainerFactory")
//	public void consume(ContaDTO message) throws IOException {
//		logger.info(String.format("Consuming stream_new_account, message: %s", message.toString()));
//		service.create(message);
//	}
}
