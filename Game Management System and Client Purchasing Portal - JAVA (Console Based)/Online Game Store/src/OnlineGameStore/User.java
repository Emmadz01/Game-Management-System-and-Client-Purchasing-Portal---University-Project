package OnlineGameStore;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class User implements Serializable {

    protected String name, email, password, userRole;
    protected int smsNumber;

    public User() {
        this.name = null;
        this.email = null;
        this.password = null;
        this.smsNumber = -1;
    }

    public Boolean emailLogin(ArrayList<Customer> customerList, GameLibrary gameLibrary) {

        Login login = new Email();

        return login.Login(customerList, gameLibrary);
    }

    public Boolean admin_emailLogin(ArrayList<Admin> adminList, GameLibrary gameLibrary) {

        Login login = new Email();
        return login.adminLogin(adminList, gameLibrary);
    }

    public Boolean smsLogin(ArrayList<Customer> customerList, GameLibrary gameLibrary) {

        Login login = new SMS();
        return login.Login(customerList, gameLibrary);
    }

    public Boolean admin_smsLogin(ArrayList<Admin> adminList, GameLibrary gameLibrary) {

        Login login = new SMS();
        return login.adminLogin(adminList, gameLibrary);
    }

    public Boolean register(ArrayList arrayList, String role) {

        String _name, _email, _password;
        int _smsNumber;
        Boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        System.out.print("\t Enter Name: ");
        _name = scanner.next();

        int x;

        ArrayList<User> userList = arrayList;

        do {
            System.out.print("\t Enter Email Address: ");
            _email = scanner.next();

            if (!(userList.isEmpty())) {

                for (x = 0; x < (userList.size()); x++) {

                    if (_email.equals(userList.get(x).getEmailAddress())) {
                        flag = null;
                        System.out.println("\n\t Account with same Email Address already exists | Please try again");
                    }
                }
            }

            if (flag != null) {
                int email_length = _email.length();

                x = 0;

                while (flag && ((x + 1) <= email_length)) {

                    if (_email.substring(x, (x + 1)).equals("@")) {
                        flag = false;
                    }

                    x++;
                }

                if (flag) {
                    System.out.println("\t Email Address should contain '@' | Please try again\n");
                } else {

                    if (_email.charAt(0) == '@') {

                        System.out.println("\t Invalid Email Address | Please try again\n");
                        flag = true;
                    }
                }
            } else {
                flag = true;
            }

        } while (flag);

        do {
            flag = false;

            System.out.print("\t Enter SMS Number: ");
            _smsNumber = scanner.nextInt();

            if (!(userList.isEmpty())) {

                for (x = 0; x < (userList.size()); x++) {

                    if (_smsNumber == userList.get(x).getSMSNumber()) {
                        flag = true;
                        System.out.println("\n\t Account with same SMS Number already exists | Please try again");
                    }
                }
            }

        } while (flag);

        flag = true;

        do {
            System.out.print("\n\t Enter Password: ");
            _password = scanner.next();

            if (_password.length() < 7) {
                System.out.println("\t Password should be atleast 7 characters long");
            } else if (_password.length() > 25) {
                System.out.println("\t Password should not be greater than 25 characters long");
            } else {
                System.out.print("\t Confirm Password: ");
                if (_password.equals(scanner.next())) {
                    this.name = _name;
                    this.email = _email;
                    this.password = _password;
                    this.smsNumber = _smsNumber;
                    this.userRole = role;
                    flag = false;
                    
                    Serialization si = new Serialization(this);

                    if (si.addUser()) {
                        System.out.println("\n\t You have been registered successfully | Login to access your account");
                    } else {
                        System.out.println("\n\t Unexpected Error Occurred | Please Try Again");
                    }

                } else {
                    flag = true;
                    System.out.println("\n\t Passwords do not match | Please try again.");
                }
            }
        } while (flag);

        return !flag;

    }

    public String getName() {
        return this.name;
    }

    public String getEmailAddress() {
        return this.email;
    }

    public int getSMSNumber() {
        return this.smsNumber;
    }

    public Customer getCustomerObject(ArrayList<Customer> userList, String email) {

        for (int x = 0; x < (userList.size()); x++) {

            if (email.equals(userList.get(x).getEmailAddress())) {
                return userList.get(x);
            }
        }

        return null;
    }

    public Customer getCustomerObject(ArrayList<Customer> userList, int smsNumber) {

        for (int x = 0; x < (userList.size()); x++) {

            if (smsNumber == userList.get(x).getSMSNumber()) {
                return userList.get(x);
            }
        }

        return null;
    }
    
    public String getUserRole(){
        return this.userRole;
    }
}
