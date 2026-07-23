import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeMenu extends JFrame implements ActionListener {

    // This keeps the main menu so we can return to it.
    MainMenu mainMenu;

    // These labels show the heading of this section.
    JLabel title, subtitle;

    // These buttons open tree topics.
    JButton traversalButton, bstButton, avlButton, backButton;

    TreeMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;

        // Null layout is simple and easy to understand.
        setLayout(null);

        // Main heading of the tree section.
        title = new JLabel("Tree Section");
        title.setBounds(305, 35, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // This line tells the user to choose a tree topic.
        subtitle = new JLabel("Select the tree topic you want to visualize");
        subtitle.setBounds(240, 85, 380, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setForeground(new Color(70, 80, 90));
        add(subtitle);

        // This button opens the basic tree traversal screen.
        traversalButton = new JButton("Tree Traversal");
        traversalButton.setBounds(290, 150, 280, 45);
        traversalButton.addActionListener(this);
        designButton(traversalButton);
        add(traversalButton);

        // This button is kept for binary search tree.
        bstButton = new JButton("Binary Search Tree");
        bstButton.setBounds(290, 215, 280, 45);
        bstButton.addActionListener(this);
        designButton(bstButton);
        add(bstButton);

        // This button is kept for AVL tree.
        avlButton = new JButton("AVL Tree");
        avlButton.setBounds(290, 280, 280, 45);
        avlButton.addActionListener(this);
        designButton(avlButton);
        add(avlButton);

        // This button returns to the main menu.
        backButton = new JButton("Back");
        backButton.setBounds(290, 345, 280, 45);
        backButton.addActionListener(this);
        designButton(backButton);
        add(backButton);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(860, 500);
        MainMenu.makeResponsive(this, 860, 500);
        MainMenu.designTopicButtons(this, MainMenu.TREE_CONVERSION_COLOR);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == traversalButton) {
            new TreeTraversalVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == bstButton) {
            new BinarySearchTreeVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == avlButton) {
            new AVLTreeVisualizer(this);
            setVisible(false);
        } else if (e.getSource() == backButton) {
            mainMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method gives the same style to every button.
    private void designButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setBackground(MainMenu.TREE_CONVERSION_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(235, 240, 245), 1));
    }
}
