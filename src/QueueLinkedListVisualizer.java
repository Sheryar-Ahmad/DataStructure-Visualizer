import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueueLinkedListVisualizer extends JFrame implements ActionListener {

    // This keeps the queue menu so Back can open it again.
    QueueMenu queueMenu;

    // These labels and field are used for input and messages.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform queue operations.
    JButton enqueueButton, dequeueButton, frontButton, printButton, clearButton, backButton;

    // This area shows operation history.
    JTextArea logArea;

    // This panel draws the linked queue.
    DrawLinkedQueuePanel drawPanel;

    // This object stores the actual linked queue.
    MyLinkedQueue que = new MyLinkedQueue();

    // This position is highlighted after enqueue or front.
    int selectedPosition = -1;

    QueueLinkedListVisualizer(QueueMenu queueMenu) {
        this.queueMenu = queueMenu;

        // Null layout keeps the code simple.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Queue Using Linked List");
        title.setBounds(290, 20, 390, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for input value.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(40, 90, 70, 25);
        add(valueLabel);

        // Text field where user writes value.
        valueField = new JTextField();
        valueField.setBounds(105, 90, 150, 25);
        add(valueField);

        // Enqueue button adds value at rear.
        enqueueButton = new JButton("Enqueue");
        enqueueButton.setBounds(285, 80, 105, 35);
        enqueueButton.addActionListener(this);
        add(enqueueButton);

        // Dequeue button removes value from front.
        dequeueButton = new JButton("Dequeue");
        dequeueButton.setBounds(405, 80, 105, 35);
        dequeueButton.addActionListener(this);
        add(dequeueButton);

        // Front button shows first value.
        frontButton = new JButton("Front");
        frontButton.setBounds(525, 80, 105, 35);
        frontButton.addActionListener(this);
        add(frontButton);

        // Print button shows queue values.
        printButton = new JButton("Print");
        printButton.setBounds(645, 80, 105, 35);
        printButton.addActionListener(this);
        add(printButton);

        // Clear button removes all nodes.
        clearButton = new JButton("Clear");
        clearButton.setBounds(765, 80, 105, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to queue menu.
        backButton = new JButton("Back");
        backButton.setBounds(765, 130, 105, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label shows current queue state.
        messageLabel = new JLabel("Queue is empty. Front and rear are null.");
        messageLabel.setBounds(40, 160, 840, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows the linked queue.
        drawPanel = new DrawLinkedQueuePanel();
        drawPanel.setBounds(40, 205, 830, 230);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area shows operation messages.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(40, 460, 830, 110);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(930, 650);
        MainMenu.makeResponsive(this, 930, 650);
        MainMenu.designTopicButtons(this, MainMenu.STACK_QUEUE_COLOR);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enqueueButton) {
            String value = getValue();

            // Enqueue needs a value from the text field.
            if (value != null) {
                enqueueValue(value);
            }
        } else if (e.getSource() == dequeueButton) {
            dequeueValue();
        } else if (e.getSource() == frontButton) {
            frontValue();
        } else if (e.getSource() == printButton) {
            printQueue();
        } else if (e.getSource() == clearButton) {
            clearQueue();
        } else if (e.getSource() == backButton) {
            queueMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method sends value to the queue enqueue method.
    private void enqueueValue(String value) {
        que.enqueue(value);
        selectedPosition = que.size() - 1;
        messageLabel.setText("Enqueued " + value + ". Rear points to the new node.");
        logArea.append("Enqueued: " + value + "\n");
        drawPanel.repaint();
    }

    // This method removes the front node.
    private void dequeueValue() {
        if (que.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Queue is empty.");
            logArea.append("Queue is empty\n");
            return;
        }

        String value = que.dequeue();
        selectedPosition = -1;
        messageLabel.setText("Dequeued " + value + ". Front moved to the next node.");
        logArea.append("Dequeued value: " + value + "\n");
        drawPanel.repaint();
    }

    // This method shows the front value.
    private void frontValue() {
        if (que.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Queue is empty.");
            logArea.append("Queue is empty\n");
            return;
        }

        String value = que.frontValue();
        selectedPosition = 0;
        messageLabel.setText("Front value is " + value + ".");
        logArea.append("Front value is: " + value + "\n");
        drawPanel.repaint();
    }

    // This method prints queue values from front to rear.
    private void printQueue() {
        if (que.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Queue is empty.");
            logArea.append("Queue is empty\n");
            return;
        }

        String output = que.printQueue();
        selectedPosition = -1;
        messageLabel.setText("Queue printed from front to rear. Size is " + que.size() + ".");
        logArea.append("Queue from front to rear: " + output + "\n");
        drawPanel.repaint();
    }

    // This method clears the full queue.
    private void clearQueue() {
        que = new MyLinkedQueue();
        selectedPosition = -1;
        messageLabel.setText("Queue is empty. Front and rear are null.");
        logArea.append("Queue cleared\n");
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

    // This class contains the actual linked list queue code.
    static class MyLinkedQueue {

        // Front points to the first node.
        Node front;

        // Rear points to the last node.
        Node rear;

        // This class represents one queue node.
        static class Node {

            // Data stores the value inside the node.
            String data;

            // Next points to the next node.
            Node next;

            // Constructor stores value in the node.
            Node(String value) {
                data = value;
                next = null;
            }
        }

        // This method checks if queue is empty.
        public boolean isEmpty() {
            if (front == null) {
                return true;
            } else {
                return false;
            }
        }

        // This method adds value at the rear.
        public void enqueue(String value) {
            Node newNode = new Node(value);

            // If queue is empty, front and rear both point to new node.
            if (isEmpty()) {
                front = newNode;
                rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
        }

        // This method removes value from the front.
        public String dequeue() {
            if (isEmpty()) {
                return null;
            }

            // Save front value before removing it.
            String value = front.data;

            // Front moves to next node.
            front = front.next;

            // If queue became empty, rear should also become null.
            if (front == null) {
                rear = null;
            }

            return value;
        }

        // This method returns the front value.
        public String frontValue() {
            if (isEmpty()) {
                return null;
            } else {
                return front.data;
            }
        }

        // This method counts queue nodes.
        public int size() {
            int count = 0;
            Node currNode = front;

            while (currNode != null) {
                count++;
                currNode = currNode.next;
            }

            return count;
        }

        // This method prints queue values from front to rear.
        public String printQueue() {
            String output = "";
            Node currNode = front;

            while (currNode != null) {
                output = output + currNode.data;

                if (currNode.next != null) {
                    output = output + " -> ";
                }

                currNode = currNode.next;
            }

            return output;
        }
    }

    // This inner class draws the linked queue.
    class DrawLinkedQueuePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Starting position and node size.
            int x = 70;
            int y = 88;
            int nodeWidth = 105;
            int nodeHeight = 50;
            int gap = 45;

            // Draw heading inside panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Linked Queue", 350, 30);

            // If queue is empty, show front and rear as null.
            if (que.isEmpty()) {
                g.drawString("FRONT", 80, 105);
                g.drawLine(130, 100, 200, 100);
                g.drawString("NULL", 215, 105);
                g.drawString("REAR", 80, 145);
                g.drawLine(125, 140, 200, 140);
                g.drawString("NULL", 215, 145);
                return;
            }

            // Draw FRONT pointing to first node.
            g.drawString("FRONT", 18, y + 30);
            g.drawLine(68, y + 25, x - 10, y + 25);

            // Start drawing from front node.
            MyLinkedQueue.Node currNode = que.front;
            int position = 0;

            // Draw every node until null.
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

                // Draw line inside node to show data and next.
                g.drawLine(x + 68, y, x + 68, y + nodeHeight);

                // Show data inside node.
                g.drawString(currNode.data, x + 22, y + 30);

                // Show next word in pointer part.
                g.drawString("next", x + 72, y + 30);

                // Show rear label under last node.
                if (currNode == que.rear) {
                    g.drawString("REAR", x + 32, y + 85);
                    g.drawLine(x + 52, y + 72, x + 52, y + 52);
                }

                // Draw arrow to next node or show null at end.
                if (currNode.next != null) {
                    int startX = x + nodeWidth;
                    int endX = x + nodeWidth + gap;
                    int midY = y + 25;

                    g.drawLine(startX, midY, endX, midY);
                    g.drawLine(endX, midY, endX - 8, midY - 6);
                    g.drawLine(endX, midY, endX - 8, midY + 6);
                } else {
                    g.drawLine(x + nodeWidth, y + 25, x + nodeWidth + 30, y + 25);
                    g.drawString("NULL", x + nodeWidth + 38, y + 30);
                }

                // Move right for the next node.
                x = x + nodeWidth + gap;
                currNode = currNode.next;
                position++;
            }
        }
    }
}
