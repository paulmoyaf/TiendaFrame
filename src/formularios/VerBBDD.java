package formularios;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import conexion.Conexion;
import productos.Teclado;
import formularios.Formulario;
import formularios.Tabla;

public class VerBBDD extends javax.swing.JFrame{
	

	public static ResultSet conexionResultSet(Conexion conexion) throws ClassNotFoundException, SQLException{
		Statement sql = null;
		ResultSet rs = null;
		// Conexion conexion = new Conexion();
		sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		System.out.println("rs Establecida");
		return rs = sql.executeQuery("select * from instrumentos");
	}

	public static ArrayList<Teclado> listar() throws ClassNotFoundException {

		ArrayList<Teclado> arrTeclados = new ArrayList<Teclado>();

		Connection conexion = null;
		Statement sql = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "productos" + "?useSSL=false";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conexion = DriverManager.getConnection(url, "root", "");
				System.out.println("Conexión establecida");
				sql = conexion.createStatement();
				rs = sql.executeQuery("select * from instrumentos");

				System.out.println("Consulta ejecutada correctamente...\n");

				boolean r = rs.next();
				while (r) {
					Teclado teclado = new Teclado();
					String id = rs.getString("codigo");
					String marca = rs.getString("marca");
					Double precio = rs.getDouble("precio");
					Double dcto = rs.getDouble("descuento");
					String tipo = rs.getString("tipo");
					Boolean prime;
					String color = rs.getString("color");
					int teclas = rs.getInt("teclas");
					String conector = rs.getString("conector");
					String envio = rs.getString("envio");
					String pvp = rs.getString("pvp");

					if (tipo.equals("PRIME")) {
						prime = true;
					} else {
						prime = false;
					}

					teclado.setId(id);
					teclado.setMarca(marca);
					teclado.setPrecio(precio);
					teclado.setDcto(dcto);
					teclado.setPrime(prime);
					teclado.setColor(color);
					teclado.setTeclas(teclas);
					teclado.setConector(conector);

					System.out.println(teclado.toString());
					r = rs.next();
					arrTeclados.add(teclado);
				}

				conexion.close();
				System.out.println("\nCerrando la conexión");

			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error del controlador ");
		}
		return arrTeclados;

	}

	public static Teclado buscarItem(String txt_code) throws Exception {
		DefaultTableModel modelo = new DefaultTableModel();

		Conexion conexion = new Conexion();
		
		Statement sql = null;
		ResultSet rs = null;
		Teclado teclado = new Teclado();
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			try {
				sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				System.out.println("Conexión establecida");
				rs = sql.executeQuery("select * from instrumentos where instrumentos.codigo = '" + txt_code + "';");
				boolean r = rs.next();
				while (r) {

					String id = rs.getString("codigo");
					String marca = rs.getString("marca");
					Double precio = rs.getDouble("precio");
					Double dcto = rs.getDouble("descuento");
					String tipo = rs.getString("tipo");
					Boolean prime;
					String color = rs.getString("color");
					int teclas = rs.getInt("teclas");
					String conector = rs.getString("conector");
					String envio = rs.getString("envio");
					String pvp = rs.getString("pvp");

					if (tipo.equals("PRIME")) {
						prime = true;
					} else {
						prime = false;
					}

					teclado.setId(id);
					teclado.setMarca(marca);
					teclado.setPrecio(precio);
					teclado.setDcto(dcto);
					teclado.setPrime(prime);
					teclado.setColor(color);
					teclado.setTeclas(teclas);
					teclado.setConector(conector);

					System.out.println(teclado.toString());
					r = rs.next();
					// arrTeclados.add(teclado);
					// return teclado;
				}
				// conexion.conectarMySQL().close();
				// conexion.close();
				System.out.println("\nCerrando la conexión");
				
			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error del controlador " + e);
		}

		return teclado;
	}

	public static void cambiarItem(String txt_code, String txt_id, String txt_marca, Double txt_precio, Double txt_dcto,
			String combo, String txt_color, Integer txt_teclas, String txt_conector, String txt_envio,
			Double txt_pvp) throws Exception {
		DefaultTableModel modelo = new DefaultTableModel();

		Connection conexion = null;
		Statement sql = null;
		// ResultSet rs = null;

		String url = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "productos" + "?useSSL=false";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conexion = DriverManager.getConnection(url, "root", "");
				System.out.println("Conexión establecida");
				String query = "update instrumentos set codigo = '" + txt_id.toLowerCase() + "', marca = '" + txt_marca.toUpperCase()
						+ "', precio = '" + txt_precio + "', descuento = '" + txt_dcto + "', tipo = '" + combo.toUpperCase()
						+ "', color = '" + txt_color.toUpperCase() + "', teclas = '" + txt_teclas + "', conector = '" + txt_conector.toUpperCase()
						+ "' where (codigo = '" + txt_code + "');";
				sql = conexion.createStatement();
				sql.executeUpdate(query);

				System.out.println("\nCambios realizados satisfactoriamente....");
				conexion.close();
				System.out.println("\nCerrando la conexión");

			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL" + e);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error del controlador " + e);
		}

	}

	public static void crearItem(String txt_id, String txt_marca, Double txt_precio, Double txt_dcto, String combo,
			String txt_color, Integer txt_teclas, String txt_conector) throws Exception {
		DefaultTableModel modelo = new DefaultTableModel();

		Connection conexion = null;
		Statement sql = null;
		// ResultSet rs = null;

		String url = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "productos" + "?useSSL=false";
		txt_color.toUpperCase();
		// Teclado teclado = new Teclado();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conexion = DriverManager.getConnection(url, "root", "");
				System.out.println("Conexión establecida");
				// String query = "update instrumentos set marca = 'FENDER', precio = '300',
				// descuento = '10', tipo = 'Regular', color = 'GRIS', teclas = '61', conector =
				// 'MIDI', envio = '15€', pvp = '3000' where (codigo = 't32');";
				String query = "insert into instrumentos (codigo , marca , precio , descuento , tipo, color, teclas, conector) values ('"
						+ txt_id.toLowerCase() + "','" + txt_marca.toUpperCase() + "','" + txt_precio + "','" + txt_dcto + "','" + combo + "','"
						+ txt_color.toUpperCase() + "','" + txt_teclas + "','" + txt_conector.toUpperCase() +
						"');";
				sql = conexion.createStatement();
				sql.executeUpdate(query);

				System.out.println("\nInstrumento creado satisfactoriamente....");
				conexion.close();
				System.out.println("\nCerrando la conexión");

			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL" + e);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error del controlador " + e);
		}

		// return teclado;
	}

	public static void borrarItem(String txt_code) throws Exception {
		DefaultTableModel modelo = new DefaultTableModel();

		Connection conexion = null;
		Statement sql = null;
		// ResultSet rs = null;

		String url = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "productos" + "?useSSL=false";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conexion = DriverManager.getConnection(url, "root", "");
				System.out.println("Conexión establecida");
		
				String query = "delete from instrumentos where (codigo = '" + txt_code + "');";
				sql = conexion.createStatement();
				sql.executeUpdate(query);

				System.out.printf("\nItem borrado %s satisfactoriamente....", txt_code);
				conexion.close();
				System.out.println("\nCerrando la conexión");

			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL" + e);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error del controlador " + e);
		}

	}

	public static Teclado previousItem(ResultSet rs) throws Exception {
		Teclado teclado = new Teclado();
		try {
			if (rs.isFirst() == false) {
				rs.previous();

				String id = rs.getString("codigo");
				String marca = rs.getString("marca");
				Double precio = rs.getDouble("precio");
				Double dcto = rs.getDouble("descuento");
				String tipo = rs.getString("tipo");
				Boolean prime;
				String color = rs.getString("color");
				int teclas = rs.getInt("teclas");
				String conector = rs.getString("conector");
				String envio = rs.getString("envio");
				String pvp = rs.getString("pvp");

				if (tipo.equals("PRIME")) {
					prime = true;
				} else {
					prime = false;
				}

				teclado.setId(id);
				teclado.setMarca(marca);
				teclado.setPrecio(precio);
				teclado.setDcto(dcto);
				teclado.setPrime(prime);
				teclado.setColor(color);
				teclado.setTeclas(teclas);
				teclado.setConector(conector);
				System.out.println(teclado.toString());
			} else {
				return teclado = null;
			}

		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		}
		return teclado;
	}

	public static Teclado nextItem(ResultSet rs) throws Exception {
		Teclado teclado = new Teclado();

		try {
			if (rs.isLast() == false) {
				rs.next();
				// rs.previous();

				String id = rs.getString("codigo");
				String marca = rs.getString("marca");
				Double precio = rs.getDouble("precio");
				Double dcto = rs.getDouble("descuento");
				String tipo = rs.getString("tipo");
				Boolean prime;
				String color = rs.getString("color");
				int teclas = rs.getInt("teclas");
				String conector = rs.getString("conector");
				String envio = rs.getString("envio");
				String pvp = rs.getString("pvp");

				if (tipo.equals("PRIME")) {
					prime = true;
				} else {
					prime = false;
				}

				teclado.setId(id);
				teclado.setMarca(marca);
				teclado.setPrecio(precio);
				teclado.setDcto(dcto);
				teclado.setPrime(prime);
				teclado.setColor(color);
				teclado.setTeclas(teclas);
				teclado.setConector(conector);

				System.out.println(teclado.toString());
			} else {
				return teclado = null;
			}

		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		}
		return teclado;
	}

	public static Teclado primerItem() throws Exception {
		Teclado teclado = new Teclado();

		try{
			Conexion conexion = new Conexion();
			conexion.conectarMySQL();
			Statement sql = null;
			ResultSet rs = null;
			
			sql = conexion.conectarMySQL().createStatement();
			System.out.println("Conexión establecida");
			rs = sql.executeQuery("select * from instrumentos");
			
			boolean r = rs.next();

			while (r) {

				String id = rs.getString("codigo");
				String marca = rs.getString("marca");
				Double precio = rs.getDouble("precio");
				Double dcto = rs.getDouble("descuento");
				String tipo = rs.getString("tipo");
				Boolean prime;
				String color = rs.getString("color");
				int teclas = rs.getInt("teclas");
				String conector = rs.getString("conector");
				String envio = rs.getString("envio");
				String pvp = rs.getString("pvp");

				if (tipo.equals("PRIME")) {
					prime = true;
				} else {
					prime = false;
				}

				teclado.setId(id);
				teclado.setMarca(marca);
				teclado.setPrecio(precio);
				teclado.setDcto(dcto);
				teclado.setPrime(prime);
				teclado.setColor(color);
				teclado.setTeclas(teclas);
				teclado.setConector(conector);

				System.out.println(teclado.toString());
				r = false;
				// rs.next();
			}
		// }
			conexion.conectarMySQL().close();
			System.out.println("\nCerrando la conexión");

		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		}
		return teclado;
	}

}

