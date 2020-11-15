/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import dao.impl.ArticleDAOImpl;
import dao.impl.CategoryDAOImpl;
import java.io.IOException;
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
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    private final ArticleDAOImpl articleDAOImpl = new ArticleDAOImpl();
    private final CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String articleId = request.getParameter("id");
        
        if (!Tool.isNull(articleId)) {
            Article article = articleDAOImpl.findById(Tool.toInteger(articleId, 0));
            if (article != null) {
                request.setAttribute("article", article);
            } else {
                request.setAttribute(Constant.ERROR_MESSAGE_ATTR, "Article not found");
            }
        } else {
            List<Article> articlesLast = articleDAOImpl.find(1, 1);
            request.setAttribute("article", articlesLast.get(0));
        }
        // Show in footer
        List<Article> articlesLast = articleDAOImpl.find(1, 3);
        request.setAttribute("articlesLast", articlesLast);
        // Show in sidebar
        List<Category> categories = categoryDAOImpl.findAll();
        request.setAttribute("categories", categories);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
}
