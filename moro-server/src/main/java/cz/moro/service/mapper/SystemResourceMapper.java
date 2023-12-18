package cz.moro.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.moro.api.dto.SystemResourceInfoDto;
import cz.moro.domain.SystemResourceInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Mapper(componentModel = "spring")
public interface SystemResourceMapper {

    @Named("fromJson")
    default SystemResourceInfo fromJson(String input) throws JsonProcessingException {
        if (Objects.nonNull(input)) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(input, SystemResourceInfo.class);
        }
        return null;
    }

    @Named("toJson")
    default String toJson(SystemResourceInfoDto info) throws JsonProcessingException {
        if (Objects.nonNull(info)) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(info);
        }
        return null;
    }

    SystemResourceInfo toEntity(SystemResourceInfoDto dto);

    SystemResourceInfoDto toDto(SystemResourceInfo entity);
}
