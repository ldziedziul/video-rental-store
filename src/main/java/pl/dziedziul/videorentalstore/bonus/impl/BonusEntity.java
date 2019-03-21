package pl.dziedziul.videorentalstore.bonus.impl;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "RENTAL")
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
class BonusEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();
    @Version
    private long version = 0;
    private @NonNull Instant dateCreated;
    @Column(columnDefinition = "uuid")
    private @NonNull UUID customerId;
    @Column(columnDefinition = "uuid")
    private @NonNull UUID filmId;
    private @NonNull Integer points;

    BonusEntity(final @NonNull UUID customerId, final @NonNull UUID filmId, final @NonNull Integer points, final @NonNull Instant dateCreated) {
        this.customerId = customerId;
        this.filmId = filmId;
        this.points = points;
        this.dateCreated = dateCreated;
    }
}
