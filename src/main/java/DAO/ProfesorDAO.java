package DAO;

import java.util.List;

import entidades.ProfesorEntidad;

public interface ProfesorDAO {
	public void crearProfesor(ProfesorEntidad profesor);
	public ProfesorEntidad obtenerProfesorPorId(String id);
	public void actualizarProfesor(ProfesorEntidad porofesor);
	public void eliminarProfesor(String id);
	public List<ProfesorEntidad> obtenerProfesor();
	public List<String> obtenerModulosProfesor(String codProfesor);

}
