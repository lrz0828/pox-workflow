package com.sgai.pox.engine.vo;

import lombok.Data;

/**
 * @author pox
 */
@Data
public class CcToVo {
    private String userId;
    private String userName;

    @Override
    public String toString(){
        return String.format("%s[%s]",this.userName,this.userId);
    }
}
