package OnlineGameStore;

import java.util.ArrayList;
import java.io.Serializable;

public class PurchasedGames implements Serializable{

    final private ArrayList<Game> purchasedGamesList;

    public PurchasedGames() {
        purchasedGamesList = new ArrayList<>();
    }

    public void addGame(Game game) {
        if (purchasedGamesList.contains(game)) {
            System.out.println("\n\t You have already Purchased this game (" + game.getGameName() + ")\n");
        } else {
            purchasedGamesList.add(game);
        }
    }

    public void removeGame(Game game) {

        purchasedGamesList.remove(game);
    }

    public void browse() {
        if (purchasedGamesList.isEmpty()) {
            System.out.println("\n\t You did not purchased any game\n");
        } else {
            for (int x = 0; x < (purchasedGamesList.size()); x++) {
                System.out.println("\n\t Purchased Game " + (x + 1) + ":\n");
                purchasedGamesList.get(x).printGameDetails();

                System.out.println("\n-------------------------------------------------\n");
            }
        }
    }
    
    public int getCount() {
        return purchasedGamesList.size();
    }
}
