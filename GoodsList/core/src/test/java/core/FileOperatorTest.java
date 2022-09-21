package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileOperatorTest {
    
    @Test
    @DisplayName("Test if writeUserToFile writes users to our test file")
    public void testFileOperator(){
        FileOperator fileOperator = new FileOperator();
        File testfile = new File("Testfile.txt");
        fileOperator.writeUserToFile("Testfile.txt", "testUsername;testPassword");
        fileOperator.writeUserToFile("Testfile.txt", "testUsername2;testPassword2");
        try(Stream<String> list = Files.lines(Paths.get("Testfile.txt"));){
            String users = list.collect(Collectors.joining(" "));
            Assertions.assertEquals("testUsername;testPassword testUsername2;testPassword2", users);
        }
    
        catch (IOException e) {
            e.printStackTrace();
        }
        testfile.delete();
    }
}

