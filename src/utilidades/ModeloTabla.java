package utilidades;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//contruir una tabla 
// una clase de swing que generes tablas con los elementos por separado
public class ModeloTabla extends DefaultTableModel{
	String[] titulos;
	Object[][]datos;
	
//	JTableHeader header = table.getTableHeader();
//	header.setFont(header.getFont().deriveFont(30f));
	
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
