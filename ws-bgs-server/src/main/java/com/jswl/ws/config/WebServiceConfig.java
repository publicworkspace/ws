package com.jswl.ws.config;

import com.jswl.ws.interceptor.AuthInterceptor;
import com.jswl.ws.service.ReceiveService;
import org.apache.cxf.Bus;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.List;

@Configuration
public class WebServiceConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private ReceiveService receiveService;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus,receiveService);
        endpoint.publish("/receive");
        endpoint.getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getInInterceptors().add(new AuthInterceptor());
        return endpoint;
    }

//    /**
//     * 此方法作用是改变项目中服务名的前缀名，此处127.0.0.1或者localhost不能访问时，请使用ipconfig查看本机ip来访问
//     *      * 此方法被注释后:wsdl访问地址为http://127.0.0.1:8080/services/user?wsdl
//     *      * 去掉注释后：wsdl访问地址为：http://127.0.0.1:8080/soap/user?wsdl
//     *
//     * 注入servlet  bean name不能dispatcherServlet 否则会覆盖dispatcherServlet
//     * @return
//     */
//    @Bean(name = "cxfServlet")
//    public ServletRegistrationBean cxfServlet(){
//        //注册servlet 拦截/soap 开头的请求 不设置 默认为：/services/*
//        return new ServletRegistrationBean(new CXFServlet(),"/soap/*");
//    }

//    @Bean(name = Bus.DEFAULT_BUS_ID)
//    public SpringBus springBus(){
//        return new SpringBus();
//    }
}
