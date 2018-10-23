package hu.adamkiss.hoeweek7.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.adamkiss.hoeweek7.service.TechnologyService;
import hu.adamkiss.hoeweek7.service.dto.TechnologyDto;
import hu.adamkiss.hoeweek7.service.exception.TechnologyServiceException;

@WebServlet(name = "Technologies", urlPatterns = { "/Technologies" })
public class GetAll extends HttpServlet {

    @Inject
    TechnologyService technologyService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<TechnologyDto> all = technologyService.listTechnologies();
            request.setAttribute("dbus", all);
            request.getRequestDispatcher("technology.jsp").include(request, response);
        } catch (TechnologyServiceException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("error.jsp").include(request, response);
        }
    }
}