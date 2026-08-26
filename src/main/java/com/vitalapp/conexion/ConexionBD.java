package com.vitalapp.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/vitalapp";
    private static final String USUARIO = "root";
   private static final String CONTRASENA = "Maclamana2001.";
    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );

            System.out.println("Conexión exitosa con la base de datos VitalApp.");

            return conexion;

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
            return null;
        }
    }

    public static void cerrar(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
            }
        }
    }
}