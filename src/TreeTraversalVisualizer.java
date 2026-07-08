import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeTraversalVisualizer extends JFrame implements ActionListener {

    // This keeps the tree menu so Back can open it again.
    TreeMenu treeMenu;

    // These labels and fields are used for input and messages.
    JLabel title, rootLabel, leftLabel, rightLabel, messageLabel;
    JTextField rootField, leftField, rightField;

    // These buttons perform tree operations.
    JButton createButton, inorderButton, preorderButton, postorderButton, clearButton, backButton;

    // This area shows traversal output.
    JTextArea logArea;

    // This panel draws the tree.
    DrawTreePanel drawPanel;

    // Root points to the first node of the tree.
    Node root;

    // This class represents one tree node.
    static class Node {

        // Data stores the value inside the node.
        int data;

        // Left points to the left child.
        Node left;

        // Right points to the right child.
        Node right;

        // Constructor stores value and makes children null.
        public Node(int value) {
            data = value;
            left = null;
            right = null;
        }
    }

    TreeTraversalVisualizer(TreeMenu treeMenu) {
        this.treeMenu = treeMenu;

        // Null layout keeps positions simple and clear.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Tree Traversal Visualizer");
        title.setBounds(290, 20, 390, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label and input field for root value.
        rootLabel = new JLabel("Root:");
        rootLabel.setBounds(40, 85, 70, 25);
        add(rootLabel);

        rootField = new JTextField();
        rootField.setBounds(95, 85, 100, 25);
        add(rootField);

        // Label and input field for left child.
        leftLabel = new JLabel("Left:");
        leftLabel.setBounds(215, 85, 70, 25);
        add(leftLabel);

        leftField = new JTextField();
        leftField.setBounds(265, 85, 100, 25);
        add(leftField);

        // Label and input field for right child.
        rightLabel = new JLabel("Right:");
        rightLabel.setBounds(385, 85, 70, 25);
        add(rightLabel);

        rightField = new JTextField();
        rightField.setBounds(440, 85, 100, 25);
        add(rightField);

        // Create button builds the tree from three values.
        createButton = new JButton("Create Tree");
        createButton.setBounds(570, 80, 125, 35);
        createButton.addActionListener(this);
        add(createButton);

        // Inorder button shows left, root, right order.
        inorderButton = new JButton("Inorder");
        inorderButton.setBounds(710, 80, 105, 35);
        inorderButton.addActionListener(this);
        add(inorderButton);

        // Preorder button shows root, left, right order.
        preorderButton = new JButton("Preorder");
        preorderButton.setBounds(570, 130, 125, 35);
        preorderButton.addActionListener(this);
        add(preorderButton);

        // Postorder button shows left, right, root order.
        postorderButton = new JButton("Postorder");
        postorderButton.setBounds(710, 130, 105, 35);
        postorderButton.addActionListener(this);
        add(postorderButton);

        // Clear button removes the current tree.
        clearButton = new JButton("Clear");
        clearButton.setBounds(830, 80, 90, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to tree menu.
        backButton = new JButton("Back");
        backButton.setBounds(830, 130, 90, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label shows the current state.
        messageLabel = new JLabel("Basic binary tree: left and right values are placed exactly where you enter them.");
        messageLabel.setBounds(40, 185, 880, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows the graphical tree.
        drawPanel = new DrawTreePanel();
        drawPanel.setBounds(40, 225, 420, 300);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area shows traversal result.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(490, 225, 430, 300);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(980, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            createTree();
        } else if (e.getSource() == inorderButton) {
            showInorder();
        } else if (e.getSource() == preorderButton) {
            showPreorder();
        } else if (e.getSource() == postorderButton) {
            showPostorder();
        } else if (e.getSource() == clearButton) {
            clearTree();
        } else if (e.getSource() == backButton) {
            treeMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method creates root, left child, and right child.
    private void createTree() {
        Integer rootValue = getNumber(rootField, "root");
        Integer leftValue = getNumber(leftField, "left child");
        Integer rightValue = getNumber(rightField, "right child");

        // If any input is wrong, stop this method.
        if (rootValue == null || leftValue == null || rightValue == null) {
            return;
        }

        // Create individual nodes, same as the sample code.
        root = new Node(rootValue);
        Node leftChild = new Node(leftValue);
        Node rightChild = new Node(rightValue);

        // Link left and right child with root.
        root.left = leftChild;
        root.right = rightChild;

        messageLabel.setText("Tree created manually. Left value is left child and right value is right child.");
        logArea.append("Tree created: root=" + rootValue + ", left=" + leftValue + ", right=" + rightValue + "\n");
        drawPanel.repaint();
    }

    // This method shows inorder traversal.
    private void showInorder() {
        if (root == null) {
            JOptionPane.showMessageDialog(null, "Create the tree first.");
            return;
        }

        // Inorder means left, root, right.
        String output = printTree(root);
        messageLabel.setText("Inorder traversal: left, root, right.");
        logArea.append("Inorder: " + output + "\n");
    }

    // This method shows preorder traversal.
    private void showPreorder() {
        if (root == null) {
            JOptionPane.showMessageDialog(null, "Create the tree first.");
            return;
        }

        // Preorder means root, left, right.
        String output = printPreorder(root);
        messageLabel.setText("Preorder traversal: root, left, right.");
        logArea.append("Preorder: " + output + "\n");
    }

    // This method shows postorder traversal.
    private void showPostorder() {
        if (root == null) {
            JOptionPane.showMessageDialog(null, "Create the tree first.");
            return;
        }

        // Postorder means left, right, root.
        String output = printPostorder(root);
        messageLabel.setText("Postorder traversal: left, right, root.");
        logArea.append("Postorder: " + output + "\n");
    }

    // This recursive method prints tree in inorder.
    public String printTree(Node node) {
        if (node == null) {
            return "";
        }

        return printTree(node.left) + node.data + " " + printTree(node.right);
    }

    // This recursive method prints tree in preorder.
    public String printPreorder(Node node) {
        if (node == null) {
            return "";
        }

        return node.data + " " + printPreorder(node.left) + printPreorder(node.right);
    }

    // This recursive method prints tree in postorder.
    public String printPostorder(Node node) {
        if (node == null) {
            return "";
        }

        return printPostorder(node.left) + printPostorder(node.right) + node.data + " ";
    }

    // This method clears the current tree.
    private void clearTree() {
        root = null;
        messageLabel.setText("Tree cleared. Root is null.");
        logArea.append("Tree cleared\n");
        drawPanel.repaint();
    }

    // This method reads a number from a text field.
    private Integer getNumber(JTextField field, String name) {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter correct " + name + " value.");
            return null;
        }
    }

    // This inner class draws the tree.
    class DrawTreePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw heading inside the panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Basic Binary Tree", 145, 30);

            // If tree is empty, show root as null.
            if (root == null) {
                g.drawString("ROOT", 135, 145);
                g.drawLine(180, 140, 250, 140);
                g.drawString("NULL", 265, 145);
                return;
            }

            // These positions are used for root and child nodes.
            int rootX = 190;
            int rootY = 70;
            int leftX = 95;
            int leftY = 190;
            int rightX = 285;
            int rightY = 190;

            // Draw lines from root to left and right child.
            g.drawLine(rootX + 20, rootY + 40, leftX + 20, leftY);
            g.drawLine(rootX + 20, rootY + 40, rightX + 20, rightY);

            // Draw root node.
            drawNode(g, rootX, rootY, root.data, "Root");

            // Draw left child node.
            drawNode(g, leftX, leftY, root.left.data, "Left");

            // Draw right child node.
            drawNode(g, rightX, rightY, root.right.data, "Right");
        }

        // This method draws one circular tree node.
        private void drawNode(Graphics g, int x, int y, int value, String name) {
            g.setColor(new Color(220, 235, 240));
            g.fillOval(x, y, 45, 45);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, 45, 45);
            g.drawString(String.valueOf(value), x + 18, y + 27);
            g.drawString(name, x + 5, y + 65);
        }
    }
}
