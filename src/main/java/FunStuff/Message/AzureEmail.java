package FunStuff.Message;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.communication.email.models.EmailSendStatus;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;

public class AzureEmail {
    private static final Duration POLLER_WAIT_TIME = Duration.ofSeconds(10);

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String connectionString = "endpoint=https://" + dotenv.get("AZURE_EMAIL_RESOURCE") + ".communication.azure.com/;accesskey=" + dotenv.get("AZURE_EMAIL_KEY");

        EmailClient emailClient = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        EmailMessage message = new EmailMessage()
                .setSenderAddress(dotenv.get("AZURE_FROM_EMAIL"))
                .setToRecipients("<tylerhand13@gmail.com>") // Change this to the email of the logged in user
                .setSubject("Welcome to Azure Communication Services Email")
                .setBodyPlainText("This email message is sent from Azure Communication Services Email using the Java SDK.");

        try
        {
            SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message, null);

            PollResponse<EmailSendResult> pollResponse = null;

            Duration timeElapsed = Duration.ofSeconds(0);

            while (pollResponse == null
                    || pollResponse.getStatus() == LongRunningOperationStatus.NOT_STARTED
                    || pollResponse.getStatus() == LongRunningOperationStatus.IN_PROGRESS)
            {
                pollResponse = poller.poll();
                System.out.println("Email send poller status: " + pollResponse.getStatus());

                Thread.sleep(POLLER_WAIT_TIME.toMillis());
                timeElapsed = timeElapsed.plus(POLLER_WAIT_TIME);

                if (timeElapsed.compareTo(POLLER_WAIT_TIME.multipliedBy(18)) >= 0)
                {
                    throw new RuntimeException("Polling timed out.");
                }
            }

            if (poller.getFinalResult().getStatus() == EmailSendStatus.SUCCEEDED)
            {
                System.out.printf("Successfully sent the email (operation id: %s)", poller.getFinalResult().getId());
            }
            else
            {
                throw new RuntimeException(poller.getFinalResult().getError().getMessage());
            }
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }

    }
}