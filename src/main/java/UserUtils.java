import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class UserUtils {

//    READ USERS
    public static JSONArray getAllUsers() {
        JSONParser jsonParser = new JSONParser();
        JSONArray usersArr = new JSONArray();

        try (FileReader reader = new FileReader("users.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray listUsers = (JSONArray) obj;

            for (int i = 0; i < listUsers.size(); i++) {
                JSONObject parseValue = parseUserObject((JSONObject) listUsers.get(i));
                usersArr.add(parseValue);
            }
            return usersArr;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return usersArr;
    }

//    CREATE USER
    public static void createUser(User user) {
        JSONArray users = getAllUsers();
        JSONObject wrapper = new JSONObject();

        wrapper.put("login", user.login);
        wrapper.put("password", user.password);

        users.add(wrapper);

        try (FileWriter file = new FileWriter("users.json")) {
            file.write(users.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    DELETE USER
    public static void deleteUser() {
        JSONArray users = getAllUsers();
        String usersString = "Digite o Index do usuário que deseja remover.\n";

        for (int i = 0; i < users.size(); i++) {
            usersString += String.valueOf(i) + users.get(i) + "\n";
        }

//      TENTA DELETAR O USUÁRIO A PARTIR DO INDEX
        try {
            int userIndex = Integer.parseInt(JOptionPane.showInputDialog(null, usersString, "SYSTEM", JOptionPane.INFORMATION_MESSAGE));
            users.remove(userIndex);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

//      TENTA ESCREVER OU SOBRE ESCREVER NO JSONArray
        try (FileWriter file = new FileWriter("users.json")) {
            file.write(users.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    UPDATE USER
    public static void updateUser() {
        JSONArray users = getAllUsers();

        String usersString = "Enter the user Index you wnat to change.\n\n";

        for (int i = 0; i < users.size(); i++) {
            usersString += String.valueOf(i) + users.get(i) + "\n";
        }

        int userIndex = Integer.parseInt(JOptionPane.showInputDialog(null, usersString, "SYSTEM", JOptionPane.INFORMATION_MESSAGE));

        JSONObject userData = (JSONObject) users.get(userIndex);

        String userLoginInput = JOptionPane.showInputDialog(null, "Digite o Login");
        String userPasswordInput = JOptionPane.showInputDialog(null, "Digite a Senha");

        userData.put("login", userLoginInput);
        userData.put("password", userPasswordInput);

        users.set(userIndex, userData);

        try (FileWriter file = new FileWriter("users.json")) {
            file.write(users.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject parseUserObject(JSONObject user) {
        String loginField = (String) user.get("login");
        String passwordField = (String) user.get("password");

        JSONObject userData = new JSONObject();
        userData.put("login", loginField);
        userData.put("password", passwordField);

        return userData;
    }
}
