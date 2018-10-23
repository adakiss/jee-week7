package hu.adamkiss.hoeweek7.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import hu.adamkiss.hoeweek7.entity.utils.QueryUtils;

@Entity
@NamedQueries({ //
        @NamedQuery(name = QueryUtils.TECHNOLOGY_LIST_ALL, query = "SELECT t FROM Technology t"), //
        @NamedQuery(name = QueryUtils.TECHNOLOGY_FIND_BY_ID, query = "SELECT t FROM Technology t WHERE t.id = :id"), //
        @NamedQuery(name = QueryUtils.TECHNOLOGY_FIND_BY_NAME, query = "SELECT t FROM Technology t WHERE t.name = :name"),
        @NamedQuery(name = QueryUtils.TECHNOLOGY_DELETE_BY_ID, query = "DELETE FROM Technology t WHERE t.id = :id") })
public class Technology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
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

    public long getId() {
        return id;
    }
}
