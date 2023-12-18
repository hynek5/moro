package cz.moro.service;

import cz.moro.api.dto.SystemResourceInfoDto;
import cz.moro.domain.SystemResourceInfo;
import cz.moro.repository.SystemResourceInfoRepository;
import cz.moro.service.mapper.SystemResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SystemResourceInfoService {

    private final SystemResourceInfoRepository repository;
    private final SystemResourceMapper mapper;
    @Autowired
        public SystemResourceInfoService(SystemResourceInfoRepository repository, SystemResourceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public Flux<SystemResourceInfoDto> findAllInfos() {
        return repository.findAll()
                .map(mapper::toDto);
    }


    @Transactional
    public Mono<SystemResourceInfoDto> save(SystemResourceInfo resourceInfo) {
        return repository.save(resourceInfo)
                .map(mapper::toDto);
    }
}
