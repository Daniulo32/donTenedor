/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.RestaurantJpaController;
import DAO.UsersJpaController;
import DTO.Restaurant;
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
 * @author DanielJL
 */
public class comprobarUsuario extends HttpServlet {

    protected void proceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");

            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            String[] email = usuario.split("[@._]");

            if (email.length > 2) {
                Users user = loginUser(usuario, emf);

                byte[] encoded = Base64.encodeBase64(password.getBytes());
                String passwordEncryption = new String(encoded);

                if (user != null) {
                    if (user.getPassword().equals(passwordEncryption)) {
                        HttpSession sesion = request.getSession();

                        if (user.getRole() == 0) {
                            sesion.setAttribute("usuario", user);

                            out.print("usuario");
                        } else {
                            Restaurant restaurante = loginRestaurant(user, emf);
                            sesion.setAttribute("restaurante", restaurante);
                            sesion.setAttribute("usuario", user);

                            out.print("restaurante");
                        }

                    } else {
                        out.print("Contraseña o Usuario Incorrecta");
                    }
                } else {
                    out.print("Contraseña o Usuario Incorrecta");
                }

            } else {
//                Administration admin = loginAdmin(usuario, password, emf);
//
//                if (admin != null) {
//                    if (admin.getPassword().equals(password)) {
//                        HttpSession sesion = request.getSession();
//
//                        sesion.setAttribute("usuario", admin);
//
//                        out.print("true");
//
//                    } else {
//                        out.print("Contraseña o Usuario Incorrecta");
//                    }
//                } else {
//                    out.print("Contraseña o Usuario Incorrecta");
//                }

            }

        } catch (Exception e) {
            out.println(e);
        }

    }

//    public Administration loginAdmin(String usuario, String password, EntityManagerFactory emf) {
//        AdministrationJpaController ctrAdmin = new AdministrationJpaController(emf);
//
//        Administration admin = ctrAdmin.getUserAdmin(usuario);
//
//        return admin;
//    }

    public Users loginUser(String usuario, EntityManagerFactory emf) {
        UsersJpaController ctrUsuarios = new UsersJpaController(emf);

        Users user = ctrUsuarios.getUser(usuario);

        return user;
    }
    
    public Restaurant loginRestaurant(Users usuario, EntityManagerFactory emf) {
        RestaurantJpaController ctrRestaurant = new RestaurantJpaController(emf);
        
        Restaurant restaurante = ctrRestaurant.getRestaurant(usuario);

        return restaurante;
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
