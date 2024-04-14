/**
 * PROGRAM DESCRIPTION: TO CREATE A PAYMENT USING CREDIT/DEBIT CARD
 *
 * HANIFF & HAZIQ
 * 28 MARCH 2024
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PaymentFrame extends JFrame {
    public PaymentFrame(String bookingDetails) {
        initUI(bookingDetails);
    }

    private void initUI(String bookingDetails) {
        setTitle("Payment");
        setSize(400, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Card Number
        addComponent(new JLabel("Card Number:"), gbc);
        JTextField cardNumberField = new JTextField(20);
        addComponent(cardNumberField, gbc);

        // Expiration Date
        addComponent(new JLabel("Expiration Date (MM/yyyy):"), gbc);
        JTextField expirationDateField = new JTextField(10);
        addComponent(expirationDateField, gbc);

        // CVV
        addComponent(new JLabel("CVV:"), gbc);
        JTextField cvvField = new JTextField(4);
        addComponent(cvvField, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit Payment");
        submitButton.addActionListener(e -> submitPayment(cardNumberField.getText(), expirationDateField.getText(), cvvField.getText(), bookingDetails));
        addComponent(submitButton, gbc);
    }

    private void addComponent(Component component, GridBagConstraints gbc) {
        getContentPane().add(component, gbc);
    }

    private void submitPayment(String cardNumber, String expirationDate, String cvv, String bookingDetails) {
        // Basic validation (In real applications, more comprehensive validation and encryption mechanisms should be implemented)
        if (cardNumber.isEmpty() || expirationDate.isEmpty() || cvv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate expiration date format
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth.parse(expirationDate, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid expiration date format. Please use MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Here, you would typically handle the payment processing logic

        // For demonstration, we'll just show a success message
        JOptionPane.showMessageDialog(this, "Payment Successful!\n" + bookingDetails, "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the payment frame
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                    new PaymentFrame("Sample Booking Details").setVisible(true);
            });
    }
}