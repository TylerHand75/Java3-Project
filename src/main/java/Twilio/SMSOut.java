package Twilio;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SMSOut", value = "/send-message")
public class SMSOut extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");
        Map<String, String> results = new HashMap<>();

        String messageOption = request.getParameter("options");

        if (messageOption.equals("textMessage")) {
            if (!results.containsKey("messageError") && results.get("messageError").equals("")) {
                Twilio twilio = new Twilio();
                results.put("message", twilio.sendTextMessage(phone, message));
            }
        } else if (messageOption.equals("voiceMail")) {
            if (!results.containsKey("messageError") && results.get("messageError").equals("")) {
                Twilio twilio = new Twilio();
                results.put("message", twilio.sendPhoneCall(phone, message));
            }
        } else {
            results.put("message", "Please select text message or phone call");
        }


        results.put("phone", phone);
        results.put("message", message);
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
    }
}
