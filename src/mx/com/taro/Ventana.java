package mx.com.taro;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import utilidades.ModeloTabla;

public class Ventana {
	private JPanel contP;
	private JScrollPane spt;
	private JTable tablaPersonas;
	ArrayList <ClienteDTO> listaPersonas;
	
	ModeloTabla modelo;
	private int filasTabla;
	private int columnasTabla;
	
	public Ventana() {
		iniciarComponentes();
		
		construirTabla();
		
	}
	
	
	public JPanel getContP() {
		return contP;
	}

	private void iniciarComponentes() {
		contP = new JPanel();
		contP.setBorder(new EmptyBorder(5,5,5,5));
		contP.setLayout(new BorderLayout(0,0));

//		JLabel lbltPersonas = new JLabel("Tabla Personas");
		spt = new JScrollPane();
		contP.add(spt);
		
		tablaPersonas = new JTable();
		tablaPersonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
	}
	
	private void construirTabla() {
		listaPersonas=consultarListaPersonas();
		
		//reverenda mama
		ArrayList tl = new ArrayList();
		tl.add("Nombre");
		tl.add("Apellido");
		tl.add("Telefono");
		tl.add("holahola");
		
		
		String titulos[]=new String[tl.size()];
		for(int i =0; i<titulos.length;i++) {
			titulos[i]=(String) tl.get(i);
		}
		
		Object[][]data=obtenerMatrizDatos(tl);
		construirTabla(titulos,data);
		
	}
	
	private ArrayList consultarListaPersonas() {
		ArrayList lista = new ArrayList();
		
		lista.add(new ClienteDTO("hola","12344","1412"));
		lista.add(new ClienteDTO("hobablaa","12344","5475"));
		lista.add(new ClienteDTO("hola","12asasd344","234"));
		lista.add(new ClienteDTO("hoasdla","12344","234"));
		lista.add(new ClienteDTO("hola","12344","1412"));
		lista.add(new ClienteDTO("hobablaa","12344","5475"));
		lista.add(new ClienteDTO("hola","12asasd344","234"));
		lista.add(new ClienteDTO("hoasdla","12344","234"));
		
		return lista;
	}
	
	private Object[][] obtenerMatrizDatos(ArrayList titulosList){
		String info[][]= new String[listaPersonas.size()][titulosList.size()];

		for(int x=0;x<info.length;x++) {
			info[x][0]=listaPersonas.get(x).getNombre();
			info[x][1]=listaPersonas.get(x).getApellido();
			info[x][2]=listaPersonas.get(x).getTelefono();
	}
		return info;
	
	}
	
	private void construirTabla(String[]titulos,Object[][]data) {
		modelo = new ModeloTabla(data,titulos);
		//agregar tabla en tablaPersonas
		tablaPersonas.setModel(modelo);
		
		//Se puede hacer de esta manera siempre y cuando se instancia en este momento
		//tablaPersonas = new JTable(data,titulos);
		
		filasTabla=tablaPersonas.getRowCount();
		columnasTabla=tablaPersonas.getColumnCount();
		
		//si le pongo true, puedo cambiar el orden
		tablaPersonas.getTableHeader().setReorderingAllowed(false);
		tablaPersonas.setRowHeight(25);
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(130);
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(110);
		
		//se agrega la tabla dentro de un viewport (quien sabe que sea)
		spt.setViewportView(tablaPersonas);
		//tambien se puede agregar de esta manera 
		//este se agrega directamente al spt
//		spt.add(tablaPersonas);
		
		//si la tablaPersonas se mete en un Panel, ya no se veria el titulo, y tamopco se podria ver toda la tabla
		// contP.add(tablaPersonas);
		
	}
}
