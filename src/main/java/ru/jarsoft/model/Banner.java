package ru.jarsoft.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple JavaBean domain object that represents a Banner.
 *
 * @author Artem Kostritsa
 * @version 1.0
 */

@Entity
@Table(name = "banner")
public class Banner {

    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("categoryId")
    @OneToOne(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    @Column()
    private boolean deleted;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(unique = true)
    private String name;

    @Column(precision = 8, scale = 2)
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "banner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Request> requestList = new ArrayList<>();

    public Banner() {
    }

    public int getId() {
        return id;
    }
    public Category getCategory() {
        return category;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public String getContent() {
        return content;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public List<Request> getRequestList() {
        return requestList;
    }

    public Banner setId(int id) {
        this.id = id;
        return this;
    }
    public Banner setCategory(Category category) {
        this.category = category;
        return this;
    }
    public Banner setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
    public Banner setContent(String content) {
        this.content = content;
        return this;
    }
    public Banner setName(String name) {
        this.name = name;
        return this;
    }
    public Banner setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public Banner setRequestList(List<Request> requestList) {
        this.requestList = requestList;
        return this;
    }
}
