package br.cefetrj.aps.crypto.main;

import br.cefetrj.aps.crypto.domain.Ativo;
import br.cefetrj.aps.crypto.domain.Carteira;
import br.cefetrj.aps.crypto.domain.Transacao;
import br.cefetrj.aps.crypto.domain.Transferencia;
import br.cefetrj.aps.crypto.services.CarteiraService;
import br.cefetrj.aps.crypto.services.TransferService;
import br.cefetrj.aps.crypto.utils.DateUtils;

public class Main 
{
	
	// Programação em Camadas - APRESENTAÇÃO -> SERVIÇO -> DADOS (3-camadas)
	public static void main(String[] args) 
	{
		CarteiraService carteiraService = new CarteiraService();
		TransferService transferService = new TransferService();
		
		// 1 - Recuperar a carteira do "Banco de Dados"
		Carteira carteira = carteiraService.buscarCarteira("WALLET");
		Carteira carteira2 = carteiraService.buscarCarteira("WALLET2");
		
	
		
		// 2 - Criar novos ativos e novas transações
		Ativo     btc = new Ativo("BTC",0,0,0, DateUtils.parse("2022-02-04 11:00:00"));
		Transacao tx1 = new Transacao(btc, true, 2.000, 37500,  DateUtils.parse("2022-02-04 11:00:00"));
		Transacao tx2 = new Transacao(btc, true, 2.000, 37500, DateUtils.parse("2022-02-04 11:00:00"));
		

		
		Transacao tx3 = new Transacao(btc, true, 3.000, 37500, DateUtils.parse("2022-05-04 11:00:00"));
		Transacao tx4 = new Transacao(btc, true, 1.000, 37500, DateUtils.parse("2022-03-04 11:00:00"));
		
		Ativo     eth = new Ativo("ETH",0,0,0, DateUtils.parse("2022-02-04 11:00:00"));
		Transacao tx5 = new Transacao(eth, true, 0.1, 3900, DateUtils.parse("2022-02-04 11:00:00"));
		Transacao tx6 = new Transacao(eth, true, 0.2, 4000, DateUtils.parse("2022-02-04 11:00:00"));
		
		
		Transferencia tx7 = new Transferencia(btc, true, 0.1, 37500, DateUtils.parse("2022-02-04 11:00:00"));

		// 3 - Processar os novos ativos e novas transações na carteira
		carteiraService.processarTransacao(carteira,tx1);
		carteiraService.processarTransacao(carteira,tx2);
		carteiraService.processarTransacao(carteira2,tx3);
		carteiraService.processarTransacao(carteira2,tx4);
		carteiraService.processarTransacao(carteira,tx5);
		carteiraService.processarTransacao(carteira,tx6);
		
		
		// 4 - Imprimir a carteira para teste
		System.out.println(carteira);
		System.out.println(carteira2);
		System.out.println("------Realizar Transferencia------\n");
		transferService.processarTransferencia(carteira, carteira2, tx7);
		System.out.println(carteira);
		System.out.println(carteira2);
		
	}
}
