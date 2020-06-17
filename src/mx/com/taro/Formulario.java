package mx.com.taro;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Formulario extends JFrame implements ActionListener{
	private JLabel label1,label2;
	JButton boton1,boton2,boton3;
	private JTextField textfieldNombre,textfieldTelefono;
	private JTextArea textarea1;
	//va junto con textarea
	private JScrollPane scrollpane1;
	private JPanel a ;
	String texto="";
	Ventana mi;
	private JPanel tablilla;

	
	public Formulario() {
		//para indicar que no se pone en cualquier lado
		setLayout(null);
		
//		label0=new JLabel("Nombre");
//		label0.setBounds(10, 0, 300, 30);
//		add(label0);
		
		a= new JPanel();
		a.setBounds(300, 10, 100, 50);
		a.setBackground(Color.RED);
		add(a);
		
		
		label1=new JLabel("Nombre");
		label1.setBounds(10, 5, 300, 30);
		add(label1);
		
		label2=new JLabel("Telefono");
		label2.setBounds(10, 40, 100, 30);
		//label2.setBounds(x, y, width, height);
		add(label2);
		
		textfieldNombre= new JTextField();
		textfieldNombre.setBounds(120, 5, 150, 20);
		add(textfieldNombre);
		
		textfieldTelefono= new JTextField();
		textfieldTelefono.setBounds(120, 40, 150, 20);
		add(textfieldTelefono);
		
		boton1= new JButton("Pasar");
		boton1.setBounds(5, 60, 100, 30);
		add(boton1);
		boton1.addActionListener(this);
		
		boton2= new JButton("Eliminar");
		boton2.setBounds(110, 60, 100, 30);
		add(boton2);
		boton2.addActionListener(this);
		
		boton3= new JButton("Cerrar");
		boton3.setBounds(5, 100, 100, 30);
		add(boton3);
		boton3.addActionListener(this);
		//this = instancia de esa clase
		
		//textarea esta dentro de scrollpane
//		textarea1=new JTextArea();
////		mi= new Ventana();
//		
//		
//		
		
		mi = new Ventana();
		tablilla=mi.getContP();
		tablilla.setBounds(5, 150, 300, 150);
		add(tablilla);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//objeto en donde se origino el evento, al darle click, el evento se va a guardar en e
		
		if(e.getSource()==boton2) {
			label1.setText("Se elimino correctamente");
		}
		if(e.getSource()==boton3) {
			System.exit(0);
		}
		
		
		if(e.getSource()==boton1) {
			texto+= textfieldNombre.getText()+"  "+textfieldTelefono.getText()+"\n";
			textarea1.setText(texto);
			textfieldNombre.setText("");
			textfieldTelefono.setText("");
			
			
		}
		
		
	}

}
