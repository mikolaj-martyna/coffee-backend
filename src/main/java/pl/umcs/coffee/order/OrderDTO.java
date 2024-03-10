package pl.umcs.coffee.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Status status;

    private Long userId;
    private List<Long> productIds;
}
