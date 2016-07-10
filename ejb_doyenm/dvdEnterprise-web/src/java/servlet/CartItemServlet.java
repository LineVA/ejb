/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dvdShop.jpa.DVD;
import dvdShop.metier.entities.DeliveryStateException;
import dvdShop.metier.service.ICartItemMetierLocal;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "CartItemServlet", urlPatterns = {"/CartItemServlet"})
public class CartItemServlet extends HttpServlet {

    @EJB
    private ICartItemMetierLocal cartMetier;

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
        try {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action").toLowerCase();
        List<DVD> cart = null;
        if (action.equalsIgnoreCase("display")) {
            cart = cartMetier.getCart();
            request.setAttribute("cartAll", cart);
            request.getRequestDispatcher("cartItemInfo.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("deliver")) {
            int deliver = cartMetier.deliverCart();
                            ArrayList<String> deliverStr = new ArrayList<>();
            if(deliver == 0)
                  deliverStr.add("Vous ne pouvez pas passer une commande vide.");
            else if(deliver == 1)
                   deliverStr.add("Commande envoyée avec succès");
            
//            if (deliver) {
//                ArrayList<String> deliverStr = new ArrayList<>();
//                deliverStr.add("Commande envoyée avec succès");
//                request.setAttribute("deliverResult", deliverStr);
//            } else {
//                 ArrayList<String> deliverStr = new ArrayList<>();
//                deliverStr.add("Echec de l'envoi de la commande : en ");
//                request.setAttribute("deliverResult", deliverStr);
//            }
                 request.setAttribute("deliverResult", deliverStr);
            request.getRequestDispatcher("cartItemInfo.jsp").forward(request, response);
        } else {
            // DVD's creation
            int idDVD = Integer.parseInt(action);
            DVD dvd = new DVD(idDVD);
            cartMetier.addDVDToCart(dvd);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
        } catch (DeliveryStateException ex){
            ex.printStackTrace();
        }
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
