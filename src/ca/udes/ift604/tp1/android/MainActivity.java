package ca.udes.ift604.tp1.android;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ca.udes.ift604.tp1.client.ClientUDP;

import com.example.tp2.R;

public class MainActivity extends Activity
{

    public static final String SERVERIP = "192.168.2.21";
    public static final int SERVERPORT = 8000;

    private ClientUDP clientUDP;
    private InetAddress ip;

    public TextView dateText;
    public TextView periodText;
    public TextView timeText;
    public TextView teamText;
    public TextView team1Text;
    public TextView team2Text;
    public TextView goalText;
    public TextView goal1Text;
    public TextView goal2Text;
    public TextView penaltyText;
    public TextView penalty1Text;
    public TextView penalty2Text;
    public Button btnUpdateMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);

        dateText = (TextView) findViewById(R.id.dateText);
        periodText = (TextView) findViewById(R.id.periodText);
        timeText = (TextView) findViewById(R.id.timeText);
        teamText = (TextView) findViewById(R.id.teamText);
        team1Text = (TextView) findViewById(R.id.team1Text);
        team2Text = (TextView) findViewById(R.id.team2Text);
        goalText = (TextView) findViewById(R.id.goalText);
        goal1Text = (TextView) findViewById(R.id.goal1Text);
        goal2Text = (TextView) findViewById(R.id.goal2Text);
        penaltyText = (TextView) findViewById(R.id.penaltyText);
        penalty1Text = (TextView) findViewById(R.id.penalty1Text);
        penalty2Text = (TextView) findViewById(R.id.penalty2Text);

        btnUpdateMatch = (Button) findViewById(R.id.btnUpdateMatch);
        
        try
        {
            ip = InetAddress.getByName(SERVERIP);
            clientUDP = new ClientUDP(ip, SERVERPORT);

            clientUDP.execute("update");

        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        btnUpdateMatch.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    clientUDP = new ClientUDP(ip, SERVERPORT);
                    clientUDP.execute("update");

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if (clientUDP.clientOk)
                            {
                                update(0);
                            }
                        }
                    }, 700);

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
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

    private void update(int index)
    {
        dateText.setText(clientUDP.getListMatch().get(index).getDate().toString());
        periodText.setText(clientUDP.getListMatch().get(index).getPeriod() + " Per");

        long millis = clientUDP.getListMatch().get(index).getChrono().getTimeLeft();
        int min = (int) (millis / 60000);
        millis -= (min * 60000);
        int sec = (int) ((millis / 1000) % 60);
        
        timeText.setText(String.format("%02d : %02d",min,sec));

        team1Text.setText(clientUDP.getListMatch().get(index).getTeam1());
        team2Text.setText(clientUDP.getListMatch().get(index).getTeam2());
        goal1Text.setText(""+clientUDP.getListMatch().get(index).getGoalTeam1());
        goal2Text.setText(""+clientUDP.getListMatch().get(index).getGoalTeam2());
        penalty1Text.setText(""+clientUDP.getListMatch().get(index).getPenaltyTeam1());
        penalty2Text.setText(""+clientUDP.getListMatch().get(index).getPenaltyTeam2());
    }
}
