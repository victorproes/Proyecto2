  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <h2>Iniciar Sesi�n</h2>
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
    <form action="EmpresaServlet" method="post">
     <input type="hidden" name="opcion" value="login"> 
        Nombre de Usuario: <input type="text" name="username"><br>
        Contrase�a: <input type="password" name="password"><br>
        <input type="submit" value="Iniciar Sesi�n">
    </form>
    <p><a href="registro.jsp">Registrarse</a></p>
