package OnlineGameStore;

import java.util.ArrayList;
import java.util.Scanner;

public class OnlineGameStore {

    private static ArrayList<Customer> customerList = new ArrayList<>();
    private static ArrayList<Admin> adminList = new ArrayList<>();
    final private static Scanner scanner = new Scanner(System.in);
    final private static GameLibrary library = new GameLibrary();
    final private static Deserialization getData = new Deserialization();

    // Main Classa
    public static void main(String[] args) {
        Boolean flag = true; // Controls do-while loop | False for breaking the loop
        int s_no = 0; // To store Serial Number of the chosen option from the menu by the user


//        Game game1 = new Game("Uncharted", "Game about Nathan Drake", 78, "Adventure, Shooter",
//                "Naughty Dog", "Sony Interactive Entertainment", 7, 2014, 0);
//
//        Game game2 = new Game("Assassin's Creed Syndicate", "Play as an Assassin", 29.99, "Stealth, Action",
//                "Ubisoft", "Ubisoft", 8, 2015, 5);
//
//        Game game3 = new Game("Stray", "Players will play as a cat to escape an unknown area", 25.99, "Adventure, Indie",
//                "BlueTwelve Studio", "Annapurna Interactive", 10, 2022, 10);
//
//        Game game4 = new Game("Marvel's Spider-Man", "Play as Peter Parker to eliminate threats", 23.99, "Adventure, Open-World",
//                "Insomniac Games", "Sony Interactive Entertainment", 9, 2018, 15);
//
//        Game game5 = new Game("Marvel's Spider-Man: Miles Morales", "Play as Miles Morales to eliminate threats", 27.99, "Adventure, Open-World",
//                "Insomniac Games", "Sony Interactive Entertainment", 9, 2020, 0);

        getAdmin_Data();
        getCustomer_Data();
        library.getGames();
        

        do { // Loop will not break until user chooses a valid option from the menu

            printMenu(); // Prints Menu

            s_no = scanner.nextInt();

            switch (s_no) {

                case 1 -> { // Admin
                    userMenu("Admin");
                }

                case 2 -> { // Customer
                    userMenu("Customer");
                }

                case 3 -> {
                    System.out.println("\n\t Exited Program");
                    flag = false;
                }

                default -> {
                    System.out.println("\tInvald Option | Please try again!");
                    flag = true;
                }
            }
        } while (flag);
    }

    private static void userMenu(String role) {

        Boolean flag = true;
        int s_no;

        do {

            System.out.println("\n\t Role Selected: '" + role + "'\n");

            System.out.println("\t 1. Login");
            System.out.println("\t 2. Register");
            System.out.println("\t 3. Exit Role");

            System.out.print("\n\t Enter serial number: ");

            s_no = scanner.nextInt();

            Boolean success;

            switch (s_no) {
                case 1 -> {

                    if (role.equals("Admin")) {
                        do {
                            success = adminLogin();

                            if (!success) {
                                System.out.print("\n\t Enter 1 to try again or any other number to exit: ");
                                s_no = scanner.nextInt();

                                if (s_no != 1) {
                                    System.out.println("\n\t Exited");
                                }
                            } else {
                                s_no = 0;
                            }
                        } while (s_no == 1);

                    } else {
                        do {
                            success = customerLogin();

                            if (!success) {
                                System.out.print("\n\t Enter 1 to try again or any other number to exit: ");
                                s_no = scanner.nextInt();

                                if (s_no != 1) {
                                    System.out.println("\n\t Exited");
                                }
                            } else {
                                s_no = 0;
                            }
                        } while (s_no == 1);
                    }
                }

                case 2 -> {

                    do {
                        success = register(role);

                        if (!success) {
                            System.out.print("\n\t Enter 1 to try again or any other number to exit: ");
                            s_no = scanner.nextInt();

                            if (s_no != 1) {
                                System.out.println("\n\t Exited");
                            }
                        } else {
                            s_no = 0;
                        }
                    } while (s_no == 1);
                }

                case 3 -> {
                    flag = false;
                    System.out.println("\n\t Exited Role");
                }
                default -> {
                    System.out.println("Invald Option | Please try again!");
                }
            }
        } while (flag);
    }

    private static void printMenu() {

        System.out.println("\n\n\t\t Online Game Store\n");

        System.out.println("\t Select your role\n");
        System.out.println("\t 1. Admin");
        System.out.println("\t 2. Customer");
        System.out.println("\t 3. Exit Program\n");

        System.out.print("\t Enter serial number: ");
    }

    private static Boolean customerLogin() {

        Customer customer = new Customer();

        Boolean flag;

        System.out.println("\n\t 1. Login by Email Address");
        System.out.println("\t 2. Login by SMS");

        System.out.print("\n\t Enter Serial Number: ");
        int s_no = scanner.nextInt();

        switch (s_no) {
            case 1 -> {
                flag = customer.emailLogin(customerList, library);
            }

            case 2 -> {
                flag = customer.smsLogin(customerList, library);
            }

            default -> {
                System.out.println("\n\t Invalid Input | Please try again\n");
                return false;
            }
        }

        return flag;
    }

    private static Boolean adminLogin() {

        Admin admin = new Admin();

        Boolean flag;

        System.out.println("\n\t 1. Login by Email Address");
        System.out.println("\t 2. Login by SMS");

        System.out.print("\n\t Enter Serial Number: ");
        int s_no = scanner.nextInt();

        switch (s_no) {
            case 1 -> {
                flag = admin.admin_emailLogin(adminList, library);
            }

            case 2 -> {
                flag = admin.admin_smsLogin(adminList, library);
            }

            default -> {
                System.out.println("\n\t Invalid Input | Please try again\n");
                return false;
            }
        }

        return flag;
    }

    // Method to Register a new Customer
    private static Boolean register(String role) {
        Boolean success;

        if (role.equals("Admin")) {

            Admin newAdmin = new Admin();

            success = newAdmin.register(adminList, "Admin");

            if (success) {
                adminList.add(newAdmin);
                getAdmin_Data();
            }

        } else {

            Customer newCustomer = new Customer();

            success = newCustomer.register(customerList, "Customer");

            if (success) {
                customerList.add(newCustomer);
                getCustomer_Data();
            }
        }

        return success;
    }
    // End of Method

    private static void getAdmin_Data() {
        adminList = getData.getAdmin_Data();       
    }

    private static void getCustomer_Data() {
        customerList = getData.getCustomer_Data();
    }
}
