package ui;

import service.RentalService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private RentalService rentalService;

    private JTextField txtCarId;
    private JTextArea displayArea;

    public MainFrame() {

        rentalService = new RentalService();

        setTitle("Car Rental System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel lblId = new JLabel("Car ID:");

        txtCarId = new JTextField(10);

        JButton btnRent = new JButton("Rent Car");
        JButton btnReturn = new JButton("Return Car");
        JButton btnRefresh = new JButton("Refresh");

        displayArea = new JTextArea(15, 35);
        displayArea.setEditable(false);

        panel.add(lblId);
        panel.add(txtCarId);
        panel.add(btnRent);
        panel.add(btnReturn);
        panel.add(btnRefresh);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        refreshCars();

        // Event Handling
        btnRent.addActionListener(e -> rentCar());

        btnReturn.addActionListener(e -> returnCar());

        btnRefresh.addActionListener(e -> refreshCars());
    }

    private void rentCar() {

        try {

            String carId = txtCarId.getText().trim();

            if (carId.isEmpty()) {
                throw new IllegalArgumentException("Car ID cannot be empty.");
            }

            rentalService.rentCar(carId);

            JOptionPane.showMessageDialog(this,
                    "Car rented successfully.");

            refreshCars();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Rental Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnCar() {

        try {

            String carId = txtCarId.getText().trim();

            if (carId.isEmpty()) {
                throw new IllegalArgumentException("Car ID cannot be empty.");
            }

            rentalService.returnCar(carId);

            JOptionPane.showMessageDialog(this,
                    "Car returned successfully.");

            refreshCars();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Return Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshCars() {

        displayArea.setText("");

        rentalService.getCars().forEach(car ->
                displayArea.append(car + "\n"));
    }
}