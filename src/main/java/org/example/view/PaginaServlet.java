package org.example.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({
        "/home",
        "/detalhes",
        "/carrinho",
        "/compra",
        "/admin"
})
public class PaginaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String caminho = request.getServletPath();

        HttpSession session = request.getSession(false);

        // =========================================
        // VERIFICA LOGIN
        // =========================================

        if (session == null ||
                session.getAttribute("usuario") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login.html"
            );

            return;
        }

        // =========================================
        // ADMIN
        // =========================================

        if ("/admin".equals(caminho)) {

            String perfil =
                    (String) session.getAttribute("perfil");

            if (!"ADMIN".equals(perfil)) {

                response.sendRedirect(
                        request.getContextPath() + "/home"
                );

                return;
            }

            request.getRequestDispatcher(
                    "/WEB-INF/views/admin.html"
            ).forward(request, response);

            return;
        }

        // =========================================
        // HOME
        // =========================================

        if ("/home".equals(caminho)) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/home.html"
            ).forward(request, response);

            return;
        }

        // =========================================
        // DETALHES
        // =========================================

        if ("/detalhes".equals(caminho)) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/detalhes.html"
            ).forward(request, response);

            return;
        }

        // =========================================
        // CARRINHO
        // =========================================

        if ("/carrinho".equals(caminho)) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/carrinho.html"
            ).forward(request, response);

            return;
        }

        // =========================================
        // COMPRA
        // =========================================

        if ("/compra".equals(caminho)) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/compra.html"
            ).forward(request, response);
        }
    }
}