import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SinglyLinkedListVisualizer extends JFrame implements ActionListener {

    // This keeps the linked list menu so the Back button can open it again.
    LinkedListMenu linkedListMenu;

    // These labels and field are used to take value from the user.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform linked list operations.
    JButton insertStartButton, insertEndButton, deleteFirstButton, deleteLastButton;
    JButton searchButton, traverseButton, clearButton, backButton;

    // This area shows the work done by the program.
    JTextArea logArea;

    // This panel draws linked list nodes and arrows.
    DrawListPanel drawPanel;

    // Head points to the first node of the linked list.
    Node head;

    // This position is highlighted after search or insert.
    int selectedPosition = -1;

    // This class represents one node of the linked list.
    static class Node {

        // Data stores the value inside the node.
        String data;

        // Next stores the address/reference of the next node.
        Node next;

        // Constructor puts value in the new node.
        Node(String value) {
            data = value;
            next = null;
        }
    }

    SinglyLinkedListVisualizer(LinkedListMenu linkedListMenu) {
        this.linkedListMenu = linkedListMenu;

        // We use simple fixed positions to make the code easy to understand.
        setLayout(null);

        // Main title of the linked list screen.
        title = new JLabel("Single Linked List Visualizer");
        title.setBounds(280, 20, 430, 40);
        title.setFont(new Font("Arial", Font.BOLD, 27));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for the input value.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(40, 90, 70, 25);
        add(valueLabel);

        // Text field where user writes node value.
        valueField = new JTextField();
        valueField.setBounds(105, 90, 150, 25);
        add(valueField);

        // This button inserts a node at the start.
        insertStartButton = new JButton("Insert Start");
        insertStartButton.setBounds(290, 80, 125, 35);
        insertStartButton.addActionListener(this);
        add(insertStartButton);

        // This button inserts a node at the end.
        insertEndButton = new JButton("Insert End");
        insertEndButton.setBounds(430, 80, 125, 35);
        insertEndButton.addActionListener(this);
        add(insertEndButton);

        // This button deletes the first node.
        deleteFirstButton = new JButton("Delete First");
        deleteFirstButton.setBounds(570, 80, 125, 35);
        deleteFirstButton.addActionListener(this);
        add(deleteFirstButton);

        // This button deletes the last node.
        deleteLastButton = new JButton("Delete Last");
        deleteLastButton.setBounds(710, 80, 125, 35);
        deleteLastButton.addActionListener(this);
        add(deleteLastButton);

        // This button searches a value in the list.
        searchButton = new JButton("Search");
        searchButton.setBounds(290, 130, 125, 35);
        searchButton.addActionListener(this);
        add(searchButton);

        // This button shows all nodes in order.
        traverseButton = new JButton("Traverse");
        traverseButton.setBounds(430, 130, 125, 35);
        traverseButton.addActionListener(this);
        add(traverseButton);

        // This button clears the full linked list.
        clearButton = new JButton("Clear");
        clearButton.setBounds(570, 130, 125, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // This button goes back to the main menu.
        backButton = new JButton("Back");
        backButton.setBounds(710, 130, 125, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label explains the current condition of the list.
        messageLabel = new JLabel("Head is null. The linked list is empty.");
        messageLabel.setBounds(40, 185, 820, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows nodes, links, HEAD, and NULL.
        drawPanel = new DrawListPanel();
        drawPanel.setBounds(40, 225, 820, 210);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area stores small messages after every operation.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(40, 455, 820, 105);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(920, 635);
        MainMenu.makeResponsive(this, 920, 635);
        MainMenu.designTopicButtons(this, MainMenu.LINKED_LIST_COLOR);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button the user clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertStartButton) {
            insertAtStart();
        } else if (e.getSource() == insertEndButton) {
            insertAtEnd();
        } else if (e.getSource() == deleteFirstButton) {
            deleteFirst();
        } else if (e.getSource() == deleteLastButton) {
            deleteLast();
        } else if (e.getSource() == searchButton) {
            searchValue();
        } else if (e.getSource() == traverseButton) {
            traverseList();
        } else if (e.getSource() == clearButton) {
            clearList();
        } else if (e.getSource() == backButton) {
            linkedListMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method inserts a new node at the start of the list.
    private void insertAtStart() {
        String value = getValue();

        // If value is empty, stop this operation.
        if (value == null) {
            return;
        }

        // Create a new node for the entered value.
        Node newNode = new Node(value);

        // New node will point to the old head.
        newNode.next = head;

        // Now head will point to the new node.
        head = newNode;
        selectedPosition = 0;
        messageLabel.setText("Inserted " + value + " at the start. Head moved to the new node.");
        logArea.append("Inserted " + value + " at start\n");
        drawPanel.repaint();
    }

    // This method inserts a new node at the end of the list.
    private void insertAtEnd() {
        String value = getValue();

        // If value is empty, stop this operation.
        if (value == null) {
            return;
        }

        // Create the new node first.
        Node newNode = new Node(value);

        // If list is empty, new node becomes head.
        if (head == null) {
            head = newNode;
            selectedPosition = 0;
            messageLabel.setText("Inserted " + value + ". This is the first node, so head points to it.");
            logArea.append("Inserted " + value + " as first node\n");
            drawPanel.repaint();
            return;
        }

        // Start from head and move until the last node.
        Node currNode = head;
        int position = 0;

        // Last node is the node whose next is null.
        while (currNode.next != null) {
            currNode = currNode.next;
            position++;
        }

        // Last node now points to the new node.
        currNode.next = newNode;
        selectedPosition = position + 1;
        messageLabel.setText("Inserted " + value + " at the end of the linked list.");
        logArea.append("Inserted " + value + " at end\n");
        drawPanel.repaint();
    }

    // This method deletes the first node of the list.
    private void deleteFirst() {

        // Empty list has no node to delete.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // Save value only for showing message.
        String deletedValue = head.data;

        // Head moves to the second node.
        head = head.next;
        selectedPosition = -1;
        messageLabel.setText("Deleted first node with value " + deletedValue + ".");
        logArea.append("Deleted first node: " + deletedValue + "\n");
        drawPanel.repaint();
    }

    // This method deletes the last node of the list.
    private void deleteLast() {

        // Empty list has no node to delete.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // If only one node exists, head becomes null.
        if (head.next == null) {
            String deletedValue = head.data;
            head = null;
            selectedPosition = -1;
            messageLabel.setText("Deleted last node with value " + deletedValue + ". List is now empty.");
            logArea.append("Deleted last node: " + deletedValue + "\n");
            drawPanel.repaint();
            return;
        }

        // secondLast will stop one node before the last node.
        Node secondLast = head;

        // Move until secondLast.next is the last node.
        while (secondLast.next.next != null) {
            secondLast = secondLast.next;
        }

        // Save last value for message.
        String deletedValue = secondLast.next.data;

        // Remove the last node by making secondLast.next null.
        secondLast.next = null;
        selectedPosition = -1;
        messageLabel.setText("Deleted last node with value " + deletedValue + ".");
        logArea.append("Deleted last node: " + deletedValue + "\n");
        drawPanel.repaint();
    }

    // This method searches the entered value in the list.
    private void searchValue() {
        String value = getValue();

        // If value is empty, stop searching.
        if (value == null) {
            return;
        }

        // Start checking from the head node.
        Node currNode = head;
        int position = 0;

        // Move through nodes one by one.
        while (currNode != null) {
            if (currNode.data.equals(value)) {
                selectedPosition = position;
                messageLabel.setText("Value " + value + " found at node position " + position + ".");
                logArea.append("Searched " + value + " and found at position " + position + "\n");
                drawPanel.repaint();
                return;
            }

            currNode = currNode.next;
            position++;
        }

        // This runs when the value is not present.
        selectedPosition = -1;
        messageLabel.setText("Value " + value + " not found in the linked list.");
        logArea.append("Searched " + value + " but not found\n");
        drawPanel.repaint();
    }

    // This method prints all nodes in the log area.
    private void traverseList() {

        // Empty list has nothing to traverse.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // Build output like A -> B -> C -> Null.
        String output = "";
        Node currNode = head;

        // Visit each node from head to null.
        while (currNode != null) {
            output = output + currNode.data + " -> ";
            currNode = currNode.next;
        }

        // Add Null to show where the list ends.
        output = output + "Null";
        selectedPosition = -1;
        messageLabel.setText("Traversal completed from head to null.");
        logArea.append("Traversal: " + output + "\n");
        drawPanel.repaint();
    }

    // This method clears the full list.
    private void clearList() {
        head = null;
        selectedPosition = -1;
        messageLabel.setText("Head is null. The linked list is empty.");
        logArea.append("Linked list cleared\n");
        drawPanel.repaint();
    }

    // This method gets value from the text field.
    private String getValue() {
        String value = valueField.getText().trim();

        // Empty value should not be inserted or searched.
        if (value.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a value first.");
            return null;
        }

        return value;
    }

    // This inner class draws the linked list.
    class DrawListPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Starting position for the first node.
            int x = 70;
            int y = 82;
            int nodeWidth = 95;
            int nodeHeight = 50;
            int arrowGap = 35;

            // Draw heading inside the visual panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Single Linked List", 35, 35);

            // If list is empty, show HEAD -> NULL.
            if (head == null) {
                g.drawString("HEAD", 70, 95);
                g.drawLine(120, 90, 190, 90);
                g.drawString("NULL", 205, 95);
                return;
            }

            // Show HEAD pointing to the first node.
            g.drawString("HEAD", 25, y + 30);
            g.drawLine(68, y + 25, x - 10, y + 25);

            // Start drawing from the first node.
            Node currNode = head;
            int position = 0;

            // Draw every node until currNode becomes null.
            while (currNode != null) {
                if (position == selectedPosition) {
                    g.setColor(new Color(255, 230, 150));
                } else {
                    g.setColor(new Color(220, 235, 240));
                }

                // Draw one node box.
                g.fillRect(x, y, nodeWidth, nodeHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, nodeWidth, nodeHeight);

                // Draw line inside node to show data and next parts.
                g.drawLine(x + 65, y, x + 65, y + nodeHeight);

                // Show value inside the data part of the node.
                g.drawString(currNode.data, x + 18, y + 30);

                // Show next word in the pointer part.
                g.drawString("next", x + 68, y + 30);

                // Show node position below the box.
                g.drawString("Node " + position, x + 25, y + 75);

                // Draw arrow to next node or show NULL at the end.
                if (currNode.next != null) {
                    int startX = x + nodeWidth;
                    int endX = x + nodeWidth + arrowGap;
                    int midY = y + 25;

                    g.drawLine(startX, midY, endX, midY);
                    g.drawLine(endX, midY, endX - 8, midY - 6);
                    g.drawLine(endX, midY, endX - 8, midY + 6);
                } else {
                    g.drawLine(x + nodeWidth, y + 25, x + nodeWidth + 30, y + 25);
                    g.drawString("NULL", x + nodeWidth + 38, y + 30);
                }

                // Move x position for the next node.
                x = x + nodeWidth + arrowGap;
                currNode = currNode.next;
                position++;
            }
        }
    }
}
