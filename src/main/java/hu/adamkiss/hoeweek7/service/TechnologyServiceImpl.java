package hu.adamkiss.hoeweek7.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import hu.adamkiss.hoeweek7.entity.Technology;
import hu.adamkiss.hoeweek7.entity.utils.QueryUtils;
import hu.adamkiss.hoeweek7.service.dto.CreateTechnologyRequest;
import hu.adamkiss.hoeweek7.service.dto.TechnologyDto;
import hu.adamkiss.hoeweek7.service.validator.DeleteTechnologyValidator;
import hu.adamkiss.hoeweek7.service.validator.NewTechnologyValidator;
import hu.adamkiss.hoeweek7.service.validator.UpdateTechnologyValidator;

@RequestScoped
public class TechnologyServiceImpl implements TechnologyService {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("hoePU").createEntityManager();

    @Inject
    private NewTechnologyValidator newTechnologyValidator;

    @Inject
    private DeleteTechnologyValidator deleteTechnologyValidator;

    @Inject
    private UpdateTechnologyValidator updateTechnologyValidator;

    @Override
    public void deleteTechnology(long id) {
        deleteTechnologyValidator.validate(id);
        entityManager.getTransaction().begin();
        entityManager.createNamedQuery(QueryUtils.TECHNOLOGY_DELETE_BY_ID).setParameter("id", id).executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public TechnologyDto createTechnology(CreateTechnologyRequest createRequest) {
        newTechnologyValidator.validate(createRequest);
        Technology technology = createTechnologyEntity(createRequest);
        entityManager.getTransaction().begin();
        entityManager.persist(technology);
        entityManager.getTransaction().commit();
        return new TechnologyDto(technology);
    }

    private Technology createTechnologyEntity(CreateTechnologyRequest createRequest) {
        Technology technology = new Technology();
        technology.setName(createRequest.getName());
        technology.setDescription(createRequest.getDescription());
        technology.setEmpireLevel(createRequest.getEmpireLevel());
        return technology;
    }

    @Override
    public TechnologyDto updateTechnology(TechnologyDto technology) {
        updateTechnologyValidator.validate(technology);
        Technology updatedTechnology = updateTechnologyEntity(technology);
        entityManager.getTransaction().begin();
        entityManager.merge(updatedTechnology);
        entityManager.getTransaction().commit();
        return new TechnologyDto(updatedTechnology);
    }

    private Technology updateTechnologyEntity(TechnologyDto technology) {
        Technology updatedTechnology = entityManager
                .createNamedQuery(QueryUtils.TECHNOLOGY_FIND_BY_ID, Technology.class)
                .setParameter("id", technology.getId()).getSingleResult();
        updatedTechnology.setDescription(technology.getDescription());
        updatedTechnology.setEmpireLevel(technology.getEmpireLevel());
        updatedTechnology.setName(technology.getName());
        return updatedTechnology;
    }

    @Override
    public List<TechnologyDto> listTechnologies() {
        List<Technology> technologies = entityManager.createNamedQuery(QueryUtils.TECHNOLOGY_LIST_ALL, Technology.class)
                .getResultList();
        List<TechnologyDto> technologyDtos = new ArrayList<>();
        for (Technology technology : technologies) {
            technologyDtos.add(new TechnologyDto(technology));
        }
        return technologyDtos;
    }

    @Override
    public TechnologyDto getById(long id) {
        return new TechnologyDto(entityManager.createNamedQuery(QueryUtils.TECHNOLOGY_FIND_BY_ID, Technology.class)
                .setParameter("id", id).getSingleResult());
    }

}
