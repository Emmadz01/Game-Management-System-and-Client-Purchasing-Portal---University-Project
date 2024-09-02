package OnlineGameStore;

import java.util.ArrayList;

abstract public class Login {
    
    abstract public Boolean Login(ArrayList<Customer> customerList, GameLibrary gameLibrary);
    abstract public Boolean adminLogin(ArrayList<Admin> adminList, GameLibrary gameLibrary);
}
