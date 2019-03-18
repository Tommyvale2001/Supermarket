
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author INFO18
 * classe contenete i metodi per l'aggiunta del pannello
 */
public class AddPanel extends JPanel {

    private JTextField nomeprod;
    private JTextField giacenza;
    private Model m;
    
    /**
     * Costruttore che aggiunge un pannello per l'input dei prodotti
     */
    public AddPanel() {
        nomeprod = new JTextField();
        giacenza = new JTextField();
        m= new Model();
        setLayout(new GridLayout(4, 2));       
        this.add(new JLabel("Nome prodotto:"));
        this.add(nomeprod);
        this.add(new JLabel("Giacenza:"));
        this.add(giacenza);
    }
    
    /**
     * 
     * @param product setta i prodotti aggiunti alla JTextField
     * 
     */
    public void setProdotto(Prodotti product) {
        
        nomeprod.setText(product.getNomeprod());
        giacenza.setText(String.valueOf(product.getGiacenza()));
    }
    /**
     * 
     * @return ritorna il prodotto aggiunto alla TextField
     */
    public Prodotti getProdotto() {
        return new Prodotti(m.getContaProdotti(), nomeprod.getText(), Integer.parseInt(giacenza.getText()));
    }
}