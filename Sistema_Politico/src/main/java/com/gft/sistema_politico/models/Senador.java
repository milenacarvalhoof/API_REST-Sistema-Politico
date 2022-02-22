package com.gft.sistema_politico.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_senador")
public class Senador extends Politico{
		
	public Senador() {}

	public Senador(Long id, String nome, String cpf, Endereco endereco, String telefone, Partido partido,
			String projetosDeLei, Boolean processos, String fotos) {
		this.setId(id);
		this.setNome(nome);
		this.setCpf(cpf);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setPartido(partido);
		this.setProjetosDeLei(projetosDeLei);
		this.setProcessos(processos);
		this.setFotos(fotos);
	}
}
