package shivangnagaria.careless;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class firstRun extends Activity {

    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
    @Override
    protected void onStart() {

        if(preferences.getBoolean(easyShort.prefs.FIRST_RUN_DONE,false)) {
            Intent jmp = new Intent(firstRun.this,appEntry.class);
            startActivity(jmp);
            return;
        }
        super.onStart();
    }

    String pickQ,ansE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // creating reference
        setContentView(R.layout.activity_first_run);
        final EditText pass = (EditText) findViewById(R.id.pass);
        final EditText passAgain = (EditText) findViewById(R.id.passAgain);
        Spinner spinQ = (Spinner) findViewById(R.id.spinQ);
        final EditText ansQ = (EditText) findViewById(R.id.ansQ);
        Button saveBtn = (Button) findViewById(R.id.saveBtn);

        // setting adapter to spinner
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(firstRun.this,R.array.hintQ,R.layout.support_simple_spinner_dropdown_item);
        spinQ.setAdapter(arrayAdapter);
        spinQ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pickQ = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(firstRun.this,"Please select a question",Toast.LENGTH_SHORT).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansE = ansQ.getText().toString();
                if(!(pass.getText().toString().equals(passAgain.getText().toString()))) {
                    if(pass.getText().toString().length() < 8 || passAgain.getText().toString().length() < 8 ) {
                        Toast.makeText(firstRun.this, "Minimum password length 8", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(firstRun.this,"Password didn't match",Toast.LENGTH_SHORT).show();
                    }
                } else if(ansE.equals("")) {
                    Toast.makeText(firstRun.this,"Please provide answer",Toast.LENGTH_SHORT).show();
                } else {
                    // save details in SharedPreferences
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(easyShort.prefs.HINT_QUES,pickQ);
                    editor.putString(easyShort.prefs.HINT_ANS,ansE);
                    editor.putString(easyShort.prefs.PASS,pass.getText().toString());
                    editor.putBoolean(easyShort.prefs.FIRST_RUN_DONE,true);
                    editor.commit();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_run, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
