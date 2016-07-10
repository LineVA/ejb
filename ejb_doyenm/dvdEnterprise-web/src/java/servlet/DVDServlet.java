/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dvdShop.jpa.DVD;
import dvdShop.jpa.Supplier;
import dvdShop.metier.service.ICartItemMetierLocal;
import dvdShop.metier.service.IDVDMetierLocal;
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
@WebServlet(name = "DVDServlet", urlPatterns = {"/DVDServlet"})
public class DVDServlet extends HttpServlet {

    @EJB
    private IDVDMetierLocal dvdDao;

    @EJB
    private ICartItemMetierLocal cartMetier;

    private List<DVD> search(DVD dvd) {
        return dvdDao.getDVD(dvd.getTitle(), dvd.getGenre(), dvd.getYear());
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

        String action = request.getParameter("action").toLowerCase();
        // DVD's creation
        String title = request.getParameter("title").toLowerCase();
        String genre = request.getParameter("genre").toLowerCase();
        String dvdYearStr = request.getParameter("year");
        int dvdYear = 0;
        if (dvdYearStr != null && !dvdYearStr.equals("")) {
            dvdYear = Integer.parseInt(dvdYearStr);
        }
        String supplier = request.getParameter("supplier");
        String stockStr = request.getParameter("stock");
        int stock = 0;
        if (stockStr != null && !stockStr.equals("")) {
            stock = Integer.parseInt(stockStr);
        }

        Supplier sup = new Supplier(supplier);
        DVD dvd = new DVD(title, genre, dvdYear, stock, sup);

        List<DVD> dvdSearch = null;
        if ("Add".equalsIgnoreCase(action)) {
            System.out.println(dvd);
            dvdDao.addDVD(dvd);
        } else if ("Edit".equalsIgnoreCase(action)) {
            dvdDao.editDVD(dvd);
        } else if ("Delete".equalsIgnoreCase(action)) {
            dvdDao.deleteDVD(dvd);
        } else if ("Search".equalsIgnoreCase(action)) {
            dvdSearch = search(dvd);
        } else if ("AddToCartItem".equalsIgnoreCase(action)) {
            cartMetier.addDVDToCart(dvd);
        } else if ("displayCartItem".equalsIgnoreCase(action)) {
            request.setAttribute("cart", cartMetier.getCart());
            request.getRequestDispatcher("cartItemInfo.jsp").forward(request, response);
        }

        request.setAttribute("dvd", dvd);
        request.setAttribute("researchDVD", dvdSearch);
        request.setAttribute("allDVDs", dvdDao.getAllDVDs());
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