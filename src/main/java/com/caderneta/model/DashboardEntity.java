package com.caderneta.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "DASHBOARD_ACCOUNT")
public class DashboardEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_VIEW")
    private Long codigo;

    @Column(name = "MES")
    private String mes;

    @Column(name = "ANO")
    private Integer ano;

    @Column(name = "QTD_CONTAS")
    private Integer qtdConta;

    @Column(name = "TOTAL_GASTOS")
    private BigDecimal totalGastos;

    @Column(name = "USUARIO")
    private Long idUsuario;
}
