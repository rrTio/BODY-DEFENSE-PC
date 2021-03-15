import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

public class RUN extends ProjectClass {
    private final JFrame loadingFrame, mainFrame, instructionsFrame;

    private final JPanel topPanel, centerPanel, bottomPanel, insTop, insCenter, insBottom, loadA, loadB, loadC;
    private final JButton btnPlay, btnInstructions, close;
    private final JTextField pressA, pressD, pressSpace, pressEsc;
    private final JLabel titleWord, credits, headline, credits1;
    private final JProgressBar loadingBar;
    private final int width;
    private final int height;
    private int max = 0;

    public RUN(int width, int height) {
        this.width = width; this.height = height;
           mainFrame = new JFrame("BODY DEFENSE");         instructionsFrame = new JFrame("BODY DEFENSE");
        loadingFrame = new JFrame("BODY DEFENSE");                 titleWord = new JLabel("BODY DEFENSE");

        headline = new JLabel("HOW TO PLAY");
         credits = new JLabel("CREATED BY: NEIL RENDELL REGLOS & ROZS RAVEN TIO");

        topPanel = new JPanel();    centerPanel = new JPanel();     bottomPanel = new JPanel();
          insTop = new JPanel();      insCenter = new JPanel();       insBottom = new JPanel();
           loadA = new JPanel();          loadB = new JPanel();           loadC = new JPanel();

        credits1 = new JLabel("");

                btnPlay = new JButton("PLAY GAME");
        btnInstructions = new JButton("INSTRUCTIONS");
                  close = new JButton("CLOSE");

            pressA = new JTextField("PRESS A TO MOVE LEFT");
            pressD = new JTextField("PRESS D TO MOVE RIGHT");
          pressEsc = new JTextField("PRESS ESC TO CLOSE");
        pressSpace = new JTextField("PRESS SPACE TO FIRE");

        loadingBar = new JProgressBar();
    }

    public void progress()
    {
        String enemyIcon = "C:\\Users\\Vader\\Desktop\\PROJECT RT\\ENEMY.png";
        Image icon = Toolkit.getDefaultToolkit().getImage(enemyIcon);
        ImageIcon logoMain = new ImageIcon(new ImageIcon(enemyIcon).getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
        loadingFrame.setIconImage(icon);
        
        titleWord.setFont(new Font("Castellar", Font.BOLD, 35));
        titleWord.setForeground(Color.WHITE);
        titleWord.setVerticalTextPosition(JLabel.CENTER); titleWord.setHorizontalTextPosition(JLabel.CENTER);
        loadA.setBackground(Color.BLACK);
        loadA.add(titleWord);

        credits1.setIcon(logoMain);
        credits1.setHorizontalTextPosition(JLabel.CENTER);
        credits1.setVerticalTextPosition(JLabel.CENTER);
        credits1.setForeground(Color.WHITE);
        loadB.setBackground(Color.BLACK);
        loadB.add(credits1);

        loadingBar.setValue(max);
        loadingBar.setPreferredSize(new Dimension(380, 35));
        loadingBar.setForeground(Color.GREEN);
        loadingBar.setStringPainted(true);
        loadingBar.setOpaque(false);
        loadC.setBackground(Color.BLACK);
        loadC.add(loadingBar);

        loadingFrame.add(loadA, BorderLayout.NORTH); loadingFrame.add(loadB, BorderLayout.CENTER); loadingFrame.add(loadC, BorderLayout.SOUTH);
        loadingFrame.setSize(400, 260);
        loadingFrame.setResizable(false);
        loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setVisible(true);
        Timer();
    }

    public void Timer() {
        int delay = 500, period = 50;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                String pathSO = "C:\\Users\\Vader\\Desktop\\PROJECT RT\\CreditsSO.jpg";
                String pathGO = "C:\\Users\\Vader\\Desktop\\PROJECT RT\\CreditsGO.jpg";
                String enemyIcon = "C:\\Users\\Vader\\Desktop\\PROJECT RT\\";
                ImageIcon iconA = new ImageIcon(new ImageIcon(enemyIcon + "ENEMY.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                ImageIcon STACK = new ImageIcon(new ImageIcon(pathSO).getImage().getScaledInstance(240, 70, Image.SCALE_SMOOTH));
                ImageIcon GOOGLE = new ImageIcon(new ImageIcon(pathGO).getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH));

                loadingBar.setValue(max);   max++;

                if(max == 25)
                {
                    credits1.setHorizontalTextPosition(JLabel.CENTER); credits1.setVerticalTextPosition(JLabel.TOP);
                    credits1.setIcon(STACK); credits1.setText("---CREDITS TO---");
                }
                if(max == 45)
                {
                    credits1.setHorizontalTextPosition(JLabel.CENTER); credits1.setVerticalTextPosition(JLabel.TOP);
                    credits1.setIcon(GOOGLE); credits1.setText("---CREDITS TO---");
                }
                if(max == 70)
                {
                    credits1.setIcon(iconA);
                    credits1.setHorizontalTextPosition(JLabel.CENTER); credits1.setVerticalTextPosition(JLabel.CENTER);
                    credits1.setText("CREATED BY: NEIL RENDELL REGLOS & ROZS RAVEN TIO");
                }
                if(max == 100)
                {loadingFrame.setVisible(false); RUN run = new RUN(500,300); run.StartGame(); run.setUpButtonListeners();}
            }
        }, delay, period);
    }

    public void StartGame()
    {
        String enemyIcon = "C:\\Users\\Vader\\Desktop\\PROJECT RT\\";
        Image icon = Toolkit.getDefaultToolkit().getImage(enemyIcon + "ENEMY.png");
        mainFrame.setIconImage(icon);

        titleWord.setFont(new Font("Castellar", Font.BOLD, 50));
        titleWord.setForeground(Color.WHITE);
        topPanel.setBackground(Color.BLACK);
        topPanel.add(titleWord);

        btnPlay.setBorderPainted(false);
        btnPlay.setFocusPainted(false);
        btnPlay.setPreferredSize(new Dimension(350, 70));
        btnPlay.setBackground(Color.BLACK); btnPlay.setForeground(Color.GREEN);
        btnPlay.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

        btnInstructions.setBorderPainted(false);
        btnInstructions.setFocusPainted(false);
        btnInstructions.setPreferredSize(new Dimension(350, 70));
        btnInstructions.setBackground(Color.BLACK); btnInstructions.setForeground(Color.GREEN);
        btnInstructions.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(btnPlay); centerPanel.add(btnInstructions);
        credits.setForeground(Color.WHITE);

        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(credits);

        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(centerPanel, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.setSize(width, height);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void Instructions()
    {
        String enemyIcon = "C:\\Users\\Vader\\Desktop\\PROJECT RT\\";
        Image icon = Toolkit.getDefaultToolkit().getImage(enemyIcon + "ENEMY.png");
        instructionsFrame.setIconImage(icon);

        headline.setFont(new Font("Castellar", Font.BOLD, 35));
        headline.setForeground(Color.WHITE);
        insTop.setBackground(Color.BLACK);
        insTop.add(headline);

        pressA.setEditable(false);
        pressA.setBackground(Color.GREEN);  pressA.setForeground(Color.BLACK);
        pressA.setHorizontalAlignment(SwingConstants.CENTER);
        pressA.setPreferredSize(new Dimension(450, 30));
        pressA.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 20));
        insCenter.add(pressA);

        pressD.setEditable(false);
        pressD.setBackground(Color.GREEN);  pressD.setForeground(Color.BLACK);
        pressD.setHorizontalAlignment(SwingConstants.CENTER);
        pressD.setPreferredSize(new Dimension(450, 30));
        pressD.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 20));
        insCenter.add(pressD);

        pressSpace.setEditable(false);
        pressSpace.setBackground(Color.GREEN);  pressSpace.setForeground(Color.BLACK);
        pressSpace.setHorizontalAlignment(SwingConstants.CENTER);
        pressSpace.setPreferredSize(new Dimension(450, 30));
        pressSpace.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 20));
        insCenter.add(pressSpace);

        pressEsc.setEditable(false);
        pressEsc.setBackground(Color.GREEN);    pressEsc.setForeground(Color.BLACK);
        pressEsc.setHorizontalAlignment(SwingConstants.CENTER);
        pressEsc.setPreferredSize(new Dimension(450, 30));
        pressEsc.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 20));
        insCenter.setBackground(Color.BLACK);
        insCenter.add(pressEsc);

        close.setBorderPainted(false);
        close.setBackground(Color.YELLOW);  close.setForeground(Color.BLACK);
        close.setPreferredSize(new Dimension(400, 30));
        close.setFont(new Font("Verdana", Font.BOLD, 30));
        insBottom.setBackground(Color.BLACK);
        insBottom.add(close);
        instructionsFrame.setSize(550, 300);
        instructionsFrame.add(insTop, BorderLayout.NORTH);
        instructionsFrame.add(insCenter, BorderLayout.CENTER);
        instructionsFrame.add(insBottom, BorderLayout.SOUTH);
        instructionsFrame.setResizable(false);
        instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructionsFrame.setLocationRelativeTo(null);
        instructionsFrame.setVisible(true);
    }

    public void playGame()
    {
        String enemyIcon = "C:\\Users\\Vader\\Desktop\\PROJECT RT\\";
        Image icon = Toolkit.getDefaultToolkit().getImage(enemyIcon + "ENEMY.png");
        ProjectClass projectClass = new ProjectClass();
        projectClass.setPreferredSize(new Dimension(600, 400));
        JFrame frame = new JFrame("BODY DEFENSE");
        frame.add(projectClass); frame.setIconImage(icon); frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setLocationRelativeTo(null);
        frame.setResizable(false); frame.setVisible(true);
        projectClass.start(); projectClass.play();
    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = action -> {

            Object actionValue = action.getSource();

            if (actionValue == btnPlay)         { mainFrame.setVisible(false); playGame(); }
            if (actionValue == btnInstructions) { mainFrame.setVisible(false); Instructions(); }
            if (actionValue == close)           { instructionsFrame.setVisible(false); StartGame();}
        };
        close.addActionListener(buttonListener);    btnPlay.addActionListener(buttonListener);
        btnInstructions.addActionListener(buttonListener);
    }

    public static void main(String[] args)
    {RUN run = new RUN(550,300); run.progress();}
}
