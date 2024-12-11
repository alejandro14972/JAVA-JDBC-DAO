package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entidades.AlumnoEntidad;
import entidades.ModuloEntidad;
import entidades.ProfesorEntidad;

public class ProfesorDAOImplementacion implements ProfesorDAO {

	private static ProfesorDAOImplementacion instancia;
	private DataSource dataSource;

	// Constructor privado para evitar instanciación
	private ProfesorDAOImplementacion(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// Método para obtener la instancia única de AlumnoDAO
	public static ProfesorDAOImplementacion getInstancia(DataSource dataSource) {
		if (instancia == null) {
			instancia = new ProfesorDAOImplementacion(dataSource);
		}
		return instancia;
	}

	@Override
	public void crearProfesor(ProfesorEntidad profesor) {

		String sql = "insert into profesor (cod_profesor, nombre_profesor, ciudad) " + "values (?,?,?) ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, profesor.getCodProfesor());
			statement.setString(2, profesor.getNombreProfesor());
			statement.setString(3, profesor.getCiudad());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ProfesorEntidad obtenerProfesorPorId(String id) {

		String sql = "SELECT cod_profesor, nombre_profesor, ciudad " + "FROM profesor WHERE cod_profesor = ?";

		Connection con;
		try {
			con = dataSource.getConnection();
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, id);
			try (ResultSet resultSet = pre.executeQuery()) {
				if (resultSet.next()) {
					return mapearResultSetProfesor(resultSet);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void actualizarProfesor(ProfesorEntidad profesor) {
		// TODO Auto-generated method stub

		String sql = "UPDATE profesor SET nombre_profesor = ?, ciudad = ?" + " WHERE cod_profesor = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, profesor.getNombreProfesor());
			statement.setString(2, profesor.getCiudad());
			statement.setString(3, profesor.getCodProfesor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void eliminarProfesor(String id) {
		// TODO Auto-generated method stub

		String sql = "DELETE FROM profesor WHERE cod_profesor = ?";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<ProfesorEntidad> obtenerProfesor() {
		List<ProfesorEntidad> profesores = new ArrayList<>();
		String sql = "SELECT cod_profesor, nombre_profesor, ciudad FROM profesor";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				ProfesorEntidad all = mapearResultSetProfesor(resultSet);
				profesores.add(all);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return profesores;
	}

	private ProfesorEntidad mapearResultSetProfesor(ResultSet resultSet) throws SQLException {
		ProfesorEntidad profesor = new ProfesorEntidad();
		profesor.setCodProfesor(resultSet.getString("cod_profesor"));
		profesor.setNombreProfesor(resultSet.getString("nombre_profesor"));
		profesor.setCiudad(resultSet.getString("ciudad"));
		return profesor;
	}

	@Override
	public List<String> obtenerModulosProfesor(String codProfesor) {
		List<String> modulos = new ArrayList<>();
		String sql = "SELECT nombre_modulo from modulo where cod_profesor = ?";

		try (Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, codProfesor);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String nombre = resultSet.getString(1);
				modulos.add(nombre);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modulos;
	}

}
