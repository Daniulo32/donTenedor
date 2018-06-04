/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import DAO.ReservationsJpaController;
import DAO.UsersJpaController;
import DTO.Reservations;
import DTO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
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
public class updateSessionUser extends HttpServlet {

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
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
            HttpSession session = request.getSession();
            UsersJpaController ctrUsers = new UsersJpaController(emf);

            Users user = (Users) session.getAttribute("usuario");

            Users usuario = ctrUsers.findUsers(user.getIdUser());
            Date today = new Date();
            
            //Delete reserve if is uncofirmed and date is old
            ReservationsJpaController ctrReservation = new ReservationsJpaController(emf);
            for (Reservations reserve : usuario.getReservationsList()) {
                if (reserve.getReservationDate().before(today) && reserve.getStatus().equals("Sin Confirmar")) {
                    ctrReservation.destroy(reserve.getIdReservation());
                }
            }

            //update session user
            Users usu = ctrUsers.findUsers(user.getIdUser());

            HashMap listDatesReservation = new HashMap();

            for (Reservations reserve : usu.getReservationsList()) {
                if (reserve.getReservationDate().after(today)) {
                    listDatesReservation.put(reserve.getIdReservation(), false);
                } else {
                    listDatesReservation.put(reserve.getIdReservation(), true);
                }
            }
            session.setAttribute("usuario", usu);
            session.setAttribute("listDatesReservation", listDatesReservation);
            
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
