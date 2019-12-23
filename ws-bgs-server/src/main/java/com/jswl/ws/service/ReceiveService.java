package com.jswl.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * Created by wecan on 2019/12/19.
 */

@WebService(
        name = "receiveService", //暴露服务名称
        targetNamespace = "http://service.ws.jswl.com/" // 命名空间，包名倒叙
)
public interface ReceiveService {

//    public boolean receiveMessage(String name);

//    @WebResult(name = "return", targetNamespace = "")
//    @RequestWrapper(localName = "sayHello",
//            targetNamespace = "http://service.ws.jswl.com/",
//            className = "com.jswl.ws.service.SayHello")
//    @WebMethod(action = "urn:SayHello")
//    @ResponseWrapper(localName = "sayHelloResponse",
//            targetNamespace = "http://service.ws.jswl.com/",
//            className = "com.jswl.ws.service.SayHelloResponse")
    public String sayHello(@WebParam(name = "name", targetNamespace = "")String name);
}
