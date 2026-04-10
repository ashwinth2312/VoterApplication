package com.voter.dao;

import com.voter.model.Candidate;
import com.voter.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {

    // Get all candidates
    public List<Candidate> getAllCandidates() throws SQLException {
        List<Candidate> list = new ArrayList<>();
        String sql = "SELECT * FROM candidates ORDER BY candidate_id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(extractCandidate(rs));
            }
        }
        return list;
    }

    // Cast a vote
    public boolean castVote(int voterId, int candidateId) throws SQLException {
        Connection con = DBConnection.getConnection();
        try {
            con.setAutoCommit(false);

            // Insert vote record
            String insertVote = "INSERT INTO votes (voter_id, candidate_id) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertVote)) {
                ps.setInt(1, voterId);
                ps.setInt(2, candidateId);
                ps.executeUpdate();
            }

            // Increment candidate vote count
            String updateCount = "UPDATE candidates SET vote_count = vote_count + 1 WHERE candidate_id = ?";
            try (PreparedStatement ps = con.prepareStatement(updateCount)) {
                ps.setInt(1, candidateId);
                ps.executeUpdate();
            }

            // Mark voter as voted
            String markVoted = "UPDATE voters SET is_voted = 1 WHERE voter_id = ?";
            try (PreparedStatement ps = con.prepareStatement(markVoted)) {
                ps.setInt(1, voterId);
                ps.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

    // Get results ordered by votes
    public List<Candidate> getResults() throws SQLException {
        List<Candidate> list = new ArrayList<>();
        String sql = "SELECT * FROM candidates ORDER BY vote_count DESC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(extractCandidate(rs));
            }
        }
        return list;
    }

    private Candidate extractCandidate(ResultSet rs) throws SQLException {
        Candidate c = new Candidate();
        c.setCandidateId(rs.getInt("candidate_id"));
        c.setName(rs.getString("name"));
        c.setParty(rs.getString("party"));
        c.setSymbol(rs.getString("symbol"));
        c.setVoteCount(rs.getInt("vote_count"));
        return c;
    }
}
