import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ArrayVisualizer extends JFrame implements ActionListener {

    // This variable keeps the main menu so we can go back to it.
    MainMenu mainMenu;

    // These labels and fields are used to take input from the user.
    JLabel title, valueLabel, indexLabel, messageLabel;
    JTextField valueField, indexField;

    // These buttons perform array operations.
    JButton insertButton, updateButton, deleteButton, searchButton, clearButton, backButton;

    // This text area shows what operation was performed.
    JTextArea logArea;

    // This panel draws the array boxes on the screen.
    DrawArrayPanel drawPanel;

    // Size of array is fixed at 8 so that we can demonstarte it easily.
    int size = 8;

    // This array stores actual integer values.
    int[] arr = new int[size];

    // This array tells which index has a value.
    boolean[] used = new boolean[size];

    // This index is highlighted after insert, update, delete, or search.
    int selectedIndex = -1;

    ArrayVisualizer(MainMenu mainMenu) {
        this.mainMenu = mainMenu;

        // Main title of this screen.
        title = new JLabel("Array Visualizer");
        title.setBounds(360, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(40, 60, 75));
        add(title);

        // Label and text field for value.
        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(40, 90, 80, 25);
        add(valueLabel);

        valueField = new JTextField();
        valueField.setBounds(120, 90, 120, 25);
        add(valueField);

        // Label and text field for index.
        indexLabel = new JLabel("Index:");
        indexLabel.setBounds(270, 90, 80, 25);
        add(indexLabel);

        indexField = new JTextField();
        indexField.setBounds(340, 90, 120, 25);
        add(indexField);

        // Insert button stores value at given index.
        insertButton = new JButton("Insert");
        insertButton.setBounds(500, 85, 100, 35);
        insertButton.addActionListener(this);
        add(insertButton);

        // Update button changes value at given index.
        updateButton = new JButton("Update");
        updateButton.setBounds(620, 85, 100, 35);
        updateButton.addActionListener(this);
        add(updateButton);

        // Delete button removes value from given index.
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(740, 85, 100, 35);
        deleteButton.addActionListener(this);
        add(deleteButton);

        // Search button finds value in the array.
        searchButton = new JButton("Search");
        searchButton.setBounds(500, 135, 100, 35);
        searchButton.addActionListener(this);
        add(searchButton);

        // Clear button makes all array boxes empty.
        clearButton = new JButton("Clear");
        clearButton.setBounds(620, 135, 100, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        // Back button returns to the main menu.
        backButton = new JButton("Back");
        backButton.setBounds(740, 135, 100, 35);
        backButton.addActionListener(this);
        add(backButton);

        // This label shows current message to the user.
        messageLabel = new JLabel("Array size is 8. Index starts from 0 and ends at 7.");
        messageLabel.setBounds(40, 185, 760, 25);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Draw panel shows the graphical array.
        drawPanel = new DrawArrayPanel();
        drawPanel.setBounds(40, 225, 800, 190);
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(drawPanel);

        // Log area stores small history of operations.
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBounds(40, 435, 800, 100);
        add(scrollPane);

        // Basic window settings.
        getContentPane().setBackground(new Color(235, 240, 242));
        setSize(900, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // This method checks which button was clicked.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertButton) {
            insertValue();
        } else if (e.getSource() == updateButton) {
            updateValue();
        } else if (e.getSource() == deleteButton) {
            deleteValue();
        } else if (e.getSource() == searchButton) {
            searchValue();
        } else if (e.getSource() == clearButton) {
            clearArray();
        } else if (e.getSource() == backButton) {
            mainMenu.setVisible(true);
            setVisible(false);
        }
    }

    // This method inserts a new value at a selected index.
    private void insertValue() {
        Integer value = getValue();
        Integer index = getIndex();

        // If input is wrong, stop the operation.
        if (value == null || index == null) {
            return;
        }

        // Insert is not allowed if index already has value.
        if (used[index]) {
            JOptionPane.showMessageDialog(null, "This index already has a value. Use Update.");
            return;
        }

        // Store value and mark this index as filled.
        arr[index] = value;
        used[index] = true;
        selectedIndex = index;
        messageLabel.setText("Value " + value + " inserted at index " + index + ".");
        logArea.append("Inserted " + value + " at index " + index + "\n");
        drawPanel.repaint();
    }

    // This method changes value at any index.
    private void updateValue() {
        Integer value = getValue();
        Integer index = getIndex();

        // If value or index is wrong, do nothing.
        if (value == null || index == null) {
            return;
        }

        // Update stores the new value at that index.
        arr[index] = value;
        used[index] = true;
        selectedIndex = index;
        messageLabel.setText("Index " + index + " updated with value " + value + ".");
        logArea.append("Updated index " + index + " with value " + value + "\n");
        drawPanel.repaint();
    }

    // This method removes value from selected index.
    private void deleteValue() {
        Integer index = getIndex();

        // If index is wrong, stop here.
        if (index == null) {
            return;
        }

        // Empty index cannot be deleted again.
        if (!used[index]) {
            JOptionPane.showMessageDialog(null, "This index is already empty.");
            return;
        }

        // Mark index as empty.
        used[index] = false;
        selectedIndex = index;
        messageLabel.setText("Value deleted from index " + index + ".");
        logArea.append("Deleted value from index " + index + "\n");
        drawPanel.repaint();
    }

    // This method searches a value in the array.
    private void searchValue() {
        Integer value = getValue();

        // If value is wrong, stop searching.
        if (value == null) {
            return;
        }

        // First remove old highlight.
        selectedIndex = -1;

        // Check every array index one by one.
        for (int i = 0; i < size; i++) {
            if (used[i] && arr[i] == value) {
                selectedIndex = i;
                messageLabel.setText("Value " + value + " found at index " + i + ".");
                logArea.append("Searched " + value + " and found at index " + i + "\n");
                drawPanel.repaint();
                return;
            }
        }

        // This runs when value is not found.
        messageLabel.setText("Value " + value + " not found.");
        logArea.append("Searched " + value + " but not found\n");
        drawPanel.repaint();
    }

    // This method clears all array indexes.
    private void clearArray() {
        for (int i = 0; i < size; i++) {
            used[i] = false;
        }

        // Remove highlight and redraw empty array.
        selectedIndex = -1;
        messageLabel.setText("Array cleared.");
        logArea.append("Array cleared\n");
        drawPanel.repaint();
    }

    // This method reads value from value text field.
    private Integer getValue() {
        try {
            return Integer.parseInt(valueField.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter correct value.");
            return null;
        }
    }

    // This method reads index and checks its range.
    private Integer getIndex() {
        try {
            int index = Integer.parseInt(indexField.getText().trim());

            // Array index must be between 0 and 7.
            if (index < 0 || index >= size) {
                JOptionPane.showMessageDialog(null, "Index should be from 0 to 7.");
                return null;
            }

            return index;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter correct index.");
            return null;
        }
    }

    // This inner class draws the array on screen.
    class DrawArrayPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Starting position and box size.
            int x = 45;
            int y = 65;
            int boxWidth = 80;
            int boxHeight = 55;

            // Heading inside drawing panel.
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.BLACK);
            g.drawString("One Dimensional Array", 45, 35);

            // Draw all array boxes one by one.
            for (int i = 0; i < size; i++) {
                if (i == selectedIndex) {
                    g.setColor(new Color(255, 230, 150));
                } else {
                    g.setColor(new Color(220, 235, 240));
                }

                // Draw one array box.
                g.fillRect(x, y, boxWidth, boxHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, boxWidth, boxHeight);

                // Show value if index is filled, otherwise show dash.
                if (used[i]) {
                    g.drawString(String.valueOf(arr[i]), x + 32, y + 32);
                } else {
                    g.drawString("-", x + 37, y + 32);
                }

                // Show index number below every box.
                g.drawString("[" + i + "]", x + 28, y + 78);
                x = x + 90;
            }
        }
    }
}
