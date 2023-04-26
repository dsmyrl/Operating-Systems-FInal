import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong extends JFrame implements ActionListener, Runnable {

    private int width = 700;
    private int height = 400;
    private int ballX = 350;
    private int ballY = 200;
    private int ballSize = 20;
    private int ballXDir = -1;
    private int ballYDir = -1;
    private int player1Y = 150;
    private int player2Y = 150;
    private int paddleWidth = 20;
    private int paddleHeight = 50;
    private int playerSpeed = 5;
    private int player1Score = 0;
    private int player2Score = 0;
    private JLabel scoreLabel = new JLabel("Player 1: 0 | Player 2: 0", SwingConstants.CENTER);
    private boolean running = false;

    public Pong() {
        setTitle("Pong");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    player1Y -= playerSpeed;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    player1Y += playerSpeed;
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player2Y -= playerSpeed;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player2Y += playerSpeed;
                }
            }
        });
        scoreLabel.setBounds(width / 2 - 100, 10, 200, 30);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        //scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);
        setVisible(true);
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
    }

    public void run() {
        while (running) {
            update();
            render();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // Move the ball
        ballX += ballXDir;
        ballY += ballYDir;

        // Check for collision with walls
        if (ballY <= 0 || ballY + ballSize >= height) {
            ballYDir = -ballYDir;
        }
        if (ballX <= 0) {
            player2Score++;
            updateScoreLabel();
            reset();
        } else if (ballX + ballSize >= width) {
            player1Score++;
            updateScoreLabel();
            reset();
        }

        // Check for collision with paddles
        if (ballX <= paddleWidth && ballY + ballSize >= player1Y && ballY <= player1Y + paddleHeight) {
            ballXDir = -ballXDir;
        }
        if (ballX + ballSize >= width - paddleWidth && ballY + ballSize >= player2Y && ballY <= player2Y + paddleHeight) {
            ballXDir = -ballXDir;
        }
    }

    public void reset() {
        ballX = 350;
        ballY = 200;
        ballXDir = -ballXDir;
        player1Y = 150;
        player2Y = 150;
    }

    public void render() {
        Graphics g = getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(0, player1Y, paddleWidth, paddleHeight);
        g.fillRect(width - paddleWidth, player2Y, paddleWidth, paddleHeight);
        g.fillOval(ballX, ballY, ballSize, ballSize);
        g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 20));
    FontMetrics metrics = g.getFontMetrics(g.getFont());
    int x = (width - metrics.stringWidth(scoreLabel.getText())) / 2;
    int y = 50;
    g.drawString(scoreLabel.getText(), x, y);
    }
    
    public void updateScoreLabel() {
        scoreLabel.setText("Player 1: " + player1Score + " | Player 2: " + player2Score);
    }
    
    // public static void main(String[] args) {
    //     Pong pong = new Pong();
    //     pong.start();
    // }
    
    public void actionPerformed(ActionEvent e) {
        
    }
}
    
