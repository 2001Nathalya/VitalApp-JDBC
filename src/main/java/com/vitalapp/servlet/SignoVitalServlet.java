package com.vitalapp.servlet;

import com.vitalapp.dao.SignoVitalDAO;
import com.vitalapp.modelo.SignoVital;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/signos-vitales")
public class SignoVitalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private SignoVitalDAO signoVitalDAO;

    @Override
    public void init() {
        signoVitalDAO = new SignoVitalDAO();
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
                eliminarSignoVital(request, response);
                break;

            case "listar":
            default:
                listarSignosVitales(request, response);
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
            insertarSignoVital(request, response);

        } else if ("actualizar".equals(accion)) {
            actualizarSignoVital(request, response);

        } else {
            response.sendRedirect(
                    request.getContextPath() + "/signos-vitales");
        }
    }

    private void listarSignosVitales(HttpServletRequest request,
                                     HttpServletResponse response)
            throws ServletException, IOException {

        List<SignoVital> signosVitales = signoVitalDAO.listar();

        request.setAttribute("signosVitales", signosVitales);

        request.getRequestDispatcher(
                "/signos-vitales/listar.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request,
                                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("titulo", "Registrar Signo Vital");
        request.setAttribute("accion", "insertar");

        request.getRequestDispatcher(
                "/signos-vitales/formulario.jsp")
                .forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request,
                                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id"));

            SignoVital signoVital =
                    signoVitalDAO.buscarPorId(id);

            if (signoVital != null) {

                request.setAttribute(
                        "signoVital", signoVital);

                request.setAttribute(
                        "titulo", "Editar Signo Vital");

                request.setAttribute(
                        "accion", "actualizar");

                request.getRequestDispatcher(
                        "/signos-vitales/formulario.jsp")
                        .forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/signos-vitales");
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/signos-vitales");
        }
    }

    private void insertarSignoVital(HttpServletRequest request,
                                    HttpServletResponse response)
            throws IOException {

        try {

            int idPaciente = Integer.parseInt(
                    request.getParameter("idPaciente"));

            LocalDateTime fechaRegistro =
                    convertirFecha(request.getParameter(
                            "fechaRegistro"));

            int frecuenciaCardiaca =
                    Integer.parseInt(request.getParameter(
                            "frecuenciaCardiaca"));

            int frecuenciaRespiratoria =
                    Integer.parseInt(request.getParameter(
                            "frecuenciaRespiratoria"));

            String presionArterial =
                    request.getParameter("presionArterial");

            double temperatura =
                    Double.parseDouble(request.getParameter(
                            "temperatura"));

            double saturacionOxigeno =
                    Double.parseDouble(request.getParameter(
                            "saturacionOxigeno"));

            double glucemia =
                    Double.parseDouble(request.getParameter(
                            "glucemia"));

            SignoVital signoVital = new SignoVital();

            signoVital.setIdPaciente(idPaciente);
            signoVital.setFechaRegistro(fechaRegistro);
            signoVital.setFrecuenciaCardiaca(
                    frecuenciaCardiaca);
            signoVital.setFrecuenciaRespiratoria(
                    frecuenciaRespiratoria);
            signoVital.setPresionArterial(
                    presionArterial);
            signoVital.setTemperatura(temperatura);
            signoVital.setSaturacionOxigeno(
                    saturacionOxigeno);
            signoVital.setGlucemia(glucemia);

            signoVitalDAO.insertar(signoVital);

        } catch (Exception e) {

            System.out.println(
                    "Error al insertar signo vital: "
                            + e.getMessage());
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/signos-vitales");
    }

    private void actualizarSignoVital(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            int idSigno = Integer.parseInt(
                    request.getParameter("idSigno"));

            int idPaciente = Integer.parseInt(
                    request.getParameter("idPaciente"));

            LocalDateTime fechaRegistro =
                    convertirFecha(request.getParameter(
                            "fechaRegistro"));

            int frecuenciaCardiaca =
                    Integer.parseInt(request.getParameter(
                            "frecuenciaCardiaca"));

            int frecuenciaRespiratoria =
                    Integer.parseInt(request.getParameter(
                            "frecuenciaRespiratoria"));

            String presionArterial =
                    request.getParameter("presionArterial");

            double temperatura =
                    Double.parseDouble(request.getParameter(
                            "temperatura"));

            double saturacionOxigeno =
                    Double.parseDouble(request.getParameter(
                            "saturacionOxigeno"));

            double glucemia =
                    Double.parseDouble(request.getParameter(
                            "glucemia"));

            SignoVital signoVital = new SignoVital();

            signoVital.setIdSigno(idSigno);
            signoVital.setIdPaciente(idPaciente);
            signoVital.setFechaRegistro(fechaRegistro);
            signoVital.setFrecuenciaCardiaca(
                    frecuenciaCardiaca);
            signoVital.setFrecuenciaRespiratoria(
                    frecuenciaRespiratoria);
            signoVital.setPresionArterial(
                    presionArterial);
            signoVital.setTemperatura(temperatura);
            signoVital.setSaturacionOxigeno(
                    saturacionOxigeno);
            signoVital.setGlucemia(glucemia);

            boolean resultado =
                    signoVitalDAO.actualizar(signoVital);

            System.out.println(
                    "Actualización de signo vital: "
                            + resultado);

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar signo vital: "
                            + e.getMessage());
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/signos-vitales");
    }

    private void eliminarSignoVital(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            int idSigno = Integer.parseInt(
                    request.getParameter("id"));

            signoVitalDAO.eliminar(idSigno);

        } catch (NumberFormatException e) {

            System.out.println(
                    "ID de signo vital inválido.");
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/signos-vitales");
    }

    private LocalDateTime convertirFecha(String fecha) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd'T'HH:mm");

        return LocalDateTime.parse(
                fecha,
                formatter);
    }
}