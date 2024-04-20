import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.HashMap;

public class ListWork extends JFrame {
    private DefaultListModel<String> myListModel;
    private JList<String> myList;
    private JTextField ageField;
    private JTextField addressField;
    private HashMap<String, StudentData> studentMap;

    public static void main(String[] args) {
        ListWork window = new ListWork("Работа со списком");
        window.setVisible(true);
        window.pack();
        window.setMinimumSize(window.getSize());
    }

    public ListWork(String s) {
        super(s);
        myListModel = new DefaultListModel<>();
        myList = new JList<>(myListModel);
        JScrollPane myScroll = new JScrollPane(myList);

        ageField = new JTextField();
        addressField = new JTextField();
        JPanel infoPanel = new JPanel(new GridLayout(2, 2));
        infoPanel.add(new JLabel("Возраст:"));
        infoPanel.add(ageField);
        infoPanel.add(new JLabel("Адрес:"));
        infoPanel.add(addressField);

        Box controlBox = Box.createHorizontalBox();
        JButton button1 = new JButton("Добавить студента");
        JButton button2 = new JButton("Удалить студента");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Введите имя студента:");
                if (name != null && !name.isEmpty()) {
                    String age = JOptionPane.showInputDialog("Введите возраст студента:");
                    String address = JOptionPane.showInputDialog("Введите адрес студента:");
                    myListModel.addElement(name);
                    studentMap.put(name, new StudentData(age, address));
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedStudent = myList.getSelectedValue();
                if (selectedStudent != null) {
                    myListModel.removeElement(selectedStudent);
                    studentMap.remove(selectedStudent);
                    ageField.setText("");
                    addressField.setText("");
                }
            }
        });

        controlBox.add(button1);
        controlBox.add(Box.createHorizontalStrut(10));
        controlBox.add(button2);

        add(myScroll, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.NORTH);
        add(controlBox, BorderLayout.SOUTH);

        studentMap = new HashMap<>();

        myList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedStudent = myList.getSelectedValue();
                if (selectedStudent != null) {
                    StudentData data = studentMap.get(selectedStudent);
                    ageField.setText(data.getAge());
                    addressField.setText(data.getAddress());
                }
            }
        });
    }

    // Inner class to hold student data
    private class StudentData {
        private String age;
        private String address;

        public StudentData(String age, String address) {
            this.age = age;
            this.address = address;
        }

        public String getAge() {
            return age;
        }

        public String getAddress() {
            return address;
        }
    }
}
