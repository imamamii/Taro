package mx.com.taro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import utilidades.ModeloTabla;

public class Sistema extends JFrame implements ActionListener{
	
	JTextField textfid,textfNombre,textfApellido,textfTel,textfCalle,textfNumInt,textfNumExt,textfColonia,
	textfDel,textfCP,textfECalle,textfYCalle,textfColor,textfAlergia,textfIntolerancia,
	textfCumple,textfEdad,textfPreferencia,textfInstagram,textfFb,textfCorreo,textfNotas,filtro;
	
	JLabel labelTitulo,labelNombre,labelApellido,labelTel,labelDireccion,labelCalle,labelNumInt,labelNumExt,labelColonia,
	labelDel,labelCP,labelECalle,labelYCalle,labelColor,labelAlergia,labelIntolerancia,labelInfoExtra,labelCumple,labelEdad,
	labelPreferencia,labelInstagram,labelFb,labelCorreo,labelNotas;
	
	JRadioButton rbVegetariano,rbVegano;
	
	JButton bGuardar,bModificar,bNuevo,bEliminar;
	
	
	
	String []textFields;
	JButton boton1 ,boton2 ,boton3, boton4;
	JTable table;
	private TableRowSorter<TableModel> rowSorter;
	ModeloTabla modelo;
	
	public Sistema() {
		
		crearComponentes();
		
//		textf1.setBounds(x, y, width, height);
	}
	public void crearComponentes() {
		
		setLayout(null);
		
		labelTitulo = new JLabel("Registro Clientes");
		labelTitulo.setBounds(5, 5, 500, 45);
		labelTitulo.setFont(new Font("Arial", Font.PLAIN, 40));
		add(labelTitulo);
		
		labelNombre = new JLabel("NOMBRE");
		labelNombre.setBounds(5, 80, 80, 30);
		labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelNombre);
		
//		textf = new JTextField();
//		textf.setBounds(100, 80, 80, 30);
//		add(textf);
		
		textfNombre = new JTextField();
		textfNombre.setBounds(100, 80, 80, 30);
		add(textfNombre);
		
		labelApellido = new JLabel("APELLIDO");
		labelApellido.setBounds(5, 100, 80, 30);
		labelApellido.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelApellido);
		
		labelTel = new JLabel("TEL");
		labelTel.setBounds(5, 120, 80, 30);
		labelTel.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelTel);
		
//		label = new JLabel("");
//		label.setBounds(5, 120, 80, 30);
//		label.setFont(new Font("Arial", Font.PLAIN, 14));
//		add(label);
		
		labelDireccion = new JLabel("DIRECCION");
		labelDireccion.setBounds(5, 160, 140, 30);
		labelDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		add(labelDireccion);
		
		labelCalle = new JLabel("CALLE");
		labelCalle.setBounds(5, 180, 80, 30);
		labelCalle.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelCalle);
		
		labelNumExt = new JLabel("N° EXT.");
		labelNumExt.setBounds(5, 200, 80, 30);
		labelNumExt.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelNumExt);
		
		labelNumInt = new JLabel("N° INT.");
		labelNumInt.setBounds(100, 200, 80, 30);
		labelNumInt.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelNumInt);
		
		labelColonia = new JLabel("COLONIA");
		labelColonia.setBounds(5, 220, 100, 30);
		labelColonia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelColonia);
		
		labelDel = new JLabel("DELEGACION");
		labelDel.setBounds(5, 240, 100, 30);
		labelDel.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelDel);
		
		labelCP = new JLabel("CP");
		labelCP.setBounds(5, 260, 50, 30);
		labelCP.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelCP);
		
		labelECalle = new JLabel("ENTRE CALLE");
		labelECalle.setBounds(10, 280, 100, 30);
		labelECalle.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelECalle);
		
		textfECalle = new JTextField();
		textfECalle.setBounds(110, 280, 80, 25);
		add(textfECalle);
		
		labelYCalle = new JLabel("Y CALLE");
		labelYCalle.setBounds(5, 300, 80, 30);
		labelYCalle.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelYCalle);
		
		labelColor = new JLabel("COLOR");
		labelColor.setBounds(5, 320, 80, 30);
		labelColor.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelColor);
		
		labelAlergia = new JLabel("ALERGIA");
		labelAlergia.setBounds(5, 340, 80, 30);
		labelAlergia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelAlergia);
		
		labelIntolerancia = new JLabel("INTOLERANCIA");
		labelIntolerancia.setBounds(100, 340, 120, 30);
		labelIntolerancia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelIntolerancia);
		
		labelInfoExtra = new JLabel("INFORMACION EXTRA");
		labelInfoExtra.setBounds(5, 380, 180, 30);
		labelInfoExtra.setFont(new Font("Arial", Font.PLAIN, 16));
		add(labelInfoExtra);
		
		labelCumple = new JLabel("CUMPLEAÑOS");
		labelCumple.setBounds(5, 400, 120, 30);
		labelCumple.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelCumple);
		
		labelEdad = new JLabel("EDAD");
		labelEdad.setBounds(5, 420, 80, 30);
		labelEdad.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelEdad);
		
		labelPreferencia = new JLabel("PREFERENCIA");
		labelPreferencia.setBounds(5, 460, 130, 30);
		labelPreferencia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelPreferencia);
		
		labelInstagram = new JLabel("INSTAGRAM");
		labelInstagram.setBounds(5, 480, 120, 30);
		labelInstagram.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelInstagram);
		
		labelFb = new JLabel("FACEBOOK");
		labelFb.setBounds(5, 500, 100, 30);
		labelFb.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelFb);
		
		labelCorreo = new JLabel("CORREO");
		labelCorreo.setBounds(5, 520, 80, 30);
		labelCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelCorreo);
		
		labelNotas = new JLabel("NOTAS");
		labelNotas.setBounds(5, 540, 80, 30);
		labelNotas.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelNotas);
		
		
		
		
		
//		textfid= new JTextField();
//		textfid.setBounds(120, 5, 150, 20);
//		add(textfid);
//		textfid.setEnabled(false);
//		textfid.setVisible(false);
//		
//		textfNombre= new JTextField();
//		textfNombre.setBounds(120, 30, 150, 20);
//		add(textfNombre);
//		
//		textfApellido= new JTextField();
//		textfApellido.setBounds(120, 60, 150, 20);
//		add(textfApellido);
//		
//		textfTel= new JTextField();
//		textfTel.setBounds(120, 90, 150, 20);
//		add(textfTel);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	
