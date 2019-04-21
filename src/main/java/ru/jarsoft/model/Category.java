package ru.jarsoft.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple JavaBean domain object that represents a Category.
 *
 * @author Artem Kostritsa
 * @version 1.0
 */

@Entity
@Table(name = "category")
public class Category {

    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column()
    private boolean deleted;

    @Column(unique = true)
    @Size(min = 2, max = 100)
    private String name;

    @Column(unique = true)
    @Size(min = 2, max = 100)
    private String reqName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Banner> bannerList = new ArrayList<>();

    public Category() {
    }

    public int getId() {
        return id;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public String getName() {
        return name;
    }
    public String getReqName() {
        return reqName;
    }
    public List<Banner> getBannerList() {
        return bannerList;
    }

    public Category setId(int id) {
        this.id = id;
        return this;
    }
    public Category setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
    public Category setName(String name) {
        this.name = name;
        return this;
    }
    public Category setReqName(String reqName) {
        this.reqName = reqName;
        return this;
    }
    public Category setBannerList(List<Banner> bannerList) {
        this.bannerList = bannerList;
        return this;
    }
}
