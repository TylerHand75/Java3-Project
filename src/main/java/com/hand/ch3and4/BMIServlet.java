package com.hand.ch3and4;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(
        name = "BMIServlet"
//        , value = "/add"
        , urlPatterns = {"/Bmi", "/bmi", "/body-mass"}
        , loadOnStartup = 1
)

public class BMIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/Bmi.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        Map<String, String> results = new HashMap<>();
        Person person = new Person();
        try {
            person.setHeightInInches(Double.parseDouble(height));
        }catch (NumberFormatException ex){
           results.put("HeightError","Invalid Height");
        }catch (IllegalArgumentException e){
            results.put("HeightError", e.getMessage());
        }
        try {
            person.setWeightInPounds(Double.parseDouble(weight));
        }catch (NumberFormatException ex){
            results.put("WeightError","Invalid Weight");
        }catch (IllegalArgumentException e){
            results.put("WeightError", e.getMessage());
        }
        if (!results.containsKey("HeightError") && !results.containsKey("WeightError")){
            results.put("bmi",person.getBMI());
        }

        results.put("height",height);
        results.put("weight",weight);

        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/Bmi.jsp").forward(request, response);

    }


}
