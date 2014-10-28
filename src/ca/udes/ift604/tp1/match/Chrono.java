package ca.udes.ift604.tp1.match;

import java.io.Serializable;

public class Chrono implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -9159146217082064149L;
    
    /*------------------------------------------------------------------*\
    |*							Constructeurs							*|
    \*------------------------------------------------------------------*/

    public Chrono(long timeTotal)
    {
        isRunning = false;
        timeStart = -1;
        timeRunnig = 0;
        this.timeTotal = timeTotal;
    }

    /*------------------------------------------------------------------*\
    |*							Methodes Public							*|
    \*------------------------------------------------------------------*/

    public void start()
    {
        isRunning = true;
        timeStart = System.currentTimeMillis();
    }

    public void pause()
    {
        isRunning = false;
        timeRunnig += System.currentTimeMillis() - timeStart;
    }

    public void reset()
    {
        isRunning = false;
        timeStart = -1;
        timeRunnig = 0;
    }

    public boolean isFinish()
    {
        if (getTimeRunning() >= timeTotal)
        {
            reset();
            return true;
        } else
        {
            return false;
        }
    }

    /*------------------------------*\
    |*				Set				*|
    \*------------------------------*/

    /*------------------------------*\
    |*				Get				*|
    \*------------------------------*/

    // Temps ecoulé
    public long getTimeRunning()
    {
        if (isRunning == true)
        {
            return timeRunnig + (System.currentTimeMillis() - timeStart);
        } else
        {
            return timeRunnig;
        }
    }

    // Temps restant
    public long getTimeLeft()
    {
        return timeTotal - getTimeRunning();
    }

    public boolean isRunning()
    {
        return isRunning;
    }

    /*------------------------------------------------------------------*\
    |*							Methodes Private						*|
    \*------------------------------------------------------------------*/

    /*------------------------------------------------------------------*\
    |*							Attributs Private						*|
    \*------------------------------------------------------------------*/

    // Tools
    private long timeStart;
    private long timeRunnig;
    private long timeTotal;
    private boolean isRunning;
}
