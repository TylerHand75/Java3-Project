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
        String call = request.getParameter("call");
        String voiceMail = request.getParameter("voice mail");
        Map<String,String> results = new HashMap<>();
        Twilio twilio = new Twilio();
        try {
            twilio.sendTextMessage(phone, message);
            results.put("messageSuccess", "Message Sent");

        } catch(IllegalArgumentException e) {
            results.put("messageError", e.getMessage());
            results.put("phone", phone);
            results.put("message", message);
        }
        try {
            twilio.sendPhoneCall(call,voiceMail);
            results.put("messageSuccess", "Call made");

        } catch (IllegalArgumentException e) {
            results.put("messageError", e.getMessage());
            results.put("call", call);
            results.put("voiceMail", voiceMail);
        }
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
    }
}
