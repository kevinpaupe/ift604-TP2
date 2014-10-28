package ca.udes.ift604.tp1.match;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -4646585754834531203L;
    
    /*------------------------------------------------------------------*\
    |*                          Attributs Private                       *|
    \*------------------------------------------------------------------*/
    
    private Date date;
    private String team1;
    private String team2;
    private int goalTeam1;
    private int goalTeam2;
    private List<Goal> listGoal1;
    private List<Goal> listGoal2;
    private int penaltyTeam1; // nombre de joueur penalise en temps reel
    private int penaltyTeam2;
    private int period; // seulement 1, 2 ou 3
    private StateMatch state;
    private Chrono chrono;
    private String name;
    private int numMatch;

    public enum StateMatch
    {
        JEU, SUSPENDU, FINI
    }

    /*------------------------------------------------------------------*\
    |*                          Constructeurs                           *|
    \*------------------------------------------------------------------*/

    public Match(Date date, String team1, String team2, String name, int numMatch)
    {
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.name = name;
        this.numMatch = numMatch;
        goalTeam1 = 0;
        goalTeam2 = 0;
        listGoal1 = new ArrayList<Goal>();
        listGoal2 = new ArrayList<Goal>();
        penaltyTeam1 = 0;
        penaltyTeam2 = 0;
        period = 1;
        chrono = new Chrono(1200000); // 20 minutes par périodes
        state = StateMatch.SUSPENDU;
    }

    /*------------------------------------------------------------------*\
    |*                          Methodes Public                         *|
    \*------------------------------------------------------------------*/

    public void team1Goal(Goal goal)
    {
        goalTeam1++;
        listGoal1.add(goal);
    }

    public void team2Goal(Goal goal)
    {
        goalTeam2++;
        listGoal2.add(goal);
    }

    public void team1Penalty()
    {
        penaltyTeam1++;
    }

    public void team2Penalty()
    {
        penaltyTeam2++;
    }

    public void team1EndPenalty()
    {
        if (penaltyTeam1 > 0)
        {
            penaltyTeam1--;
        }
    }

    public void team2EndPenalty()
    {
        if (penaltyTeam2 > 0)
        {
            penaltyTeam2--;
        }
    }

    public void pause()
    {
        chrono.pause();
        this.state = StateMatch.SUSPENDU;
    }

    public void start()
    {
        chrono.start();
        this.state = StateMatch.JEU;

        Thread threadChrono = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (state != StateMatch.FINI)
                {
                    if (chrono.isRunning())
                    {
                        if (period < 3 && chrono.isFinish())
                        {
                            period++;
                            // chrono.start();
                        } else if (period == 3 && chrono.isFinish())
                        {
                            state = StateMatch.FINI;
                            System.out.println("Fin du match");
                        }
                    }
                }
            }
        });
        threadChrono.start();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Match :\nEquipes : ");
        builder.append(team1);
        builder.append(" - ");
        builder.append(team2);

        builder.append("\nMatch du ");
        builder.append(date.toString());

        builder.append("\nPeriod : ");
        builder.append(period);
        builder.append("\nTemps restant : ");
        long millis = chrono.getTimeLeft();
        int min = (int) (millis / 60000);
        millis -= (min * 60000);
        int sec = (int) ((millis / 1000) % 60);
        builder.append(min + ":" + sec);

        builder.append("\nScore : ");
        builder.append(goalTeam1);
        builder.append(" - ");
        builder.append(goalTeam2);

        builder.append("\nPenalités : ");
        builder.append(penaltyTeam1);
        builder.append(" - ");
        builder.append(penaltyTeam2);
        builder.append("\n");
        return builder.toString();
    }

    /*------------------------------*\
    |*              Set             *|
    \*------------------------------*/

    /*
     * public void setButEquipe1(int butEquipe1) { this.butEquipe1 = butEquipe1;
     * } public void setButEquipe2(int butEquipe2) { this.butEquipe2 =
     * butEquipe2; } public void setPenaliteEquipe1(int penaliteEquipe1) {
     * this.penaliteEquipe1 = penaliteEquipe1; } public void
     * setPenaliteEquipe2(int penaliteEquipe2) { this.penaliteEquipe2 =
     * penaliteEquipe2; } public void setEquipe1(Equipe equipe1) { this.equipe1
     * = equipe1; } public void setEquipe2(Equipe equipe2) { this.equipe2 =
     * equipe2; } public void setTiersTemps(int tiersTemps) { this.tiersTemps =
     * tiersTemps; }
     */

    public void setDate(Date date)
    {
        this.date = date;
    }

    /*------------------------------*\
    |*              Get             *|
    \*------------------------------*/

    public int getGoalTeam1()
    {
        return goalTeam1;
    }

    public int getGoalTeam2()
    {
        return goalTeam2;
    }

    public List<Goal> getlistGoal1()
    {
        return listGoal1;
    }

    public List<Goal> getlistGoal2()
    {
        return listGoal2;
    }

    public int getPenaltyTeam1()
    {
        return penaltyTeam1;
    }

    public int getPenaltyTeam2()
    {
        return penaltyTeam2;
    }

    public int getPeriod()
    {
        return period;
    }

    public String getTeam1()
    {
        return team1;
    }

    public String getTeam2()
    {
        return team2;
    }

    public Date getDate()
    {
        return date;
    }

    public Chrono getChrono()
    {
        return chrono;
    }

    public String getName()
    {
        return name;
    }

    public StateMatch getStateMatch()
    {
        return state;
    }

    public int getNumMatch()
    {
        return numMatch;
    }

    /*------------------------------------------------------------------*\
    |*                          Methodes Private                        *|
    \*------------------------------------------------------------------*/
}
