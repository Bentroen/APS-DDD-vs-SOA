package br.cefetrj.aps.crypto.services;

import java.time.LocalDateTime;
import java.util.List;

import br.cefetrj.aps.crypto.dao.CarteiraDao;
import br.cefetrj.aps.crypto.domain.Ativo;
import br.cefetrj.aps.crypto.domain.Carteira;
import br.cefetrj.aps.crypto.domain.Transacao;
import br.cefetrj.aps.crypto.utils.DateUtils;

public class CarteiraService 
{
	public Carteira buscarCarteira(String id)
	{
		return CarteiraDao.buscar(id);
	}	
	
	public void processarTransacao(Carteira carteira,Transacao tx) // SUPER HIPER COMPLEXO!!! 
	{
		// 1 - recuperar ou criar o ativo que está sendo transacionando
		Ativo ativo = recuperarAtivo(carteira,tx);
		
		// 2 - atualizar o saldo do ativo pela transação
		processarAtivo(ativo,tx);
		
		// 3 - atualizar o saldo total da carteira
		consolidarCarteira(carteira);	
		
		// 4 - salvar a carteira
		CarteiraDao.salvar(carteira);		
	}
	
	private Ativo recuperarAtivo(Carteira carteira,Transacao tx)
	{
		List<Ativo> ativos = carteira.getAtivos();
		
		String siglaAtivo = tx.getAtivo().getSigla();
		
		for (Ativo ativo : ativos) 
		{
			if (ativo.getSigla().equals(siglaAtivo))
				return ativo;
		}	
		
		Ativo ativo = new Ativo(siglaAtivo,0d,0d,0d, DateUtils.parse("2022-02-04 11:00:00"));
		ativos.add(ativo);
		return ativo;
	}
	
	public static void processarAtivo(Ativo ativo, Transacao tx)
	{
		if (tx.getCompra())
		{
			double qtdComprada = tx.getQuantidade();
			double precoPago   = tx.getPrecoPago();
			double valorPago   = qtdComprada*precoPago;			
			LocalDateTime dataTrans = tx.getData();
			
			
			double qtdPossuida   = ativo.getQuantidade();
			double precoEntrada  = ativo.getPrecoEntrada();
			double valorPossuido = qtdPossuida*precoEntrada;
			
			double novoValorTotal   = valorPossuido + valorPago;
			double novaQuantidade   = qtdPossuida + qtdComprada;
			double novoPrecoEntrada = novoValorTotal / novaQuantidade;
			
			ativo.setQuantidade(novaQuantidade);
			ativo.setPrecoEntrada(novoPrecoEntrada);	
			ativo.setData(dataTrans);
		}
		else
		{
			// TODO ...
		}
	}
	
	private void consolidarCarteira(Carteira carteira)
	{
		List<Ativo> ativos = carteira.getAtivos();
		
		double entradaTotal = 0d;
		
		for (Ativo ativoAtual : ativos) 
		{
			double entradaAtual = ativoAtual.getQuantidade()*ativoAtual.getPrecoEntrada();
			entradaTotal+=entradaAtual;
		}
		
		carteira.setEntradaTotal(entradaTotal);
	}		
}
