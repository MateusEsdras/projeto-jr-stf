package com.jr.stf.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Obra implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date publicacao;
	
	@Column
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date exposicao;
	
	@ManyToMany
	@JoinTable(name="OBRA_AUTOR",
	   joinColumns=@JoinColumn(name="obra_id"),
	   inverseJoinColumns = @JoinColumn(name="autor_id"))
	private List<Autor> autores = new ArrayList<>();
	
	public Obra() {
	}
	public Obra(Integer id, String nome, String descricao, Date publicacao, Date exposicao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.publicacao = publicacao;
		this.exposicao = exposicao;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obra other = (Obra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
