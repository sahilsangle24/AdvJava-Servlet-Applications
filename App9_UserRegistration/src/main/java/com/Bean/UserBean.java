package com.Bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String USERNAME;
    private String PASSWORD;
    private String FIRSTNAME;
    private String LASTNAME;
    private String MAILID;
    private String PHONE;

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String uSERNAME) {
        USERNAME = uSERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String pASSWORD) {
        PASSWORD = pASSWORD;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String fIRSTNAME) {
        FIRSTNAME = fIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String lASTNAME) {
        LASTNAME = lASTNAME;
    }

    public String getMAILID() {
        return MAILID;
    }

    public void setMAILID(String mAILID) {
        MAILID = mAILID;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String pHONE) {
        PHONE = pHONE;
    }
}