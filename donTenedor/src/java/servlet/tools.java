/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author danieljimenez
 */
public class tools {

    public tools() {
        //empty constructor
    }

    public Date convertStringToDate(String dateString) {
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");

        try {
            date = df.parse(dateString);
        } catch (Exception ex) {

        }
        return date;
    }
    
    public String convertDateToString(Date date) {
        String dateString = null;
        SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy");

        try {
            dateString = df.format(date);
        } catch (Exception ex) {

        }
        return dateString;
    }
    
    public String convertHourToString(Date dateString) {
        String date = null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        try {
            date = df.format(dateString);
        } catch (Exception ex) {

        }
        return date;
    }

    public String encrypterPassword(String password) {
        byte[] encoded = Base64.encodeBase64(password.getBytes());
        String passwordEncryption = new String(encoded);
        return passwordEncryption;
    }

    public String formatDaysOpen(String[] daysOpen) {
        String diasAbiertos = "";

        for (int i = 0; daysOpen.length > i; i++) {
            if (i == 0) {
                diasAbiertos += daysOpen[i];
            } else {
                diasAbiertos += "-" + daysOpen[i];
            }
        }
        return diasAbiertos;
    }

    public Date parseToTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date hora = sdf.parse(time);
        return hora;
    }
    
    public static Date maxTime(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 23);  
        cal.set(Calendar.MINUTE, 59);  
        cal.set(Calendar.SECOND, 59);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime(); 
    }

}
