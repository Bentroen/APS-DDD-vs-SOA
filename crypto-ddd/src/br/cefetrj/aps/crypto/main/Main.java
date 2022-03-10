package br.cefetrj.aps.crypto.main;

import br.cefetrj.aps.crypto.domain.Ativo;
import br.cefetrj.aps.crypto.domain.Carteira;
import br.cefetrj.aps.crypto.domain.Transacao;
import br.cefetrj.aps.crypto.repository.Carteiras;
import br.cefetrj.aps.crypto.utils.DateUtils;

public class Main 
{
	public static void main(String[] args) 
	{
		// 1 - Recuperar a carteira
		Carteira carteira = Carteiras.buscar("WALLET");
		
		// 2 - Criar novos ativos e novas transações
		Ativo     btc = new Ativo("BTC",0,0,0);
		Transacao tx1 = new Transacao(btc, true, 0.001, 37500, DateUtils.parse("2022-02-04 11:00:00"));
		Transacao tx2 = new Transacao(btc, true, 0.001, 37500, DateUtils.parse("2022-02-04 11:05:00"));
		
		Ativo     eth = new Ativo("ETH",0,0,0);
		Transacao tx3 = new Transacao(eth, true, 0.1, 3900, DateUtils.parse("2022-02-04 11:00:00"));
		Transacao tx4 = new Transacao(eth, true, 0.2, 4000, DateUtils.parse("2022-02-04 11:05:00"));

		// 3 - Processar os novos ativos e novas transações na carteira
		carteira.processarTransacao(tx1);
		carteira.processarTransacao(tx2);
		carteira.processarTransacao(tx3);
		carteira.processarTransacao(tx4);
		
		// 4 - Imprimir a carteira
		System.out.println(carteira);		
		
		// 5 - Salvar a carteira
		Carteiras.salvar(carteira);
	}		
}
