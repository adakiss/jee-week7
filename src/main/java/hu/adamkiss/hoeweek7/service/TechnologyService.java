package hu.adamkiss.hoeweek7.service;

import java.util.List;

import hu.adamkiss.hoeweek7.service.dto.CreateTechnologyRequest;
import hu.adamkiss.hoeweek7.service.dto.TechnologyDto;

public interface TechnologyService {

    void deleteTechnology(long id);

    TechnologyDto createTechnology(CreateTechnologyRequest createRequest);

    TechnologyDto updateTechnology(TechnologyDto technology);

    List<TechnologyDto> listTechnologies();

    TechnologyDto getById(long id);
}
