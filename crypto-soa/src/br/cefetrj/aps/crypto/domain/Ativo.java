package br.cefetrj.aps.crypto.domain;

import java.time.LocalDateTime;

public class Ativo
{
	private String sigla;
	private double quantidade;
	private double precoEntrada;
	private double precoAtual;
	private LocalDateTime data;
	
	public Ativo(String sigla, double quantidade, double precoEntrada, double precoAtual, LocalDateTime data) 
	{
		this.sigla = sigla;
		this.quantidade = quantidade;
		this.precoEntrada = precoEntrada;
		this.precoAtual = precoAtual;
		this.data = data;
	}
	
	public String toString()
	{
		return String.format("[%s] %s (Entrada - %s) (Atual - %s) (Data ultima movimentação- %s)",
							 sigla,quantidade,precoEntrada,precoAtual,data);
	}
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecoEntrada() {
		return precoEntrada;
	}
	public void setPrecoEntrada(double precoEntrada) {
		this.precoEntrada = precoEntrada;
	}
	public double getPrecoAtual() {
		return precoAtual;
	}
	public void setPrecoAtual(double precoAtual) {
		this.precoAtual = precoAtual;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
}
