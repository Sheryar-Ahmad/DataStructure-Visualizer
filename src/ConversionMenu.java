import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversionMenu extends JFrame implements ActionListener {

    // This keeps the main menu so we can return to it.
    MainMenu mainMenu;

    // These labels show the heading of this section.
    JLabel title, subtitle;

    // These buttons open conversion topics.
    JButton postfixButton, prefixButton, backButton;

    ConversionMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;

        // Null layout keeps positions easy to understand.
        setLayout(null);

        // Main heading of conversion section.
        title = new JLabel("Conversion Section");
        title.setBounds(270, 40, 340, 40);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // This line tells the user to select one conversion.
        subtitle = new JLabel("Select the expression conversion you want to visualize");
        subtitle.setBounds(220, 90, 470, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(70, 80, 90));
        add(subtitle);

        // This button opens infix to postfix screen.
        postfixButton = new JButton("Infix to Postfix");
        postfixButton.setBounds(290, 160, 280, 45);
        postfixButton.addActionListener(this);
        designButton(postfixButton);
        add(postfixButton);

        // This button opens infix to prefix screen.
        prefixButton = new JButton("Infix to Prefix");
        prefixButton.setBounds(290, 230, 280, 45);
        prefixButton.addActionListener(this);
        designButton(prefixButton);
        add(prefixButton);

        // This button returns to main menu.
        backButton = new JButton("Back");
        backButton.setBounds(290, 300, 280, 45);
        backButton.addActionListener(this);
        designButton(backButton);
        add(backButton);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(860, 470);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == postfixButton) {
            new InfixToPostfixVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == prefixButton) {
            new InfixToPrefixVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == backButton) {
            mainMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method gives the same style to every button.
    private void designButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBackground(new Color(110, 95, 145));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
