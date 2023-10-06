import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    JPanel mainPanel;
    JPanel buttonPanel;

    JButton rockButton;
    JButton paperButton;
    JButton scissorButton;
    JButton quitButton;

    ImageIcon rockIcon;
    ImageIcon paperIcon;
    ImageIcon scissorIcon;
    ImageIcon quitIcon;


    JPanel statsPanel;
    JLabel playerWins;
    JTextField playerWinsField;
    ImageIcon playerIcon;
    JLabel computerWins;
    JTextField computerWinsField;
    ImageIcon computerIcon;
    JLabel tie;
    JTextField tieField;
    ImageIcon tieIcon;


    JPanel resultsPanel;
    JTextArea resultsField;
    JScrollPane resultsScroll;


    Random random;
    int playerMove = 0;
    int computerMove = 0;
    int playerWinCount = 0;
    int computerWinCount = 0;
    int tieCount = 0;

    Border bevel, empty;

    public RockPaperScissorsFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int height = screen.height;
        int width = screen.width;
        setSize(3*(width / 4), 3*(height / 4));
        setLocationRelativeTo(null);

        add(mainPanel);

        createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        createStatsPanel();
        mainPanel.add(statsPanel, BorderLayout.EAST);
        createResultsPanel();
        mainPanel.add(resultsPanel, BorderLayout.NORTH);
    }

    private void createResultsPanel() {
        resultsPanel = new JPanel();
        resultsField = new JTextArea(8, 25);
        resultsField.setFont(new Font("Serif", Font.PLAIN, 16));
        resultsField.setEditable(false);
        resultsScroll = new JScrollPane(resultsField);
        resultsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        resultsPanel.add(resultsScroll, BorderLayout.CENTER);
    }

    private void createStatsPanel() {
        statsPanel = new JPanel();
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setLayout(new GridLayout(0, 1));

        playerWins = new JLabel("Player Win Count", playerIcon, JLabel.CENTER);
        playerWins.setFont(new Font("Serif", Font.PLAIN, 16));
        playerWins.setVerticalTextPosition(JLabel.BOTTOM);
        playerWins.setHorizontalTextPosition(JLabel.CENTER);

        playerWinsField = new JTextField();
        playerWinsField.setHorizontalAlignment(JTextField.CENTER);
        empty = BorderFactory.createEmptyBorder();
        playerWinsField.setBorder(empty);
        playerWinsField.setFont(new Font("Monospaced", Font.PLAIN, 18));
        playerWinsField.setEditable(false);

        tie = new JLabel("Tie Count", tieIcon, JLabel.CENTER);
        tie.setFont(new Font("Serif", Font.PLAIN, 16));
        tie.setVerticalTextPosition(JLabel.BOTTOM);
        tie.setHorizontalTextPosition(JLabel.CENTER);

        tieField = new JTextField();
        tieField.setHorizontalAlignment(JTextField.CENTER);
        tieField.setBorder(empty);
        tieField.setFont(new Font("Monospaced", Font.PLAIN, 18));
        tieField.setEditable(false);

        computerWins = new JLabel("Computer Win Count", computerIcon, JLabel.CENTER);
        computerWins.setFont(new Font("Serif", Font.PLAIN, 16));
        computerWins.setVerticalTextPosition(JLabel.BOTTOM);
        computerWins.setHorizontalTextPosition(JLabel.CENTER);

        computerWinsField = new JTextField();
        computerWinsField.setHorizontalAlignment(JTextField.CENTER);
        computerWinsField.setBorder(empty);
        computerWinsField.setFont(new Font("Monospaced", Font.PLAIN, 18));
        computerWinsField.setEditable(false);

        statsPanel.add(playerWins);
        statsPanel.add(playerWinsField);
        statsPanel.add(tie);
        statsPanel.add(tieField);
        statsPanel.add(computerWins);
        statsPanel.add(computerWinsField);
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        bevel = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        buttonPanel.setBorder(bevel);
        buttonPanel.setLayout(new GridLayout(1, 4));

        rockIcon = new ImageIcon("src/rockIcon.jpg");
        rockButton = new JButton("ROCK", rockIcon);
        rockButton.setVerticalTextPosition(JButton.BOTTOM);
        rockButton.setHorizontalTextPosition(JButton.CENTER);
        rockButton.setFont(new Font("Dialog", Font.BOLD, 15));
        buttonPanel.add(rockButton);

        rockButton.addActionListener((ActionEvent ae) -> {
            playerMove = 1;
            random = new Random();
            computerMove = random.nextInt(3) + 1;
            if (computerMove == 1) {
                resultsField.append("Computer Picked Rock. (Tie!)\n\n");
                tieCount = tieCount + 1;
                tieField.setText(String.valueOf(tieCount));
            }

            if (computerMove == 2) {
                resultsField.append("Paper Covers Rock. (Computer Wins!)\n\n");
                computerWinCount = computerWinCount + 1;
                computerWinsField.setText(String.valueOf(computerWinCount));
            }
            if (computerMove == 3) {
                resultsField.append("Rock Breaks Scissors. (Player Wins!)\n\n");
                playerWinCount = playerWinCount + 1;
                playerWinsField.setText(String.valueOf(playerWinCount));
            }
        });

        paperIcon = new ImageIcon("src/paperIcon.png");
        paperButton = new JButton("PAPER", paperIcon);
        paperButton.setVerticalTextPosition(JButton.BOTTOM);
        paperButton.setHorizontalTextPosition(JButton.CENTER);
        paperButton.setFont(new Font("Dialog", Font.BOLD, 15));
        buttonPanel.add(paperButton);

        paperButton.addActionListener((ActionEvent ae) -> {
            playerMove = 2;
            random = new Random();
            computerMove = random.nextInt(3) + 1;
            if (computerMove == 1) {
                resultsField.append("Paper Covers Rock. (Player Wins!)\n\n");
                playerWinCount = playerWinCount + 1;
                playerWinsField.setText(String.valueOf(playerWinCount));
            }

            if (computerMove == 2) {
                resultsField.append("Computer Picked Paper. (Tie Game!)\n\n");
                tieCount = tieCount + 1;
                tieField.setText(String.valueOf(tieCount));
            }

            if (computerMove == 3) {
                resultsField.append("Scissors Cuts Paper. (Computer Wins!)\n\n");
                computerWinCount = computerWinCount + 1;
                computerWinsField.setText(String.valueOf(computerWinCount));
            }
        });

        scissorIcon = new ImageIcon("src/scissorIcon.png");
        scissorButton = new JButton("SCISSORS", scissorIcon);
        scissorButton.setVerticalTextPosition(JButton.BOTTOM);
        scissorButton.setHorizontalTextPosition(JButton.CENTER);
        scissorButton.setFont(new Font("Dialog", Font.BOLD, 15));

        scissorButton.addActionListener((ActionEvent ae) -> {
            playerMove = 3;
            random = new Random();
            computerMove = random.nextInt(3) + 1;
            if (computerMove == 1) {
                resultsField.append("Rock Breaks Scissors. (Computer Wins!)\n\n");
                computerWinCount = computerWinCount + 1;
                computerWinsField.setText(String.valueOf(computerWinCount));
            }

            if (computerMove == 2) {
                resultsField.append("Scissors Cuts Paper. (Player Wins!)\n\n");
                playerWinCount = playerWinCount + 1;
                playerWinsField.setText(String.valueOf(playerWinCount));
            }

            if (computerMove == 3) {
                resultsField.append("Computer Picked Scissors. (Tie Game!)\n\n");
                tieCount = tieCount + 1;
                tieField.setText(String.valueOf(tieCount));
            }
        });

        buttonPanel.add(scissorButton);

        quitIcon = new ImageIcon("src/quitIcon.png");
        quitButton = new JButton("QUIT", quitIcon);
        quitButton.setVerticalTextPosition(JButton.BOTTOM);
        quitButton.setHorizontalTextPosition(JButton.CENTER);
        quitButton.setFont(new Font("Dialog", Font.BOLD, 15));
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
        buttonPanel.add(quitButton);
    }


}
