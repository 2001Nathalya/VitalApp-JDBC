package com.vitalapp.servlet;

import com.vitalapp.dao.PacienteDAO;
import com.vitalapp.modelo.Paciente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/pacientes")
public class PacienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PacienteDAO pacienteDAO;

    @Override
    public void init() {
        pacienteDAO = new PacienteDAO();
    }

    // ============================
    // MÉTODO GET
    // ============================
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
                eliminarPaciente(request, response);
                break;

            case "listar":
            default:
                listarPacientes(request, response);
                break;
        }
    }

    // ============================
    // MÉTODO POST
    // ============================
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {

            insertarPaciente(request, response);

        } else if ("actualizar".equals(accion)) {

            actualizarPaciente(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/pacientes"
            );
        }
    }

    // ============================
    // LISTAR PACIENTES
    // ============================
    private void listarPacientes(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        List<Paciente> pacientes = pacienteDAO.listar();

        request.setAttribute("pacientes", pacientes);

        request.getRequestDispatcher(
                "/pacientes/listar.jsp"
        ).forward(request, response);
    }

    // ============================
    // MOSTRAR FORMULARIO NUEVO
    // ============================
    private void mostrarFormularioNuevo(HttpServletRequest request,
                                        HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("titulo", "Registrar Paciente");
        request.setAttribute("accion", "insertar");

        request.getRequestDispatcher(
                "/pacientes/formulario.jsp"
        ).forward(request, response);
    }

    // ============================
    // MOSTRAR FORMULARIO EDITAR
    // ============================
    private void mostrarFormularioEditar(HttpServletRequest request,
                                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            Paciente paciente = pacienteDAO.buscarPorId(id);

            if (paciente != null) {

                request.setAttribute("paciente", paciente);
                request.setAttribute("titulo", "Editar Paciente");
                request.setAttribute("accion", "actualizar");

                request.getRequestDispatcher(
                        "/pacientes/formulario.jsp"
                ).forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath() + "/pacientes"
                );
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/pacientes"
            );
        }
    }

    // ============================
    // INSERTAR PACIENTE
    // ============================
    private void insertarPaciente(HttpServletRequest request,
                                  HttpServletResponse response)
            throws IOException {

        try {

            int idUsuario = Integer.parseInt(
                    request.getParameter("idUsuario")
            );

            LocalDate fechaNacimiento = LocalDate.parse(
                    request.getParameter("fechaNacimiento")
            );

            String sexo = request.getParameter("sexo");

            Paciente paciente = new Paciente();

            paciente.setIdUsuario(idUsuario);
            paciente.setFechaNacimiento(fechaNacimiento);
            paciente.setSexo(sexo);

            pacienteDAO.insertar(paciente);

        } catch (Exception e) {

            System.out.println(
                    "Error al insertar paciente: " + e.getMessage()
            );
        }

        response.sendRedirect(
                request.getContextPath() + "/pacientes"
        );
    }

    // ============================
    // ACTUALIZAR PACIENTE
    // ============================
    private void actualizarPaciente(HttpServletRequest request,
                                    HttpServletResponse response)
            throws IOException {

        try {

            int idPaciente = Integer.parseInt(
                    request.getParameter("idPaciente")
            );

            int idUsuario = Integer.parseInt(
                    request.getParameter("idUsuario")
            );

            LocalDate fechaNacimiento = LocalDate.parse(
                    request.getParameter("fechaNacimiento")
            );

            String sexo = request.getParameter("sexo");

            Paciente paciente = new Paciente();

            paciente.setIdPaciente(idPaciente);
            paciente.setIdUsuario(idUsuario);
            paciente.setFechaNacimiento(fechaNacimiento);
            paciente.setSexo(sexo);

            pacienteDAO.actualizar(paciente);

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar paciente: " + e.getMessage()
            );
        }

        response.sendRedirect(
                request.getContextPath() + "/pacientes"
        );
    }

    // ============================
    // ELIMINAR PACIENTE
    // ============================
    private void eliminarPaciente(HttpServletRequest request,
                                  HttpServletResponse response)
            throws IOException {

        try {

            int idPaciente = Integer.parseInt(
                    request.getParameter("id")
            );

            pacienteDAO.eliminar(idPaciente);

        } catch (NumberFormatException e) {

            System.out.println(
                    "ID de paciente inválido."
            );
        }

        response.sendRedirect(
                request.getContextPath() + "/pacientes"
        );
    }
}
