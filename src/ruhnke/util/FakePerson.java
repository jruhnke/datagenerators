package ruhnke.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jruhnke
 */
public class FakePerson extends Person {


    public FakePerson() {
        this.setFirstName(getRandomLine("/ruhnke/util/firstnames.txt"));
        this.setLastName(getRandomLine("/ruhnke/util/lastnames.txt"));
        this.setMiddleInitial(getRandomChar().toUpperCase());
        this.setName(genDisplayName(this.getFirstName(), this.getMiddleInitial(), this.getLastName()));
        if ((this.getMiddleInitial() != null) || (!this.getMiddleInitial().equalsIgnoreCase(" "))) {
            this.setUserName(this.getFirstName().charAt(0) + this.getMiddleInitial() + this.getLastName().substring(0, Math.min(6, this.getLastName().length())));
        } else {
            this.setUserName(this.getFirstName().charAt(0) + this.getLastName().substring(0, Math.min(7, this.getLastName().length())));
        }
        this.setEmail(this.getName().replace(" ", ".").toLowerCase() + "@nomail.mil");
    }

    private String getRandomLine(String fileName) {
        Reader fr = null;
        String line = null;
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream(fileName);
            //URL url = getClass().getResource(fileName);
            //System.out.println(url.getFile());
            fr = new InputStreamReader(in);

            int lineCount = getMaxLineCount(fileName);
            Random random = new Random();
            int randomInt = random.nextInt(lineCount);

            BufferedReader reader = new BufferedReader(fr);
            line = reader.readLine();
            for (int i = 0; i < randomInt + 1; i++) {
                line = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FakePerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FakePerson.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FakePerson.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(FakePerson.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return line;
    }

    private String genDisplayName(String firstName, String middleName, String lastName) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(" ");
        if (!middleName.equalsIgnoreCase(" ")) {
            sb.append(middleName).append(" ").append(lastName);
        } else {
            sb.append(lastName);
        }
        return sb.toString();
    }

    private String getRandomChar() {
        String s;
        Random r = new Random();
        char symbel = (char) (r.nextInt(26) + 'a');
        if (symbel >= 'a' && symbel <= 'z') {
            s = String.valueOf(symbel);
        } else {
            s = " ";
        }
        return s;
    }

    private int getMaxLineCount(String file) {
        int lineCount = 0;
        InputStream in = getClass().getResourceAsStream(file);
        LineNumberReader lnr = null;
        try {
            lnr = new LineNumberReader(new InputStreamReader(in));
            lnr.skip(Long.MAX_VALUE);
            lineCount = lnr.getLineNumber() - 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FakePerson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FakePerson.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (lnr != null) {
                try {
                    lnr.close();
                } catch (IOException ex) {
                    Logger.getLogger(FakePerson.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(FakePerson.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lineCount;
    }
}
