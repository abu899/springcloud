package catalog.service;

import catalog.domain.CatalogEntity;
import catalog.domain.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogEntity> getCatalogs() {
        return catalogRepository.findAll();
    }
}
