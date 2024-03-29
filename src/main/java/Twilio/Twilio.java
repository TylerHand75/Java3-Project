package Twilio;

import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Random;

public class Twilio {
    private String fromPhone;
    private String sid;
    private String secretKey;

    public Twilio() {
        Dotenv dotenv = Dotenv.load();
        fromPhone = dotenv.get("TWILIO_PHONE");
        sid = dotenv.get("TWILIO_SID");
        secretKey = dotenv.get("TWILIO_KEY");
    }

    public String sendPhoneCall(String toPhone, String callMsg) {
        try {
            Call call = Call.creator(
                            new com.twilio.type.PhoneNumber(toPhone),
                            new com.twilio.type.PhoneNumber(fromPhone),
                            new com.twilio.type.Twiml("<Response><Say voice='alice'>" + callMsg + "</Say></Response>"))
                    .create();

            return "Phone call successfully made";
        } catch (ApiException e) {
            return e.getMessage();
        }
    }

    public String sendTextMessage(String phone, String msg) {
        com.twilio.Twilio.init(sid, secretKey);
        if (!isValidUsPhoneNumber(phone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (!isValidMessage(msg)) {
            throw new IllegalArgumentException("Invalid characters detected in the message");
        }
        if (phone.charAt(0) != '1') {
            phone = "1" + phone;
        }

        try {
            Message message = Message.creator(new PhoneNumber("+" + phone),
                    new PhoneNumber(fromPhone),
                    msg).create();
        } catch (ApiException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return phone;
    }

    public boolean isValidMessage(String message) {
        if (message.contains("fu**")) {
            return false;
        }
        return true;
    }

    public boolean isValidUsPhoneNumber(String phone) {
        if (phone.matches("^(1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$")) {
            return true;
        }
        return false;
    }

    public String sendTwoFactorCode(String phone) {
        com.twilio.Twilio.init(sid, secretKey);
        if (!isValidUsPhoneNumber(phone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (phone.charAt(0) != '1') {
            phone = "1" + phone;
        }

        // generate a 6-digit code
        String code = String.format("%06d", new Random().nextInt(999999));

        // send the code via SMS
        try {
            Message message = Message.creator(new PhoneNumber("+" + phone),
                    new PhoneNumber(fromPhone),
                    "Your verification code is: " + code).create();
        } catch (ApiException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return code;
    }
}

