/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
     
/*
*
* @author dodo99
*/
public class Model {
    public interface ModelObserver {

        /**
         * 
         * @param prodotti parametro che richiede un elemento dell' array list Prodotti
         * @param operazioni parametro che richiede un elemento dell' array list Operazioni
         * @param status parametro che richiede un oggetto Status
         * osservatori
         */
      public void modelChanged(Prodotti[] prodotti, Operazioni[] operazioni,Status status);
      //public void modelChanged(Operazioni[] operation, Status status);
   }

   public enum Status {
      openedError,savedError,addError,deleteError,normalExecution,qntError;
   }
   
   private List<Prodotti> products;
   public List<Operazioni> operation;
   private List<ModelObserver> observers;
   private static int contaProdotti= 0;
   /**
    * Costruttore Model contenete gli arraylist
    */
    public Model() {
        this.products = new ArrayList<>();
        this.operation = new ArrayList<>();
        this.observers = new ArrayList<>();
    }
    /**
     * metodo di apertura file
     */
    void open() {
      Status status= Status.normalExecution;
       try {
           //qui apertura file a caricamento degli studenti nell'arraylist
           FileInputStream f = new FileInputStream("Prodotti.dat");
           ObjectInputStream fi = new ObjectInputStream(f);
           boolean ended=false;
           while(!ended){
               try{
            products.add((Prodotti)fi.readObject());
            contaProdotti=contaProdotti+1;
            status=Status.normalExecution;
            //operation.add((Operazioni)fi.readObject());
                  
               
                 }catch(EOFException e){
                   ended=true;
                   fi.close();
               }
              
               }
              
       } catch (FileNotFoundException | ClassNotFoundException ex) {
          status=Status.openedError;
       } catch (IOException ex) {
           status=Status.addError;    
       }
      updateObservers(status);
   }
    
    void openOp() {
      Status status= Status.normalExecution;
       try {
           //qui apertura file a caricamento degli studenti nell'arraylist
           FileInputStream f = new FileInputStream("Operazioni.dat");
           ObjectInputStream fi = new ObjectInputStream(f);
           boolean ended=false;
           while(!ended){
               try{
            operation.add((Operazioni)fi.readObject());
            status=Status.normalExecution;
            //operation.add((Operazioni)fi.readObject());
                  
               
                 }catch(EOFException e){
                   ended=true;
                   fi.close();
               }
              
               }
              
       } catch (FileNotFoundException | ClassNotFoundException ex) {
          status=Status.openedError;
       } catch (IOException ex) {
           status=Status.addError;    
       }
      updateObservers(status);
   }

    /**
     * metodo di salvataggio
     */
   void save() {
      Status status= Status.normalExecution;
       try {
           //qui salvataggio degli studenti
           FileOutputStream prod = new FileOutputStream("Prodotti.dat");
           
          try (ObjectOutputStream fout = new ObjectOutputStream(prod)) {
              for (Prodotti products : products) {
                  fout.writeObject(products);
                  fout.flush();

              }
              fout.close();
          }
          
       } catch (Exception ex) {
          
           status=Status.savedError;
       }
      
      updateObservers(status);
   }
   /**
    * metodo che salva su file le operazioni
    */
   void saveOp() {
      Status status= Status.normalExecution;
       try {
           //qui salvataggio degli studenti
           FileOutputStream oper = new FileOutputStream("Operazioni.dat");
           
          try (ObjectOutputStream fout = new ObjectOutputStream(oper)) {
              for (Operazioni operation : operation) {
                  fout.writeObject(operation);
                  fout.flush();

              }
              fout.close();
          }
          
       } catch (Exception ex) {
          
           status=Status.savedError;
       }
      
      updateObservers(status);
   }
   
   /**
    * 
    * @param prodotto parametro che richiede un oggetto prodotto da aggiungere 
    * 
    */
    public void add(Prodotti prodotto) {
      Status status= Status.addError;
      contaProdotti+=1;
      if (!products.contains(prodotto)) {
         products.add(prodotto);     
         status=Status.normalExecution;
      }
      updateObservers(status);
    }
    
    /**
     * 
     * @param numprod parametro che richiede l'inserimento un numero prodotto
     * @param qnt parametro che richiede l'inserimento della quantità da prelevare
     */
    public void Prelievo(int numprod, int qnt){
        Status status= Status.normalExecution;
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getNumprod() == numprod && qnt <= products.get(i).getGiacenza()){
                products.get(i).setGiacenza(products.get(i).getGiacenza()-qnt);
                Operazioni op = new Operazioni(numprod,"Prelievo",qnt);
                operation.add(op);
                if(products.get(i).getGiacenza()==0){
                    status=Status.qntError;
                }
            }
        updateObservers(status);
    }
    }
    /**
     * 
     * @param numprod parametro che richiede l'inserimento un numero prodotto
     * @param qnt parametro che richiede l'inserimento della quantità da depositare
     */
    public void Deposito(int numprod, int qnt){
        Status status= Status.normalExecution;
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getNumprod() == numprod){                
                products.get(i).setGiacenza(products.get(i).getGiacenza()+qnt);
                Operazioni op = new Operazioni(numprod,"Deposito",qnt);
                operation.add(op);
            }
        }    
    }
    
    /**
     * 
     * @return ritorna contaProdotti per il conteggio dei prodotti 
     */
    public int getContaProdotti(){
       return contaProdotti+1;
  
    }
    
    /**
     * 
     * @param observer parametro che permette l'aggiunta di un oggetto ModelObServer
     */
    public void addObserver(ModelObserver observer){
      observers.add(observer);
    }
    /**
     * 
     * @param observer parametro che permette la rimozione di un oggetto ModelObServer
     * @return restituisce la lista degli osservatori rimossi
     */
    public boolean removeObserver(ModelObserver observer){
      return observers.remove(observer);
    }
    /**
     * 
     * @param status parametro che richiede una status è lo aggiorna
     */
   private void updateObservers(Status status) {
      Prodotti []v=new Prodotti[products.size()];
      Operazioni []v1=new Operazioni[operation.size()];
      products.toArray(v);
      operation.toArray(v1);
      for(ModelObserver observer:observers){
         observer.modelChanged(v,v1,status);
   
        }
    }

   /**
    * 
    * @return ritorna l'intera lista dei prodotti
    */
    public List<Prodotti> getProducts() {
        return products;
    }
    /**
     * 
     * @return l'intera lista delle operazioni
     */
    public List<Operazioni> getOperation() {
        return operation;
    }
   
}
