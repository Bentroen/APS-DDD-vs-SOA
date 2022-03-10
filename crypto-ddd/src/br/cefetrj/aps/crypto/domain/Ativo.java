package br.cefetrj.aps.crypto.domain;

public class Ativo
{
	//
	// Atributos
	//
	private String sigla;
	private double quantidade;
	private double precoEntrada;
	private double precoAtual;
	
	//
	// Construtor
	//  
	public Ativo(String sigla, double quantidade, double precoEntrada, double precoAtual) 
	{
		this.sigla = sigla;
		this.quantidade = quantidade;
		this.precoEntrada = precoEntrada;
		this.precoAtual = precoAtual;
	}
	
	//
	// Operações
	//
	public void processaTx(Transacao tx)
	{
		if (tx.getCompra())
		{
			double qtdComprada = tx.getQuantidade();
			double precoPago   = tx.getPrecoPago();
			double valorPago   = qtdComprada*precoPago;			
			
			double qtdPossuida   = getQuantidade();
			double precoEntrada  = getPrecoEntrada();
			double valorPossuido = qtdPossuida*precoEntrada;
			
			double novoValorTotal   = valorPossuido + valorPago;
			double novaQuantidade   = qtdPossuida + qtdComprada;
			double novoPrecoEntrada = novoValorTotal / novaQuantidade;
			
			setQuantidade(novaQuantidade);
			setPrecoEntrada(novoPrecoEntrada);			
		}
		else
		{
			// TODO ...
		}
	}
	
	//
	// Meta
	//
	public String toString()
	{
		return String.format("[%s] %s (Entrada - %s) (Atual - %s)",
							 sigla,quantidade,precoEntrada,precoAtual);
	}
	
	//
	// Acessors
	//
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
}
