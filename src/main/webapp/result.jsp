<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.voter.dao.CandidateDAO, com.voter.model.*, java.util.*" %>
<%
    if (session == null || session.getAttribute("voter") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Candidate> results = new CandidateDAO().getResults();
    int totalVotes = results.stream().mapToInt(Candidate::getVoteCount).sum();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Results | Voter Application</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="card">
        <div class="header-row">
            <h2>📊 Election Results</h2>
            <a href="LogoutServlet" class="btn btn-outline btn-sm">Logout</a>
        </div>

        <% String msg = request.getParameter("msg");
           if (msg != null && !msg.isEmpty()) { %>
            <div class="alert alert-success"><%= msg %></div>
        <% } %>

        <p>Total votes cast: <strong><%= totalVotes %></strong></p>

        <div class="results-table">
            <div class="result-header">
                <span>Rank</span>
                <span>Candidate</span>
                <span>Party</span>
                <span>Votes</span>
                <span>%</span>
            </div>
            <% int rank = 1; for (Candidate c : results) {
               double pct = totalVotes > 0 ? (c.getVoteCount() * 100.0 / totalVotes) : 0;
            %>
            <div class="result-row <%= rank == 1 ? "winner" : "" %>">
                <span><%= rank == 1 ? "🏆" : rank %></span>
                <span><%= c.getName() %></span>
                <span><%= c.getParty() %></span>
                <span><%= c.getVoteCount() %></span>
                <span><%= String.format("%.1f", pct) %>%</span>
            </div>
            <div class="progress-bar-wrap">
                <div class="progress-bar" style="width:<%= String.format("%.1f", pct) %>%"></div>
            </div>
            <% rank++; } %>
        </div>
    </div>
</div>
</body>
</html>
