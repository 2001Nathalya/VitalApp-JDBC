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
            max-width: 600px;
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

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
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

    <p>Gestión de Médicos</p>

</header>

<main>

    <h2>${titulo}</h2>

    <form action="${pageContext.request.contextPath}/medicos"
          method="post">

        <input type="hidden"
               name="accion"
               value="${accion}">

        <c:if test="${not empty medico}">
            <input type="hidden"
                   name="idMedico"
                   value="${medico.idMedico}">
        </c:if>

        <label for="idUsuario">
            ID Usuario
        </label>

        <input type="number"
               id="idUsuario"
               name="idUsuario"
               value="${medico.idUsuario}"
               required>

        <label for="especialidad">
            Especialidad
        </label>

        <input type="text"
               id="especialidad"
               name="especialidad"
               value="${medico.especialidad}"
               maxlength="100"
               required>

        <div class="botones">

            <button type="submit" class="guardar">
                Guardar
            </button>

            <a class="cancelar"
               href="${pageContext.request.contextPath}/medicos">
                Cancelar
            </a>

        </div>

    </form>

</main>

</body>

</html>