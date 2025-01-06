package com.beyondtech.studenttvpss.model;

public class School {
    private Long id;
    private String name;
    private String code;
    private String address1;
    private String address2;
    private String postcode;
    private String district;
    private String state;
    private String phoneNumber;
    private String email;
    private String fax;
    private String principalName;

    public School() {}
    public School(Long id, String name, String code, String address1, String address2, String postcode, String district, String state, String phoneNumber, String email, String fax, String principalName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.address1 = address1;
        this.address2 = address2;
        this.postcode = postcode;
        this.district = district;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.fax = fax;
        this.principalName = principalName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }


}
