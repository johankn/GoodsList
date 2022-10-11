package core;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileNotFoundException;

public class FileOperator {

    // Writes a user to the json-file
    public void writeUserDataToFile(String filename, LoginUser userInfo) throws FileNotFoundException {
        JSONObject jsonFileAsJsonObject;
        try {
            //Henter ut json-filen som JSONObject.
            jsonFileAsJsonObject = makeJsonObjectFromJsonFile(filename);
            //Legger til den nye brukeren i JSONobjektet
            JSONArray jsonArray = jsonFileAsJsonObject.getJSONArray("users");
            JSONObject userToBeAppended = new JSONObject();
            userToBeAppended.put("username", userInfo.getUsername());
            userToBeAppended.put("password", userInfo.getPassword());
            jsonArray.put(userToBeAppended);
            jsonFileAsJsonObject.put("users", jsonArray);
            JSONObject newJsonFileAsJsonObject = new JSONObject();
            newJsonFileAsJsonObject.put("users", jsonArray);

            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(newJsonFileAsJsonObject.toString());
            fileWriter.close();

            //Kode for å skrive til fil med objectMapper. Kan evt brukes senere.
            /* //Prøver å bruke ObjectMapper for å skrive til fil.
            final File file = new File(filename);
            ObjectMapper objectMapper = new ObjectMapper();
            // System.out.println(newJsonObject.toString());
            // String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newJsonObject);

            try (OutputStream outputStream = new FileOutputStream(file, true)){
                objectMapper.writeValue(outputStream, userInfo);
            }
            catch (final IOException e){
                e.printStackTrace();
            } */

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject makeJsonObjectFromJsonFile(String filename) throws JSONException, Exception {
        JSONObject jsonObject = new JSONObject(readFileAsString(filename));
        return jsonObject;
    }

    private static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public List<String> getAllUsersAsList(String filename){
        try {
            JSONObject jsonFileAsObject = makeJsonObjectFromJsonFile(filename);
            JSONArray usersAsJsonArray = jsonFileAsObject.getJSONArray("users");
            List<String> usersAsList = new ArrayList<>();
            for (int i = 0; i < usersAsJsonArray.length(); i++) {
                JSONObject userInfo = usersAsJsonArray.getJSONObject(i);
                usersAsList.add(userInfo.getString("username"));
                usersAsList.add(userInfo.getString("password"));
                usersAsList.add(userInfo.getString("fullname"));
            }
            return usersAsList;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }

    public static void main(String[] args) throws Exception {
        FileOperator f = new FileOperator();
        //System.out.println(f.makeJsonObjectFromJsonFile("GoodsList/core/src/main/java/json/dataObjects.json"));
        System.out.println(f.getAllUsersAsList("GoodsList/core/src/main/java/json/dataObjects.json"));
    }

}
