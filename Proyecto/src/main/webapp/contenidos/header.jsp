  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

  <header class="bg-secondary text-white py-4">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h1>Nombre de la Empresa</h1>
                </div>
                <div class="col-md-6 text-md-right">
                    <ul class="list-unstyled">
                        <c:if test="${empty sessionScope.username}">
                            <li class="d-inline-block mr-3"><a href="EmpresaServlet?opcion=login" class="text-white">Iniciar Sesión</a></li>
                            <li class="d-inline-block"><a href="EmpresaServlet?opcion=registro" class="text-white">Registrarse</a></li>
                        </c:if>
                        <c:if test="${not empty sessionScope.username}">
                            <li class="d-inline-block"><a href="EmpresaServlet?opcion=logout" class="text-white">Cerrar Sesión</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </header>