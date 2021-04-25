package net.nikaria.gni.boundary;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "COMICS")
public class Comic extends AbstractEntity {

    @Column(name = "NAME")
    @Getter
    @Setter
    private String title;

    @Column(name = "RELEASE_YEAR")
    @Getter
    @Setter
    private int releaseYear;

    @Column(name = "RELEASE_MONTH")
    @Getter
    @Setter
    private int releaseMonth;

//    @ManyToOne
//    @JoinColumn(name="SERIES_ID")
//    private Series seriesId;
}
