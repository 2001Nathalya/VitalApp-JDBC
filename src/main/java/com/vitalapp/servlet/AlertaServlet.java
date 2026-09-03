package com.vitalapp.servlet;

import com.vitalapp.dao.AlertaDAO;
import com.vitalapp.modelo.Alerta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/alertas")
public class AlertaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AlertaDAO alertaDAO;

    @Override
    public void init() {
        alertaDAO = new AlertaDAO();
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
                eliminarAlerta(request, response);
                break;

            case "listar":
            default:
                listarAlertas(request, response);
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
            insertarAlerta(request, response);

        } else if ("actualizar".equals(accion)) {
            actualizarAlerta(request, response);

        } else {
            response.sendRedirect(
                    request.getContextPath() + "/alertas");
        }
    }

    private void listarAlertas(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        List<Alerta> alertas = alertaDAO.listar();

        request.setAttribute("alertas", alertas);

        request.getRequestDispatcher(
                "/alertas/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request,
                                        HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("titulo", "Registrar Alerta");
        request.setAttribute("accion", "insertar");

        request.getRequestDispatcher(
                "/alertas/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request,
                                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id"));

            Alerta alerta = alertaDAO.buscarPorId(id);

            if (alerta != null) {

                request.setAttribute("alerta", alerta);
                request.setAttribute("titulo", "Editar Alerta");
                request.setAttribute("accion", "actualizar");

                request.getRequestDispatcher(
                        "/alertas/formulario.jsp").forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath() + "/alertas");
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/alertas");
        }
    }

    private void insertarAlerta(HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException {

        try {

            int idPaciente = Integer.parseInt(
                    request.getParameter("idPaciente"));

            String tipoAlerta =
                    request.getParameter("tipoAlerta");

            String descripcion =
                    request.getParameter("descripcion");

            LocalDateTime fechaAlerta =
                    LocalDateTime.parse(
                            request.getParameter("fechaAlerta"));

            Alerta alerta = new Alerta();

            alerta.setIdPaciente(idPaciente);
            alerta.setTipoAlerta(tipoAlerta);
            alerta.setDescripcion(descripcion);
            alerta.setFechaAlerta(fechaAlerta);

            alertaDAO.insertar(alerta);

        } catch (Exception e) {

            System.out.println(
                    "Error al insertar alerta: " + e.getMessage());
        }

        response.sendRedirect(
                request.getContextPath() + "/alertas");
    }

    private void actualizarAlerta(HttpServletRequest request,
                                  HttpServletResponse response)
            throws IOException {

        try {

            int idAlerta = Integer.parseInt(
                    request.getParameter("idAlerta"));

            int idPaciente = Integer.parseInt(
                    request.getParameter("idPaciente"));

            String tipoAlerta =
                    request.getParameter("tipoAlerta");

            String descripcion =
                    request.getParameter("descripcion");

            LocalDateTime fechaAlerta =
                    LocalDateTime.parse(
                            request.getParameter("fechaAlerta"));

            Alerta alerta = new Alerta();

            alerta.setIdAlerta(idAlerta);
            alerta.setIdPaciente(idPaciente);
            alerta.setTipoAlerta(tipoAlerta);
            alerta.setDescripcion(descripcion);
            alerta.setFechaAlerta(fechaAlerta);

            boolean resultado =
                    alertaDAO.actualizar(alerta);

            System.out.println("=================================");
            System.out.println("ACTUALIZACION DE ALERTA");
            System.out.println("ID Alerta: " + idAlerta);
            System.out.println("ID Paciente: " + idPaciente);
            System.out.println("Tipo: " + tipoAlerta);
            System.out.println("Resultado: " + resultado);
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar alerta: " + e.getMessage());
        }

        response.sendRedirect(
                request.getContextPath() + "/alertas");
    }

    private void eliminarAlerta(HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException {

        try {

            int idAlerta = Integer.parseInt(
                    request.getParameter("id"));

            alertaDAO.eliminar(idAlerta);

        } catch (NumberFormatException e) {

            System.out.println(
                    "ID de alerta inválido.");
        }

        response.sendRedirect(
                request.getContextPath() + "/alertas");
    }
}