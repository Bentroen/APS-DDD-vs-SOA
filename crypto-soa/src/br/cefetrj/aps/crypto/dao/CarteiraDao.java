package br.cefetrj.aps.crypto.dao;

import java.util.Set;
import java.util.TreeSet;

import br.cefetrj.aps.crypto.domain.Ativo;
import br.cefetrj.aps.crypto.domain.Carteira;
import br.cefetrj.aps.crypto.domain.Transacao;
import br.cefetrj.aps.crypto.services.CarteiraService;
import br.cefetrj.aps.crypto.utils.DateUtils;

public class CarteiraDao // D.A.O = Data Access Object
{
	private static Set<Carteira> carteiras = new TreeSet<Carteira>();
	
	// Preenche um bando de dados de "mentirinha"
	static
	{
		// Cria um ativo
		Ativo     ltc = new Ativo("LTC",0,0,0,DateUtils.parse("2089-02-04 11:00:00"));
		// Cria uma transação
		Transacao tx1 = new Transacao(ltc, true, 0.1, 230, DateUtils.parse("2022-02-04 11:00:00"));
		// Cria uma carteira
		Carteira carteira = new Carteira();
		carteira.setId("WALLET");
		
		// Processa a transação na carteira
		CarteiraService carteiraService = new CarteiraService();
		carteiraService.processarTransacao(carteira,tx1);
		
		// Salva a carteira no banco de dados
		salvar(carteira);
		
		
	}
	static
	{
		// Cria um ativo
		Ativo     rtc = new Ativo("RTC",0,0,0,DateUtils.parse("2022-02-04 11:00:00"));
		// Cria uma transação
		Transacao tx2 = new Transacao(rtc, true, 0.1, 230, DateUtils.parse("2078-02-04 11:00:00"));
		// Cria uma carteira
		Carteira carteira2 = new Carteira();
		carteira2.setId("WALLET2");
		
		// Processa a transação na carteira
		CarteiraService carteiraService2 = new CarteiraService();
		carteiraService2.processarTransacao(carteira2,tx2);
		
		// Salva a carteira no banco de dados
		salvar2(carteira2);
		
		
	}
	
	public static void salvar(Carteira carteira) 
	{
		carteiras.add(carteira);
	}
	
	public static void remover(Carteira carteira)
	{
		carteiras.remove(carteira);
	}
	
	public static Carteira buscar(String id)
	{
		for (Carteira carteira : carteiras) 
		{
			if (carteira.getId().equals(id))
				return carteira;
		}
		
		return null;
	}
	public static void salvar2(Carteira carteira2) 
	{
		carteiras.add(carteira2);
	}
	
	public static void remover2(Carteira carteira2)
	{
		carteiras.remove(carteira2);
	}
	
	
}
