package Ch5;

import FunStuff.Message.AzureCommunication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserProfileServlet", value = "/profile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        User user = (User) session.getAttribute("user");
        AzureCommunication.sendMail(user.getEmail(), "Welcome to the site!", "Welcome to the site, " + user.getFirst_name()+ "" + user.getLast_name() + "!");

        session.setAttribute("example", new Example("Test"));
        request.getRequestDispatcher("WEB-INF/ch5/user-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
