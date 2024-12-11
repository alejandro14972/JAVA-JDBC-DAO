package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import dto.ProfesorDTO;
import services.ProfesorService;

public class principalProfesor {

	public static void main(String[] args) {
		
		DataSource dataSource = configurarDataSource();
		ProfesorService profService = ProfesorService.getInstancia(dataSource);
		
		
		ProfesorDTO nuevoprofesor = new ProfesorDTO();
		nuevoprofesor.setCodProfesor("08");
		nuevoprofesor.setNombreProfesor("Alejandro");
		nuevoprofesor.setCiudad("Mieres");
		
		//profService.crearProfesor(nuevoprofesor);
		
		nuevoprofesor.setNombreProfesor("Pedro");
		nuevoprofesor.setCiudad("Oviedo");
		//profService.actualizarProfesor(nuevoprofesor.getCodProfesor(), nuevoprofesor);
		
		System.out.println(profService.obtenerProfesorPorID("05"));
		
		
		
		
		//System.out.println(profService.obtenerTodoProfesorDTO());

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
