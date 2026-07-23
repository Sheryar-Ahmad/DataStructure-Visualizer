import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircularLinkedListVisualizer extends JFrame implements ActionListener {

    // This keeps the linked list menu so Back can open it again.
    LinkedListMenu linkedListMenu;

    // These labels and field are used for input and messages.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform circular linked list operations.
    JButton insertStartButton, insertEndButton, deleteFirstButton, deleteLastButton;
    JButton searchButton, traverseButton, clearButton, backButton;

    // This area shows the result of every operation.
    JTextArea logArea;

    // This panel draws the circular linked list.
    DrawCircularListPanel drawPanel;

    // Head points to the first node of the circular linked list.
    Node head;

    // This position is highlighted after insert or search.
    int selectedPosition = -1;

    // This class represents one node.
    static class Node {

        // Data stores the value of the node.
        String data;

        // Next stores the reference of the next node.
        Node next;

        // Constructor stores value and keeps next empty at first.
        Node(String value) {
            data = value;
            next = null;
        }
    }

    CircularLinkedListVisualizer(LinkedListMenu linkedListMenu) {
        this.linkedListMenu = linkedListMenu;

        // Null layout keeps the code simple and direct.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Circular Linked List Visualizer");
        title.setBounds(250, 20, 480, 40);
        title.setFont(new Font("Arial", Font.BOLD, 27));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for value input.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(40, 90, 70, 25);
        add(valueLabel);

        // Text field where user enters node value.
        valueField = new JTextField();
        valueField.setBounds(105, 90, 150, 25);
        add(valueField);

        // This button inserts node at the start.
        insertStartButton = new JButton("Insert Start");
        insertStartButton.setBounds(290, 80, 125, 35);
        insertStartButton.addActionListener(this);
        add(insertStartButton);

        // This button inserts node at the end.
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

        // This button searches a value.
        searchButton = new JButton("Search");
        searchButton.setBounds(290, 130, 125, 35);
        searchButton.addActionListener(this);
        add(searchButton);

        // This button traverses the circular linked list.
        traverseButton = new JButton("Traverse");
        traverseButton.setBounds(430, 130, 125, 35);
        traverseButton.addActionListener(this);
        add(traverseButton);

        // This button clears all nodes.
        clearButton = new JButton("Clear");
        clearButton.setBounds(570, 130, 125, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // This button returns to linked list menu.
        backButton = new JButton("Back");
        backButton.setBounds(710, 130, 125, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This message shows the current state of the circular list.
        messageLabel = new JLabel("Head is null. The circular linked list is empty.");
        messageLabel.setBounds(40, 185, 820, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows circular nodes and arrows.
        drawPanel = new DrawCircularListPanel();
        drawPanel.setBounds(40, 225, 820, 230);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area shows operation history.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(40, 475, 820, 105);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(920, 655);
        MainMenu.makeResponsive(this, 920, 655);
        MainMenu.designTopicButtons(this, MainMenu.LINKED_LIST_COLOR);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
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

    // This method inserts a node at the start.
    private void insertAtStart() {
        String value = getValue();

        // If input is empty, stop the operation.
        if (value == null) {
            return;
        }

        // Create a new node for the entered value.
        Node newNode = new Node(value);

        // If list is empty, new node points to itself.
        if (head == null) {
            head = newNode;
            newNode.next = head;
            selectedPosition = 0;
            messageLabel.setText("Inserted " + value + ". This node points back to itself.");
            logArea.append("Inserted " + value + " as first circular node\n");
            drawPanel.repaint();
            return;
        }

        // Find the last node because its next should point to new head.
        Node lastNode = head;
        while (lastNode.next != head) {
            lastNode = lastNode.next;
        }

        // New node points to old head.
        newNode.next = head;

        // Last node points to new node.
        lastNode.next = newNode;

        // Head moves to the new node.
        head = newNode;
        selectedPosition = 0;
        messageLabel.setText("Inserted " + value + " at start. Last node now points to new head.");
        logArea.append("Inserted " + value + " at start\n");
        drawPanel.repaint();
    }

    // This method inserts a node at the end.
    private void insertAtEnd() {
        String value = getValue();

        // If input is empty, stop the operation.
        if (value == null) {
            return;
        }

        // Create a new node for the entered value.
        Node newNode = new Node(value);

        // If list is empty, new node becomes head and points to itself.
        if (head == null) {
            head = newNode;
            newNode.next = head;
            selectedPosition = 0;
            messageLabel.setText("Inserted " + value + ". This is the first circular node.");
            logArea.append("Inserted " + value + " as first circular node\n");
            drawPanel.repaint();
            return;
        }

        // Start from head and move until the last node.
        Node lastNode = head;
        int position = 0;
        while (lastNode.next != head) {
            lastNode = lastNode.next;
            position++;
        }

        // Last node points to the new node.
        lastNode.next = newNode;

        // New node points back to head, so the list remains circular.
        newNode.next = head;
        selectedPosition = position + 1;
        messageLabel.setText("Inserted " + value + " at end. New last node points back to head.");
        logArea.append("Inserted " + value + " at end\n");
        drawPanel.repaint();
    }

    // This method deletes the first node.
    private void deleteFirst() {

        // Empty circular list has no node to delete.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // If only one node exists, remove it and make head null.
        if (head.next == head) {
            String deletedValue = head.data;
            head = null;
            selectedPosition = -1;
            messageLabel.setText("Deleted " + deletedValue + ". List is now empty.");
            logArea.append("Deleted first node: " + deletedValue + "\n");
            drawPanel.repaint();
            return;
        }

        // Find the last node before changing head.
        Node lastNode = head;
        while (lastNode.next != head) {
            lastNode = lastNode.next;
        }

        // Save deleted value for the message.
        String deletedValue = head.data;

        // Head moves to the second node.
        head = head.next;

        // Last node points to the new head.
        lastNode.next = head;
        selectedPosition = -1;
        messageLabel.setText("Deleted first node " + deletedValue + ". Last node still points to head.");
        logArea.append("Deleted first node: " + deletedValue + "\n");
        drawPanel.repaint();
    }

    // This method deletes the last node.
    private void deleteLast() {

        // Empty circular list has no node to delete.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // If only one node exists, remove it.
        if (head.next == head) {
            String deletedValue = head.data;
            head = null;
            selectedPosition = -1;
            messageLabel.setText("Deleted " + deletedValue + ". List is now empty.");
            logArea.append("Deleted last node: " + deletedValue + "\n");
            drawPanel.repaint();
            return;
        }

        // secondLast will stop one node before the last node.
        Node secondLast = head;

        // Move until secondLast.next is the last node.
        while (secondLast.next.next != head) {
            secondLast = secondLast.next;
        }

        // Save deleted value for message.
        String deletedValue = secondLast.next.data;

        // Remove last node and point secondLast back to head.
        secondLast.next = head;
        selectedPosition = -1;
        messageLabel.setText("Deleted last node " + deletedValue + ". New last node points to head.");
        logArea.append("Deleted last node: " + deletedValue + "\n");
        drawPanel.repaint();
    }

    // This method searches a value in the circular list.
    private void searchValue() {
        String value = getValue();

        // If input is empty, stop searching.
        if (value == null) {
            return;
        }

        // Empty circular list has nothing to search.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // Start checking from head.
        Node currNode = head;
        int position = 0;

        // This loop stops when it reaches head again.
        while (true) {
            if (currNode.data.equals(value)) {
                selectedPosition = position;
                messageLabel.setText("Value " + value + " found at node position " + position + ".");
                logArea.append("Searched " + value + " and found at position " + position + "\n");
                drawPanel.repaint();
                return;
            }

            currNode = currNode.next;
            position++;

            // If we came back to head, the value was not found.
            if (currNode == head) {
                break;
            }
        }

        // This message runs when value is not present.
        selectedPosition = -1;
        messageLabel.setText("Value " + value + " not found in the circular linked list.");
        logArea.append("Searched " + value + " but not found\n");
        drawPanel.repaint();
    }

    // This method shows all nodes in order.
    private void traverseList() {

        // Empty circular list has nothing to traverse.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // Build output by starting from head.
        String output = "";
        Node currNode = head;

        // This loop visits every node once.
        while (true) {
            output = output + currNode.data + " -> ";
            currNode = currNode.next;

            // Stop when we reach head again.
            if (currNode == head) {
                break;
            }
        }

        // Add HEAD again to show circular connection.
        output = output + "HEAD";
        selectedPosition = -1;
        messageLabel.setText("Traversal completed and came back to head.");
        logArea.append("Traversal: " + output + "\n");
        drawPanel.repaint();
    }

    // This method clears the whole circular list.
    private void clearList() {
        head = null;
        selectedPosition = -1;
        messageLabel.setText("Head is null. The circular linked list is empty.");
        logArea.append("Circular linked list cleared\n");
        drawPanel.repaint();
    }

    // This method reads value from the text field.
    private String getValue() {
        String value = valueField.getText().trim();

        // Empty value is not allowed.
        if (value.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a value first.");
            return null;
        }

        return value;
    }

    // This inner class draws circular linked list nodes.
    class DrawCircularListPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Starting position for the first node.
            int x = 70;
            int y = 82;
            int firstX = x;
            int nodeWidth = 95;
            int nodeHeight = 50;
            int arrowGap = 35;

            // Draw heading inside the panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Circular Linked List", 35, 35);

            // If list is empty, show HEAD as null.
            if (head == null) {
                g.drawString("HEAD", 70, 105);
                g.drawLine(120, 100, 190, 100);
                g.drawString("NULL", 205, 105);
                return;
            }

            // Draw HEAD pointing to first node.
            g.drawString("HEAD", 25, y + 30);
            g.drawLine(68, y + 25, x - 10, y + 25);

            // Start drawing from head.
            Node currNode = head;
            int position = 0;
            int lastX = x;

            // Draw all nodes until we reach head again.
            while (true) {
                if (position == selectedPosition) {
                    g.setColor(new Color(255, 230, 150));
                } else {
                    g.setColor(new Color(220, 235, 240));
                }

                // Draw one node box.
                g.fillRect(x, y, nodeWidth, nodeHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, nodeWidth, nodeHeight);

                // Draw a line inside node for data and next.
                g.drawLine(x + 65, y, x + 65, y + nodeHeight);

                // Show node value.
                g.drawString(currNode.data, x + 18, y + 30);

                // Show next part of the node.
                g.drawString("next", x + 68, y + 30);

                // Show node number below it.
                g.drawString("Node " + position, x + 25, y + 75);

                // Save x position of current node as last drawn node.
                lastX = x;

                // Move to next node.
                currNode = currNode.next;
                position++;

                // If next node is head, stop after this node.
                if (currNode == head) {
                    break;
                }

                // Draw arrow from current node to next node.
                int startX = x + nodeWidth;
                int endX = x + nodeWidth + arrowGap;
                int midY = y + 25;
                g.drawLine(startX, midY, endX, midY);
                g.drawLine(endX, midY, endX - 8, midY - 6);
                g.drawLine(endX, midY, endX - 8, midY + 6);

                // Move x position for next node.
                x = x + nodeWidth + arrowGap;
            }

            // Draw circular arrow from last node back to first node.
            int bottomY = y + 125;
            g.drawLine(lastX + nodeWidth, y + 25, lastX + nodeWidth + 20, y + 25);
            g.drawLine(lastX + nodeWidth + 20, y + 25, lastX + nodeWidth + 20, bottomY);
            g.drawLine(lastX + nodeWidth + 20, bottomY, firstX + 40, bottomY);
            g.drawLine(firstX + 40, bottomY, firstX + 40, y + nodeHeight);
            g.drawLine(firstX + 40, y + nodeHeight, firstX + 34, y + nodeHeight + 8);
            g.drawLine(firstX + 40, y + nodeHeight, firstX + 46, y + nodeHeight + 8);
            g.drawString("last.next points back to HEAD", firstX + 190, bottomY + 20);
        }
    }
}
