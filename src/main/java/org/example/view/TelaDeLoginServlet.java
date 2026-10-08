package org.example.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.example.controller.Validacao;

import java.io.IOException;

@WebServlet("/login")
public class TelaDeLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath() + "/login.html"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if (Validacao.validarLogin(usuario, senha)) {

            String perfil = Validacao.getPerfil(usuario);

            HttpSession session = request.getSession();

            session.setAttribute("usuario", usuario);
            session.setAttribute("perfil", perfil);

            if ("ADMIN".equals(perfil)) {

                response.sendRedirect(
                        request.getContextPath() + "/admin"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath() + "/home"
                );
            }

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.html?erro=senha"
            );
        }
    }
}