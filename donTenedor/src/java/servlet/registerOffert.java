/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.OffersJpaController;
import DTO.Offers;
import DTO.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class registerOffert extends HttpServlet {

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
        HttpSession session = request.getSession();
        tools t = new tools();
        OffersJpaController ctrOffert = new OffersJpaController(emf);
        try {
            Restaurant restaurante = (Restaurant) session.getAttribute("restaurante");
            Date dateStart = t.convertStringToDate(request.getParameter("dateStart"));
            Date dateEnd = t.convertStringToDate(request.getParameter("dateEnd"));
            int percentage = Integer.parseInt(request.getParameter("percentage"));
            
            Offers offer = new Offers();
            offer.setPercentage(percentage);
            offer.setEndDate(dateEnd);
            offer.setStartDate(dateStart);
            offer.setIdRestaurant(restaurante);
            
            ctrOffert.create(offer);
            
            session.setAttribute("message", "Oferta registrada correctamente");
        } catch (Exception e) {
            session.setAttribute("error", "Se produjo un error al registrar la oferta");
        }
        response.sendRedirect("index.jsp?view=restaurantPanel");
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
