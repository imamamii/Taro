package mx.com.taro;

import java.time.LocalDate;
import java.time.Period;

public class ClienteDTO {
	
	
	private Integer idCliente;
	private String nombre,apellido,telefono,calle,numInt,numExt,colonia,delegacion,cp,
	eCalle,yCalle,color,alergia,intolerancia,regimen,
	edad,preferencia,instagram,fb,correo,notas;
	private LocalDate cumple;
	
	
	
	public ClienteDTO(){
		
	}
	
	public ClienteDTO(Integer idCliente,String nombre,String apellido,String telefono,
			String calle,String numExt,String numInt,String colonia,String delegacion,
			String cp,String eCalle,String yCalle,String color,String alergia,String intolerancia,
			String regimen,
			String edad,LocalDate cumple,String preferencia,String instagram,String fb,
			String correo,String notas){
		super();
		this.idCliente=idCliente;
		this.nombre=nombre;
		this.apellido=apellido;
		this.telefono=telefono;
		this.calle=calle;
		this.numExt=numExt;
		this.numInt=numInt;
		this.colonia=colonia;
		this.delegacion=delegacion;
		this.cp=cp;
		this.eCalle=eCalle;
		this.yCalle=yCalle;
		this.color=color;
		this.alergia=alergia;
		this.intolerancia=intolerancia;
		this.regimen=regimen;
		this.edad=edad;
		this.cumple=cumple;
		this.preferencia=preferencia;
		this.instagram=instagram;
		this.fb=fb;
		this.correo=correo;
		this.notas=notas;
		
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

	
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumInt() {
		return numInt;
	}

	public void setNumInt(String numInt) {
		this.numInt = numInt;
	}

	public String getNumExt() {
		return numExt;
	}

	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getDelegacion() {
		return delegacion;
	}

	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String geteCalle() {
		return eCalle;
	}

	public void seteCalle(String eCalle) {
		this.eCalle = eCalle;
	}

	public String getyCalle() {
		return yCalle;
	}

	public void setyCalle(String yCalle) {
		this.yCalle = yCalle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getIntolerancia() {
		return intolerancia;
	}

	public void setIntolerancia(String intolerancia) {
		this.intolerancia = intolerancia;
	}
	
	
	

	
	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public String getEdad() {
		
//		LocalDate fechaDeNac = LocalDate.of(ldA, ldM, ldD); // specify year, month, date directly
		
		
		LocalDate now = LocalDate.now(); // gets localDate
		if(cumple!=null) {
			Period diff = Period.between(cumple, now); // difference between the dates is calculated
			edad = String.valueOf(diff.getYears());
		}
//		System.out.println(diff.getYears() + "years" + diff.getMonths() + "months" + diff.getDays() + "days");
		return edad;
	}

//	public void setEdad(String edad) {
//		this.edad = edad;
//	}

	public LocalDate getCumple() {
		return cumple;
	}

	public void setCumple(LocalDate cumple) {
		this.cumple = cumple;
	}

	public String getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getFb() {
		return fb;
	}

	public void setFb(String fb) {
		this.fb = fb;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono="
				+ telefono + ", calle=" + calle + ", numInt=" + numInt + ", numExt=" + numExt + ", colonia=" + colonia
				+ ", delegacion=" + delegacion + ", cp=" + cp + ", eCalle=" + eCalle + ", yCalle=" + yCalle + ", color="
				+ color + ", alergia=" + alergia + ", intolerancia=" + intolerancia + ", edad=" + edad + ", cumple="
				+ cumple + ", preferencia=" + preferencia + ", instagram=" + instagram + ", fb=" + fb + ", correo="
				+ correo + ", notas=" + notas + "]";
	}


	
	
	
	

	
	
	
}
