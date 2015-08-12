package shivangnagaria.careless;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created on 10/8/15.
 */
public class entryApp extends Activity {

    String entredPass,hintQues,hintAns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entry_app);

        // get shared preference
        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final String userPass = preferences.getString(easyShort.prefs.PASS, null);

        final EditText entryPass = (EditText) findViewById(R.id.entryPass);
        Button entryBtn = (Button) findViewById(R.id.enterBtn);
        Button forgetBtn = (Button) findViewById(R.id.forgetBtn);

        entryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entredPass = entryPass.getText().toString();
                if(entredPass.equals(userPass)) {
                    Intent jmpToDocs = new Intent(entryApp.this,docShower.class);
                    startActivity(jmpToDocs);
                } else {
                    Toast.makeText(entryApp.this,"Wrong password!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintQues = preferences.getString(easyShort.prefs.HINT_QUES,"What is your favorite color?");
                hintAns = preferences.getString(easyShort.prefs.HINT_ANS,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(entryApp.this);
                builder.setTitle(R.string.ansQues).setMessage(hintQues).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // TODO: check for hint answer and then pop up appropriate dialog box.
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_entry,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(entryApp.this,"working on " + id,Toast.LENGTH_SHORT).show();
        }
        return super.onMenuItemSelected(featureId, item);
    }

}
