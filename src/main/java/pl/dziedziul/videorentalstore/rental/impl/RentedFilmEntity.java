package pl.dziedziul.videorentalstore.rental.impl;

import static pl.dziedziul.videorentalstore.rental.impl.RentalStatus.RENTED;
import static pl.dziedziul.videorentalstore.rental.impl.RentalStatus.RETURNED;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "RENTED_FILM")
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
class RentedFilmEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();
    @Version
    private long version = 0;
    @Column(columnDefinition = "uuid")
    private @NonNull UUID filmId;
    private @NonNull Integer price;
    @ManyToOne(optional = false)
    private @NonNull RentalEntity rentalEntity;
    private @NonNull Integer durationInDays;
    @Enumerated(EnumType.STRING)
    private @NonNull RentalStatus status = RENTED;


    RentedFilmEntity(final @NonNull UUID filmId, final @NonNull Integer price, final @NonNull Integer durationInDays) {
        this.filmId = filmId;
        this.price = price;
        this.durationInDays = durationInDays;
    }

    void markAsReturned() {
        status = RETURNED;
    }
}
