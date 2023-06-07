
package tn.usousse.eniso.ia1.stage.presentation.view;

import tn.usousse.eniso.ia1.stage.model.Table;
import tn.usousse.eniso.ia1.stage.presentation.controller.HashtableController;
import tn.usousse.eniso.ia1.stage.service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Presentation extends JFrame {
    private HashtableController controller = new HashtableController();
    private int tableSize;
    Service service = new Service(new Table(10));
    HashTableDrawComponent drawComponent = new HashTableDrawComponent(controller);


    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem addMenuItem = new JMenuItem("Add");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);

        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSizeInputDialog();
            }
        });

        addMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddStringDialog();
            }
        });
        aboutMenuItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Hello you can draw HashTable Here! Enjoy :)");


        });

        fileMenu.add(newMenuItem);
        fileMenu.add(addMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }


    public void showAddStringDialog() {
        JTextField stringField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("String:"));
        inputPanel.add(stringField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel,
                "Add String to Table", JOptionPane.OK_CANCEL_OPTION);

        //int index=0;

        if (result == JOptionPane.OK_OPTION) {
            String str = stringField.getText();
            //service=new Service(new Table(tableSize));
            controller.setService(service);
            service.add(str);

            repaint();

        }
    }

    private void showSizeInputDialog() {
        JTextField sizeField = new JTextField(5);

        // drawTable.setTableSize(tableSize);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Size:"));
        inputPanel.add(sizeField);
        inputPanel.add(Box.createHorizontalStrut(10));

        int result = JOptionPane.showConfirmDialog(null, inputPanel,
                "Enter Table Size", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Table table = controller.getModel();

                tableSize = Integer.parseInt(sizeField.getText());
                table.setSize(tableSize);
                drawComponent.setModel(table);
                service = new Service(new Table(tableSize));
                controller.setService(service);

                this.add(drawComponent);
                //frame.setVisible(true);
                createTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid dimensions.");
            }


        }
    }

    private void createTable() {
        service = new Service(new Table(tableSize));
        controller.setService(service);
        //HashTableDrawComponent drawComponent=new HashTableDrawComponent(controller);
        drawComponent.setModel(service.getTable());
        getContentPane().add(drawComponent, BorderLayout.CENTER);
        revalidate();
    }

    public void showPresentation() {


        this.setTitle("HashTable");
        this.setLocationRelativeTo(null);


        this.setSize(800, 600);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenuBar();

        setVisible(true);


    }
}






