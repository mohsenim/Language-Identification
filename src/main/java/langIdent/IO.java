package langIdent;

import java.io.*;

/**
 * Created by Mahdi on 7/13/2016.
 */
public class IO {

    public static String[] readLinesFromFile(String filePath) {
        String text = readFile(filePath);
        if (text == null) {
            return null;
        }
        return text.split("\r\n|\n");
    }

    public static String readFile(String filePath) {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            byte[] inputBytes = new byte[inputStream.available()];
            inputStream.read(inputBytes);
            inputStream.close();
            String text = new String(inputBytes, "UTF-8");
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeLinesToFile(String filePath, String[] lines) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
