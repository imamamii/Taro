package mx.com.taro;

public class ClienteDTO {
	
	
	private Integer idCliente;
	private String nombre;
	private String apellido;
	private String telefono;
	
	public ClienteDTO(){
		
	}
	
	public ClienteDTO(Integer idCliente,String nombre,String apellido,String telefono){
		super();
		this.idCliente=idCliente;
		this.nombre=nombre;
		this.apellido=apellido;
		this.telefono=telefono;
	}
	
	


	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono="
				+ telefono + "]";
	}

	
	
	
	

	
	
	
}
