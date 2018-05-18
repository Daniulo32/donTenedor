/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import DAO.PhotoRestaurantJpaController;
import DAO.RestaurantJpaController;
import DTO.PhotoRestaurant;
import DTO.PhotoRestaurantPK;
import DTO.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
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
public class favoritePhoto extends HttpServlet {

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
       Restaurant restaurante = (Restaurant)session.getAttribute("restaurante");
       String photo = request.getParameter("photo");
       
       PhotoRestaurantJpaController ctrPhoto = new PhotoRestaurantJpaController(emf);
       
       ctrPhoto.resetFavoritePhoto(restaurante);
       
        PhotoRestaurantPK photoPK = new PhotoRestaurantPK();
        photoPK.setIdRestaurant(restaurante.getIdRestaurant());
        photoPK.setNamePhoto(photo);
        
       ctrPhoto.setFavoritePhoto(photoPK);
       
       //Update restaurant of session
        RestaurantJpaController ctrRestaurant = new RestaurantJpaController(emf);
        Restaurant restaurant = ctrRestaurant.findRestaurant(restaurante.getIdRestaurant());
        session.setAttribute("restaurante", restaurant);
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
