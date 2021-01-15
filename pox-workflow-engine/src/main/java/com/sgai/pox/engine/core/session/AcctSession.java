package com.sgai.pox.engine.core.session;

import com.sun.javafx.fxml.BeanAdapter;
import lombok.Data;

import java.util.Map;

/**
 * 管理员session
 * @Auther: pox
 */
@Data
public class AcctSession {


    private String sessionId;

    private String userId;

    private String userRealName;

    private String orgId;

    private Map<String, Object> additionalInformation;


}
