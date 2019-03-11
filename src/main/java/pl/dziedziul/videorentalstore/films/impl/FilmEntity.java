package pl.dziedziul.videorentalstore.films.impl;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import pl.dziedziul.videorentalstore.films.FilmType;

@Data
@NoArgsConstructor
@Entity
@Table(name = "FILM")
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
class FilmEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();
    @Version
    private long version = 0;
    private @NonNull String name;
    @Enumerated(EnumType.STRING)
    private @NonNull FilmType type;
    private @NonNull Instant dateCreated;

    public FilmEntity(final @NonNull String name, final @NonNull FilmType type, final @NonNull Instant dateCreated) {
        this.name = name;
        this.type = type;
        this.dateCreated = dateCreated;
    }
}
