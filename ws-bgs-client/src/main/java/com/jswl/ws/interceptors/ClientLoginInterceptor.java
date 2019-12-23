package com.jswl.ws.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * Created by wecan on 2019/12/20.
 */
public class ClientLoginInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "111111";

    public ClientLoginInterceptor(){
        super(Phase.PREPARE_SEND);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers = message.getHeaders();
        Document document = DOMUtils.createDocument();
        Element authHeader = document.createElement("authHeader");

        Element userNameEle = document.createElement("userName");
        userNameEle.setTextContent(USER_NAME);
        authHeader.appendChild(userNameEle);

        Element passwordEle = document.createElement("password");
        passwordEle.setTextContent(PASSWORD);
        authHeader.appendChild(passwordEle);

        headers.add(new Header(new QName(""),authHeader));
    }
}
