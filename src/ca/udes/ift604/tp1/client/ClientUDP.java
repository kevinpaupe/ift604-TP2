package ca.udes.ift604.tp1.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import ca.udes.ift604.tp1.match.Match;
import ca.udes.ift604.tp1.tools.Tools;

public class ClientUDP extends AsyncTask<String, Void, Void>
{
    /*------------------------------------------------------------------*\
    |*                          Attributs Private                       *|
    \*------------------------------------------------------------------*/
 
    private final DatagramSocket clientSocket;
    private InetAddress serverAddress;
    private int serverPort;
    private List<Match> listMatch;
    private static final int size = 1024;

    public boolean clientOk;
    
    static byte sendBuffer[] = new byte[size];

    /*------------------------------------------------------------------*\
    |*                          Constructeurs                           *|
    \*------------------------------------------------------------------*/

    public ClientUDP(InetAddress serverAddress, int serverPort) throws IOException
    {
        clientSocket = new DatagramSocket();
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        clientOk = false;
    }
 
    /*------------------------------------------------------------------*\
    |*                          Methodes Public                         *|
    \*------------------------------------------------------------------*/

    @Override
    protected Void doInBackground(String... params)
    {
        try
        {
            Log.i("ClientUDP", "Client Start : " + serverAddress);

            String request = params[0];

            // On demande la liste de match
            sendBuffer = request.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);

            clientSocket.send(sendPacket);

            // On re�oit en premier la taille de la liste
            byte[] receiveSizeBuffer = new byte[size];
            DatagramPacket sizeListPacket = new DatagramPacket(receiveSizeBuffer, receiveSizeBuffer.length);
            clientSocket.receive(sizeListPacket);

            int sizeList = (Integer) Tools.deserealizer(sizeListPacket.getData());

            Log.i("ClientUDP", "Taille : " + sizeList);

            sendBuffer = new String("ok0").getBytes();
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // On re�oit la liste complete
            listMatch = new ArrayList<Match>();
            for (int i = 1; i < sizeList; i++)
            {
                byte[] receiveBuffer = new byte[size];
                DatagramPacket replyPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                // Reception Bloquante
                clientSocket.receive(replyPacket);

                Log.i("ClientUDP", "Reception");
                listMatch.add((Match) Tools.deserealizer(replyPacket.getData()));

                
                sendBuffer = new String("ok" + i).getBytes();
                sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);
                Log.i("ClientUDP", "R�ponse : ok"+i);
            }

        } catch (Exception e)
        {
            Log.e("ClientUDP", "Error Client");
            e.printStackTrace();
        } finally
        {
            clientSocket.close();
        }

        return null; 
    }

    @Override
    public void onPostExecute(Void result)
    {      
        clientSocket.close();
        Log.i("ClientUDP", "Client Ok");
        clientOk = true;
    }

    /*------------------------------*\
    |*              Get             *|
    \*------------------------------*/

    public List<Match> getListMatch()
    {
        return listMatch;
    }

    public Match getMatch(String nomMatch)
    {
        // On recherche le match qui nous interesse
        int index = findIndexMatch(nomMatch);
        if (index == -1)
        {
            Log.e("ClientUDP", "Erreur de la requette");
            return new Match(new Date(), "Error", "Error", "Error", -1);
        } else
        {
            return listMatch.get(index);
        }
    }

    /*------------------------------------------------------------------*\
    |*                          Methodes Private                        *|
    \*------------------------------------------------------------------*/

    private int findIndexMatch(String match)
    {
        int i = 0, ret = -1;
        for (Match element : listMatch)
        {
            if (match.contains(element.getName()))
            {
                ret = i;
            }
            i++;
        }
        return ret;
    }

}