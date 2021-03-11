package com.caderneta.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String mes;
	private Integer ano;
	private Integer qtdConta;
	private String totalGastos;
}
