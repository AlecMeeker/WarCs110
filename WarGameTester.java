/**
 * Created by Alec Meeker on 4/16/2015.
 */
public class WarGameTester {
    public static void main(String[]arg){
        WarGame wahoo=new WarGame();
        wahoo.flip();
        wahoo.show();
        wahoo.resolve();
        System.out.println(wahoo.getDeckSize(1));
        System.out.println(wahoo.getDeckSize(2));
    }
}
