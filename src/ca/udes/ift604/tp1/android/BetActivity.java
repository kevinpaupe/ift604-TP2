package ca.udes.ift604.tp1.android;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.udes.ift604.tp1.client.ClientTCP;
import ca.udes.ift604.tp1.match.Bet;
import ca.udes.ift604.tp1.match.Match;
import ca.udes.ift604.tp1.match.User;

import com.example.tp2.R;

public class BetActivity extends Activity
{

    private ClientTCP clientTCP;
    private InetAddress ip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bet_activity);

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button send = (Button) findViewById(R.id.send_button);
        Log.i("MainActivity", "etape1");
        // clientTCP = new ClientTCP("", port, bet);

        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // connectTask monconnect = new connectTask();
                // monconnect.execute();
                Log.i("MainActivity", "etape2");
                new connectTask().execute();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class connectTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                Log.i("MainActivity", "etape3");
                Bet bet = new Bet(new Match(new Date(), "xamax", "bale", "match1", 125), new User("Toto"), 120, "xamax");
                ip = InetAddress.getByName("192.168.56.1");
                Log.i("MainActivity", "etape4");
                clientTCP = new ClientTCP(ip, 8002, bet);
            } catch (UnknownHostException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Log.i("MainActivity", "etape6");
            clientTCP.start();
            Log.i("MainActivity", "etape20");
            return null;
        }

    }

}
