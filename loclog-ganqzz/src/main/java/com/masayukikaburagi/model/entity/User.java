package com.masayukikaburagi.model.entity;

public class User {
    private String userName; // user_name PK
    private String password; // password
    private String apiKey; // api_key
    private String email; // email
    private Short role; // role
    private Long createdAt; // created_at
    private Long updatedAt; // updated_at

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param passwrod the password to set
     */
    public void setPassword(String passwrod) {
        this.password = passwrod;
    }

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey the apiKey to set
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the role
     */
    public Short getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Short role) {
        this.role = role;
    }

    /**
     * @return the createdAt
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString
    @Override
    public String toString() {
        // return
        // String.format("Id: %d\tName: %s\tCountry Code: %s\tDistrict: %s\tPopulation: %d\n");
        return super.toString();
    }
}
