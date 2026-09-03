<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Gestión de Pacientes - VitalApp</title>

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

    <p>Gestión de Pacientes</p>

</header>

<main>

    <h2>Lista de Pacientes</h2>

    <a class="boton"
       href="${pageContext.request.contextPath}/pacientes?accion=nuevo">

        + Registrar Paciente

    </a>

    <table>

        <thead>

        <tr>

            <th>ID Paciente</th>
            <th>ID Usuario</th>
            <th>Fecha de nacimiento</th>
            <th>Sexo</th>
            <th>Acciones</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach var="paciente" items="${pacientes}">

            <tr>

                <td>
                    ${paciente.idPaciente}
                </td>

                <td>
                    ${paciente.idUsuario}
                </td>

                <td>
                    ${paciente.fechaNacimiento}
                </td>

                <td>
                    ${paciente.sexo}
                </td>

                <td>

                    <a class="editar"
                       href="${pageContext.request.contextPath}/pacientes?accion=editar&id=${paciente.idPaciente}">
                        Editar
                    </a>

                    &nbsp; | &nbsp;

                    <a class="eliminar"
                       href="${pageContext.request.contextPath}/pacientes?accion=eliminar&id=${paciente.idPaciente}"
                       onclick="return confirm('¿Está seguro de eliminar este paciente?');">
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