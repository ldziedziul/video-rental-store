package pl.dziedziul.videorentalstore.infra;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    @NotNull
    @Singular("message")
    private List<String> messages;

}
