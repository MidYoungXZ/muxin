package com.muxin.controller;

import cn.com.bsfit.frms.pre.StreamCubePreClient;
import com.muxin.pojo.PageReqVO;
import com.muxin.pojo.TestEvent;
import com.muxin.pojo.TestObject;
import com.muxin.service.BsfitGraphResponse;
import com.muxin.service.GDBDriverServiceApi;
import com.muxin.service.ITestService;
import com.muxin.service.TestEventPublisher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Projectname: muxin
 * @Filename: WebFluxController
 * @Author: yangxz
 * @Data:2023/7/27 15:01
 * @Description:
 */

@RestController
@RequestMapping(value = "/web")
@Tag(name = "WebController")
public class WebController {

    @Autowired
    private TestEventPublisher eventPublisher;

    @Autowired
    private GDBDriverServiceApi gdbDriverServiceApi;

    @Autowired(required = false)
    private ITestService iTestService;

    @Autowired
    private StreamCubePreClient streamCubePreClient;


    @Operation(summary = "demo")
    @GetMapping(value = "/demo")
    public String demo() {

//        AsOperateMessageDTO asOperateMessageDTO = new AsOperateMessageDTO();
//        asOperateMessageDTO.delete("memCachedKey");
//        MemCachedItem memCachedItem = new MemCachedItem();
//        asOperateMessageDTO.write();
//
//        streamCubePreClient.asyncPushMessage();


        return "success";
    }


    @Operation(summary = "testAPi")
    @GetMapping(value = "/testAPi")
    public String testAPi() {
        BsfitGraphResponse<Map> clusterPublic = gdbDriverServiceApi.getDriverInfo("CLUSTER_PUBLIC");
        System.out.println(clusterPublic);
        return "success";
    }

    @Operation(summary = "string")
    @GetMapping(value = "/string",params = {"name"})
    public String string(String name) {
        try {
            System.out.println("string:"+name);
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    @Operation(summary = "string2")
    @GetMapping(value = "/string")
    public String string2(String name) {
        try {
            if (iTestService!=null){
                iTestService.printYY();
            }else {
                System.out.println("iTestService is null");
            }
            System.out.println("string2:"+name);
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    @Operation(summary = "object")
    @PostMapping(value = "/object",params = {"event.name=1"})
    public String object(@RequestBody TestObject event) {
        System.out.println("object:"+event.getName());
        return "success";
    }

    @Operation(summary = "object2")
    @PostMapping(value = "/object")
    public String object2(@RequestBody TestObject event) {
        System.out.println("object2:"+event.getName());
        return "success";
    }



    @Operation(summary = "event")
    @GetMapping(value = "/event/{name}")
    public String event(@PathVariable String name) {
        eventPublisher.sendEvent(new TestEvent(name));
        return "success";
    }

    @Operation(summary = "list")
    @GetMapping(value = "/list")
    public String list(PageReqVO pageReqVO) {
        System.out.println(pageReqVO);
        return "success";
    }


}
