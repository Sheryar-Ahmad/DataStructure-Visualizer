# Data Structure Visualizer

## A Java Swing Desktop Application for Visual Learning

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-5382a1?style=for-the-badge&logo=java&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

## 📖 Overview

**Data Structure Visualizer** is an educational desktop application built with Java Swing that helps students and beginners understand complex data structures through interactive graphical representations. Instead of just reading code or looking at static diagrams, users can perform operations and see the internal changes happen in real-time.

## 🎯 Purpose

Many students struggle to understand how data structures work internally - how pointers move, how nodes connect, how elements are inserted or removed. This project bridges the gap between theoretical concepts and practical understanding by providing a visual, hands-on learning experience.

## ✨ Features

### 📊 Comprehensive Topic Coverage

- **Arrays** - 1D and 2D array visualization with index-based access
- **Linked Lists** - Singly, Circular, and Two-Way Circular Linked Lists
- **Stacks** - Array-based, Linked list-based, Stack applications, Recursion visualization
- **Queues** - Simple, Circular, Priority, and Dynamic Priority Queues
- **Trees** - Binary Search Tree (BST), AVL Tree, Tree Traversal methods
- **Expression Conversion** - Infix to Postfix, Infix to Prefix

### 🎨 Interactive Visualization

- **Graphical Display** - Boxes, arrows, circles, and labels show data structure state
- **Real-time Updates** - Visualization changes instantly with each operation
- **Pointer Labels** - Clear labels for TOP, FRONT, REAR, HEAD, TAIL, ROOT
- **Operation Log** - Detailed log explaining what happened after each action

## 🚀 Getting Started

### Prerequisites

- Java 8 or newer installed on your system
- Any Java IDE or command-line compiler

### Installation

1. Clone the repository:
```bash
git clone https://github.com/Sheryar-Ahmad/DataStructure-Visualizer.git
Navigate to the project directory:

bash
cd DataStructure-Visualizer
Compile the Java files:

bash
javac *.java
Run the application:

bash
java Main
💻 Usage Guide
Launch the application

Select a data structure topic from the main menu

Enter a value in the input field

Click operation buttons (Push, Pop, Insert, Delete, etc.)

Watch the visualization update in real-time

Check the operation log for detailed feedback

Use the Back button to return to the main menu

📁 Project Structure
text
DataStructure-Visualizer/
├── src/
│   ├── Main.java                    # Application launcher
│   ├── MainMenu.java                # Main navigation screen
│   ├── ArrayVisualizer.java         # Array visualization
│   ├── SinglyLinkedListVisualizer.java
│   ├── CircularLinkedListVisualizer.java
│   ├── TwoWayCircularLinkedListVisualizer.java
│   ├── StackArrayVisualizer.java
│   ├── StackLinkedListVisualizer.java
│   ├── StackApplicationsVisualizer.java
│   ├── RecursionVisualizer.java
│   ├── QueueVisualizer.java
│   ├── CircularQueueVisualizer.java
│   ├── PriorityQueueVisualizer.java
│   ├── DynamicPriorityQueueVisualizer.java
│   ├── QueueApplicationsVisualizer.java
│   ├── BinarySearchTreeVisualizer.java
│   ├── AVLTreeVisualizer.java
│   ├── TreeTraversalVisualizer.java
│   └── ExpressionConversionVisualizer.java
├── README.md
└── LICENSE
🎓 Learning Outcomes
✅ How data is stored in memory

✅ How pointers and references work

✅ How insertion and deletion operations affect structure

✅ Why different data structures suit different use cases

✅ How tree balancing works (AVL rotations)

✅ How expression conversion uses stack operations

✅ How recursion uses the call stack

🎯 Target Audience
Students learning data structures in computer science courses

Teachers demonstrating concepts in the classroom

Beginners wanting to understand memory representation

Self-learners exploring data structure fundamentals

🛠️ Technologies Used
Language: Java (JDK 8+)

GUI Framework: Java Swing

Development Tools: Any Java IDE

Version Control: Git

🚧 Limitations
Desktop-only application (not web-based)

No permanent data storage (educational focus)

Limited to Java Swing graphics

Not suitable for large-scale data processing

🤝 Contributing
Contributions are welcome! Please follow these steps:

Fork the repository

Create a feature branch (git checkout -b feature/AmazingFeature)

Commit your changes (git commit -m 'Add some AmazingFeature')

Push to the branch (git push origin feature/AmazingFeature)

Open a Pull Request

📝 License
This project is licensed under the MIT License - see the LICENSE file for details.

📧 Contact
Owner: Sheryar Ahmad
Project Link: https://github.com/Sheryar-Ahmad/DataStructure-Visualizer

🙏 Acknowledgments
Course instructors for project guidance

Java Swing documentation and tutorials

Open-source community for inspiration
