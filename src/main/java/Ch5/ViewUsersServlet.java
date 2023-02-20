package Ch5;

import dataAccess.DAO_MySql;
import dataAccess.UserDAO_MySql;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ViewUsersServlet", value = "/view-users")
public class ViewUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO_MySql userData = new UserDAO_MySql();
            userData.getAll();
            request.setAttribute("users",userData.getAll());
            request.getRequestDispatcher("WEB-INF/viewusers.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
