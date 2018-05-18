/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.RestaurantJpaController;
import DTO.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class searchRestaurant extends HttpServlet {

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
        List<Restaurant> listaRestaurantes = null;
        
        String type = request.getParameter("type");
        int idProvince = Integer.parseInt(request.getParameter("idProvincia"));
        int idPoblacion = Integer.parseInt(request.getParameter("idPoblacion"));
        
        if (!type.equals("any") && idProvince == 0 && idPoblacion == 0) {
            //busqueda por tipo
            listaRestaurantes = ctrRestaurant.findrestaurantType(type);
        }else if(type.equals("any") && idProvince != 0 && idPoblacion == 0){
            //busqueda por provincia
            listaRestaurantes = ctrRestaurant.findrestaurantProvince(idProvince);
        }else if(type.equals("any") && idProvince != 0 && idPoblacion != 0){
            //busqueda por provincia y poblacion
            listaRestaurantes = ctrRestaurant.findrestaurantProvinceTown(idProvince, idPoblacion);
        }else if(!type.equals("any") && idProvince != 0 && idPoblacion == 0){
            //busqueda por tipo y provincia
            listaRestaurantes = ctrRestaurant.findrestaurantProvinceType(type, idProvince);
        }else if(!type.equals("any") && idProvince != 0 && idPoblacion != 0){
            //busqueda por tipo,provincia y poblacion
            listaRestaurantes = ctrRestaurant.findrestaurantProvinceTownType(type, idProvince, idPoblacion);
        }
        if(listaRestaurantes != null){
            request.setAttribute("listarestaurantes", listaRestaurantes);
        }else{
            response.sendRedirect("index.jsp?view=principal");
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
