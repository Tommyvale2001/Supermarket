
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author dodo99
 * classe contenete i metodi per l'aggiunta del pannello
 */
public class AddPanel extends JPanel {

    private JTextField nomeprod;
    private JTextField giacenza;
    private JTextField prezzo;
    private Model m;
    
    /**
     * Costruttore che aggiunge un pannello per l'input dei prodotti
     */
    public AddPanel() {
        nomeprod = new JTextField();
        giacenza = new JTextField();
        prezzo = new JTextField();
        m= new Model();
        setLayout(new GridLayout(4, 2));       
        this.add(new JLabel("Nome prodotto:"));
        this.add(nomeprod);
        this.add(new JLabel("Giacenza:"));
        this.add(giacenza);
        this.add(new JLabel("Prezzo:"));
        this.add(prezzo);
    }
    
    /**
     * 
     * @param product setta i prodotti aggiunti alla JTextField
     * 
     */
    public void setProdotto(Prodotti product) {
        
        nomeprod.setText(product.getNomeprod());
        giacenza.setText(String.valueOf(product.getGiacenza()));
        prezzo.setText(String.valueOf(product.getPrezzo()));
    }
    /**
     * 
     * @return ritorna il prodotto aggiunto alla TextField
     */
    public Prodotti getProdotto() {
        return new Prodotti(m.getContaProdotti(), nomeprod.getText(), Integer.parseInt(giacenza.getText()), Float.parseFloat(prezzo.getText()));
    }
}