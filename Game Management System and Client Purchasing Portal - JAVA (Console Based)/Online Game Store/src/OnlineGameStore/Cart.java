package OnlineGameStore;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Cart implements Serializable {

    final private ArrayList<Game> cartItems;
    final private PurchasedGames purchased;

    public Cart(PurchasedGames purchased) {
        cartItems = new ArrayList<>();
        this.purchased = purchased;
    }

    public void home(Customer customer) {
        System.out.println("\n\t Customer Cart\n");

        System.out.println("\t What do you want to do?\n");

        Boolean flag = false;

        do {
            System.out.println("\t 1. Browse Cart");
            System.out.println("\t 2. Remove Game from Cart");
            System.out.println("\t 3. Proceed to Checkout");
            System.out.println("\t 4. Exit");

            Scanner scanner = new Scanner(System.in);

            System.out.print("\n\t Enter serial number: ");
            final int s_no = scanner.nextInt();

            switch (s_no) {

                case 1 -> { // Browse Cart
                    browseCart();
                }

                case 2 -> { // Remove Game from Cart
                    removeGame();
                }

                case 3 -> { // Proceed to Checkout
                    checkout(customer);
                }

                case 4 -> { // Exit
                    flag = false;
                    System.out.println("\n\t Returning back to your Dashboard\n");
                }

                default -> { // Invalid Input

                    System.out.println("\n\t Invalid Input | Please try again\n");
                }
            }
        } while (flag);
    }

    public void browseCart() {

        if (cartItems.isEmpty()) {
            System.out.println("\n\t Your Cart is empty | Try purchasing some games\n");
        } else {
            for (int x = 0; x < cartItems.size(); x++) {
                System.out.println("\n\t Game " + (x + 1) + ":\n");

                cartItems.get(x).printGameDetails();
                System.out.println("\n-------------------------------------------------\n");
            }
        }
    }

    public void addGame(Game game) {

        this.cartItems.add(game);
    }

    private void removeGame() {

        if (cartItems.isEmpty()) {
            System.out.println("\n\t Your cart is Empty\n");
        } else {

            browseCart();

            Boolean flag = false;
            Scanner scanner = new Scanner(System.in);

            do {
                System.out.print("\n\t Enter serial number to Remove (0 to Exit): ");
                final int s_no = scanner.nextInt();

                if (s_no >= 1) {
                    if (s_no > cartItems.size()) {
                        System.out.println("\n\t Invalid Input | Plese try again\n");
                    } else {

                        cartItems.remove((s_no - 1));
                        System.out.println("\n\t Game Removed from your Cart\n");
                    }
                } else if (s_no == 0) {
                    System.out.println("\n\t Cancelled removing game\n");
                }
            } while (flag);
        }
    }

    public void checkout(Customer customer) {
        int numberOfItems;
        double totalPrice = 0;

        if (cartItems.isEmpty()) {
            System.out.println("\n\t Your Cart is Empty | Try purchasing some games\n");
        } else {

            browseCart();

            numberOfItems = cartItems.size();

            for (int x = 0; x < numberOfItems; x++) {

                totalPrice += cartItems.get(x).getGamePrice();
            }

            System.out.println("\n\t Receipt\n");
            System.out.println("\t ");

            System.out.println("\t Total Amount: $" + totalPrice);
            System.out.println("\t Payment Method: Cash on Delivery");

            System.out.println("\n\t Confirm Checkout\n");
            System.out.print("\t Enter 1 to Confirm or any other number to Cancel: ");

            Scanner scanner = new Scanner(System.in);

            if (scanner.nextInt() == 1) {
                finalizeCheckout(totalPrice);
                cartItems.clear();

                System.out.println("\n\t Checkout Successful\n");
                
                Client client = new Client((customer.getName() + " made a total purchase of $") + totalPrice);
                
            } else {
                System.out.println("\n\t Checkout Cancelled\n");
            }
        }
    }

    private void finalizeCheckout(double totalPrice) {

        for (int x = 0; x < (cartItems.size()); x++) {
            purchased.addGame(cartItems.get(x));
        }
        
        sendEmail(totalPrice);
    }
    
    public void sendEmail(double totalPrice){
        Email email = new Email();
        
        email.sendEmail(cartItems, totalPrice);
                
    }
}
