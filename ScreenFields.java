package Q1;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class ScreenFields {
    JFrame fr;
    Container con;
    JTextField usernameTF;
    JPasswordField passwordPF;
    JLabel usernameL, passwordL, loginOutput;
    JButton cancelB, submitB;

    ScreenFields() {
        init1("");
        init2();
    }

    public void init1(String submitButtonText) {
        fr = new JFrame();
        con = fr.getContentPane();
        con.setLayout(new GridLayout(5,2));

        usernameL = new JLabel("Username: ");
        usernameL.setHorizontalAlignment(SwingConstants.CENTER);
        passwordL = new JLabel("Password: ");
        passwordL.setHorizontalAlignment(SwingConstants.CENTER);
        usernameTF = new JTextField();
        usernameTF.setHorizontalAlignment(SwingConstants.CENTER);
        passwordPF = new JPasswordField();
        passwordPF.setHorizontalAlignment(SwingConstants.CENTER);

        fieldLimit(passwordPF, 10);
        fieldLimit(usernameTF, 20);

        cancelB = new JButton("Cancel");
        submitB = new JButton(submitButtonText);
        loginOutput = new JLabel("");
        loginOutput.setHorizontalAlignment(SwingConstants.CENTER);

        con.add(usernameL);
        con.add(usernameTF);
        con.add(passwordL);
        con.add(passwordPF);
    }
    
    public void init2(){
        con.add(cancelB);
        con.add(submitB);
        con.add(loginOutput);

        fr.setSize(600, 400);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void fieldLimit(JTextField tf, int len){
        PlainDocument document = (PlainDocument) tf.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= len) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
    }
}
