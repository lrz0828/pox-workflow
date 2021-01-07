package com.sgai.pox.admin.security.component.authorization;

import com.sgai.pox.admin.security.component.resource.AuthorizeRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PoxSecurityProperties {
    private List<AuthorizeRequest> authorizeRequests = new ArrayList<>();
}
