<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Gestión de Alertas - VitalApp</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
        }

        header {
            background: linear-gradient(90deg, #2D4EF7, #EA5459);
            color: white;
            padding: 25px;
            text-align: center;
        }

        main {
            width: 95%;
            max-width: 1200px;
            margin: 35px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h2 {
            color: #333;
        }

        .boton {
            display: inline-block;
            padding: 10px 18px;
            background-color: #2D4EF7;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f0f2f5;
        }

        .editar {
            color: #2D4EF7;
            text-decoration: none;
        }

        .eliminar {
            color: #EA5459;
            text-decoration: none;
        }

    </style>

</head>

<body>

<header>

    <h1>VitalApp</h1>

    <p>Gestión de Alertas</p>

</header>

<main>

    <h2>Lista de Alertas</h2>

    <a class="boton"
       href="${pageContext.request.contextPath}/alertas?accion=nuevo">
        + Registrar Alerta
    </a>

    <table>

        <thead>

            <tr>

                <th>ID Alerta</th>

                <th>ID Paciente</th>

                <th>Tipo de Alerta</th>

                <th>Descripción</th>

                <th>Fecha de Alerta</th>

                <th>Acciones</th>

            </tr>

        </thead>

        <tbody>

            <c:forEach var="alerta" items="${alertas}">

                <tr>

                    <td>${alerta.idAlerta}</td>

                    <td>${alerta.idPaciente}</td>

                    <td>${alerta.tipoAlerta}</td>

                    <td>${alerta.descripcion}</td>

                    <td>${alerta.fechaAlerta}</td>

                    <td>

                        <a class="editar"
                           href="${pageContext.request.contextPath}/alertas?accion=editar&id=${alerta.idAlerta}">
                            Editar
                        </a>

                        &nbsp; | &nbsp;

                        <a class="eliminar"
                           href="${pageContext.request.contextPath}/alertas?accion=eliminar&id=${alerta.idAlerta}"
                           onclick="return confirm('¿Está seguro de eliminar esta alerta?');">
                            Eliminar
                        </a>

                    </td>

                </tr>

            </c:forEach>

            <c:if test="${empty alertas}">

                <tr>

                    <td colspan="6">
                        No hay alertas registradas.
                    </td>

                </tr>

            </c:if>

        </tbody>

    </table>

</main>

</body>

</html>