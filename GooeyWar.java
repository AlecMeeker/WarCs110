import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alec Meeker on 4/17/2015.
 */
public class GooeyWar {
    public static void main(String[]arg){
        JFrame frame = new WarFrame("WAR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        // OR
        frame.setSize(850,500);
        frame.validate();
        frame.setVisible(true);
    }
}

class WarFrame extends JFrame{
    private JPanel fieldPanela,noticeBoard,fieldPanelb,yourPanel,yourDeck, opponentDeck;
    private JButton flip,shuffle;
    private ImageIcon iif1,iif2,iid,iid2,iiph;
    private JLabel title,f1,f2,d,d2,p1ds,p2ds,ph,rndWinner;
    private WarGame game;

    public WarFrame(String nm){
        super(nm);
        setLayout(new GridLayout(2, 3));
        game=new WarGame();


        noticeBoard=new JPanel();
        noticeBoard.setLayout(new BorderLayout());
        rndWinner=new JLabel("This Is a new game of War");
        rndWinner.setFont(new Font("Times New Roman",Font.BOLD,24));
        noticeBoard.add(rndWinner,BorderLayout.CENTER);

        yourPanel=new JPanel();
        yourPanel.setLayout(new GridLayout(0,1));
        flip=new JButton("Flip");
        yourPanel.add(flip);
        flip.addActionListener(new FlipListener());

        p1ds=new JLabel("Player 1 deck size: "+game.getDeckSize(1));
        yourPanel.add(p1ds);
        p2ds=new JLabel("Player 2 deck size: "+game.getDeckSize(2));
        yourPanel.add(p2ds);

        shuffle=new JButton("Shuffle");
        yourPanel.add(shuffle);
        shuffle.addActionListener(new ShuffleListener());

        yourDeck=new JPanel();
        iid=new ImageIcon("back.jpg");
        d=new JLabel(iid);
        yourDeck.add(d);

        opponentDeck=new JPanel();
        iid2=new ImageIcon("back.jpg");
        d2=new JLabel(iid2);
        opponentDeck.add(d2);

        fieldPanela=new JPanel();
        iif1=new ImageIcon("blank.jpg");
        f1=new JLabel(iif1);
        fieldPanela.add(f1);

        fieldPanelb=new JPanel();
        iif2=new ImageIcon("blank.jpg");
        f2=new JLabel(iif2);
        fieldPanelb.add(f2);

        add(fieldPanela);
        add(noticeBoard);
        add(fieldPanelb);
        add(yourDeck);
        add(yourPanel);
        add(opponentDeck);
    }

    //
    private class FlipListener implements ActionListener{
        public void actionPerformed (ActionEvent e){
            game.resolve();
            game.flip();
            f1.setIcon(new ImageIcon(game.picName(1)));
            f2.setIcon(new ImageIcon(game.picName(2)));
            p1ds.setText("Player 1 deck size: "+game.getDeckSize(1));
            p2ds.setText("Player 2 deck size: " + game.getDeckSize(2));
            //if()
            yourPanel.remove(shuffle);
            if(game.warState){
                flip.setText("WAR");
            }else {
                flip.setText("Flip");
                yourPanel.add(shuffle);
            }
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
        }
    }
}
