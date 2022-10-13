package catalog.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CATALOG")
@Getter
public class CatalogEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
