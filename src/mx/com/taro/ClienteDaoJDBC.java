package mx.com.taro;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import utilidades.Conexion;

public class ClienteDaoJDBC {
	private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT ID, NOMBRE, APELLIDO, TELEFONO,CASA, CALLE,NUMEXT,NUMINT,COLONIA,DELEGACION,CP,ECALLE,YCALLE,COLOR,ALERGIA,INTOLERANCIA,REGIMEN,CUMPLE,PREFERENCIA,INSTAGRAM,FB,CORREO,NOTAS FROM SISTEMASQL ORDER BY ID DESC";
    private static final String SQL_INSERT = "INSERT INTO SISTEMASQL (NOMBRE, APELLIDO, TELEFONO,CASA,CALLE,NUMEXT,NUMINT,COLONIA,DELEGACION,CP,ECALLE,YCALLE,COLOR,ALERGIA,INTOLERANCIA,REGIMEN,CUMPLE,PREFERENCIA,INSTAGRAM,FB,CORREO,NOTAS) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE SISTEMASQL SET NOMBRE=?, APELLIDO=?, TELEFONO=? ,CASA=?,CALLE=?,NUMEXT=?,NUMINT=?,COLONIA=?,DELEGACION=?,CP=?,ECALLE=?,YCALLE=?,COLOR=?,ALERGIA=?,INTOLERANCIA=?,REGIMEN=?,CUMPLE=?,PREFERENCIA=?,INSTAGRAM=?,FB=?,CORREO=?,NOTAS=? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM SISTEMASQL WHERE ID=?";

    ClienteDaoJDBC(){
    	
    }
    
    public int insert(ClienteDTO cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
//            stmt.setInt(0, cliente.getIdCliente());
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getCasa());
            stmt.setString(5, cliente.getCalle());
            stmt.setString(6, cliente.getNumExt());
            stmt.setString(7, cliente.getNumInt());
            stmt.setString(8, cliente.getColonia());
            stmt.setString(9, cliente.getDelegacion());
            stmt.setString(10, cliente.getCp());
            stmt.setString(11, cliente.geteCalle());
            stmt.setString(12, cliente.getyCalle());
            stmt.setString(13, cliente.getColor());
            stmt.setString(14, cliente.getAlergia());
            stmt.setString(15, cliente.getIntolerancia());
            stmt.setString(16, cliente.getRegimen());
//            stmt.setsetString(16, cliente.getEdad());
            stmt.setDate(17, Date.valueOf(cliente.getCumple()));
            //stmt.setDate(int,Date)pero estoy mandando LocalDate, por lo tanto se tiene que convertir a Date con el valueOf
//            stmt.setDate(17, java.sql.Date.valueOf(cliente.getCumple()));
            stmt.setString(18, cliente.getPreferencia());
            stmt.setString(19, cliente.getInstagram());
            stmt.setString(20, cliente.getFb());
            stmt.setString(21, cliente.getCorreo());
            stmt.setString(22, cliente.getNotas());
            
            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }
    
    public ClienteDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    public List<ClienteDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteDTO cliente = null;
        List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_cliente = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDO");
                String telefono = rs.getString("TELEFONO");
                String casa = rs.getString("CASA");
                String calle=rs.getString("CALLE");
                String numExt =rs.getString("NUMEXT");
                String numInt =rs.getString("NUMINT");
                String colonia =rs.getString("COLONIA");
                String delegacion=rs.getString("DELEGACION");
                String cp =rs.getString("CP");
                String eCalle =rs.getString("ECALLE");
                String yCalle=rs.getString("YCALLE");
                String color=rs.getString("COLOR");
                String alergia=rs.getString("ALERGIA");
                String intolerancia=rs.getString("INTOLERANCIA");
                String regimen=rs.getString("REGIMEN");
//                String edad=rs.getString("EDAD");
                Date cumple=rs.getDate("CUMPLE");
                String preferencia=rs.getString("PREFERENCIA");
                String instagram=rs.getString("INSTAGRAM");
                String fb=rs.getString("FB");
                String correo=rs.getString("CORREO");
                String notas=rs.getString("NOTAS");
            
                

                cliente = new ClienteDTO();
                cliente.setIdCliente(id_cliente);
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);
                cliente.setCasa(casa);
                cliente.setCalle(calle);
                cliente.setNumExt(numExt);
                cliente.setNumInt(numInt);
                cliente.setColonia(colonia);
                cliente.setDelegacion(delegacion);
                cliente.setCp(cp);
                cliente.seteCalle(eCalle);
                cliente.setyCalle(yCalle);
                cliente.setColor(color);
                cliente.setAlergia(alergia);
                cliente.setIntolerancia(intolerancia);
                cliente.setRegimen(regimen);
//                cliente.setEdad(edad);
                // TODO explicar NullPointer
                if(cumple==null) {
                	cliente.setCumple(null);
                }else {
                	cliente.setCumple(cumple.toLocalDate());
                }
                cliente.setPreferencia(preferencia);
                cliente.setInstagram(instagram);
                cliente.setFb(fb);
                cliente.setCorreo(correo);
                cliente.setNotas(notas);

                clientes.add(cliente);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }

        }

        return clientes;
    }

    public int update(ClienteDTO cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getCasa());
            stmt.setString(5, cliente.getCalle());
            stmt.setString(6, cliente.getNumExt());
            stmt.setString(7, cliente.getNumInt());
            stmt.setString(8, cliente.getColonia());
            stmt.setString(9, cliente.getDelegacion());
            stmt.setString(10, cliente.getCp());
            stmt.setString(11, cliente.geteCalle());
            stmt.setString(12, cliente.getyCalle());
            stmt.setString(13, cliente.getColor());
            stmt.setString(14, cliente.getAlergia());
            stmt.setString(15, cliente.getIntolerancia());
            stmt.setString(16, cliente.getRegimen());
//            stmt.setString(16, cliente.getEdad());
            stmt.setDate(17, Date.valueOf(cliente.getCumple()));
            stmt.setString(18, cliente.getPreferencia());
            stmt.setString(19, cliente.getInstagram());
            stmt.setString(20, cliente.getFb());
            stmt.setString(21, cliente.getCorreo());
            stmt.setString(22, cliente.getNotas());
            
            stmt.setInt(23, cliente.getIdCliente());
            
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
    
    public int delete(ClienteDTO cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdCliente());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }






}
