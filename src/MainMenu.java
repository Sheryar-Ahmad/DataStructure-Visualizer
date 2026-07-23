import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {

    // These colors are used for the buttons in all screens.
    static final Color LINKED_LIST_COLOR = new Color(70, 120, 125);
    static final Color STACK_QUEUE_COLOR = new Color(80, 110, 150);
    static final Color TREE_CONVERSION_COLOR = new Color(110, 95, 145);
    static final Color EXIT_COLOR = new Color(74, 82, 94);

    // These labels show the heading of the main screen.
    JLabel title, subtitle;

    // These buttons are used to open different data structure topics.
    JButton arrays, linkedLists, stacks, queues, trees, conversions, exit;

    MainMenu() {
        super("Data Structure Visualizer");

        // This panel is the main background of the window.
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 45, 25, 45));
        mainPanel.setBackground(new Color(34, 43, 52));
        add(mainPanel);

        // Header panel contains project title and short line.
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        headerPanel.setBackground(new Color(38, 75, 90));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Main title of the project.
        title = new JLabel("DATA STRUCTURE VISUALIZER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        // This line tells the user what to do.
        subtitle = new JLabel("Select a topic to understand how data is stored graphically", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(221, 238, 240));
        headerPanel.add(subtitle);

        // This panel arranges all topic buttons in rows and columns.
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 35, 20));
        buttonPanel.setBackground(new Color(34, 43, 52));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Array button opens the array topic screen.
        arrays = new JButton("ARRAYS");
        designButton(arrays, LINKED_LIST_COLOR);
        arrays.addActionListener(this);
        buttonPanel.add(arrays);

        // Linked list button opens the linked list menu.
        linkedLists = new JButton("LINKED LISTS");
        designButton(linkedLists, LINKED_LIST_COLOR);
        linkedLists.addActionListener(this);
        buttonPanel.add(linkedLists);

        // Stack button opens the stack menu.
        stacks = new JButton("STACKS");
        designButton(stacks, STACK_QUEUE_COLOR);
        stacks.addActionListener(this);
        buttonPanel.add(stacks);

        // Queue button opens the queue menu.
        queues = new JButton("QUEUES");
        designButton(queues, STACK_QUEUE_COLOR);
        queues.addActionListener(this);
        buttonPanel.add(queues);

        // Tree button opens the tree menu.
        trees = new JButton("TREES");
        designButton(trees, TREE_CONVERSION_COLOR);
        trees.addActionListener(this);
        buttonPanel.add(trees);

        // Conversion button opens infix conversion topics.
        conversions = new JButton("CONVERSIONS");
        designButton(conversions, TREE_CONVERSION_COLOR);
        conversions.addActionListener(this);
        buttonPanel.add(conversions);

        // This panel keeps the exit button separate from topic buttons.
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitPanel.setBackground(new Color(34, 43, 52));
        mainPanel.add(exitPanel, BorderLayout.SOUTH);

        // Exit button closes the project.
        exit = new JButton("EXIT");
        designButton(exit, EXIT_COLOR);
        exit.setPreferredSize(new Dimension(220, 42));
        exit.addActionListener(this);
        exitPanel.add(exit);

        // These settings control the size and closing behavior of the main menu.
        setSize(900, 485);
        setMinimumSize(new Dimension(720, 420));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method runs when any button is clicked.
    public void actionPerformed(ActionEvent e) {

        // If the array button is clicked, open array visualizer.
        if (e.getSource() == arrays) {
            new ArrayVisualizer(this);
            setVisible(false);

        } else if (e.getSource() == linkedLists) {
            new LinkedListMenu(this);
            setVisible(false);
        } else if (e.getSource() == stacks) {
            new StackMenu(this);
            setVisible(false);
        } else if (e.getSource() == queues) {
            new QueueMenu(this);
            setVisible(false);
        } else if (e.getSource() == trees) {
            new TreeMenu(this);
            setVisible(false);
        } else if (e.getSource() == conversions) {
            new ConversionMenu(this);
            setVisible(false);

        // Exit button ends the program.
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    // This method gives the simple style to every button.
    private void designButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(235, 240, 245), 1));
    }

    // This method makes fixed-position screens scale when the window size changes.
    static void makeResponsive(JFrame frame, int baseWidth, int baseHeight) {
        frame.setMinimumSize(new Dimension(baseWidth, baseHeight));

        Container contentPane = frame.getContentPane();
        Component[] components = contentPane.getComponents();
        Rectangle[] bounds = new Rectangle[components.length];

        for (int i = 0; i < components.length; i++) {
            bounds[i] = components[i].getBounds();
        }

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int currentWidth = contentPane.getWidth();
                int currentHeight = contentPane.getHeight();

                if (currentWidth <= 0 || currentHeight <= 0) {
                    return;
                }

                double widthScale = currentWidth / (double) baseWidth;
                double heightScale = currentHeight / (double) baseHeight;

                for (int i = 0; i < components.length; i++) {
                    Rectangle oldBounds = bounds[i];
                    components[i].setBounds(
                            (int) (oldBounds.x * widthScale),
                            (int) (oldBounds.y * heightScale),
                            (int) (oldBounds.width * widthScale),
                            (int) (oldBounds.height * heightScale)
                    );
                }

                contentPane.revalidate();
                contentPane.repaint();
            }
        });
    }

    // This method gives every button the main menu style with clearer text.
    static void designTopicButtons(Container container, Color color) {
        Component[] components = container.getComponents();

        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setFont(new Font("Arial", Font.BOLD, 17));
                button.setBackground(color);
                button.setForeground(Color.WHITE);
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createLineBorder(new Color(235, 240, 245), 1));
            }

            if (component instanceof Container) {
                designTopicButtons((Container) component, color);
            }
        }
    }

    // This main method lets us run this file directly if needed.
    public static void main(String[] args) {
        new MainMenu();
    }
}
