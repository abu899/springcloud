package catalog.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ResponseCatalogDto {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private LocalDate createdAt;
}
