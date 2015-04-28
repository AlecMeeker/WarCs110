import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Alec Meeker
 * CS 110 final project
 *
 * This contains both the main method to generate the gui and the gui method to play the game
 *
 */
public class GooeyWar {
    public static void main(String[]arg){
        JFrame frame = new WarFrame("WAR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(850,500);

        frame.validate();
        frame.setVisible(true);
    }
}


class WarFrame extends JFrame{
    private JPanel fieldPanela,noticeBoard,fieldPanelb,yourPanel,yourDeck, opponentDeck, warAnti;
    private JButton flip,shuffle,newgame;
    private ImageIcon iif1,iif2,iid,iid2,iiph;
    private JLabel title,f1,f2,d,d2,p1ds,p2ds,ph,rndWinner,a11,a12,a13,a14,a15,a16,a17,a18,a19,a21,a22,a23,a24,a25,a26,a27,a28,a29;
    private WarGame game;

    /**
     constructor
     @param nm the name for the gui
     */
    public WarFrame(String nm){
        super(nm);
        setLayout(new GridLayout(2, 3));
        game=new WarGame();

        // initializing the place where notification will go
        noticeBoard=new JPanel();
        noticeBoard.setLayout(new BorderLayout());
        rndWinner=new JLabel("This Is a new game of War");
        rndWinner.setFont(new Font("Times New Roman",Font.BOLD,24));
        noticeBoard.add(rndWinner,BorderLayout.CENTER);

        // initializing the place where all the buttons go
        yourPanel=new JPanel();
        yourPanel.setLayout(new GridLayout(0,1));
        flip=new JButton("Flip");
        yourPanel.add(flip);
        flip.addActionListener(new FlipListener());

        p1ds=new JLabel("Player 1 deck size: "+game.getDeckSize(1));
        yourPanel.add(p1ds);
        p2ds=new JLabel("Player 2 deck size: "+game.getDeckSize(2),SwingConstants.RIGHT);
        yourPanel.add(p2ds);

        shuffle=new JButton("Shuffle");
        yourPanel.add(shuffle);
        shuffle.addActionListener(new ShuffleListener());

        // initializing the place where player 1's deck will go
        yourDeck=new JPanel();
        iid=new ImageIcon("back.jpg");
        d=new JLabel(iid);
        yourDeck.add(d);

        // initializing the place where player 2's deck will go
        opponentDeck=new JPanel();
        iid2=new ImageIcon("back.jpg");
        d2=new JLabel(iid2);
        opponentDeck.add(d2);

        // initializing the place where player 1's face up card will go
        fieldPanela=new JPanel();
        iif1=new ImageIcon("blank.jpg");
        f1=new JLabel(iif1);
        fieldPanela.add(f1);

        // initializing the place where player 2's face up card will go
        fieldPanelb=new JPanel();
        iif2=new ImageIcon("blank.jpg");
        f2=new JLabel(iif2);
        fieldPanelb.add(f2);

        //to be printed when used
        warAnti=new JPanel();
        warAnti.setLayout(new GridLayout(0,2));
        noticeBoard.add(warAnti,BorderLayout.SOUTH);

        //an additional button that will pop up once the game ends
        newgame=new JButton("New Game?");
        newgame.addActionListener(new newGameListener());

        settingTheBackground();

        // adding everything to the main frame
        add(fieldPanela);
        add(noticeBoard);
        add(fieldPanelb);
        add(yourDeck);
        add(yourPanel);
        add(opponentDeck);
    }

    private void settingTheBackground(){
        fieldPanela.setBackground(new Color(40,255,40,200).darker());
        fieldPanelb.setBackground(new Color(40,255,40,200).darker());
        noticeBoard.setBackground(new Color(40,255,40,200).darker());
        yourDeck.setBackground(new Color(40,255,40,200).darker());
        yourPanel.setBackground(new Color(40,255,40,200).darker());
        opponentDeck.setBackground(new Color(40,255,40,200).darker());

    }
    // triggered when flip is clicked
    private class FlipListener implements ActionListener{
        public void actionPerformed (ActionEvent e){
            game.resolve();
            if(game.getDeckSize(1)!=0&&game.getDeckSize(2)!=0) { //checks for an empty deck
                game.flip();
                //set images as face-up cards
                f1.setIcon(new ImageIcon(game.picName(1)));
                f2.setIcon(new ImageIcon(game.picName(2)));
                //displays new deck sizes
                p1ds.setText("Player 1 deck size: " + game.getDeckSize(1));
                p2ds.setText("Player 2 deck size: " + game.getDeckSize(2));

                rndWinner.setText(game.theWinRound());
                yourPanel.remove(shuffle);
                if (game.warState) {
                    flip.setText("WAR");
                    int i=0;
                    ArrayList<Card> p1=game.p1warcards,p2=game.p2warcards;
                    try {
                        while (i<p1.size()) {
                            warAnti.add(new JLabel(p1.get(i).toString()));
                            warAnti.add(new JLabel(p2.get(i).toString()));
                            i++;
                        }
                    }catch(Exception ex){}
                } else {
                    flip.setText("Flip");
                    yourPanel.add(shuffle);
                    warAnti.removeAll();
                }
            }else {
                if(game.getDeckSize(1)==0){ // player 2 wins
                    d.setIcon(new ImageIcon("blank.jpg"));
                    rndWinner.setText("Player 2 Wins the Game");
                    f1.setIcon(new ImageIcon("blank.jpg"));
                    f2.setIcon(new ImageIcon("blank.jpg"));
                    p1ds.setText("Player 1 deck size: "+game.getDeckSize(1));
                    p2ds.setText("Player 2 deck size: " + game.getDeckSize(2));
                    yourPanel.remove(shuffle);
                    yourPanel.remove(flip);
                    yourPanel.add(newgame);

                }else if(game.getDeckSize(2)==0){ //player 1 wins
                    d2.setIcon(new ImageIcon("blank.jpg"));
                    rndWinner.setText("Player 1 wins The Game");
                    f1.setIcon(new ImageIcon("blank.jpg"));
                    f2.setIcon(new ImageIcon("blank.jpg"));
                    p1ds.setText("Player 1 deck size: "+game.getDeckSize(1));
                    p2ds.setText("Player 2 deck size: " + game.getDeckSize(2));
                    yourPanel.remove(shuffle);
                    yourPanel.remove(flip);
                    yourPanel.add(newgame);
                }
            }
            settingTheBackground();
        }
    }

    private class ShuffleListener implements ActionListener{
        public void actionPerformed (ActionEvent e){
            game.resolve();
            game.shuffle();
            f1.setIcon(new ImageIcon("blank.jpg"));
            f2.setIcon(new ImageIcon("blank.jpg"));
            p1ds.setText("Player 1 deck size: "+game.getDeckSize(1));
            p2ds.setText("Player 2 deck size: " + game.getDeckSize(2));
            rndWinner.setText("SHUFFLE");
            settingTheBackground();
        }
    }

    // starting a new game
    private class newGameListener implements ActionListener{
        public void actionPerformed (ActionEvent e){
            String[] arg=new String[] {"again"};
            GooeyWar.main(arg);
        }
    }
}
