/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serviciosapi.HTTP;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author alumno
 */
public class TranslatorApp {
    
public static void main(String[] args) {
        SwingUtilities.invokeLater(TranslatorApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Traductor HTTP 🌍");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel inputLabel = new JLabel("Texto a traducir:");
        JTextField inputField = new JTextField();
        JLabel fromLabel = new JLabel("Idioma origen:");
        JLabel toLabel = new JLabel("Idioma destino:");

        JComboBox<String> fromLang = new JComboBox<>();
        JComboBox<String> toLang = new JComboBox<>();

        Map<String, String> languages = Language.getLanguages();
        for (String lang : languages.keySet()) {
            fromLang.addItem(lang);
            toLang.addItem(lang);
        }

        JButton translateButton = new JButton("Traducir");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(fromLabel);
        panel.add(fromLang);
        panel.add(toLabel);
        panel.add(toLang);
        panel.add(translateButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputField.getText();
                String sourceLang = languages.get(fromLang.getSelectedItem().toString());
                String targetLang = languages.get(toLang.getSelectedItem().toString());

                if (!text.isEmpty() && !sourceLang.equals(targetLang)) {
                    String translatedText = TranslatorService.translate(text, sourceLang, targetLang);
                    resultArea.setText(translatedText);
                } else {
                    JOptionPane.showMessageDialog(frame, "Verifique los idiomas y el texto ingresado.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}

