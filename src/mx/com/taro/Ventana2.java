package mx.com.taro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import utilidades.Conexion;
import utilidades.ModeloTabla;

public class Ventana2 extends JFrame {
	
	public Ventana2(){
		super("Selected Row");
		
		Conexion conn = null;
		try {
			conn.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel label1=new JLabel("Nombre");
		label1.setBounds(10, 10, 300, 30);
		add(label1);
		
		JTextField textf1= new JTextField();
//		textf1.setBounds(x, y, width, height);
		textf1.setBounds(120, 5, 150, 20);
		add(textf1);
		
		JTextField textf2= new JTextField();
		textf2.setBounds(120, 30, 150, 20);
		add(textf2);
		
		JTextField textf3= new JTextField();
		textf3.setBounds(120, 60, 150, 20);
		add(textf3);
		
		JTextField textf4= new JTextField();
		textf4.setBounds(120, 90, 150, 20);
		add(textf4);
		
		JButton boton1= new JButton("Pasar");
		boton1.setName("Pasar");
		boton1.setBounds(5, 60, 100, 30);
		add(boton1);
		Botones botonaction = new Botones();
		boton1.addActionListener(botonaction);
		
		
		JButton boton2= new JButton("Pasarrrr");
		boton2.setName("Pasarrrr");
		boton2.setBounds(5, 80, 100, 30);
		add(boton2);
//		Botones botonaction2 = new Botones();
		boton2.addActionListener(botonaction);
		
		
		Object[][]data= {
				{"1","Hola",new Integer(2013),"21"},
				{"2","H12la",new Integer(20213),"21231"},
				{"3","Ho23la",new Integer(201353),"23451"},
				{"4","Hol123a",new Integer(201673),"24571"},
		};
		
		String[] header = {"Position","team","Las year","trophies"};
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		JPanel jp = new JPanel();
		jp.setBounds(5, 150, 300, 100);
		jp.setBorder(new EmptyBorder(5,5,5,5));
		jp.setLayout(new BorderLayout(0,0));
		
		JScrollPane pane = new JScrollPane();
		jp.add(pane);
		
		JTable table = new JTable(data,header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.setRowHeight(25);
		ModeloTabla modelo = new ModeloTabla(data,header);
		table.setModel(modelo);
		pane.setViewportView(table);
		
		
//		getContentPane().add(pane,BorderLayout.CENTER);
//		setSize(450,100);
		
		add(jp);
		
		/*JTable tiene un metodo llamado addMouseListener
		el cual intancia una clase abstracta llamada MouseAdapter (sin nombre de clase)
		en el mismo parametro, se instancia MouseApadater y la sobreescritura de uno de sus metdoos.
		que es mouseClicked
		la firma de mouseClicked en el parametro necesita un evento.
		getSource trae el componente que origino el evento -en este caso -table-*/
	      table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 1) {     // to detect double click events
	               int row = table.getSelectedRow(); // select a row
	               
	               textf1.setText((String)table.getValueAt(row, 0));
	               textf2.setText((String)table.getValueAt(row, 1));
	               textf3.setText(table.getValueAt(row, 2).toString());
	               textf4.setText((String)table.getValueAt(row, 3));
	               
//	              JOptionPane.showMessageDialog(null, table.getValueAt(row, column)); // get the value of a row and column.
	            }
	         }
	      });
//	      si se escribe esto, pasa al codigo de JTableListener.
//	      table.addMouseListener(new JTableListener());
		
		ListSelectionModel model = table.getSelectionModel();
		
		
		
	}


	
}



class JTableListener extends MouseAdapter{
	public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2) {     // to detect double click events
           JTable target = (JTable)me.getSource();
           int row = target.getSelectedRow(); // select a row
           int column = target.getSelectedColumn(); // select a column
          JOptionPane.showMessageDialog(null, target.getValueAt(row, column)); // get the value of a row and column.
        }
     }
  
}


//class Botones implements ActionListener{
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		JButton boton=(JButton)e.getSource();
//		if(boton.getName().equals("Pasar")) {
//			
//			System.exit(0);
//		}
//		if(boton.getName().equals("Pasarrrr")) {
//			
//			JOptionPane.showMessageDialog(null, "Boton pasarrrrrr");
//		}
//		
//	}
//	
//}


	



