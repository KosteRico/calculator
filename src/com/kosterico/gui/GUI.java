package com.kosterico.gui;

import com.kosterico.factory.Calculator;
import com.kosterico.factory.PolishNotator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame{

    private JTextField input;
    private JButton calc;
    private JLabel res;
    private JButton clearAll;

    GUI () {
        super("Calculator");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 350, 100);

        input = new JTextField("");
        input.setMaximumSize(new Dimension(90, 40));


        calc = new JButton("=");
        calc.setMaximumSize(new Dimension(40, 40));
        calc.setEnabled(false);

        res = new JLabel("_");

        clearAll = new JButton("Clear All");
        clearAll.setEnabled(false);

        input.getDocument().addDocumentListener(new DocumentListener() {

            private boolean hasDigit;
            private boolean hasSymbol;

            private void check () {
                char[] array = input.getText().toCharArray();

                hasDigit = false;
                hasSymbol = false;

                for (char c : array) {
                    if (Character.isDigit(c)) {
                        hasDigit = true;
                    } else if (!PolishNotator.isDelimiter(String.valueOf(c)) && c != ' ') {
                        hasSymbol = true;
                    }
                }

                if (!hasSymbol && hasDigit) {
                    calc.setEnabled(true);
                } else if (calc.isEnabled()) {
                    calc.setEnabled(false);
                }

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (input.getText().length() > 0) {
                    clearAll.setEnabled(true);
                }
                check();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (input.getText().length() == 0) {
                    calc.setEnabled(false);
                    if (res.getText().equals("_")) {
                        clearAll.setEnabled(false);
                    }
                }
                check();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                /*if (input.getText().length() != 0) {
                    res.setEnabled(true);
                    clearAll.setEnabled(true);
                }*/
            }
        });

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calc.getActionListeners()[0].actionPerformed(e);
            }
        });

        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String exp = input.getText();
                String resNum = Calculator.calculate(exp);
                res.setText(resNum);
            }
        });

        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!res.getText().equals("_")) {
                    res.setText("_");
                }

                if (input.getText().length() > 0) {
                    input.setText("");
                }

                clearAll.setEnabled(false);

            }
        });

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 3, 15, 5));

        container.add(input);
        container.add(calc);
        container.add(res);
        container.add(clearAll);
        container.add(new JPanel());

    }



}
