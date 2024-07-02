import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener{

    //deklarien alle Variable die wir brauchen werden 
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HIGHT = 600; 
    static final int UNIT_SIZE = 25; 
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HIGHT)/UNIT_SIZE; 
    //für unseren Timer den wir später inplementieren wollen. Je höher unser
    //Wert, desto langsamer ist unsere Schlange 
    static final int DELAY = 75;
    //enthält alle x koordinaten der Schlange, auch den Kopf der Schlange
    final int x[] = new int[GAME_UNITS];
    //enthält alle y koordinaten der Schlange 
    final int y[] = new int[GAME_UNITS];
    //fangen mit 6 Bodyparts an 
    int bodyParts = 6; 
    int applesEaten; 
    //die x und y Koordinate des Apfels im Spiel 
    int appleX; 
    int appleY;
    //sagt uns in welche Richtig die Schlange läuft wenn wir das Spiel starten
    // R L U D sind mögliche Richtungen 
    char direction = 'R'; 
    boolean running = false; 
    Timer timer; 
    Random random; 


    public gamePanel(){

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HIGHT));
        this.setBackground(Color.BLACK);
        // aktiviert die Fokusfähigkeit für die Komponente, sodass Eingaben 
        //von der Tastatur erhalten werden können 
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAddaptor());

    }

    public void startGame(){

        newApple();
        running = true; 
        // Es gibt 2 Pakete mit Timer, wir 
        //brauchen Swing hier nicht util 
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

    }

    public void draw(Graphics g){

    }

    public void move (){

    }

    public void newApple(){

    }

    public void checkAppel(){

    }

    public void checkCollisions(){

    }

    public void gameOver(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public class MyKeyAddaptor extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e ){
            
        }
    }



}
