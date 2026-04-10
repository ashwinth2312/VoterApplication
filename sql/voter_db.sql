-- ============================================
--  Voter Application Database Schema
--  Database: MySQL
-- ============================================

CREATE DATABASE IF NOT EXISTS voter_db;
USE voter_db;

-- Table: voters
CREATE TABLE IF NOT EXISTS voters (
    voter_id    INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    dob         DATE NOT NULL,
    aadhar_no   VARCHAR(12) NOT NULL UNIQUE,
    gender      ENUM('Male','Female','Other') NOT NULL,
    address     TEXT NOT NULL,
    is_voted    TINYINT(1) DEFAULT 0,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: candidates
CREATE TABLE IF NOT EXISTS candidates (
    candidate_id   INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    party          VARCHAR(100) NOT NULL,
    symbol         VARCHAR(50),
    vote_count     INT DEFAULT 0
);

-- Table: votes
CREATE TABLE IF NOT EXISTS votes (
    vote_id       INT AUTO_INCREMENT PRIMARY KEY,
    voter_id      INT NOT NULL,
    candidate_id  INT NOT NULL,
    voted_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (voter_id)    REFERENCES voters(voter_id),
    FOREIGN KEY (candidate_id) REFERENCES candidates(candidate_id)
);

-- Sample Candidates
INSERT INTO candidates (name, party, symbol) VALUES
('Rahul Sharma',  'Democratic Party',     'Hand'),
('Priya Mehta',   'Progressive Alliance', 'Star'),
('Arun Kumar',    'National Front',       'Sun'),
('Sunita Verma',  'Citizens United',      'Lotus');

-- Sample Admin Voter (password: admin123)
INSERT INTO voters (name, email, password, dob, aadhar_no, gender, address) VALUES
('Admin User', 'admin@voter.com', 'admin123', '1990-01-01', '123456789012', 'Male', 'Admin HQ');
