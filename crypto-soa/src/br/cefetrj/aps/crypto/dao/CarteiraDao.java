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
		Ativo     ltc = new Ativo("LTC",0,0,0);
		// Cria uma transa��o
		Transacao tx1 = new Transacao(ltc, true, 0.1, 230, DateUtils.parse("2022-02-04 11:00:00"));
		// Cria uma carteira
		Carteira carteira = new Carteira();
		carteira.setId("WALLET");
		
		// Processa a transa��o na carteira
		CarteiraService carteiraService = new CarteiraService();
		carteiraService.processarTransacao(carteira,tx1);
		
		// Salva a carteira no banco de dados
		salvar(carteira);
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
}
