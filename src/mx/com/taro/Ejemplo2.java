package mx.com.taro;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ejemplo2 extends JFrame implements ActionListener{

	JTextField nombre,apellido,bien;
	JButton boton;

	
	
	
	Ejemplo2(){
		
		crearComponentes();
		
	}
	
	public void crearComponentes() {
		setLayout(null);
		ImageIcon icon = new ImageIcon("src/resources/saveicon.png"); // load the image to a imageIcon
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg); 
		
//		ImageIcon icon = new ImageIcon("src/resources/saveicon.png");
		
		
		boton = new JButton(icon);
		boton.setBounds(5, 150, 70, 70);
		boton.addActionListener(this);
		add(boton);
		
		nombre = new JTextField();
		nombre.setBounds(5, 10, 100, 25);
//		nombre.setBackground(Color.pink);
		add(nombre);
		
		apellido = new JTextField();
		apellido.setBounds(5, 30, 100, 25);
		add(apellido);
		
		bien = new JTextField();
		bien.setBounds(5, 60, 100, 25);
		add(bien);

		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==boton) {
			String hola = "vientos";
			
			if(nombre.getText().equals("")) {
				nombre.setBackground(Color.pink);	
				
			}else {
				nombre.setBackground(Color.white);
			}
			
			if(apellido.getText().equals("")) {
				apellido.setBackground(Color.pink);
			}else {
				apellido.setBackground(Color.white);
				
			}
			
			if(apellido.getText().equals("")||nombre.getText().equals("")) {
				infoBox("Holakase","Title");
				
			}
			
			
		}
		
	}
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }
	

}
