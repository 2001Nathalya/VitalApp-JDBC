<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>VitalApp</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background: #f4f6f8;
        }

        header {
            background: linear-gradient(90deg, #2D4EF7, #EA5459);
            color: white;
            padding: 30px;
            text-align: center;
        }

        main {
            max-width: 800px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            text-align: center;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 25px;
            background: #2D4EF7;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

        a:hover {
            background: #1f3bc5;
        }
    </style>
</head>

<body>

<header>
    <h1>VitalApp</h1>
    <p>Registro y monitoreo de signos vitales</p>
</header>

<main>

    <h2>Bienvenido a VitalApp</h2>

    <p>
        Sistema web para la gestión de pacientes,
        profesionales y signos vitales.
    </p>

    <a href="${pageContext.request.contextPath}/usuarios">
        Gestión de Usuarios
    </a>

</main>

</body>

</html>