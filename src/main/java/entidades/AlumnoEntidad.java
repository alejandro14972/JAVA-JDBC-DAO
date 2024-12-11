package entidades;

import java.util.Date;

public class AlumnoEntidad {
	private String codAlumno;
	private String nombreAlumno;
	private String apellidosAlumno;
	private Date fechaNacimiento;
	private char grupo;
	
	
	public AlumnoEntidad() {
		super();
	}
	public AlumnoEntidad(String codAlumno, String nombreAlumno, String apellidosAlumno, Date fechaNacimiento, char grupo) {
		super();
		this.codAlumno = codAlumno;
		this.nombreAlumno = nombreAlumno;
		this.apellidosAlumno = apellidosAlumno;
		this.fechaNacimiento = fechaNacimiento;
		this.grupo = grupo;
	}
	public String getCodAlumno() {
		return codAlumno;
	}
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public String getApellidosAlumno() {
		return apellidosAlumno;
	}
	public void setApellidosAlumno(String apellidosAlumno) {
		this.apellidosAlumno = apellidosAlumno;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public char getGrupo() {
		return grupo;
	}
	public void setGrupo(char c) {
		this.grupo = c;
	}

	
	
}
