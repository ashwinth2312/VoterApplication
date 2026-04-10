package com.voter.servlet;

import com.voter.dao.CandidateDAO;
import com.voter.model.Voter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("voter") == null) {
            resp.sendRedirect("login.jsp?msg=Session+expired.+Please+login.");
            return;
        }

        Voter voter = (Voter) session.getAttribute("voter");

        if (voter.isVoted()) {
            resp.sendRedirect("result.jsp?msg=You+have+already+voted!");
            return;
        }

        String candidateIdStr = req.getParameter("candidateId");
        if (candidateIdStr == null || candidateIdStr.isEmpty()) {
            req.setAttribute("error", "Please select a candidate!");
            req.getRequestDispatcher("vote.jsp").forward(req, resp);
            return;
        }

        int candidateId = Integer.parseInt(candidateIdStr);
        CandidateDAO dao = new CandidateDAO();

        try {
            boolean success = dao.castVote(voter.getVoterId(), candidateId);
            if (success) {
                voter.setVoted(true);
                session.setAttribute("voter", voter);
                resp.sendRedirect("result.jsp?msg=Vote+cast+successfully!");
            } else {
                req.setAttribute("error", "Vote failed. Try again.");
                req.getRequestDispatcher("vote.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("vote.jsp").forward(req, resp);
        }
    }
}
