/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.ArticleDAOImpl;
import dao.impl.CategoryDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;
import model.Category;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    private final ArticleDAOImpl articleDAOImpl = new ArticleDAOImpl();
    private final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        int pageReq = Tool.toInteger(page, 1);
        int limitReq = 5;

        request.setAttribute("categoryId", id);
        request.setAttribute("page", pageReq);

        // Set to sidebar
        // Show in sidebar
        List<Category> categories = categoryDAOImpl.findAll();
        request.setAttribute("categories", categories);
        
        List<Article> searchResults = new ArrayList<>();
        int totalElements = 0;
        int totalPages = 0;
        if (!Tool.isNull(id)) {
            int categoryId = Tool.toInteger(id, 0);
            searchResults = articleDAOImpl.findByCategory(pageReq, limitReq, categoryId);
            totalElements = articleDAOImpl.countByCategory(categoryId);
            totalPages = (int) Math.ceil((double) totalElements / (double)limitReq);
        }
        request.setAttribute("articles", searchResults);
        request.setAttribute("totalElements", totalElements);
        request.setAttribute("totalPages", totalPages);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/category.jsp").forward(request, response);

    }
}
