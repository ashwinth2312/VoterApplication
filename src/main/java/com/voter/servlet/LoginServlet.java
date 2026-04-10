package com.voter.servlet;

import com.voter.dao.VoterDAO;
import com.voter.model.Voter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email    = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();

        VoterDAO dao = new VoterDAO();
        try {
            Voter voter = dao.login(email, password);
            if (voter != null) {
                HttpSession session = req.getSession();
                session.setAttribute("voter", voter);
                session.setAttribute("voterId", voter.getVoterId());

                if (voter.isVoted()) {
                    resp.sendRedirect("result.jsp");
                } else {
                    resp.sendRedirect("vote.jsp");
                }
            } else {
                req.setAttribute("error", "Invalid email or password!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
