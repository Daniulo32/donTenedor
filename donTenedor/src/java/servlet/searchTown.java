/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.PoblacionJpaController;
import DAO.RestaurantJpaController;
import DAO.UsersJpaController;
import DTO.Poblacion;
import DTO.Restaurant;
import DTO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;

/**
 *
 * @author danieljimenez
 */
public class searchTown extends HttpServlet {

    protected void proceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");

        int idProvincia = Integer.parseInt(request.getParameter("idProvincia"));

        PoblacionJpaController ctrPoblacion = new PoblacionJpaController(emf);

        try {
            
            ArrayList<Poblacion> listaPoblaciones = (ArrayList) ctrPoblacion.getPoblacionByProvincia(idProvincia);
            JSONObject obj = new JSONObject();
            for (Poblacion poblacion : listaPoblaciones) {
                obj.put(poblacion.getIdpoblacion() + "", poblacion.getPoblacion());
            }
            out.print(obj);
        } catch (Exception e) {
        }
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
