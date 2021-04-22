package org.corso.magazzino;

import org.corso.magazzino.exceptions.ErroreCapacitaMassimaDepositoSuperata;
import org.corso.magazzino.exceptions.ErroreCaricoException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Deposito {
    private String nome;
    private int capacitaMassima;
    private int esistenzaComplessiva;
    private Map<Prodotto, Integer> prodotti;

    public Deposito(){
        prodotti = new HashMap<>();
    }

    public Deposito(String nome, int capacitaMassima){
        this();
        this.nome = nome;
        this.capacitaMassima = capacitaMassima;
        this.esistenzaComplessiva = 0;
    }

    public void caricoDeposito(Prodotto prodotto, int quantita) throws ErroreCaricoException, ErroreCapacitaMassimaDepositoSuperata {
        if(quantita<0 || prodotto==null)
            throw new ErroreCaricoException();

        int esistenza = 0;
        if(prodotti.get(prodotto) != null){
            esistenza = prodotti.get(prodotto) + quantita;
        }
        if (capacitaMassima < esistenzaComplessiva + quantita)
            throw new ErroreCapacitaMassimaDepositoSuperata();

        prodotti.put(prodotto, esistenza + quantita);
        this.esistenzaComplessiva += quantita;
    }


    public int getCapacitaMassima() {
        return capacitaMassima;
    }

    public void setCapacitaMassima(int capacitaMassima) {
        this.capacitaMassima = capacitaMassima;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposito deposito = (Deposito) o;
        return nome.equals(deposito.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}