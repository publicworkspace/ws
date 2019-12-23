package com.jswl.ws.service;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * Created by wecan on 2019/12/19.
 */

@WebService(
        serviceName = "receiveService", //web服务名称
//        portName = "receivePort",
        targetNamespace = "http://service.ws.jswl.com/", // 命名空间
        endpointInterface = "com.jswl.ws.service.ReceiveService" //接口全包名
)
@Component
public class ReceiveServiceImpl implements ReceiveService {
    @Override
    public String sayHello(String name) {

        return "Hello, Welcome to CXF Spring boot " + name + "!!!";
    }

//    @Override
//    public boolean receiveMessage(String name) {
//        System.out.println("name：" + name);
//        return false;
//    }
}
