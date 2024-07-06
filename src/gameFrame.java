import javax.swing.JButton;
import javax.swing.JFrame;

public class gameFrame extends JFrame{

    public gameFrame(){

        gamePanel panel = new gamePanel();
        panel.startGame();
        //panel.add(new JButton());
        this.add(panel);
        this.setTitle("snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        
    }

}
