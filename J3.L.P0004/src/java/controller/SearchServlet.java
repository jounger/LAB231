/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import dao.impl.ArticleDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    private final ArticleDAOImpl articleDAOImpl = new ArticleDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTitle = request.getParameter("search-title");
        String page = request.getParameter("page");
        int pageReq = Tool.toInteger(page, 1);
        int limitReq = 5;

        request.setAttribute("searchTitle", searchTitle);
        request.setAttribute("page", pageReq);
        request.setAttribute("limit", limitReq);

        // Set to sidebar
        List<Article> articlesLast = articleDAOImpl.find(1, 5);
        request.setAttribute("articlesLast", articlesLast);
        
        List<Article> searchResults = new ArrayList<>();
        int totalElements = 0;
        int totalPages = 0;
        if (!Tool.isNull(searchTitle)) {
            searchResults = articleDAOImpl.findByTitle(1, 5, searchTitle);
            totalElements = articleDAOImpl.countBySearch(searchTitle);
            totalPages = (int) Math.ceil((double) totalElements / (double)limitReq);
        }
        request.setAttribute("articles", searchResults);
        request.setAttribute("totalElements", totalElements);
        request.setAttribute("totalPages", totalPages);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/search.jsp").forward(request, response);

    }
}
