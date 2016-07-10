/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dvdShop.metier.entities.ExtendsIntervention;
import dvdShop.metier.service.IParticipationMetierRemote;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author doyenm
 */
@WebServlet(name = "ParticipationServlet", urlPatterns = {"/ParticipationServlet"})
public class ParticipationServlet extends HttpServlet {

    @EJB
    IParticipationMetierRemote intervMetier;

    public ArrayList<ExtendsIntervention> search(ExtendsIntervention interv) {
        if ((interv.getFirstName().equals("") && !interv.getLastName().equals(""))
                || (!interv.getFirstName().equals("") && interv.getLastName().equals(""))) {
            return null;
        } else if ((interv.getYear() == 0 && !interv.getTitle().equals(""))
                || (interv.getYear() != 0 && interv.getTitle().equals(""))) {
            return null;
        } else if (!interv.getFirstName().equals("")&& !interv.getLastName().equals("")) {
                return intervMetier.searchByIdentity(interv);
        } else if (interv.getYear() != 0 && !interv.getTitle().equals("")){
            return intervMetier.searchByDVD(interv);
        } else {
            return null;
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        // Participation's creation
        String yearStr = request.getParameter("year");
        int year = 0;
        if (yearStr != null && !yearStr.equals("")) {
            year = Integer.parseInt(yearStr);
        }
        String title = request.getParameter("title").toLowerCase();
        String firstName = request.getParameter("firstName").toLowerCase();
        String lastName = request.getParameter("lastName").toLowerCase();

        ExtendsIntervention particip = new ExtendsIntervention(title, year, firstName, lastName);

        ArrayList<ExtendsIntervention> participSearch = null;
        if ("Add".equalsIgnoreCase(action)) {
            intervMetier.addParticipation(title, year, firstName, lastName);
        } else if ("Search".equalsIgnoreCase(action)) {
            participSearch = search(particip);
        }

        request.setAttribute("participation", particip);
        request.setAttribute("researchParticipations", participSearch);
        request.setAttribute("allParticipations", intervMetier.getAllParticipations());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
