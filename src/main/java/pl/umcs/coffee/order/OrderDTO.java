package pl.umcs.coffee.order;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Status status;

    private Long userId;
    private List<Long> productIds;

    private LocalDateTime date;
}
