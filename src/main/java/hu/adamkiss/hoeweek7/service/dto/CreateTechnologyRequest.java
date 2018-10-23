package hu.adamkiss.hoeweek7.service.dto;

public class CreateTechnologyRequest {

    private String name;
    private String description;
    private int empireLevel;

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
}
