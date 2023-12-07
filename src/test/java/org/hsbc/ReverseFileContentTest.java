package org.hsbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseFileContentTest {

    Path path, path2;
    File file, outputFile;

    /* This directory and the files created in it will be deleted after
     * tests are run, even in the event of failures or exceptions.
     */
    @TempDir
    Path tempDir;
    ReverseFileContent reverseFileContent = new ReverseFileContent();

    @BeforeEach
    public void setUp() {
        try {
            path = tempDir.resolve("input.txt");
            file = path.toFile();
            path2 = tempDir.resolve("output.txt");
            outputFile = path2.toFile();
            new FileWriter(file, false).close();
            new FileWriter(outputFile, false).close();
        } catch (InvalidPathException | IOException pathException) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }
    }

    @Test
    void testReversFileContent() {
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(file));
            bw1.write("ABC");
            bw1.close();
            assertEquals(3L, file.length());
            String rev = reverseFileContent.reverseFileContent(file);
            assertEquals("CBA", rev);
            file.deleteOnExit();
        } catch (IOException e) {
            System.err.println(
                    "error in testReversFileContent " +
                            this.getClass().getSimpleName());
        }
    }

    @Test
    void testReversEmptyFileContent() {
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(file));
            bw1.write("");
            bw1.close();
            assertEquals(0L, file.length());
            String rev = reverseFileContent.reverseFileContent(file);
            assertEquals("", rev);
            file.deleteOnExit();
        } catch (IOException e) {
            System.err.println(
                    "error in testReversEmptyFileContent " +
                            this.getClass().getSimpleName());
        }
    }

}
