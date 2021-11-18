package br.com.leivas.supertrunforeciclagem.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class APP extends Frame implements WindowListener,ActionListener {
    TextField textName1 = new TextField(20);
    TextField textName2 = new TextField(20);

    Button b;

    public static void main(String[] args) {
        br.com.leivas.supertrunforeciclagem.main.APP myWindow = new br.com.leivas.supertrunforeciclagem.main.APP("SUPER TRUNFO DA RECICLAGEM");
        myWindow.setSize(550,550);
        myWindow.setVisible(true);
    }
    public APP(String title) {
        super(title);
        setLayout(new FlowLayout());
        addWindowListener(this);
        b = new Button("PLAY ME");
        setLayout(null);
       /* Image img = new ImageIcon(getClass().getResource("res/bgIcon.jpg")).getImage(); */
        b.setBounds(100,400,60,75);
        add(b);
        textName1.setBounds(210,350,200,50);
        textName1.setBackground(Color.pink);
        textName1.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        textName2.setBounds(210,450,200,50);
        textName2.setBackground(Color.green);
        textName2.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        add(textName1);
        add(textName2);
        b.addActionListener(this);


    }
        //BOTAO FUNCIONAL
    public void actionPerformed(ActionEvent e) {
        String n1 = textName1.getText();
        String n2 = textName2.getText();
        String vencedor = SuperTrunfoDaReciclagemSimulacao.main(n1,n2);
        JOptionPane.showMessageDialog(null,vencedor);
    }
        //WINDOW
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
}
