import java.lang.Math;
import java.text.DecimalFormat;
/**
 * Each player or team will create their own Player, this is only an example....
 * 
 * Your code MUST INCLUDE a move method with the following signature. To insure that, you must implement
 * the Player interface
 * 
 */
public class MyPlayer implements Player
{
    /**
     * An example of a method - replace this comment with your own
     * You must create some kind of logic of what to play against your opponent...start researching,
     * driver code to be provided TUESDAY, 2/28
     * 
     * @return      the move you want to play against opponent
     *              'r' - rock
     *              'p' - paper
     *              's' - scissors
     *              anything else - forfeit the turn
     */
    public char move(char [] myMoves, char [] opponentMoves, int myScore, int opponentScore)
    {
        if (myMoves.length <=15)
        {
            
        }
        return ' ';
    }
    
    /**
     * 33% chance rock, paper, or scissor
     */
    private char strat0()
    {
        double n = Math.random();
        if(n < .33){return 'r';}
        else if(n < .66){return 'p';}
        else{return 's';}
    }
    
    /**
     * input chance to throw rock, paper, or scissor
     */
    private char strat0(double rPer, double pPer, double sPer)
    {
        double n = Math.random();
        if(n < rPer){return 'r';}
        else if(n < rPer + pPer){return 'p';}
        else{return 's';}  
    }
    
    /**
     * counter strat0
     */
    private char strat1(char[] moves)
    {
        int rockC = 0; int paperC = 0; int scissorC = 0; int otherC = 0;
        int total = moves.length;
        for(char c : moves)
        {
            if(c == 'r'){rockC++;}
            else if(c == 'p'){paperC++;}
            else if(c == 's'){scissorC++;}
            else{otherC++;}
        }
        double rockP = (double)rockC/total;
        double paperP = (double)paperC/total;
        double scissorP = (double)scissorC/total;
        double otherP = (double)otherC/total;
        double n = Math.random();
        if(n < rockP){return 'p';}
        else if(n < paperP){return 's';}
        else{return 'r';}
    }
    
    /**
     * counter strat(tier).
     */
    private char iterate(int tier, char[] moves)
    {
        char[] choiceSet = {'r','p','s'};
        tier %= 3;
        char choice = strat1(moves);
        int choiceN;
        if(choice == 'r'){choiceN = 0;}
        else if(choice == 'p'){choiceN = 1;}
        else{choiceN = 2;}
        choiceN += tier;
        choiceN %= 3;
        return choiceSet[choiceN];
    }
    
    public double[] getData(int rProb, int pProb, int sProb)
    {
        char p1; char p2;
        int win1 = 0; int win2 = 0;
        int total = 1000;
        double[] toRet = new double[2];
        for(int n = 0; n < total; n++)
        {
            p1 = strat1(ratios(rProb, pProb, sProb));
            p2 = strat1(ratios(rProb, pProb, sProb));
            if     (p1 == 'r' && p2 == 'p'){win2++;}
            else if(p1 == 'r' && p2 == 's'){win1++;}
            else if(p1 == 'p' && p2 == 'r'){win1++;}
            else if(p1 == 'p' && p2 == 's'){win2++;}
            else if(p1 == 's' && p2 == 'r'){win2++;}
            else if(p1 == 's' && p2 == 'p'){win1++;}
            else                           {total--;}
        }
        toRet[0] = (double)win1/total;
        toRet[1] = (double)win2/total;
        return toRet;
    }
    
    public String getAvgData(int rProb, int pProb, int sProb)
    {
        int n = 1000;
        double sum1 = 0; double sum2 = 0;
        double[] temp = new double[2];
        for(int m = 0; m < n; m++)
        {
            temp = getData(rProb, pProb, sProb);
            sum1 += temp[0];
            sum2 += temp[1];
        }
        String str1 = new DecimalFormat("#.##").format(sum1/n);
        String str2 = new DecimalFormat("#.##").format(sum2/n);
        return "(" + str1 + ", " + str2 + ")";
    }
    
    public char[] ratios(int r, int p, int s)
    {
        char[] toRet = new char[100];
        for(int n = 0; n < r; n++)
        {
            toRet[n] = 'r';
        }
        for (int n = r; n < r + p; n ++)
        {
            toRet[n] = 'p';
        }
        for (int n = r; n < r + p + s; n++)
        {
            toRet[n] = 's';
        }
        return toRet;
    }
    
    public String[][] manyAverages()
    {
        int[] tens = {33, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        String[][] data = new String[12][12];
        int p1, p2;
        for (int r = 0; r < data.length; r++)
        {
            for (int c = 0; c < data[0].length; c++)
            {
                p1 = tens[r];
                p2 = tens[c];
                if (p1 + p2 > 100)
                {
                    data[r][c] = "n/a";
                } else
                {
                    data[r][c] = getAvgData(tens[r], tens[c], 100 - tens[r] - tens[c]);
                }
            }
        }
        for (String[] n : data)
        {
            for (String m : n)
            {
                System.out.print(m + ", ");
            }
            System.out.println();
        }
        return data;
    }
}
