package br.com.leivas.supertrunforeciclagem.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/* CODIGOS ANOTADOS*/

public class UI {

    SuperTrunfoDaReciclagemSimulacao stdr;

    JFrame window;
    public JTextArea messageText;
    public JPanel[] bgPanel = new JPanel[10];
    public JLabel[] bgLabel = new JLabel[10];


    public UI(SuperTrunfoDaReciclagemSimulacao stdr){
        this.stdr=stdr;

        createMainField();
        generateScreen();       //gerador de telas

        window.setVisible(true);

    }

    public void createMainField() {     //CRIA JANELA DO WINDOWS
        window = new JFrame();
        window.setSize(800, 600);       //TAMANHO DA JANELA WXH
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);     //COR
        window.setLayout(null);

        messageText = new JTextArea("SUPER TRUNFO DA RECICLAGEM");  //MENSAGEM IMPRESSA NA TELA
        messageText.setBounds(150, 410, 700, 200);      //POSIÇÃO X|Y TAMANHO W|H
        messageText.setBackground(Color.black);
        messageText.setForeground(Color.white);     //CORES
        messageText.setEditable(false);             //NAO PODE SER EDITADO
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));  //FONTE USADA, TAMANHO
        window.add(messageText);
    }
        public void createBackground(int bgNum, String obgFileName){        //COM VARIAVEIS PARA CRIAR VÁRIOS
// BG NUM = n do background, STRING = nome da imagem na pasta
            bgPanel[bgNum] = new JPanel();
            bgPanel[bgNum].setBounds(50,50,640,339);
            bgPanel[bgNum].setBackground(Color.gray);
            bgPanel[bgNum].setLayout(null);
            window.add(bgPanel[bgNum]);

            bgLabel[bgNum] = new JLabel();
            bgLabel[bgNum].setBounds(0,0,700,339);      //IMAGEM DE FUNDO POSIÇÃO X TAMANHO

            ImageIcon bgIcon = new ImageIcon (getClass().getClassLoader().getResource (obgFileName));
            bgLabel[bgNum].setIcon(bgIcon);
    }
    public void createObject(int bgNum, int objx, int objy, int objW, int objH,
                             String objFileName, String Escolha1Nome, String Escolha2Nome,
                             String Escolha3Nome, String Escolha1comando, String Escolha2comando, String Escolha3comando){

        //CRIAÇÃO DE MENU
        JPopupMenu popupMenu = new JPopupMenu();

        //CRIAÇÃO DOS ITENS QUE COMPÕEM O MENU
        JMenuItem menuItem[] = new JMenuItem[4];  //NUMERO DE AÇÕES = 3
      /*  menuItem[1] = new JMenuItem(Escolha1Nome);
        menuItem[1].addActionListener(stdr.aManipulador);
        menuItem[1].setActionCommand(Escolha1comando);
        popupMenu.add(menuItem[1]);

        menuItem[2] = new JMenuItem(Escolha2Nome);
        menuItem[2].addActionListener(stdr.aManipulador);
        menuItem[2].setActionCommand(Escolha2comando);
        popupMenu.add(menuItem[2]);

        menuItem[3] = new JMenuItem(Escolha3Nome);
        menuItem[3].addActionListener(stdr.aManipulador);
        menuItem[3].setActionCommand(Escolha3comando);
        popupMenu.add(menuItem[3]); */


       //CRIA OBJETOS
        JLabel objectLabel = new JLabel();
//        objectLabel.setBounds(200,100,50,50);
          objectLabel.setBounds(objx,objy,objW,objH);

        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));
        objectLabel.setIcon(objectIcon);

        //CRIA INTERAÇÃO COM O MOUSE
        objectLabel.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
                    if(SwingUtilities.isRightMouseButton(e));       //HABILITANDO CLIQUE DO MOUSE NO OBJETO PARA APARECER MENU
                    popupMenu.show(objectLabel, e.getX(), e.getY());
            }
            public void mouseReleased(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
        });

        //ORGANIZA ORDEM DE APARIÇÃO NA TELA
        bgPanel[bgNum].add(objectLabel);

    }
        public void createSkipButton(int bgNum, int x, int y, int w, int h, String skipFileName, String comando){
            ImageIcon skipIcon = new ImageIcon(getClass().getClassLoader().getResource (skipFileName));
            JButton skipButton = new JButton();
            skipButton.setBounds(x, y, w, h);
            skipButton.setBackground(null);
            skipButton.setContentAreaFilled(false);
            skipButton.setFocusPainted(false);
            skipButton.setIcon(skipIcon);
           /* skipButton.addActionListener(stdr.aManipulador); */
            skipButton.setActionCommand(comando);
            skipButton.setBorderPainted(false);

            bgPanel[bgNum].add(skipButton);
        }

        public void generateScreen(){
            //TELA 01
            createBackground(1,"res/bgIcon.jpg");
            createObject(1,570,280,50,50, "res/FireIcon.png", "INICIAR", "OPCOES", "SAIR","INICIARfire", "OPCOESfire", "SAIRfire" );
            createSkipButton(1,580,150,60,60, "res/skipbutton.png","goScene2");
            bgPanel[1].add(bgLabel[1]);

           // createObject(1,200,200,70,97, "res/", "DESCRIÇÃO","","","DESCRIÇÃOtest","","");

            //TELA 02
            createBackground(2,"res/skipButton.png");
            createObject(2,300,280,50,50, "res/FireIcon.png", "INICIAR", "OPCOES", "SAIR","INICIARfire", "OPCOESfire", "SAIRfire" );
            createSkipButton(2,0,150,50,50, "res/skipbutton.png","goScene2");
            bgPanel[2].add(bgLabel[2]);

        }
}


