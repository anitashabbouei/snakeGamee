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
        draw(this.getGraphics());
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g){

        for(int i = 0; i < SCREEN_HIGHT/UNIT_SIZE; i++){
            //zeichen hier kästchen Grid
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HIGHT);
            g.drawLine(0,i*UNIT_SIZE, SCREEN_WIDTH,i*UNIT_SIZE);
        }

        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        //Head and Body of the Snake

        for(int i = 0; i< bodyParts;i++){
            if (i==0){
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
            else{
                g.setColor(new Color(45, 180, 0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }

    }

    /*
     * Mit dieser Methode bewegen wir die Schlange 
     */
    public void move (){
        for (int i = bodyParts; i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
            
        }

        switch (direction){
            case 'U': 
                y[0] = y[0] - UNIT_SIZE;
                break; 
            
            case 'D': 
                y[0] = y[0] + UNIT_SIZE;
                break       ;
            
            case 'L': 
                x[0] = x[0] - UNIT_SIZE;
                break; 

            case 'R': 
                x[0] = x[0] - UNIT_SIZE;
                break; 

        }

    }

    /*
     * Bestimmen in dieser Methode die Koordinaten jedes neuen Apfels
     */
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))* UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HIGHT/UNIT_SIZE))* UNIT_SIZE;

    }

    public void checkAppel(){

    }

    public void checkCollisions(){

        //check if the head of the snake touches its body

        //check if the snake collides with the Wall


    }

    public void gameOver(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");

        if (running) {
            move();
            checkAppel();
            checkCollisions();

        }
        repaint();
    }

    public class MyKeyAddaptor extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e ){
            
        }
    }



}
