import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackLinkedListVisualizer extends JFrame implements ActionListener {

    // This keeps the stack menu so Back can open it again.
    StackMenu stackMenu;

    // These labels and field are used for input and messages.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform stack operations.
    JButton pushButton, popButton, peekButton, printButton, clearButton, backButton;

    // This area shows operation history.
    JTextArea logArea;

    // This panel draws the linked stack.
    DrawLinkedStackPanel drawPanel;

    // Top points to the first node of the stack.
    Node top;

    // This position is highlighted after push or peek.
    int selectedPosition = -1;

    // This class represents one stack node.
    static class Node {

        // Data stores the value inside the node.
        String data;

        // Next points to the node below it.
        Node next;

        // Constructor stores value in the node.
        Node(String value) {
            data = value;
            next = null;
        }
    }

    StackLinkedListVisualizer(StackMenu stackMenu) {
        this.stackMenu = stackMenu;

        // Null layout keeps the code simple.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Stack Using Linked List");
        title.setBounds(300, 20, 380, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for the input field.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(40, 90, 70, 25);
        add(valueLabel);

        // Text field where user enters value.
        valueField = new JTextField();
        valueField.setBounds(105, 90, 150, 25);
        add(valueField);

        // Push button adds a node on top.
        pushButton = new JButton("Push");
        pushButton.setBounds(290, 80, 105, 35);
        pushButton.addActionListener(this);
        add(pushButton);

        // Pop button removes the top node.
        popButton = new JButton("Pop");
        popButton.setBounds(410, 80, 105, 35);
        popButton.addActionListener(this);
        add(popButton);

        // Peek button shows the top value.
        peekButton = new JButton("Peek");
        peekButton.setBounds(530, 80, 105, 35);
        peekButton.addActionListener(this);
        add(peekButton);

        // Print button shows stack values from top to bottom.
        printButton = new JButton("Print");
        printButton.setBounds(650, 80, 115, 35);
        printButton.addActionListener(this);
        add(printButton);

        // Clear button removes all nodes.
        clearButton = new JButton("Clear");
        clearButton.setBounds(780, 80, 105, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to stack menu.
        backButton = new JButton("Back");
        backButton.setBounds(780, 130, 105, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label shows current stack state.
        messageLabel = new JLabel("Stack is empty. Top is null.");
        messageLabel.setBounds(40, 160, 850, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows top node and next links.
        drawPanel = new DrawLinkedStackPanel();
        drawPanel.setBounds(40, 200, 420, 370);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area shows messages after operations.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(490, 200, 395, 370);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(940, 650);
        MainMenu.makeResponsive(this, 940, 650);
        MainMenu.designTopicButtons(this, MainMenu.STACK_QUEUE_COLOR);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pushButton) {
            String value = getValue();

            // Push needs value from the text field.
            if (value != null) {
                push(value);
            }
        } else if (e.getSource() == popButton) {
            pop();
        } else if (e.getSource() == peekButton) {
            peek();
        } else if (e.getSource() == printButton) {
            printstack();
        } else if (e.getSource() == clearButton) {
            clearStack();
        } else if (e.getSource() == backButton) {
            stackMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method checks if stack is empty.
    public boolean isEmpty() {
        if (top == null) {
            return true;
        } else {
            return false;
        }
    }

    // This method pushes a new node on top.
    public void push(String value) {
        // Create new node for the value.
        Node newNode = new Node(value);

        // New node points to old top.
        newNode.next = top;

        // Top now points to new node.
        top = newNode;
        selectedPosition = 0;
        messageLabel.setText("Pushed " + value + ". Top points to the new node.");
        logArea.append("Push: " + value + " added on top\n");
        drawPanel.repaint();
    }

    // This method removes the top node.
    public String pop() {

        // If top is null, stack is empty.
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stack is empty. This is underflow.");
            logArea.append("Stack is empty\n");
            return null;
        }

        // Save top value before removing it.
        String removedValue = top.data;

        // Top moves to the next node.
        top = top.next;
        selectedPosition = -1;
        messageLabel.setText("Popped " + removedValue + ". Top moved to the next node.");
        logArea.append("Pop: " + removedValue + " removed\n");
        drawPanel.repaint();
        return removedValue;
    }

    // This method shows the top value.
    public void peek() {

        // Empty stack has no top value.
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stack is empty.");
            return;
        }

        // Highlight top node and show its value.
        selectedPosition = 0;
        messageLabel.setText("Peek value is " + top.data + ".");
        logArea.append("Peek: top value is " + top.data + "\n");
        drawPanel.repaint();
    }

    // This method shows all stack values from top to bottom.
    public void printstack() {

        // Empty stack has nothing to traverse.
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stack is empty.");
            return;
        }

        // Build output using top to bottom order.
        String output = "";
        Node currNode = top;

        // Visit each node until null.
        while (currNode != null) {
            output = output + currNode.data + " -> ";
            currNode = currNode.next;
        }

        // Null shows the bottom end of linked stack.
        output = output + "Null";
        selectedPosition = -1;
        messageLabel.setText("Stack printed from top to bottom.");
        logArea.append("Stack from top to bottom: " + output + "\n");
        drawPanel.repaint();
    }

    // This method clears the full stack.
    private void clearStack() {
        top = null;
        selectedPosition = -1;
        messageLabel.setText("Stack is empty. Top is null.");
        logArea.append("Stack cleared\n");
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

    // This inner class draws the stack linked list.
    class DrawLinkedStackPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Starting position and node size.
            int x = 140;
            int y = 55;
            int nodeWidth = 120;
            int nodeHeight = 45;
            int gap = 35;

            // Draw heading inside panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Linked Stack", 145, 30);

            // If stack is empty, show TOP as null.
            if (top == null) {
                g.drawString("TOP", 85, 95);
                g.drawLine(120, 90, 190, 90);
                g.drawString("NULL", 205, 95);
                return;
            }

            // Draw TOP label pointing to first node.
            g.drawString("TOP", 75, y + 28);
            g.drawLine(112, y + 23, x - 10, y + 23);

            // Start drawing from top node.
            Node currNode = top;
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

                // Draw line to show data and next parts.
                g.drawLine(x + 75, y, x + 75, y + nodeHeight);

                // Show data inside node.
                g.drawString(currNode.data, x + 25, y + 28);

                // Show next word in pointer part.
                g.drawString("next", x + 80, y + 28);

                // Draw arrow to the node below.
                if (currNode.next != null) {
                    int midX = x + 95;
                    g.drawLine(midX, y + nodeHeight, midX, y + nodeHeight + gap);
                    g.drawLine(midX, y + nodeHeight + gap, midX - 6, y + nodeHeight + gap - 8);
                    g.drawLine(midX, y + nodeHeight + gap, midX + 6, y + nodeHeight + gap - 8);
                } else {
                    g.drawLine(x + 95, y + nodeHeight, x + 95, y + nodeHeight + 25);
                    g.drawString("NULL", x + 78, y + nodeHeight + 45);
                }

                // Move down for next node.
                y = y + nodeHeight + gap;
                currNode = currNode.next;
                position++;
            }
        }
    }
}
