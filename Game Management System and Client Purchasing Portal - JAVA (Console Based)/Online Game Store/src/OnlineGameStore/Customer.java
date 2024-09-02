package OnlineGameStore;

import java.util.Scanner;

public class Customer extends User {

    final private PurchasedGames purchased;
    final private FavouriteGameList favourites;
    final private Cart cart;

    public Customer() {
        this.name = null;
        this.email = null;
        this.password = null;
        this.smsNumber = -1;
        this.purchased = new PurchasedGames();
        this.cart = new Cart(purchased);
        favourites = new FavouriteGameList();
    }

    public Customer(String name, String email, int smsNumber, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.smsNumber = smsNumber;
        this.purchased = new PurchasedGames();
        this.cart = new Cart(purchased);
        favourites = new FavouriteGameList();
    }

    private void browseReviews(GameLibrary library) {
        library.browse();

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter serial number to select game for browsing reviews: ");
        int s_no = scanner.nextInt();

        int gameCount = library.getGameCount();

        if (s_no > 0 && s_no <= gameCount) {

            System.out.println("\n\t Browsing reviews for '" + library.getGame((s_no - 1)).getGameName() + "'\n");
            library.getGame((s_no - 1)).browseReviews();
        } else {
            System.out.println("\n\t Invalid Input | Please try again");
        }
    }

    private void addReview(GameLibrary library) {
        library.browse();

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter serial number to select game for adding a review: ");
        int s_no = scanner.nextInt();

        int gameCount = library.getGameCount();

        if (s_no > 0 && s_no <= gameCount) {

            System.out.println("\n\t Writing a review for '" + library.getGame((s_no - 1)).getGameName() + "'\n");
            library.getGame((s_no - 1)).addReview(this);
        } else {
            System.out.println("\n\t Invalid Input | Please try again");
        }
    }

    private void purchaseGame(GameLibrary library) {

        library.browse();

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter serial number to purchase: ");
        int s_no = scanner.nextInt();

        int gameCount = library.getGameCount();

        if (s_no > 0 && s_no <= gameCount) {

            cart.addGame(library.getGame((s_no - 1)));
            System.out.println("\n\t Selected game has been added to your cart | Go to your Cart to checkout");
        } else {
            System.out.println("\n\t Invalid Input | Please try again");
        }
    }

    private void add_favouriteGames(GameLibrary library) {

        library.browse();

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter serial number to mark as favourite: ");
        int s_no = scanner.nextInt();

        int gameCount = library.getGameCount();

        if (s_no > 0 && s_no <= gameCount) {

            favourites.addGame(library.getGame((s_no - 1)));
            System.out.println("\n\t Selected game has been added to your Favourites");
        } else {
            System.out.println("\n\t Invalid Input | Please try again");
        }
    }

    public void home(GameLibrary library) {

        Boolean flag = true;

        do {
            System.out.println("\n\t Customer Dashboard \t Logged in as: " + name + "\n");

            System.out.println("\t What do you want to do?\n");
            
            System.out.println("\t 1. Browse Games");
            System.out.println("\t 2. Browse Purchased Games");
            System.out.println("\t 3. Purchase Game");
            System.out.println("\t 4. Go to Cart");
            System.out.println("\t 5. View favourite games");
            System.out.println("\t 6. Mark games as favourite");
            System.out.println("\t 7. Write a Review");
            System.out.println("\t 8. Browse Reviews");
            System.out.println("\t 9. Exit | You will be Logged Out");

            Scanner scanner = new Scanner(System.in);

            System.out.print("\n\t Enter serial number: ");
            final int s_no = scanner.nextInt();

            switch (s_no) {

                case 1 -> { // Browse Games
                    library.browse();
                }

                case 2 -> { // Browse Purchased Games
                    purchased.browse();
                }

                case 3 -> { // Purchase Game
                    purchaseGame(library);
                }

                case 4 -> { // Go to Cart
                    cart.home(this);
                }

                case 5 -> { // View favourite games
                    favourites.browse();
                }

                case 6 -> { // Mark games as favourite
                    add_favouriteGames(library);
                }

                case 7 -> { // Write a review
                    addReview(library);
                }

                case 8 -> { // Browse Reviews
                    browseReviews(library);
                }

                case 9 -> { // Exit
                    System.out.println("\n\t Exited | You have been Logged Out\n");
                    flag = false;
                }

                default -> { // Invalid Input

                    System.out.println("\n\t Invalid Input | Please try again\n");
                }
            }
        } while (flag);

    }
}
