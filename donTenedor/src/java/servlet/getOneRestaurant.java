/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.RestaurantJpaController;
import DTO.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danieljimenez
 */
public class getOneRestaurant extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        int idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
        RestaurantJpaController ctrRestaurant = new RestaurantJpaController(emf);
        Restaurant restaurante = ctrRestaurant.findRestaurant(idRestaurant);
        
        HashMap mapaService = new HashMap();
        mapaService.put("Wifi", restaurante.getWifi());
        mapaService.put("Pago con Tarjeta", restaurante.getCardPayment());
        mapaService.put("Adaptado Minusvalidos", restaurante.getHandicapped());
        mapaService.put("Terraza", restaurante.getTerrace());
        mapaService.put("Servicio a Domicilio", restaurante.getHomeService());
        
        request.setAttribute("listService", mapaService);
        request.setAttribute("restaurantView", restaurante);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
