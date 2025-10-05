package com.example.HostelManagement.service;
import com.example.HostelManagement.model.Hosteler;
import com.example.HostelManagement.model.Payment;
import com.example.HostelManagement.repository.HostelerRepository;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private HostelerRepository hostelerRepository;

    public void sendReceipt(Payment payment) {
        Optional<Hosteler> hostelerOpt = hostelerRepository.findById(payment.getHostelerId());
        if (hostelerOpt.isEmpty()) {
            throw new RuntimeException("Hosteler not found");
        }

        Hosteler hosteler = hostelerOpt.get();
        Mail mail = buildReceiptMail(hosteler, payment);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Email sent: " + response.getStatusCode());
        } catch (IOException ex) {
            throw new RuntimeException("Failed to send email", ex);
        }
    }
    private Mail buildReceiptMail(Hosteler hosteler, Payment payment) {
        Email from = new Email("atharvachobe72@gmail.com");
        String subject = "Payment receipt for : " + payment.getMonth();
        Email to = new Email(hosteler.getEmail());
        Content content = new Content("text/plain",
                "Hi, " + hosteler.getName() + "\n\nYour payment was successful for the month of: "
                        + payment.getMonth() + "\nPayment ID: " + payment.getId()
                        + "\n\nNote: This is a system generated email.");
        return new Mail(from, subject, to, content);
    }
}