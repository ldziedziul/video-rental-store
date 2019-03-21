package pl.dziedziul.videorentalstore.rental;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class FilmRentedEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    UUID filmId;
    UUID customerId;

    public FilmRentedEvent(final @NonNull Object source, final @NonNull UUID filmId, final @NonNull UUID customerId) {
        super(source);
        this.filmId = filmId;
        this.customerId = customerId;
    }

}
