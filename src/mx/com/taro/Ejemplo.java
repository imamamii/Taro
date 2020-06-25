package mx.com.taro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class Ejemplo extends JFrame implements ActionListener, ChangeListener {
//		JTextField cumple,edad;
////		JRadioButton rbVegetariano,rbVegano,rbNinguno;
////		RadioListener myListener;
//		ButtonGroup bg;

	JComboBox<String> dia, mes, anio;
	Integer ldD, ldM, ldA;
	JButton boton;
	JTextField txt;
	LocalDate yearNow;

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
		txt.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String text = txt.getText();

				if (text.trim().length() == 0) {
					anio.setSelectedIndex(0);
//					rowSorter.setRowFilter(null);
				} else {
					
					anio.setSelectedIndex(Integer.parseInt(text));
					
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(txt);
		
		dia = new JComboBox();
		dia.setBounds(5, 5, 100, 25);

		mes = new JComboBox();
		mes.setBounds(5, 30, 100, 25);

		anio = new JComboBox();
		anio.setBounds(5, 60, 100, 25);
		anio.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(dia.getSelectedItem().equals("Dia")){
					ldD=1;
				}else {
					ldD = Integer.parseInt((String)dia.getSelectedItem());
				}
				
				if(mes.getSelectedItem().equals("Mes")){
					ldM=1;
				}else {
					ldM = Integer.parseInt((String)mes.getSelectedItem());
				}
				
				
				ldA = Integer.parseInt((String)anio.getSelectedItem());
					try {
						txt.setText(calcular(ldA, ldM, ldD));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

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
		
		

		yearNow = LocalDate.now();
		for (int a = yearNow.getYear()+1; a >=1900 ; a--) {
			if (a == yearNow.getYear()+1) {
				anio.addItem("Anio");
			} else {
				anio.addItem(String.valueOf(a));
			}
		}
	
		anio.addActionListener(this);
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
//		this.ldD = Integer.parseInt((String)dia.getSelectedItem());
//		this.ldM = Integer.parseInt((String)mes.getSelectedItem());
//		this.ldA = Integer.parseInt((String)anio.getSelectedItem());
////		try {
//			txt.setText(calcular(ldA, ldM, ldD));
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
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
	
	public void obtenerFiltro() {

//		table.setRowSorter(rowSorter);

		txt.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				String text = txt.getText();

				if (text.trim().length() == 0) {
					anio.setSelectedIndex(0);
//					rowSorter.setRowFilter(null);
				} else {
					
					anio.setSelectedIndex(Integer.parseInt(text));
					
				}
			}

			public void removeUpdate(DocumentEvent e) {
				String text = txt.getText();

				if (text.trim().length() == 0) {
					anio.setSelectedIndex(0);
//					rowSorter.setRowFilter(null);
				} else {
					
					
					anio.setSelectedIndex(Integer.parseInt(text));
					
				}
			}
				

			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});
	}
	
	
	

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}
//	class ItemChangeListener implements ItemListener{
//
//		@Override
//		public void itemStateChanged(ItemEvent event) {
//			// TODO Auto-generated method stub
//			if (event.getStateChange() == ItemEvent.SELECTED) {
//				Object item = event.getItem();
//				// do something with object
//			}
//		}
//			
//		}       
	

//}

//public class SelectItemListener implements ItemListener{
//
//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
//		
//	}

}
