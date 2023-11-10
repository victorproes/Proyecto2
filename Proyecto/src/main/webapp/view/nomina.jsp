<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nómina de Empleados</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">


   <style>
        body {
            padding-bottom: 70px; /* Ajusta este valor según la altura de tu footer */
        }
    </style>
</head>

<body class="bg-light">


    <div class="container mt-3">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Sexo</th>
                    <th>Categoría</th>
                    <th>Años Trabajados</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${empleados}" var="empleado">
                  <c:if test="${!empleado.estado.equals('inactivo')}">
                    <tr>
                        <td>${empleado.dni}</td>
                        <td>${empleado.nombre}</td>
                        <td>${empleado.sexo}</td>
                        <td>${empleado.categoria}</td>
                        <td>${empleado.anyos}</td>
                        <td>
                            <a href="EmpresaServlet?opcion=editar&dni=${empleado.dni}" class="btn btn-primary custom-button">Editar</a>
                            <a href="EmpresaServlet?opcion=eliminar&dni=${empleado.dni}" class="btn btn-danger custom-button">Eliminar</a>
                        </td>
                    </tr>
                     </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <button class="btn btn-secondary fixed-bottom mb-4" onclick="goBack()">Volver</button>


    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
