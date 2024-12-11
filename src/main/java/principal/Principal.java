package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import dto.AlumnoDTO;
import services.AlumnoService;

public class Principal {

	public static void main(String[] args) {

		DataSource dataSource = configurarDataSource();
		AlumnoService alService = AlumnoService.getInstancia(dataSource);

		AlumnoDTO nuevo = new AlumnoDTO();
		nuevo.setCodAlumno("38");
		nuevo.setNombreAlumno("Alejandro");
		nuevo.setApellidosAlumno("Gonzalez");
		nuevo.setEdad(27);
		nuevo.setGrupo('A'); // ES TIPO CHAR

		alService.crearAlumno(nuevo);

		nuevo.setNombreAlumno("Juanito");
		alService.actualizarAlumno(nuevo.getCodAlumno(), nuevo); // ?

		System.out.println(alService.obtenerAlumnoPorID(nuevo.getCodAlumno()));

		alService.eliminarAlumno(nuevo.getCodAlumno());

		System.out.println(alService.obtenerTodoAlumnoDTO()); // tip: meter en una lista los datos y luego recorrerlo

	}

	static DataSource configurarDataSource() {
		MysqlDataSource ds = new MysqlDataSource();
		Properties props = new Properties();

		FileInputStream file;
		try {
			file = new FileInputStream("src\\main\\resources\\db.properties");
			props.load(file);

			ds.setUrl(props.getProperty("url"));
			ds.setUser(props.getProperty("user"));
			ds.setPassword(props.getProperty("password"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;

	}

}
