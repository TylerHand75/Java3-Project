package com.hand.ch3and4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TempConverter", value = "/TempConverter")
public class TempConverter extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/temp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempConverted = request.getParameter("temp");



        if(request.getParameter("fToC") != null) {
            tempConverted = "temp.jsp";
        }
        else if (request.getParameter("cToF") != null) {
            tempConverted = "temp.jsp";
        }

        try (PrintWriter out = response.getWriter()) {
            request.getRequestDispatcher(tempConverted).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }





}
