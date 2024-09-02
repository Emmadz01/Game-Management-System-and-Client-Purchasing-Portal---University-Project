package OnlineGameStore;

import java.util.ArrayList;
import java.io.Serializable;

public class FavouriteGameList implements Serializable{

    final private ArrayList<Game> favouriteGamesList;

    public FavouriteGameList() {
        favouriteGamesList = new ArrayList<>();
    }

    public void addGame(Game game) {
        if (favouriteGamesList.contains(game)) {
            System.out.println("\n\t This game is already set as your Favourite\n");
        } else {
            favouriteGamesList.add(game);
        }
    }

    public void removeGame(Game game) {

        favouriteGamesList.remove(game);
    }

    public void browse() {
        if(favouriteGamesList.isEmpty()){
            System.out.println("\n\t You did not marked any game as Favourite\n");
        }else{
            for (int x = 0; x < (favouriteGamesList.size()); x++) {
            System.out.println("\n\t Favourite Game " + (x + 1) + ":\n");
            favouriteGamesList.get(x).printGameDetails();

            System.out.println("\n-------------------------------------------------\n");
        }
        }
    }

    public int getCount() {
        return favouriteGamesList.size();
    }
}
