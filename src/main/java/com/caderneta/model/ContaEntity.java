package com.caderneta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_CONTA")
public class ContaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull
	@Column(name = "VALOR_CONTA")
	private BigDecimal valorConta;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_VENCIMENTO")
	private Date dataVencimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_PAGAMENTO")
	private Date dataPagamento;

	@Column(name = "QTD_PARCELAS")
	private Integer qtdParcelas;

	@Column(name = "COMENTARIO")
	private String comentario;

    @JoinColumn(name = "ID_MES", nullable = false)
    @ManyToOne(targetEntity = MesEntity.class, fetch = FetchType.EAGER)
    private MesEntity mes;

	@JoinColumn(name = "ID_STATUS_CONTA", nullable = false)
	@ManyToOne(targetEntity = StatusContaEntity.class, fetch = FetchType.EAGER)
	private StatusContaEntity status;

	@JoinColumn(name = "ID_TIPO_CONTA", nullable = false)
	@ManyToOne(targetEntity = TipoContaEntity.class, fetch = FetchType.EAGER)
	private TipoContaEntity tipoConta;

	@NotNull
	@Column(name = "ID_USUARIO")
	private Long usuario;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_CADASTRO", updatable = false)
	private Date createdAt;
}
