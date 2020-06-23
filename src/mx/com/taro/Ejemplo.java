package mx.com.taro;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ejemplo extends JFrame implements ActionListener,ChangeListener {
		JTextField textfid;
		JRadioButton rbVegetariano,rbVegano,rbNinguno;
//		RadioListener myListener;
		ButtonGroup bg;
	
	public Ejemplo(){
		
		crearComponentes();
		
		
	}
	
	public void crearComponentes() {
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		textfid= new JTextField();
//		textf1.setBounds(x, y, width, height);
		textfid.setBounds(120, 5, 150, 20);
		textfid.setBackground(Color.pink);
		add(textfid);
//		textfid.setEnabled(false);
//		textfid.setVisible(false);
		
		
		
		rbVegetariano = new JRadioButton("VEGETARIANO");
		rbVegetariano.setBounds(110, 415, 130, 30);
		rbVegano = new JRadioButton("VEGANO");
		rbVegano.setBounds(228, 415, 100, 30);
		rbNinguno = new JRadioButton("NINGUNO",true);
		rbNinguno.setBounds(315, 415, 100, 30);
//		rbNinguno.setSelected(true);
		
		bg = new ButtonGroup();
		bg.add(rbVegetariano);
		bg.add(rbVegano);
		bg.add(rbNinguno);
		
//	    myListener = new RadioListener();
//        displacement.addActionListener(myListener);
//        accel.addActionListener(myListener);
//        time.addActionListener(myListener);
		
		rbVegetariano.addChangeListener(this);
		rbVegano.addChangeListener(this);
		rbNinguno.addChangeListener(this);
		
		add(rbNinguno);
		add(rbVegetariano);
		add(rbVegano);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(rbVegetariano.isSelected()) {
			textfid.setText(rbVegetariano.getText());
		}
		if(rbVegano.isSelected()) {
			textfid.setText(rbVegano.getText());
		}
		if(rbNinguno.isSelected()) {
			textfid.setText("");
		}
		
	}

	
//}

//public class SelectItemListener implements ItemListener{
//
//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
