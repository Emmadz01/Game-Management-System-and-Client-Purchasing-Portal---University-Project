package OnlineGameStore;

public class Game {

    private String name, genre, description, developer, publisher;
    private int rating, releaseYear, discount;
    private double price, discountedPrice;
    private Review reviews;

    public Game() {
        this.name = null;
        this.genre = null;
        this.description = null;
        this.developer = null;
        this.publisher = null;
        this.rating = -1;
        this.releaseYear = -1;
        this.price = -1;
        this.discount = 0;
        this.discountedPrice = 0;
        this.reviews = new Review();
    }

    public Game(String name, String description, double price, String genre, String developerName, String publisherName,
            int rating, int releaseYear, int discount) {

        this.name = name;
        this.genre = genre;
        this.description = description;
        this.developer = developerName;
        this.publisher = publisherName;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.price = price;
        this.discount = discount;
        this.discountedPrice = (1.0 - (discount / 100.0)) * price;
        this.reviews = new Review();
    }

    public void printGameDetails() {
        System.out.println("\t\t Name: " + this.getGameName());
        System.out.println("\t\t Description: " + this.getGameDescription());
        System.out.println("\t\t Price: $" + this.getGamePrice());
        System.out.println("\t\t Discount: " + this.discount+ "%");
        System.out.println("\t\t Genre: " + this.getGameGenre());
        System.out.println("\t\t Developer: " + this.getGameDeveloper());
        System.out.println("\t\t Publisher: " + this.getGamePublisher());
        System.out.println("\t\t Rating: " + this.getGameRating());
        System.out.println("\t\t Release Year: " + this.getGameReleaseYear());
    }

    public void setGameName(String name) {
        this.name = name;
    }

    public String getGameName() {
        return this.name;
    }

    public void setGameGenre(String genre) {
        this.genre = genre;
    }

    public String getGameGenre() {
        return this.genre;
    }

    public void setGameDescription(String description) {
        this.description = description;
    }

    public String getGameDescription() {
        return this.description;
    }

    public void setGameDeveloper(String developerName) {
        this.developer = developerName;
    }

    public String getGameDeveloper() {
        return this.developer;
    }

    public void setGamePublisher(String publisherName) {
        this.publisher = publisherName;
    }

    public String getGamePublisher() {
        return this.publisher;
    }

    public void setGameRating(int rating) {
        this.rating = rating;
    }

    public int getGameRating() {
        return this.rating;
    }

    public void setGameReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getGameReleaseYear() {
        return this.releaseYear;
    }

    public void setGamePrice(double price) {
        this.price = price;
        this.discountedPrice = (1.0 - (this.discount / 100.0)) * this.price;
    }

    public double getGamePrice() {
        
        return discountedPrice;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        this.discountedPrice = (1.0 - (this.discount / 100.0)) * this.price;
    }
    
    public void addReview(Customer customer){
        reviews.addReview(customer);
    }
    public void browseReviews(){
        reviews.browse();
    }
}
