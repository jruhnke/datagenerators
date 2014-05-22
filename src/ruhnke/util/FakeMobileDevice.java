package ruhnke.util;

import java.util.Random;

/**
 *
 * @author jruhnke
 */
public class FakeMobileDevice extends MobileDevice {

    public FakeMobileDevice() {
        this.setDeviceType(isPDA());
        this.setPlatform(getRandomMobileOS());
        this.setCountryCode("1");
        this.setPhoneNumber(getRandomPhone());
        this.setCarrier(getRandomCarrier());
        this.setSerialNumber(getRandomSerialNumber());
    }

    private boolean isPDA() {
        Random r = new Random();
        boolean x = false;
        int randomInt = r.nextInt(9);
        if (randomInt == 6) {
            x = true;
        }
        return x;
    }

    private String getRandomMobileOS() {
        /**
         * Mobile OS platforms
         * A = Android
         * I = iOS
         * L = OSX
         * M = Windows Phone 8
         */
        String[] vendor = {"A", "I", "L", "M"};
        Random r = new Random();
        int randomInt = r.nextInt(vendor.length);
        return vendor[randomInt];
    }

    private String getRandomPhone() {
        Random r = new Random();

        int i1 = r.nextInt(6) + 2;
        int i2 = r.nextInt(7) + 1;
        int i3 = r.nextInt(7) + 1;
        int i4 = r.nextInt(742); // returns random number between 0 and 741
        int i5 = r.nextInt(10000); // returns random number between 0 and 9999

        return String.format("%d%d%d%03d%04d", i1, i2, i3, i4, i5);
    }

    private String getRandomCarrier() {
        String[] telco = {"Verizon", "AT&T", "Sprint", "T-Mobile", "Virgin Mobile", "US Cellular", "Metro Southwest"};
        Random r = new Random();
        int randomInt = r.nextInt(telco.length);
        return telco[randomInt];
    }

    private String getRandomSerialNumber() {
        Random r = new Random();

        int i1 = r.nextInt(10000);
        int i2 = r.nextInt(10000);
        int i3 = r.nextInt(10000);
        int i4 = r.nextInt(10000);

        return String.format("%04d-%04d-%04d-%04d", i1, i2, i3, i4);
    }
}
