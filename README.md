# 🗳️ Voter Application — Servlet + JDBC

A full-featured **Online Voting System** built with **Java Servlets**, **JSP**, and **JDBC (MySQL)**. Designed as a Java EE web application deployable on Apache Tomcat.

---

## 📸 Pages Overview

| Page | Description |
|------|-------------|
| `index.jsp` | Landing / Home page |
| `register.jsp` | Voter registration form |
| `login.jsp` | Voter login form |
| `vote.jsp` | Candidate selection & vote casting |
| `result.jsp` | Live election results with progress bars |

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | JSP, HTML5, CSS3 |
| Backend | Java Servlets (javax.servlet) |
| Database | MySQL 8.x |
| JDBC Driver | mysql-connector-java |
| Server | Apache Tomcat 9+ |
| Build Tool | Maven (optional) |

---

## 📁 Project Structure

```
VoterApplication/
│
├── sql/
│   └── voter_db.sql                    ← Database schema + sample data
│
├── src/main/
│   ├── java/com/voter/
│   │   ├── util/
│   │   │   └── DBConnection.java       ← JDBC connection utility
│   │   │
│   │   ├── model/
│   │   │   ├── Voter.java              ← Voter POJO (bean)
│   │   │   └── Candidate.java          ← Candidate POJO (bean)
│   │   │
│   │   ├── dao/
│   │   │   ├── VoterDAO.java           ← Voter DB operations
│   │   │   └── CandidateDAO.java       ← Candidate DB operations + voting
│   │   │
│   │   └── servlet/
│   │       ├── RegisterServlet.java    ← POST /RegisterServlet
│   │       ├── LoginServlet.java       ← POST /LoginServlet
│   │       ├── VoteServlet.java        ← POST /VoteServlet
│   │       └── LogoutServlet.java      ← GET  /LogoutServlet
│   │
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml                 ← Deployment descriptor
│       ├── css/
│       │   └── style.css               ← Stylesheet
│       ├── index.jsp                   ← Home page
│       ├── login.jsp                   ← Login page
│       ├── register.jsp                ← Registration page
│       ├── vote.jsp                    ← Voting page
│       └── result.jsp                  ← Results page
│
└── README.md
```

---

## ⚙️ Setup & Installation

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/VoterApplication.git
cd VoterApplication
```

### 2. Create the Database
Open **MySQL Workbench** or MySQL CLI and run:
```sql
source sql/voter_db.sql;
```
This will:
- Create the `voter_db` database
- Create `voters`, `candidates`, and `votes` tables
- Insert 4 sample candidates

### 3. Configure DB Credentials
Edit `src/main/java/com/voter/util/DBConnection.java`:
```java
private static final String URL      = "jdbc:mysql://localhost:3306/voter_db";
private static final String USERNAME = "root";     // ← your MySQL username
private static final String PASSWORD = "root";     // ← your MySQL password
```

### 4. Add MySQL JDBC Driver
Download **mysql-connector-java-8.x.x.jar** from:
👉 https://dev.mysql.com/downloads/connector/j/

Place it in:
```
src/main/webapp/WEB-INF/lib/mysql-connector-java-8.x.x.jar
```

### 5. Import into IDE

**Eclipse:**
- File → Import → Existing Projects into Workspace
- Select project folder → Finish
- Right-click project → Properties → Project Facets → Enable "Dynamic Web Module"

**IntelliJ IDEA:**
- File → Open → Select project folder
- Add Tomcat run configuration

### 6. Deploy on Tomcat
- Add Apache Tomcat 9+ in your IDE
- Run the project on the server
- Access at: `http://localhost:8080/VoterApplication/`

---

## 🔄 Application Flow

```
index.jsp
    ├── register.jsp → RegisterServlet → login.jsp
    └── login.jsp    → LoginServlet
                            ├── (not voted) → vote.jsp → VoteServlet → result.jsp
                            └── (voted)     → result.jsp
```

---

## ✅ Features

- ✅ Voter Registration with Aadhar number validation
- ✅ Duplicate Aadhar / email detection
- ✅ Session-based login and authentication
- ✅ One vote per voter (enforced at DB and session level)
- ✅ JDBC Transaction for atomic vote operations
- ✅ Live election results with percentage and progress bars
- ✅ Winner highlighted in results
- ✅ Session timeout after 30 minutes
- ✅ Logout with session invalidation

---

## 🗃️ Database Tables

### `voters`
| Column | Type | Description |
|--------|------|-------------|
| voter_id | INT PK AUTO_INCREMENT | Unique voter ID |
| name | VARCHAR(100) | Full name |
| email | VARCHAR(100) UNIQUE | Login email |
| password | VARCHAR(255) | Password |
| dob | DATE | Date of birth |
| aadhar_no | VARCHAR(12) UNIQUE | 12-digit Aadhar |
| gender | ENUM | Male/Female/Other |
| address | TEXT | Residential address |
| is_voted | TINYINT | 0 = not voted, 1 = voted |

### `candidates`
| Column | Type | Description |
|--------|------|-------------|
| candidate_id | INT PK | Unique candidate ID |
| name | VARCHAR(100) | Candidate name |
| party | VARCHAR(100) | Political party |
| symbol | VARCHAR(50) | Election symbol |
| vote_count | INT | Total votes received |

### `votes`
| Column | Type | Description |
|--------|------|-------------|
| vote_id | INT PK | Unique vote ID |
| voter_id | INT FK | References voters |
| candidate_id | INT FK | References candidates |
| voted_at | TIMESTAMP | Time of vote |


## 👨‍💻 Author

Ashwinth K
- GitHub: [@YOUR_USERNAME](https://github.com/YOUR_USERNAME)


