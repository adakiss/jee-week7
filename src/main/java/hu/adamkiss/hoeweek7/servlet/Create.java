package hu.adamkiss.hoeweek7.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.adamkiss.hoeweek7.service.TechnologyService;
import hu.adamkiss.hoeweek7.service.dto.CreateTechnologyRequest;
import hu.adamkiss.hoeweek7.service.exception.TechnologyServiceException;

@WebServlet(name = "Create", urlPatterns = { "/Create" })
public class Create extends HttpServlet {

    @Inject
    TechnologyService technologyService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!missingParameters(request.getParameterMap()).isEmpty()) {
            String errorMessage = "The following parameters are missing "
                    + String.join(",", missingParameters(request.getParameterMap()));
            request.setAttribute("message", errorMessage);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            try {
                CreateTechnologyRequest ct = new CreateTechnologyRequest();
                ct.setName(request.getParameter("tech_name"));
                ct.setDescription(request.getParameter("tech_desc"));
                ct.setEmpireLevel(Integer.parseInt(request.getParameter("tech_level")));

                technologyService.createTechnology(ct);
                response.sendRedirect("Technologies");

            } catch (TechnologyServiceException ex) {
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        }

    }

    private List<String> missingParameters(Map<String, String[]> params) {
        List<String> missingParams = new ArrayList<>();

        if (!params.containsKey("tech_name") || (params.get("tech_name")[0] == null)
                || params.get("tech_name")[0].trim().isEmpty()) {
            missingParams.add("tech_name");
        }
        if (!params.containsKey("tech_desc") || (params.get("tech_desc")[0] == null)
                || params.get("tech_desc")[0].trim().isEmpty()) {
            missingParams.add("tech_desc");
        }
        if (!params.containsKey("tech_level") || !Utils.isNumber(params.get("tech_level")[0])) {
            missingParams.add("tech_level");
        }

        return missingParams;
    }
}