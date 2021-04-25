package net.nikaria.gni.boundary;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@ToString
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID_",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name="CREATION_TS")
    private LocalDateTime creationTs;

    @Column(name="UPDATE_TS")
    private LocalDateTime updateTs;
}
