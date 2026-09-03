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
            padding: 30px;
            border-radius: 10px;
        }

        h2 {
            color: #333;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .boton {
            margin-top: 25px;
            padding: 11px 20px;
            background-color: #2D4EF7;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .cancelar {
            display: inline-block;
            margin-left: 10px;
            padding: 10px 18px;
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

    <h2>${titulo}</h2>

    <form action="${pageContext.request.contextPath}/pacientes"
          method="post">

        <input type="hidden"
               name="accion"
               value="${accion}">

        <c:if test="${accion == 'actualizar'}">

            <input type="hidden"
                   name="idPaciente"
                   value="${paciente.idPaciente}">

        </c:if>

        <label for="idUsuario">
            ID Usuario
        </label>

        <input type="number"
               id="idUsuario"
               name="idUsuario"
               value="${paciente.idUsuario}"
               required>

        <label for="fechaNacimiento">
            Fecha de nacimiento
        </label>

        <input type="date"
               id="fechaNacimiento"
               name="fechaNacimiento"
               value="${paciente.fechaNacimiento}"
               required>

        <label for="sexo">
            Sexo
        </label>

        <select id="sexo"
                name="sexo"
                required>

            <option value="">
                Seleccione una opción
            </option>

            <option value="Masculino"
                ${paciente.sexo == 'Masculino' ? 'selected' : ''}>
                Masculino
            </option>

            <option value="Femenino"
                ${paciente.sexo == 'Femenino' ? 'selected' : ''}>
                Femenino
            </option>

            <option value="Otro"
                ${paciente.sexo == 'Otro' ? 'selected' : ''}>
                Otro
            </option>

        </select>

        <button type="submit" class="boton">
            Guardar
        </button>

        <a class="cancelar"
           href="${pageContext.request.contextPath}/pacientes">
            Cancelar
        </a>

    </form>

</main>

</body>

</html>

