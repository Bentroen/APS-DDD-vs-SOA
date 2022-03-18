package br.cefetrj.aps.crypto.main;

import java.time.LocalDateTime;
import java.util.Random;

import br.cefetrj.aps.crypto.domain.Ativo;
import br.cefetrj.aps.crypto.domain.Carteira;
import br.cefetrj.aps.crypto.domain.Transacao;
import br.cefetrj.aps.crypto.repository.Carteiras;
import br.cefetrj.aps.crypto.utils.DateUtils;

public class Main {
    static Random random = new Random();
    static{
        random.setSeed(System.currentTimeMillis());
    }
    private static String ativos[] = { "BTC", "ETH", "LTC", "XRP", "BCH", "EOS",
                                       "XLM", "ADA", "XMR", "TRX", "NEO","DASH",
                                       "XEM","XVG", "LSK", "XRB", "XLM", "XMR",
                                       "TRX", "NEO", "DASH", "XEM", "XVG", "LSK",
                                       "XRB", "DOGE" };

    private static void pupularCarteira(Carteira carteira) {
        for (int i = 0; i < random.nextInt() % 5; i++) {
            Ativo ativo = new Ativo(ativos[random.nextInt(ativos.length)],
                                    random.nextDouble() * 100,
                                    random.nextDouble() * 100,
                                    random.nextDouble() * 100); 
            boolean compra = random.nextBoolean();
            double quantidade = random.nextDouble() * 100;
            double precoPago = random.nextDouble() * 100;
            LocalDateTime data = DateUtils.getRandomDate(random);
            Transacao tx = new Transacao(ativo, compra, quantidade, precoPago, data);
            carteira.processarTransacao(tx);    
        }
    }
    public static void main(String[] args) {
        Carteiras carteiras = Carteiras.getInstance();
        carteiras.salvar(new Carteira("Carteira1", 0d, 0d));
        carteiras.salvar(new Carteira("Carteira2", 0d, 0d));
        carteiras.salvar(new Carteira("Carteira3", 0d, 0d));
        carteiras.salvar(new Carteira("Carteira4", 0d, 0d));
        carteiras.salvar(new Carteira("5", 0d, 0d));

        Carteira carteira = carteiras.buscar("Carteira1");
        pupularCarteira(carteira);
        System.out.println(carteira);

        carteira = carteiras.buscar("Carteira2");
        pupularCarteira(carteira);
        pupularCarteira(carteira);
        System.out.println(carteira);

        carteira = carteiras.buscar("Carteira3");
        pupularCarteira(carteira);
        pupularCarteira(carteira);
        pupularCarteira(carteira);
        System.out.println(carteira);

        carteira = carteiras.buscar("Carteira4");
        pupularCarteira(carteira);
        pupularCarteira(carteira);
        System.out.println(carteira);


    }
}
