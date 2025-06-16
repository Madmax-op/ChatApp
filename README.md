# 💬 Real-Time Chat Application

A real-time multi-user chat application built using **Java Sockets**, **JDBC**, **PostgreSQL**, and **Swing**. This project enables seamless and persistent communication among users with a simple and intuitive desktop UI.

---

## 🧩 Features

- 🔗 Real-time communication via Java Sockets (Client-Server Architecture)
- 💾 Persistent storage of messages and user data using PostgreSQL and JDBC
- 👤 User authentication and management
- 📨 Chat history saved and retrieved on login
- 🖥️ GUI built using Java Swing
- 🛡️ Basic error handling and connection checks

---

## 🛠️ Technologies Used

| Technology   | Description                                        |
|--------------|----------------------------------------------------|
| Java         | Core application logic, multithreading, Sockets   |
| Swing        | GUI for client-side user experience               |
| JDBC         | Database connectivity                             |
| PostgreSQL   | Persistent storage of user data and chat logs     |

---

## 🖥️ Application Architecture

+-----------------+ +------------------+ +----------------------+
| Client 1 | <---> | Server | <---> | PostgreSQL DB |
+-----------------+ +------------------+ +----------------------+
| Swing UI | | Socket Handler | | Users, Messages |
| Java Socket | | JDBC Connector | | Tables |
+-----------------+ +------------------+ +----------------------+


## 🚀 How to Run

### Prerequisites
- Java 8 or above
- PostgreSQL Server
- IDE (e.g., IntelliJ, Eclipse)

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/chat-app.git
2. Configure the Database
Create a new PostgreSQL database (e.g., chat_app)
Execute the provided SQL schema (e.g., schema.sql)
3. Update DB credentials in your Java code (DB_URL, USERNAME, PASSWORD)
4. Compile and Run the Server


📂 Project Structure
arduino
Copy
Edit
chat-app/
│
├── client/
│   ├── Client.java
│   ├── ChatUI.java
│   └── ...
│
├── server/
│   ├── Server.java
│   ├── ClientHandler.java
│   └── ...
│
├── database/
│   ├── DBConnection.java
│   ├── schema.sql
│   └── ...
│
└── README.md

📌 Future Improvements
✅ Group chats
✅ Message encryption
✅ File sharing
✅ Online user tracking

🧑‍💻 Author
Vivek Raj Sahay
