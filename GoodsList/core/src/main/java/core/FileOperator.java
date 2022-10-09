package core;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class FileOperator {

    // Writes a user to the text-file
    public void writeUserToFile(String filename, String userInfo) {
        try (FileWriter fileWriter = new FileWriter(filename, true);) {
            File file = new File(filename);
            if(!file.exists() && !file.isDirectory()){
                throw new FileNotFoundException("Filen finnes ikke");
            }
            // Checks if the file is empty 
            if (file.length() == 0) {
                fileWriter.write(userInfo.toString());
            } else {
                fileWriter.write("\n" + userInfo.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
