package com.caderneta.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caderneta.clients.factory.UserFallbackFactory;
import com.caderneta.model.dto.UserDTO;

@Component
@FeignClient(name = "user", url = "${client.user_url}", path = "/v1/user", fallbackFactory = UserFallbackFactory.class)
public interface UserClient {

	@GetMapping(value = "/by-email")
	UserDTO findByEmail(@RequestParam String email);
}
