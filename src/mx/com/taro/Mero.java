package mx.com.taro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import utilidades.Conexion;

public class Mero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		
//		Formulario f1= new Formulario();
//		f1.setBounds(0, 0, 500, 400);
//		f1.setVisible(true);
//		//cuando se ejecute, sin importar las coordenadas, siempre este en el centro
//		f1.setLocationRelativeTo(null);
//		//para que no cambie el tamano
//		f1.setResizable(false);
//		
//		Ventana mi = new Ventana();
//		mi.setVisible(true);
		
		Sistema abc = new Sistema();
		abc.setBounds(0, 0, 1300, 800);
		abc.setLocationRelativeTo(null);
		abc.setVisible(true);
		abc.setResizable(true);
		
//		Ventana2 abc = new Ventana2();
//		abc.setBounds(0, 0, 800, 400);
//		abc.setLocationRelativeTo(null);
//		abc.setVisible(true);
//		abc.setResizable(false);
//		
//		
		 Connection conexion = null;
	        try {
	            conexion = Conexion.getConnection();
	            if (conexion.getAutoCommit()) {
	                conexion.setAutoCommit(false);
	            }

	            ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conexion);
	            
	            List<ClienteDTO> clientes = clienteDao.select();
	            
	            for(ClienteDTO cliente: clientes){
	                System.out.println("Persona DTO:" + cliente);
	            }
	            
	            conexion.commit();
	            System.out.println("Se ha hecho commit de la transaccion");
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

}
