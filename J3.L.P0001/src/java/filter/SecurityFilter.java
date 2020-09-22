/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import common.Router;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Users;
import utils.SecurityStore;
import utils.Tool;

/**
 *
 * @author nguyenvanan
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE})
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getServletPath();
        System.out.println("Path: " + path);
        if (path.contains("/login") || path.contains("/logout") || path.contains("/registration") || path.contains("/error")) {
            chain.doFilter(request, response);
            return;
        }
        Users user = SecurityStore.getAuth(req.getSession());
        if (user != null) {
            String roleName = user.getRoles().get(0).getName();
            if ((roleName.equals("TEACHER") && Tool.includes(Router.TEACHER, path)) 
                    || (roleName.equals("STUDENT") && Tool.includes(Router.STUDENT, path))) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getServletContext().getContextPath() + "/error?status=403");
            }
            return;
        }
        res.sendRedirect(req.getServletContext().getContextPath() + "/login");
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
    }

}
