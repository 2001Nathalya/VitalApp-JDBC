package com.vitalapp;

import com.vitalapp.dao.ReporteDAO;
import com.vitalapp.modelo.Reporte;

import java.time.LocalDateTime;
import java.util.List;

public class PruebaReporte {

    public static void main(String[] args) {

        ReporteDAO reporteDAO = new ReporteDAO();

        System.out.println("======================================");
        System.out.println("   PRUEBA CRUD REPORTES - VITALAPP");
        System.out.println("======================================");

        // 1. INSERTAR
        System.out.println("\n--- 1. INSERTAR REPORTE ---");

        Reporte reporte = new Reporte(
                0,
                1,
                LocalDateTime.now(),
                "Reporte de evolución del paciente generado correctamente."
        );

        boolean registrado = reporteDAO.insertar(reporte);

        if (!registrado) {
            System.out.println("ERROR: No se pudo insertar el reporte.");
            return;
        }

        System.out.println("ID generado: " + reporte.getIdReporte());

        // 2. LISTAR
        System.out.println("\n--- 2. LISTAR REPORTES ---");

        List<Reporte> reportes = reporteDAO.listar();

        for (Reporte r : reportes) {
            System.out.println(r);
        }

        // 3. BUSCAR
        System.out.println("\n--- 3. BUSCAR REPORTE ---");

        Reporte encontrado =
                reporteDAO.buscarPorId(reporte.getIdReporte());

        if (encontrado != null) {
            System.out.println("Reporte encontrado:");
            System.out.println(encontrado);
        }

        // 4. ACTUALIZAR
        System.out.println("\n--- 4. ACTUALIZAR REPORTE ---");

        reporte.setDescripcion(
                "Reporte actualizado de evolución del paciente."
        );

        boolean actualizado =
                reporteDAO.actualizar(reporte);

        if (actualizado) {
            System.out.println("Reporte actualizado correctamente.");
        }

        // 5. COMPROBAR ACTUALIZACIÓN
        System.out.println("\n--- COMPROBAR ACTUALIZACIÓN ---");

        Reporte actualizadoEncontrado =
                reporteDAO.buscarPorId(reporte.getIdReporte());

        if (actualizadoEncontrado != null) {
            System.out.println(actualizadoEncontrado);
        }

        // 6. ELIMINAR
        System.out.println("\n--- 5. ELIMINAR REPORTE ---");

        boolean eliminado =
                reporteDAO.eliminar(reporte.getIdReporte());

        if (eliminado) {
            System.out.println("Reporte eliminado correctamente.");
        }

        System.out.println("\n======================================");
        System.out.println("   PRUEBA CRUD REPORTES FINALIZADA");
        System.out.println("======================================");
    }
}