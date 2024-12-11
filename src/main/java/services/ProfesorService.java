package services;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import DAO.AlumnoDAOImplementacion;
import DAO.ProfesorDAO;
import DAO.ProfesorDAOImplementacion;
import dto.AlumnoDTO;
import dto.ProfesorDTO;
import entidades.AlumnoEntidad;
import entidades.ProfesorEntidad;

public class ProfesorService {

	private static ProfesorService instancia;
	private static ProfesorDAOImplementacion ProfesorDAOImplementacion;

	private ProfesorService(DataSource dataSource) {
		super();
		this.ProfesorDAOImplementacion = ProfesorDAOImplementacion.getInstancia(dataSource);
	}

	public static ProfesorService getInstancia(DataSource dataSource) {
		if (instancia == null) {
			instancia = new ProfesorService(dataSource);
		}
		return instancia;
	}
	
	public void crearProfesor(ProfesorDTO al) {
		ProfesorEntidad alm = mapeoDTOtoEntidad(al);
		ProfesorDAOImplementacion.crearProfesor(alm);
	}
	
	
	public ProfesorDTO obtenerProfesorPorID(String id) {
		ProfesorEntidad al = ProfesorDAOImplementacion.obtenerProfesorPorId(id);
		ProfesorDTO alDTO = mapeaEntidadTOdto(al);
		return alDTO;
	}
	

	public List<ProfesorDTO> obtenerTodoProfesorDTO() {
		List<ProfesorEntidad> lista = ProfesorDAOImplementacion.obtenerProfesor();
		List<ProfesorDTO> listaDTO = new ArrayList<ProfesorDTO>();
		for (ProfesorEntidad a : lista) {
			listaDTO.add(mapeaEntidadTOdto(a));
		}

		return listaDTO;
	}
	
	public ProfesorEntidad actualizarProfesor(String id, ProfesorDTO pr) {
		ProfesorEntidad profEntidad = ProfesorDAOImplementacion.obtenerProfesorPorId(id);
		if (profEntidad != null) {
			profEntidad.setNombreProfesor(pr.getNombreProfesor());
			profEntidad.setCiudad(pr.getCiudad());
			ProfesorDAOImplementacion.actualizarProfesor(profEntidad);
		}

		return profEntidad;
	}
	
	public boolean eliminarProfesor(String id) {
		ProfesorEntidad profesor = ProfesorDAOImplementacion.obtenerProfesorPorId(id);
		if (profesor != null) {
			ProfesorDAOImplementacion.eliminarProfesor(id);
			return true;
		}
		return false;
	}
	
	
	private static ProfesorDTO mapeaEntidadTOdto(ProfesorEntidad a) {
		ProfesorDTO pr = new ProfesorDTO();
		
		pr.setNombreProfesor(a.getNombreProfesor());
		pr.setCiudad(a.getCiudad());
		pr.setCodProfesor(a.getCodProfesor());
		
		List<String> modulos = ProfesorDAOImplementacion.obtenerModulosProfesor(a.getCodProfesor());
		
		pr.setModulos(modulos);

		return pr;
	}

	private static ProfesorEntidad mapeoDTOtoEntidad(ProfesorDTO pr) {

		ProfesorEntidad prEntidad = new ProfesorEntidad();

		prEntidad.setNombreProfesor(pr.getNombreProfesor());
		prEntidad.setCiudad(pr.getCiudad());
		prEntidad.setCodProfesor(pr.getCodProfesor());
	
		return prEntidad;

	}
	
	
	
}
