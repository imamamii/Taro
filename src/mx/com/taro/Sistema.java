package mx.com.taro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import utilidades.Conexion;
import utilidades.Edad;
import utilidades.ModeloTabla;

public class Sistema extends JFrame implements ActionListener {
	
	

	private JTextField textfid, textfNombre, textfApellido, textfTel, textfCalle, textfNumInt, textfNumExt, textfColonia,
			textfDel, textfCP, textfECalle, textfYCalle, textfColor, textfAlergia, textfIntolerancia, textfCumple,
			textfEdad, textfPreferencia, textfInstagram, textfFb, textfCorreo, filtro;

	private JLabel labelTitulo, labelNombre, labelApellido, labelTel, labelDireccion, labelCalle, labelNumInt, labelNumExt,
			labelColonia, labelDel, labelCP, labelECalle, labelYCalle, labelColor, labelAlergia, labelIntolerancia,
			labelInfoExtra, labelCumple, labelEdad, labelPreferencia, labelInstagram, labelFb, labelCorreo, labelNotas;

	private JRadioButton rbVegetariano, rbVegano, rbNinguno;
	
	private JComboBox dia, mes, anio;
	

	JButton bGuardar, bModificar, bNuevo, bEliminar, bCancelar;
	
	LocalDate yearNow=LocalDate.now();;
	
	Integer ldD=1, ldM=1, ldA=yearNow.getYear();

	JTextArea taNotas;
	JScrollPane spNotas;
	
	JPanel todo = new JPanel();

	String[] textFields;
//	JButton boton1 ,boton2 ,boton3, boton4;
	JTable table;
	private TableRowSorter<TableModel> rowSorter;
	ModeloTabla modelo;

	public Sistema() {
		
//		todo.setBounds(0, 0, 435, 800);
//		add(todo);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setIconImage(new ImageIcon(getClass().getResource("/Taro/sloth.png")).getImage());
		getContentPane().setBackground(new Color(140,160,80));
		crearComponentes();
		iniciarTabla();
		ingresarDatosTabla(selectConexion());
		textFields = new String[23];
		pasarTablaTextF();
		obtenerFiltro();
		habilitarDefaultBotones();
		deshabilitarCampos();

//		textf1.setBounds(x, y, width, height);
	}

	public void crearComponentes() {

		setLayout(null);

		labelTitulo = new JLabel("Registro Clientes");
		labelTitulo.setBounds(5, 5, 500, 45);
		labelTitulo.setFont(new Font("Arial", Font.PLAIN, 40));
		labelTitulo.setForeground(Color.WHITE);
		add(labelTitulo);

		textfid = new JTextField();
		textfid.setBounds(200, 5, 30, 25);
		textfid.setVisible(false);
		add(textfid);

		labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(16, 50, 80, 30);
		labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		labelNombre.setForeground(Color.WHITE);
		add(labelNombre);

		textfNombre = new JTextField();
		textfNombre.setBounds(12, 70, 200, 25);
		textfNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		add(textfNombre);

		labelApellido = new JLabel("Apellido:");
		labelApellido.setBounds(222, 50, 80, 30);
		labelApellido.setFont(new Font("Arial", Font.PLAIN, 14));
		labelApellido.setForeground(Color.WHITE);
//		labelApellido.setVisible(false);
		add(labelApellido);

		textfApellido = new JTextField();
		textfApellido.setBounds(218, 70, 205, 25);
		textfApellido.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfApellido.setVisible(false);
		add(textfApellido);

		labelTel = new JLabel("Teléfono:");
		labelTel.setBounds(16, 93, 80, 30);
		labelTel.setFont(new Font("Arial", Font.PLAIN, 14));
		labelTel.setForeground(Color.WHITE);
//		labelTel.setVisible(false);
		add(labelTel);

		textfTel = new JTextField();
		textfTel.setBounds(12, 113, 200, 25);
		textfTel.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfTel.setVisible(false);
		add(textfTel);

//		label = new JLabel("");
//		label.setBounds(5, 120, 80, 30);
//		label.setFont(new Font("Arial", Font.PLAIN, 14));
//		add(label);

//		labelDireccion = new JLabel("DIRECCION");
//		labelDireccion.setBounds(5, 140, 140, 30);
//		labelDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
//		labelDireccion.setVisible(false);
//		add(labelDireccion);
		
		labelCalle = new JLabel("Calle:");
		labelCalle.setBounds(10, 15, 78, 30);
		labelCalle.setFont(new Font("Arial", Font.PLAIN, 14));
		labelCalle.setForeground(Color.WHITE);
//		labelCalle.setVisible(false);
		
		textfCalle = new JTextField();
		textfCalle.setBounds(6, 35, 200, 25);
		textfCalle.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfCalle.setVisible(false);
		
		
		labelNumExt = new JLabel("N° Ext.");
		labelNumExt.setBounds(216, 15, 80, 30);
		labelNumExt.setFont(new Font("Arial", Font.PLAIN, 14));
		labelNumExt.setForeground(Color.WHITE);
//		labelNumExt.setVisible(false);
		
		
		textfNumExt = new JTextField();
		textfNumExt.setBounds(212, 35, 100, 25);
		textfNumExt.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfNumExt.setVisible(false);
		
		
		labelNumInt = new JLabel("N° Int.");
		labelNumInt.setBounds(321, 15, 80, 30);
		labelNumInt.setFont(new Font("Arial", Font.PLAIN, 14));
		labelNumInt.setForeground(Color.WHITE);
//		labelNumInt.setVisible(false);
		
		
		textfNumInt = new JTextField();
		textfNumInt.setBounds(317, 35, 100, 25);
		textfNumInt.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfNumInt.setVisible(false);
		
		
		
		labelColonia = new JLabel("Colonia:");
		labelColonia.setBounds(10,58, 100, 30);
		labelColonia.setFont(new Font("Arial", Font.PLAIN, 14));
		labelColonia.setForeground(Color.WHITE);
//		labelColonia.setVisible(false);
		
		
		textfColonia = new JTextField();
		textfColonia.setBounds(6, 78, 200, 25);
		textfColonia.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfColonia.setVisible(false);
		
		
		labelDel = new JLabel("Delegacion:");
		labelDel.setBounds(216, 58, 100, 30);
		labelDel.setFont(new Font("Arial", Font.PLAIN, 14));
		labelDel.setForeground(Color.WHITE);
//		labelDel.setVisible(false);
		
		
		textfDel = new JTextField();
		textfDel.setBounds(212, 78, 205, 25);
		textfDel.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfDel.setVisible(false);
		
		labelECalle = new JLabel("Entre calle:");
		labelECalle.setBounds(10, 101, 100, 30);
		labelECalle.setFont(new Font("Arial", Font.PLAIN, 14));
		labelECalle.setForeground(Color.WHITE);
//		labelECalle.setVisible(false);
		
		
		textfECalle = new JTextField();
		textfECalle.setBounds(6, 121, 200, 25);
		textfECalle.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfECalle.setVisible(false);
		
		
		labelYCalle = new JLabel("Y calle:");
		labelYCalle.setBounds(216, 101, 80, 30);
		labelYCalle.setFont(new Font("Arial", Font.PLAIN, 14));
		labelYCalle.setForeground(Color.WHITE);
//		labelYCalle.setVisible(false);
		
		
		textfYCalle = new JTextField();
		textfYCalle.setBounds(212, 121, 205, 25);
		textfYCalle.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfYCalle.setVisible(false);
		
		
		labelCP = new JLabel("C.P.");
		labelCP.setBounds(10, 144, 48, 30);
		labelCP.setFont(new Font("Arial", Font.PLAIN, 14));
		labelCP.setForeground(Color.WHITE);
//		labelCP.setVisible(false);
		
		
		textfCP = new JTextField();
		textfCP.setBounds(6, 164, 100, 25);
		textfCP.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfCP.setVisible(false);
		
		
		labelColor = new JLabel("Referencia:");
		labelColor.setBounds(111, 144, 100, 30);
		labelColor.setFont(new Font("Arial", Font.PLAIN, 14));
		labelColor.setForeground(Color.WHITE);
//		labelColor.setVisible(false);
		
		
		textfColor = new JTextField();
		textfColor.setBounds(107, 164, 311, 25);
		textfColor.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfColor.setVisible(false);
		
		
		
		
		
		
		
		
		JPanel pdireccion = new JPanel();
		pdireccion.setLayout(null);
		pdireccion.setBounds(6, 145, 425, 200);
		pdireccion.setBackground(new Color(140,160,80));
		TitledBorder td = new TitledBorder("Direccion");
		td.setTitleFont(new Font("Arial", Font.PLAIN, 15));
		td.setTitleColor(Color.WHITE);
//		td.set
		
		pdireccion.setBorder(td);
		pdireccion.add(labelCalle);
		pdireccion.add(textfCalle);
		pdireccion.add(labelNumExt);
		pdireccion.add(textfNumExt);
		pdireccion.add(textfNumInt);
		pdireccion.add(labelNumInt);
		pdireccion.add(labelColonia);
		pdireccion.add(textfColonia);
		pdireccion.add(labelDel);
		pdireccion.add(textfDel);
		pdireccion.add(labelECalle);
		pdireccion.add(textfECalle);
		pdireccion.add(labelYCalle);
		pdireccion.add(textfYCalle);
		pdireccion.add(labelCP);
		pdireccion.add(textfCP);
		pdireccion.add(labelColor);
		pdireccion.add(textfColor);
		add(pdireccion);



		labelAlergia = new JLabel("Alergia:");
		labelAlergia.setBounds(10, 15, 80, 30);
		labelAlergia.setFont(new Font("Arial", Font.PLAIN, 14));
		labelAlergia.setForeground(Color.WHITE);
//		labelAlergia.setVisible(false);
		

		textfAlergia = new JTextField();
		textfAlergia.setBounds(6, 35, 200, 25);
		textfAlergia.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfAlergia.setVisible(false);
		

		labelIntolerancia = new JLabel("Intolerancia:");
		labelIntolerancia.setBounds(216, 15, 120, 30);
		labelIntolerancia.setFont(new Font("Arial", Font.PLAIN, 14));
		labelIntolerancia.setForeground(Color.WHITE);
//		labelIntolerancia.setVisible(false);
		

		textfIntolerancia = new JTextField();
		textfIntolerancia.setBounds(212, 35, 205, 25);
		textfIntolerancia.setFont(new Font("Arial", Font.PLAIN, 14));
//		textfIntolerancia.setVisible(false);
		

		rbVegetariano = new JRadioButton("Vegetariano");
		rbVegetariano.setBounds(60, 410, 130, 30);
		rbVegetariano.setForeground(Color.WHITE);
		rbVegetariano.setBackground(new Color(140,160,80));
		rbVegano = new JRadioButton("Vegano");
		rbVegano.setBounds(188, 410, 100, 30);
		rbVegano.setForeground(Color.WHITE);
		rbVegano.setBackground(new Color(140,160,80));
		rbNinguno = new JRadioButton("Ninguno", true);
		rbNinguno.setBounds(285, 410, 100, 30);
		rbNinguno.setForeground(Color.WHITE);
		rbNinguno.setBackground(new Color(140,160,80));
		rbNinguno.setSelected(true);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rbVegetariano);
		bg.add(rbVegano);
		bg.add(rbNinguno);

//		rbVegetariano.addChangeListener(this);
//		rbVegano.addChangeListener(this);
//		rbNinguno.addChangeListener(this);

		add(rbNinguno);
		add(rbVegetariano);
		add(rbVegano);
		
		JPanel pmedico = new JPanel();
		pmedico.setLayout(null);
		pmedico.setBounds(6, 345, 425, 100);
		pmedico.setBackground(new Color(140,160,80));
		TitledBorder mtd = new TitledBorder("Regimen");
		mtd.setTitleFont(new Font("Arial", Font.PLAIN, 15));
		mtd.setTitleColor(Color.WHITE);
		pmedico.setBorder(mtd);
		pmedico.add(labelAlergia);
		pmedico.add(textfAlergia);
		pmedico.add(labelIntolerancia);
		pmedico.add(textfIntolerancia);
		add(pmedico);
		

//		labelInfoExtra = new JLabel("INFORMACION EXTRA");
//		labelInfoExtra.setBounds(5, 445, 180, 30);
//		labelInfoExtra.setFont(new Font("Arial", Font.PLAIN, 16));
//		add(labelInfoExtra);

		labelCumple = new JLabel("Cumpleaños:");
		labelCumple.setBounds(10, 15, 120, 30);
		labelCumple.setFont(new Font("Arial", Font.PLAIN, 14));
		labelCumple.setForeground(Color.WHITE);
		

//		textfCumple = new JTextField();
//		textfCumple.setBounds(110, 470, 300, 25);
//		add(textfCumple);
		
		dia = new JComboBox();
		//dia.setBounds(10, 490, 90, 25);
		dia.setBounds(10, 485, 80, 25);
		dia.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				pasarSeleccionCumple();
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		mes = new JComboBox();
//		mes.setBounds(100, 490, 90, 25);
		mes.setBounds(88, 485, 125, 25);
		mes.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				pasarSeleccionCumple();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});

		anio = new JComboBox();
//		anio.setBounds(190, 490, 100, 25);
		anio.setBounds(211, 485, 90, 25);
		
		
		anio.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				pasarSeleccionCumple();
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		for (int d = 0; d <= 31; d++) {
			if (d == 0) {
				dia.addItem("Día");
			} else {
				dia.addItem(String.valueOf(d));
			}
		}
		
		add(dia);
		
		
		mes.addItem(new ComboItem("Mes", "1"));
		mes.addItem(new ComboItem("Enero", "1"));
		mes.addItem(new ComboItem("Febrero", "2"));
		mes.addItem(new ComboItem("Marzo", "3"));
		mes.addItem(new ComboItem("Abril", "4"));
		mes.addItem(new ComboItem("Mayo", "5"));
		mes.addItem(new ComboItem("Junio", "6"));
		mes.addItem(new ComboItem("Julio", "7"));
		mes.addItem(new ComboItem("Agosto", "8"));
		mes.addItem(new ComboItem("Septiembre", "9"));
		mes.addItem(new ComboItem("Octubre", "10"));
		mes.addItem(new ComboItem("Noviembre", "11"));
		mes.addItem(new ComboItem("Diciembre", "12"));
		add(mes);
		
		
		for (int a = yearNow.getYear()+1; a >=1900 ; a--) {
			if (a == yearNow.getYear()+1) {
				anio.addItem("Año");
			} else {
				anio.addItem(String.valueOf(a));
			}
		}
	
		anio.addActionListener(this);
		add(anio);

		labelEdad = new JLabel("Edad:");
		labelEdad.setBounds(321, 15, 80, 30);
		labelEdad.setFont(new Font("Arial", Font.PLAIN, 14));
		labelEdad.setForeground(Color.WHITE);
		

		textfEdad = new JTextField();
		textfEdad.setBounds(317, 35, 100, 30);
		textfEdad.setFont(new Font("Arial", Font.PLAIN, 14));
		textfEdad.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String edad = textfEdad.getText();

				if (edad.trim().length() == 0) {
					anio.setSelectedIndex(0);
//					rowSorter.setRowFilter(null);
				} else {
					
					anio.setSelectedIndex(Integer.parseInt(edad)+1);
					ldA=yearNow.getYear()-(Integer.parseInt(edad));
//					anio.setSelectedIndex(ldA);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		

		labelPreferencia = new JLabel("Preferencia:");
		labelPreferencia.setBounds(10, 60, 130, 30);
		labelPreferencia.setFont(new Font("Arial", Font.PLAIN, 14));
		labelPreferencia.setForeground(Color.WHITE);
		

		textfPreferencia = new JTextField();
		textfPreferencia.setBounds(6, 80, 200, 25);
		textfPreferencia.setFont(new Font("Arial", Font.PLAIN, 14));
		
		labelCorreo = new JLabel("Correo:");
		labelCorreo.setBounds(216, 60, 80, 30);
		labelCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
		labelCorreo.setForeground(Color.WHITE);
		

		textfCorreo = new JTextField();
		textfCorreo.setBounds(212, 80, 205, 25);
		textfCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
		

		labelInstagram = new JLabel("Instagram:");
		labelInstagram.setBounds(10, 103, 120, 30);
		labelInstagram.setFont(new Font("Arial", Font.PLAIN, 14));
		labelInstagram.setForeground(Color.WHITE);
		

		textfInstagram = new JTextField();
		textfInstagram.setBounds(6, 123, 200, 25);
		textfInstagram.setFont(new Font("Arial", Font.PLAIN, 14));
		

		labelFb = new JLabel("Facebook:");
		labelFb.setBounds(216, 103, 100, 30);
		labelFb.setFont(new Font("Arial", Font.PLAIN, 14));
		labelFb.setForeground(Color.WHITE);
		

		textfFb = new JTextField();
		textfFb.setBounds(212, 123, 205, 25);
		textfFb.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		JPanel pie = new JPanel();
		pie.setLayout(null);
		pie.setBounds(6, 445, 425, 160);
		pie.setBackground(new Color(140,160,80));
		TitledBorder ietd = new TitledBorder("Informacion Extra");
		ietd.setTitleFont(new Font("Arial", Font.PLAIN, 15));
		ietd.setTitleColor(Color.WHITE);
		pie.setBorder(ietd);
		pie.add(labelCumple);
		pie.add(labelEdad);
		pie.add(textfEdad);
		pie.add(labelPreferencia);
		pie.add(textfPreferencia);
		pie.add(labelCorreo);
		pie.add(textfCorreo);
		pie.add(labelInstagram);
		pie.add(textfInstagram);
		pie.add(labelFb);
		pie.add(textfFb);
//		
//		pie.add(spNotas);
		
		add(pie);
		
		
//		labelNotas = new JLabel("Notas:");
//		labelNotas.setBounds(12, 166, 80, 30);
//		labelNotas.setFont(new Font("Arial", Font.PLAIN, 14));
		

		taNotas = new JTextArea();
		taNotas.setFont(new Font("Arial", Font.PLAIN, 14));
		spNotas = new JScrollPane(taNotas);
		spNotas.setBounds(10, 20, 403, 60);
		spNotas.setForeground(Color.WHITE);
		
		JPanel pnotas = new JPanel();
		pnotas.setLayout(null);
		pnotas.setBounds(6, 605, 425, 90);
		pnotas.setBackground(new Color(140,160,80));
		TitledBorder notastd = new TitledBorder("Notas");
		notastd.setTitleFont(new Font("Arial", Font.PLAIN, 15));
		notastd.setTitleColor(Color.WHITE);
		pnotas.setBorder(notastd);
//		pie.add(labelNotas);
		pnotas.add(spNotas);
		
		add(pnotas);
		
		
		
		
		
		ImageIcon iconGuardar = new ImageIcon("Taro_img/saveicon.png"); // load the image to a imageIcon
		Image imageGuardar = iconGuardar.getImage(); // transform it
		Image newimgGuardar = imageGuardar.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iconGuardar = new ImageIcon(newimgGuardar); 

		bGuardar = new JButton(iconGuardar);
//		bGuardar = new JButton("GUARDAR");
		bGuardar.setBounds(15, 697, 75, 75);
		bGuardar.setToolTipText("GUARDAR");
		add(bGuardar);
//		bGuardar.setVerticalAlignment(SwingConstants.BOTTOM);
		bGuardar.addActionListener(this);
		
		ImageIcon iconModificar = new ImageIcon("Taro_img/modifyicon.png"); // load the image to a imageIcon
		Image imageModificar = iconModificar.getImage(); // transform it
		Image newimgModificar = imageModificar.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iconModificar = new ImageIcon(newimgModificar); 

		bModificar = new JButton(iconModificar);
		bModificar.setBounds(95, 697, 75, 75);
		bModificar.setToolTipText("MODIFICAR");
		add(bModificar);
		bModificar.addActionListener(this);
		
		ImageIcon iconNuevo = new ImageIcon("Taro_img/newicon.png"); // load the image to a imageIcon
		Image imageNuevo = iconNuevo.getImage(); // transform it
		Image newimgNuevo = imageNuevo.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iconNuevo = new ImageIcon(newimgNuevo); 

		bNuevo = new JButton(iconNuevo);
		bNuevo.setBounds(175, 697, 75, 75);
		bNuevo.setToolTipText("NUEVO");
		add(bNuevo);
		bNuevo.addActionListener(this);

		ImageIcon iconEliminar = new ImageIcon("Taro_img/deleteicon2.png"); // load the image to a imageIcon
		Image imageEliminar = iconEliminar.getImage(); // transform it
		Image newimgEliminar = imageEliminar.getScaledInstance(53, 53,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iconEliminar = new ImageIcon(newimgEliminar); 
		
		bEliminar = new JButton(iconEliminar);
		bEliminar.setBounds(255, 697, 75, 75);
		bEliminar.setToolTipText("ELIMINAR");
		add(bEliminar);
		bEliminar.addActionListener(this);
		
		ImageIcon iconCancelar = new ImageIcon("Taro_img/cancelicon.png"); // load the image to a imageIcon
		Image imageCancelar = iconCancelar.getImage(); // transform it
		Image newimgCancelar = imageCancelar.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iconCancelar = new ImageIcon(newimgCancelar); 
		
		bCancelar = new JButton(iconCancelar);
		bCancelar.setBounds(335, 697, 75, 75);
		bCancelar.setToolTipText("CANCELAR");
		add(bCancelar);
		bCancelar.addActionListener(this);

	
		
		filtro = new JTextField();
		filtro.setBounds(660, 25, 410, 35);
		filtro.setFont(new Font("Arial", Font.PLAIN, 14));
		add(filtro);

	}

	public void iniciarTabla() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setGridColor(Color.GRAY);
		table.setRowHeight(20);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Arial", Font.PLAIN, 14));
		
		setLayout(null);

		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(440, 68, 840, 660);
		jp.setBorder(new EmptyBorder(5, 5, 5, 5));
		jp.setBackground(new Color(140,160,80));
		jp.setLayout(new BorderLayout(0, 0));

		JScrollPane pane = new JScrollPane();
		jp.add(pane);

		pane.setViewportView(table);

		add(jp);

	}

	public void ingresarDatosTabla(List<ClienteDTO> clientes) {

		String[] header = { "Id", "Nombre", "Apellido", "Telefono", "Calle", "N° Ext.", "N° Int", "Colonia",
				"Delegacion", "C.P.", "Entre calle", "y calle", "Color", "Alergico", "Intolerante", "Regimen", "Edad",
				"Cumpleaños", "Preferencia", "Instagram", "Facebook", "Correo", "Notas" };
		

		Object data[][] = new Object[clientes.size()][header.length];

		for (int x = 0; x < data.length; x++) {
			data[x][0] = clientes.get(x).getIdCliente();
			data[x][1] = clientes.get(x).getNombre();
			data[x][2] = clientes.get(x).getApellido();
			data[x][3] = clientes.get(x).getTelefono();
			data[x][4] = clientes.get(x).getCalle();
			data[x][5] = clientes.get(x).getNumExt();
			data[x][6] = clientes.get(x).getNumInt();
			data[x][7] = clientes.get(x).getColonia();
			data[x][8] = clientes.get(x).getDelegacion();
			data[x][9] = clientes.get(x).getCp();
			data[x][10] = clientes.get(x).geteCalle();
			data[x][11] = clientes.get(x).getyCalle();
			data[x][12] = clientes.get(x).getColor();
			data[x][13] = clientes.get(x).getAlergia();
			data[x][14] = clientes.get(x).getIntolerancia();
			data[x][15] = clientes.get(x).getRegimen();
			data[x][16] = clientes.get(x).getEdad();
			data[x][17] = clientes.get(x).getCumple();
			data[x][18] = clientes.get(x).getPreferencia();
			data[x][19] = clientes.get(x).getInstagram();
			data[x][20] = clientes.get(x).getFb();
			data[x][21] = clientes.get(x).getCorreo();
			data[x][22] = clientes.get(x).getNotas();

		}

		modelo = new ModeloTabla(data, header);

		table.setModel(modelo);
		TableColumnModel colsize = table.getColumnModel();
		 table.getColumnModel().getColumn(0).setMinWidth(0);
		 table.getColumnModel().getColumn(0).setMaxWidth(0);
		 table.getColumnModel().getColumn(0).setWidth(0);
		colsize.getColumn(1).setPreferredWidth(150);
		colsize.getColumn(2).setPreferredWidth(150);
		colsize.getColumn(3).setPreferredWidth(150);
		for(int i =4;i<header.length;i++) {
			colsize.getColumn(i).setPreferredWidth(90);
		}
		

	}

	public List<ClienteDTO> selectConexion() {
		Connection conexion = null;
		List<ClienteDTO> clientes = null;
		try {
			conexion = Conexion.getConnection();
			if (conexion.getAutoCommit()) {
				conexion.setAutoCommit(false);
			}
			ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conexion);
			clientes = clienteDao.select();

			Conexion.close(conexion);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			System.out.println("Entramos al rollback");
			try {
				conexion.rollback();
			} catch (SQLException ex1) {
				ex1.printStackTrace(System.out);
			}
		}
		return clientes;
	}

	public void insertConexion(ClienteDTO cliente) {

		Connection conexion = null;
//		List<ClienteDTO> clientes = null;
		try {
			conexion = Conexion.getConnection();
			if (conexion.getAutoCommit()) {
				conexion.setAutoCommit(false);
			}
//			ClienteDTO clienteNuevo = new ClienteDTO();
//			int ID = Integer.parseInt(textFields[0]);
//			clienteNuevo.setIdCliente(ID);
			
//			clienteNuevo.setNombre(textFields[1]);
//			clienteNuevo.setApellido(textFields[2]);
//			clienteNuevo.setTelefono(textFields[3]);
//			clienteNuevo.setCalle(textFields[4]);
//			clienteNuevo.setNumExt(textFields[5]);
//			clienteNuevo.setNumInt(textFields[6]);
//			clienteNuevo.setColonia(textFields[7]);
//			clienteNuevo.setDelegacion(textFields[8]);
//			clienteNuevo.setCp(textFields[9]);
//			clienteNuevo.seteCalle(textFields[10]);
//			clienteNuevo.setyCalle(textFields[11]);
//			clienteNuevo.setColor(textFields[12]);
//			clienteNuevo.setAlergia(textFields[13]);
//			clienteNuevo.setIntolerancia(textFields[14]);
//			clienteNuevo.setRegimen(textFields[15]);
//			clienteNuevo.setEdad(textFields[16]);
//			clienteNuevo.setCumple(textFields[17]);
//			clienteNuevo.setPreferencia(textFields[18]);
//			clienteNuevo.setInstagram(textFields[19]);
//			clienteNuevo.setFb(textFields[20]);
//			clienteNuevo.setCorreo(textFields[21]);
//			clienteNuevo.setNotas(textFields[22]);

			ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conexion);
			clienteDao.insert(cliente);

			conexion.commit();
			System.out.println("Se ha hecho commit de la transaccion");

			Conexion.close(conexion);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			System.out.println("Entramos al rollback");
			try {
				conexion.rollback();
			} catch (SQLException ex1) {
				ex1.printStackTrace(System.out);
			}
		}

	}

	public void updateConexion(ClienteDTO cliente) {
		Connection conexion = null;
//		List<ClienteDTO> clientes = null;
		try {
			conexion = Conexion.getConnection();
			if (conexion.getAutoCommit()) {
				conexion.setAutoCommit(false);
			}
//			ClienteDTO clienteNuevo = new ClienteDTO();
//			int ID = Integer.parseInt(infoCliente[0]);
//			clienteNuevo.setIdCliente(ID);
//			clienteNuevo.setNombre(infoCliente[1]);
//			clienteNuevo.setApellido(infoCliente[2]);
//			clienteNuevo.setTelefono(infoCliente[3]);
//			clienteNuevo.setCalle(infoCliente[4]);
//			clienteNuevo.setNumExt(infoCliente[5]);
//			clienteNuevo.setNumInt(infoCliente[6]);
//			clienteNuevo.setColonia(infoCliente[7]);
//			clienteNuevo.setDelegacion(infoCliente[8]);
//			clienteNuevo.setCp(infoCliente[9]);
//			clienteNuevo.seteCalle(infoCliente[10]);
//			clienteNuevo.setyCalle(infoCliente[11]);
//			clienteNuevo.setColor(infoCliente[12]);
//			clienteNuevo.setAlergia(infoCliente[13]);
//			clienteNuevo.setIntolerancia(infoCliente[14]);
//			clienteNuevo.setRegimen(infoCliente[15]);
//			clienteNuevo.setEdad(infoCliente[16]);
//			clienteNuevo.setCumple(infoCliente[17]);
//			clienteNuevo.setPreferencia(infoCliente[18]);
//			clienteNuevo.setInstagram(infoCliente[19]);
//			clienteNuevo.setFb(infoCliente[20]);
//			clienteNuevo.setCorreo(infoCliente[21]);
//			clienteNuevo.setNotas(infoCliente[22]);

			ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conexion);
			clienteDao.update(cliente);

			conexion.commit();
			System.out.println("Se ha hecho commit de la transaccion");

			Conexion.close(conexion);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			System.out.println("Entramos al rollback");
			try {
				conexion.rollback();
			} catch (SQLException ex1) {
				ex1.printStackTrace(System.out);
			}
		}
	}

	public void limpiarTextF() {
		textfid.setText("");
		textfNombre.setText("");
		textfNombre.setBackground(Color.white);
		textfApellido.setText("");
		textfApellido.setBackground(Color.white);
		textfTel.setText("");
		textfTel.setBackground(Color.white);
		textfCalle.setText("");
		textfNumExt.setText("");
		textfNumInt.setText("");
		textfColonia.setText("");
		textfDel.setText("");
		textfCP.setText("");
		textfECalle.setText("");
		textfYCalle.setText("");
		textfColor.setText("");
		textfAlergia.setText("");
		textfIntolerancia.setText("");
		rbNinguno.setSelected(true);
		textfEdad.setText("");
		dia.setSelectedIndex(0);
		mes.setSelectedIndex(0);
		anio.setSelectedIndex(0);
		textfPreferencia.setText("");
		textfInstagram.setText("");
		textfFb.setText("");
		textfCorreo.setText("");
		taNotas.setText("");
		
	}

	public void deleteConexion(String infoCliente) {
		Connection conexion = null;
		List<ClienteDTO> clientes = null;
		try {
			conexion = Conexion.getConnection();
			if (conexion.getAutoCommit()) {
				conexion.setAutoCommit(false);
			}
			ClienteDTO clienteNuevo = new ClienteDTO();
			int id = Integer.parseInt(infoCliente);
			clienteNuevo.setIdCliente(id);

			ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conexion);
			clienteDao.delete(clienteNuevo);

			conexion.commit();
			System.out.println("Se ha hecho commit de la transaccion");

			Conexion.close(conexion);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			System.out.println("Entramos al rollback");
			try {
				conexion.rollback();
			} catch (SQLException ex1) {
				ex1.printStackTrace(System.out);
			}
		}
	}

	public void obtenerFiltro() {

		rowSorter = new TableRowSorter<>(modelo);
		table.setRowSorter(rowSorter);

		filtro.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				String text = filtro.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			public void removeUpdate(DocumentEvent e) {
				String text = filtro.getText();
				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}

			}

			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});
	}

	
	public void pasarTablaTextF() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
//		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			@Override
//			public void valueChanged(ListSelectionEvent event) {
				if (me.getClickCount() == 1 && table.isEnabled()) { // to detect double click events
//	            	boton2.setEnabled(false);
					
					int row = table.getSelectedRow(); // select a row
					
					LocalDate dt = (LocalDate) table.getValueAt(row, 17);
					LocalDate now = LocalDate.now();
					

					textfid.setText(table.getValueAt(row, 0).toString());
					textfNombre.setText((String) table.getValueAt(row, 1));
					textfApellido.setText((String) table.getValueAt(row, 2));
					textfTel.setText((String) table.getValueAt(row, 3));
					textfCalle.setText((String) table.getValueAt(row, 4));
					textfNumExt.setText((String) table.getValueAt(row, 5));
					textfNumInt.setText((String) table.getValueAt(row, 6));
					textfColonia.setText((String) table.getValueAt(row, 7));
					textfDel.setText((String) table.getValueAt(row, 8));
					textfCP.setText((String) table.getValueAt(row, 9));
					textfECalle.setText((String) table.getValueAt(row, 10));
					textfYCalle.setText((String) table.getValueAt(row, 11));
					textfColor.setText((String) table.getValueAt(row, 12));
					textfAlergia.setText((String) table.getValueAt(row, 13));
					textfIntolerancia.setText((String) table.getValueAt(row, 14));
					if (((String) table.getValueAt(row, 15)).equals("VEGETARIANO")) {
						rbVegetariano.setSelected(true);
					} else if (((String) table.getValueAt(row, 15)).equals("VEGANO")) {
						rbVegano.setSelected(true);
					} else if (((String) table.getValueAt(row, 15)).equals(" ")) {
						rbNinguno.setSelected(true);
					}
					textfEdad.setText((String) table.getValueAt(row, 16));
					if(dt==null) {
						dia.setSelectedIndex(0);
						mes.setSelectedIndex(0);
						anio.setSelectedIndex(0);
					}else {
						dia.setSelectedIndex(dt.getDayOfMonth());
						mes.setSelectedIndex(dt.getMonthValue());
						anio.setSelectedIndex(now.getYear()-dt.getYear()+1);
						
					}
					
//					textfCumple.setText((String) table.getValueAt(row, 17));
					textfPreferencia.setText((String) table.getValueAt(row, 18));
					textfInstagram.setText((String) table.getValueAt(row, 19));
					textfFb.setText((String) table.getValueAt(row, 20));
					textfCorreo.setText((String) table.getValueAt(row, 21));
					taNotas.setText((String) table.getValueAt(row, 22));

					habilitarRowSelectedBotones();
//	              JOptionPane.showMessageDialog(null, table.getValueAt(row, column)); // get the value of a row and column.
				}
			}

			
			
		});
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// select
		if (e.getSource() == bGuardar) {
			
			if(textfNombre.getText().equals("")||textfTel.getText().equals("")) {
				campoVacio();
				habilitarCampos();
				deshabilitarBotones();
				
			}else {
				textfNombre.setBackground(Color.white);
//				textfApellido.setBackground(Color.white);
				textfTel.setBackground(Color.white);
				ClienteDTO cliente =generarClienteDesdeCampos();
				if(textfid.getText().equals("")) {
					insertConexion(cliente);
					
				}
				else {
					updateConexion(cliente);
					
				}
				
				List<ClienteDTO> clientes = selectConexion();
				ingresarDatosTabla(clientes);
				
				rowSorter = new TableRowSorter<>(modelo);
				table.setRowSorter(rowSorter);
//			limpiarTextF();
				
				habilitarDefaultBotones();
				deshabilitarCampos();
			}
			

			
			
		}

//		if(boton.getName().equals("Agregar")) insert
		if (e.getSource() == bNuevo) {
			
			limpiarTextF();
			habilitarCampos();
			deshabilitarBotones();
			
			textfNombre.requestFocus();
			
//			List<ClienteDTO> clientes = selectConexion();
//			ingresarDatosTabla(clientes);
//			rowSorter = new TableRowSorter<>(modelo);
//			table.setRowSorter(rowSorter);
			
			
			
//			generarCampos();
			

//			System.out.println("texto"+ tabli.textFields[0]);
//			insertConexion(textFields);

//			limpiarTextF();
		}

		// update
		if (e.getSource() == bModificar) {
			
			habilitarCampos();
			deshabilitarBotones();
			
			
			

//			generarCampos();
//
//			updateConexion(textFields);
//			List<ClienteDTO> clientes = selectConexion();
//			ingresarDatosTabla(clientes);
//			rowSorter = new TableRowSorter<>(modelo);
//			table.setRowSorter(rowSorter);
//			limpiarTextF();
		}

		// delete
		if (e.getSource() == bEliminar) {
			
			int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?","Advertencia",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(dialogResult == JOptionPane.YES_OPTION){
				deleteConexion(textfid.getText());
				List<ClienteDTO> clientes = selectConexion();
				ingresarDatosTabla(clientes);
				rowSorter = new TableRowSorter<>(modelo);
				table.setRowSorter(rowSorter);
			  
			}
			
			habilitarDefaultBotones();
			limpiarTextF();

		}
		
		//cancelar
		if(e.getSource()== bCancelar) {
//			limpiarTextF();
			deshabilitarCampos();
			habilitarDefaultBotones();
			
		}
		
		
	}


	public ClienteDTO generarClienteDesdeCampos() {
		ClienteDTO cliente = new ClienteDTO();
		if(!textfid.getText().equals("")) {
			cliente.setIdCliente(Integer.parseInt(textfid.getText()));
		}
		cliente.setNombre(textfNombre.getText());
		cliente.setApellido(textfApellido.getText());
		cliente.setTelefono(textfTel.getText());
		cliente.setCalle(textfCalle.getText());
		cliente.setNumExt(textfNumExt.getText());
		cliente.setNumInt(textfNumInt.getText());
		cliente.setColonia(textfColonia.getText());
		cliente.setDelegacion(textfDel.getText());
		cliente.setCp(textfCP.getText());
		cliente.seteCalle(textfECalle.getText());
		cliente.setyCalle(textfYCalle.getText());
		cliente.setColor(textfColor.getText());
		cliente.setAlergia(textfAlergia.getText());
		cliente.setIntolerancia(textfIntolerancia.getText());
		if (rbVegetariano.isSelected()) {
			cliente.setRegimen(rbVegetariano.getText());
//			textFields[15] = rbVegetariano.getText();
		} else if (rbVegano.isSelected()) {
			cliente.setRegimen(rbVegano.getText());
//			textFields[15] = rbVegano.getText();
		} else if (rbNinguno.isSelected()) {
			cliente.setRegimen(rbNinguno.getText());
//			textFields[15] = rbNinguno.getText();
		}
		
		cliente.setCumple(LocalDate.of(ldA, ldM, ldD));
		cliente.setPreferencia(textfPreferencia.getText());
		cliente.setInstagram(textfInstagram.getText());
		cliente.setFb(textfFb.getText());
		cliente.setCorreo(textfCorreo.getText());
		cliente.setNotas(taNotas.getText());
		
//		textFields[0] = textfid.getText();
//		textFields[1] = textfNombre.getText();
//		textFields[2] = textfApellido.getText();
//		textFields[3] = textfTel.getText();
//		textFields[4] = textfCalle.getText();
//		textFields[5] = textfNumExt.getText();
//		textFields[6] = textfNumInt.getText();
//		textFields[7] = textfColonia.getText();
//		textFields[8] = textfDel.getText();
//		textFields[9] = textfCP.getText();
//		textFields[10] = textfECalle.getText();
//		textFields[11] = textfYCalle.getText();
//		textFields[12] = textfColor.getText();
//		textFields[13] = textfAlergia.getText();
//		textFields[14] = textfIntolerancia.getText();
//		if (rbVegetariano.isSelected()) {
//			textFields[15] = rbVegetariano.getText();
//		} else if (rbVegano.isSelected()) {
//			textFields[15] = rbVegano.getText();
//		} else if (rbNinguno.isSelected()) {
//			textFields[15] = rbNinguno.getText();
//		}
//		textFields[16] = textfEdad.getText();
//		textFields[17] = textfCumple.getText();
//		textFields[18] = textfPreferencia.getText();
//		textFields[19] = textfInstagram.getText();
//		textFields[20] = textfFb.getText();
//		textFields[21] = textfCorreo.getText();
//		textFields[22] = taNotas.getText();
	
		return cliente;
	}
	
	//nuevo habilitado

	//habilita Nuevo
	public void habilitarDefaultBotones() {
		bNuevo.setEnabled(true);
		bGuardar.setEnabled(false);
		bModificar.setEnabled(false);
		bEliminar.setEnabled(false);
		bCancelar.setEnabled(false);
	}
	

	//habilita Guardar
	public void deshabilitarBotones() {
		bNuevo.setEnabled(false);
		bGuardar.setEnabled(true);
		bModificar.setEnabled(false);
		bEliminar.setEnabled(false);
		bCancelar.setEnabled(true);
	}
	

	//habilita nuevo,modificiar,eliminar
	public void habilitarRowSelectedBotones() {
		bNuevo.setEnabled(true);
		bGuardar.setEnabled(false);
		bModificar.setEnabled(true);
		bEliminar.setEnabled(true);
		bCancelar.setEnabled(false);
	}
	
	
	//habilita JTextField,JTextArea,JRadioButton
	public void habilitarCampos() {
		textfNombre.setEditable(true);
		textfApellido.setEditable(true);
		textfTel.setEditable(true);
		textfCalle.setEditable(true);
		textfNumExt.setEditable(true);
		textfNumInt.setEditable(true);
		textfColonia.setEditable(true);
		textfDel.setEditable(true);
		textfCP.setEditable(true);
		textfECalle.setEditable(true);
		textfYCalle.setEditable(true);
		textfColor.setEditable(true);
		textfAlergia.setEditable(true);
		textfIntolerancia.setEditable(true);
		rbVegetariano.setEnabled(true);
		rbVegano.setEnabled(true);
		rbNinguno.setEnabled(true);
		textfEdad.setEnabled(true);
		dia.setEnabled(true);
		mes.setEnabled(true);
		anio.setEnabled(true);
		textfPreferencia.setEditable(true);
		textfInstagram.setEditable(true);
		textfFb.setEditable(true);
		textfCorreo.setEditable(true);
		taNotas.setEditable(true);
		table.setEnabled(false);
	}
	
	
	//deshabilita JTextField,JTextArea,JRadioButton
	public void deshabilitarCampos() {
		textfNombre.setEditable(false);
		textfApellido.setEditable(false);
		textfTel.setEditable(false);
		textfCalle.setEditable(false);
		textfNumExt.setEditable(false);
		textfNumInt.setEditable(false);
		textfColonia.setEditable(false);
		textfDel.setEditable(false);
		textfCP.setEditable(false);
		textfECalle.setEditable(false);
		textfYCalle.setEditable(false);
		textfColor.setEditable(false);
		textfAlergia.setEditable(false);
		textfIntolerancia.setEditable(false);
		rbVegetariano.setEnabled(false);
		rbVegano.setEnabled(false);
		rbNinguno.setEnabled(false);
		textfEdad.setEnabled(false);
		dia.setEnabled(false);
		mes.setEnabled(false);
		anio.setEnabled(false);
		textfPreferencia.setEditable(false);
		textfInstagram.setEditable(false);
		textfFb.setEditable(false);
		textfCorreo.setEditable(false);
		taNotas.setEditable(false);
		table.setEnabled(true);
	}
	
	
	public void infoBox(String infoMessage, String titleBar){
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }
	
	
	public void campoVacio() {
		if(textfNombre.getText().equals("")) {
			textfNombre.setBackground(Color.pink);	
			
		}else {
			textfNombre.setBackground(Color.white);
		}
		
//		if(textfApellido.getText().equals("")) {
//			textfApellido.setBackground(Color.pink);
//		}else {
//			textfApellido.setBackground(Color.white);
//			
//		}
		if(textfTel.getText().equals("")) {
			textfTel.setBackground(Color.pink);	
			
		}else {
			textfTel.setBackground(Color.white);
		}
		
		
		if(textfNombre.getText().equals("")||textfTel.getText().equals("")) {
			infoBox("Llene los campos faltantes"," ");
			
		}
	}
	
	private void pasarSeleccionCumple() {
		Object item;
		if(dia.getSelectedItem().equals("Día")){
			ldD=1;
		}else {
			ldD = Integer.parseInt((String)dia.getSelectedItem());
		}
		
		if(mes.getSelectedItem().equals("Mes")){
			ldM=1;
		}else {
			item = mes.getSelectedItem();
			ldM = Integer.parseInt(((ComboItem)item).getValue());
		}
		
		if(anio.getSelectedItem().equals("Año")){
			ldA=2020;
		}else {
			ldA = Integer.parseInt((String)anio.getSelectedItem());
			textfEdad.setText(Edad.calcularEdad(LocalDate.of(ldA,ldM,ldD)));
//			textfEdad.setText(yearNow.getYear()-ldA);
		}
	}
}

class ComboItem{
    private String key;
    private String value;

    public ComboItem(String key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString(){
        return key;
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }
}
