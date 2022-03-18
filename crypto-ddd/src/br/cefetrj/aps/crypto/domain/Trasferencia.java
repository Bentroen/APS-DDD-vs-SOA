package br.cefetrj.aps.crypto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.cefetrj.aps.crypto.utils.DateUtils;

public class Trasferencia implements Serializable{
    private Carteira destino;
    private Carteira origem;
    private Ativo ativo;
    private double valor;
    private LocalDateTime data;

    public Trasferencia(Carteira destino, Carteira origem, Ativo ativo, double valor, LocalDateTime data) {
        this.destino = destino;
        this.origem = origem;
        this.ativo = ativo;
        this.valor = valor;
        this.data = data;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Sigla: ");
        sb.append(ativo.getSigla());
        sb.append("Quantidade: ");
        sb.append(valor);
        sb.append("Tipo: ");
        sb.append("Transferencia");
        sb.append("Preço: ");
        sb.append(valor);
        sb.append("Data: ");
        sb.append(DateUtils.format(data));
        return sb.toString();
    }

    public Carteira getDestino() {
        return destino;
    }

    public void setDestino(Carteira destino) {
        this.destino = destino;
    }

    public Carteira getOrigem() {
        return origem;
    }

    public void setOrigem(Carteira origem) {
        this.origem = origem;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

}
