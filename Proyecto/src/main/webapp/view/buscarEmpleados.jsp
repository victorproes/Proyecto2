  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<script>
        // Función para ocultar el mensaje de error después de 3 segundos
        function hideErrorMessage() {
            var errorMessage = document.getElementById("error-message");
            if (errorMessage) {
                setTimeout(function () {
                    errorMessage.style.display = "none";
                }, 3000);
            }
        }

        // Llama a la función al cargar la página
        window.onload = hideErrorMessage;
    </script>

	<div class="container">
		<h1 class="mt-5">Buscar Empleados</h1>

		<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
		<% if (errorMsg != null) { %>
		<div class="alert alert-danger mt-3" id="error-message">
			<strong>Error:</strong>
			<%= errorMsg %>
		</div>
		<% } %>

		<form class="mt-4" action="EmpresaServlet" method="post">
			<div class="form-group">
			      <input type="hidden" name="opcion" value="buscarEmpleado">
			
				<label for="criterio">Criterio de Búsqueda:</label> <select
					class="form-control" name="criterio" id="criterio">
					<option value="dni">DNI</option>
					<option value="nombre">Nombre</option>
					<option value="categoria">Categoría</option>
					<option value="anyos">Años</option>
					<option value="sexo">Sexo</option>
				</select>
			</div>
			<div class="form-group">
				<label for="valor">Valor de Búsqueda:</label> <input type="text"
					class="form-control" name="valor" id="valor">
			</div>
			<button type="submit" class="btn btn-primary">Buscar
				Empleado</button>
		</form>
		<button class="back-button" onclick="goBack()">Volver</button>

		<script>
            function goBack() {
                window.history.back();
            }
        </script>
	</div>


