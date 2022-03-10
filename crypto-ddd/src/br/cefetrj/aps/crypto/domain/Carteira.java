package br.cefetrj.aps.crypto.domain;

import java.util.ArrayList;
import java.util.List;

import br.cefetrj.aps.crypto.utils.NumberUtils;

public class Carteira implements Comparable<Carteira>
{
	//
	// Atributos
	//
	private String id;
	private double entradaTotal;
	private double saldoTotal;
	
	private List<Ativo> ativos = new ArrayList<Ativo>();
	
	//
	// Operações
	//
	public void processarTransacao(Transacao tx)
	{
		// 1 - recuperar ou criar o ativo que está sendo transacionando
		Ativo ativo = recuperarAtivo(tx);
		
		// 2 - atualizar o saldo do ativo pela transação - COMPOSABILIDADE|BOUNDED-CONTEXT
		ativo.processaTx(tx);
		
		// 3 - atualizar o saldo total da carteira
		consolidarCarteira();				
	}
	
	private Ativo recuperarAtivo(Transacao tx)
	{
		Ativo ativo = tx.getAtivo();
		
		if (!ativos.contains(ativo))
		{
			ativos.add(ativo);
		}	
		
		return ativo;
	}
	
	private void consolidarCarteira()
	{
		entradaTotal = 0d;
		
		for (Ativo ativoAtual : ativos) 
		{
			double entradaAtual = ativoAtual.getQuantidade()*ativoAtual.getPrecoEntrada();
			entradaTotal+=entradaAtual;
		}		
	}
	
	//
	// Meta
	//
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("#### CARTEIRA ####\n");
		builder.append("Entrada Total: ");
		builder.append(NumberUtils.formatUsd(entradaTotal)+"\n");
		builder.append("------------------\n");
		for (Ativo ativo : ativos) 
		{
			builder.append(ativo.toString());
			builder.append("\n");
		}		
		
		return builder.toString();
	}	
	
	//
	// Accessors
	//
	public double getEntradaTotal() {
		return entradaTotal;
	}

	public void setEntradaTotal(double entradaTotal) {
		this.entradaTotal = entradaTotal;
	}

	public double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public List<Ativo> getAtivos() {
		return ativos;
	}

	public void setAtivos(List<Ativo> ativos) {
		this.ativos = ativos;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(Carteira other) 
	{
		return id.compareTo(other.id);
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
		Carteira other = (Carteira) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
