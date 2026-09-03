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
        select,
        textarea {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        textarea {
            min-height: 100px;
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

    <p>Gestión de Alertas</p>

</header>

<main>

    <h2>${titulo}</h2>

    <form action="${pageContext.request.contextPath}/alertas"
          method="post">

        <input type="hidden"
               name="accion"
               value="${accion}">

        <c:if test="${not empty alerta}">

            <input type="hidden"
                   name="idAlerta"
                   value="${alerta.idAlerta}">

        </c:if>

        <label for="idPaciente">
            ID Paciente
        </label>

        <input type="number"
               id="idPaciente"
               name="idPaciente"
               value="${alerta.idPaciente}"
               min="1"
               required>

        <label for="tipoAlerta">
            Tipo de Alerta
        </label>

        <select id="tipoAlerta"
                name="tipoAlerta"
                required>

            <option value="">
                Seleccione un tipo
            </option>

            <option value="Frecuencia cardíaca alta"
                ${alerta.tipoAlerta == 'Frecuencia cardíaca alta' ? 'selected' : ''}>
                Frecuencia cardíaca alta
            </option>

            <option value="Frecuencia cardíaca baja"
                ${alerta.tipoAlerta == 'Frecuencia cardíaca baja' ? 'selected' : ''}>
                Frecuencia cardíaca baja
            </option>

            <option value="Presión arterial alta"
                ${alerta.tipoAlerta == 'Presión arterial alta' ? 'selected' : ''}>
                Presión arterial alta
            </option>

            <option value="Presión arterial baja"
                ${alerta.tipoAlerta == 'Presión arterial baja' ? 'selected' : ''}>
                Presión arterial baja
            </option>

            <option value="Temperatura alta"
                ${alerta.tipoAlerta == 'Temperatura alta' ? 'selected' : ''}>
                Temperatura alta
            </option>

            <option value="Temperatura baja"
                ${alerta.tipoAlerta == 'Temperatura baja' ? 'selected' : ''}>
                Temperatura baja
            </option>

            <option value="Saturación de oxígeno baja"
                ${alerta.tipoAlerta == 'Saturación de oxígeno baja' ? 'selected' : ''}>
                Saturación de oxígeno baja
            </option>

            <option value="Glucemia alta"
                ${alerta.tipoAlerta == 'Glucemia alta' ? 'selected' : ''}>
                Glucemia alta
            </option>

            <option value="Glucemia baja"
                ${alerta.tipoAlerta == 'Glucemia baja' ? 'selected' : ''}>
                Glucemia baja
            </option>

            <option value="Otra">
                Otra
            </option>

        </select>

        <label for="descripcion">
            Descripción
        </label>

        <textarea id="descripcion"
                  name="descripcion"
                  maxlength="500"
                  required>${alerta.descripcion}</textarea>

        <label for="fechaAlerta">
            Fecha y hora de la alerta
        </label>

        <input type="datetime-local"
               id="fechaAlerta"
               name="fechaAlerta"
               value="${alerta.fechaAlerta}"
               required>

        <div class="botones">

            <button type="submit"
                    class="guardar">
                Guardar
            </button>

            <a class="cancelar"
               href="${pageContext.request.contextPath}/alertas">
                Cancelar
            </a>

        </div>

    </form>

</main>

</body>

</html>