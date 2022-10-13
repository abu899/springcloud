package catalog.service;

import catalog.domain.CatalogEntity;

import java.util.List;

public interface CatalogService {
    List<CatalogEntity> getCatalogs();
}
