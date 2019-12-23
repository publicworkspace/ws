package com.jswl.ws.interceptor;

import cn.hutool.core.util.StrUtil;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import java.util.List;

/**
 * Created by wecan on 2019/12/20.
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "111111";

    public AuthInterceptor() {
//        super(Phase.PRE_STREAM);
        super(Phase.USER_LOGICAL);
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        List<Header> headers = null;
        String username = null;
        String password = null;
        try {
            headers = soapMessage.getHeaders();
        } catch (Exception e) {
            logger.error("getSOAPHeader error: {}", e.getMessage(), e);
        }

        if (null == headers || headers.size() == 0) {
            throw new Fault(new IllegalArgumentException("找不到Header，无法验证用户信息"));
        }
        //获取用户名,密码
        for (Header header : headers) {
            SoapHeader soapHeader = (SoapHeader) header;
            Element e = (Element) soapHeader.getObject();
            NodeList userNameNode = e.getElementsByTagName("userName");
            NodeList pwdNode = e.getElementsByTagName("password");
            username = userNameNode.item(0).getTextContent();
            password = pwdNode.item(0).getTextContent();
            if (StrUtil.hasBlank(username) || StrUtil.hasBlank(password)) {
                throw new Fault(new IllegalArgumentException("用户信息为空"));
            }
        }
        //校验用户名密码
        if (!(username.equals(USER_NAME) && password.equals(PASSWORD))) {
            SOAPException soapExc = new SOAPException("认证失败");
            logger.debug("用户认证信息错误");
            throw new Fault(soapExc);
        }
    }
}
