package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.conexion.Conexion;
import com.model.DatosNoCorrectoExcpetion;
import com.model.Empleado;
import com.model.Nomina;

public class NominaDAO {
	public void actualizarEstadoEmpleado(String dni, String nuevoEstado) throws DatosNoCorrectoExcpetion {
        String sql = "UPDATE empleados SET estado = ? WHERE dni = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, nuevoEstado);
            preparedStatement.setString(2, dni);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas != 1) {
                throw new DatosNoCorrectoExcpetion("No se pudo actualizar el estado del empleado con DNI: " + dni);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción específica de SQL según tu necesidad
            throw new DatosNoCorrectoExcpetion("Error al actualizar el estado del empleado con DNI: " + dni);
        }
    }

	// Funciones de validación
	public static boolean isValidDNI(String dni) {
	    // Verifica que el DNI tenga 8 dígitos seguidos de una letra en mayúscula
	    return dni.matches("\\d{8}[A-Z]");
	}

	public static boolean isValidSexo(String sexo) {
	    // Verifica que el sexo sea 'M' o 'F'
	    return sexo.equals("M") || sexo.equals("F");
	}

	public static boolean isValidCategoria(int categoria) {
	    // Verifica que la categoría no sea negativa
	    return categoria >= 0;
	}

	public static boolean isValidAnyos(int anyos) {
	    // Verifica que los años no sean negativos
	    return anyos >= 0;
	}

	
	public List<Empleado> buscarEmpleadosPorDNI(String dni) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();

	    try (Connection conexion = Conexion.getConnection()) {
	        String consulta = "SELECT nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, dni);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por DNI");
	    }

	    return empleados;
	}

	
	
	public List<Empleado> buscarEmpleadosPorNombre(String nombre) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();

	    try (Connection conexion = Conexion.getConnection()) {
	        String consulta = "SELECT dni, sexo, categoria, anyos FROM empleados WHERE nombre = ?";
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, nombre);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por nombre");
	    }

	    return empleados;
	}


	public List<Empleado> buscarEmpleadosPorCategoria(int categoria) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();

	    try (Connection conexion = Conexion.getConnection()) {
	        String consulta = "SELECT dni, nombre, sexo, anyos FROM empleados WHERE categoria = ?";
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setInt(1, categoria);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int anyos = resultSet.getInt("anyos");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por categoría");
	    }

	    return empleados;
	}

	
	public List<Empleado> buscarEmpleadosPorAnyos(int anyos) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();

	    try (Connection conexion = Conexion.getConnection()) {
	        String consulta = "SELECT dni, nombre, sexo, categoria FROM empleados WHERE anyos = ?";
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setInt(1, anyos);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por años");
	    }

	    return empleados;
	}

	
	public List<Empleado> buscarEmpleadosPorSexo(String sexo) throws DatosNoCorrectoExcpetion {
	    List<Empleado> empleados = new ArrayList<>();

	    try (Connection conexion = Conexion.getConnection()) {
	        String consulta = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE sexo = ?";
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
	            preparedStatement.setString(1, sexo);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    String dni = resultSet.getString("dni");
	                    String nombre = resultSet.getString("nombre");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
	                    empleados.add(empleado);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al buscar empleados por sexo");
	    }

	    return empleados;
	}

	
	public Empleado recuperarDatosEmpleadoPorDNI(String dni) throws DatosNoCorrectoExcpetion {
	    try (Connection conn = Conexion.getConnection()) {
	        String consulta = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";

	        try (PreparedStatement preparedStatement = conn.prepareStatement(consulta)) {
	            preparedStatement.setString(1, dni);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    String nombre = resultSet.getString("nombre");
	                    String sexo = resultSet.getString("sexo");
	                    int categoria = resultSet.getInt("categoria");
	                    int anyos = resultSet.getInt("anyos");

	                    return new Empleado(dni, nombre, sexo, categoria, anyos);
	                } else {
	                    throw new DatosNoCorrectoExcpetion("No se encontró el empleado con el DNI proporcionado.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al recuperar los datos del empleado desde la base de datos");
	    }
	}
	
	public static void modificarEmpleado(Empleado empleado) throws DatosNoCorrectoExcpetion {
	    try (Connection conn = Conexion.getConnection()) {
	        // Inicia una transacción ya que realizarás múltiples actualizaciones
	        conn.setAutoCommit(false);

	        // Primero, actualiza los datos del empleado en la tabla "empleados"
	        String sqlUpdateEmpleado = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
	        try (PreparedStatement stmtUpdateEmpleado = conn.prepareStatement(sqlUpdateEmpleado)) {
	            stmtUpdateEmpleado.setString(1, empleado.getNombre());
	            stmtUpdateEmpleado.setString(2, empleado.getSexo());
	            stmtUpdateEmpleado.setInt(3, empleado.getCategoria());
	            stmtUpdateEmpleado.setInt(4, empleado.getAnyos());
	            stmtUpdateEmpleado.setString(5, empleado.getDni());
	            stmtUpdateEmpleado.executeUpdate();
	        }

	        // Luego, actualiza el sueldo en la tabla "nominas" basado en la nueva categoría y años trabajados
	        String sqlUpdateSueldo = "UPDATE nomina SET sueldo = ? WHERE id_empleado= (SELECT dni FROM empleados WHERE dni = ?)";
	        try (PreparedStatement stmtUpdateSueldo = conn.prepareStatement(sqlUpdateSueldo)) {
	            int nuevoSueldo = Nomina.sueldo(empleado); // Utiliza el método sueldo de la clase Nomina
	            stmtUpdateSueldo.setInt(1, nuevoSueldo);
	            stmtUpdateSueldo.setString(2, empleado.getDni());
	            stmtUpdateSueldo.executeUpdate();
	        }

	        // Realiza el commit para confirmar las actualizaciones
	        conn.commit();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DatosNoCorrectoExcpetion("Error al modificar los datos del empleado en la base de datos");
	    }
	}

	
	public Double obtenerSalarioEmpleado(String dni) throws DatosNoCorrectoExcpetion {
        try (Connection conexion = Conexion.getConnection()) {
            String consulta = "SELECT sueldo FROM nomina WHERE id_empleado = (SELECT dni FROM empleados WHERE dni = ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
                preparedStatement.setString(1, dni);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Si se encontró un salario en la base de datos, regresamos el valor
                        double salario = resultSet.getDouble("sueldo");
                        return salario;
                    } else {
                        // Si no se encontró un salario, regresamos null
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatosNoCorrectoExcpetion("Error al obtener el salario del empleado desde la base de datos");
        }
    }
	
	public List<Empleado> obtenerEmpleadosDesdeBaseDeDatos() throws DatosNoCorrectoExcpetion {
        List<Empleado> listaEmpleados = new ArrayList<>();
        String selectEmpleadosSQL = "SELECT * FROM empleados";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(selectEmpleadosSQL);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre");
                String sexo = resultSet.getString("sexo");
                int categoria = resultSet.getInt("categoria");
                int anyos = resultSet.getInt("anyos");

                Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción de SQL
            throw new DatosNoCorrectoExcpetion("Error al obtener empleados desde la base de datos");
        }

        return listaEmpleados;
    }
	
	public void crearEmpleado(Empleado empleado) {
        Connection connection = null;
        
        try {
            connection = Conexion.getConnection();
            
            // Insertar el empleado en la tabla "empleados"
            String insertEmpleadoSQL = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = connection.prepareStatement(insertEmpleadoSQL)) {
                pstmt.setString(1, empleado.getDni());
                pstmt.setString(2, empleado.getNombre());
                pstmt.setString(3, empleado.getSexo());
                pstmt.setInt(4, empleado.getCategoria());
                pstmt.setInt(5, empleado.getAnyos());
                
                pstmt.executeUpdate();
            }
            
            // Calcular el sueldo usando la clase Nomina
            int sueldo = Nomina.sueldo(empleado);

            // Insertar el sueldo en la tabla "nomina"
            String insertSueldoSQL = "INSERT INTO nomina (id_empleado, sueldo) VALUES (?, ?)";
            
            try (PreparedStatement pstmt = connection.prepareStatement(insertSueldoSQL)) {
                pstmt.setString(1, empleado.getDni());
                pstmt.setInt(2, sueldo);
                
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción de SQL
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar la excepción de SQL
            }
        }
    }
}
