package pl.zajavka.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.dao.PartDAO;
import pl.zajavka.domain.Part;
import pl.zajavka.infrastructure.database.repository.jpa.PartJpaRepository;
import pl.zajavka.infrastructure.database.repository.mapper.PartEntityMapper;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class PartRepository implements PartDAO {

    private final PartJpaRepository partJpaRepository;
    private final PartEntityMapper partMapper;

    @Override
    public List<Part> findAll() {
        return partJpaRepository.findAll().stream()
                .map(partMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Part> findBySerialNumber(String serialNumber) {
        return partJpaRepository.findBySerialNumber(serialNumber)
            .map(partMapper::mapFromEntity);
    }
}
