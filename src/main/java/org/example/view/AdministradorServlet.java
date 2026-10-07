package org.example.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin")
public class AdministradorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException{
        HttpSession session = request.getSession(false);

        if(session == null){
            response.sendRedirect(request.getContextPath()+"/login.html");

            return;
        }

        String perfil = session.getAttribute("perfil").toString();
        if(!"ADMIN".equals(perfil)){
            response.sendRedirect(request.getContextPath()+"/home.html");

            return;
        }
        response.sendRedirect(request.getContextPath()+"/admin.html");
    }
}
