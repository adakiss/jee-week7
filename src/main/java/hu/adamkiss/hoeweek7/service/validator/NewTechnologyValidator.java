package hu.adamkiss.hoeweek7.service.validator;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import hu.adamkiss.hoeweek7.entity.Technology;
import hu.adamkiss.hoeweek7.entity.utils.QueryUtils;
import hu.adamkiss.hoeweek7.service.dto.CreateTechnologyRequest;
import hu.adamkiss.hoeweek7.service.exception.TechnologyServiceException;

@RequestScoped
public class NewTechnologyValidator extends BaseValidator {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("hoePU").createEntityManager();

    public void validate(CreateTechnologyRequest createTechnologyRequest) {
        validateParamIsNotNull(createTechnologyRequest);
        validateParamIsNotNull(createTechnologyRequest.getName());
        validateNameIsUnique(createTechnologyRequest);

    }

    private void validateNameIsUnique(CreateTechnologyRequest createTechnologyRequest) {
        List<Technology> technologies = entityManager
                .createNamedQuery(QueryUtils.TECHNOLOGY_FIND_BY_NAME, Technology.class)
                .setParameter("name", createTechnologyRequest.getName()).getResultList();
        if (technologies.size() > 0) {
            throw new TechnologyServiceException("Name is not uniqe");
        }
    }
}
