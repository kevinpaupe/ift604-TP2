package ca.udes.ift604.tp2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ca.udes.ift604.tp2.client.ClientUDP;

import com.example.tp2.R;

public class MainActivity extends Activity
{

    public static final String SERVERIP = "10.238.144.40";
    public static final int SERVERPORT = 8000;
    public TextView text1;
    public Button buttonUpdate;

    private ClientUDP clientUDP;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.textView1);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (clientUDP.clientOk)
                {
                    text1.setText(clientUDP.getMatch("match1").getTeam1());
                }
            }
        });

        try
        {
            InetAddress ip = InetAddress.getByName(SERVERIP);
            clientUDP = new ClientUDP(ip, SERVERPORT);

            clientUDP.execute("update");

        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        text1.setText(clientUDP.getMatch("match1").getTeam1());
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
}
