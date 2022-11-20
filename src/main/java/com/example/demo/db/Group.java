package com.example.demo.db;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="group_table")
public class Group {
    @OneToMany(mappedBy = "group")
    private Set<Student> students;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String groupName;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @Override
    public String toString() {
        return id + " - " + groupName;
    }
}