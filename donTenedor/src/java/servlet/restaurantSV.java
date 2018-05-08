/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.ProvinciaJpaController;
import DAO.RestaurantJpaController;
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

/**
 *
 * @author danieljimenez
 */
public class restaurantSV extends HttpServlet {

    protected void proceso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        
        ProvinciaJpaController ctrProvincia = new ProvinciaJpaController(emf);
        
        ArrayList listaProvincias = (ArrayList) ctrProvincia.getAllProvincias();
        
        request.setAttribute("listaProvincias", listaProvincias);

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
