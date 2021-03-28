import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args){
        Object[] options = {"Login", "Register", "Change Password"};
        int option = JOptionPane.showMessageDialog(null, options, "Choose", JOptionPane.YES_NO_CANCEL_OPTION);
        if(JOptionPane.YES_OPTION)
        {
            LoginScreen ls = new LoginScreen();
            ls.init();
        }
    }
}
