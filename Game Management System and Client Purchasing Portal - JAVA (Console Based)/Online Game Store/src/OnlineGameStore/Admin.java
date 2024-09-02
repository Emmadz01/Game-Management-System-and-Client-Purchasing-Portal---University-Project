package OnlineGameStore;

import java.util.Scanner;

public class Admin extends User {

    public Admin() {
        this.name = null;
        this.email = null;
        this.password = null;
        this.smsNumber = -1;
    }

    public Admin(String name, String email, int sms, String password) {
        this.name = name;
        this.email = email;
        this.smsNumber = sms;
        this.password = password;
    }

    public void home(GameLibrary library) {

        Boolean flag = true;

        System.out.println("\n\t Admin Dashboard \t Logged in as: " + name + "\n");

        System.out.println("\t What do you want to do?\n");

        do {
            System.out.println("\t 1. Browse Games");
            System.out.println("\t 2. Change Game Details");
            System.out.println("\t 3. Add a new Game");
            System.out.println("\t 4. Remove an existing Game");
            System.out.println("\t 5. Set Discount on a Game");
            System.out.println("\t 6. Remove Discount from a Game");
            System.out.println("\t 7. View Checkout History");
            System.out.println("\t 8. Exit | You will be Logged Out");

            Scanner scanner = new Scanner(System.in);

            System.out.print("\n\t Enter serial number: ");
            final int s_no = scanner.nextInt();

            switch (s_no) {

                case 1 -> {
                    library.browse();
                }

                case 2 -> {
                    library.modifyGame();
                }

                case 3 -> {
                    library.addGame();
                }

                case 4 -> {
                    library.removeGame();
                }

                case 5 -> {
                    library.setDiscount();
                }

                case 6 -> {
                    library.removeDiscount();
                }

                case 7 -> {
                    checkoutHistory();
                }

                case 8 -> {
                    System.out.println("\n\t Exited | You have been Logged Out\n");
                    flag = false;
                }

                default -> {
                    System.out.println("\n\t Invalid Input | Please try again\n");
                }
            }

        } while (flag);

    }

    private void checkoutHistory() {

        System.out.println("\n\t Checkout History");

        Boolean flag = true;
        Client client;

        client = new Client("");

    }
}
