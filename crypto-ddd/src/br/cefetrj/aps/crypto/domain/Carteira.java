package br.cefetrj.aps.crypto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.cefetrj.aps.crypto.utils.NumberUtils;

public class Carteira implements Comparable<Carteira>, Serializable {
    private String id;
    private double entradaTotal;
    private double saldoTotal;

    private List<Ativo> ativos = new ArrayList<Ativo>();

    public Carteira(String id, double entradaTotal, double saldoTotal) {
        this.id = id;
        this.entradaTotal = entradaTotal;
        this.saldoTotal = saldoTotal;
    }

    public void processarTransacao(Transacao tx) {
        Ativo ativo = recuperarAtivo(tx);
        ativo.processaTx(tx);
        consolidarCarteira();
    }

    public void processarTransferencia(Trasferencia tf) {
        Ativo ativo = recuperarAtivo(tf);
        ativo.processaTf(tf);
        consolidarCarteira();
        tf.getDestino().consolidarCarteira();
    }

    private Ativo recuperarAtivo(Trasferencia tx) {
        Ativo ativo = tx.getAtivo();
        if (!ativos.contains(ativo)) {
            ativos.add(ativo);
        }
        return ativo;
    }

    private Ativo recuperarAtivo(Transacao tx) {
        String siglaAtivo = tx.getAtivo().getSigla();
        for (Ativo ativo : ativos) {
            if (ativo.getSigla().equals(siglaAtivo))
                return ativo;
        }

        Ativo ativo = new Ativo(siglaAtivo, 0d, 0d, 0d);
        ativos.add(ativo);
        return ativo;
    }

    protected void consolidarCarteira() {
        entradaTotal = 0d;
        for (Ativo ativoAtual : ativos) {
            double entradaAtual = ativoAtual.getQuantidade() * ativoAtual.getPrecoEntrada();
            entradaTotal += entradaAtual;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("#### CARTEIRA ####\n");
        builder.append("ID: ").append(id).append("\n");
        builder.append("ENTRADA TOTAL: ").append(NumberUtils.format(entradaTotal)).append("\n");
        builder.append("SALDO TOTAL: ").append(NumberUtils.format(saldoTotal)).append("\n");
        builder.append("ATIVOS:\n");
        builder.append("------------------\n");
        for (Ativo ativo : ativos) {
            builder.append(ativo);
            builder.append("\n");
        }
        return builder.toString();
    }

    public double getEntradaTotal() {
        return entradaTotal;
    }

    public void setEntradaTotal(double entradaTotal) {
        this.entradaTotal = entradaTotal;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public List<Ativo> getAtivos() {
        return ativos;
    }

    public void setAtivos(List<Ativo> ativos) {
        this.ativos = ativos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Carteira other) {
        return id.compareTo(other.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carteira other = (Carteira) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Ativo getAtivo(String sigla) {
        for (Ativo ativo : ativos) {
            if (ativo.getSigla().equals(sigla))
                return ativo;
        }
        return null;
    }
}
