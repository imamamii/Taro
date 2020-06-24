package mx.com.taro;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.text.ParseException;



import utilidades.Conexion;

public class Mero {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
//		calcular();
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
		
		Ejemplo abc = new Ejemplo();
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
//		 Connection conexion = null;
//	        try {
//	            conexion = Conexion.getConnection();
//	            if (conexion.getAutoCommit()) {
//	                conexion.setAutoCommit(false);
//	            }
//
//	            ClienteDaoJDBC clienteDao = new ClienteDaoJDBC(conexion);
//	            
//	            List<ClienteDTO> clientes = clienteDao.select();
//	            
//	            for(ClienteDTO cliente: clientes){
//	                System.out.println("Persona DTO:" + cliente);
//	            }
//	            
//	            conexion.commit();
//	            System.out.println("Se ha hecho commit de la transaccion");
//	        } catch (SQLException ex) {
//	            ex.printStackTrace(System.out);
//	            System.out.println("Entramos al rollback");
//	            try {
//	                conexion.rollback();
//	            } catch (SQLException ex1) {
//	                ex1.printStackTrace(System.out);
//	            }
//	        }
		
}
//	public static void calcular()throws ParseException {
//		
//		LocalDate l = LocalDate.of(1993, 11, 17); //specify year, month, date directly
//		LocalDate now = LocalDate.now(); //gets localDate
//		Period diff = Period.between(l, now); //difference between the dates is calculated
//		System.out.println(diff.getYears() + "years" + diff.getMonths() + "months" + diff.getDays() + "days");
//		
//		System.out.println(now.getYear());
//	}

}
