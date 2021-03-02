package com.caderneta.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_TIPO_CONTA")
public class TipoContaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    @Getter
    @Setter
    @Column(name = "NOME_TIPO_CONTA")
    private String tipo;

    @Getter
    @Setter
    @Column(name = "DESC_TIPO_CONTA")
    private String descricao;
}
