import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoWayCircularLinkedListVisualizer extends JFrame implements ActionListener {

    // This keeps the linked list menu so the Back button can open it again.
    LinkedListMenu linkedListMenu;

    // These labels and field are used for input and messages.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform two-way circular linked list operations.
    JButton insertStartButton, insertEndButton, deleteFirstButton, deleteLastButton;
    JButton searchButton, forwardButton, backwardButton, clearButton, backButton;

    // This area shows operation history.
    JTextArea logArea;

    // This panel draws the nodes and their links.
    DrawTwoWayPanel drawPanel;

    // Head points to the first node of the list.
    Node head;

    // This position is highlighted after insert or search.
    int selectedPosition = -1;

    // This class represents one node of two-way circular linked list.
    static class Node {

        // Prev points to the previous node.
        Node prev;

        // Data stores the value of the node.
        String data;

        // Next points to the next node.
        Node next;

        // Constructor stores value in the new node.
        Node(String value) {
            data = value;
            prev = null;
            next = null;
        }
    }

    TwoWayCircularLinkedListVisualizer(LinkedListMenu linkedListMenu) {
        this.linkedListMenu = linkedListMenu;

        // Null layout is used to keep positions simple.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Two-Way Circular Linked List Visualizer");
        title.setBounds(190, 20, 620, 40);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for value input.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(35, 90, 70, 25);
        add(valueLabel);

        // Text field where user enters node value.
        valueField = new JTextField();
        valueField.setBounds(100, 90, 145, 25);
        add(valueField);

        // This button inserts a node at the start.
        insertStartButton = new JButton("Insert Start");
        insertStartButton.setBounds(275, 80, 125, 35);
        insertStartButton.addActionListener(this);
        add(insertStartButton);

        // This button inserts a node at the end.
        insertEndButton = new JButton("Insert End");
        insertEndButton.setBounds(415, 80, 125, 35);
        insertEndButton.addActionListener(this);
        add(insertEndButton);

        // This button deletes the first node.
        deleteFirstButton = new JButton("Delete First");
        deleteFirstButton.setBounds(555, 80, 125, 35);
        deleteFirstButton.addActionListener(this);
        add(deleteFirstButton);

        // This button deletes the last node.
        deleteLastButton = new JButton("Delete Last");
        deleteLastButton.setBounds(695, 80, 125, 35);
        deleteLastButton.addActionListener(this);
        add(deleteLastButton);

        // This button searches a value.
        searchButton = new JButton("Search");
        searchButton.setBounds(275, 130, 125, 35);
        searchButton.addActionListener(this);
        add(searchButton);

        // This button traverses the list from head to next nodes.
        forwardButton = new JButton("Forward");
        forwardButton.setBounds(415, 130, 125, 35);
        forwardButton.addActionListener(this);
        add(forwardButton);

        // This button traverses the list from last to previous nodes.
        backwardButton = new JButton("Backward");
        backwardButton.setBounds(555, 130, 125, 35);
        backwardButton.addActionListener(this);
        add(backwardButton);

        // This button removes all nodes.
        clearButton = new JButton("Clear");
        clearButton.setBounds(695, 130, 125, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // This button goes back to the linked list menu.
        backButton = new JButton("Back");
        backButton.setBounds(835, 105, 85, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label shows the current condition of the list.
        messageLabel = new JLabel("Head is null. The two-way circular linked list is empty.");
        messageLabel.setBounds(35, 190, 860, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows prev, data, next, and circular links.
        drawPanel = new DrawTwoWayPanel();
        drawPanel.setBounds(35, 230, 885, 240);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area shows messages after button clicks.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(35, 490, 885, 110);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(975, 680);
        MainMenu.makeResponsive(this, 975, 680);
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
        } else if (e.getSource() == forwardButton) {
            traverseForward();
        } else if (e.getSource() == backwardButton) {
            traverseBackward();
        } else if (e.getSource() == clearButton) {
            clearList();
        } else if (e.getSource() == backButton) {
            linkedListMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method inserts a new node at the start.
    private void insertAtStart() {
        String value = getValue();

        // If input is empty, stop this operation.
        if (value == null) {
            return;
        }

        // Create a new node.
        Node newNode = new Node(value);

        // If list is empty, the node points to itself from both sides.
        if (head == null) {
            head = newNode;
            newNode.next = head;
            newNode.prev = head;
            selectedPosition = 0;
            messageLabel.setText("Inserted " + value + ". Its next and prev both point to itself.");
            logArea.append("Inserted " + value + " as first node\n");
            drawPanel.repaint();
            return;
        }

        // Last node is the node before head.
        Node lastNode = head.prev;

        // New node comes before old head.
        newNode.next = head;
        newNode.prev = lastNode;

        // Old last node points forward to new node.
        lastNode.next = newNode;

        // Old head points backward to new node.
        head.prev = newNode;

        // Head moves to the new node.
        head = newNode;
        selectedPosition = 0;
        messageLabel.setText("Inserted " + value + " at start. Head moved to the new node.");
        logArea.append("Inserted " + value + " at start\n");
        drawPanel.repaint();
    }

    // This method inserts a new node at the end.
    private void insertAtEnd() {
        String value = getValue();

        // If input is empty, stop this operation.
        if (value == null) {
            return;
        }

        // Create a new node.
        Node newNode = new Node(value);

        // If list is empty, the new node becomes head.
        if (head == null) {
            head = newNode;
            newNode.next = head;
            newNode.prev = head;
            selectedPosition = 0;
            messageLabel.setText("Inserted " + value + ". This is the first node.");
            logArea.append("Inserted " + value + " as first node\n");
            drawPanel.repaint();
            return;
        }

        // Last node is stored in head.prev.
        Node lastNode = head.prev;

        // New node will come after last node and before head.
        newNode.next = head;
        newNode.prev = lastNode;

        // Last node points forward to new node.
        lastNode.next = newNode;

        // Head points backward to new node.
        head.prev = newNode;
        selectedPosition = countNodes() - 1;
        messageLabel.setText("Inserted " + value + " at end. New node is before head in the circle.");
        logArea.append("Inserted " + value + " at end\n");
        drawPanel.repaint();
    }

    // This method deletes the first node.
    private void deleteFirst() {

        // Empty list has no node to delete.
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
            logArea.append("Deleted first node: " + deletedValue + "\n");
            drawPanel.repaint();
            return;
        }

        // Save old head and last node.
        String deletedValue = head.data;
        Node lastNode = head.prev;

        // Head moves to the next node.
        head = head.next;

        // New head points backward to last node.
        head.prev = lastNode;

        // Last node points forward to new head.
        lastNode.next = head;
        selectedPosition = -1;
        messageLabel.setText("Deleted first node " + deletedValue + ". Head moved forward.");
        logArea.append("Deleted first node: " + deletedValue + "\n");
        drawPanel.repaint();
    }

    // This method deletes the last node.
    private void deleteLast() {

        // Empty list has no node to delete.
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

        // Last node is before head.
        Node lastNode = head.prev;

        // Second last node is before last node.
        Node secondLast = lastNode.prev;

        // Save deleted value for message.
        String deletedValue = lastNode.data;

        // Second last now points forward to head.
        secondLast.next = head;

        // Head now points backward to second last.
        head.prev = secondLast;
        selectedPosition = -1;
        messageLabel.setText("Deleted last node " + deletedValue + ". New last node connects to head.");
        logArea.append("Deleted last node: " + deletedValue + "\n");
        drawPanel.repaint();
    }

    // This method searches a value in the list.
    private void searchValue() {
        String value = getValue();

        // If input is empty, stop searching.
        if (value == null) {
            return;
        }

        // Empty list has nothing to search.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // Start checking from head.
        Node currNode = head;
        int position = 0;

        // This loop stops when it comes back to head.
        while (true) {
            if (currNode.data.equals(value)) {
                selectedPosition = position;
                messageLabel.setText("Value " + value + " found at position " + position + ".");
                logArea.append("Searched " + value + " and found at position " + position + "\n");
                drawPanel.repaint();
                return;
            }

            currNode = currNode.next;
            position++;

            // If current node is head again, search is finished.
            if (currNode == head) {
                break;
            }
        }

        // This runs when value is not found.
        selectedPosition = -1;
        messageLabel.setText("Value " + value + " not found.");
        logArea.append("Searched " + value + " but not found\n");
        drawPanel.repaint();
    }

    // This method traverses from head using next links.
    private void traverseForward() {

        // Empty list has nothing to traverse.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // Build output in forward direction.
        String output = "";
        Node currNode = head;

        // Visit each node once using next.
        while (true) {
            output = output + currNode.data + " -> ";
            currNode = currNode.next;

            // Stop when we reach head again.
            if (currNode == head) {
                break;
            }
        }

        // Add HEAD to show circular ending.
        output = output + "HEAD";
        selectedPosition = -1;
        messageLabel.setText("Forward traversal completed using next links.");
        logArea.append("Forward: " + output + "\n");
        drawPanel.repaint();
    }

    // This method traverses from last node using prev links.
    private void traverseBackward() {

        // Empty list has nothing to traverse.
        if (head == null) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }

        // Last node is head.prev.
        Node lastNode = head.prev;
        Node currNode = lastNode;
        String output = "";

        // Visit each node once using prev.
        while (true) {
            output = output + currNode.data + " -> ";
            currNode = currNode.prev;

            // Stop when we reach last node again.
            if (currNode == lastNode) {
                break;
            }
        }

        // Add LAST to show circular ending in reverse.
        output = output + "LAST";
        selectedPosition = -1;
        messageLabel.setText("Backward traversal completed using prev links.");
        logArea.append("Backward: " + output + "\n");
        drawPanel.repaint();
    }

    // This method clears the full list.
    private void clearList() {
        head = null;
        selectedPosition = -1;
        messageLabel.setText("Head is null. The two-way circular linked list is empty.");
        logArea.append("Two-way circular linked list cleared\n");
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

    // This method counts nodes in the circular list.
    private int countNodes() {

        // Empty list has zero nodes.
        if (head == null) {
            return 0;
        }

        // Count starts from head.
        int count = 0;
        Node currNode = head;

        // Move until we come back to head.
        while (true) {
            count++;
            currNode = currNode.next;

            if (currNode == head) {
                break;
            }
        }

        return count;
    }

    // This inner class draws the two-way circular linked list.
    class DrawTwoWayPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Starting position for first node.
            int x = 80;
            int y = 86;
            int firstX = x;
            int nodeWidth = 125;
            int nodeHeight = 55;
            int arrowGap = 35;

            // Draw heading inside the visual panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Two-Way Circular Linked List", 35, 35);

            // If list is empty, show HEAD as null.
            if (head == null) {
                g.drawString("HEAD", 75, 110);
                g.drawLine(125, 105, 195, 105);
                g.drawString("NULL", 210, 110);
                return;
            }

            // Draw HEAD pointing to first node.
            g.drawString("HEAD", 25, y + 32);
            g.drawLine(68, y + 27, x - 10, y + 27);

            // Start drawing from head.
            Node currNode = head;
            int position = 0;
            int lastX = x;

            // Draw all nodes one by one.
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

                // Draw lines to show prev, data, and next parts.
                g.drawLine(x + 38, y, x + 38, y + nodeHeight);
                g.drawLine(x + 88, y, x + 88, y + nodeHeight);

                // Write values inside the three parts.
                g.drawString("prev", x + 5, y + 32);
                g.drawString(currNode.data, x + 48, y + 32);
                g.drawString("next", x + 92, y + 32);

                // Show node number below the node.
                g.drawString("Node " + position, x + 38, y + 78);

                // Save current x as last node position.
                lastX = x;

                // Move to next node.
                currNode = currNode.next;
                position++;

                // If next node is head, stop after drawing current node.
                if (currNode == head) {
                    break;
                }

                // Draw forward arrow from current node to next node.
                int startX = x + nodeWidth;
                int endX = x + nodeWidth + arrowGap;
                g.drawLine(startX, y + 18, endX, y + 18);
                g.drawLine(endX, y + 18, endX - 8, y + 12);
                g.drawLine(endX, y + 18, endX - 8, y + 24);

                // Draw backward arrow from next node to current node.
                g.drawLine(endX, y + 40, startX, y + 40);
                g.drawLine(startX, y + 40, startX + 8, y + 34);
                g.drawLine(startX, y + 40, startX + 8, y + 46);

                // Move x for the next node.
                x = x + nodeWidth + arrowGap;
            }

            // Draw circular forward link from last node back to head.
            int bottomY = y + 135;
            g.drawLine(lastX + nodeWidth, y + 18, lastX + nodeWidth + 20, y + 18);
            g.drawLine(lastX + nodeWidth + 20, y + 18, lastX + nodeWidth + 20, bottomY);
            g.drawLine(lastX + nodeWidth + 20, bottomY, firstX + 62, bottomY);
            g.drawLine(firstX + 62, bottomY, firstX + 62, y + nodeHeight);
            g.drawLine(firstX + 62, y + nodeHeight, firstX + 56, y + nodeHeight + 8);
            g.drawLine(firstX + 62, y + nodeHeight, firstX + 68, y + nodeHeight + 8);

            // Draw circular backward link from head back to last node.
            int topY = y - 35;
            g.drawLine(firstX, y + 40, firstX - 20, y + 40);
            g.drawLine(firstX - 20, y + 40, firstX - 20, topY);
            g.drawLine(firstX - 20, topY, lastX + 60, topY);
            g.drawLine(lastX + 60, topY, lastX + 60, y);
            g.drawLine(lastX + 60, y, lastX + 54, y - 8);
            g.drawLine(lastX + 60, y, lastX + 66, y - 8);

            // These labels explain both circular links.
            g.drawString("last.next points to HEAD", firstX + 190, bottomY + 20);
            g.drawString("head.prev points to last node", firstX + 190, topY - 8);
        }
    }
}
