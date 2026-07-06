import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueueArrayVisualizer extends JFrame implements ActionListener {

    // This keeps the queue menu so Back can open it again.
    QueueMenu queueMenu;

    // These labels and field are used for input and messages.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform queue operations.
    JButton enqueueButton, dequeueButton, frontButton, printButton, clearButton, backButton;

    // This area shows operation history.
    JTextArea logArea;

    // This panel draws the queue array.
    DrawQueuePanel drawPanel;

    // This object stores the actual queue.
    MyQueue que = new MyQueue();

    // This index is highlighted after enqueue, dequeue, or front.
    int selectedIndex = -1;

    QueueArrayVisualizer(QueueMenu queueMenu) {
        this.queueMenu = queueMenu;

        // Null layout keeps every position easy to see in code.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Queue Using Array");
        title.setBounds(330, 20, 320, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for user input.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(40, 90, 70, 25);
        add(valueLabel);

        // Text field where user enters integer value.
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

        // Front button shows the first value.
        frontButton = new JButton("Front");
        frontButton.setBounds(525, 80, 105, 35);
        frontButton.addActionListener(this);
        add(frontButton);

        // Print button shows the queue from front to rear.
        printButton = new JButton("Print");
        printButton.setBounds(645, 80, 105, 35);
        printButton.addActionListener(this);
        add(printButton);

        // Clear button makes the queue empty.
        clearButton = new JButton("Clear");
        clearButton.setBounds(765, 80, 105, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to queue menu.
        backButton = new JButton("Back");
        backButton.setBounds(765, 130, 105, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label shows the current queue state.
        messageLabel = new JLabel("Queue is empty. Head is 0 and tail is -1.");
        messageLabel.setBounds(40, 160, 840, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows the array queue.
        drawPanel = new DrawQueuePanel();
        drawPanel.setBounds(40, 205, 830, 210);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area shows operation messages.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(40, 440, 830, 110);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(930, 630);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enqueueButton) {
            Integer value = getValue();

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
    private void enqueueValue(int value) {
        if (que.isFull()) {
            JOptionPane.showMessageDialog(null, "Queue is full.");
            logArea.append("Queue is full\n");
            return;
        }

        que.enqueue(value);
        selectedIndex = que.tail;
        messageLabel.setText("Enqueued " + value + ". Tail is now " + que.tail + ".");
        logArea.append("Enqueued: " + value + "\n");
        drawPanel.repaint();
    }

    // This method removes value from queue front.
    private void dequeueValue() {
        if (que.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Queue is empty.");
            logArea.append("Queue is empty\n");
            return;
        }

        int value = que.dequeue();
        selectedIndex = -1;
        messageLabel.setText("Dequeued " + value + ". After shifting, front is again at index 0.");
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

        int value = que.front();
        selectedIndex = que.head;
        messageLabel.setText("Front value is " + value + " at index " + que.head + ".");
        logArea.append("Front value is: " + value + "\n");
        drawPanel.repaint();
    }

    // This method prints all queue values.
    private void printQueue() {
        if (que.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Queue is empty.");
            logArea.append("Queue is empty\n");
            return;
        }

        // Build output from head to tail.
        String output = "";
        for (int i = que.head; i <= que.tail; i++) {
            output = output + que.arr[i];

            if (i != que.tail) {
                output = output + " -> ";
            }
        }

        selectedIndex = -1;
        messageLabel.setText("Queue printed from front to rear. Size is " + que.size() + ".");
        logArea.append("Queue from front to rear: " + output + "\n");
        drawPanel.repaint();
    }

    // This method clears the full queue.
    private void clearQueue() {
        que = new MyQueue();
        selectedIndex = -1;
        messageLabel.setText("Queue is empty. Head is 0 and tail is -1.");
        logArea.append("Queue cleared\n");
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

    // This class contains the actual array queue code.
    static class MyQueue {

        // nSize is the fixed size of the queue.
        public static int nSize = 5;

        // arr stores the queue values.
        int[] arr = new int[nSize];

        // head shows the front index.
        int head = 0;

        // tail shows the rear index.
        int tail = -1;

        // This method checks if queue is empty.
        public boolean isEmpty() {
            if (tail < 0) {
                return true;
            } else {
                return false;
            }
        }

        // This method checks if queue is full.
        public boolean isFull() {
            if (tail == nSize - 1) {
                return true;
            } else {
                return false;
            }
        }

        // This method adds value at the rear side.
        public void enqueue(int value) {
            if (isFull()) {
                System.out.println("Queue is full");
            } else {
                arr[++tail] = value;
            }
        }

        // This method removes value from the front side.
        public int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -9999;
            } else {
                int value = arr[head];
                moveArray();
                return value;
            }
        }

        // This method returns the front value.
        public int front() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -9999;
            } else {
                return arr[head];
            }
        }

        // This method returns the current queue size.
        public int size() {
            return tail + 1;
        }

        // This method shifts values to the left after dequeue.
        private void moveArray() {
            for (int i = 0; i < tail; i++) {
                arr[i] = arr[i + 1];
            }

            tail--;
        }
    }

    // This inner class draws the queue array.
    class DrawQueuePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Box size and starting position.
            int x = 75;
            int y = 80;
            int boxWidth = 110;
            int boxHeight = 55;

            // Draw heading inside panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Array Queue", 350, 30);

            // Draw all queue array boxes.
            for (int i = 0; i < MyQueue.nSize; i++) {
                if (i == selectedIndex) {
                    g.setColor(new Color(255, 230, 150));
                } else {
                    g.setColor(new Color(220, 235, 240));
                }

                // Draw one array box.
                g.fillRect(x, y, boxWidth, boxHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, boxWidth, boxHeight);

                // Show value only if this index is inside queue.
                if (i <= que.tail) {
                    g.drawString(String.valueOf(que.arr[i]), x + 48, y + 32);
                } else {
                    g.drawString("-", x + 52, y + 32);
                }

                // Show index below the box.
                g.drawString("[" + i + "]", x + 42, y + 78);

                // Show FRONT under the first value.
                if (!que.isEmpty() && i == que.head) {
                    g.drawString("FRONT", x + 28, y - 15);
                }

                // Show REAR above the last value.
                if (!que.isEmpty() && i == que.tail) {
                    g.drawString("REAR", x + 35, y + 105);
                }

                // Move right for the next array box.
                x = x + boxWidth + 25;
            }
        }
    }
}
