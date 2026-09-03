<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>${titulo} - VitalApp</title>

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
            max-width: 650px;
            margin: 35px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h2 {
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input,
        textarea {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        textarea {
            min-height: 120px;
            resize: vertical;
        }

        .botones {
            margin-top: 25px;
        }

        .guardar {
            padding: 10px 18px;
            background-color: #2D4EF7;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .cancelar {
            display: inline-block;
            padding: 10px 18px;
            margin-left: 10px;
            background-color: #EA5459;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

    </style>

</head>

<body>

<header>

    <h1>VitalApp</h1>

    <p>Gestión de Reportes</p>

</header>

<main>

    <h2>${titulo}</h2>

    <form action="${pageContext.request.contextPath}/reportes"
          method="post">

        <input type="hidden"
               name="accion"
               value="${accion}">

        <c:if test="${not empty reporte}">

            <input type="hidden"
                   name="idReporte"
                   value="${reporte.idReporte}">

        </c:if>

        <label for="idPaciente">
            ID Paciente
        </label>

        <input type="number"
               id="idPaciente"
               name="idPaciente"
               value="${reporte.idPaciente}"
               min="1"
               required>

        <label for="fechaGeneracion">
            Fecha y hora de generación
        </label>

        <input type="datetime-local"
               id="fechaGeneracion"
               name="fechaGeneracion"
               value="${reporte.fechaGeneracion}"
               required>

        <label for="descripcion">
            Descripción
        </label>

        <textarea id="descripcion"
                  name="descripcion"
                  maxlength="500"
                  required>${reporte.descripcion}</textarea>

        <div class="botones">

            <button type="submit"
                    class="guardar">

                Guardar

            </button>

            <a class="cancelar"
               href="${pageContext.request.contextPath}/reportes">

                Cancelar

            </a>

        </div>

    </form>

</main>

</body>

</html>