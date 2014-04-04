package ruhnke;

import au.com.bytecode.opencsv.CSVWriter;
import com.sanityinc.jargs.CmdLineParser;
import com.sanityinc.jargs.CmdLineParser.Option;
import com.sanityinc.jargs.CmdLineParser.OptionException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import ruhnke.util.FakeMobileDevice;
import ruhnke.util.FakePerson;

/**
 *
 * @author jruhnke
 */
public class MobileIronGenerator {
    private static final String VERSION = "0.01";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CmdLineParser parser = new CmdLineParser();
        Option<Integer> numRecords = parser.addIntegerOption('n', "num");
        Option<String> outFile = parser.addStringOption('o', "out");
        Option<Boolean> help = parser.addBooleanOption('h', "help");

        try {
            parser.parse(args);
        } catch (OptionException e) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }
        
        if (parser.getOptionValue(help, false)) {
            printUsage();
            System.exit(0);
        }

        int numEntries = parser.getOptionValue(numRecords, 1000);
        String out = parser.getOptionValue(outFile, "output.csv");

        System.out.println("MobileIron Sample Data Generator v" + VERSION);
        System.out.println("\nEntries generating: " + numEntries + ", Outfile: " + out);
        List<String[]> records = genRecords(numEntries);

        outputToFile(records, out);
    }

    private static void outputToFile(List<String[]> records, String out) {
        FileWriter fileWriter = null;
        StringWriter writer = convertToCSV(records);

        try {
            File newTextFile = new File(out);
            fileWriter = new FileWriter(newTextFile);
            fileWriter.write(writer.toString());
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(MobileIronGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MobileIronGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static StringWriter convertToCSV(List<String[]> records) {
        StringWriter writer = new StringWriter();
        CSVWriter csvwriter = new CSVWriter(writer);
        csvwriter.writeAll(records);
        try {
            csvwriter.close();
        } catch (IOException ex) {
            Logger.getLogger(MobileIronGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return writer;
    }

    private static List<String[]> genRecords(int numEntries) {
        List<String[]> records = new ArrayList<>();
        System.out.print("Record: 0000000");
        for (int i = 0; i < numEntries; i++) {
            FakePerson person = new FakePerson();
            FakeMobileDevice device = new FakeMobileDevice();
            List<String> line = new ArrayList<>();
            line.add(person.getUserName());
            if (!device.getDeviceType()) {
                line.add(device.getCountryCode());
                line.add(device.getPhoneNumber());
                line.add(device.getCarrier());
            } else {
                line.add(null);
                line.add("PDA");
                line.add(null);
            }
            line.add(device.getPlatform());
            line.add(getRandonOwnership());
            line.add("L");
            line.add(person.getFirstName());
            line.add(person.getLastName());
            line.add(person.getEmail());
            line.add(getRandomPassword());
            line.add("en-US");
            line.add(person.getName());
            line.add("FALSE");
            line.add(device.getSerialNumber());
            String[] entry = new String[line.size()];
            line.toArray(entry);
            records.add(entry);
            System.out.print("\b\b\b\b\b\b\b");
            System.out.print(String.format("%07d", i + 1));
        }
        System.out.println("...Done.");
        return records;
    }

    private static String getRandonOwnership() {
        Random r = new Random();
        List<String> list = new ArrayList<>();
        list.add("E");
        list.add("C");
        int randomInt = r.nextInt(2);
        return list.get(randomInt);
    }

    private static String getRandomPassword() {
        return UUID.randomUUID().toString();
    }

    private static void printUsage() {
        System.err.println("usage: MobileIronGenerator [-h,--help] [-n,--num] numOfEntries [-o,--out] outputFile");
    }

}
