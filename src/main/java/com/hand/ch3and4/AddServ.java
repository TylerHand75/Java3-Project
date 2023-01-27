package com.hand.ch3and4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "AddServ",urlPatterns = {"/add" , "/sum" ,"/addition"}, loadOnStartup = 1)
public class AddServ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstNumber = request.getParameter("firstNumber");
        String secondNumber = request.getParameter("secondNumber");
        Map<String, String> results = add(firstNumber, secondNumber);

        request.setAttribute("results", results);
        request.getRequestDispatcher("add.jsp").forward(request, response);
    }
    public Map<String, String> add(String firstNumber, String secondNumber) {
        Map<String, String> results = new HashMap<>();
        boolean firstNumberValid = isANumber(firstNumber);
        boolean secondNumberValid = isANumber(secondNumber);
        if(!firstNumberValid) {
            results.put("firstNumberInvalid", "Invalid number");
        }
        if(!secondNumberValid) {
            results.put("secondNumberInvalid", "Invalid number");
        }
        if (firstNumberValid && secondNumberValid) {
            double num1 = Double.parseDouble(firstNumber);
            double num2 = Double.parseDouble(secondNumber);
            results.put("num1", num1 + "");
            results.put("num2", num2 + "");
            results.put("sum", num1 + num2 + "");
        }
        return results;
    }
    public boolean isANumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
