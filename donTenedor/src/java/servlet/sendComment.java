/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.CommentsJpaController;
import DAO.RestaurantJpaController;
import DTO.Comments;
import DTO.Restaurant;
import DTO.Users;
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
public class sendComment extends HttpServlet {

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
        CommentsJpaController ctrComments = new CommentsJpaController(emf);
        RestaurantJpaController ctrRestaurant = new RestaurantJpaController(emf);
        tools t = new tools();
        
        int idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
        String comment = request.getParameter("comment");
        Users user = (Users) session.getAttribute("usuario");
        Restaurant restaurante = ctrRestaurant.findRestaurant(idRestaurant);
        
        if(user != null){
            try {
                Comments comentario = new Comments();
                comentario.setComment(comment);
                comentario.setIdUser(user);
                comentario.setIdRestaurant(restaurante);
                comentario.setCommentDate(new Date());
                ctrComments.create(comentario);
                session.setAttribute("message", "Comentario realizado correctamente");
            } catch (Exception e) {
                session.setAttribute("error", "Se produjo un error al realizar el comentario");
            }
        }else{
            session.setAttribute("error", "Necesitas estar registrado para realizar un comentario");
        }
        
        response.sendRedirect("index.jsp?view=viewOneRestaurant&controller=getOneRestaurant&idRestaurant="+idRestaurant);
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
