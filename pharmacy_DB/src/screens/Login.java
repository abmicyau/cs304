package screens;

import main.Pharmacy_DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JPanel {

    private JLabel title = new JLabel("Employee Login");
    private JLabel labelUsername = new JLabel("Enter username: ");
    private JLabel labelPassword = new JLabel("Enter password: ");
    private JTextField textUsername = new JTextField(20);
    private JPasswordField fieldPassword = new JPasswordField(20);
    private JButton buttonLogin = new JButton("Login");

    private JLabel loginMsg = new JLabel(" ");



    private String fetchUserPass(String uname) {
        StringBuilder query = new StringBuilder();
        StringBuilder query2 = new StringBuilder();
        query.append("SELECT password FROM Employee WHERE username = ");
        query.append("'");
        query.append(uname);
        query.append("'");
        ResultSet rs = Pharmacy_DB.getResults(query.toString());
        String pw = null;

        try {
            while (rs.next()) {
                pw = rs.getString("PASSWORD");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(pw);
        return pw;

    }

    private String rsToString(ResultSet rs) {
        if (rs != null) {
            try {
                while (rs.next()) {
                    String pw = rs.getString("password");
                    System.out.println(pw);
                    return pw;
                }
            } catch (SQLException e) {

            }
        }
        return null;
    }

    public Login() {

        // important! call JPanel constructor and pass GridBagLayout
        super(new GridBagLayout());

        // set contraints and padding
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(title, constraints);

        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelUsername, constraints);

        constraints.gridx = 1;
        add(textUsername, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(labelPassword, constraints);

        constraints.gridx = 1;
        add(fieldPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(buttonLogin, constraints);

        // set border for the panel
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login"));

        constraints.gridx = 0;
        constraints.gridy = 5;

        add(loginMsg, constraints);



        // login button action
        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //authentication
                if (fieldPassword.getText().equals(fetchUserPass(textUsername.getText()))) {
                    Pharmacy_DB.switchScreen(Pharmacy_DB.getHomePanel());
                } else {
                    loginMsg.setText("Login failed. Invalid username/password.");
                }

            }
        });
    }

}
