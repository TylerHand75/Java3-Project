package Ch5;

<<<<<<< HEAD
import dataAccess.UserDAO_MySQL;
import org.mindrot.jbcrypt.*;

=======
>>>>>>> parent of 02b7134 (Class things)
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/Ch5/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
<<<<<<< HEAD
        UserDAO_MySQL dao = new UserDAO_MySQL();
        User user = dao.getUser(email);
        if(email == null || email.equals("") ||
                password == null || password.equals("") ||
                user == null
        ) {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("WEB-INF/Ch5/login.jsp").forward(request, response);
            return;
        } else {
            // User was found
            if(BCrypt.checkpw(password, String.valueOf(user.getPassword()))) {
                // Match!
                HttpSession session = request.getSession();
                request.changeSessionId();
                user.unsetPassword();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath()); // redirects to the homepage
                return;
            } else {
                // Not match
                request.setAttribute("loginFailed", true);
                request.getRequestDispatcher("WEB-INF/Ch5/login.jsp").forward(request, response);
                return;
            }
=======
        if (email == null || email.equals("") || password == null || password.equals("")) {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("WEB-INF/Ch5/login.jsp").forward(request, response);
            return;
>>>>>>> parent of 02b7134 (Class things)
        }
    }
}
