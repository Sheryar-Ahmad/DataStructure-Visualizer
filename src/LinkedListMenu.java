import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinkedListMenu extends JFrame implements ActionListener {

    // This keeps the main menu so we can go back to it.
    MainMenu mainMenu;

    // These labels show the title and small guide line.
    JLabel title, subtitle;

    // These buttons open different linked list topics.
    JButton singleListButton, circularListButton, twoWayCircularButton, backButton;

    LinkedListMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;

        // We use null layout because it is easy to understand for this project.
        setLayout(null);

        // Main heading of this screen.
        title = new JLabel("Linked List Section");
        title.setBounds(255, 35, 380, 40);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // This line tells the user to select one linked list topic.
        subtitle = new JLabel("Select the linked list topic you want to visualize");
        subtitle.setBounds(230, 85, 430, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(70, 80, 90));
        add(subtitle);

        // This button opens the single linked list visualizer.
        singleListButton = new JButton("Single Linked List");
        singleListButton.setBounds(290, 150, 280, 45);
        singleListButton.addActionListener(this);
        designButton(singleListButton);
        add(singleListButton);

        // This button opens the circular linked list visualizer.
        circularListButton = new JButton("Circular Linked List");
        circularListButton.setBounds(290, 215, 280, 45);
        circularListButton.addActionListener(this);
        designButton(circularListButton);
        add(circularListButton);

        // This button opens the two-way circular linked list visualizer.
        twoWayCircularButton = new JButton("Two-Way Circular Linked List");
        twoWayCircularButton.setBounds(290, 280, 280, 45);
        twoWayCircularButton.addActionListener(this);
        designButton(twoWayCircularButton);
        add(twoWayCircularButton);

        // This button returns to the main menu.
        backButton = new JButton("Back");
        backButton.setBounds(290, 345, 280, 45);
        backButton.addActionListener(this);
        designButton(backButton);
        add(backButton);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(860, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method runs when a button is clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singleListButton) {
            new SinglyLinkedListVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == circularListButton) {
            new CircularLinkedListVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == twoWayCircularButton) {
            new TwoWayCircularLinkedListVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == backButton) {
            mainMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method gives the same style to all buttons.
    private void designButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBackground(new Color(70, 120, 125));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
