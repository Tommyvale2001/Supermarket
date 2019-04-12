package provagrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Label;

public class fstjfrm extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fstjfrm frame = new fstjfrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public fstjfrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnFdg = new JButton("Admin");
		btnFdg.setBounds(572, 217, 242, 103);
		btnFdg.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnFdg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				try {
					
					String current = new java.io.File( "." ).getCanonicalPath();
					
					current= current +"\\Gestore_magazzino.jar";
					//System.out.println(current);
					runtime.exec("java -jar "+current);
					Thread.sleep(320);
					setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setBounds(40, 217, 242, 103);
		btnCustomer.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCustomer);
		contentPane.add(btnFdg);
		
		lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/supermarket.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));	
		lblNewLabel.setBounds(-90, -44, 1101, 494);
		contentPane.add(lblNewLabel);
	}
}
