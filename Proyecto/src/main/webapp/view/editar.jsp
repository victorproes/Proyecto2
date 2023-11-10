  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<div class="container">
    <h1 class="mt-5">Editar Empleado</h1>
    <form action="EmpresaServlet" method="post">
        <input type="hidden" name="opcion" value="editar"> <!-- Esta línea agrega la opción "editar" -->

       
        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" value="${empleado.nombre}" class="form-control">
        </div>
        <div class="form-group">
            <label for="sexo">Sexo:</label>
            <input type="text" name="sexo" value="${empleado.sexo}" class="form-control">
        </div>
        <div class="form-group">
            <label for="categoria">Categoría:</label>
            <input type="number" name="categoria" value="${empleado.categoria}" class="form-control">
        </div>
        <div class="form-group">
            <label for="anyos">Años Trabajados:</label>
            <input type="number" name="anyos" value="${empleado.anyos}" class="form-control">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </div>
    </form>
    <button class="back-button" onclick="goBack()">Volver</button>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</div>


