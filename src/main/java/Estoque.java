import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Estoque {

    //    READ USERS
    public static JSONArray getAllItems() {
        JSONParser jsonParser = new JSONParser();
        JSONArray storageItemsArr = new JSONArray();

        try (FileReader reader = new FileReader("storage.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray listItems = (JSONArray) obj;

            int count = 0;
            for (int i = 0; i < listItems.size(); i++) {
                JSONObject parseValue = parseStorageObject((JSONObject) listItems.get(i));
                storageItemsArr.add(parseValue);
            }
            return storageItemsArr;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return storageItemsArr;
    }

    //    CREATE USER
    public static void addItem(Item item) {
        JSONArray items = getAllItems();
        JSONObject wrapper = new JSONObject();

        wrapper.put("login", item.name);
        wrapper.put("price", item.price);
        wrapper.put("description", item.description);

        items.add(wrapper);

        try (FileWriter file = new FileWriter("storage.json")) {
            file.write(items.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    DELETE USER
    public static void deleteItem() {
        JSONArray items = getAllItems();
        String itemsString = "Enter index you want to remove.\n";

        for (int i = 0; i < items.size(); i++) {
            itemsString += String.valueOf(i) + items.get(i) + "\n";
        }

//      TENTA DELETAR O USUÁRIO A PARTIR DO INDEX
        try {
            int itemIndex = Integer.parseInt(JOptionPane.showInputDialog(null, itemsString, "SYSTEM", JOptionPane.INFORMATION_MESSAGE));
            items.remove(itemIndex);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

//      TENTA ESCREVER OU SOBRE ESCREVER NO JSONArray
        try (FileWriter file = new FileWriter("storage.json")) {
            file.write(items.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    UPDATE USER
    public static void updateItem() {
        JSONArray items = getAllItems();

        String itemsString = "Enter the index you want to change.\n\n";

        for (int i = 0; i < items.size(); i++) {
            itemsString += String.valueOf(i) + items.get(i) + "\n";
        }

        int userIndex = Integer.parseInt(JOptionPane.showInputDialog(null, itemsString, "SYSTEM", JOptionPane.INFORMATION_MESSAGE));

        JSONObject itemData = (JSONObject) items.get(userIndex);

        String itemNameInput = JOptionPane.showInputDialog(null, "Enter Product Name", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
        String itemPriceInput = JOptionPane.showInputDialog(null, "Enter Product Price", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
        String itemDescriptionInput = JOptionPane.showInputDialog(null, "Enter Product Description", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

        if (itemDescriptionInput == null) {
            itemDescriptionInput = "";
        } else if (itemNameInput == null){
            itemNameInput = "";
        } else if (itemPriceInput == null) {
            itemPriceInput = "";
        }

        itemData.put("name", itemNameInput);
        itemData.put("price", itemPriceInput);
        itemData.put("description", itemDescriptionInput);

        items.set(userIndex, itemData);

        try (FileWriter file = new FileWriter("storage.json")) {
            file.write(items.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject parseStorageObject(JSONObject item) {
        String nameField = (String) item.get("name");
        String priceField = String.valueOf(item.get("price"));
        String descriptionField = (String) item.get("description");

        JSONObject itemData = new JSONObject();
        itemData.put("name", nameField);
        itemData.put("price", priceField);
        itemData.put("desciption", descriptionField);

        return itemData;
    }


}
