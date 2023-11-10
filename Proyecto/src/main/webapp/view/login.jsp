  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <h2>Iniciar Sesión</h2>
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
    <form action="EmpresaServlet" method="post">
     <input type="hidden" name="opcion" value="login"> 
        Nombre de Usuario: <input type="text" name="username"><br>
        Contraseña: <input type="password" name="password"><br>
        <input type="submit" value="Iniciar Sesión">
    </form>
    <p><a href="registro.jsp">Registrarse</a></p>
