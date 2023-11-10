  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<div class="container result-container">
		<h1 class="mt-5">Salario del Empleado</h1>

		<c:if test="${not empty salario}">
			<p>El salario del empleado es: ${salario}</p>
		</c:if>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>

		<button class="back-button" onclick="goBack()">Volver</button>

		<script>
			function goBack() {
				window.history.back();
			}
		</script>

	</div>



