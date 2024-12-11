package services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import DAO.AlumnoDAOImplementacion;
import dto.AlumnoDTO;
import entidades.AlumnoEntidad;

public class AlumnoService {

	private static AlumnoService instancia;
	private AlumnoDAOImplementacion alumnoDAOImplementacion;

	private AlumnoService(DataSource dataSource) {
		super();
		this.alumnoDAOImplementacion = alumnoDAOImplementacion.getInstancia(dataSource);
	}

	public static AlumnoService getInstancia(DataSource dataSource) {
		if (instancia == null) {
			instancia = new AlumnoService(dataSource);
		}
		return instancia;
	}

	/// metodos alumnos

	public void crearAlumno(AlumnoDTO al) {
		AlumnoEntidad alm = mapeoDTOtoEntidad(al);
		alumnoDAOImplementacion.crearAlumno(alm);
	}

	public AlumnoDTO obtenerAlumnoPorID(String id) {
		AlumnoEntidad al = alumnoDAOImplementacion.obtenerAlumnosPorId(id);
		AlumnoDTO alDTO = mapeaEntidadTOdto(al);
		return alDTO;
	}

	public List<AlumnoDTO> obtenerTodoAlumnoDTO() {

		List<AlumnoEntidad> lista = alumnoDAOImplementacion.obtenerAlumnos();
		List<AlumnoDTO> listaDTO = new ArrayList<AlumnoDTO>();

		for (AlumnoEntidad a : lista) {
			listaDTO.add(mapeaEntidadTOdto(a));
		}

		return listaDTO;

	}

	public AlumnoEntidad actualizarAlumno(String id, AlumnoDTO al) {

		AlumnoEntidad alumEntidad = alumnoDAOImplementacion.obtenerAlumnosPorId(id);
		if (alumEntidad != null) {
			alumEntidad.setApellidosAlumno(al.getApellidosAlumno());
			alumEntidad.setNombreAlumno(al.getNombreAlumno());
			alumEntidad.setFechaNacimiento(convertirEdadFecha(al.getEdad()));
			alumEntidad.setGrupo(al.getGrupo());
			alumnoDAOImplementacion.actualizarAlumno(alumEntidad);
		}

		return alumEntidad;

	}

	public boolean eliminarAlumno(String id) {

		AlumnoEntidad alumno = alumnoDAOImplementacion.obtenerAlumnosPorId(id);
		if (alumno != null) {
			alumnoDAOImplementacion.eliminarAlumno(id);
			return true;
		}
		return false;
	}

	private static AlumnoDTO mapeaEntidadTOdto(AlumnoEntidad a) {

		AlumnoDTO al = new AlumnoDTO();

		al.setNombreAlumno(a.getNombreAlumno());
		al.setApellidosAlumno(a.getApellidosAlumno());
		al.setCodAlumno(a.getCodAlumno());
		al.setEdad(convertirFechaEdad(a.getFechaNacimiento()));
		al.setGrupo(a.getGrupo());

		return al;
	}

	private static AlumnoEntidad mapeoDTOtoEntidad(AlumnoDTO al) {

		AlumnoEntidad alEntidad = new AlumnoEntidad();

		alEntidad.setNombreAlumno(al.getNombreAlumno());
		alEntidad.setApellidosAlumno(al.getApellidosAlumno());
		alEntidad.setCodAlumno(al.getCodAlumno());
		alEntidad.setFechaNacimiento(convertirEdadFecha(al.getEdad()));
		alEntidad.setGrupo(al.getGrupo());

		return alEntidad;

	}

	private static Date convertirEdadFecha(int edad) {
		LocalDate nac = LocalDate.now().minusYears(edad);
		return Date.from(nac.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private static int convertirFechaEdad(Date fechaNacimiento) {

		LocalDate nac = new java.util.Date(fechaNacimiento.getTime()).toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		return Period.between(nac, LocalDate.now()).getYears();

	}

}
