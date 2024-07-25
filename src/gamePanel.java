import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener {

    //deklarien alle Variable die wir brauchen werden 
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600; 
    static final int UNIT_SIZE = 25; 
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE; 
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
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        // aktiviert die Fokusfähigkeit für die Komponente, sodass Eingaben 
        //von der Tastatur erhalten werden können 
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAddaptor());
        //Wichtig hier, sonst startet das Spiel gar nicht 
        startGame();

    }

    public void startGame(){

        //Diese Methode muss ich noch bearbeiten. Kann Grid z.B. nicht sehen 

        //draw(getGraphics());
        newApple();
        running = true; 
        // Es gibt 2 Pakete mit Timer, wir 
        //brauchen Swing hier, nicht util 
        //draw(this.getGraphics());
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        draw(g);

        //ich  
    }

    public void draw(Graphics g){

        if (running){
            for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++){
                //zeichen hier kästchen Grid
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0,i*UNIT_SIZE, SCREEN_WIDTH,i*UNIT_SIZE);
                System.out.println("I love food");
                g.setColor(Color.WHITE);
             }
             
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

         //Head and Body of the Snake

            for(int i = 0; i< bodyParts;i++){
                 if (i==0){
                     g.setColor(Color.green);
                     g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                 } else { 
                g.setColor(new Color(45, 180, 0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
    

        }
        else{
            gameOver(g);
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

        switch (direction) {
            case 'U': 
                y[0] = y[0] - UNIT_SIZE;
                break; 
            
            case 'D': 
                y[0] = y[0] + UNIT_SIZE;
                break;
            
            case 'L': 
                x[0] = x[0] - UNIT_SIZE;
                break; 

            case 'R': 
                x[0] = x[0] + UNIT_SIZE;
                break; 

        }

    }

    /*
     * Bestimmen in dieser Methode die Koordinaten jedes neuen Apfels
     */
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))* UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))* UNIT_SIZE;

    }

    public void checkAppel(){
        // hier checken wir einmal ob wir einen Apfel getroffen haben oder nicht 
        
        if(x[0] == appleX && y[0] == appleY){
            bodyParts ++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions(){

        //check if the head of the snake touches its body
        //if the for loop is true that means the head colided with the body

        for (int i = bodyParts; i>0; i-- ) {
            if ((x[0]== x[i])&&(y[0] == y[i])){
                
                running = false; 
            }
        }

        //check if the snake collides with left Wall

        if(x[0] < 0){
            running = false;
        }
        // check if head touches right border 
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }

        //check if head touches the upper border 
        if(y[0] < 0){
            running = false; 
        }

        //check if head touches the bottom border 
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }

        if(!running){
            timer.stop();
        }

    }

    public void gameOver(Graphics g){
        // display Score 
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());


        //GameOver test 
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);

    }

    public void playAgain(){

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        if (running){

            move();
            checkApple(Graphics g); 
            checkCollisions();
        }

        repaint();
    }

    public class MyKeyAddaptor extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e ){

            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                break; 

                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                break;
        
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                break;
                
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                break; 
    
            }
            // Hier wird einmal festgelegt, welche Taste gedrückt wurde 
            if (running == true) {
                move();
                checkAppel();
                checkCollisions();
    
            }
            repaint();
            
        }
    }

}

