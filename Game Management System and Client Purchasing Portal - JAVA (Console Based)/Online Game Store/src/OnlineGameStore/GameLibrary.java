package OnlineGameStore;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLibrary extends Game {

    private ArrayList<Game> gameList;

    public GameLibrary() {

        gameList = new ArrayList<>();
    }

    public void browse() {

        if (!gameList.isEmpty()) {

            for (int x = 0; x < (gameList.size()); x++) {

                System.out.println("\n\t Game " + (x + 1) + ":\n");

                gameList.get(x).printGameDetails();
                System.out.println("\n-------------------------------------------------\n");
            }
        } else {
            System.out.println("\n\t Game Library is Empty\n");
        }
    }

    public void setDiscount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter Discount: %");
        final int discount = scanner.nextInt();

        if (discount <= 0) {
            System.out.println("\n\t Discount must be greater than Zero | Action Cancelled - Please try again\n");
        } else {
            browse();

            System.out.print("\n\t Enter serial number of a game to apply discount: ");
            final int s_no = scanner.nextInt();

            if (s_no != 0) {

                if (s_no <= gameList.size()) {
                    gameList.get((s_no - 1)).setDiscount(discount);
                    System.out.println("\n\t Discount applied\n");
                } else {
                    System.out.println("\n\tInvalid Input | Please try again\n");
                }
            } else {
                System.out.println("\n\tInvalid Input | Please try again\n");
            }
        }
    }

    public void removeDiscount() {
        browse();

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter serial number of a game to remove discount: ");
        final int s_no = scanner.nextInt();

        if (s_no != 0) {

            if (s_no <= gameList.size()) {
                gameList.get((s_no - 1)).setDiscount(0);
            } else {
                System.out.println("\n\tInvalid Input | Please try again\n");
            }
        } else {
            System.out.println("\n\tInvalid Input | Please try again\n");
        }
    }

    public void addGame(ArrayList<Game> game) {
        this.gameList = game;
    }

    public void addGame(Game game) {
        if (!gameList.contains(game)) {
            gameList.add(game);
        }
    }

    public void addGame() {

        Boolean flag = false;
        Scanner scanner = new Scanner(System.in);

        String name, genre, description, developer, publisher;
        int rating, releaseYear, discount;
        double price;

        System.out.println("\n\t Add a New Game\n");

        do {

            scanner.useDelimiter("\\n");

            System.out.print("\t Enter Name (! to exit): ");
            name = scanner.next();

            Boolean gameExist;

            if (!name.equals("!")) {
                final int gameIndex = checkGameExist(name);

                if (gameIndex == -1) {
                    flag = false;

                    System.out.print("\t Enter Description: ");
                    description = scanner.next();

                    System.out.print("\t Enter Price: $");
                    price = scanner.nextDouble();

                    System.out.print("\t Enter Genre: ");
                    genre = scanner.next();

                    System.out.print("\t Enter Developer Name: ");
                    developer = scanner.next();

                    System.out.print("\t Enter Publisher: ");
                    publisher = scanner.next();

                    System.out.print("\t Enter Rating: ");
                    rating = scanner.nextInt();

                    System.out.print("\t Enter Release Year: ");
                    releaseYear = scanner.nextInt();

                    System.out.print("\t Enter Discount: %");
                    discount = scanner.nextInt();

                    Game newGame = new Game(name, description, price, genre, developer, publisher, rating,
                            releaseYear, discount);

                    gameList.add(newGame);

                    int gameID;

                    gameID = gameList.size();

                    String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                    String username = "GameDatabase";
                    String password = "game123";

                    try {

                        Class.forName("oracle.jdbc.OracleDriver");

                        Connection connection = DriverManager.getConnection(database_URL, username, password);
                        Statement statement = connection.createStatement();

                        String query = "INSERT INTO GAMES VALUES(\n"
                                + "    " + gameID + ", \n"
                                + "    '" + name + "', \n"
                                + "    '" + description + "',\n"
                                + "    " + price + ",\n"
                                + "    '" + genre + "',\n"
                                + "    '" + developer + "',\n"
                                + "    '" + publisher + "',\n"
                                + "    " + rating + ",\n"
                                + "    " + releaseYear + ",\n"
                                + "    " + discount
                                + ")";

                        int rowsAffected = statement.executeUpdate(query);

                        if (rowsAffected > 0) {
                            System.out.println("\n\t Game Added\n");
                        } else {
                            System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                        }

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

                } else {
                    System.out.println("\n\t Game already exists and is as follows:\n");

                    gameList.get(gameIndex).printGameDetails();

                    System.out.println("\n\t Please add another game");
                }

            } else {
                flag = false;
                System.out.println("\n\t Cancelled adding a new game\n");
            }
        } while (flag);
    }

    public void getGames() {

        GamesDatabase gameDB = new GamesDatabase();

        gameList = gameDB.getGames();

        for (Game g : gameList) {
            System.out.println("\n\t" + g.getGameName());
        }

    }

    public int getGameCount() {

        return (gameList.size());
    }

    public void modifyGame() {
        Boolean flag = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\t Modify a Game\n");

        do {
            scanner.useDelimiter("\\n");

            System.out.print("\t Enter Name (! to exit): ");
            final String name = scanner.next();

            if (!name.equals("!")) {
                if (gameList.isEmpty()) {
                    System.out.println("\n\t Game Library is Empty\n");
                } else {
                    final int gameIndex = checkGameExist(name);

                    if (gameIndex != -1) {
                        flag = false;

                        int s_no;

                        do {
                            System.out.println("\n\t What do you want to Modify?\n");
                            System.out.println("\t 1. Name");
                            System.out.println("\t 2. Description");
                            System.out.println("\t 3. Price");
                            System.out.println("\t 4. Genre");
                            System.out.println("\t 5. Developer Name");
                            System.out.println("\t 6. Publisher Name");
                            System.out.println("\t 7. Rating");
                            System.out.println("\t 8. Release Year");
                            System.out.println("\t 9. Exit");

                            System.out.print("\n\t Enter serial number: ");
                            s_no = scanner.nextInt();

                            String newData;

                            switch (s_no) {

                                case 1 -> { // Name

                                    System.out.print("\n\t Enter a new Name: ");
                                    newData = scanner.next();

                                    if (checkGameExist(newData) != -1) {

                                        System.out.println("\n\t This name for the game is already registered | Please try again\n");
                                    } else {

                                        gameList.get(getGameIndex(name)).setGameName(newData);

                                        String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                                        String username = "GameDatabase";
                                        String password = "game123";

                                        try {

                                            Class.forName("oracle.jdbc.OracleDriver");

                                            Connection connection = DriverManager.getConnection(database_URL, username, password);
                                            Statement statement = connection.createStatement();

                                            String query = "UPDATE GAMES SET GAME_NAME = '" + newData + "' WHERE GAME_ID = " + getGameIndex(name);

                                            int rowsAffected = statement.executeUpdate(query);

                                            if (rowsAffected > 0) {
                                                System.out.println("\n\t Name changed to '" + newData + "'\n");
                                                getGames();
                                            } else {
                                                System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                                            }

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
                                    }
                                }

                                case 2 -> { // Description

                                    System.out.print("\n\t Enter a new Description: ");
                                    newData = scanner.next();

                                    gameList.get(getGameIndex(name)).setGameDescription(newData);

                                    String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                                    String username = "GameDatabase";
                                    String password = "game123";

                                    try {

                                        Class.forName("oracle.jdbc.OracleDriver");

                                        Connection connection = DriverManager.getConnection(database_URL, username, password);
                                        Statement statement = connection.createStatement();

                                        String query = "UPDATE GAMES SET GAME_DESCRIPTION = '" + newData + "' WHERE GAME_ID = " + (getGameIndex(name));

                                        int rowsAffected = statement.executeUpdate(query);

                                        if (rowsAffected > 0) {
                                            System.out.println("\n\t Description changed to '" + newData + "'\n");
                                            getGames();
                                        } else {
                                            System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                                        }

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
                                }

                                case 3 -> { // Price

                                    System.out.print("\n\t Enter a new Price: ");
                                    newData = scanner.next();

                                    gameList.get(getGameIndex(name)).setGamePrice(Double.parseDouble(newData));

                                    String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                                    String username = "GameDatabase";
                                    String password = "game123";

                                    try {

                                        Class.forName("oracle.jdbc.OracleDriver");

                                        Connection connection = DriverManager.getConnection(database_URL, username, password);
                                        Statement statement = connection.createStatement();

                                        String query = "UPDATE GAMES SET PRICE = " + Double.parseDouble(newData) + " WHERE GAME_ID = " + (getGameIndex(name));

                                        int rowsAffected = statement.executeUpdate(query);

                                        if (rowsAffected > 0) {
                                            System.out.println("\n\t Price changed to '" + newData + "'\n");
                                            getGames();
                                        } else {
                                            System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                                        }

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
                                }

                                case 4 -> { // Genre

                                    System.out.print("\n\t Enter a new Genre: ");
                                    newData = scanner.next();

                                    String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                                    String username = "GameDatabase";
                                    String password = "game123";

                                    try {

                                        Class.forName("oracle.jdbc.OracleDriver");

                                        Connection connection = DriverManager.getConnection(database_URL, username, password);
                                        Statement statement = connection.createStatement();

                                        String query = "UPDATE GAMES SET GENRE = '" + newData + "' WHERE GAME_ID = " + (getGameIndex(name));

                                        int rowsAffected = statement.executeUpdate(query);

                                        if (rowsAffected > 0) {
                                            System.out.println("\n\t Genre changed to '" + newData + "'\n");
                                            getGames();
                                        } else {
                                            System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                                        }

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
                                    gameList.get(getGameIndex(name)).setGameGenre(newData);
                                }

                                case 5 -> { // Developer Name

                                    System.out.print("\n\t Enter a new Developer Name: ");
                                    newData = scanner.next();

                                    String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                                    String username = "GameDatabase";
                                    String password = "game123";

                                    try {

                                        Class.forName("oracle.jdbc.OracleDriver");

                                        Connection connection = DriverManager.getConnection(database_URL, username, password);
                                        Statement statement = connection.createStatement();

                                        String query = "UPDATE GAMES SET DEVELOPER = '" + newData + "' WHERE GAME_ID = " + (getGameIndex(name));

                                        int rowsAffected = statement.executeUpdate(query);

                                        if (rowsAffected > 0) {
                                            System.out.println("\n\t Developer Name changed to '" + newData + "'\n");
                                            getGames();
                                        } else {
                                            System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                                        }

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
                                    gameList.get(getGameIndex(name)).setGameDeveloper(newData);
                                }

                                case 6 -> { // Publisher Name

                                    System.out.print("\n\t Enter a new Publisher Name: ");
                                    newData = scanner.next();

                                    String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                                    String username = "GameDatabase";
                                    String password = "game123";

                                    try {

                                        Class.forName("oracle.jdbc.OracleDriver");

                                        Connection connection = DriverManager.getConnection(database_URL, username, password);
                                        Statement statement = connection.createStatement();

                                        String query = "UPDATE GAMES SET PUBLISHER = '" + newData + "' WHERE GAME_ID = " + (getGameIndex(name));

                                        int rowsAffected = statement.executeUpdate(query);

                                        if (rowsAffected > 0) {
                                            System.out.println("\n\t Publisher Name changed to '" + newData + "'\n");
                                            getGames();
                                        } else {
                                            System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                                        }

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
                                    gameList.get(getGameIndex(name)).setGamePublisher(newData);
                                }

                                case 7 -> { // Rating

                                    System.out.print("\n\t Enter a new Rating: ");
                                    newData = scanner.next();

                                    String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                                    String username = "GameDatabase";
                                    String password = "game123";

                                    try {

                                        Class.forName("oracle.jdbc.OracleDriver");

                                        Connection connection = DriverManager.getConnection(database_URL, username, password);
                                        Statement statement = connection.createStatement();

                                        String query = "UPDATE GAMES SET RATING = " + Integer.parseInt(newData) + " WHERE GAME_ID = " + (getGameIndex(name));

                                        int rowsAffected = statement.executeUpdate(query);

                                        if (rowsAffected > 0) {
                                            System.out.println("\n\t Rating changed to '" + newData + "'\n");
                                            getGames();
                                        } else {
                                            System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                                        }

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
                                    gameList.get(getGameIndex(name)).setGameRating(Integer.parseInt(newData));
                                }

                                case 8 -> { // Release Year

                                    System.out.print("\n\t Enter a new Release Year: ");
                                    newData = scanner.next();

                                    String database_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
                                    String username = "GameDatabase";
                                    String password = "game123";

                                    try {

                                        Class.forName("oracle.jdbc.OracleDriver");

                                        Connection connection = DriverManager.getConnection(database_URL, username, password);
                                        Statement statement = connection.createStatement();

                                        String query = "UPDATE GAMES SET RELEASEYEAR = " + Integer.parseInt(newData) + " WHERE GAME_ID = " + (getGameIndex(name));

                                        int rowsAffected = statement.executeUpdate(query);

                                        if (rowsAffected > 0) {
                                            System.out.println("\n\t Release Year changed to '" + newData + "'\n");
                                            getGames();
                                        } else {
                                            System.out.println("\n\t Unexpected Error Occured | Game not Added\n");
                                        }

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
                                    gameList.get(getGameIndex(name)).setGameReleaseYear(Integer.parseInt(newData));
                                }
                            }
                        } while (s_no != 9);
                        System.out.print("\n");
                    } else {
                        System.out.println("\n\t Game does not exist | Please try again for a different game\n");
                    }
                }

            } else {
                flag = false;
                System.out.println("\n\t Cancelled modifying a game\n");
            }
        } while (flag);
    }

    public void removeGame() {

        Scanner scanner = new Scanner(System.in);

        scanner.useDelimiter("\\n");

        System.out.print("\n\t Enter name of the game: ");
        String name = scanner.next();

        int x = -1;
        Boolean gameExist = false;

        if (gameList.isEmpty()) {
            System.out.println("\n\t Game Library is Empty\n");
        } else {

            final int gameIndex = checkGameExist(name);

            if (gameIndex != 1) {
                System.out.println("\n\t Confirm Deletion\n");
                System.out.println("\t Game Details are as follows:\n");

                gameList.get(gameIndex).printGameDetails();

                System.out.println("\n\t Enter 1 to Delete Game or any other number to Cancel Deletion");
                int s_no = scanner.nextInt();

                switch (s_no) {

                    case 1 -> {
                        gameList.remove(x);

                        System.out.println("\n\t Game Deleted");
                    }

                    default -> {
                        System.out.println("\n\t Game Deletion Cancelled");
                    }
                }
            } else {
                System.out.println("\n\t Game does not exist");
            }
        }
        System.out.print("\n");
    }

    public Game getGame(int x) {

        return gameList.get(x);
    }

    private int checkGameExist(String name) {

        int x;

        if (!gameList.isEmpty()) {

            for (x = 0; x < (gameList.size()); x++) {
                if (gameList.get(x).getGameName().equals(name)) {
                    return x;
                }
            }
        }

        return -1;
    }

    private int getGameIndex(String name) {

        int x = 0;

        for (x = 0; x < (gameList.size()); x++) {
            if (gameList.get(x).getGameName().equals(name)) {
                return x;
            }
        }

        return x;
    }
}
