package br.cefetrj.aps.crypto.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import br.cefetrj.aps.crypto.domain.Carteira;

public class Carteiras implements Serializable {
    private static Carteiras singleton = null;
    private Set<Carteira> carteiras = new TreeSet<Carteira>();

    public Carteiras() {
        if (singleton == null) {
            // load data from file
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("carteiras.db");
                ObjectInputStream ois = new ObjectInputStream(fis);
                singleton = (Carteiras) ois.readObject();
                ois.close();
            } catch (Exception e) {
                singleton = this;
            }
        }
    }

    private void escreverDados() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("carteiras.db");
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fos);
            oos.writeObject(singleton);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar(Carteira carteira) {
        this.carteiras.add(carteira);
        escreverDados();
    }

    public void remover(Carteira carteira) {
        this.carteiras.remove(carteira);
        escreverDados();
    }

    public Carteira buscar(String id) {
        for (Carteira carteira : this.carteiras) {
            if (carteira.getId() == id)
                return carteira;
        }
        return null;
    }

    public static Carteiras getInstance() {
        return new Carteiras();
    }
}
