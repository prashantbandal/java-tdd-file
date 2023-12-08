package org.hsbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ReverseFileContent {

    private static final Logger logger = LoggerFactory.getLogger(ReverseFileContent.class);

    /**
     * Reverse the file content.
     *
     * @param inputFile  input file
     * @param outputFile output file
     * @return output file with reverse content
     */
    public File reverseFileContent(File inputFile, File outputFile) {
        logger.info("Reversing the file content.");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            String line = br.readLine();
            if (null != line) {
                StringBuilder builder = new StringBuilder(line);
                builder.reverse();
                String reverse = builder.toString();
                bw.write(reverse);
            }
            logger.info("Reversed the file content.");
        } catch (IOException e) {
            logger.error(
                    "error while reading file in reverseFileContent {}",
                    e.getMessage());
        }
        return outputFile;
    }
}
