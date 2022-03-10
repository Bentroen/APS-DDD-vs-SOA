package br.cefetrj.aps.crypto.repository;

import java.util.Set;
import java.util.TreeSet;

import br.cefetrj.aps.crypto.domain.Ativo;
import br.cefetrj.aps.crypto.domain.Carteira;
import br.cefetrj.aps.crypto.domain.Transacao;
import br.cefetrj.aps.crypto.utils.DateUtils;

public class Carteiras 
{
	private static Set<Carteira> carteiras = new TreeSet<Carteira>();
	
	static
	{
		Ativo     ltc = new Ativo("LTC",0,0,0);
		Transacao tx1 = new Transacao(ltc, true, 0.1, 230, DateUtils.parse("2022-02-04 11:00:00"));
		
		Carteira carteira = new Carteira();
		carteira.setId("WALLET");
		carteira.processarTransacao(tx1);
		
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
			if (carteira.getId()==id)
				return carteira;
		}
		
		return null;
	}
}
