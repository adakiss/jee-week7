package hu.adamkiss.hoeweek7.service.validator;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import hu.adamkiss.hoeweek7.entity.Technology;
import hu.adamkiss.hoeweek7.entity.utils.QueryUtils;
import hu.adamkiss.hoeweek7.service.exception.TechnologyServiceException;

@RequestScoped
public class DeleteTechnologyValidator extends BaseValidator {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("hoePU").createEntityManager();

    public void validate(long id) {
        List<Technology> technologies = entityManager
                .createNamedQuery(QueryUtils.TECHNOLOGY_FIND_BY_ID, Technology.class).setParameter("id", id)
                .getResultList();
        if (technologies.size() < 1) {
            throw new TechnologyServiceException("Technology with id does not exist");
        }
    }
}
