/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author danieljimenez
 */
public class initApplication extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        // Always call super.init(config) first  (servlet mantra #1)
        super.init(config);
        ServletContext context = getServletContext();
        
        Map nameType = new HashMap();
        nameType.put("espanol", "Español");
        nameType.put("chino", "Chino");
        nameType.put("tapas", "Bar de Tapas");
        nameType.put("italiano", "Italiano");
        nameType.put("indio", "Indio");
        nameType.put("rapida", "Comida Rápida");
        nameType.put("buffet", "Bufffet Libre");
        context.setAttribute("nameType", nameType);
        
    }

}
