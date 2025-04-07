package pizza;
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PizzaShopGUI{
    private static int totalCost = 10;
    private static ArrayList<String> ingredients = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lucky Pizza Ordering System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 700);

            // Главная панель с цветным фоном
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null); 
            mainPanel.setBackground(new Color(255, 239, 213)); // Цвет "теплого крема"
            frame.add(mainPanel);

            // Лейблы и поля для данных пользователя с обновленным дизайном
            JLabel nameLabel = new JLabel("Имя:");
            nameLabel.setBounds(50, 20, 100, 30);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            mainPanel.add(nameLabel);

            JTextField nameField = new JTextField();
            nameField.setBounds(150, 20, 350, 30);
            nameField.setFont(new Font("Arial", Font.PLAIN, 14));
            mainPanel.add(nameField);

            JLabel surnameLabel = new JLabel("Фамилия:");
            surnameLabel.setBounds(50, 60, 100, 30);
            surnameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            mainPanel.add(surnameLabel);

            JTextField surnameField = new JTextField();
            surnameField.setBounds(150, 60, 350, 30);
            surnameField.setFont(new Font("Arial", Font.PLAIN, 14));
            mainPanel.add(surnameField);

            JLabel addressLabel = new JLabel("Адрес:");
            addressLabel.setBounds(50, 100, 100, 30);
            addressLabel.setFont(new Font("Arial", Font.BOLD, 14));
            mainPanel.add(addressLabel);

            JTextField addressField = new JTextField();
            addressField.setBounds(150, 100, 350, 30);
            addressField.setFont(new Font("Arial", Font.PLAIN, 14));
            mainPanel.add(addressField);

            // Выпадающий список для выбора пиццы с дизайном
            JLabel pizzaLabel = new JLabel("Выберите пиццу:");
            pizzaLabel.setBounds(50, 160, 200, 30);
            pizzaLabel.setFont(new Font("Arial", Font.BOLD, 14));
            mainPanel.add(pizzaLabel);

            String[] pizzas = {"Маргарита", "Пепперони", "Четыре сыра", "Мясная"};
            JComboBox<String> pizzaBox = new JComboBox<>(pizzas);
            pizzaBox.setBounds(50, 200, 300, 30);
            pizzaBox.setFont(new Font("Arial", Font.PLAIN, 14));
            pizzaBox.setBackground(Color.WHITE);
            mainPanel.add(pizzaBox);

            // Кнопка для добавления ингредиентов
            JButton addIngredientButton = new JButton("Добавить ингредиент");
            addIngredientButton.setBounds(50, 250, 200, 40);
            addIngredientButton.setFont(new Font("Arial", Font.BOLD, 14));
            addIngredientButton.setBackground(new Color(144, 238, 144)); // Цвет "светло-зеленый"
            addIngredientButton.addActionListener(e -> {
                String ingredient = JOptionPane.showInputDialog("Введите ингредиент:");
                if (ingredient != null && !ingredient.isEmpty()) {
                    ingredients.add(ingredient);
                    totalCost += 2;
                    JOptionPane.showMessageDialog(frame, "Ингредиент добавлен: " + ingredient + "\nСтоимость: " + totalCost + " EUR");
                }
            });
            mainPanel.add(addIngredientButton);

            // Кнопка завершения заказа с дизайном
            JButton finishOrderButton = new JButton("Завершить заказ");
            finishOrderButton.setBounds(50, 300, 200, 40);
            finishOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
            finishOrderButton.setBackground(new Color(255, 182, 193)); // Цвет "розовый"
            finishOrderButton.addActionListener(e -> {
                String userData = "Имя: " + nameField.getText() + ", Фамилия: " +
                        surnameField.getText() + ", Адрес: " + addressField.getText();
                
                String pizzaChoice = (String) pizzaBox.getSelectedItem();
                
                StringBuilder receipt = new StringBuilder();
                receipt.append(userData).append("\n");
                receipt.append("Пицца: ").append(pizzaChoice).append("\n");
                receipt.append("Ингредиенты: ").append(ingredients).append("\n");
                receipt.append("Общая стоимость: ").append(totalCost).append(" EUR");
                
                saveToFile("receipt.txt", receipt.toString());
                JOptionPane.showMessageDialog(frame, "Ваш заказ оформлен!\n" + receipt);
            });
            mainPanel.add(finishOrderButton);

            // Кнопка выхода
            JButton exitButton = new JButton("Выход");
            exitButton.setBounds(50, 350, 200, 40);
            exitButton.setFont(new Font("Arial", Font.BOLD, 14));
            exitButton.setBackground(new Color(240, 128, 128)); // Цвет "светло-красный"
            exitButton.addActionListener(e -> System.exit(0));
            mainPanel.add(exitButton);

            frame.setVisible(true);
        });
    }

    private static void saveToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
