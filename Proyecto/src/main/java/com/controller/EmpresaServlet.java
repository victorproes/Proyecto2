package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.NominaDAO;
import com.dao.UserDAO;
import com.model.DatosNoCorrectoExcpetion;
import com.model.Empleado;
import com.model.Nomina;


/**
 * Servlet implementation class EmpresaServlet
 */
@WebServlet("/EmpresaServlet")
public class EmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public void init() throws ServletException {
		super.init();
		userDAO = new UserDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpresaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String opcion = request.getParameter("opcion");

	    if (opcion != null) {
	        if ("crear".equals(opcion)) {
	            request.setAttribute("content", "/view/crear.jsp");
	        } else if ("listar".equals(opcion)) {
	            try {
	                NominaDAO nominaDAO = new NominaDAO();
	                List<Empleado> empleados = nominaDAO.obtenerEmpleadosDesdeBaseDeDatos();

	                // Filtra los empleados que tienen estado "inactivo"
	                empleados.removeIf(empleado -> "inactivo".equalsIgnoreCase(empleado.getEstado()));

	                // Guarda la lista de empleados en la sesión 
	                HttpSession session = request.getSession();
	                session.setAttribute("empleados", empleados);

	                request.setAttribute("content", "/view/nomina.jsp");
	            } catch (DatosNoCorrectoExcpetion e) {
	                e.printStackTrace(); // Maneja la excepción
	            }
	        } else if ("buscarSalario".equals(opcion)) {
	            request.setAttribute("content", "/view/buscarSalario.jsp");
	        } else if ("editar".equals(opcion)) {
	            request.setAttribute("content", "/view/editar.jsp");
	        } else if ("buscarEmpleados".equals(opcion)) {
	            request.setAttribute("content", "/view/buscarEmpleados.jsp");
	        } else if ("login".equals(opcion)) {
	            // Verificar si el usuario ya está logeado
	            if (request.getSession().getAttribute("username") != null) {
	                // El usuario ya está logeado,  mostrar un mensaje.
	                request.setAttribute("message", "Ya ha iniciado sesión.");
	                request.getRequestDispatcher("/view/principal.jsp").forward(request, response);
	                return;
	            }
	            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
	        } else if ("registro".equals(opcion)) {
	            // Verificar si el usuario ya está logeado
	            if (request.getSession().getAttribute("username") != null) {
	                // El usuario ya está logeado, redirigir  mostrar un mensaje.
	                request.setAttribute("message", "Ya ha iniciado sesión.");
	                request.getRequestDispatcher("/view/principal.jsp").forward(request, response);
	                return;
	            }
	            request.getRequestDispatcher("/view/registro.jsp").forward(request, response);
	        } else if ("logout".equals(opcion)) {
	            // Cierra la sesión y redirige al índice
	            HttpSession session = request.getSession();
	            session.invalidate();

	            // Redirige al índice
	            response.sendRedirect(request.getContextPath() + "/index.jsp");
	            return;
	        } else if ("eliminar".equals(opcion)) {
	            try {
	                String dniEmpleadoAEliminar = request.getParameter("dni");

	                // Obtén la sesión desde la solicitud
	                HttpSession session = request.getSession();

	                // Obtén la lista actual de empleados desde la sesión
	                List<Empleado> empleados = (List<Empleado>) session.getAttribute("empleados");

	                // Actualiza el estado del empleado a "inactivo" en la base de datos
	                NominaDAO nominaDAO = new NominaDAO();
	                nominaDAO.actualizarEstadoEmpleado(dniEmpleadoAEliminar, "inactivo");

	                // Filtra la lista para eliminar el empleado marcado como "inactivo"
	                empleados.removeIf(empleado -> dniEmpleadoAEliminar.equals(empleado.getDni()));

	                // Actualiza la lista en la sesión
	                session.setAttribute("empleados", empleados);

	                request.setAttribute("content", "/view/nomina.jsp");
	            } catch (DatosNoCorrectoExcpetion e) {
	                // Manejar la excepción 
	                e.printStackTrace();
	            }
	        } else {
	            // Si no se selecciona ninguna opción, muestra una página de inicio por defecto
	            request.setAttribute("content", "/view/inicio.jsp");
	        }
	    }

	    // Configura la respuesta para redirigir a la página principal
	    request.getRequestDispatcher("/view/principal.jsp").forward(request, response);
	}


	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if (opcion.equals("guardar")) {
			try {
				String dni = request.getParameter("dni");
				String nombre = request.getParameter("nombre");
				String sexo = request.getParameter("sexo");
				int categoria = Integer.parseInt(request.getParameter("categoria"));
				int anyos = Integer.parseInt(request.getParameter("anyos"));

				// Realizar las validaciones
				if (NominaDAO.isValidDNI(dni) && NominaDAO.isValidSexo(sexo) && NominaDAO.isValidCategoria(categoria) && NominaDAO.isValidAnyos(anyos)) {
					Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos);
					NominaDAO nominaDAO = new NominaDAO();
					nominaDAO.crearEmpleado(empleado);
					// Redirige a la vista JSP para mostrar el resultado
					System.out.println("Registro guardado satisfactoriamente...");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("menu/menu.jsp");
					requestDispatcher.forward(request, response);
				} else {
					response.getWriter().println("Error: Los datos no son correctos.");

				}

			} catch (DatosNoCorrectoExcpetion e) {
				// Manejar la excepción aquí
				e.printStackTrace();
				response.getWriter().println("Error: Los datos no son correctos.");
			} catch (Exception ex) {
				ex.printStackTrace();
				// Manejar otras excepciones generales si es necesario.
			}
		} else if (opcion.equals("buscar")) {
		    String dni = request.getParameter("dni");

		    try {
		        NominaDAO nominaDAO = new NominaDAO();
		        Double salario = nominaDAO.obtenerSalarioEmpleado(dni);

		        if (salario != null) {
		            request.setAttribute("salario", salario);
		            // Mantén el contenido en la misma página
		            request.setAttribute("content", "/view/mostrarSalario.jsp");
		        } else {
		            // Indica que el DNI no tiene salario registrado
		            request.setAttribute("mensaje",
		                    "El DNI proporcionado no tiene salario registrado o no se encuentra en la base de datos.");
		            // Mantén el contenido en la misma página
		            request.setAttribute("content", "/view/buscarSalario.jsp");
		        }
		    } catch (DatosNoCorrectoExcpetion e) {
		        // Maneja la excepción
		        e.printStackTrace();
		    }

		    request.getRequestDispatcher("/view/principal.jsp").forward(request, response);
		}

 else if (opcion.equals("editar")) {
			try {
				String dni = request.getParameter("dni");
				String nombre = request.getParameter("nombre");
				int categoria = Integer.parseInt(request.getParameter("categoria"));
				int anyos = Integer.parseInt(request.getParameter("anyos"));
				String sexo = request.getParameter("sexo");

				// Actualizar los datos del empleado en la base de datos
				NominaDAO nominaDAO = new NominaDAO();
				Empleado empleado = new Empleado(dni, nombre, sexo, categoria, anyos); 
				nominaDAO.modificarEmpleado(empleado);

				// Cargar la lista actualizada de empleados
				List<Empleado> empleados = nominaDAO.obtenerEmpleadosDesdeBaseDeDatos();

				// Guardar la lista de empleados en el atributo de la solicitud
				request.setAttribute("empleados", empleados);

				// Redirigir a la página de resultados (nomina.jsp) para mostrar la lista
				// actualizada de empleados
				request.getRequestDispatcher("/view/nomina.jsp").forward(request, response);
			} catch (DatosNoCorrectoExcpetion e) {
				// Manejar la excepción si se lanza
				e.printStackTrace(); // O muestra un mensaje de error
				request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			}
		} else if (opcion.equals("buscarEmpleado")) {
		    // Obtiene los parámetros del formulario de búsqueda
		    String criterio = request.getParameter("criterio");
		    String valor = request.getParameter("valor");
		    String errorMsg = null; // Variable para almacenar mensajes de error
		    String previousPage = "/view/buscarEmpleados.jsp"; // Página anterior

		    try {
		        // Crea una instancia de NominaDAO
		        NominaDAO nominaDAO = new NominaDAO();

		        // Realiza validaciones específicas
		        if ("anyos".equals(criterio)) {
		            // Verifica que "valor" sea un número entero no negativo
		            int anyos = Integer.parseInt(valor);
		            if (anyos < 0) {
		                errorMsg = "Años no puede ser negativo";
		            }
		        } else if ("dni".equals(criterio)) {
		            // Verifica que "valor" sea un DNI válido
		            if (!valor.matches("[0-9]{8}[A-Z]")) {
		                errorMsg = "DNI inválido, El DNI debe ser 8 digitos y una letra en mayuscula";
		            }
		        } else if ("sexo".equals(criterio)) {
		            // Verifica que "valor" sea "M", "O" o "F"
		            if (!valor.equals("M") && !valor.equals("F") && !valor.equals("O")) {
		                errorMsg = "Sexo debe ser M, F o O";
		            }
		        }

		        // Si se encontró un error, almacena el mensaje y la página anterior
		        if (errorMsg != null) {
		            request.setAttribute("errorMsg", errorMsg);
		            request.getRequestDispatcher(previousPage).forward(request, response);
		            return; // Termina la ejecución del método
		        }

		        // Realiza la búsqueda en la base de datos según el criterio y valor
		        List<Empleado> empleados = null;
		        if ("dni".equals(criterio)) {
		            // Buscar por DNI
		            empleados = nominaDAO.buscarEmpleadosPorDNI(valor);
		        } else if ("nombre".equals(criterio)) {
		            // Buscar por Nombre
		            empleados = nominaDAO.buscarEmpleadosPorNombre(valor);
		        } else if ("categoria".equals(criterio)) {
		            // Buscar por Categoría
		            empleados = nominaDAO.buscarEmpleadosPorCategoria(Integer.parseInt(valor));
		        } else if ("anyos".equals(criterio)) {
		            // Buscar por Años
		            empleados = nominaDAO.buscarEmpleadosPorAnyos(Integer.parseInt(valor));
		        } else if ("sexo".equals(criterio)) {
		            // Buscar por Sexo
		            empleados = nominaDAO.buscarEmpleadosPorSexo(valor);
		        }

		        // Establece los resultados de la búsqueda como un atributo para la vista
		        request.setAttribute("empleados", empleados);

		      

		    } catch (DatosNoCorrectoExcpetion e) {
		        e.printStackTrace(); // Maneja la excepción
		    }

		
		    request.getRequestDispatcher("/view/nomina.jsp").forward(request, response);
		}
 else if (opcion.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (userDAO.validarCredenciales(username, password)) {
				// Verificar si el usuario ya está logeado
				if (request.getSession().getAttribute("username") != null) {
					// El usuario ya está logeado,  mostrar un
					// mensaje.
					request.setAttribute("message", "Ya ha iniciado sesión.");
					request.getRequestDispatcher("/view/principal.jsp").forward(request, response);
				} else {
					// El usuario está registrado y las credenciales son válidas
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					
					request.getRequestDispatcher("/view/principal.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("message", "Credenciales incorrectas. Por favor, inténtelo de nuevo.");
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}
		} else if (opcion.equals("registro")) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			if (userDAO.isUserExists(username)) {
				request.setAttribute("message", "El usuario ya existe. Por favor, elija otro nombre de usuario.");
				request.getRequestDispatcher("/view/registro.jsp").forward(request, response);
			} else {
				userDAO.registrarUsuario(username, password);
				request.setAttribute("message", "Registro exitoso. Ahora puede iniciar sesión.");
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}


		}
	}
}