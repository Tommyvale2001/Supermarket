/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author dodo99
 */
public class Operazioni implements Serializable {
    private Object data;
    private int numProd;
    private String tipoOp;
    private int quantita;
    Calendar calendario = new GregorianCalendar();
    /**
     * 
     * @param numProd parametro che richiede un numero prodotto
     * @param tipoOp parametro che richiede il tipo di operazione
     * @param quantita parametro che richiede la quantita
     */
    public Operazioni(int numProd, String tipoOp, int quantita) {
        this.data = calendario.get(Calendar.DAY_OF_MONTH)+"/"+
                    calendario.get(Calendar.MONTH)+"/"+
                    calendario.get(Calendar.YEAR);
        this.numProd = numProd;
        this.tipoOp = tipoOp;
        this.quantita = quantita;
    }

    /**
     * 
     * @return  restituisce la data
     */
    public String getData() {
        return (String) data;
    }

    /**
     * 
     * @return restituisce il numero prodotto 
     */
    public int getNumProd() {
        return numProd;
    }
    /**
     * 
     * @return restituisce il tipo di operazione
     */
    public String getTipoOp() {
        return tipoOp;
    }
    /**
     * 
     * @return restituisce la quantirà
     */
    public int getquantita() {
        return quantita;
    }
    /**
     * 
     * @param data parametro che richiede una data
     */
    public void setData(String data) {
        this.data = data;
    }
    /**
     * 
     * @param numProd parametro che richiede un numero prodotto
     */
    public void setNumProd(int numProd) {
        this.numProd = numProd;
    }
    /**
     * 
     * @param tipoOp parametro che richiede il tipo di operazione
     */
    public void setTipoOp(String tipoOp) {
        this.tipoOp = tipoOp;
    }
    /**
     * 
     * @param quantita parametro che richiede la quantita
     */
    public void setquantita(int quantita) {
        this.quantita = quantita;
    }
    /**
     * 
     * @return parametro che calcola il codice hash e lo restituisce
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.numProd;
        return hash;
    }

    /**
     * 
     * @param obj parametro che richiede un oggetto obj
     * @return ritorna falso se il numero prodotto è diverso da un altro altrimenti true
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
        final Operazioni other = (Operazioni) obj;
        if (this.numProd != other.numProd) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return ritorna la lista degli attributi convertiti in stringhe
     */
    @Override
    public String toString() {
        return "Operazioni " + "data=" + data + ", numProd=" + numProd + ", tipoOp=" + tipoOp + ", quantit\u00e0=" + quantita;
    }
    
    
}
