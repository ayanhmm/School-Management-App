package org.javaSchool.studentManagement;

import java.util.Map;

public class Student {
    private Integer id;
    private String name;
    private Map<String, Object> Details;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getDetails() {
        return Details;
    }
    public void setDetails(Map<String, Object> details) {
        Details = details;
    }

    @Override
    public String toString(){
        return "Student { " +
                "Id = " +  id +
                ";Name = " +  name +
                ";Details = " +  Details +
                " }";
    }
}
