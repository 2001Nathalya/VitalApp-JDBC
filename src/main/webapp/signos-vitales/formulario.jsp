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
            width: 600px;
            max-width: 90%;
            margin: 30px auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 25px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 12px;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }

        input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        .botones {
            display: flex;
            gap: 10px;
            margin-top: 25px;
        }

        button {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 15px;
        }

        .guardar {
            background-color: #2D4EF7;
            color: white;
        }

        .cancelar {
            flex: 1;
            background-color: #777;
            color: white;
            text-decoration: none;
            text-align: center;
            padding: 12px;
            border-radius: 5px;
            box-sizing: border-box;
        }
    </style>
</head>

<body>

<header>
    <h1>VitalApp</h1>
    <p>Registro y Monitoreo de Signos Vitales</p>
</header>

<main>

    <h2>${titulo}</h2>

    <form action="${pageContext.request.contextPath}/signos-vitales"
          method="post">

        <input type="hidden"
               name="accion"
               value="${accion}">

        <c:if test="${not empty signoVital}">
            <input type="hidden"
                   name="idSigno"
                   value="${signoVital.idSigno}">
        </c:if>

        <label for="idPaciente">ID Paciente</label>

        <input type="number"
               id="idPaciente"
               name="idPaciente"
               value="${signoVital.idPaciente}"
               min="1"
               required>

        <label for="fechaRegistro">Fecha y hora de registro</label>

        <input type="datetime-local"
               id="fechaRegistro"
               name="fechaRegistro"
               value="${signoVital.fechaRegistro}"
               required>

        <label for="frecuenciaCardiaca">
            Frecuencia cardíaca (lpm)
        </label>

        <input type="number"
               id="frecuenciaCardiaca"
               name="frecuenciaCardiaca"
               value="${signoVital.frecuenciaCardiaca}"
               min="1"
               required>

        <label for="frecuenciaRespiratoria">
            Frecuencia respiratoria (rpm)
        </label>

        <input type="number"
               id="frecuenciaRespiratoria"
               name="frecuenciaRespiratoria"
               value="${signoVital.frecuenciaRespiratoria}"
               min="1"
               required>

        <label for="presionArterial">
            Presión arterial
        </label>

        <input type="text"
               id="presionArterial"
               name="presionArterial"
               value="${signoVital.presionArterial}"
               placeholder="Ejemplo: 120/80"
               maxlength="20"
               required>

        <label for="temperatura">
            Temperatura (°C)
        </label>

        <input type="number"
               id="temperatura"
               name="temperatura"
               value="${signoVital.temperatura}"
               step="0.01"
               min="0"
               required>

        <label for="saturacionOxigeno">
            Saturación de oxígeno (%)
        </label>

        <input type="number"
               id="saturacionOxigeno"
               name="saturacionOxigeno"
               value="${signoVital.saturacionOxigeno}"
               step="0.01"
               min="0"
               max="100"
               required>

        <label for="glucemia">
            Glucemia (mg/dL)
        </label>

        <input type="number"
               id="glucemia"
               name="glucemia"
               value="${signoVital.glucemia}"
               step="0.01"
               min="0"
               required>

        <div class="botones">

            <button type="submit" class="guardar">
                Guardar
            </button>

            <a class="cancelar"
               href="${pageContext.request.contextPath}/signos-vitales">
                Cancelar
            </a>

        </div>

    </form>

</main>

</body>
</html>