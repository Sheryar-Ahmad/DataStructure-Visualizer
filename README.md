# 🗂️ Data Structure Visualizer — Java Swing DSA Learning Tool

> **Data Structure Visualizer** is an open-source Java Swing desktop application that visually demonstrates how Stacks, Queues, Linked Lists, Binary Search Trees, and AVL Trees work internally — built to help students **learn Data Structures and Algorithms (DSA)** through real-time, interactive graphics instead of static diagrams.

[![Java](https://img.shields.io/badge/Java-8%2B-orange?style=flat-square&logo=openjdk&logoColor=white)](https://docs.oracle.com/en/java/)
[![Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue?style=flat-square&logo=java&logoColor=white)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Platform](https://img.shields.io/badge/Platform-Desktop-lightgrey?style=flat-square)]()
[![License: MIT](https://img.shields.io/github/license/Sheryar-Ahmad/DataStructure-Visualizer?style=flat-square&color=green)](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer/blob/main/LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

[![Stars](https://img.shields.io/github/stars/Sheryar-Ahmad/DataStructure-Visualizer?style=flat-square&logo=github)](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer/stargazers)
[![Forks](https://img.shields.io/github/forks/Sheryar-Ahmad/DataStructure-Visualizer?style=flat-square&logo=github)](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer/network/members)
[![Issues](https://img.shields.io/github/issues/Sheryar-Ahmad/DataStructure-Visualizer?style=flat-square&logo=github)](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer/issues)
[![Last Commit](https://img.shields.io/github/last-commit/Sheryar-Ahmad/DataStructure-Visualizer?style=flat-square&logo=github)](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer/commits/main)
[![Repo Size](https://img.shields.io/github/repo-size/Sheryar-Ahmad/DataStructure-Visualizer?style=flat-square)]()
[![Top Language](https://img.shields.io/github/languages/top/Sheryar-Ahmad/DataStructure-Visualizer?style=flat-square)]()

🔗 **Repository:** [github.com/Sheryar-Ahmad/DataStructure-Visualizer](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer)

---

### 📌 Table of Contents

- [About The Project](#-about-the-project)
- [Why This Project? (Use Cases)](#-why-this-project)
- [Features](#-features)
- [Topics Covered](#-topics-covered)
- [Built With](#️-built-with)
- [Getting Started](#-getting-started)
- [Project Structure](#-project-structure)
- [How It Works](#-how-it-works)
- [FAQ](#-frequently-asked-questions)
- [Contributing](#-contributing)
- [License](#-license)
- [Author](#-author)
- [Show Your Support](#-show-your-support)
- [Keywords / Topics](#-keywords--topics)

---

## 📖 About The Project

Most students understand data structures in theory — but struggle to picture what's actually happening in memory when a node is inserted, a pointer moves, or a tree rebalances. Textbook diagrams are static, and reading code alone doesn't show *how* the structure changes over time.

**Data Structure Visualizer** solves this by giving every operation a live, graphical representation using **Java Swing**. Select a topic, perform an operation like `Push`, `Enqueue`, or `Insert`, and watch the structure update in real time — complete with labeled pointers (`TOP`, `FRONT`, `REAR`, `HEAD`, `ROOT`) and a running operation log that explains exactly what just happened.

This is a semester project built for the **Data Structures** course at COMSATS University, designed to work as both a self-learning tool and a classroom demonstration aid for Java-based DSA education.

---

## 🎯 Why This Project?

| If you are a... | This project helps you... |
|---|---|
| **CS/SE Student** | Visually understand Stack, Queue, Linked List, BST, and AVL Tree operations for exams and semester projects |
| **Instructor / TA** | Demonstrate live data structure behavior on-screen instead of drawing on a whiteboard |
| **Java Beginner** | See a real-world example of a multi-screen Java Swing desktop application |
| **Self-Learner** | Practice DSA concepts interactively without needing an IDE debugger or pen-and-paper tracing |

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
   git clone https://github.com/Sheryar-Ahmad/DataStructure-Visualizer.git
   cd DataStructure-Visualizer
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
DataStructure-Visualizer/
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

## ❓ Frequently Asked Questions

**What is Data Structure Visualizer?**
It's a free, open-source Java Swing desktop application that graphically demonstrates how core data structures — Arrays, Stacks, Queues, Linked Lists, and Trees — store and manipulate data in memory.

**Which data structures can I visualize?**
Arrays, Singly/Circular/Two-Way Linked Lists, Array & Linked List Stacks, Simple/Circular/Priority Queues, Binary Search Trees, AVL Trees, and Infix-Prefix-Postfix conversions.

**Do I need to install a database?**
No. The project is intentionally database-free — all data structures are visualized in memory, since that's how they actually work at runtime.

**Is this suitable for a university DSA course project?**
Yes — it was built as a semester project for a Data Structures course and is well-suited for academic submission, viva demonstrations, or classroom teaching aids.

**What Java version do I need?**
Java 8 or any newer version. No external libraries beyond the standard Java Swing toolkit are required.

**Can I contribute or extend it with new data structures?**
Absolutely — see the [Contributing](#-contributing) section below.

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

Distributed under the MIT License. See [`LICENSE`](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer/blob/main/LICENSE) for more information.

---

## 👤 Author

**Sheryar Ahmad**
🎓 BSE (Software Engineering), — COMSATS University Islamabad, Abbottabad Campus
🔗 GitHub: [@Sheryar-Ahmad](https://github.com/Sheryar-Ahmad)
📁 Project Repository: [DataStructure-Visualizer](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer)

---

## ⭐ Show Your Support

If this project helped you understand data structures better, consider:

- ⭐ **Starring** the [repository](https://github.com/Sheryar-Ahmad/DataStructure-Visualizer) — it directly improves visibility in GitHub search results
- 🍴 **Forking** it to build your own version or add new topics
- 🐛 **Opening an issue** for bugs or feature requests
- 📢 **Sharing** it with classmates learning Java or DSA

---

## 🔑 Keywords / Topics

`java` · `java-swing` · `data-structures` · `dsa` · `dsa-visualizer` · `algorithm-visualization` · `stack` · `queue` · `linked-list` · `binary-search-tree` · `avl-tree` · `tree-traversal` · `infix-postfix-prefix` · `gui-application` · `desktop-application` · `computer-science` · `student-project` · `education-tool` · `comsats`

>
