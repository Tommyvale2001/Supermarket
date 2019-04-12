/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.EOFException;
import java.lang.String;
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

import javax.swing.JOptionPane;
     
/*
*
* @author dodo99
*/
public class Model {
    

   
   
   public ArrayList<Prodotti> products;
   public ArrayList<Operazioni> operation;
   private static int contaProdotti= 0;
   /**
    * Costruttore Model contenete gli arraylist
    */
    public Model() {
        this.products = new ArrayList<>();
        this.operation = new ArrayList<>();
    }
    /**
     * metodo di apertura file
     */
    void open() {
       try {
           //qui apertura file a caricamento degli studenti nell'arraylist
           FileInputStream f = new FileInputStream("Prodotti.dat");
           ObjectInputStream fi = new ObjectInputStream(f);
           boolean ended=false;
           while(!ended){
               try{
            products.add((Prodotti)fi.readObject());
            contaProdotti=contaProdotti+1;
            //operation.add((Operazioni)fi.readObject());
                  
               
                 }catch(EOFException e){
                   ended=true;
                   fi.close();
               }
              
               }
              
       } catch (FileNotFoundException | ClassNotFoundException ex) {
       } catch (IOException ex) {    
       }
   }
    
    void openOp() {
       try {
           //qui apertura file a caricamento degli studenti nell'arraylist
           FileInputStream f = new FileInputStream("Operazioni.dat");
           ObjectInputStream fi = new ObjectInputStream(f);
           boolean ended=false;
           while(!ended){
               try{
            operation.add((Operazioni)fi.readObject());
            //operation.add((Operazioni)fi.readObject());
                  
               
                 }catch(EOFException e){
                   ended=true;
                   fi.close();
               }
              
               }
              
       } catch (FileNotFoundException | ClassNotFoundException ex) {
       } catch (IOException ex) {
       }
   }

    /**
     * metodo di salvataggio
     */
   void save() {
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
          
       }
      
   }
   /**
    * metodo che salva su file le operazioni
    */
   void saveOp() {
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
          
       }
      
   }
   
   /**
    * 
    * @param prodotto parametro che richiede un oggetto prodotto da aggiungere 
    * 
    */
    public void add(Prodotti prodotto) {
      
      boolean f=false;
      for(int i=0;i<products.size()&&!f;i++){
    	  if(prodotto.getGiacenza()<0) {
    		  JOptionPane.showMessageDialog(null,"Errore nell'inserimento!");
	          f=!f;
    	  }
    	  else if (products.get(i).getNomeprod().equals(prodotto.getNomeprod())) {
	          JOptionPane.showMessageDialog(null,"Prodotto gi� esistente!");
	          f=!f;
	      }
    	  
    	  
      }
      
      if(!f) {
    	  contaProdotti+=1;
    	 products.add(prodotto);
    	 Operazioni op = new Operazioni(prodotto.getNumprod(),"Aggiunta prodotto",prodotto.getGiacenza());
     	 operation.add(op);
      }
    	 
      
    }
    
    /**
     * 
     * @param numprod parametro che richiede l'inserimento un numero prodotto
     */
    public void remove(int numprod) {
    
		for(int i=0; i<products.size(); i++){
	        if(products.get(i).getNumprod() == numprod){
	        	int result = JOptionPane.showConfirmDialog(null,"Sei sicuro di voler eliminare il prodotto: "+ products.get(i).getNomeprod() +" ?" ,null , JOptionPane.YES_NO_OPTION);
	            if (result == JOptionPane.YES_OPTION) {
	               Operazioni op = new Operazioni(products.get(i).getNumprod(),"Rimozione prodotto",products.get(i).getGiacenza());
	           	   operation.add(op);
	           	   products.remove(i);
	            }
	            else {
	            	JOptionPane.showMessageDialog(null,"Operazione annullata!");
	            } 
	        }
        }
	}
    
    /**
     * 
     * @param numprod parametro che richiede l'inserimento un numero prodotto
     * @param qnt parametro che richiede l'inserimento della quantità da prelevare
     */
    public void Prelievo(int numprod, int qnt){
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getNumprod() == numprod){
                
                if (qnt <= products.get(i).getGiacenza()&&qnt>0) {
                	products.get(i).setGiacenza(products.get(i).getGiacenza()-qnt);
                	Operazioni op = new Operazioni(numprod,"Prelievo",qnt);
                	operation.add(op);
                }
                else {
                	JOptionPane.showMessageDialog(null,"Errore durante il prelievo!");
                }
                
            }
            
        }
    }
    /**
     * 
     * @param numprod parametro che richiede l'inserimento un numero prodotto
     * @param qnt parametro che richiede l'inserimento della quantità da depositare
     */
    public void Deposito(int numprod, int qnt){
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getNumprod() == numprod){                
                
                if(qnt>0) {
                	products.get(i).setGiacenza(products.get(i).getGiacenza()+qnt);
                	Operazioni op = new Operazioni(numprod,"Deposito",qnt);
                	operation.add(op);
                }
                else {
                	JOptionPane.showMessageDialog(null,"Errore durante il deposito!");
                }
                
            }
            
        }    
    }
    
    
    public void ModificaPrezzo(int numprod, float newprezzo){
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getNumprod() == numprod){                
                
                if(newprezzo>0) {
                	products.get(i).setPrezzo(newprezzo);
                	Operazioni op = new Operazioni(numprod,"Modifica prezzo",products.get(i).getGiacenza() );
                	operation.add(op);
                }
                else {
                	JOptionPane.showMessageDialog(null,"Errore durante la modifica!");
                }
                
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
