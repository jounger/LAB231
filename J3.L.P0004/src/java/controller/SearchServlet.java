/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import dao.impl.ArticleDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "ManageBookingServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    private final ArticleDAOImpl articleDAOImpl = new ArticleDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTitle = request.getParameter("search-title");
        String page = request.getParameter("page");
        int pageReq = Tool.toInteger(page, 1);
        int limitReq = 5;

        request.setAttribute("page", pageReq);
        request.setAttribute("limit", limitReq);

        List<Article> articles = articleDAOImpl.find(pageReq, limitReq);

        // Set to sidebar
        List<Article> articlesLast = articleDAOImpl.find(1, 5);
        request.setAttribute("articlesLast", articlesLast);

        if (!Tool.isNull(searchTitle)) {
            List<Article> searchResults = articleDAOImpl.findByTitle(1, 5, searchTitle);
            request.setAttribute("articles", searchResults);
            
            int totalElements = articleDAOImpl.countBySearch(searchTitle);
        
            int totalPages = (int) Math.ceil((double) totalElements / (double)limitReq);

            request.setAttribute("totalElements", totalElements);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("page", pageReq);
            request.setAttribute("limit", limitReq);
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/search-page.jsp").forward(request, response);
        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/home");
        }

    }
}
