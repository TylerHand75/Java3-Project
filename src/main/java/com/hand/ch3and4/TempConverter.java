package com.hand.ch3and4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

@WebServlet(name = "TempConverter", value = "/TempConverter")
public class TempConverter extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/temp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempConverted = request.getParameter("temp");


        {
            String temperature = request.getParameter("temperature");
            String conversion = request.getParameter("conversion");

            String result = "";
            if(conversion.equals("celsius")){
                result = convertCelsius(temperature);
                request.setAttribute("result",result);
            } else if (conversion.equals("fahrenheit")) {
                result = convertFahrenheit(temperature);
                request.setAttribute("result", result);
            }else{
                result = "An error has occured";
                request.setAttribute("result",result);
            }

            request.getRequestDispatcher("/WEB-INF/temp.jsp").forward(request,response);
        }
    }
    private boolean checkInput(String input){
        try{
            Double.parseDouble(input);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    // convert to Fahrenheit
    private String convertFahrenheit(String input){
        String result = "";
        double conversion = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        if(checkInput(input)){
            Double startTemp = Double.parseDouble(input);
            conversion = ((startTemp * 9/5) + 32);
        }
        else{
            return "Please enter a valid number";
        }

        if(conversion < -459.67){
            result = "That is below absolute zero, please choose a larger value";
        }else{
            result = df.format(conversion);
        }
        return result;
    }
    // convert to Celsius
    private String convertCelsius(String input){
        String result = "";
        double conversion = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        if(checkInput(input)){
            double startTemp = Double.parseDouble(input);
            conversion = ((startTemp-32) * 5/9);
        }else{
            return "Please enter a valid number";
        }

        if(conversion < -273.15){
            result = "That is below absolute zero, please choose a larger value";
        }else{
            result = df.format(conversion);
        }
        return result;
    }





}
