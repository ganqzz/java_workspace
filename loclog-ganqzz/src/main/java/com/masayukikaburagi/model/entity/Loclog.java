package com.masayukikaburagi.model.entity;

public class Loclog {
    private Integer logId; // log_id PK
    private String userName; // user_name
    private String tag; // tag
    private String address; // address
    private Double latitude; // latitude
    private Double longitude; // longitude
    private Long fixTime; // fix_time
    private String note; // note
    private String imageUri; // image_uri
    private String image; // image
    private String thumbnail; // thumbnail
    private Boolean open; // open
    private Long createdAt; // created_at
    private Long updatedAt; // updated_at

    /**
     * @return the logId
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * @param logId the logId to set
     */
    public void setLogId(int logId) {
        this.logId = logId;
    }

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
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the fixTime
     */
    public Long getFixTime() {
        return fixTime;
    }

    /**
     * @param fixTime the fixTime to set
     */
    public void setFixTime(long fixTime) {
        this.fixTime = fixTime;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the imageUri
     */
    public String getImageUri() {
        return imageUri;
    }

    /**
     * @param imageUri the imageUri to set
     */
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the open
     */
    public Boolean getOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(boolean open) {
        this.open = open;
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
        return String
            .format("logId: %d\tuserName: %s\ttag: %s\taddress: %s\tlatitude: %f\tlongitude: %f\t"
                    + "fixTime: %d\tnote: %s\timage: %s\topen: %b\tcreatedAt: %d\tupdatedAt: %d\t",
                    getLogId(), getUserName(), getTag(), getAddress(), getLatitude(),
                    getLongitude(), getFixTime(), getNote(), getImage(), getOpen(),
                    getCreatedAt(), getUpdatedAt());
    }
}
