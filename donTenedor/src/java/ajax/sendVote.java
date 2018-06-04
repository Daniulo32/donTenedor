/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import DAO.ReservationsJpaController;
import DAO.VotingJpaController;
import DTO.Reservations;
import DTO.Restaurant;
import DTO.Users;
import DTO.Voting;
import DTO.VotingPK;
import java.io.IOException;
import java.io.PrintWriter;
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
public class sendVote extends HttpServlet {

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

        int vote = Integer.parseInt(request.getParameter("valueVote"));
        int idReserve = Integer.parseInt(request.getParameter("idReservation"));

        ReservationsJpaController ctrReservation = new ReservationsJpaController(emf);

        Reservations reserve = ctrReservation.findReservations(idReserve);

        Restaurant restaurant = reserve.getIdRestaurant();
        Users user = reserve.getIdUser();

        VotingJpaController ctrVoting = new VotingJpaController(emf);

        Voting voting = new Voting();
        voting.setRestaurant(restaurant);
        voting.setUsers(user);
        voting.setVote(vote);

        Reservations reser = ctrReservation.findReservationPK(restaurant, user);
        try {
            if (reser == null) {
                ctrVoting.create(voting);
                ctrReservation.destroy(idReserve);
            } else {
                ctrReservation.destroy(idReserve);
            }
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
