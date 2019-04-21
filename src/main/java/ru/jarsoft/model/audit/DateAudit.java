package ru.jarsoft.model.audit;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/**
 * JPA Date audit object.
 *
 * @author Artem Kostritsa
 * @version 1.0
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAudit implements Serializable {

    @CreatedDate
    private Instant date;

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
