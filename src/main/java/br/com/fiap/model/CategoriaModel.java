package br.com.fiap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TB_CATEGORIA")
public class CategoriaModel {

	@ApiModelProperty(value = "ID da categoria", position = 1)
	private long idCategoria;
	
	//@JsonProperty()
	@ApiModelProperty(value = "Nome da categoria")
	private String nomeCategoria;
	
	@JsonIgnore
	private List<ProdutoModel> produtos;

	public CategoriaModel() {
	}

	public CategoriaModel(long idCategoria, String nomeCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}

	@Id
	@Column(name = "ID_CATEGORIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_SEQ")
	@SequenceGenerator(name = "CATEGORIA_SEQ", sequenceName = "CATEGORIA_SEQ", allocationSize = 1)
	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Column(name = "NOME_CATEGORIA")
	@NotNull(message = "Nome obrigatório")
	@Size(min = 2, max = 50, message = "Nome deve ser entre 2 e 50 caracteres")
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	@OneToMany(mappedBy = "categoria")
	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}

}
