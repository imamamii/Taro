package mx.com.taro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import utilidades.Conexion;

public class ClienteDaoJDBC {
	private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT ID, NOMBRE, APELLIDO, TELEFONO  FROM TEST";
    private static final String SQL_INSERT = "INSERT INTO TEST (NOMBRE, APELLIDO, TELEFONO) VALUES(?,?,?);";
    private static final String SQL_UPDATE = "UPDATE TEST SET NOMBRE=?, APELLIDO=?, TELEFONO=? WHERE ID = ?";

    ClienteDaoJDBC(){
    	
    }
    
    public int insert(ClienteDTO cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
//            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());

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

                cliente = new ClienteDTO();
                cliente.setIdCliente(id_cliente);
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);

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
            stmt.setInt(4, cliente.getIdCliente());
            
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







}
