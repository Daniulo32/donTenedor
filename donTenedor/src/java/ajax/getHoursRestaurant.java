/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import DAO.ReservationsJpaController;
import DAO.RestaurantJpaController;
import DTO.Reservations;
import DTO.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.tools;

/**
 *
 * @author danieljimenez
 */
public class getHoursRestaurant extends HttpServlet {

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
        
        tools t = new tools();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        RestaurantJpaController ctrRestaurant = new RestaurantJpaController(emf);
        
        int idrestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
        Date date = t.convertStringToDate(request.getParameter("date"));
        
        Restaurant restaurant = ctrRestaurant.findRestaurant(idrestaurant);
        
        String hourOpen = t.convertHourToString(restaurant.getScheduleOpen());
        String hourClose = t.convertHourToString(restaurant.getScheduleClose());
        
        ArrayList listHour = new ArrayList();
        String[] hourMinutOpen = hourOpen.split(":");
        String[] hourMinutClose = hourClose.split(":");
        
        listHour.add(hourOpen);
        
        int firstHour = Integer.parseInt(hourMinutOpen[0]);
        int lastHour = Integer.parseInt(hourMinutClose[0]);
        
        if(lastHour == 00){
            lastHour = 24;
        }
        
        boolean minut;
        if(Integer.parseInt(hourMinutOpen[1]) >= 30){
            minut = true;
        }else{
            minut = false;
        }
        
        while(firstHour < lastHour){
            if(!minut){
                String hour = firstHour+":"+"30";
                listHour.add(hour);
                firstHour++;
                minut = true;
            }else{
                String hour = firstHour+":"+"00";
                listHour.add(hour);
                minut = false;
            }
        }
        
        ReservationsJpaController ctrReservation = new ReservationsJpaController(emf);
        
        ArrayList<Reservations> listReservations = (ArrayList<Reservations>) ctrReservation.findReservationsRestaurantDate(date, idrestaurant);
        
        if(!listReservations.isEmpty()){
            for( Reservations reservation : listReservations){
                String hora = t.convertHourToString(reservation.getHour());
                if(listHour.contains(hora)){
                    listHour.remove(hora);
                }
            }
        }
        
        String horas = "";
        
        for (int i = 0; i < listHour.size(); i++) {
            if(i != listHour.size()-1){
                horas = horas+listHour.get(i)+",";
            }else{
                horas = horas+listHour.get(i);
            }
        }
        
        out.print(horas);
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
