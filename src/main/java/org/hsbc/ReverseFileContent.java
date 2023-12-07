package org.hsbc;

import java.io.*;

public class ReverseFileContent {
    /**
     * Reverse file content.
     *
     * @param inputFile input file
     * @return output file
     */
    public String reverseFileContent(File inputFile) {
        String reverse = "";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = br.readLine();
            if(null != line) {
                StringBuilder builder = new StringBuilder(line);
                builder.reverse();
                reverse = builder.toString();
            }
        } catch (IOException e) {
            System.err.println(
                    "error reading file in " +
                            this.getClass().getSimpleName());
        }
        return reverse;
    }
}
