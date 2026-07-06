import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueueMenu extends JFrame implements ActionListener {

    // This keeps the main menu so we can return to it.
    MainMenu mainMenu;

    // These labels show the heading of this section.
    JLabel title, subtitle;

    // These buttons open queue topics.
    JButton arrayQueueButton, linkedQueueButton, backButton;

    QueueMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;

        // Null layout is simple and easy to understand.
        setLayout(null);

        // Main heading of the queue section.
        title = new JLabel("Queue Section");
        title.setBounds(310, 40, 280, 40);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // This line tells the user to choose a queue type.
        subtitle = new JLabel("Select the queue topic you want to visualize");
        subtitle.setBounds(250, 90, 390, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(70, 80, 90));
        add(subtitle);

        // This button opens queue using array.
        arrayQueueButton = new JButton("Queue Using Array");
        arrayQueueButton.setBounds(290, 160, 280, 45);
        arrayQueueButton.addActionListener(this);
        designButton(arrayQueueButton);
        add(arrayQueueButton);

        // This button opens queue using linked list.
        linkedQueueButton = new JButton("Queue Using Linked List");
        linkedQueueButton.setBounds(290, 230, 280, 45);
        linkedQueueButton.addActionListener(this);
        designButton(linkedQueueButton);
        add(linkedQueueButton);

        // This button returns to the main menu.
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
        if (e.getSource() == arrayQueueButton) {
            new QueueArrayVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == linkedQueueButton) {
            new QueueLinkedListVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == backButton) {
            mainMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method gives the same style to every button.
    private void designButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBackground(new Color(80, 110, 150));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
