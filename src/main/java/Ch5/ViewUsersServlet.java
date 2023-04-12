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

        // Add this code in the doGet method of any servlet
        // that you want users to log in first before viewing
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        System.out.println(session.getMaxInactiveInterval());
        if(session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        UserDAO_MySql user_data = new UserDAO_MySql();
        request.setAttribute("users",user_data.getAll());
        User user = (User) session.getAttribute("user");
        if (user.getPrivileges().equals("admin")) {
            request.getRequestDispatcher("WEB-INF/Ch5/view-users.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
