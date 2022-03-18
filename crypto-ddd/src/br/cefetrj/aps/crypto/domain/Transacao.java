package br.cefetrj.aps.crypto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.cefetrj.aps.crypto.utils.DateUtils;

public class Transacao implements Serializable{
    private Ativo ativo;
    private boolean compra;
    private double quantidade;
    private double precoPago;
    private LocalDateTime data;

    public Transacao(Ativo ativo, boolean compra, double quantidade, double precoPago, LocalDateTime data) {
        this.ativo = ativo;
        this.compra = compra;
        this.quantidade = quantidade;
        this.precoPago = precoPago;
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Sigla: ");
        sb.append(ativo.getSigla());
        sb.append("Quantidade: ");
        sb.append(quantidade);
        sb.append("Tipo: ");
        sb.append(compra ? "Compra" : "Venda");
        sb.append("Preço: ");
        sb.append(precoPago);
        sb.append("Data: ");
        sb.append(DateUtils.format(data));
        return sb.toString();
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public boolean getCompra() {
        return compra;
    }

    public void setCompra(boolean compra) {
        this.compra = compra;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoPago() {
        return precoPago;
    }

    public void setPrecoPago(double precoPago) {
        this.precoPago = precoPago;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
