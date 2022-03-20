package br.cefetrj.aps.crypto.services;

import java.time.LocalDateTime;
import java.util.List;

import br.cefetrj.aps.crypto.dao.CarteiraDao;
import br.cefetrj.aps.crypto.domain.Ativo;
import br.cefetrj.aps.crypto.domain.Carteira;
import br.cefetrj.aps.crypto.domain.Transferencia;

public class TransferService {
	public Carteira buscarCarteira(String id)
	{
		return CarteiraDao.buscar(id);
	}	
	
	public void processarTransferencia(Carteira carteira, Carteira carteira2, Transferencia entreX) // SUPER HIPER COMPLEXO!!! 
	{
		// 1 - recuperar ou criar o ativo que está sendo transacionando
		Ativo ativo = recuperarAtivo(carteira,entreX);
		Ativo ativo2 = recuperarAtivo(carteira2,entreX);
		// 2 - atualizar o saldo do ativo pela transação
		processarVenda(ativo, ativo2, entreX);
		
		// 3 - atualizar o saldo total da carteira
		consolidarCarteira(carteira);
		consolidarCarteira(carteira2);	
		
		// 4 - salvar a carteira
		CarteiraDao.salvar(carteira);	
		CarteiraDao.salvar(carteira2);	
		
	}
	
	private Ativo recuperarAtivo(Carteira carteira,Transferencia tx)
	{
		List<Ativo> ativos = carteira.getAtivos();
		
		String siglaAtivo = tx.getAtivo().getSigla();
		
		for (Ativo ativo : ativos) 
		{
			if (ativo.getSigla().equals(siglaAtivo))
				return ativo;
		}	
		
		Ativo ativo = new Ativo(siglaAtivo,0d,0d,0d, null);
		ativos.add(ativo);
		return ativo;
	}
	
	public static void processarVenda(Ativo ativo, Ativo ativo2, Transferencia entreX)
	{
		if (entreX.getCompra())
		{
			double qtdVendida = entreX.getQuantidade();
			double precoPago   = entreX.getPrecoPago();
			double valorPago   = qtdVendida*precoPago;			
			
			double qtdPossuida   = ativo.getQuantidade();
			double qtdPossuida2   = ativo2.getQuantidade();
			double precoEntrada  = ativo.getPrecoEntrada();
			double valorPossuido = qtdPossuida*precoEntrada;
			double valorPossuido2 = qtdPossuida2*precoEntrada;
			
			double novoValorTotal   = valorPossuido - valorPago;
			double novaQuantidade   = qtdPossuida - qtdVendida;
			double novoPrecoEntrada = novoValorTotal / novaQuantidade;
			
			double novoRecebido= qtdPossuida2 + qtdVendida;
			double posVenda = valorPossuido2 + valorPago;
			double novoPrecoEntrada2 = posVenda / novoRecebido;
			
			LocalDateTime dataTransfer = entreX.getData(); 
			
			ativo.setQuantidade(novaQuantidade);
			ativo.setPrecoEntrada(novoPrecoEntrada);	
			ativo2.setQuantidade(novoRecebido);
			ativo2.setPrecoEntrada(novoPrecoEntrada2);
			ativo.setData(dataTransfer);
			ativo2.setData(dataTransfer);
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
