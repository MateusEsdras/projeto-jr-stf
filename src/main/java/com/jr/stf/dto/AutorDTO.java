package com.jr.stf.dto;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jr.stf.domain.Autor;

public class AutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String cpf;
	private String nome;
	private String sexo;
	private String email;
	private String paisOrigem;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date nascimento;
	
	public AutorDTO() {
	}
	public AutorDTO(Autor obj) {
		id = obj.getId();
		cpf = obj.getCpf();
		nome = obj.getNome();
		sexo = obj.getSexo();
		email = obj.getEmail();
		paisOrigem = obj.getPaisOrigem();
		nascimento = obj.getNascimento();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPaisOrigem() {
		return paisOrigem;
	}
	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
}
