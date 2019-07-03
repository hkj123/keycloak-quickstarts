package org.keycloak.quickstart.springboot.util;

import java.io.Serializable;

/**
 *@System: 车贷金融
 *@Auther: hukaijia
 *@Description: 返回封装对象
 *@Modified By:
*/
public class Result implements Serializable {
    public enum ReturnValue {
        SUCCESS, FAILURE
    }

    private String returnValue;
    private String reason;
    private Object object;
    public Result(){

    }
    public Result(ReturnValue returnValue, String reason) {
        this.returnValue = returnValue.name();
        this.reason = reason;
    }
    public Result(ReturnValue returnValue, String reason, Object  object) {
        this.returnValue = returnValue.name();
        this.reason = reason;
        this.object=object;
    }

    public Boolean isSuccess(){
        return "SUCCESS".equals(getReturnValue());
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Result{" +
                "returnValue='" + returnValue + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
