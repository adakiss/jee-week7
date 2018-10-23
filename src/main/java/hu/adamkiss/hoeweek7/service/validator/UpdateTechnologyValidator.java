package hu.adamkiss.hoeweek7.service.validator;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import hu.adamkiss.hoeweek7.entity.Technology;
import hu.adamkiss.hoeweek7.entity.utils.QueryUtils;
import hu.adamkiss.hoeweek7.service.dto.TechnologyDto;
import hu.adamkiss.hoeweek7.service.exception.TechnologyServiceException;

@RequestScoped
public class UpdateTechnologyValidator extends BaseValidator {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("hoePU").createEntityManager();

    public void validate(TechnologyDto technologyDto) {
        validateParamIsNotNull(technologyDto);
        validateParamIsNotNull(technologyDto.getName());
        List<Technology> technologies = entityManager
                .createNamedQuery(QueryUtils.TECHNOLOGY_FIND_BY_ID, Technology.class)
                .setParameter("id", technologyDto.getId()).getResultList();
        if (technologies.size() < 1) {
            throw new TechnologyServiceException("Technology with id does not exist");
        }
        technologies = entityManager.createNamedQuery(QueryUtils.TECHNOLOGY_FIND_BY_NAME, Technology.class)
                .setParameter("name", technologyDto.getName()).getResultList();
        if (technologies.size() > 0) {
            Technology technology = technologies.iterator().next();
            if (technology.getId() != technologyDto.getId()) {
                throw new TechnologyServiceException("Technology name is not unique");
            }
        }
    }
}
