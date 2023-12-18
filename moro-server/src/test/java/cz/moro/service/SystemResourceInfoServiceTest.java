package cz.moro.service;

import cz.moro.Main;
import cz.moro.api.dto.SystemResourceInfoDto;
import cz.moro.domain.SystemResourceInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@SpringBootTest(classes = {Main.class})
class SystemResourceInfoServiceTest {


    @Autowired
    SystemResourceInfoService service;

    @BeforeEach
    void insertData() {
        SystemResourceInfo info = new SystemResourceInfo();
        info.setClientName("test client");
        info.setCpuUsage(95.2);
        info.setFreeMemory(10000);
        service.save(info).block();
    }

    @Test
    void getFirstTest() {
        SystemResourceInfoDto info = service.findAllInfos().blockFirst();
        Assertions.assertNotNull(info);
        Assertions.assertEquals(95.2, info.getCpuUsage());

    }
}
