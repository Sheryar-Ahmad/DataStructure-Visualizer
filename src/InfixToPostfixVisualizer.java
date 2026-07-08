import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class InfixToPostfixVisualizer extends JFrame implements ActionListener {

    // This keeps the conversion menu so Back can open it again.
    ConversionMenu conversionMenu;

    // These labels and field are used for input, output, and messages.
    JLabel title, expressionLabel, resultLabel, messageLabel;
    JTextField expressionField;

    // These buttons perform screen operations.
    JButton convertButton, clearButton, backButton;

    // This area shows each conversion step.
    JTextArea logArea;

    // This panel draws the current stack.
    DrawStackPanel drawPanel;

    // This stack stores operators during conversion.
    Stack<Character> stk = new Stack<Character>();

    // This string stores the final postfix expression.
    String pexpression = "";

    // This tells the drawing panel that conversion has finished.
    boolean conversionDone = false;

    InfixToPostfixVisualizer(ConversionMenu conversionMenu) {
        this.conversionMenu = conversionMenu;

        // Null layout keeps positions simple.
        setLayout(null);

        // Main title of this screen.
        title = new JLabel("Infix to Postfix Conversion");
        title.setBounds(280, 20, 430, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label for expression input.
        expressionLabel = new JLabel("Infix:");
        expressionLabel.setBounds(40, 90, 70, 25);
        add(expressionLabel);

        // Text field where user enters expression.
        expressionField = new JTextField();
        expressionField.setBounds(100, 90, 250, 25);
        add(expressionField);

        // Convert button starts conversion.
        convertButton = new JButton("Convert");
        convertButton.setBounds(380, 82, 120, 35);
        convertButton.addActionListener(this);
        add(convertButton);

        // Clear button clears input and output.
        clearButton = new JButton("Clear");
        clearButton.setBounds(515, 82, 120, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to conversion menu.
        backButton = new JButton("Back");
        backButton.setBounds(650, 82, 120, 35);
        backButton.addActionListener(this);
        add(backButton);

        // Result label shows final postfix expression.
        resultLabel = new JLabel("Postfix Expression:");
        resultLabel.setBounds(40, 140, 820, 25);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel);

        // Message label explains the main rule.
        messageLabel = new JLabel("Rule: operands go to output, operators use stack according to precedence.");
        messageLabel.setBounds(40, 175, 840, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Drawing panel shows operator stack.
        drawPanel = new DrawStackPanel();
        drawPanel.setBounds(40, 220, 260, 330);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area shows conversion steps.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(330, 220, 560, 330);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(950, 630);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            convertExpression();
        } else if (e.getSource() == clearButton) {
            clearScreen();
        } else if (e.getSource() == backButton) {
            conversionMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method tells which operator has more power.
    public static int precedence(char op) {
        if (op == '*' || op == '/') {
            return 2;
        } else if (op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    // This method converts infix expression to postfix.
    private void convertExpression() {
        String exp = expressionField.getText().trim();

        // Empty expression cannot be converted.
        if (exp.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an expression first.");
            return;
        }

        // Start with empty stack and empty output.
        stk.clear();
        pexpression = "";
        conversionDone = false;
        logArea.setText("");

        // Read every character one by one.
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            // Ignore spaces in expression.
            if (ch == ' ') {
                continue;
            }

            if (Character.isLetterOrDigit(ch)) {
                pexpression = pexpression + ch;
                logArea.append(ch + " is operand, add to output: " + pexpression + "\n");
            } else if (ch == '(') {
                stk.push(ch);
                logArea.append(ch + " pushed to stack\n");
            } else if (ch == ')') {
                while (!stk.isEmpty() && stk.peek() != '(') {
                    pexpression = pexpression + stk.pop();
                }

                // Remove opening bracket from stack.
                if (!stk.isEmpty()) {
                    stk.pop();
                }

                logArea.append("Closing bracket found, pop until opening bracket\n");
            } else {
                while (!stk.isEmpty() && stk.peek() != '(' && precedence(stk.peek()) >= precedence(ch)) {
                    pexpression = pexpression + stk.pop();
                }

                stk.push(ch);
                logArea.append(ch + " pushed to stack\n");
            }
        }

        // Pop remaining operators from stack.
        while (!stk.isEmpty()) {
            pexpression = pexpression + stk.pop();
        }

        // After final popping, the operator stack becomes empty.
        conversionDone = true;
        resultLabel.setText("Postfix Expression: " + pexpression);
        messageLabel.setText("Conversion completed. Stack is empty because all operators were popped.");
        logArea.append("Final postfix expression: " + pexpression + "\n");
        drawPanel.repaint();
    }

    // This method clears all screen values.
    private void clearScreen() {
        expressionField.setText("");
        stk.clear();
        pexpression = "";
        conversionDone = false;
        resultLabel.setText("Postfix Expression:");
        messageLabel.setText("Rule: operands go to output, operators use stack according to precedence.");
        logArea.setText("");
        drawPanel.repaint();
    }

    // This inner class draws the operator stack.
    class DrawStackPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Heading of stack panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("Operator Stack", 75, 30);

            // Starting position for stack boxes.
            int x = 85;
            int y = 265;
            int boxWidth = 80;
            int boxHeight = 35;

            // If stack is empty, show empty message.
            if (stk.isEmpty()) {
                if (conversionDone) {
                    g.drawString("Stack is empty", 75, 150);
                    g.drawString("after final pop", 72, 175);
                } else {
                    g.drawString("Stack is empty", 75, 160);
                }
                return;
            }

            // Draw stack from bottom to top.
            for (int i = 0; i < stk.size(); i++) {
                g.setColor(new Color(220, 235, 240));
                g.fillRect(x, y, boxWidth, boxHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, boxWidth, boxHeight);
                g.drawString(String.valueOf(stk.get(i)), x + 35, y + 23);

                // Move upward for next stack value.
                y = y - boxHeight;
            }

            // Show top label.
            g.drawString("TOP", x + boxWidth + 15, y + boxHeight + 23);
        }
    }
}
