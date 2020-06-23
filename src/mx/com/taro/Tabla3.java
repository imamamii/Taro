package mx.com.taro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import utilidades.Conexion;
import utilidades.ModeloTabla;

public class Tabla3 extends JFrame implements ActionListener{
	
	
	JTextField textfid,textfNombre,textfApellido,textfTel,filtro;
	String []textFields;
	JButton boton1 ,boton2 ,boton3, boton4;
	JTable table;
	private TableRowSorter<TableModel> rowSorter;
	ModeloTabla modelo;
	
	public Tabla3(){
		crearComponentes();
//		iniciarTabla(ingresarDatosTabla(selectConexion()));
		iniciarTabla();
		ingresarDatosTabla(selectConexion());
		textFields = new String[4];
		pasarTablaTextF();

		obtenerFiltro();
			
	}
	
	public void crearComponentes() {
		textfid= new JTextField();
//		textf1.setBounds(x, y, width, height);
		textfid.setBounds(120, 5, 150, 20);
		add(textfid);
		textfid.setEnabled(false);
		textfid.setVisible(false);
		
		textfNombre= new JTextField();
		textfNombre.setBounds(120, 30, 150, 20);
		add(textfNombre);
		
		textfApellido= new JTextField();
		textfApellido.setBounds(120, 60, 150, 20);
		add(textfApellido);
		
		textfTel= new JTextField();
		textfTel.setBounds(120, 90, 150, 20);
		add(textfTel);
		
		filtro= new JTextField();
		filtro.setBounds(300, 60, 150, 20);
		add(filtro);
		filtro.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				rowSorter= new TableRowSorter<>(modelo);
				table.setRowSorter(rowSorter);
				  String text = filtro.getText();
				  if (text.trim().length() == 0) {
				     rowSorter.setRowFilter(null);
				  } else {
				     rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				  }
				
			}
			
		});
		
		
		
//		Integer a = Integer.parseInt(textfid.getText());
		
		
		boton1= new JButton("Cargar");
//		boton1.setName("Cargar");
		boton1.setBounds(5, 20, 100, 30);
		add(boton1);
//		botonaction = new Botones();
		boton1.addActionListener(this);
//		
		boton2= new JButton("Agregar");
		boton2.setBounds(5, 40, 100, 30);
		add(boton2);
		boton2.addActionListener(this);
		
		boton3= new JButton("Update");
		boton3.setBounds(5, 60, 100, 30);
		add(boton3);
		boton3.addActionListener(this);
		
		boton4= new JButton("Delete");
		boton4.setBounds(5, 80, 100, 30);
		add(boton4);
		boton4.addActionListener(this);
		
		
	}
	
	public List<ClienteDTO> selectConexion() {
		Connection conexion = null;
		List<ClienteDTO> clientes =null;
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
	
	public void insertConexion(String[]textFields) {
		
		Connection conexion = null;
		List<ClienteDTO> clientes =null;
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
	
	public void ingresarDatosTabla(List<ClienteDTO> clientes) {
		
		String[] header = {"Nombre","Apellido","Telefono"};
		
		Object data[][]= new Object[clientes.size()][header.length];
		
		for(int x=0;x<data.length;x++) {
			data[x][0]=clientes.get(x).getIdCliente(); 
			data[x][1]=clientes.get(x).getNombre(); 
			data[x][2]=clientes.get(x).getApellido(); 
			data[x][3]=clientes.get(x).getTelefono(); 
			
		}
		
		modelo = new ModeloTabla(data,header);
		
		table.setModel(modelo);
		
		
		
	}
	
	public void iniciarTabla() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		JPanel jp = new JPanel();
		jp.setBounds(5, 150, 300, 100);
		jp.setBorder(new EmptyBorder(5,5,5,5));
		jp.setLayout(new BorderLayout(0,0));
		
		JScrollPane pane = new JScrollPane();
		jp.add(pane);
		
		
		pane.setViewportView(table);
		
		add(jp);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		JButton boton=(JButton)e.getSource();
		if(e.getSource()==boton1) {
			
			
			List<ClienteDTO> clientes =selectConexion();
			ingresarDatosTabla(clientes);
			limpiar();
		}
		
//		if(boton.getName().equals("Agregar")) {
			if(e.getSource()==boton2) {
				
				textFields[0]=textfid.getText();
				textFields[1]=textfNombre.getText();
				textFields[2]=textfApellido.getText();
				textFields[3]=textfTel.getText();
				
				
//			System.out.println("texto"+ tabli.textFields[0]);
			insertConexion(textFields);
			
			
			List<ClienteDTO> clientes =selectConexion();
			ingresarDatosTabla(clientes);
			rowSorter= new TableRowSorter<>(modelo);
			table.setRowSorter(rowSorter);	
			limpiar();
		}
			
			if(e.getSource()==boton3) {
				
				
				textFields[0]=textfid.getText();
				textFields[1]=textfNombre.getText();
				textFields[2]=textfApellido.getText();
				textFields[3]=textfTel.getText();
				
				updateConexion(textFields);
				List<ClienteDTO> clientes =selectConexion();
				ingresarDatosTabla(clientes);
				rowSorter= new TableRowSorter<>(modelo);
				table.setRowSorter(rowSorter);
				limpiar();
			}
			
			if(e.getSource()==boton4) {
				deleteConexion(textfid.getText());
				List<ClienteDTO> clientes =selectConexion();
				ingresarDatosTabla(clientes);
				rowSorter= new TableRowSorter<>(modelo);
				table.setRowSorter(rowSorter);
				limpiar();
				
				
			}
			
			
		
	}

	public void pasarTablaTextF() {
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 1) {     // to detect double click events
	            	boton2.setEnabled(false);
	               int row = table.getSelectedRow(); // select a row
	               
	               textfid.setText(table.getValueAt(row, 0).toString());
	               textfNombre.setText((String)table.getValueAt(row, 1));
	               textfApellido.setText((String)table.getValueAt(row, 2));
	               textfTel.setText((String)table.getValueAt(row, 3));
	               
//	              JOptionPane.showMessageDialog(null, table.getValueAt(row, column)); // get the value of a row and column.
	            }
	         }
	      });
	}

	public void updateConexion(String[]infoCliente) {
		Connection conexion = null;
		List<ClienteDTO> clientes =null;
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
	
	public void deleteConexion(String infoCliente) {
		Connection conexion = null;
		List<ClienteDTO> clientes =null;
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
	
	public void limpiar() {
		textfid.setText("");
		textfNombre.setText("");
		textfApellido.setText("");
		textfTel.setText("");
	}
		
	public void obtenerFiltro() {
		
		rowSorter= new TableRowSorter<>(modelo);
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
//		List<ClienteDTO> clientes =selectConexion();
//		ingresarDatosTabla(clientes);
		
}
}

	
	

	

	
	
	
	


