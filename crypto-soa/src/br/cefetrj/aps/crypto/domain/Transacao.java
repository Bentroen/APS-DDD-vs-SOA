package br.cefetrj.aps.crypto.domain;

import java.time.LocalDateTime;

import br.cefetrj.aps.crypto.utils.DateUtils;

public class Transacao 
{
	private Ativo ativo;
	private boolean compra;
	private double quantidade;
	private double precoPago;
	private LocalDateTime data;
	
	public Transacao(Ativo ativo, boolean compra, double quantidade, double precoPago, LocalDateTime data) 
	{
		this.ativo = ativo;
		this.compra = compra;
		this.quantidade = quantidade;
		this.precoPago = precoPago;
		this.data = data;
	}
	
	public String toString()
	{
		return String.format("%s - [%s] %s (Quantidade - %s) (Preco - %s) (Data - %s)",
			
							 compra?"COMPRA":"VENDA",
							 ativo.getSigla(),
							 quantidade,
							 precoPago,
							 data);
	}
	
	public Ativo getAtivo() {
		return ativo;
	}
	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	public boolean getCompra() {
		return compra;
	}
	public void setCompra(boolean compra) {
		this.compra = compra;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecoPago() {
		return precoPago;
	}
	public void setPrecoPago(double precoPago) {
		this.precoPago = precoPago;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
}
