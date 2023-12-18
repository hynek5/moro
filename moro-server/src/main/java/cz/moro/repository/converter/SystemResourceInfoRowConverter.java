package cz.moro.repository.converter;

import cz.moro.domain.SystemResourceInfo;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * This class is used by SystemResourceInfoRepository for converting from db record to SystemInfoResource entity
 *  when calling methods implemented in org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository
 */
@ReadingConverter
public class SystemResourceInfoRowConverter implements Converter<Row, SystemResourceInfo> {

    @Override
    public SystemResourceInfo convert(Row source) {
        SystemResourceInfo systemResourceInfo = new SystemResourceInfo();

        systemResourceInfo.setId(source.get("id", Long.class));
        systemResourceInfo.setClientName(source.get("client_name", String.class));
        systemResourceInfo.setCpuUsage(source.get("cpu_usage", Double.class));
        systemResourceInfo.setMemoryUsage(source.get("memory_usage", Long.class));
        systemResourceInfo.setOperatingSystem(source.get("operating_system", String.class));
        systemResourceInfo.setTotalMemory(source.get("total_memory", Long.class));
        systemResourceInfo.setFreeMemory(source.get("free_memory", Long.class));

        return systemResourceInfo;
    }
}