package dto;

import java.io.Serializable;
import java.util.List;



public class ProfesorDTO implements Serializable {
	
	private String codProfesor;
	private String nombreProfesor;
	private String ciudad;
	private List<String> modulos;
	
	public ProfesorDTO() {
		super();
	}

	public ProfesorDTO(String codProfesor, String nombreProfesor, String ciudad, List<String> modulos) {
		super();
		this.codProfesor = codProfesor;
		this.nombreProfesor = nombreProfesor;
		this.ciudad = ciudad;
		this.modulos = modulos;
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

	public List<String> getModulos() {
		return modulos;
	}

	public void setModulos(List<String> modulos) {
		this.modulos = modulos;
	}

	@Override
	public String toString() {
		return "ProfesorDTO [codProfesor=" + codProfesor + ", nombreProfesor=" + nombreProfesor + ", ciudad=" + ciudad
				+ ", modulos=" + modulos + "]";
	}
	
}
