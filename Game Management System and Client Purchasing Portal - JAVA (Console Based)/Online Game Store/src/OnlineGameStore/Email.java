package OnlineGameStore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class Email extends Login {

    public void sendEmail(ArrayList<Game> gameList, double totalPrice) {
        final String username = "";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            String email = "emmad.zahid15@gmail.com";

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("Receipt - Game Purchased");

            Multipart multipart = new MimeMultipart();

            // Add the text part
            MimeBodyPart textPart = new MimeBodyPart();

            String temp = "           Online Game Store        " + "\n";

            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            if (!gameList.isEmpty()) {
                for (int x = 0; x < (gameList.size()); x++) {
                    temp += "-----------------------------------------------------------------" + "\n";
                    temp += "Game " + (x + 1) + ":\n\n";
                    temp += "\tGame Name: " + gameList.get(x).getGameName() + "\n";
                    temp += "\tGame Price: $" + decimalFormat.format(gameList.get(x).getGamePrice()) + "\n\n";
                }
            }

            temp += "\nTransaction Total Amount: $" + decimalFormat.format(totalPrice);
            temp += "\n\nThanks for purchasing from us!";

            textPart.setText(temp);

            multipart.addBodyPart(textPart);

            MimeBodyPart attachmentPart = new MimeBodyPart();

            DataSource source = new FileDataSource("D:\\Riphah\\Semester_5\\ACP\\Project\\game.jpg");
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("game1.jpg");
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("\n\tEmail sent to " + email + "\n");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean Login(ArrayList<Customer> customerList, GameLibrary gameLibrary) {

        Boolean loginSuccess = false;
        String email, password;
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter Email Address: ");
        email = scanner.next();

        System.out.print("\t Enter Password: ");
        password = scanner.next();

        if (customerList.isEmpty()) {
            System.out.println("\t Account does not exist | Please register your account");
            loginSuccess = false;
        } else {

            for (int x = 0; x < (customerList.size()); x++) {

                if (customerList.get(x).email.equals(email)) {
                    if (customerList.get(x).password.equals(password)) {

                        loginSuccess = true;
                        System.out.println("\n\t Login SUCCESS\n");

                        customerList.get(x).home(gameLibrary);

                        return loginSuccess;
                    } else {
                        loginSuccess = false;
                    }
                } else {
                    loginSuccess = false;
                }

                if (!loginSuccess && (x == customerList.size() - 1)) {
                    System.out.println("\n\t Incorrect Email Address or Password | Please try again");
                }
            }
        }

        return loginSuccess;
    }

    @Override
    public Boolean adminLogin(ArrayList<Admin> adminList, GameLibrary gameLibrary) {

        Boolean loginSuccess = false;
        String email, password;
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter Email Address: ");
        email = scanner.next();

        System.out.print("\t Enter Password: ");
        password = scanner.next();

        if (adminList.isEmpty()) {
            System.out.println("\t Account does not exist | Please register your account");
            loginSuccess = false;
        } else {

            for (int x = 0; x < (adminList.size()); x++) {

                if (adminList.get(x).email.equals(email)) {
                    if (adminList.get(x).password.equals(password)) {

                        loginSuccess = true;
                        System.out.println("\n\t Login SUCCESS\n");

                        adminList.get(x).home(gameLibrary);

                        return loginSuccess;
                    } else {
                        loginSuccess = false;
                    }
                } else {
                    loginSuccess = false;
                }

                if (!loginSuccess && (x == adminList.size() - 1)) {
                    System.out.println("\n\t Incorrect Email Address or Password | Please try again");
                }
            }
        }

        return loginSuccess;
    }
}
