package br.cefetrj.aps.crypto.domain;

import java.io.Serializable;
import java.text.NumberFormat;

import br.cefetrj.aps.crypto.utils.NumberUtils;

public class Ativo implements Serializable {
    private String sigla;
    private double quantidade;
    private double precoEntrada;
    private double precoAtual;

    public Ativo(String sigla, double quantidade, double precoEntrada, double precoAtual) {
        this.sigla = sigla;
        this.quantidade = quantidade;
        this.precoEntrada = precoEntrada;
        this.precoAtual = precoAtual;
    }

    public void processaTf(Trasferencia tf) {
        Ativo destino = tf.getDestino().getAtivo(tf.getAtivo().getSigla());
        Ativo origem = tf.getOrigem().getAtivo(tf.getAtivo().getSigla());

        if (destino == null) {
            destino = new Ativo(tf.getAtivo().getSigla(), 0, 0, 0);
            tf.getDestino().getAtivos().add(destino);
        }

        if (origem == null) {
            origem = new Ativo(tf.getAtivo().getSigla(), 0, 0, 0);
            tf.getOrigem().getAtivos().add(origem);
        }

        if (origem.quantidade >= tf.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        destino.quantidade += tf.getValor();
        origem.quantidade -= tf.getValor();
    }

    public void processaTx(Transacao tx) {
        if (tx.getCompra()) {
            double qtdComprada = tx.getQuantidade();
            double precoPago = tx.getPrecoPago();
            double valorPago = qtdComprada * precoPago;

            double qtdPossuida = getQuantidade();
            double precoEntrada = getPrecoEntrada();
            double valorPossuido = qtdPossuida * precoEntrada;

            double novoValorTotal = valorPossuido + valorPago;
            double novaQuantidade = qtdPossuida + qtdComprada;
            double novoPrecoEntrada = novoValorTotal / novaQuantidade;

            setQuantidade(novaQuantidade);
            setPrecoEntrada(novoPrecoEntrada);
        } else {
            double qtdVendida = tx.getQuantidade();
            double precoVendido = tx.getPrecoPago();
            double valorVendido = qtdVendida * precoVendido;

            double qtdPossuida = getQuantidade();
            double precoEntrada = getPrecoEntrada();
            double valorPossuido = qtdPossuida * precoEntrada;

            double novoValorTotal = valorPossuido - valorVendido;
            double novaQuantidade = qtdPossuida - qtdVendida;
            double novoPrecoEntrada = novoValorTotal / novaQuantidade;

            setQuantidade(novaQuantidade);
            setPrecoEntrada(novoPrecoEntrada);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ativo: ");
        sb.append(sigla);
        sb.append(" Quantidade: ");
        sb.append(NumberFormat.getInstance().format(quantidade));
        sb.append(" Preço entrada: ");
        sb.append(NumberUtils.formatUsd(precoEntrada));
        sb.append(" Preço atual: ");
        sb.append(NumberUtils.formatUsd(precoAtual));
        return sb.toString();
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoEntrada() {
        return precoEntrada;
    }

    public void setPrecoEntrada(double precoEntrada) {
        this.precoEntrada = precoEntrada;
    }

    public double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
    }
}
