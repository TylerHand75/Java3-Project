package Twilio;

import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;

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

    public void sendPhoneCall(String fromPhone, String callMsg) {
        com.twilio.Twilio.init(sid, secretKey);
        Call call = Call.creator(
                        new com.twilio.type.PhoneNumber("+14155551212"),
                        new com.twilio.type.PhoneNumber(fromPhone),
                        new com.twilio.type.Twiml("<Response><Say>Ahoy, World!</Say></Response>"))
                .create();

        Say say = new Say.Builder("Chapeau!").voice(Say.Voice.WOMAN)
                .language(Say.Language.EN_CA).build();
        VoiceResponse response = new VoiceResponse.Builder().say(say).build();


        try {
            System.out.println(response.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }


        if (!isValidUsPhoneNumber(fromPhone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (!isValidMessage(callMsg)) {
            throw new IllegalArgumentException("Invalid characters detected in the message");
        }
        if (fromPhone.charAt(0) != '1') {
            fromPhone = "1" + fromPhone;
        }

        try {
            Message message = Message.creator(new PhoneNumber("+" + fromPhone),
                    new PhoneNumber(fromPhone),
                    callMsg).create();
        } catch (ApiException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public void sendTextMessage(String phone, String msg) {
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


}
