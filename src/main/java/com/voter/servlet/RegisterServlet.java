package com.voter.servlet;

import com.voter.dao.VoterDAO;
import com.voter.model.Voter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name     = req.getParameter("name").trim();
        String email    = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        String dob      = req.getParameter("dob").trim();
        String aadhar   = req.getParameter("aadharNo").trim();
        String gender   = req.getParameter("gender").trim();
        String address  = req.getParameter("address").trim();

        VoterDAO dao = new VoterDAO();

        try {
            if (dao.aadharExists(aadhar)) {
                req.setAttribute("error", "Aadhar number already registered!");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }

            Voter voter = new Voter();
            voter.setName(name);
            voter.setEmail(email);
            voter.setPassword(password);
            voter.setDob(dob);
            voter.setAadharNo(aadhar);
            voter.setGender(gender);
            voter.setAddress(address);

            boolean success = dao.registerVoter(voter);
            if (success) {
                resp.sendRedirect("login.jsp?msg=Registration+successful!+Please+login.");
            } else {
                req.setAttribute("error", "Registration failed. Please try again.");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
