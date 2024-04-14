/**
 * PROGRAM DESCRIPTION: TO CREATE A WELCOME PAGE FRAME FOR HOTEL BOOKING SYSTEM
 *
 * HANIFF & HAZIQ
 * 28 MARCH 2024
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class HotelReservationSystem extends JFrame {
    public HotelReservationSystem() {
        initUI();
    }

    private void initUI() {
        setTitle("Floral Hotel Reservation System");
        
        // Adjust size to better fit the image
        setSize(1280, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Center the window
        setLocationRelativeTo(null);
        
        // Use BorderLayout for better arrangement of components
        setLayout(new BorderLayout());

        // Welcome message
        JLabel welcomeLabel = new JLabel("<html><center>Welcome to Floral Hotel Reservation System<br>Book your stay with us and enjoy unparalleled comfort!</center></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Set font for welcome message
        add(welcomeLabel, BorderLayout.NORTH);

        // Load and add image
        addImage();

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20)); // Use FlowLayout for evenly spaced buttons

        // Button to navigate to the booking page
        JButton bookingButton = new JButton("Book Now");
        bookingButton.addActionListener(this::openBookingFrame);
        buttonPanel.add(bookingButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    //To add image
    private void addImage() {
        try {
            URL imageUrl = getClass().getResource("floral-hotel.jpg");
            if (imageUrl != null) {
                ImageIcon imageIcon = new ImageIcon(imageUrl);
                JLabel imageLabel = new JLabel(imageIcon);
                add(imageLabel, BorderLayout.CENTER);
            } else {
                System.err.println("Image file not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load image");
        }
    }

    private void openBookingFrame(ActionEvent e) {
        BookingFrame bookingFrame = new BookingFrame();
        bookingFrame.setVisible(true);
        dispose();
    }// Close the welcome frame

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new HotelReservationSystem().setVisible(true));
    }
}