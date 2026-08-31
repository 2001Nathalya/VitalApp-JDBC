package com.vitalapp;

import com.vitalapp.conexion.ConexionBD;
import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        System.out.println("=== PRUEBA DE CONEXION VITALAPP ===");

        Connection conexion = ConexionBD.conectar();

        if (conexion != null) {
            System.out.println("CONEXION EXITOSA.");
            ConexionBD.cerrar(conexion);
        } else {
            System.out.println("NO SE PUDO CONECTAR.");
        }
    }
}