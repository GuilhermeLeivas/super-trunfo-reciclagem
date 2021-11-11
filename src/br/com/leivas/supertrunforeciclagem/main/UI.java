package br.com.leivas.supertrunforeciclagem.main;

import javax.swing.*;
import java.awt.*;

public class UI {

    SuperTrunfoDaReciclagemMain stdr;

    JFrame window;
    public JTextArea messageText;
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];

    public UI(SuperTrunfoDaReciclagemMain stdr){
        this.stdr=stdr;

        createMainField();
        createBackground();
        window.setVisible(true);

    }
    public void createMainField() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        messageText = new JTextArea("SUPER TRUNFO DA RECICLAGEM");
        messageText.setBounds(50, 410, 700, 150);
        messageText.setBackground(Color.black);
        messageText.setForeground(Color.pink);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        window.add(messageText);
    }
        public void createBackground(){

            bgPanel[1] = new JPanel();
            bgPanel[1].setBounds(50,50,640,339);
            bgPanel[1].setBackground(Color.gray);
            bgPanel[1].setLayout(null);
            window.add(bgPanel[1]);

            bgLabel[1] = new JLabel();
            bgLabel[1].setBounds(0,0,640,339);

            ImageIcon bgIcon = new ImageIcon (getClass().getClassLoader().getResource ("bgIcon.jpg"));
            bgLabel[1].setIcon(bgIcon);

            bgPanel[1].add(bgLabel[1]);
    }
}

/*(getClass().getClassLoader().getResource ("bgIcon.jpg")); */
