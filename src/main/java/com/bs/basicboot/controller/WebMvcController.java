package com.bs.basicboot.controller;

import com.bs.basicboot.common.config.properties.MyDataProperties;
import com.bs.basicboot.model.dto.Demo;
import com.bs.basicboot.model.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class WebMvcController {

    private final DemoService demoService;
    @Value("${mydata.test}")
    private String mydata;
    @Value("${linux.home}")
    private String path;
    @Value("${linux.url}")
    private String ip;

//    @Value("${os.javahome}")
//    private String javahome;

    @Value("${os.path}")
    private String envpath;


    private final MyDataProperties myproperties;
    @RequestMapping("/")
    public String index(){
        System.out.println("프로퍼티 값 가져오기 : "+mydata);
        System.out.println("서버경로 : "+path);
        System.out.println("서버ip : "+ip);
//        System.out.println("서버ip : "+javahome);
        System.out.println("환경경로 : "+envpath);
        System.out.println("myip: "+myproperties.getIp());
        System.out.println("myip: "+myproperties.getPort());
        return "index";
    }

    @RequestMapping("/demo/demolist")
    public String demoList(Model model){
        List<Demo> result = demoService.findAll();
        model.addAttribute("demo",result);
        log.info("demo조회결과: {}",result);

        return "demo/demolist";
    }

}
