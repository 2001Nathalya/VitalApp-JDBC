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
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .campo {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .botones {
            margin-top: 25px;
            text-align: center;
        }

        button,
        .cancelar {
            display: inline-block;
            padding: 11px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }

        button {
            background-color: #2D4EF7;
            color: white;
        }

        .cancelar {
            background-color: #777;
            color: white;
            margin-left: 10px;
        }

    </style>

</head>

<body>

<header>

    <h1>VitalApp</h1>

    <p>Gestión de Usuarios</p>

</header>

<main>

    <h2>${titulo}</h2>

    <form action="${pageContext.request.contextPath}/usuarios"
          method="post">

        <input type="hidden"
               name="accion"
               value="${accion}">

        <c:if test="${not empty usuario}">

            <input type="hidden"
                   name="idUsuario"
                   value="${usuario.idUsuario}">

        </c:if>

        <div class="campo">

            <label for="nombre">
                Nombre completo:
            </label>

            <input type="text"
                   id="nombre"
                   name="nombre"
                   value="${usuario.nombre}"
                   required>

        </div>

        <div class="campo">

            <label for="correo">
                Correo electrónico:
            </label>

            <input type="email"
                   id="correo"
                   name="correo"
                   value="${usuario.correo}"
                   required>

        </div>

        <div class="campo">

            <label for="contrasena">
                Contraseña:
            </label>

            <input type="password"
                   id="contrasena"
                   name="contrasena"
                   value="${usuario.contrasena}"
                   required>

        </div>

        <div class="campo">

            <label for="rol">
                Rol:
            </label>

            <select id="rol"
                    name="rol"
                    required>

                <option value="">
                    Seleccione un rol
                </option>

                <option value="Paciente"
                    ${usuario.rol == 'Paciente' ? 'selected' : ''}>
                    Paciente
                </option>

                <option value="Medico"
                    ${usuario.rol == 'Medico' ? 'selected' : ''}>
                    Médico
                </option>

                <option value="Tecnico"
                    ${usuario.rol == 'Tecnico' ? 'selected' : ''}>
                    Técnico
                </option>

                <option value="Administrador"
                    ${usuario.rol == 'Administrador' ? 'selected' : ''}>
                    Administrador
                </option>

            </select>

        </div>

        <div class="botones">

            <button type="submit">
                Guardar
            </button>

            <a class="cancelar"
               href="${pageContext.request.contextPath}/usuarios">
                Cancelar
            </a>

        </div>

    </form>

</main>

</body>

</html>