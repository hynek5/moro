package cz.moro.web;

import cz.moro.api.dto.SystemResourceInfoDto;
import cz.moro.service.SystemResourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

@Controller
public class SystemResourceController  {
    private final SystemResourceInfoService service;
    @Autowired
    public SystemResourceController(SystemResourceInfoService service) {
        this.service = service;
    }

    @GetMapping(value = "/systemResource")
    public String getSystemResource(Model model) {
        Flux<SystemResourceInfoDto> postList = service.findAllInfos();
        model.addAttribute("systemInfos", new ReactiveDataDriverContextVariable(postList, 100));
        return "systemResource";
    }
}
