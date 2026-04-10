<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.voter.dao.CandidateDAO, com.voter.model.*, java.util.*" %>
<%
    if (session == null || session.getAttribute("voter") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Voter voter = (Voter) session.getAttribute("voter");
    if (voter.isVoted()) {
        response.sendRedirect("result.jsp");
        return;
    }
    List<Candidate> candidates = new CandidateDAO().getAllCandidates();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cast Vote | Voter Application</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <div class="card">
        <div class="header-row">
            <h2>🗳️ Cast Your Vote</h2>
            <a href="LogoutServlet" class="btn btn-outline btn-sm">Logout</a>
        </div>
        <p>Welcome, <strong><%= voter.getName() %></strong>! Select your candidate below.</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>

        <form action="VoteServlet" method="post">
            <div class="candidate-list">
                <% for (Candidate c : candidates) { %>
                <label class="candidate-card">
                    <input type="radio" name="candidateId" value="<%= c.getCandidateId() %>" required />
                    <div class="candidate-info">
                        <span class="symbol"><%=c.getSymbol()%></span>
                        <div>
                            <strong><%= c.getName() %></strong>
                            <span class="party"><%= c.getParty() %></span>
                        </div>
                    </div>
                </label>
                <% } %>
            </div>
            <button type="submit" class="btn btn-primary btn-block mt">✅ Submit Vote</button>
        </form>
    </div>
</div>
</body>
</html>
