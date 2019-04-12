/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @author dodo99
 */
public class Prodotti implements Serializable {
    private int numprod=0;
    private String nomeprod;
    private int giacenza;
    private float prezzo;
    /**
     * 
     * @param numprod parametro che richiede un numero prodotto
     * @param nomeprod parametro che richiede il nome di un prodotto
     * @param giacenza parametro che richiede la sua quantità in magazzino
     */
    public Prodotti(int numprod, String nomeprod, int giacenza, float prezzo) {
        this.numprod = numprod;
        this.nomeprod = nomeprod;
        this.giacenza = giacenza;
        this.prezzo=prezzo;
    }

    /**
     * 
     * @return restituisce un numero prodotto
     */
    public int getNumprod() {
        return numprod;
    }
    
    public float getPrezzo() {
        return prezzo;
    }
    /**
     * 
     * @return restituisce il nome del prodotto
     */
    public String getNomeprod() {
        return nomeprod;
    }
    /**
     * 
     * @return restituisce la giacenza
     */
    public int getGiacenza() {
        return giacenza;
    }

    /**
     * 
     * @return calcola il codice hash e lo restituisce
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.numprod;
        return hash;
    }

    /**
     * 
     * @param obj parametro che richiede un oggetto
     * @return ritorna false se il numero prodotto è diverso da un altro altrimenti true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prodotti other = (Prodotti) obj;
        if (this.numprod != other.numprod) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param giacenza parametro che richiede una giacenza è la setta
     */
    public void setGiacenza(int giacenza) {
        this.giacenza = giacenza;
    }

    /**
     * 
     * @param numprod parametro che richiede un numero prodotto è lo setta
     */
    public void setNumprod(int numprod) {
        this.numprod = numprod;
    }

    /**
     * 
     * @param nomeprod parametro che richiede un nome del prodotto e lo setta
     */
    public void setNomeprod(String nomeprod) {
        this.nomeprod = nomeprod;
    }
    
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    
    /**
     * 
     * @return ritorna la lista degli attributi convertiti in stringhe
     */
    @Override
    public String toString() {
        return "Prodotto " + "numprod=" + numprod + ", nomeprod=" + nomeprod + ", giacenza=" + giacenza + ", prezzo=" + prezzo;
    }
    
}