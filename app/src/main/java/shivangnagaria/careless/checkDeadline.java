package shivangnagaria.careless;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28/9/15.
 */
public class checkDeadline extends BroadcastReceiver {

    Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(easyShort.TAG, "I was called");
        Toast.makeText(context,"I was called",Toast.LENGTH_SHORT).show();

        mContext = context;
        if(getResultCode() == easyShort.CHECK_DEADLINE_CODE){
            new checkDeadlines().execute();
        }
    }

    private class checkDeadlines extends AsyncTask<Void,Void,List<Pair<String ,String >>>{

        @Override
        protected List<Pair<String ,String>> doInBackground(Void... voids) {
            List<Pair<String ,String >> deadlines = new ArrayList<>();
            deadlines.add(Pair.create("Title1 ","Body 1"));
            deadlines.add(Pair.create("Title 2","Body 2"));
            deadlines.add(Pair.create("Title 3","Body 3"));
            return deadlines;
        }

        @Override
        protected void onPostExecute(List<Pair<String, String>> pairs) {
            super.onPostExecute(pairs);
            Log.i(easyShort.TAG,"size"+pairs.size());
            int numDead = pairs.size();
            Uri notifySound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if(numDead > 0) {
                NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(mContext);
                builder.setContentTitle("You have " + numDead + " new notifications").
                        setTicker("You are becoming careless").
                        setSound(notifySound).
                        setSmallIcon(android.R.drawable.ic_dialog_info).setContentText("This is a text");
                notificationManager.notify(easyShort.NOTIFICATION_CODE,builder.build());
            }
        }
    }
}
