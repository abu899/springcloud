package catalog.controller;

import catalog.domain.CatalogEntity;
import catalog.dto.CatalogDto;
import catalog.dto.ResponseCatalogDto;
import catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {

    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping("/health")
    public String status() {
        String port = env.getProperty("local.server.port");
        return "Catalog controller health check : " + port;
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalogDto>> getCatalogs() {
        List<CatalogEntity> catalogs = catalogService.getCatalogs();
        List<ResponseCatalogDto> responses = new ArrayList<>();
        catalogs.forEach(
                catalog -> {
                    ResponseCatalogDto dto = ResponseCatalogDto.builder()
                            .productId(catalog.getProductId())
                            .productName(catalog.getProductName())
                            .unitPrice(catalog.getUnitPrice())
                            .stock(catalog.getStock())
                            .build();

                    responses.add(dto);
                }
        );

        return ResponseEntity.ok(responses);
    }
}
