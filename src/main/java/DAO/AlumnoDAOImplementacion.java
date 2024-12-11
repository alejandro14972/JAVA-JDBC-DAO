package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entidades.AlumnoEntidad;

public class AlumnoDAOImplementacion implements AlumnoDAO {

	private static AlumnoDAOImplementacion instancia;
	private DataSource dataSource;

	// Constructor privado para evitar instanciación
	private AlumnoDAOImplementacion(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// Método para obtener la instancia única de AlumnoDAO
	public static AlumnoDAOImplementacion getInstancia(DataSource dataSource) {
		if (instancia == null) {
			instancia = new AlumnoDAOImplementacion(dataSource);
		}
		return instancia;
	}

	@Override
	public void crearAlumno(AlumnoEntidad alumno) {
		// TODO Auto-generated method stub
		String sql = "insert into alumno (cod_alumno, nombre_alumno, apellidos_alumno, fecha_nacimiento, grupo) "
				+ "values (?,?,?,?,?) ";

		try (Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, alumno.getCodAlumno());
			statement.setString(2, alumno.getNombreAlumno());
			statement.setString(3, alumno.getApellidosAlumno());
			statement.setDate(4, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
			statement.setString(5, String.valueOf(alumno.getGrupo()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AlumnoEntidad obtenerAlumnosPorId(String id) {
		// TODO Auto-generated method stub

		String sql = "SELECT cod_alumno, nombre_alumno, apellidos_alumno, fecha_nacimiento, grupo "
				+ "FROM alumno WHERE cod_alumno = ?";

		Connection con;
		try {
			con = dataSource.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, id);
			try (ResultSet resultSet = pre.executeQuery()) {
				if (resultSet.next()) {
					return mapearResultSetAlumno(resultSet);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<AlumnoEntidad> obtenerAlumnos() {
		// TODO Auto-generated method stub
		List<AlumnoEntidad> alumnos = new ArrayList<>();
		String sql = "SELECT cod_alumno, nombre_alumno, apellidos_alumno, fecha_nacimiento, grupo FROM alumno";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				AlumnoEntidad all = mapearResultSetAlumno(resultSet);
				alumnos.add(all);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alumnos;
	}

	public void actualizarAlumno(AlumnoEntidad alumno) {
		String sql = "UPDATE alumno SET nombre_alumno = ?, apellidos_alumno = ?, fecha_nacimiento = ?, grupo = ?"
				+ " WHERE cod_alumno = ?";
		try (Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, alumno.getNombreAlumno());
			statement.setString(2, alumno.getApellidosAlumno());
			statement.setDate(3, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
			statement.setString(4, String.valueOf(alumno.getGrupo()));
			statement.setString(5, alumno.getCodAlumno());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarAlumno(String id) {

		String sql = "DELETE FROM alumno WHERE cod_alumno = ?";
		try (Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private AlumnoEntidad mapearResultSetAlumno(ResultSet resultSet) throws SQLException {
		AlumnoEntidad alumno = new AlumnoEntidad();
		alumno.setCodAlumno(resultSet.getString("cod_alumno"));
		alumno.setNombreAlumno(resultSet.getString("nombre_alumno"));
		alumno.setApellidosAlumno(resultSet.getString("apellidos_alumno"));
		alumno.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
		alumno.setGrupo(resultSet.getString("grupo").charAt(0));
		return alumno;
	}


}
