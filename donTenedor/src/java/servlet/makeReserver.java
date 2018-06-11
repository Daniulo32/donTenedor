/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.OffersJpaController;
import DAO.ReservationsJpaController;
import DAO.RestaurantJpaController;
import DTO.Offers;
import DTO.Reservations;
import DTO.Restaurant;
import DTO.Users;
import core.goEmail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class makeReserver extends HttpServlet {

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        HttpSession session = request.getSession();
        tools t = new tools();
        goEmail emailSend = new goEmail();

        RestaurantJpaController ctrRestaurant = new RestaurantJpaController(emf);

        int idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
        Users user = (Users) session.getAttribute("usuario");
        Restaurant restaurant = ctrRestaurant.findRestaurant(idRestaurant);

        if (session.getAttribute("usuario") == null) {
            session.setAttribute("error", "Debes estar registrado para reservar un restaurante");
            response.sendRedirect("index.jsp?view=viewOneRestaurant&controller=getOneRestaurant&idRestaurant=" + idRestaurant);
        } else {
            try {
                ReservationsJpaController ctrReservation = new ReservationsJpaController(emf);

                Date dateReserve = t.convertStringToDate(request.getParameter("dateReserve"));
                Date hourReserve = t.parseToTime(request.getParameter("hourReservation"));
                int nPeople = Integer.parseInt(request.getParameter("nPeople"));

                Reservations reserve = new Reservations();

                reserve.setIdUser(user);
                reserve.setIdRestaurant(restaurant);
                reserve.setHour(hourReserve);
                reserve.setPeople(nPeople);
                reserve.setReservationDate(dateReserve);
                reserve.setConfirmation(0);
                reserve.setStatus("Sin Confirmar");

                /*Comprobar si hay ofertas para la fecha de la reserva*/
                OffersJpaController ctrOffers = new OffersJpaController(emf);
                List<Offers> offerList = ctrOffers.findOfferByRestaurant(restaurant);

                if (offerList != null) {

                    for (Offers offer : offerList) {
                        if (!offer.getStartDate().after(dateReserve) && !offer.getEndDate().before(dateReserve)) {
                            reserve.setDiscount(offer.getPercentage());
                        }
                    }

                }
                /*----------------------------------------------------*/

                ctrReservation.create(reserve);
                session.setAttribute("content", "misReservas");

                String message = "Se ha regitrado su reserva, en breve el "
                        + "restaurante " + restaurant.getNameRestaurant() + " se "
                        + "pondra en contacto con usted para confirmar su "
                        + "reserva. Si lo desea pongase en contacto con el "
                        + "restaurante: <br> Email: " + restaurant.getEmail() + "<br>"
                        + "Tlf: " + restaurant.getPhone();

                emailSend.send(user.getEmail(), "Reserva Registrada", message);

                String messageRestaurant = "Se ha realizado una reserva del "
                        + "usuario " + user.getName() + " " + user.getSubname() + " en su "
                        + "restaurante, por favor dirijase a su cuenta y acepte/rechace"
                        + " dicha reserva segun su criterio";
                emailSend.send(restaurant.getEmail(), "Reserva realizada", messageRestaurant);
                session.setAttribute("message", "La reserva se realizo correctamente");

                response.sendRedirect("index.jsp?view=userPanel");
            } catch (Exception e) {
                session.setAttribute("error", "Se produjo un error al realizar la reserva, intentelo más tarde");
                response.sendRedirect("index.jsp?view=viewOneRestaurant&controller=getOneRestaurant&idRestaurant=" + idRestaurant);
            }
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
