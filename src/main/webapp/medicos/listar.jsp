<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Gestión de Médicos - VitalApp</title>

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
            width: 90%;
            max-width: 1100px;
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
            margin-bottom: 20px;
            background-color: #2D4EF7;
            color: white;
            text-decoration: none;
            border-radius: 5px;
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
            background-color: #f0f0f0;
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

    <p>Gestión de Médicos</p>

</header>

<main>

    <h2>Lista de Médicos</h2>

    <a class="boton"
       href="${pageContext.request.contextPath}/medicos?accion=nuevo">

        + Registrar Médico

    </a>

    <table>

        <thead>

        <tr>

            <th>ID Médico</th>
            <th>ID Usuario</th>
            <th>Especialidad</th>
            <th>Acciones</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach var="medico" items="${medicos}">

            <tr>

                <td>
                    ${medico.idMedico}
                </td>

                <td>
                    ${medico.idUsuario}
                </td>

                <td>
                    ${medico.especialidad}
                </td>

                <td>

                    <a class="editar"
                       href="${pageContext.request.contextPath}/medicos?accion=editar&id=${medico.idMedico}">
                        Editar
                    </a>

                    &nbsp; | &nbsp;

                    <a class="eliminar"
                       href="${pageContext.request.contextPath}/medicos?accion=eliminar&id=${medico.idMedico}"
                       onclick="return confirm('¿Está seguro de eliminar este médico?');">
                        Eliminar
                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</main>

</body>

</html>