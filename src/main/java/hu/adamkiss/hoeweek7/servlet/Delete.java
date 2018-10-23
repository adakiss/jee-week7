package hu.adamkiss.hoeweek7.servlet;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.adamkiss.hoeweek7.service.TechnologyService;
import hu.adamkiss.hoeweek7.service.exception.TechnologyServiceException;

@WebServlet(name = "Delete", urlPatterns = { "/Delete" })
public class Delete extends HttpServlet {

    @Inject
    TechnologyService technologyService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (validate(request.getParameterMap())) {
            try {
                long id = Long.parseLong(request.getParameter("id"));
                technologyService.deleteTechnology(id);
                response.sendRedirect("Technologies");
            } catch (TechnologyServiceException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Missing identifier!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private boolean validate(Map<String, String[]> paramMap) {
        return paramMap.containsKey("id") && Utils.isNumber(paramMap.get("id")[0]);
    }

}