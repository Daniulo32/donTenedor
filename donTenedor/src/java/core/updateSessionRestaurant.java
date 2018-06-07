/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import DAO.OffersJpaController;
import DAO.RestaurantJpaController;
import DTO.Offers;
import DTO.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.tools;

/**
 *
 * @author danieljimenez
 */
public class updateSessionRestaurant extends HttpServlet {

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
        RestaurantJpaController ctrRestaurant = new RestaurantJpaController(emf);
        OffersJpaController ctrOffer = new OffersJpaController(emf);
        tools t = new tools();
        HttpSession session = request.getSession();

        Restaurant restaurant = (Restaurant) session.getAttribute("restaurante");

        Restaurant restaurante = ctrRestaurant.findRestaurant(restaurant.getIdRestaurant());

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        try {
            for (Offers offer : restaurante.getOffersList()) {
                Date checkDate = t.maxTime(offer.getEndDate());
                
                if (checkDate.before(today)) {
                    ctrOffer.destroy(offer.getIdOffer());
                }
            }
        } catch (Exception e) {
        }
        
        Restaurant rest = ctrRestaurant.findRestaurant(restaurant.getIdRestaurant());
        session.setAttribute("restaurante", rest);
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
