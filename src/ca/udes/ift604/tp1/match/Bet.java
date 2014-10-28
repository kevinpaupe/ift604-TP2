package ca.udes.ift604.tp1.match;

import java.io.Serializable;

public class Bet implements Serializable
{
 
    /**
     * 
     */
    private static final long serialVersionUID = -4872230612930077638L;
    /*------------------------------------------------------------------*\
    |*                          Constructeurs                           *|
    \*------------------------------------------------------------------*/
    public Bet(Match match, User user, int bet, String teambet)
    {
        super();
        this.match = match;
        this.user = user;
        this.sum = bet;
        this.teamBet = teambet;
    }

    /*------------------------------------------------------------------*\
    |*                          Methodes Public                         *|
    \*------------------------------------------------------------------*/

    /*------------------------------*\
    |*              Set             *|
    \*------------------------------*/
    public void setMatch(Match match)
    {
        this.match = match;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setBet(int bet)
    {
        this.sum = bet;
    }

    public void setTeamBet(String teamBet)
    {
        this.teamBet = teamBet;
    }

    /*------------------------------*\
    |*              Get             *|
    \*------------------------------*/
    public Match getMatch()
    {
        return match;
    }

    public User getUser()
    {
        return user;
    }

    public int getBet()
    {
        return sum;
    }

    public String getTeamBet()
    {
        return teamBet;
    }

    public int getNumMatch()
    {
        return match.getNumMatch();
    }

    /*------------------------------------------------------------------*\
    |*                          Attributs Private                       *|
    \*------------------------------------------------------------------*/
    private Match match;
    private String teamBet;
    private User user;
    private int sum;

}
