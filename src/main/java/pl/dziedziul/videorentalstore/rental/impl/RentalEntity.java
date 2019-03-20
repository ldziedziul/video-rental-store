package pl.dziedziul.videorentalstore.rental.impl;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

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
class RentalEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();
    @Version
    private long version = 0;
    private @NonNull Instant dateCreated;
    @Column(columnDefinition = "uuid")
    private @NonNull UUID customerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rentalEntity", orphanRemoval = true)
    private @Size(min = 1) @NonNull Set<RentedFilmEntity> rentedFilms = new HashSet<>();

    public RentalEntity(final @NonNull UUID customerId, final @NonNull Instant dateCreated) {
        this.customerId = customerId;
        this.dateCreated = dateCreated;
    }

    RentalEntity withRentedFilm(final @NonNull RentedFilmEntity rentedFilmEntity) {
        rentedFilmEntity.setRentalEntity(this);
        rentedFilms.add(rentedFilmEntity);
        return this;
    }

    int getTotalPrice() {
        return rentedFilms.stream().map(RentedFilmEntity::getPrice).reduce(0, Integer::sum);
    }

}
