/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dvdShop.jpa.Delivery;
import dvdShop.jpa.Subdelivery;
import dvdShop.metier.entities.DeliveryStateException;
import dvdShop.metier.service.IDeliveryMetierLocal;
import dvdShop.metier.service.ISubdeliveryMetierLocal;
import java.io.IOException;
import java.util.Iterator;
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
@WebServlet(name = "DeliveryServlet", urlPatterns = {"/DeliveryServlet"})
public class DeliveryServlet extends HttpServlet {

    @EJB
    private IDeliveryMetierLocal delMetier;

    @EJB
    private ISubdeliveryMetierLocal subMetier;

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
            List<Delivery> deliveryList = null;
            System.out.println(action.substring(0, 5));
            // Display the deliveries
            if (action.equalsIgnoreCase("display")) {
                // Departure of a delivery
            } else if (action.substring(0, 4).equals("gone")) {
                int id = Integer.parseInt(action.substring(4));
                delMetier.gone(id);
            } else if (action.substring(0, 9).equals("sub_ready")) {
                int id = Integer.parseInt(action.substring(9));
                subMetier.ready(id);
            }
            List<Delivery> listDel = delMetier.getAll();
            Iterator it = listDel.iterator();
            Delivery next;
            String attribute;
            List<Subdelivery> sub;
            while (it.hasNext()) {
                next = (Delivery) it.next();
                attribute = "subdelivery" + next.getIdDelivery();
                sub = subMetier.getByDelivery(next);
                next.setSubdeliveries(sub);

            }
            request.setAttribute("allDeliveries", listDel);
            request.getRequestDispatcher("deliveryIndex.jsp").forward(request, response);
        } catch (DeliveryStateException ex) {
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
