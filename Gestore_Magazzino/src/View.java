/*
 * To change this  s license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author INFO17
 */
public class View extends JFrame implements ActionListener, Model.ModelObserver{
    JButton aggiunta;
    JButton salvataggio;
    JButton visualizzaprod;
    JButton operazione;
    JButton visualizzaopsaldo;
    JButton apri;
    JButton salva;
    JList<Prodotti> list;
    JList<Operazioni> list2;
    Model model;
    
    /**
     * 
     * @param model parametro che richiede un oggetto Model per poter utilizzare i metodi di quella classe
     */
    public View(Model model) {
      this.model = model;
      apri = new JButton("Apertura");
      salva = new JButton("Salvataggio");
      aggiunta = new JButton("Aggiungi");
      operazione = new JButton("Operazioni");
      visualizzaopsaldo = new JButton("Visualizza op/saldo");
      visualizzaprod = new JButton("Visualizza prodotti");
      list = new JList<>();
      list2 = new JList<>();
      
      JPanel east = new JPanel();
      JPanel south = new JPanel();
      JPanel ovest = new JPanel();
      
      ovest.add(new JScrollPane(list2));
      east.add(new JScrollPane(list));
      
      //ButtonGroup group = new ButtonGroup();
      south.add(apri);
      south.add(salva);
      south.add(aggiunta);
      south.add(operazione);
      south.add(visualizzaopsaldo);
      south.add(visualizzaprod);
      
      add(south, BorderLayout.SOUTH);
      add(east, BorderLayout.EAST);
      add(ovest, BorderLayout.WEST);
      
      apri.addActionListener(this);
      salva.addActionListener(this);
      aggiunta.addActionListener(this);
      operazione.addActionListener(this);
      visualizzaopsaldo.addActionListener(this);
      visualizzaprod.addActionListener(this);
      setSize(100, 250);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Dimension dim = toolkit.getScreenSize();
      setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Dimension minimumSize = new Dimension(100, 250);      
      setMinimumSize(minimumSize);
      setVisible(true);
      pack();
    }   
    
    /**
     * 
     * @param actionPerformed gestione degli eventi è richiamo dei metodi necessari
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
         case "Apertura":
            model.open();
            model.openOp();
            JOptionPane.showMessageDialog(null,"File aperti!");
            break;
         case "Salvataggio":
            model.save();
            model.saveOp();
            JOptionPane.showMessageDialog(null,"File salvato!");
            break;
         case "Aggiungi":
            AddPanel add = new AddPanel();
            int result = JOptionPane.showConfirmDialog(View.this, add, "Inserimento studente", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
               model.add(add.getProdotto());              
            }
            break;
         case "Visualizza prodotti":
             Prodotti[] v = new Prodotti[model.getProducts().size()];
             model.getProducts().toArray(v);
             list.setListData(v);
             
             //list.setListData(prodotti);
             //list2.setListData(operazioni);
             break;
         case "Operazioni":
             Object []buttons = {"Preleva","Deposita"};
		int i = JOptionPane.showOptionDialog(null,
			"Scegli un opzione", "Scelta operazione",
			JOptionPane.YES_NO_OPTION,
    		JOptionPane.QUESTION_MESSAGE,
    		null,
 			buttons, buttons[0]);
                int opnumprod = Integer.parseInt(JOptionPane.showInputDialog(null,"Inserisci numero prodotto"));
                int opgiac = Integer.parseInt(JOptionPane.showInputDialog(null,"Inserisci giacenza"));
		if(i == JOptionPane.YES_NO_OPTION)
                    
                    model.Prelievo(opnumprod, opgiac);
                    //System.exit(0);
                else{
                    model.Deposito(opnumprod, opgiac);
                }
             break;
         case "Visualizza op/saldo":
             Operazioni[] c = new Operazioni[model.getOperation().size()];
             model.getOperation().toArray(c);
             list2.setListData(c);
             
             break;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

    /**
     * 
     * @param prodotti parametro che richiede un prodotto
     * @param operazioni parametro che richiede un operazione
     * @param status parametro che richiede uno status
     */
    @Override
    public void modelChanged(Prodotti[] prodotti, Operazioni[] operazioni, Model.Status status) {
        switch(status){
        case openedError :
             JOptionPane.showMessageDialog(this, "Errore nell'apertura del file", "Errore", JOptionPane.ERROR_MESSAGE);
             break;
          case savedError:
             JOptionPane.showMessageDialog(this, "Errore nel salvataggio", "Errore", JOptionPane.ERROR_MESSAGE);
             break;
          case addError:
             JOptionPane.showMessageDialog(this, "Codici uguali. Studente non aggiunto", "Errore", JOptionPane.ERROR_MESSAGE);
             break;
          case deleteError:
             JOptionPane.showMessageDialog(this, "Prodotto inesistente", "Errore", JOptionPane.ERROR_MESSAGE);
             break;
          case qntError:
              JOptionPane.showMessageDialog(this, "I prodotti sono esauriti", "Errore", JOptionPane.ERROR_MESSAGE);
              break;
    }
        //delete.setEnabled(false);
      //if(students.length!=0){
         //delete.setEnabled(true);
      //}
      //list.setListData(prodotti);
      //list2.setListData(operazioni);
}
}
