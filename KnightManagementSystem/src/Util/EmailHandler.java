package Util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.*;

public class EmailHandler extends Handler {
    private final String FROM_EMAIL = "roman.diachuk.oi.2024@lpnu.ua";
    private final String APP_PASSWORD = "bxac qqlx ixwv glsy";
    private Session session;

    public EmailHandler() {
        setupSession();
    }

    private void setupSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");

        this.session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });
    }

    @Override
    public void publish(LogRecord record) {
        // Надсилаємо ТІЛЬКИ SEVERE помилки
        if (record.getLevel().intValue() < Level.SEVERE.intValue()) {
            return;
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(FROM_EMAIL));
            message.setSubject(" CRITICAL ERROR - Knight Management System");

            String body = String.format(
                    " Час: %s\n\n" +
                            " Повідомлення: %s\n\n" +
                            " Клас: %s\n" +
                            " Метод: %s\n\n" +
                            " Стек помилки:\n%s",
                    new java.util.Date(record.getMillis()),
                    record.getMessage(),
                    record.getSourceClassName(),
                    record.getSourceMethodName(),
                    getStackTrace(record.getThrown())
            );

            message.setText(body);
            Transport.send(message);

            System.out.println(" Email про помилку надіслано на " + FROM_EMAIL);

        } catch (MessagingException e) {
            System.err.println(" Помилка відправки email: " + e.getMessage());
        }
    }

    private String getStackTrace(Throwable throwable) {
        if (throwable == null) return "Стека немає";

        StringBuilder sb = new StringBuilder();
        StackTraceElement[] elements = throwable.getStackTrace();
        for (StackTraceElement element : elements) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}
}