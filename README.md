# 🗂️ Data Structure Visualizer

> An interactive Java Swing desktop application that turns abstract data structure concepts into clear, step-by-step visual demonstrations.

[![Java](https://img.shields.io/badge/Java-8%2B-orange?logo=openjdk&logoColor=white)](https://docs.oracle.com/en/java/)
[![Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue?logo=java&logoColor=white)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Platform](https://img.shields.io/badge/Platform-Desktop-lightgrey)]()
[![Status](https://img.shields.io/badge/Status-In%20Development-yellow)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)
[![Stars](https://img.shields.io/github/stars/<your-username>/data-structure-visualizer?style=social)](https://github.com/<your-username>/data-structure-visualizer/stargazers)
[![Forks](https://img.shields.io/github/forks/<your-username>/data-structure-visualizer?style=social)](https://github.com/<your-username>/data-structure-visualizer/network/members)
[![Issues](https://img.shields.io/github/issues/<your-username>/data-structure-visualizer)](https://github.com/<your-username>/data-structure-visualizer/issues)

---

### 📌 Table of Contents

- [About The Project](#-about-the-project)
- [Features](#-features)
- [Topics Covered](#-topics-covered)
- [Built With](#️-built-with)
- [Getting Started](#-getting-started)
- [Project Structure](#-project-structure)
- [How It Works](#-how-it-works)
- [Contributing](#-contributing)
- [License](#-license)
- [Author](#-author)
- [Acknowledgements](#-acknowledgements)

---

## 📖 About The Project

Most students understand data structures in theory — but struggle to picture what's actually happening in memory when a node is inserted, a pointer moves, or a tree rebalances. Textbook diagrams are static, and reading code alone doesn't show *how* the structure changes over time.

**Data Structure Visualizer** solves this by giving every operation a live, graphical representation. Select a topic, perform an operation like `Push`, `Enqueue`, or `Insert`, and watch the structure update in real time — complete with labeled pointers (`TOP`, `FRONT`, `REAR`, `HEAD`, `ROOT`) and a running operation log that explains exactly what just happened.

This is a semester project built for the **Data Structures** course, designed to be both a learning tool and a classroom demonstration aid.

---

## ✨ Features

- 🧭 **Central Main Menu** — navigate to any data structure topic from one launcher screen
- 🎨 **Graphical Visualization** — boxes, arrows, and labels represent real memory layout, not just text output
- 🖱️ **Interactive Operations** — Add, Delete, Search, Push, Pop, Enqueue, Dequeue, Traverse, and more
- 🏷️ **Live Pointer Labels** — always know where `TOP`, `FRONT`, `REAR`, `HEAD`, and `ROOT` are pointing
- 📝 **Operation Log** — a running, human-readable log of every action performed
- 🧩 **Modular Design** — every topic lives in its own Java file and its own screen
- 🚫 **Zero Database Dependency** — pure in-memory visualization, no MySQL/JDBC setup required

---

## 🧠 Topics Covered

| Category | Modules |
|---|---|
| **Arrays** | Array visualization with index-based access |
| **Lists & Linked Lists** | Singly Linked List, Circular Linked List, Two-Way Circular Linked List |
| **Stack** | Array-based Stack, Linked List-based Stack, Stack Applications, Recursion via Stack |
| **Queue** | Simple Queue, Circular Queue, Priority Queue, Dynamic Priority Queue, Queue Applications |
| **Trees** | Binary Search Tree, AVL Tree (with rotations & balance factor), Tree Traversal (Inorder/Preorder/Postorder/Level-order) |
| **Conversions** | Infix ↔ Prefix ↔ Postfix using stack logic |

---

## 🛠️ Built With

- **Language:** [Java](https://docs.oracle.com/en/java/) (compatible with Java 8+)
- **GUI Toolkit:** [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/) (`JFrame`, `JPanel`, `JButton`, `JLabel`, `JTextField`, `JComboBox`, etc.)
- **IDE:** [VS Code](https://code.visualstudio.com/docs/languages/java) · [IntelliJ IDEA](https://www.jetbrains.com/idea/) · [NetBeans](https://netbeans.apache.org/)
- **Database:** None — fully in-memory, since data structures are runtime constructs

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the [Java Development Kit (JDK 8 or newer)](https://www.oracle.com/java/technologies/downloads/) installed:

```bash
java -version
javac -version
```

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/data-structure-visualizer.git
   cd data-structure-visualizer
   ```

2. **Compile the project**
   ```bash
   javac -d bin src/*.java
   ```

3. **Run the application**
   ```bash
   java -cp bin Main
   ```

   > Replace `Main` with the actual name of your launcher class if different.

4. Alternatively, open the project folder directly in **IntelliJ IDEA**, **NetBeans**, or **VS Code** (with the Java Extension Pack) and run it from the IDE.

---

## 📂 Project Structure

```
data-structure-visualizer/
├── src/
│   ├── Main.java                          # Application launcher
│   ├── MainMenu.java                      # Central navigation screen
│   ├── ArrayVisualizer.java
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
│   ├── BinarySearchTreeVisualizer.java
│   ├── AVLTreeVisualizer.java
│   ├── TreeTraversalVisualizer.java
│   └── ExpressionConversionVisualizer.java
├── README.md
└── LICENSE
```

---

## 🎯 How It Works

1. The app launches into the **Main Menu**.
2. The user selects a data structure topic.
3. The corresponding visualization screen opens.
4. The user enters a value and clicks an operation button (e.g., `Push`, `Insert`, `Enqueue`).
5. The program:
   - Validates the input
   - Performs the operation on the underlying data structure
   - Redraws the visual representation
   - Updates pointer labels (`TOP`, `FRONT`, `HEAD`, etc.)
   - Appends a message to the operation log
6. The user clicks **Back** to return to the Main Menu and explore another topic.

---

## 🤝 Contributing

Contributions make the open-source community a great place to learn and build. Any contributions are **greatly appreciated**. Check out GitHub's [First Contributions guide](https://github.com/firstcontributions/first-contributions) if you're new to pull requests.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

---

## 👤 Author

**Sheryar**
Owner: Sheryar Ahmad
Project Link: https://github.com/Sheryar-Ahmad/DataStructure-Visualizer


---


