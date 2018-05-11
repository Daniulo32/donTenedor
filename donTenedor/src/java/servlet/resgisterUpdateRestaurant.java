/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.PoblacionJpaController;
import DAO.ProvinciaJpaController;
import DAO.RestaurantJpaController;
import DAO.UsersJpaController;
import DTO.Poblacion;
import DTO.Provincia;
import DTO.Restaurant;
import DTO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author danieljimenez
 */
public class resgisterUpdateRestaurant extends HttpServlet {

    protected void proceso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        tools tool = new tools();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("donTenedorPU");
        HttpSession sesion = request.getSession();

        /*Obteniendo valores del formulario*/
        int idRestaurant = 0;
        if (request.getParameter("idRestaurant") != null && !request.getParameter("idRestaurant").isEmpty()) {
            idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
        }

        double precioMedio = 0;
        if (request.getParameter("halfPrice") != null && !request.getParameter("halfPrice").isEmpty()) {
            precioMedio = Double.parseDouble(request.getParameter("halfPrice"));
        }

        int telefono = 0;
        if (request.getParameter("idRestaurant") != null && !request.getParameter("idRestaurant").isEmpty()) {
            telefono = Integer.parseInt(request.getParameter("telefono"));
        }
        String diasAbiertos = "";
        if (request.getParameterValues("daysOpen") != null) {
            diasAbiertos = tool.formatDaysOpen(request.getParameterValues("daysOpen"));
        }

        String nombrerestaurante = request.getParameter("nameRestaurant");
        String tipoRestaurante = request.getParameter("type");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String observaciones = request.getParameter("observation");

        /*Obtenemos la provincia y la poblacion*/
        int idProvincia = Integer.parseInt(request.getParameter("idProvincia"));
        ProvinciaJpaController ctrProvincia = new ProvinciaJpaController(emf);
        Provincia provincia = ctrProvincia.findProvincia(idProvincia);

        int idPoblacion = Integer.parseInt(request.getParameter("idPoblacion"));
        PoblacionJpaController ctrPoblacion = new PoblacionJpaController(emf);
        Poblacion poblacion = ctrPoblacion.findPoblacion(idPoblacion);

        /*Obtenemos las horas y las convertimos*/
        Date horaApertura = null;
        Date horaCierre = null;
        try {
            horaApertura = tool.parseToTime(request.getParameter("hourOpen"));
            horaCierre = tool.parseToTime(request.getParameter("hourClose"));
        } catch (Exception e) {
            out.print("Error al convertir horas");
        }

        /*Obtenemos los servicios*/
        int servicioDomicilio = 0;
        if (request.getParameter("homeService") != null && !request.getParameter("homeService").isEmpty()) {
            servicioDomicilio = 1;
        }

        int wifi = 0;
        if (request.getParameter("wifi") != null && !request.getParameter("wifi").isEmpty()) {
            wifi = 1;
        }

        int terraza = 0;
        if (request.getParameter("terraza") != null && !request.getParameter("terraza").isEmpty()) {
            terraza = 1;
        }

        int tarjeta = 0;
        if (request.getParameter("tarjeta") != null && !request.getParameter("tarjeta").isEmpty()) {
            tarjeta = 1;
        }

        int minusvalidos = 0;
        if (request.getParameter("minusvalidos") != null && !request.getParameter("minusvalidos").isEmpty()) {
            minusvalidos = 1;
        }

        Users usuario = (Users) sesion.getAttribute("usuario");

        RestaurantJpaController ctrRestaurantes = new RestaurantJpaController(emf);

        Restaurant restaurant = ctrRestaurantes.getRestaurant(usuario);

        try {
            if (restaurant == null) {
                /*Creamos el objeto restaurant y le aplicamos los valores*/
                Restaurant restaurante = new Restaurant();
                restaurante.setNameRestaurant(nombrerestaurante);
                restaurante.setType(tipoRestaurante);
                restaurante.setProvince(provincia);
                restaurante.setTown(poblacion);
                restaurante.setHalfPrice(precioMedio);
                restaurante.setPhone(telefono);
                restaurante.setAddress(direccion);
                restaurante.setEmail(email);
                restaurante.setOpenDays(diasAbiertos);
                restaurante.setScheduleOpen(horaApertura);
                restaurante.setScheduleClose(horaCierre);
                restaurante.setHomeService(servicioDomicilio);
                restaurante.setWifi(wifi);
                restaurante.setTerrace(terraza);
                restaurante.setCardPayment(tarjeta);
                restaurante.setHandicapped(minusvalidos);
                restaurante.setObservations(observaciones);
                restaurante.setIdAdmin(usuario);
                restaurante.setIdRestaurant(null);
                
                ctrRestaurantes.create(restaurante);
                sesion.setAttribute("restaurante", restaurante);
            } else {
                restaurant.setNameRestaurant(nombrerestaurante);
                restaurant.setType(tipoRestaurante);
                restaurant.setProvince(provincia);
                restaurant.setTown(poblacion);
                restaurant.setHalfPrice(precioMedio);
                restaurant.setPhone(telefono);
                restaurant.setAddress(direccion);
                restaurant.setEmail(email);
                restaurant.setOpenDays(diasAbiertos);
                restaurant.setScheduleOpen(horaApertura);
                restaurant.setScheduleClose(horaCierre);
                restaurant.setHomeService(servicioDomicilio);
                restaurant.setWifi(wifi);
                restaurant.setTerrace(terraza);
                restaurant.setCardPayment(tarjeta);
                restaurant.setHandicapped(minusvalidos);
                restaurant.setObservations(observaciones);
                
                ctrRestaurantes.edit(restaurant);
                sesion.setAttribute("restaurante", restaurant);
            }
            sesion.setAttribute("error", "no");
            response.sendRedirect("index.jsp?view=restaurantPanel");
        } catch (Exception e) {
            sesion.setAttribute("error", "yes");
            response.sendRedirect("index.jsp?view=restaurantPanel");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        proceso(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        proceso(req, res);
    }

}
