package DAO;

import java.util.List;

import entidades.AlumnoEntidad;

public interface AlumnoDAO {
	public void crearAlumno(AlumnoEntidad alumno);
	public AlumnoEntidad obtenerAlumnosPorId(String id);
	public void actualizarAlumno(AlumnoEntidad alumno);
	public void eliminarAlumno(String id);
	public List<AlumnoEntidad> obtenerAlumnos();
}
