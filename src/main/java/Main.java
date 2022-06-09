import org.json.simple.JSONArray;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        FileUtils file = new FileUtils();
        boolean hasNext = true;
        int opUser;

//      CREATE START LOG
        file.createTxt();

//      CREATE USERS FILE
        file.createFile("users", ".json");
        file.createFile("storage", ".json");

        ArrayList<User> users = (ArrayList<User>) UserUtils.getAllUsers();
        String login = JOptionPane.showInputDialog(null, "Login: ", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
        String password = JOptionPane.showInputDialog(null, "Password: ", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

        for (User user : users) {
            if (login.equals(user.login) || password.equals(user.password)) {
                while (hasNext) {
                    try {
                        file.writeFile("Open main menu");
                        opUser = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                Choose One.
                                1 - User Options.
                                2 - Storage Options.
                                0 - Exit."""));
                        switch (opUser) {
                            case 1 -> {
                                while (hasNext) {
                                    file.writeFile("Opened user submenu");
                                    opUser = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                            Choose an Option.
                                            1 - See All Users.
                                            2 - Create User.
                                            3 - Delete User.
                                            4 - Update User.
                                            0 - Exit.""", "SYSTEM", JOptionPane.INFORMATION_MESSAGE));
                                    switch (opUser) {
                                        case 1 -> {
                                            file.writeFile("\nSelected 'See All Users' | option" + opUser + ".");
                                            JSONArray listUsers = UserUtils.getAllUsers();
                                            String usersMessageList = "Users Availables.\n\n";
                                            for (int i = 0; i < listUsers.size(); i++) {
                                                usersMessageList += String.valueOf(i) + listUsers.get(i) + "\n";
                                            }
                                            JOptionPane.showMessageDialog(null, usersMessageList, "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
                                            file.writeFile("Closed 'See All Users' option.\n");
                                        }
                                        case 2 -> {
                                            file.writeFile("Selected 'Create User' | option" + opUser + ".");
                                            String loginInput = JOptionPane.showInputDialog(null, "Insert Login", "System", JOptionPane.INFORMATION_MESSAGE);
                                            String passwordInput = JOptionPane.showInputDialog(null, "Insert Password", "System", JOptionPane.INFORMATION_MESSAGE);
                                            UserUtils.createUser(new User(loginInput, passwordInput));
                                            file.writeFile("Created a user");
                                            file.writeFile("Closed 'Create User' option.\n");
                                        }
                                        case 3 -> {
                                            file.writeFile("Selected 'Delete User' | option" + opUser + ".");
                                            UserUtils.deleteUser();
                                            file.writeFile("Deleted User");
                                            file.writeFile("Closed 'Delete User' option.\n");
                                        }
                                        case 4 -> {
                                            file.writeFile("Selected 'Update User' | option" + opUser + ".");
                                            UserUtils.updateUser();
                                            file.writeFile("Updated User");
                                            file.writeFile("Closed 'Update User' option.\n");
                                        }
                                        case 0 -> {
                                            file.writeFile("Selected 'Exit' | option" + opUser + ".");
                                            hasNext = false;
                                        }
                                    }
                                }
                                hasNext = true;
                            }
                            case 2 -> {
                                while (hasNext) {
                                    file.writeFile("Opened storage submenu");
                                    opUser = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                            Choose an Option.
                                            1 - View all Products.
                                            2 - Add Product.
                                            3 - Delete Product.
                                            4 - Update Product Data.
                                            0 - Exit.""", "SYSTEM", JOptionPane.INFORMATION_MESSAGE));
                                    switch (opUser) {
                                        case 1 -> {
                                            file.writeFile("\nSelected 'View All Users' | option" + opUser + ".");
                                            Estoque.getAllItems();
                                            JSONArray itemsList = Estoque.getAllItems();
                                            String itemsDataList = "Products Availables.\n\n";
                                            for (int i = 0; i < itemsList.size(); i++) {
                                                itemsDataList += String.valueOf(i) + itemsList.get(i) + "\n";
                                            }
                                            JOptionPane.showMessageDialog(null, itemsDataList, "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
                                            file.writeFile("Closed 'View All Users' option.\n");
                                        }
                                        case 2 -> {
                                            file.writeFile("Selected 'Add Product' | option" + opUser + ".");
                                            String name = JOptionPane.showInputDialog(null, "Insert Product Name", "System", JOptionPane.INFORMATION_MESSAGE);
                                            double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Insert Product Price", "System", JOptionPane.INFORMATION_MESSAGE));
                                            String description = JOptionPane.showInputDialog(null, "Insert Product Description", "System", JOptionPane.INFORMATION_MESSAGE);
                                            Estoque.addItem(new Item(name, price, description));
                                            file.writeFile("Created a item in storage");
                                            file.writeFile("Closed 'Add Product' option.\n");
                                        }
                                        case 3 -> {
                                            file.writeFile("Selected 'Delete Product' | option" + opUser + ".");
                                            Estoque.deleteItem();
                                            file.writeFile("Deleted Product");
                                            file.writeFile("Closed 'Delete Product' option.\n");
                                        }
                                        case 4 -> {
                                            file.writeFile("Selected 'Update Product Data' | option" + opUser + ".");
                                            Estoque.updateItem();
                                            file.writeFile("Updated item in storage.");
                                            file.writeFile("Closed 'Update Product Data' option.\n");
                                        }
                                        case 0 -> hasNext = false;
                                    }
                                }
                                hasNext = true;
                            }
                            default -> {
                                hasNext = false;
                                file.writeFile("Program Exited at: ");
                            }
                        }
                    } catch (NumberFormatException cancelOption) {
                        file.writeFile("Closed by pressing 'Cancel' option.");
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "LOGIN INVÁLIDO");
            }
        }
    }
}
