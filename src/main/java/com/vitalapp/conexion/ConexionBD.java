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

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );

            System.out.println("CONEXION EXITOSA CON LA BASE DE DATOS VITALAPP.");

            return conexion;

        } catch (ClassNotFoundException e) {

            System.out.println("ERROR: NO SE ENCONTRO EL DRIVER DE MYSQL.");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("ERROR AL CONECTAR CON MYSQL.");
            System.out.println("URL: " + URL);
            System.out.println("USUARIO: " + USUARIO);
            System.out.println("DETALLE DEL ERROR: " + e.getMessage());

            e.printStackTrace();
        }

        return null;
    }

    public static void cerrar(Connection conexion) {

        if (conexion != null) {

            try {

                conexion.close();
                System.out.println("CONEXION CERRADA CORRECTAMENTE.");

            } catch (SQLException e) {

                System.out.println("ERROR AL CERRAR LA CONEXION.");
                e.printStackTrace();
            }
        }
    }
}