import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinarySearchTreeVisualizer extends JFrame implements ActionListener {

    // This keeps the tree menu so Back can open it again.
    TreeMenu treeMenu;

    // These labels and field are used for input and messages.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform BST operations.
    JButton insertButton, searchButton, deleteButton, inorderButton, clearButton, backButton;

    // This area shows operation history.
    JTextArea logArea;

    // This panel draws the binary search tree.
    DrawBSTPanel drawPanel;

    // Root points to the first node of the BST.
    Node root;

    // This node is highlighted after insert or search.
    Node selectedNode;

    // This class represents one tree node.
    static class Node {

        // Data stores the value inside the node.
        int data;

        // Left stores smaller values.
        Node left;

        // Right stores greater values.
        Node right;

        // Constructor stores value and makes children null.
        Node(int value) {
            data = value;
            left = null;
            right = null;
        }
    }

    BinarySearchTreeVisualizer(TreeMenu treeMenu) {
        this.treeMenu = treeMenu;

        // Null layout keeps the GUI code easy to read.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Binary Search Tree Visualizer");
        title.setBounds(275, 20, 470, 40);
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

        // Insert button adds value in BST.
        insertButton = new JButton("Insert");
        insertButton.setBounds(290, 80, 105, 35);
        insertButton.addActionListener(this);
        add(insertButton);

        // Search button finds value in BST.
        searchButton = new JButton("Search");
        searchButton.setBounds(410, 80, 105, 35);
        searchButton.addActionListener(this);
        add(searchButton);

        // Delete button removes value from BST.
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(530, 80, 105, 35);
        deleteButton.addActionListener(this);
        add(deleteButton);

        // Inorder button prints sorted values.
        inorderButton = new JButton("Inorder");
        inorderButton.setBounds(650, 80, 105, 35);
        inorderButton.addActionListener(this);
        add(inorderButton);

        // Clear button removes all nodes.
        clearButton = new JButton("Clear");
        clearButton.setBounds(770, 80, 105, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to tree menu.
        backButton = new JButton("Back");
        backButton.setBounds(770, 130, 105, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label explains the current state.
        messageLabel = new JLabel("BST rule: smaller values go left, greater values go right. Example: 50, 30, 70.");
        messageLabel.setBounds(40, 160, 850, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows the tree.
        drawPanel = new DrawBSTPanel();
        drawPanel.setPreferredSize(new Dimension(1500, 900));
        drawPanel.setBackground(Color.WHITE);

        // Scroll pane lets us see the tree when it grows outside the screen.
        JScrollPane treeScrollPane = new JScrollPane(drawPanel);
        treeScrollPane.setBounds(40, 205, 850, 320);
        treeScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(treeScrollPane);

        // Log area shows operation messages.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(40, 545, 850, 105);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(950, 730);
        MainMenu.makeResponsive(this, 950, 730);
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
                insert(value);
            }
        } else if (e.getSource() == searchButton) {
            Integer value = getValue();

            // Search needs one integer value.
            if (value != null) {
                search(value);
            }
        } else if (e.getSource() == deleteButton) {
            Integer value = getValue();

            // Delete needs one integer value.
            if (value != null) {
                delete(value);
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

    // This method inserts value into BST.
    public void insert(int value) {

        // If tree is empty, new node becomes root.
        if (root == null) {
            root = new Node(value);
            selectedNode = root;
            messageLabel.setText(value + " inserted as root node.");
            logArea.append(value + " inserted as root\n");
            drawPanel.repaint();
            return;
        }

        // Start comparing from root.
        Node currNode = root;

        // Keep moving left or right until correct place is found.
        while (true) {
            if (value == currNode.data) {
                JOptionPane.showMessageDialog(null, "Duplicate value is not allowed in this BST.");
                return;
            } else if (value < currNode.data) {
                if (currNode.left == null) {
                    currNode.left = new Node(value);
                    selectedNode = currNode.left;
                    messageLabel.setText(value + " is smaller than " + currNode.data + ", so it went to left.");
                    logArea.append(value + " inserted on left side of " + currNode.data + "\n");
                    break;
                } else {
                    currNode = currNode.left;
                }
            } else {
                if (currNode.right == null) {
                    currNode.right = new Node(value);
                    selectedNode = currNode.right;
                    messageLabel.setText(value + " is greater than " + currNode.data + ", so it went to right.");
                    logArea.append(value + " inserted on right side of " + currNode.data + "\n");
                    break;
                } else {
                    currNode = currNode.right;
                }
            }
        }

        drawPanel.repaint();
    }

    // This method searches value in BST.
    public void search(int value) {
        Node currNode = root;

        // Move left or right according to BST rule.
        while (currNode != null) {
            if (value == currNode.data) {
                selectedNode = currNode;
                messageLabel.setText(value + " found in the tree.");
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
        selectedNode = null;
        messageLabel.setText(value + " not found in the tree.");
        logArea.append(value + " not found\n");
        drawPanel.repaint();
    }

    // This method deletes a value from BST.
    public void delete(int value) {
        if (!contains(root, value)) {
            JOptionPane.showMessageDialog(null, "Value not found.");
            return;
        }

        root = deleteNode(root, value);
        selectedNode = null;
        messageLabel.setText(value + " deleted from BST.");
        logArea.append(value + " deleted\n");
        drawPanel.repaint();
    }

    // This recursive method deletes a node.
    private Node deleteNode(Node node, int value) {
        if (node == null) {
            return null;
        }

        // First find the node by going left or right.
        if (value < node.data) {
            node.left = deleteNode(node.left, value);
        } else if (value > node.data) {
            node.right = deleteNode(node.right, value);
        } else {

            // If node has no left child, return right child.
            if (node.left == null) {
                return node.right;
            }

            // If node has no right child, return left child.
            if (node.right == null) {
                return node.left;
            }

            // If node has two children, use smallest value from right side.
            Node smallest = findSmallest(node.right);
            node.data = smallest.data;
            node.right = deleteNode(node.right, smallest.data);
        }

        return node;
    }

    // This method finds the smallest node in a subtree.
    private Node findSmallest(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    // This method checks if a value exists.
    private boolean contains(Node node, int value) {
        while (node != null) {
            if (value == node.data) {
                return true;
            } else if (value < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return false;
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

    // This method clears the full tree.
    private void clearTree() {
        root = null;
        selectedNode = null;
        messageLabel.setText("BST rule: smaller values go left, greater values go right. Example: 50, 30, 70.");
        logArea.append("BST cleared\n");
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

    // This inner class draws the BST.
    class DrawBSTPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw heading inside panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Binary Search Tree", 350, 30);

            // If tree is empty, show root as null.
            if (root == null) {
                g.drawString("ROOT", 330, 160);
                g.drawLine(375, 155, 445, 155);
                g.drawString("NULL", 460, 160);
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

            // Draw line and left child.
            if (node.left != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x + 20, y + 40, x - gap + 20, y + 85);
                drawNode(g, node.left, x - gap, y + 85, gap / 2);
            }

            // Draw line and right child.
            if (node.right != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x + 20, y + 40, x + gap + 20, y + 85);
                drawNode(g, node.right, x + gap, y + 85, gap / 2);
            }

            // Highlight selected node.
            if (node == selectedNode) {
                g.setColor(new Color(255, 230, 150));
            } else {
                g.setColor(new Color(220, 235, 240));
            }

            // Draw node circle.
            g.fillOval(x, y, 45, 45);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, 45, 45);
            g.drawString(String.valueOf(node.data), x + 15, y + 27);
        }
    }
}
