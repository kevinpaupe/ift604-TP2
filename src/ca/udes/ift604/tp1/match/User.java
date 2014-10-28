package ca.udes.ift604.tp1.match;

import java.io.Serializable;

public class User implements Serializable
{   
    /**
     * 
     */
    private static final long serialVersionUID = 4928714776306489956L;

    /*------------------------------------------------------------------*\
    |*                          Constructeurs                           *|
    \*------------------------------------------------------------------*/
    public User(String login)
    {
        super();
        this.login = login;

    }

    /*------------------------------------------------------------------*\
    |*                          Methodes Public                         *|
    \*------------------------------------------------------------------*/

    /*------------------------------*\
    |*              Set             *|
    \*------------------------------*/
    public void setLogin(String login)
    {
        this.login = login;
    }

    /*------------------------------*\
    |*              Get             *|
    \*------------------------------*/
    public String getLogin()
    {
        return login;
    }

    /*------------------------------------------------------------------*\
    |*                          Attributs Private                       *|
    \*------------------------------------------------------------------*/

    private String login;

}