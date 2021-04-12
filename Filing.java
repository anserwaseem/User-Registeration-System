package Q1;

import java.io.*;
import java.util.*;

// @SuppressWarnings("serial")
public class Filing {
    private ArrayList<String> usernames;
    private ArrayList<String> passwords;

    Filing() {
        usernames = new ArrayList<>();
        passwords = new ArrayList<>();
    }

    // Check if user exists or not
    public Boolean checkUser(String username) {
        for (int i = 0; i < usernames.size(); i++)
            if (usernames.get(i).equals(username))
                return true;
        return false;
    }

    // get user's encrypted password
    public String getEncPassword(String username) {
        for (int i = 0; i < passwords.size(); i++)
            if (usernames.get(i).equals(username))
                return passwords.get(i);
        return null;
    }

    // It sets new password by using existing username and password.
    public Boolean changePassword(UserInfo ui, String encryptedNewPassword) {
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(ui.getUsername()) && passwords.get(i).equals(ui.getPassword())) {
                passwords.set(i, encryptedNewPassword);
                return true;// password changed
            }
        }
        return false;// provided password/username is not in our database
    }

    // It sets new username by using existing username and password.
    public Boolean changeUsername(UserInfo ui, String newUsername) {
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(ui.getUsername()) && passwords.get(i).equals(ui.getPassword())) {
                usernames.set(i, newUsername);
                return true;// username changed
            }
        }
        return false;// provided password/username is not in our database
    }

    // add user record into local list
    public void addUser(UserInfo ui) {
        usernames.add(ui.getUsername());
        passwords.add(ui.getPassword());
    }

    public void save() {

        try (BufferedWriter out = new BufferedWriter(new FileWriter("Q1/u.txt"))) {
            String u = usernames.toString();
            System.out.println(u);
            u = u.replace("[", "").replace("]", "").replace(", ", "\n");
            System.out.println(u);
            out.write(u);
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter("Q1/p.txt"))) {
            String p = passwords.toString();
            System.out.println(p);
            p = p.replace("[", "").replace("]", "").replace(", ", "\n");
            System.out.println(p);
            out.write(p);
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }

    public void load() {

        try {
            File ufile = new File("Q1/u.txt");
            try (Scanner sc = new Scanner(ufile)) {// closing the resource is handled automatically by the
                                                   // try-with-resources
                while (sc.hasNextLine()) {
                    usernames.add(sc.nextLine());
                }
            } catch (FileNotFoundException fnfe) {
                fnfe.getStackTrace();
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        try {
            File pfile = new File("Q1/p.txt");
            try (Scanner sc = new Scanner(pfile)) {// closing the resource is handled automatically by the
                                                   // try-with-resources
                while (sc.hasNextLine()) {
                    passwords.add(sc.nextLine());
                }
            } catch (FileNotFoundException fnfe) {
                fnfe.getStackTrace();
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}
