/**
 * PROGRAM DESCRIPTION: TO CREATE A HOTEL BOOKING SYSTEM
 *
 * HANIFF & HAZIQ
 * 28 MARCH 2024
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookingFrame extends JFrame {
    private JTextField nameTextField;
    private JComboBox<String> roomTypeComboBox;
    private JTextField dateTextField;
    private JComboBox<String> mealSetComboBox;

    public BookingFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Booking");
        setSize(600, 500); // Adjusted size for better layout
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Initialize components
        initializeComponents(gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this::onSubmit);
        addComponent(submitButton, gbc);
    }

    private void initializeComponents(GridBagConstraints gbc) {
        nameTextField = new JTextField(20);
        roomTypeComboBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        dateTextField = new JTextField(20);
        mealSetComboBox = new JComboBox<>(new String[]{"Standard", "Vegetarian", "Vegan", "Gluten-Free"});

        addComponent(new JLabel("Name:"), gbc);
        addComponent(nameTextField, gbc);

        addComponent(new JLabel("Room Type:"), gbc);
        addComponent(roomTypeComboBox, gbc);

        addComponent(new JLabel("Date (dd/MM/yyyy):"), gbc);
        addComponent(dateTextField, gbc);

        addComponent(new JLabel("Meal Set:"), gbc);
        addComponent(mealSetComboBox, gbc);
    }

    private void addComponent(Component component, GridBagConstraints gbc) {
        getContentPane().add(component, gbc);
    }

    private void onSubmit(ActionEvent e) {
        String name = nameTextField.getText();
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        String dateString = dateTextField.getText();
        String mealSet = (String) mealSetComboBox.getSelectedItem();

        submitBooking(name, roomType, dateString, mealSet);
    }

    private void submitBooking(String name, String roomType, String dateString, String mealSet) {
        if (name.isEmpty() || dateString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String bookingDetails = String.format("Booking Successful!\nName: %s\nRoom Type: %s\nDate: %s\nMeal Set: %s", name, roomType, date, mealSet);
        JOptionPane.showMessageDialog(this, bookingDetails, "Success", JOptionPane.INFORMATION_MESSAGE);

        // Proceed to payment
        EventQueue.invokeLater(() -> {
                    PaymentFrame paymentFrame = new PaymentFrame(bookingDetails);
                    paymentFrame.setVisible(true);
            });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new BookingFrame().setVisible(true));
    }
}