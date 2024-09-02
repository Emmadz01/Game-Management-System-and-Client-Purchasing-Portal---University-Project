 package OnlineGameStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.ArrayList;

public class Deserialization {

    public ArrayList<Admin> getAdmin_Data() {

        ArrayList<String> user_fileNames = new ArrayList<>();
        ArrayList<Admin> userList = new ArrayList<>();

        String directoryPath = "D:\\_Users_\\Admin";
        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            File[] userFiles = directory.listFiles();

            if (userFiles != null) {

                for (File file : userFiles) {
                    user_fileNames.add(file.getName());
                }
            }
        }

        if (!user_fileNames.isEmpty()) {
            try {
                FileInputStream fis;
                ObjectInputStream ois;
                Admin admin;

                for (String file_path : user_fileNames) {

                    file_path = "D:\\_Users_\\Admin\\" + file_path;

                    fis = new FileInputStream(file_path);
                    ois = new ObjectInputStream(fis);

                    admin = (Admin) ois.readObject();

                    userList.add(admin);

                    ois.close();
                    fis.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Deserialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return userList;
    }

    public ArrayList<Customer> getCustomer_Data() {

        ArrayList<String> user_fileNames = new ArrayList<>();
        ArrayList<Customer> userList = new ArrayList<>();

        String directoryPath = "D:\\_Users_\\Customer";
        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            File[] userFiles = directory.listFiles();

            if (userFiles != null) {

                for (File file : userFiles) {
                    user_fileNames.add(file.getName());
                }
            }
        }

        if (!user_fileNames.isEmpty()) {
            try {
                FileInputStream fis;
                ObjectInputStream ois;
                Customer customer;

                for (String file_path : user_fileNames) {

                    file_path = "D:\\_Users_\\Customer\\" + file_path;

                    fis = new FileInputStream(file_path);
                    ois = new ObjectInputStream(fis);

                    customer = (Customer) ois.readObject();

                    userList.add(customer);

                    ois.close();
                    fis.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Deserialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return userList;
    }
}
