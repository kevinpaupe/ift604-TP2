package ca.udes.ift604.tp1.match;

import java.io.Serializable;

public class Goal implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 5844932917320517647L;
    
    /*------------------------------------------------------------------*\
    |*                          Attributs Private                       *|
    \*------------------------------------------------------------------*/

    private long time;
    private String playerGoal;
    private String firstAssist;
    private String secondAssist;

    /*------------------------------------------------------------------*\
    |*                          Constructeurs                           *|
    \*------------------------------------------------------------------*/

    public Goal(long time, String playerGoal, String firstAssist, String secondAssist)
    {
        this.time = time;
        this.playerGoal = playerGoal;
        this.firstAssist = firstAssist;
        this.secondAssist = secondAssist;
    }

    /*------------------------------------------------------------------*\
    |*                          Methodes Public                         *|
    \*------------------------------------------------------------------*/

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Goal !!!\n");
       
        int min = (int) (time / 60000);
        time -= (min * 60000);
        int sec = (int) ((time / 1000) % 60);
        builder.append(min + ":" + sec);
        
        builder.append("  "+playerGoal);
        
        builder.append("\n( " +firstAssist);
        builder.append(", "+ secondAssist + " )\n");
        
        return builder.toString();
    }

    /*------------------------------*\
    |*              Set             *|
    \*------------------------------*/

  
    /*------------------------------------------------------------------*\
    |*                          Methodes Private                        *|
    \*------------------------------------------------------------------*/
}
