/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.UsersJpaController;
import DTO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author danieljimenez
 */
public class changePassword extends HttpServlet {

    protected void proceso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        tools tool = new tools();
        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
            
            String idUser = request.getParameter("idUser");
            UsersJpaController ctrUsuarios = new UsersJpaController(emf);
            Users usuario = ctrUsuarios.findUsers(Integer.parseInt(idUser));
            
            if (request.getParameter("check") != null) {
                String password = request.getParameter("passwordActual");
                
                String passwordEncryption = tool.encrypterPassword(password);

                if (passwordEncryption.equals(usuario.getPassword())) {
                    out.print(true);
                } else {
                    out.print(false);
                }
            } else {
                
                String password = request.getParameter("newPassword");
                String passwordEncryption = tool.encrypterPassword(password);
                
                usuario.setPassword(passwordEncryption);
                HttpSession session = request.getSession();
                try {
                    ctrUsuarios.edit(usuario);
                    session.setAttribute("error", "no");
                    response.sendRedirect("index.jsp?view=restaurantPanel");
                } catch (Exception e) {
                    session.setAttribute("error", "yes");
                    response.sendRedirect("index.jsp?view=restaurantPanel");
                }
                
            }

        } catch (Exception e) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proceso(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proceso(request, response);
    }
}
