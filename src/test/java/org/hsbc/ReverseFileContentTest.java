package org.hsbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseFileContentTest {

    private static final Logger logger = LoggerFactory.getLogger(ReverseFileContentTest.class);
    Path inputFilepath, outputFilepath;
    File inputFile, outputFile;

    /* This directory and the files created in it will be deleted after
     * tests are run, even in the event of failures or exceptions.
     */
    @TempDir
    Path tempDir;
    ReverseFileContent reverseFileContent = new ReverseFileContent();

    @BeforeEach
    public void setUp() {
        try {
            inputFilepath = tempDir.resolve("input.txt");
            inputFile = inputFilepath.toFile();
            outputFilepath = tempDir.resolve("output.txt");
            outputFile = outputFilepath.toFile();
        } catch (InvalidPathException pathException) {
            logger.error(
                    "error creating temporary test file {} ",
                    pathException.getMessage());
        }
    }

    @Test
    void testReversFileContent() {
        try {
            logger.info("testReversFileContent");
            BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile));
            bw.write("ABC");
            bw.close();
            assertEquals(3L, inputFile.length());
            File rev = reverseFileContent.reverseFileContent(inputFile, outputFile);
            BufferedReader br = new BufferedReader(new FileReader(rev));
            assertEquals("CBA", br.readLine());
            br.close();
            inputFile.deleteOnExit();
            outputFile.deleteOnExit();
        } catch (IOException e) {
            logger.error(
                    "error in testReversFileContent {}",
                            e.getMessage());
        }
    }
}
