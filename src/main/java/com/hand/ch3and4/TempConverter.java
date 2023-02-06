package com.hand.ch3and4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
    }
}
