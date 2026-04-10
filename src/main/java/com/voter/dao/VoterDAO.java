package com.voter.dao;

import com.voter.model.Voter;
import com.voter.util.DBConnection;

import java.sql.*;

public class VoterDAO {

    // Register a new voter
    public boolean registerVoter(Voter voter) throws SQLException {
        String sql = "INSERT INTO voters (name, email, password, dob, aadhar_no, gender, address) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, voter.getName());
            ps.setString(2, voter.getEmail());
            ps.setString(3, voter.getPassword());
            ps.setString(4, voter.getDob());
            ps.setString(5, voter.getAadharNo());
            ps.setString(6, voter.getGender());
            ps.setString(7, voter.getAddress());
            return ps.executeUpdate() > 0;
        }
    }

    // Login validation
    public Voter login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM voters WHERE email = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractVoter(rs);
            }
        }
        return null;
    }

    // Check if aadhar already registered
    public boolean aadharExists(String aadharNo) throws SQLException {
        String sql = "SELECT voter_id FROM voters WHERE aadhar_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, aadharNo);
            return ps.executeQuery().next();
        }
    }

    // Mark voter as voted
    public boolean markAsVoted(int voterId) throws SQLException {
        String sql = "UPDATE voters SET is_voted = 1 WHERE voter_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, voterId);
            return ps.executeUpdate() > 0;
        }
    }

    // Get voter by ID
    public Voter getVoterById(int voterId) throws SQLException {
        String sql = "SELECT * FROM voters WHERE voter_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, voterId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractVoter(rs);
        }
        return null;
    }

    private Voter extractVoter(ResultSet rs) throws SQLException {
        Voter voter = new Voter();
        voter.setVoterId(rs.getInt("voter_id"));
        voter.setName(rs.getString("name"));
        voter.setEmail(rs.getString("email"));
        voter.setPassword(rs.getString("password"));
        voter.setDob(rs.getString("dob"));
        voter.setAadharNo(rs.getString("aadhar_no"));
        voter.setGender(rs.getString("gender"));
        voter.setAddress(rs.getString("address"));
        voter.setVoted(rs.getInt("is_voted") == 1);
        return voter;
    }
}
