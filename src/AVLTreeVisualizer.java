import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AVLTreeVisualizer extends JFrame implements ActionListener {

    // This keeps the tree menu so Back can open it again.
    TreeMenu treeMenu;

    // These labels and field are used for input and messages.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform AVL operations.
    JButton insertButton, searchButton, inorderButton, clearButton, backButton;

    // This area shows operation history and rotations.
    JTextArea logArea;

    // This panel draws the AVL tree.
    DrawAVLPanel drawPanel;

    // Root points to the first node of the AVL tree.
    Node root;

    // This value is highlighted after insert or search.
    int selectedValue;

    // This tells if a selected value exists.
    boolean hasSelectedValue = false;

    // This tells if a new value was actually inserted.
    boolean valueInserted = false;

    // This class represents one AVL tree node.
    static class Node {

        // Data stores the value inside the node.
        int data;

        // Left stores smaller values.
        Node left;

        // Right stores greater values.
        Node right;

        // Height is used to check balance.
        int height;

        // Constructor stores value and sets height to 1.
        Node(int value) {
            data = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    AVLTreeVisualizer(TreeMenu treeMenu) {
        this.treeMenu = treeMenu;

        // Null layout keeps the GUI code simple.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("AVL Tree Visualizer");
        title.setBounds(330, 20, 330, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for value input.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(40, 90, 70, 25);
        add(valueLabel);

        // Text field where user enters value.
        valueField = new JTextField();
        valueField.setBounds(105, 90, 150, 25);
        add(valueField);

        // Insert button adds value and balances the tree.
        insertButton = new JButton("Insert");
        insertButton.setBounds(300, 80, 110, 35);
        insertButton.addActionListener(this);
        add(insertButton);

        // Search button finds a value.
        searchButton = new JButton("Search");
        searchButton.setBounds(425, 80, 110, 35);
        searchButton.addActionListener(this);
        add(searchButton);

        // Inorder button prints sorted values.
        inorderButton = new JButton("Inorder");
        inorderButton.setBounds(550, 80, 110, 35);
        inorderButton.addActionListener(this);
        add(inorderButton);

        // Clear button removes all nodes.
        clearButton = new JButton("Clear");
        clearButton.setBounds(675, 80, 110, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to tree menu.
        backButton = new JButton("Back");
        backButton.setBounds(800, 80, 90, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label explains the current state.
        messageLabel = new JLabel("AVL uses BST rule, then rotates if one side becomes too heavy.");
        messageLabel.setBounds(40, 150, 850, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows the AVL tree.
        drawPanel = new DrawAVLPanel();
        drawPanel.setPreferredSize(new Dimension(1500, 900));
        drawPanel.setBackground(Color.WHITE);

        // Scroll pane lets us see the tree when it grows outside the screen.
        JScrollPane treeScrollPane = new JScrollPane(drawPanel);
        treeScrollPane.setBounds(40, 190, 850, 335);
        treeScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(treeScrollPane);

        // Log area shows messages and rotation names.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(40, 545, 850, 110);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(950, 735);
        MainMenu.makeResponsive(this, 950, 735);
        MainMenu.designTopicButtons(this, MainMenu.TREE_CONVERSION_COLOR);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertButton) {
            Integer value = getValue();

            // Insert needs one integer value.
            if (value != null) {
                insertValue(value);
            }
        } else if (e.getSource() == searchButton) {
            Integer value = getValue();

            // Search needs one integer value.
            if (value != null) {
                search(value);
            }
        } else if (e.getSource() == inorderButton) {
            printInorder();
        } else if (e.getSource() == clearButton) {
            clearTree();
        } else if (e.getSource() == backButton) {
            treeMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method starts AVL insertion.
    public void insertValue(int value) {
        valueInserted = false;
        root = insert(root, value);

        // If value was duplicate, do not show it as inserted.
        if (!valueInserted) {
            JOptionPane.showMessageDialog(null, "Duplicate value is not allowed in this AVL tree.");
            return;
        }

        selectedValue = value;
        hasSelectedValue = true;
        messageLabel.setText(value + " inserted. AVL checked height and balance.");
        logArea.append(value + " inserted\n");
        drawPanel.repaint();
    }

    // This recursive method inserts a value and balances the tree.
    private Node insert(Node node, int value) {

        // If place is empty, create a new node.
        if (node == null) {
            valueInserted = true;
            return new Node(value);
        }

        // Insert like a normal binary search tree first.
        if (value < node.data) {
            node.left = insert(node.left, value);
        } else if (value > node.data) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        // Update height after insertion.
        node.height = 1 + max(height(node.left), height(node.right));

        // Balance tells if left side or right side is heavy.
        int balance = getBalance(node);

        // Left Left case needs right rotation.
        if (balance > 1 && value < node.left.data) {
            logArea.append("Right rotation on " + node.data + "\n");
            return rightRotate(node);
        }

        // Right Right case needs left rotation.
        if (balance < -1 && value > node.right.data) {
            logArea.append("Left rotation on " + node.data + "\n");
            return leftRotate(node);
        }

        // Left Right case needs left rotation then right rotation.
        if (balance > 1 && value > node.left.data) {
            logArea.append("Left rotation on " + node.left.data + "\n");
            node.left = leftRotate(node.left);
            logArea.append("Right rotation on " + node.data + "\n");
            return rightRotate(node);
        }

        // Right Left case needs right rotation then left rotation.
        if (balance < -1 && value < node.right.data) {
            logArea.append("Right rotation on " + node.right.data + "\n");
            node.right = rightRotate(node.right);
            logArea.append("Left rotation on " + node.data + "\n");
            return leftRotate(node);
        }

        return node;
    }

    // This method performs right rotation.
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node temp = x.right;

        // Rotation changes the links.
        x.right = y;
        y.left = temp;

        // Update heights after rotation.
        y.height = 1 + max(height(y.left), height(y.right));
        x.height = 1 + max(height(x.left), height(x.right));

        return x;
    }

    // This method performs left rotation.
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node temp = y.left;

        // Rotation changes the links.
        y.left = x;
        x.right = temp;

        // Update heights after rotation.
        x.height = 1 + max(height(x.left), height(x.right));
        y.height = 1 + max(height(y.left), height(y.right));

        return y;
    }

    // This method returns height of a node.
    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    // This method returns balance of a node.
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        } else {
            return height(node.left) - height(node.right);
        }
    }

    // This method returns the greater number.
    private int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    // This method searches value in AVL tree.
    public void search(int value) {
        Node currNode = root;

        // Searching is the same as BST searching.
        while (currNode != null) {
            if (value == currNode.data) {
                selectedValue = value;
                hasSelectedValue = true;
                messageLabel.setText(value + " found in the AVL tree.");
                logArea.append(value + " found\n");
                drawPanel.repaint();
                return;
            } else if (value < currNode.data) {
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }
        }

        // This runs when value is not found.
        hasSelectedValue = false;
        messageLabel.setText(value + " not found in the AVL tree.");
        logArea.append(value + " not found\n");
        drawPanel.repaint();
    }

    // This method prints inorder traversal.
    private void printInorder() {
        if (root == null) {
            JOptionPane.showMessageDialog(null, "Tree is empty.");
            return;
        }

        String output = inorder(root).trim();
        messageLabel.setText("Inorder traversal gives sorted values.");
        logArea.append("Inorder: " + output + "\n");
    }

    // This recursive method returns left, root, right order.
    private String inorder(Node node) {
        if (node == null) {
            return "";
        }

        return inorder(node.left) + node.data + " " + inorder(node.right);
    }

    // This method clears the full AVL tree.
    private void clearTree() {
        root = null;
        hasSelectedValue = false;
        messageLabel.setText("AVL uses BST rule, then rotates if one side becomes too heavy.");
        logArea.append("AVL tree cleared\n");
        drawPanel.repaint();
    }

    // This method reads integer value from the text field.
    private Integer getValue() {
        try {
            return Integer.parseInt(valueField.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a correct integer value.");
            return null;
        }
    }

    // This inner class draws the AVL tree.
    class DrawAVLPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw heading inside panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("AVL Tree", 390, 30);

            // If tree is empty, show root as null.
            if (root == null) {
                g.drawString("ROOT", 330, 170);
                g.drawLine(375, 165, 445, 165);
                g.drawString("NULL", 460, 170);
                return;
            }

            // Draw tree from root.
            drawNode(g, root, 420, 55, 190);
        }

        // This method draws one node and its children.
        private void drawNode(Graphics g, Node node, int x, int y, int gap) {
            if (node == null) {
                return;
            }

            // Draw left child link.
            if (node.left != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x + 22, y + 45, x - gap + 22, y + 90);
                drawNode(g, node.left, x - gap, y + 90, gap / 2);
            }

            // Draw right child link.
            if (node.right != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x + 22, y + 45, x + gap + 22, y + 90);
                drawNode(g, node.right, x + gap, y + 90, gap / 2);
            }

            // Highlight selected value.
            if (hasSelectedValue && node.data == selectedValue) {
                g.setColor(new Color(255, 230, 150));
            } else {
                g.setColor(new Color(220, 235, 240));
            }

            // Draw node circle.
            g.fillOval(x, y, 48, 48);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, 48, 48);
            g.drawString(String.valueOf(node.data), x + 17, y + 28);

            // Show height below each node.
            g.drawString("h=" + node.height, x + 10, y + 65);
        }
    }
}
