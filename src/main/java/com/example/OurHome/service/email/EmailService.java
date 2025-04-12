package com.example.OurHome.service.email;

import com.example.OurHome.model.dto.BindingModels.InputDataBindingModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(InputDataBindingModel inputDataBindingModel) {


        String mailSubject = "Актуализация на данни за домова книга - ап." + inputDataBindingModel.getPropertyNumber();

        // Създаваме контекст с Thymeleaf променливи
        Context context = new Context();

        if (!inputDataBindingModel.getPropertyNumber().isEmpty()) {
            context.setVariable("propertyNo", inputDataBindingModel.getPropertyNumber());
        }

        //Owner
        if (!inputDataBindingModel.getOwnerName().isEmpty()) {
            context.setVariable("ownerName", inputDataBindingModel.getOwnerName());
        }
        if (!inputDataBindingModel.getOwnerPhone().isEmpty()) {
            context.setVariable("ownerPhone", inputDataBindingModel.getOwnerPhone());
        }
        if (!inputDataBindingModel.getOwnerEmail().isEmpty()) {
            context.setVariable("ownerEmail", inputDataBindingModel.getOwnerEmail());
        }

        //Adult 1
        if (!inputDataBindingModel.getAdult1Name().isEmpty()) {
            context.setVariable("adult1Name", inputDataBindingModel.getAdult1Name());
        }
        if (!inputDataBindingModel.getAdult1Phone().isEmpty()) {
            context.setVariable("adult1Phone", inputDataBindingModel.getAdult1Phone());
        }
        if (!inputDataBindingModel.getAdult1Email().isEmpty()) {
            context.setVariable("adult1Email", inputDataBindingModel.getAdult1Email());
        }

        //Adult 2
        if (!inputDataBindingModel.getAdult2Name().isEmpty()) {
            context.setVariable("adult2Name", inputDataBindingModel.getAdult2Name());
        }
        if (!inputDataBindingModel.getAdult2Phone().isEmpty()) {
            context.setVariable("adult2Phone", inputDataBindingModel.getAdult2Phone());
        }

        //Adult 3
        if (!inputDataBindingModel.getAdult3Name().isEmpty()) {
            context.setVariable("adult3Name", inputDataBindingModel.getAdult3Name());
        }
        if (!inputDataBindingModel.getAdult3Phone().isEmpty()) {
            context.setVariable("adult3Phone", inputDataBindingModel.getAdult3Phone());
        }

        //Adult 4
        if (!inputDataBindingModel.getAdult4Name().isEmpty()) {
            context.setVariable("adult4Name", inputDataBindingModel.getAdult4Name());
        }
        if (!inputDataBindingModel.getAdult4Phone().isEmpty()) {
            context.setVariable("adult4Phone", inputDataBindingModel.getAdult4Phone());
        }

        //Adult 5
        if (!inputDataBindingModel.getAdult5Name().isEmpty()) {
            context.setVariable("adult5Name", inputDataBindingModel.getAdult5Name());
        }
        if (!inputDataBindingModel.getAdult5Phone().isEmpty()) {
            context.setVariable("adult5Phone", inputDataBindingModel.getAdult5Phone());
        }

        //Child 1
        if (!inputDataBindingModel.getChild1Name().isEmpty()) {
            context.setVariable("child1Name", inputDataBindingModel.getChild1Name());
        }
        if (!inputDataBindingModel.getChild1Age().isEmpty()) {
            context.setVariable("child1Age", inputDataBindingModel.getChild1Age());
        }

        //Child 2
        if (!inputDataBindingModel.getChild2Name().isEmpty()) {
            context.setVariable("child2Name", inputDataBindingModel.getChild2Name());
        }
        if (!inputDataBindingModel.getChild2Age().isEmpty()) {
            context.setVariable("child2Age", inputDataBindingModel.getChild2Age());
        }

        //Child 3
        if (!inputDataBindingModel.getChild3Name().isEmpty()) {
            context.setVariable("child3Name", inputDataBindingModel.getChild3Name());
        }
        if (!inputDataBindingModel.getChild3Age().isEmpty()) {
            context.setVariable("child3Age", inputDataBindingModel.getChild3Age());
        }

        //Child 4
        if (!inputDataBindingModel.getChild4Name().isEmpty()) {
            context.setVariable("child4Name", inputDataBindingModel.getChild4Name());
        }
        if (!inputDataBindingModel.getChild4Age().isEmpty()) {
            context.setVariable("child4Age", inputDataBindingModel.getChild4Age());
        }

        //Child 5
        if (!inputDataBindingModel.getChild5Name().isEmpty()) {
            context.setVariable("child5Name", inputDataBindingModel.getChild5Name());
        }
        if (!inputDataBindingModel.getChild5Age().isEmpty()) {
            context.setVariable("child5Age", inputDataBindingModel.getChild5Age());
        }
        if (inputDataBindingModel.getNumberOfPets() != null) {
            context.setVariable("numberOfPets", inputDataBindingModel.getNumberOfPets());
        }

        // Генерираме HTML съдържанието
        String emailContent = templateEngine.process("emails/new-email", context);
        sendHtmlEmail("office.ourhome@gmail.com", mailSubject, emailContent);
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true -> HTML формат

            emailSender.send(message);
        } catch (MessagingException e) {
        }
    }
}
