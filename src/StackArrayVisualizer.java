import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackArrayVisualizer extends JFrame implements ActionListener {

    // This keeps the stack menu so Back can open it again.
    StackMenu stackMenu;

    // These labels and field are used for input and messages.
    JLabel title, valueLabel, messageLabel;
    JTextField valueField;

    // These buttons perform stack operations.
    JButton pushButton, popButton, peekButton, printButton, clearButton, backButton;

    // This area shows what operation happened.
    JTextArea logArea;

    // This panel draws the stack boxes.
    DrawStackPanel drawPanel;

    // This is the fixed size of our stack.
    private int nsize = 5;

    // This array stores the stack values.
    int[] arr = new int[nsize];

    // Top starts from -1 because stack is empty at start.
    int top = -1;

    // This index is highlighted after push, pop, or peek.
    int selectedIndex = -1;

    StackArrayVisualizer(StackMenu stackMenu) {
        this.stackMenu = stackMenu;

        // Null layout keeps every position easy to see in code.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Stack Using Array");
        title.setBounds(330, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for user input.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(40, 90, 70, 25);
        add(valueLabel);

        // Text field where user enters value.
        valueField = new JTextField();
        valueField.setBounds(105, 90, 150, 25);
        add(valueField);

        // Push button inserts value on top.
        pushButton = new JButton("Push");
        pushButton.setBounds(285, 80, 95, 35);
        pushButton.addActionListener(this);
        add(pushButton);

        // Pop button removes value from top.
        popButton = new JButton("Pop");
        popButton.setBounds(395, 80, 95, 35);
        popButton.addActionListener(this);
        add(popButton);

        // Peek button only shows the top value.
        peekButton = new JButton("Peek");
        peekButton.setBounds(505, 80, 95, 35);
        peekButton.addActionListener(this);
        add(peekButton);

        // Print button shows all stack values from top to bottom.
        printButton = new JButton("Print");
        printButton.setBounds(615, 80, 95, 35);
        printButton.addActionListener(this);
        add(printButton);

        // Clear button makes the stack empty.
        clearButton = new JButton("Clear");
        clearButton.setBounds(725, 80, 95, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to stack menu.
        backButton = new JButton("Back");
        backButton.setBounds(835, 80, 85, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This message label explains current stack state.
        messageLabel = new JLabel("Stack is empty. Top is -1.");
        messageLabel.setBounds(40, 150, 880, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Draw panel shows the stack array visually.
        drawPanel = new DrawStackPanel();
        drawPanel.setBounds(40, 190, 360, 335);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area shows operation history.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(430, 190, 490, 335);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(970, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pushButton) {
            Integer value = getValue();

            // Push needs a value from the text field.
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
        if (top <= -1) {
            return true;
        } else {
            return false;
        }
    }

    // This method checks if stack is full.
    public boolean isFull() {
        if (top >= nsize - 1) {
            return true;
        } else {
            return false;
        }
    }

    // This method adds value to the top of stack.
    public void push(int value) {
        if (isFull()) {
            JOptionPane.showMessageDialog(null, "Stack is full. No more elements can be added.");
            logArea.append("Stack is full. No more elements can be added\n");
        } else {
            top++;
            arr[top] = value;
            selectedIndex = top;
            messageLabel.setText("Element added to stack: " + value + ". Top is " + top + ".");
            logArea.append("Element added to stack: " + value + "\n");
            drawPanel.repaint();
        }
    }

    // This method removes value from the top of stack.
    public int pop() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stack is empty.");
            logArea.append("Stack is empty\n");
            return 0;
        } else {
            int removedValue = arr[top];
            selectedIndex = top;
            top--;
            messageLabel.setText("Element removed is: " + removedValue + ". Top is now " + top + ".");
            logArea.append("Element removed is: " + removedValue + "\n");
            drawPanel.repaint();
            return removedValue;
        }
    }

    // This method shows the top value without removing it.
    public void peek() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stack underflow.");
            logArea.append("Stack underflow\n");
        } else {
            selectedIndex = top;
            messageLabel.setText("The peek value is: " + arr[top] + ".");
            logArea.append("The peek value is: " + arr[top] + "\n");
            drawPanel.repaint();
        }
    }

    // This method returns how many values are in the stack.
    public int stackSize() {
        return top + 1;
    }

    // This method prints stack from top to bottom.
    public void printstack() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stack is empty.");
            logArea.append("Stack is empty\n");
            return;
        }

        
        String output = "";
        for (int i = stackSize() - 1; i >= 0; i--) {
            output = output + arr[i];

            if (i != 0) {
                output = output + " -> ";
            }
        }

        selectedIndex = -1;
        messageLabel.setText("Stack printed from top to bottom. Size is " + stackSize() + ".");
        logArea.append("Stack from top to bottom: " + output + "\n");
        drawPanel.repaint();
    }

    // This method clears the full stack.
    private void clearStack() {
        top = -1;
        selectedIndex = -1;
        messageLabel.setText("Stack is empty. Top is -1.");
        logArea.append("Stack cleared\n");
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

    // This inner class draws the stack array.
    class DrawStackPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Box size and starting position.
            int x = 120;
            int y = 45;
            int boxWidth = 110;
            int boxHeight = 45;

            // Draw heading inside panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Array Stack", 125, 25);

            // Draw boxes from top index to bottom index.
            for (int i = nsize - 1; i >= 0; i--) {
                if (i == selectedIndex) {
                    g.setColor(new Color(255, 230, 150));
                } else {
                    g.setColor(new Color(220, 235, 240));
                }

                // Draw one stack box.
                g.fillRect(x, y, boxWidth, boxHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, boxWidth, boxHeight);

                // Show value only if this index is inside stack.
                if (i <= top) {
                    g.drawString(String.valueOf(arr[i]), x + 48, y + 28);
                } else {
                    g.drawString("-", x + 52, y + 28);
                }

                // Show array index beside the box.
                g.drawString("[" + i + "]", x - 45, y + 28);

                // Show TOP label beside the current top index.
                if (i == top) {
                    g.drawString("TOP", x + boxWidth + 25, y + 22);

                    // This pointer line is below the word TOP so it does not cut the text.
                    int arrowY = y + 33;
                    g.drawLine(x + boxWidth + 55, arrowY, x + boxWidth + 5, arrowY);
                    g.drawLine(x + boxWidth + 5, arrowY, x + boxWidth + 13, arrowY - 6);
                    g.drawLine(x + boxWidth + 5, arrowY, x + boxWidth + 13, arrowY + 6);
                }

                // Move down for the next lower index.
                y = y + boxHeight;
            }
        }
    }
}
