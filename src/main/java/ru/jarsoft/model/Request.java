package ru.jarsoft.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ru.jarsoft.model.audit.DateAudit;

import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents a Request.
 *
 * @author Artem Kostritsa
 * @version 1.0
 */

@Entity
@Table(name = "request")
public class Request extends DateAudit {

    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("bannerId")
    @OneToOne(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private Banner banner;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    @Lob
    private String userAgent;

    public Request() {
    }

    public int getId() {
        return id;
    }
    public Banner getBanner() {
        return banner;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public String getUserAgent() {
        return userAgent;
    }

    public Request setId(int id) {
        this.id = id;
        return this;
    }
    public Request setBanner(Banner banner) {
        this.banner = banner;
        return this;
    }
    public Request setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }
    public Request setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }
}
