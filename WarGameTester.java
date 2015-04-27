/**
 * Created by Alec Meeker on 4/16/2015.
 */
public class WarGameTester {
    public static void main(String[]arg){

        WarGame wahoo=new WarGame();
        int c=0;
        while (!wahoo.warState) {
            wahoo.flip();
            wahoo.show();
            wahoo.resolve();
            c++;
            if (c>100){
                System.exit(0);
            }
        }
        wahoo.flip();
        wahoo.show();
        wahoo.resolve();

        System.out.println(wahoo.getDeckSize(1));
        System.out.println(wahoo.getDeckSize(2));
        wahoo.shuffle();
    }
}
