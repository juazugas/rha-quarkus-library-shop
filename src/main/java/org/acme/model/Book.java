package org.acme.model;

import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

@Entity
public class Book extends PanacheEntity {

    public String title;

    @Column(name="publication_year")
    public int year;

    public String isbn;

    public double price;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Author> authors;

}
