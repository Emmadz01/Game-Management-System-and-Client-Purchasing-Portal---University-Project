package OnlineGameStore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class Serialization {

    User user;

    public Serialization(User user) {
        this.user = user;
    }

    public Boolean addUser() {

        File directory = new File("D:\\_Users_");
        Boolean writeData = false, flag = false;

        if (directory.isDirectory()) {
            writeData = true;
        } else {
            if (directory.mkdir()) {
                writeData = true;
            }
        }

        directory = new File("D:\\_Users_\\Admin");

        if (!directory.isDirectory()) {
            directory.mkdir();
        }

        directory = new File("D:\\_Users_\\Customer");

        if (!directory.isDirectory()) {
            directory.mkdir();
        }

        if (writeData) {
            
            String file_name = "";
            
            if (user.getUserRole().equals("Admin")) {
                file_name = ("D:\\_Users_\\Admin\\" + user.getEmailAddress()) + ".bin";
            } else if (user.getUserRole().equals("Customer")) {
                file_name = ("D:\\_Users_\\Customer\\" + user.getEmailAddress()) + ".bin";
            }

            try {
                FileOutputStream fos = new FileOutputStream(file_name);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(user);

                oos.close();
                fos.close();

                flag = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return flag;
    }
}
