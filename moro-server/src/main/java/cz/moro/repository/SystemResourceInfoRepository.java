package cz.moro.repository;

import cz.moro.domain.SystemResourceInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemResourceInfoRepository extends ReactiveCrudRepository<SystemResourceInfo, Long> {
}
