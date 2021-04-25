package net.nikaria.gni.boundary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SERIES")
public class Series extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "SERIES_ID")
    private Set<Comic> comicList = new HashSet<>();
}
