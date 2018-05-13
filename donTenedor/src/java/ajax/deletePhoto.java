/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import DAO.PhotoRestaurantJpaController;
import DAO.RestaurantJpaController;
import DTO.PhotoRestaurantPK;
import DTO.Restaurant;
import DTO.Users;
import java.io.File;
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
 * @author DanielJL
 */
public class deletePhoto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        PhotoRestaurantJpaController ctrPhotoRestaurant = new PhotoRestaurantJpaController(emf);

        String photo = request.getParameter("photo");
        HttpSession session = request.getSession();
        Restaurant restaurante = (Restaurant) session.getAttribute("restaurante");
        Users usuario = restaurante.getIdAdmin();

        //Path photo file
        String path = getServletContext().getRealPath("")+ "images/photoRestaurant/"+ usuario.getName() + usuario.getSubname() + "-" + usuario.getIdUser() + "/";

        //file
        String fileDelete = path + photo;
        File file = new File(fileDelete);

        try {
            out.print(fileDelete);
            if (file.exists()) {
                file.delete();
                PhotoRestaurantPK photoPK = new PhotoRestaurantPK();
                photoPK.setNamePhoto(photo);
                photoPK.setIdRestaurant(restaurante.getIdRestaurant());
                ctrPhotoRestaurant.destroy(photoPK);
            }
            
            RestaurantJpaController ctrRestaurant = new RestaurantJpaController(emf);
            Restaurant restau = ctrRestaurant.findRestaurant(restaurante.getIdRestaurant());
            session.setAttribute("restaurante", restau);

        } catch (Exception e) {

        }

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
