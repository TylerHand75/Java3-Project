package FinalTest;
import Ch5.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;
// STEP 1: Define the servlet's url path
@WebServlet(name = "NewsletterServlet", value = "/newsletter")
public class NewsletterServlet extends HttpServlet {
    private static final Set<String> subscribers = Collections.synchronizedSet(new HashSet<>());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // STEP 10: Get an HttpSession object
        HttpSession session = null;
        // STEP 11: Replace null below by getting the user attribute from the session
        User user = (User) session.getAttribute("user");
        // STEP 12: Replace false below with an expression that checks if the user is not null and is an admin
        if(user != null && user.getPrivileges().equals("admin")) {
            // STEP 13: Set the Set of subscribers as an attribute on the request object
            request.setAttribute("subscribers",subscribers);

        }
        // STEP 3: forward the request and response objects to the JSP
        request.getRequestDispatcher("WEB-INF/FinalTest/newsletter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // STEP 5: Replace the empty string below by getting a parameter from the form submission

        String email = "newsletter";
        User user = new User();
        try {
            user.setEmail(email);
            // STEP 6: Add the email to the Set object
            subscribers.add(email);
            request.setAttribute("message", "<div class=\"alert alert-success\">You have been added to our mailing list</div>");
        } catch(IllegalArgumentException e) {
            // STEP 7: Set the email as an attribute on the request object
            request.setAttribute("email",email);
            request.setAttribute("message", "<div class=\"alert alert-danger\">" + e.getMessage() + "</div>");
        }
        // STEP 8: forward the request and response objects to the JSP
        request.getRequestDispatcher("WEB-INF/FinalTest/newsletter.jsp").forward(request, response);
    }
}


