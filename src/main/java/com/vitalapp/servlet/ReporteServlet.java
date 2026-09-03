package com.vitalapp.servlet;

import com.vitalapp.dao.ReporteDAO;
import com.vitalapp.modelo.Reporte;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/reportes")
public class ReporteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ReporteDAO reporteDAO;

    @Override
    public void init() {
        reporteDAO = new ReporteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;

            case "editar":
                mostrarFormularioEditar(request, response);
                break;

            case "eliminar":
                eliminarReporte(request, response);
                break;

            case "listar":
            default:
                listarReportes(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {

            insertarReporte(request, response);

        } else if ("actualizar".equals(accion)) {

            actualizarReporte(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/reportes");
        }
    }

    private void listarReportes(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        List<Reporte> reportes = reporteDAO.listar();

        request.setAttribute("reportes", reportes);

        request.getRequestDispatcher(
                "/reportes/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request,
                                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("titulo", "Registrar Reporte");
        request.setAttribute("accion", "insertar");

        request.getRequestDispatcher(
                "/reportes/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request,
                                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id"));

            Reporte reporte = reporteDAO.buscarPorId(id);

            if (reporte != null) {

                request.setAttribute("reporte", reporte);
                request.setAttribute("titulo", "Editar Reporte");
                request.setAttribute("accion", "actualizar");

                request.getRequestDispatcher(
                        "/reportes/formulario.jsp").forward(
                                request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath() + "/reportes");
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/reportes");
        }
    }

    private void insertarReporte(HttpServletRequest request,
                                  HttpServletResponse response)
            throws IOException {

        try {

            int idPaciente = Integer.parseInt(
                    request.getParameter("idPaciente"));

            String descripcion =
                    request.getParameter("descripcion");

            LocalDateTime fechaGeneracion =
                    LocalDateTime.parse(
                            request.getParameter("fechaGeneracion"));

            Reporte reporte = new Reporte();

            reporte.setIdPaciente(idPaciente);
            reporte.setFechaGeneracion(fechaGeneracion);
            reporte.setDescripcion(descripcion);

            reporteDAO.insertar(reporte);

        } catch (Exception e) {

            System.out.println(
                    "Error al insertar reporte: "
                    + e.getMessage());
        }

        response.sendRedirect(
                request.getContextPath() + "/reportes");
    }

    private void actualizarReporte(HttpServletRequest request,
                                   HttpServletResponse response)
            throws IOException {

        try {

            int idReporte = Integer.parseInt(
                    request.getParameter("idReporte"));

            int idPaciente = Integer.parseInt(
                    request.getParameter("idPaciente"));

            String descripcion =
                    request.getParameter("descripcion");

            LocalDateTime fechaGeneracion =
                    LocalDateTime.parse(
                            request.getParameter("fechaGeneracion"));

            Reporte reporte = new Reporte();

            reporte.setIdReporte(idReporte);
            reporte.setIdPaciente(idPaciente);
            reporte.setFechaGeneracion(fechaGeneracion);
            reporte.setDescripcion(descripcion);

            boolean resultado =
                    reporteDAO.actualizar(reporte);

            System.out.println("=================================");
            System.out.println("ACTUALIZACION DE REPORTE");
            System.out.println("ID Reporte: " + idReporte);
            System.out.println("ID Paciente: " + idPaciente);
            System.out.println("Resultado: " + resultado);
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar reporte: "
                    + e.getMessage());
        }

        response.sendRedirect(
                request.getContextPath() + "/reportes");
    }

    private void eliminarReporte(HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException {

        try {

            int idReporte = Integer.parseInt(
                    request.getParameter("id"));

            reporteDAO.eliminar(idReporte);

        } catch (NumberFormatException e) {

            System.out.println(
                    "ID de reporte inválido.");
        }

        response.sendRedirect(
                request.getContextPath() + "/reportes");
    }
}