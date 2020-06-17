package utilidades;

import javax.swing.table.DefaultTableModel;

//contruir una tabla 
// una clase de swing que generes tablas con los elementos por separado
public class ModeloTabla extends DefaultTableModel{
	String[] titulos;
	Object[][]datos;
	
	public ModeloTabla(Object[][]datos,String[]titulos) {
		super();
		this.titulos=titulos;
		this.datos=datos;
		setDataVector(datos,titulos);
	}
	
	
	public ModeloTabla() {
		
	}
	
	public boolean isCellEditable(int row, int column)
	{
		return false;//This causes all cells to be not editable
	};

}
