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
import hu.adamkiss.hoeweek7.service.dto.TechnologyDto;
import hu.adamkiss.hoeweek7.service.exception.TechnologyServiceException;

@WebServlet(name = "Update", urlPatterns = { "/Update" })
public class Update extends HttpServlet {

    @Inject
    TechnologyService technologyService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validateId(request.getParameterMap())) {
            try {
                TechnologyDto dbu = technologyService.getById(Long.parseLong(request.getParameter("id")));
                request.setAttribute("dbu", dbu);
                request.getRequestDispatcher("technologyEditor.jsp").forward(request, response);
            } catch (TechnologyServiceException ex) {
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Missing identifier.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (validateId(request.getParameterMap())) {
            if (!missingParameters(request.getParameterMap()).isEmpty()) {
                String errorMessage = "The following parameters are missing "
                        + String.join(",", missingParameters(request.getParameterMap()));
                request.setAttribute("message", errorMessage);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } else {
                try {
                    TechnologyDto t = new TechnologyDto();
                    t.setId(Long.parseLong(request.getParameter("id")));
                    t.setName(request.getParameter("tech_name"));
                    t.setDescription(request.getParameter("tech_desc"));
                    t.setEmpireLevel(Integer.parseInt(request.getParameter("tech_level")));

                    technologyService.updateTechnology(t);
                    response.sendRedirect("Technologies");

                } catch (TechnologyServiceException e) {
                    request.setAttribute("message", e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }

            }
        } else {
            request.setAttribute("message", "Missing identifier.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    private boolean validateId(Map<String, String[]> paramMap) {
        return paramMap.containsKey("id") && Utils.isNumber(paramMap.get("id")[0]);
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