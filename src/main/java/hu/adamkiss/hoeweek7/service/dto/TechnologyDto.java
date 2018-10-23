package hu.adamkiss.hoeweek7.service.dto;

import hu.adamkiss.hoeweek7.entity.Technology;

public class TechnologyDto {

    private String name;
    private String description;
    private int empireLevel;
    private long id;

    public TechnologyDto() {
    }

    public TechnologyDto(Technology technology) {
        this.name = technology.getName();
        this.description = technology.getDescription();
        this.empireLevel = technology.getEmpireLevel();
        this.id = technology.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmpireLevel() {
        return empireLevel;
    }

    public void setEmpireLevel(int empireLevel) {
        this.empireLevel = empireLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
