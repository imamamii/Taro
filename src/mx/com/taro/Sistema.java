package mx.com.taro;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import utilidades.Conexion;
import utilidades.ModeloTabla;

public class Sistema extends JFrame implements ActionListener {

	JTextField textfid, textfNombre, textfApellido, textfTel, textfCalle, textfNumInt, textfNumExt, textfColonia,
			textfDel, textfCP, textfECalle, textfYCalle, textfColor, textfAlergia, textfIntolerancia, textfCumple,
			textfEdad, textfPreferencia, textfInstagram, textfFb, textfCorreo, filtro;

	JLabel labelTitulo, labelNombre, labelApellido, labelTel, labelDireccion, labelCalle, labelNumInt, labelNumExt,
			labelColonia, labelDel, labelCP, labelECalle, labelYCalle, labelColor, labelAlergia, labelIntolerancia,
			labelInfoExtra, labelCumple, labelEdad, labelPreferencia, labelInstagram, labelFb, labelCorreo, labelNotas;

	public JRadioButton rbVegetariano, rbVegano, rbNinguno;

	JButton bGuardar, bModificar, bNuevo, bEliminar, bCancelar;

	JTextArea taNotas;
	JScrollPane spNotas;

	String[] textFields;
//	JButton boton1 ,boton2 ,boton3, boton4;
	JTable table;
	private TableRowSorter<TableModel> rowSorter;
	ModeloTabla modelo;

	public Sistema() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		add(labelTitulo);

		textfid = new JTextField();
		textfid.setBounds(200, 5, 30, 25);
		textfid.setEnabled(false);
		add(textfid);

		labelNombre = new JLabel("NOMBRE");
		labelNombre.setBounds(50, 60, 80, 30);
		labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelNombre);

		textfNombre = new JTextField();
		textfNombre.setBounds(110, 60, 300, 25);
		add(textfNombre);

		labelApellido = new JLabel("APELLIDO");
		labelApellido.setBounds(45, 85, 80, 30);
		labelApellido.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelApellido);

		textfApellido = new JTextField();
		textfApellido.setBounds(110, 85, 300, 25);
		add(textfApellido);

		labelTel = new JLabel("TEL");
		labelTel.setBounds(85, 110, 80, 30);
		labelTel.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelTel);

		textfTel = new JTextField();
		textfTel.setBounds(110, 110, 300, 25);
		add(textfTel);

//		label = new JLabel("");
//		label.setBounds(5, 120, 80, 30);
//		label.setFont(new Font("Arial", Font.PLAIN, 14));
//		add(label);

		labelDireccion = new JLabel("DIRECCION");
		labelDireccion.setBounds(5, 140, 140, 30);
		labelDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		add(labelDireccion);

		labelCalle = new JLabel("CALLE");
		labelCalle.setBounds(68, 160, 80, 30);
		labelCalle.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelCalle);

		textfCalle = new JTextField();
		textfCalle.setBounds(110, 160, 300, 25);
		add(textfCalle);

		labelNumExt = new JLabel("N° EXT.");
		labelNumExt.setBounds(61, 185, 80, 30);
		labelNumExt.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelNumExt);

		textfNumExt = new JTextField();
		textfNumExt.setBounds(110, 185, 115, 25);
		add(textfNumExt);

		labelNumInt = new JLabel("N° INT.");
		labelNumInt.setBounds(245, 185, 80, 30);
		labelNumInt.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelNumInt);

		textfNumInt = new JTextField();
		textfNumInt.setBounds(295, 185, 115, 25);
		add(textfNumInt);

		labelColonia = new JLabel("COLONIA");
		labelColonia.setBounds(48, 210, 100, 30);
		labelColonia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelColonia);

		textfColonia = new JTextField();
		textfColonia.setBounds(110, 210, 300, 25);
		add(textfColonia);

		labelDel = new JLabel("DELEGACION");
		labelDel.setBounds(21, 235, 100, 30);
		labelDel.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelDel);

		textfDel = new JTextField();
		textfDel.setBounds(110, 235, 300, 25);
		add(textfDel);

		labelCP = new JLabel("CP");
		labelCP.setBounds(93, 260, 50, 30);
		labelCP.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelCP);

		textfCP = new JTextField();
		textfCP.setBounds(110, 260, 300, 25);
		add(textfCP);

		labelECalle = new JLabel("ENTRE CALLE");
		labelECalle.setBounds(17, 285, 100, 30);
		labelECalle.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelECalle);

		textfECalle = new JTextField();
		textfECalle.setBounds(110, 285, 300, 25);
		add(textfECalle);

		labelYCalle = new JLabel("Y CALLE");
		labelYCalle.setBounds(55, 310, 80, 30);
		labelYCalle.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelYCalle);

		textfYCalle = new JTextField();
		textfYCalle.setBounds(110, 310, 300, 25);
		add(textfYCalle);

		labelColor = new JLabel("COLOR");
		labelColor.setBounds(62, 335, 80, 30);
		labelColor.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelColor);

		textfColor = new JTextField();
		textfColor.setBounds(110, 335, 300, 25);
		add(textfColor);

		labelAlergia = new JLabel("ALERGIA");
		labelAlergia.setBounds(52, 365, 80, 30);
		labelAlergia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelAlergia);

		textfAlergia = new JTextField();
		textfAlergia.setBounds(110, 365, 300, 25);
		add(textfAlergia);

		labelIntolerancia = new JLabel("INTOLERANCIA");
		labelIntolerancia.setBounds(9, 390, 120, 30);
		labelIntolerancia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelIntolerancia);

		textfIntolerancia = new JTextField();
		textfIntolerancia.setBounds(110, 390, 300, 25);
		add(textfIntolerancia);

		rbVegetariano = new JRadioButton("VEGETARIANO");
		rbVegetariano.setBounds(110, 415, 130, 30);
		rbVegano = new JRadioButton("VEGANO");
		rbVegano.setBounds(228, 415, 100, 30);
		rbNinguno = new JRadioButton("NINGUNO", true);
		rbNinguno.setBounds(315, 415, 100, 30);
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

		labelInfoExtra = new JLabel("INFORMACION EXTRA");
		labelInfoExtra.setBounds(5, 445, 180, 30);
		labelInfoExtra.setFont(new Font("Arial", Font.PLAIN, 16));
		add(labelInfoExtra);

		labelCumple = new JLabel("CUMPLEAÑOS");
		labelCumple.setBounds(10, 470, 120, 30);
		labelCumple.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelCumple);

		textfCumple = new JTextField();
		textfCumple.setBounds(110, 470, 300, 25);
		add(textfCumple);

		labelEdad = new JLabel("EDAD");
		labelEdad.setBounds(5, 495, 80, 30);
		labelEdad.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelEdad);

		textfEdad = new JTextField();
		textfEdad.setBounds(110, 495, 300, 25);
		add(textfEdad);

		labelPreferencia = new JLabel("PREFERENCIA");
		labelPreferencia.setBounds(5, 520, 130, 30);
		labelPreferencia.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelPreferencia);

		textfPreferencia = new JTextField();
		textfPreferencia.setBounds(110, 520, 300, 25);
		add(textfPreferencia);

		labelInstagram = new JLabel("INSTAGRAM");
		labelInstagram.setBounds(5, 545, 120, 30);
		labelInstagram.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelInstagram);

		textfInstagram = new JTextField();
		textfInstagram.setBounds(110, 545, 300, 25);
		add(textfInstagram);

		labelFb = new JLabel("FACEBOOK");
		labelFb.setBounds(5, 570, 100, 30);
		labelFb.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelFb);

		textfFb = new JTextField();
		textfFb.setBounds(110, 570, 300, 25);
		add(textfFb);

		labelCorreo = new JLabel("CORREO");
		labelCorreo.setBounds(5, 595, 80, 30);
		labelCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelCorreo);

		textfCorreo = new JTextField();
		textfCorreo.setBounds(110, 595, 300, 25);
		add(textfCorreo);

		labelNotas = new JLabel("NOTAS");
		labelNotas.setBounds(5, 620, 80, 30);
		labelNotas.setFont(new Font("Arial", Font.PLAIN, 14));
		add(labelNotas);

		taNotas = new JTextArea();
		spNotas = new JScrollPane(taNotas);
		spNotas.setBounds(113, 623, 294, 60);
		add(spNotas);

		bGuardar = new JButton();
		bGuardar = new JButton("GUARDAR");
		bGuardar.setBounds(15, 690, 75, 75);
		add(bGuardar);
//		bGuardar.setVerticalAlignment(SwingConstants.BOTTOM);
		bGuardar.addActionListener(this);

		bModificar = new JButton();
		bModificar = new JButton("MODIFICAR");
		bModificar.setBounds(95, 690, 75, 75);
		add(bModificar);
		bModificar.addActionListener(this);

		bNuevo = new JButton();
		bNuevo = new JButton("NUEVO");
		bNuevo.setBounds(175, 690, 75, 75);
		add(bNuevo);
		bNuevo.addActionListener(this);

		bEliminar = new JButton();
		bEliminar = new JButton("ELIMINAR");
		bEliminar.setBounds(255, 690, 75, 75);
		add(bEliminar);
		bEliminar.addActionListener(this);
//		
		bCancelar = new JButton();
		bCancelar = new JButton("CANCELAR");
		bCancelar.setBounds(335, 690, 75, 75);
		add(bCancelar);
		bCancelar.addActionListener(this);

		filtro = new JTextField();
		filtro.setBounds(645, 30, 410, 35);
		add(filtro);

	}

	public void iniciarTabla() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setLayout(null);

		JPanel jp = new JPanel();
		jp.setBounds(430, 72, 840, 660);
		jp.setBorder(new EmptyBorder(5, 5, 5, 5));
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

	public void insertConexion(String[] textFields) {

		Connection conexion = null;
//		List<ClienteDTO> clientes = null;
		try {
			conexion = Conexion.getConnection();
			if (conexion.getAutoCommit()) {
				conexion.setAutoCommit(false);
			}
			ClienteDTO clienteNuevo = new ClienteDTO();
//			int ID = Integer.parseInt(textFields[0]);
//			clienteNuevo.setIdCliente(ID);
			clienteNuevo.setNombre(textFields[1]);
			clienteNuevo.setApellido(textFields[2]);
			clienteNuevo.setTelefono(textFields[3]);
			clienteNuevo.setCalle(textFields[4]);
			clienteNuevo.setNumExt(textFields[5]);
			clienteNuevo.setNumInt(textFields[6]);
			clienteNuevo.setColonia(textFields[7]);
			clienteNuevo.setDelegacion(textFields[8]);
			clienteNuevo.setCp(textFields[9]);
			clienteNuevo.seteCalle(textFields[10]);
			clienteNuevo.setyCalle(textFields[11]);
			clienteNuevo.setColor(textFields[12]);
			clienteNuevo.setAlergia(textFields[13]);
			clienteNuevo.setIntolerancia(textFields[14]);
			clienteNuevo.setRegimen(textFields[15]);
			clienteNuevo.setEdad(textFields[16]);
			clienteNuevo.setCumple(textFields[17]);
			clienteNuevo.setPreferencia(textFields[18]);
			clienteNuevo.setInstagram(textFields[19]);
			clienteNuevo.setFb(textFields[20]);
			clienteNuevo.setCorreo(textFields[21]);
			clienteNuevo.setNotas(textFields[22]);

			ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conexion);
			clienteDao.insert(clienteNuevo);

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

	public void updateConexion(String[] infoCliente) {
		Connection conexion = null;
//		List<ClienteDTO> clientes = null;
		try {
			conexion = Conexion.getConnection();
			if (conexion.getAutoCommit()) {
				conexion.setAutoCommit(false);
			}
			ClienteDTO clienteNuevo = new ClienteDTO();
			int ID = Integer.parseInt(infoCliente[0]);
			clienteNuevo.setIdCliente(ID);
			clienteNuevo.setNombre(infoCliente[1]);
			clienteNuevo.setApellido(infoCliente[2]);
			clienteNuevo.setTelefono(infoCliente[3]);
			clienteNuevo.setCalle(infoCliente[4]);
			clienteNuevo.setNumExt(infoCliente[5]);
			clienteNuevo.setNumInt(infoCliente[6]);
			clienteNuevo.setColonia(infoCliente[7]);
			clienteNuevo.setDelegacion(infoCliente[8]);
			clienteNuevo.setCp(infoCliente[9]);
			clienteNuevo.seteCalle(infoCliente[10]);
			clienteNuevo.setyCalle(infoCliente[11]);
			clienteNuevo.setColor(infoCliente[12]);
			clienteNuevo.setAlergia(infoCliente[13]);
			clienteNuevo.setIntolerancia(infoCliente[14]);
			clienteNuevo.setRegimen(infoCliente[15]);
			clienteNuevo.setEdad(infoCliente[16]);
			clienteNuevo.setCumple(infoCliente[17]);
			clienteNuevo.setPreferencia(infoCliente[18]);
			clienteNuevo.setInstagram(infoCliente[19]);
			clienteNuevo.setFb(infoCliente[20]);
			clienteNuevo.setCorreo(infoCliente[21]);
			clienteNuevo.setNotas(infoCliente[22]);

			ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conexion);
			clienteDao.update(clienteNuevo);

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
		textfApellido.setText("");
		textfTel.setText("");
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
		textfCumple.setText("");
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
					textfCumple.setText((String) table.getValueAt(row, 17));
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

			generarCampos();
			
			if(textfid.getText().equals("")) {
				insertConexion(textFields);
				
			}
			else {
				updateConexion(textFields);
				
			}
			
			List<ClienteDTO> clientes = selectConexion();
			ingresarDatosTabla(clientes);
			rowSorter = new TableRowSorter<>(modelo);
			table.setRowSorter(rowSorter);
//			limpiarTextF();
			
			habilitarDefaultBotones();
			deshabilitarCampos();
			
			
		}

//		if(boton.getName().equals("Agregar")) insert
		if (e.getSource() == bNuevo) {
			
			limpiarTextF();
			habilitarCampos();
			deshabilitarBotones();
			
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
			
			
			deleteConexion(textfid.getText());
			List<ClienteDTO> clientes = selectConexion();
			ingresarDatosTabla(clientes);
			rowSorter = new TableRowSorter<>(modelo);
			table.setRowSorter(rowSorter);
			habilitarDefaultBotones();
			limpiarTextF();

		}
		
		//cancelar
		if(e.getSource()== bCancelar) {
			limpiarTextF();
			deshabilitarCampos();
			habilitarDefaultBotones();
			
		}
		
		
	}


	public void generarCampos() {
		textFields[0] = textfid.getText();
		textFields[1] = textfNombre.getText();
		textFields[2] = textfApellido.getText();
		textFields[3] = textfTel.getText();
		textFields[4] = textfCalle.getText();
		textFields[5] = textfNumExt.getText();
		textFields[6] = textfNumInt.getText();
		textFields[7] = textfColonia.getText();
		textFields[8] = textfDel.getText();
		textFields[9] = textfCP.getText();
		textFields[10] = textfECalle.getText();
		textFields[11] = textfYCalle.getText();
		textFields[12] = textfColor.getText();
		textFields[13] = textfAlergia.getText();
		textFields[14] = textfIntolerancia.getText();
		if (rbVegetariano.isSelected()) {
			textFields[15] = rbVegetariano.getText();
		} else if (rbVegano.isSelected()) {
			textFields[15] = rbVegano.getText();
		} else if (rbNinguno.isSelected()) {
			textFields[15] = rbNinguno.getText();
		}
		textFields[16] = textfEdad.getText();
		textFields[17] = textfCumple.getText();
		textFields[18] = textfPreferencia.getText();
		textFields[19] = textfInstagram.getText();
		textFields[20] = textfFb.getText();
		textFields[21] = textfCorreo.getText();
		textFields[22] = taNotas.getText();
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
	
	//habilita guardar

	//habilita Guardar
	public void deshabilitarBotones() {
		bNuevo.setEnabled(false);
		bGuardar.setEnabled(true);
		bModificar.setEnabled(false);
		bEliminar.setEnabled(false);
		bCancelar.setEnabled(true);
	}
	
	//habilitar nuevo,modificar,eliminar

	//habilita nuevo,modificiar,eliminar
	public void habilitarRowSelectedBotones() {
		bNuevo.setEnabled(true);
		bGuardar.setEnabled(false);
		bModificar.setEnabled(true);
		bEliminar.setEnabled(true);
		bCancelar.setEnabled(false);
	}
	
	//habilita TextFile
	
	//habilita JTextField,JTextArea,JRadioButton
	public void habilitarCampos() {
		textfNombre.setEnabled(true);
		textfApellido.setEnabled(true);
		textfTel.setEnabled(true);
		textfCalle.setEnabled(true);
		textfNumExt.setEnabled(true);
		textfNumInt.setEnabled(true);
		textfColonia.setEnabled(true);
		textfDel.setEnabled(true);
		textfCP.setEnabled(true);
		textfECalle.setEnabled(true);
		textfYCalle.setEnabled(true);
		textfColor.setEnabled(true);
		textfAlergia.setEnabled(true);
		textfIntolerancia.setEnabled(true);
		rbVegetariano.setEnabled(true);
		rbVegano.setEnabled(true);
		rbNinguno.setEnabled(true);
		textfEdad.setEnabled(true);
		textfCumple.setEnabled(true);
		textfPreferencia.setEnabled(true);
		textfInstagram.setEnabled(true);
		textfFb.setEnabled(true);
		textfCorreo.setEnabled(true);
		taNotas.setEnabled(true);
		table.setEnabled(false);
	}
	
	
	//deshabilita JTextField,JTextArea,JRadioButton
	public void deshabilitarCampos() {
		textfNombre.setEnabled(false);
		textfApellido.setEnabled(false);
		textfTel.setEnabled(false);
		textfCalle.setEnabled(false);
		textfNumExt.setEnabled(false);
		textfNumInt.setEnabled(false);
		textfColonia.setEnabled(false);
		textfDel.setEnabled(false);
		textfCP.setEnabled(false);
		textfECalle.setEnabled(false);
		textfYCalle.setEnabled(false);
		textfColor.setEnabled(false);
		textfAlergia.setEnabled(false);
		textfIntolerancia.setEnabled(false);
		rbVegetariano.setEnabled(false);
		rbVegano.setEnabled(false);
		rbNinguno.setEnabled(false);
		textfEdad.setEnabled(false);
		textfCumple.setEnabled(false);
		textfPreferencia.setEnabled(false);
		textfInstagram.setEnabled(false);
		textfFb.setEnabled(false);
		textfCorreo.setEnabled(false);
		taNotas.setEnabled(false);
		table.setEnabled(true);
	}
	
	
	
	
}
