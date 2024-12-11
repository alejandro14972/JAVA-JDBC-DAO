package entidades;

public class ProfesorEntidad {
	
	private String codProfesor;
	private String nombreProfesor;
	private String ciudad;
	
		
	public ProfesorEntidad() {
		super();
	}

	public ProfesorEntidad(String codProfesor, String nombreProfesor, String ciudad) {
		super();
		this.codProfesor = codProfesor;
		this.nombreProfesor = nombreProfesor;
		this.ciudad = ciudad;
	}

	public String getCodProfesor() {
		return codProfesor;
	}

	public void setCodProfesor(String codProfesor) {
		this.codProfesor = codProfesor;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	
	
	

}
