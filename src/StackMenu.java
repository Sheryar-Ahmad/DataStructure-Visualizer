import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackMenu extends JFrame implements ActionListener {

    // This keeps the main menu so we can return to it.
    MainMenu mainMenu;

    // These labels show the heading of this section.
    JLabel title, subtitle;

    // These buttons open stack topics.
    JButton arrayStackButton, linkedStackButton, backButton;

    StackMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;

        // Null layout is simple and easy to understand.
        setLayout(null);

        // Main heading of the stack section.
        title = new JLabel("Stack Section");
        title.setBounds(300, 40, 260, 40);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // This line tells the user to choose a stack type.
        subtitle = new JLabel("Select the stack topic you want to visualize");
        subtitle.setBounds(240, 90, 380, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setForeground(new Color(70, 80, 90));
        add(subtitle);

        // This button opens stack using array.
        arrayStackButton = new JButton("Stack Using Array");
        arrayStackButton.setBounds(290, 160, 280, 45);
        arrayStackButton.addActionListener(this);
        designButton(arrayStackButton);
        add(arrayStackButton);

        // This button opens stack using linked list.
        linkedStackButton = new JButton("Stack Using Linked List");
        linkedStackButton.setBounds(290, 230, 280, 45);
        linkedStackButton.addActionListener(this);
        designButton(linkedStackButton);
        add(linkedStackButton);

        // This button returns to the main menu.
        backButton = new JButton("Back");
        backButton.setBounds(290, 300, 280, 45);
        backButton.addActionListener(this);
        designButton(backButton);
        add(backButton);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(860, 470);
        MainMenu.makeResponsive(this, 860, 470);
        MainMenu.designTopicButtons(this, MainMenu.STACK_QUEUE_COLOR);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == arrayStackButton) {
            new StackArrayVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == linkedStackButton) {
            new StackLinkedListVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == backButton) {
            mainMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method gives the same style to every button.
    private void designButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setBackground(MainMenu.STACK_QUEUE_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(235, 240, 245), 1));
    }
}
