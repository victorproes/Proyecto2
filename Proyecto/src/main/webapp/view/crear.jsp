
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  
 <style>
        body {
            margin-bottom: 100px; /* Ajusta el valor según sea necesario para dar espacio al footer */
        }

        .form-container {
            max-width: 400px;
            margin: 0 auto;
            margin-top: 50px;
        }
    </style>
<div class="container mt-5">
        <h1 class="text-center">Formulario de Empleado</h1>
        <form class="mx-auto" style="max-width: 400px;" action="EmpresaServlet" method="post">
            <input type="hidden" name="opcion" value="guardar">
            <div class="form-group">
                <label for="dni">DNI:</label>
                <input type="text" class="form-control" name="dni" required>
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="sexo">Sexo:</label>
                <input type="text" class="form-control" name="sexo" required>
            </div>
            <div class="form-group">
                <label for="categoria">Categoría:</label>
                <input type="text" class="form-control" name="categoria" required>
            </div>
            <div class="form-group">
                <label for="anyos">Años:</label>
                <input type="text" class="form-control" name="anyos" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Crear Empleado</button>
        </form>
    </div>