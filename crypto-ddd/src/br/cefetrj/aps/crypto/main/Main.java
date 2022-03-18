package br.cefetrj.aps.crypto.main;

import java.util.Random;

import br.cefetrj.aps.crypto.domain.Ativo;
import br.cefetrj.aps.crypto.domain.Carteira;
import br.cefetrj.aps.crypto.domain.Transacao;
import br.cefetrj.aps.crypto.repository.Carteiras;
import br.cefetrj.aps.crypto.utils.DateUtils;

public class Main {
    static Random random = new Random();

    public static void main(String[] args) {
        String ativos[] = { "BTC", "ETH", "LTC", "XRP", "BCH", "EOS", "XLM", "ADA", "XMR", "TRX", "NEO", "DASH", "XEM",
                "XVG", "LSK", "XRB", "XLM", "XMR", "TRX", "NEO", "DASH", "XEM", "XVG", "LSK", "XRB", "DOGE" };

        Carteiras carteiras = Carteiras.getInstance();
        carteiras.salvar(new Carteira("1", 0d, 0d));
        carteiras.salvar(new Carteira("2", 0d, 0d));
        carteiras.salvar(new Carteira("3", 0d, 0d));
        carteiras.salvar(new Carteira("4", 0d, 0d));
        carteiras.salvar(new Carteira("5", 0d, 0d));

        for (int i = 0; i < random.nextInt() % 5; i++) {
            String sigla = ativos[random.nextInt(ativos.length)];
            double precoEntrada = random.nextDouble() * 100;
            double quantidade = random.nextDouble() * 100;
            double precoSaida = random.nextDouble() * 100;
            double saldo = quantidade * precoSaida;
        }

    }
}
