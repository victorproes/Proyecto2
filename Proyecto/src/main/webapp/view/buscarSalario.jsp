  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<div class="container">
	<h1 class="mt-5">Buscar Salario de Empleado</h1>

	<% String mensaje = (String) request.getAttribute("mensaje"); %>
	<div id="mensaje-error" class="alert alert-danger mt-3"
		style="<% if (mensaje == null) { %>display: none;<% } %>">
		<strong>Error:</strong>
		<%= mensaje %>
	</div>

	<!-- Formulario para buscar salario -->
	<form class="mt-4" action="EmpresaServlet" method="post">
		<div class="form-group">
		      <input type="hidden" name="opcion" value="buscar">
		
			<label for="dni">DNI del Empleado:</label> <input type="text"
				class="form-control" name="dni" id="dni">
		</div>
		<button type="submit" class="btn btn-primary">Buscar Salario</button>
	</form>

	<button class="back-button" onclick="goBack()">Volver</button>
</div>

<script>
    function goBack() {
        window.history.back();
    }

    // Ocultar el mensaje de error después de 3 segundos
    var mensajeError = document.getElementById("mensaje-error");
    if (mensajeError.style.display !== "none") {
        setTimeout(function() {
            mensajeError.style.display = "none";
        }, 3000); // 3000 milisegundos (3 segundos)
    }
</script>


