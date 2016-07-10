/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dvdShop.jpa.Individual;
import dvdShop.metier.service.IIndividualMetierLocal;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "IndividualServlet", urlPatterns = {"/IndividualServlet"})
public class IndividualServlet extends HttpServlet {

    @EJB
    private IIndividualMetierLocal metier;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String action = request.getParameter("action");
        // Individual's creation
        String firstName = request.getParameter("firstName").toLowerCase();
        String lastName = request.getParameter("lastName").toLowerCase();
        Individual indiv = new Individual(firstName, lastName);

        List<Individual> indivSearch = null;
        if ("Add".equalsIgnoreCase(action)) {
            metier.addIndividual(indiv);
        } else if ("Delete".equalsIgnoreCase(action)) {
            metier.deleteIndividual(indiv);
        } else if ("Search".equalsIgnoreCase(action)) {
            indivSearch = metier.getIndividualByIdentity(firstName, lastName);
         } 

        request.setAttribute("individual", indiv);
        request.setAttribute("researchIndividual", indivSearch);
        request.setAttribute("allIndividuals", metier.getAllIndividuals());
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
