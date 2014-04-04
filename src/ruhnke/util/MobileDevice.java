package ruhnke.util;

/**
 *
 * @author jruhnke
 */
public class MobileDevice {

    private boolean deviceType;
    private String platform;
    private String countryCode;
    private String phoneNumber;
    private String carrier;
    private String serialNumber;

    /**
     *
     * @param deviceType
     */
    public void setDeviceType(boolean deviceType) {
        this.deviceType = deviceType;
    }

    /**
     *
     * @param platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     *
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @param carrier
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    /**
     *
     * @param serialNumber
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Get the value of serialNumber
     *
     * @return the value of serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Get the value of phoneNumber
     *
     * @return the value of phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get the value of countryCode
     *
     * @return the value of countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Get the value of platform
     *
     * @return the value of platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Get the value of deviceType
     *
     * @return the value of deviceType
     */
    public boolean getDeviceType() {
        return deviceType;
    }

    /**
     * Get the value of operator
     *
     * @return the value of operator
     */
    public String getCarrier() {
        return carrier;
    }

}
