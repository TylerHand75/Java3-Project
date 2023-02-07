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
        request.getRequestDispatcher("WEB-INF/Temp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempConverted = request.getParameter("temp");


        getServletContext().getRequestDispatcher("/temp.jsp").forward(request, response);


        request.getRequestDispatcher("WEB-INF/Temp.jsp").forward(request, response);

        String path = "";
        if(request.getParameter("fToC") != null) { // F to C button is pressed
            path = "ftoc.jsp";
        }
        else if (request.getParameter("cToF") != null) { // C to F button is pressed
            path = "ctof.jsp";
        }

        try (PrintWriter out = response.getWriter()) {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }





}
