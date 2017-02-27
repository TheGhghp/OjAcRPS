import java.lang.Math;
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
    
    private char iterate(int tier, char[] moves)
    {
        char[] choiceSet = {'r','p','s'};
        tier %= 3;
        char choice = strat1(moves);
        //hi
    }
}
