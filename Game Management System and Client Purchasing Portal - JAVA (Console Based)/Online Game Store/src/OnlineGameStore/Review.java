package OnlineGameStore;

import java.util.ArrayList;
import java.util.Scanner;

public class Review {

    ArrayList<Customer> customerList;
    ArrayList<String> reviewsList;

    public Review() {
        this.customerList = new ArrayList<>();
        this.reviewsList = new ArrayList<>();
    }

    public void addReview(Customer customer) {

        if (customerList.contains(customer)) {
            System.out.println("\n\t You have already added a Review for this game\n");
        } else {
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\\n");

            System.out.println("\n\t Enter your review for this game:\n");
            System.out.print("\t ");
            final String review = scanner.next();

            reviewsList.add(review);
            customerList.add(customer);
            
            System.out.println("\n\t Review Added\n");
        }
    }

    public void browse() {
        if (reviewsList.isEmpty()) {
            System.out.println("\n\t This Game has no Reviews yet\n");
        } else {
            for (int x = 0; x < (reviewsList.size()); x++) {
                System.out.println("\n\t Review " + (x + 1) + " by " + customerList.get(x).getName() + ":\n");

                System.out.println("\t " + reviewsList.get(x));

                System.out.println("\n-------------------------------------------------\n");
            }
        }
    }
}
