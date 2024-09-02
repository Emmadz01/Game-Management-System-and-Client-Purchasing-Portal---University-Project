package OnlineGameStore;

import java.util.ArrayList;
import java.util.Scanner;

public class SMS extends Login {

    @Override
    public Boolean Login(ArrayList<Customer> customerList, GameLibrary gameLibrary) {

        Boolean loginSuccess = false;
        String password;
        int smsNumber;
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter SMS Number: ");
        smsNumber = scanner.nextInt();

        System.out.print("\t Enter Password: ");
        password = scanner.next();

        if (customerList.isEmpty()) {
            System.out.println("\t Account does not exist | Please register your account");
            loginSuccess = false;
        } else {

            for (int x = 0; x < (customerList.size()); x++) {

                if (customerList.get(x).smsNumber == smsNumber) {
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
                    System.out.println("\n\t Incorrect SMS Number or Password | Please try again");
                }
            }
        }

        return loginSuccess;
    }

    @Override
    public Boolean adminLogin(ArrayList<Admin> adminList, GameLibrary gameLibrary) {

        Boolean loginSuccess = false;
        String password;
        int smsNumber;
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter SMS Number: ");
        smsNumber = scanner.nextInt();

        System.out.print("\t Enter Password: ");
        password = scanner.next();

        if (adminList.isEmpty()) {
            System.out.println("\t Account does not exist | Please register your account");
            loginSuccess = false;
        } else {

            for (int x = 0; x < (adminList.size()); x++) {

                if (adminList.get(x).smsNumber == smsNumber) {
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
                    System.out.println("\n\t Incorrect SMS Number or Password | Please try again");
                }
            }
        }

        return loginSuccess;
    }
}
