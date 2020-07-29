package org.modmacao.core.connector;


public enum StatusType{
    DEPLOY("deploy"), UNDEPLOY("undeploy"), START("start"), STOP("stop"), CONFIGURE("configure");
	
    private String value;

    public String getResponse() {
        return value;
    }

    StatusType(String value){
        this.value = value;
    }
}
