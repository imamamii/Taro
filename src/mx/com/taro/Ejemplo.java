package mx.com.taro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ejemplo extends JFrame implements ActionListener, ChangeListener {
//		JTextField cumple,edad;
////		JRadioButton rbVegetariano,rbVegano,rbNinguno;
////		RadioListener myListener;
//		ButtonGroup bg;

	JComboBox<String> dia, mes, anio;
	Integer ldD, ldM, ldA;
	JButton boton;
	JTextField txt;

//	
	public Ejemplo() throws ParseException {
		crearComponentes();
//		calcular(ldD,ldM,ldA);
	}

	public void crearComponentes() {
		setLayout(null);
		
		boton = new JButton();
		boton.setBounds(5, 150, 30, 30);
		add(boton);
		
		txt = new JTextField();
		txt.setBounds(35, 150, 100, 25);
		add(txt);

		dia = new JComboBox();
		dia.setBounds(5, 5, 100, 25);
		

		mes = new JComboBox();
		mes.setBounds(5, 30, 100, 25);

		anio = new JComboBox();
		anio.setBounds(5, 60, 100, 25);

//		dia.setToolTipText("DIA");

		for (int d = 0; d <= 31; d++) {
			if (d == 0) {
				dia.addItem("Dia");
			} else {
				dia.addItem(String.valueOf(d));
			}
		}
		add(dia);

		for (int m = 0; m <= 12; m++) {
			if (m == 0) {
				mes.addItem("Mes");
			} else {
				mes.addItem(String.valueOf(m));
			}
		}
		add(mes);

		LocalDate yearNow = LocalDate.now();
		for (int a = 1990; a <=yearNow.getYear() ; a++) {
			if (a == 0) {
				anio.addItem("Anio");
			} else {
				anio.addItem(String.valueOf(a));
			}
		}
		add(anio);

//		this.ldD = Integer.parseInt((String)dia.getSelectedItem());

//		for(int m = 1 ; m<=12;m++) {
//			mes.addItem(Integer.toString(m));
//		}
//		this.ldM= Integer.parseInt((String)mes.getSelectedItem());
//		
//		LocalDate yearNow = LocalDate.now();
//		for(int a = 1900 ; a<=yearNow.getYear();a++) {
//			anio.addItem(Integer.toString(a));
//		}
//		this.ldA=Integer.parseInt((String)anio.getSelectedItem());
//		

//		setLayout(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		
//		
//		
//		cumple = new JTextField();
//		cumple.setBounds(120, 5, 150, 20);
//		add(cumple);
//		
//		edad = new JTextField();
//		edad.setBounds(120, 5, 150, 20);
//		add(edad);
//		
//		
//		

//	    myListener = new RadioListener();
//        displacement.addActionListener(myListener);
//        accel.addActionListener(myListener);
//        time.addActionListener(myListener);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==boton) {
		this.ldD = Integer.parseInt((String)dia.getSelectedItem());	
		this.ldM = Integer.parseInt((String)mes.getSelectedItem());	
		this.ldA = Integer.parseInt((String)anio.getSelectedItem());
		try {
			txt.setText(calcular(ldA, ldM, ldD));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
			
			
		}
	}

//	@Override
//	public void stateChanged(ChangeEvent e) {
//		// TODO Auto-generated method stub
//		if(rbVegetariano.isSelected()) {
//			textfid.setText(rbVegetariano.getText());
//		}
//		if(rbVegano.isSelected()) {
//			textfid.setText(rbVegano.getText());
//		}
//		if(rbNinguno.isSelected()) {
//			textfid.setText("");
//		}
//		
//	}
	public static String calcular(int ldA, int ldM, int ldD) throws ParseException {

		LocalDate l = LocalDate.of(ldA, ldM, ldD); // specify year, month, date directly
		LocalDate now = LocalDate.now(); // gets localDate
		Period diff = Period.between(l, now); // difference between the dates is calculated
//		System.out.println(diff.getYears() + "years" + diff.getMonths() + "months" + diff.getDays() + "days");
		return String.valueOf(diff.getYears());
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

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
