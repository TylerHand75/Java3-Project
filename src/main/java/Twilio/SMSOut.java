package Twilio;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "SMSOut", value = "/send-sms")
public class SMSOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dotenv dotenv = Dotenv.configure().load();
        System.out.println(dotenv.get("TWILIO_PHONE"));
        System.out.println(dotenv.get("TWILIO_SID"));
        System.out.println(dotenv.get("TWILIO_KEY"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
