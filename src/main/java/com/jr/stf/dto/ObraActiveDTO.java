package com.jr.stf.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jr.stf.domain.Obra;

public class ObraActiveDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date exposicao;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date publicacao;
	
	private List<AutorDTO> autores = new ArrayList<>();
	
	public ObraActiveDTO() {
	}
	public ObraActiveDTO(Obra obj) {
		id = obj.getId();
		nome = obj.getNome();
		descricao = obj.getDescricao();
		exposicao = obj.getExposicao();
		publicacao = obj.getPublicacao();
		autores = obj.getAutores().stream().map(newObj -> new AutorDTO(newObj)).collect(Collectors.toList());
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
	public Date getExposicao() {
		return exposicao;
	}
	public void setExposicao(Date exposicao) {
		this.exposicao = exposicao;
	}
	public Date getPublicacao() {
		return publicacao;
	}
	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}
	public List<AutorDTO> getAutores() {
		return autores;
	}
	public void setAutores(List<AutorDTO> autores) {
		this.autores = autores;
	}
}
