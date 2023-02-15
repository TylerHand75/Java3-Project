package Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

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

    public void sendTextMessage(String phone, String msg) {
        com.twilio.Twilio.init(sid, secretKey);
        if (!isValidUsPhoneNumber(phone)){
            throw new IllegalArgumentException("Invalid Phone number");
        }
        if(!isValidMessage(msg)) {
            throw new IllegalArgumentException("Invalid characters detected in the message");
        }
        if (!isValidMessage(msg)){
            throw new IllegalArgumentException("Invalid bad words");
        }


        if (phone.charAt(0) != '1'){
            phone = "1" + phone;
        }

        Message message = Message.creator(new PhoneNumber("+1" + phone),
                new PhoneNumber(fromPhone),
                msg).create();
    }
    public boolean isValidMessage(String message) {
        if(message.contains("fu**")) {
            return false;
        }
        return true;
    }

    public Boolean isValidUsPhoneNumber(String phone) {
    if (phone.matches("^(1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$")){
        return true;
        }
    return false;
    }


}
