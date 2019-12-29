package com.jr.stf.dto;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jr.stf.domain.Obra;

public class ObraDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date publicacao;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date exposicao;
	
	public ObraDTO() {
	}
	public ObraDTO(Obra obj) {
		id = obj.getId();
		nome = obj.getNome();
		descricao = obj.getDescricao();
		publicacao = obj.getPublicacao();
		exposicao = obj.getExposicao();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getPublicacao() {
		return publicacao;
	}
	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}
	public Date getExposicao() {
		return exposicao;
	}
	public void setExposicao(Date exposicao) {
		this.exposicao = exposicao;
	}
}
