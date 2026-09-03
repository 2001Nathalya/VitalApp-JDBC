<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Signos Vitales - VitalApp</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f4f6f8;
        }

        header {
            background: linear-gradient(90deg, #2D4EF7, #EA5459);
            color: white;
            padding: 25px;
            text-align: center;
        }

        main {
            width: 95%;
            margin: 30px auto;
        }

        h2 {
            color: #333;
        }

        .nuevo {
            display: inline-block;
            background-color: #2D4EF7;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #2D4EF7;
            color: white;
        }

        .editar {
            color: #2D4EF7;
            text-decoration: none;
            margin-right: 10px;
        }

        .eliminar {
            color: #EA5459;
            text-decoration: none;
        }

        .sin-datos {
            text-align: center;
            padding: 20px;
            color: #777;
        }
    </style>
</head>

<body>

<header>
    <h1>VitalApp</h1>
    <p>Registro y Monitoreo de Signos Vitales</p>
</header>

<main>

    <h2>Gestión de Signos Vitales</h2>

    <a class="nuevo"
       href="${pageContext.request.contextPath}/signos-vitales?accion=nuevo">
        + Registrar Signo Vital
    </a>

    <table>

        <thead>
            <tr>
                <th>ID Signo</th>
                <th>ID Paciente</th>
                <th>Fecha de Registro</th>
                <th>Frecuencia Cardíaca</th>
                <th>Frecuencia Respiratoria</th>
                <th>Presión Arterial</th>
                <th>Temperatura</th>
                <th>Saturación O₂</th>
                <th>Glucemia</th>
                <th>Acciones</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach var="signo"
                       items="${signosVitales}">

                <tr>

                    <td>${signo.idSigno}</td>

                    <td>${signo.idPaciente}</td>

                    <td>${signo.fechaRegistro}</td>

                    <td>${signo.frecuenciaCardiaca}</td>

                    <td>${signo.frecuenciaRespiratoria}</td>

                    <td>${signo.presionArterial}</td>

                    <td>${signo.temperatura}</td>

                    <td>${signo.saturacionOxigeno}</td>

                    <td>${signo.glucemia}</td>

                    <td>

                        <a class="editar"
                           href="${pageContext.request.contextPath}/signos-vitales?accion=editar&id=${signo.idSigno}">
                            Editar
                        </a>

                        <a class="eliminar"
                           href="${pageContext.request.contextPath}/signos-vitales?accion=eliminar&id=${signo.idSigno}"
                           onclick="return confirm('¿Está seguro de eliminar este signo vital?');">
                            Eliminar
                        </a>

                    </td>

                </tr>

            </c:forEach>

            <c:if test="${empty signosVitales}">
                <tr>
                    <td colspan="10" class="sin-datos">
                        No hay signos vitales registrados.
                    </td>
                </tr>
            </c:if>

        </tbody>

    </table>

</main>

</body>
</html>