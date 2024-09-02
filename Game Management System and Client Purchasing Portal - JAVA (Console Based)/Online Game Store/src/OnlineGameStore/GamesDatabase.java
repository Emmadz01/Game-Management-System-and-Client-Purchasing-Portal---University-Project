package OnlineGameStore;

import java.sql.*;
import java.util.ArrayList;

public class GamesDatabase {

    public ArrayList<Game> getGames() {

        ArrayList<Game> gameList = new ArrayList<>();

        String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
        String username = "GameDatabase";
        String password = "game123";

        try {

            Class.forName("oracle.jdbc.OracleDriver");

            Connection connection = DriverManager.getConnection(database_URL, username, password);
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM GAMES";

            ResultSet result_set = statement.executeQuery(query);

            String name, description, developerName, publisherName, genre;
            double price;
            int rating, releaseYear, discount;

            Game game;

            while (result_set.next()) {

                name = result_set.getString(2);
                description = result_set.getString(3);
                price = result_set.getDouble(4);
                genre = result_set.getString(5);
                developerName = result_set.getString(6);
                publisherName = result_set.getString(7);
                rating = result_set.getInt(8);
                releaseYear = result_set.getInt(9);
                discount = result_set.getInt(10);

                game = new Game(name, description, price, genre, developerName, publisherName, rating, releaseYear, discount);
                gameList.add(game);
            }

            result_set.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            // Error Connecting
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gameList;
    }
}
