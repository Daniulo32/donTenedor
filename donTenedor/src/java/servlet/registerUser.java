/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.UsersJpaController;
import DTO.Users;
import static DTO.Voting_.users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jni.User;
import org.apache.tomcat.util.codec.binary.Base64;
/**
 *
 * @author danieljimenez
 */
public class registerUser extends HttpServlet {
    tools t = new tools();
    protected void proceso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        
        int restaurant;
        
        String name = request.getParameter("name");
        String subname = request.getParameter("subname");
        String mail = request.getParameter("mail");
        String dni = request.getParameter("dni");
        
        if(request.getParameter("restaurant") != null){
            restaurant = Integer.parseInt(request.getParameter("restaurant"));
        }else{
            restaurant = 0;
        }
        
        String password = request.getParameter("password");
        String date = request.getParameter("date");
        Date dateSQl = t.convertStringToDate(date);
        byte[] encoded = Base64.encodeBase64(password.getBytes());
        String passwordEncryption = new String(encoded);
        
        UsersJpaController ctrUser = new UsersJpaController(emf);
        Users usuario = new Users();
        
        usuario.setName(name);
        usuario.setSubname(subname);
        usuario.setEmail(mail);
        usuario.setPassword(passwordEncryption);
        usuario.setDni(dni);
        usuario.setRole(restaurant);
        usuario.setBirthday(dateSQl);
        usuario.setActive(1);
        
        try {
            ctrUser.create(usuario);
        } catch (Exception e) {
            out.print(e);
        }
        
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        proceso(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        proceso(req, res);
    }

}
