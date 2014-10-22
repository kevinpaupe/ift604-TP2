package ca.udes.ift604.tp2.match;

import java.io.Serializable;

public class User implements Serializable
{
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