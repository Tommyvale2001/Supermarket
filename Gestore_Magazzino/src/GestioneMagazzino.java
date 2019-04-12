import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dodo99
 */
public class GestioneMagazzino {

    /**
     * @param args the command line arguments
     * funzione mai in cui richiama i vari oggetti per la visualizzazzione del programma
     */
    public static void main(String[] args) {
        Model model=new Model();
      //apertura file
   	 	model.open();
        model.openOp();
        JOptionPane.showMessageDialog(null,"File aperti!");
        View view=new View(model);

    	
    }
    
}
