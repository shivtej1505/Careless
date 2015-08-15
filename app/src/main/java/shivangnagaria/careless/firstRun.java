package shivangnagaria.careless;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onStart() {

        super.onStart();

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        if(preferences.getBoolean(easyShort.prefs.FIRST_RUN_DONE,false)) {
            Intent jmp = new Intent(firstRun.this,entryApp.class);
            startActivity(jmp);
            finish();
        }
    }

    String pickQ,ansE,userPassword;
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
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                firstRun.this,R.array.hintQ,R.layout.dropdown_item);
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
                        Toast.makeText(firstRun.this,"Password didn't match",Toast.LENGTH_SHORT).show();
                        pass.setText(null);
                        passAgain.setText(null);
                } else if(pass.getText().toString().length() < 8 || passAgain.getText().toString().length() < 8 ) {
                    Toast.makeText(firstRun.this, "Minimum password length 8", Toast.LENGTH_SHORT).show();
                    pass.setText(null);
                    passAgain.setText(null);
                }else if(ansE.equals("")) {
                    Toast.makeText(firstRun.this,"Please provide answer",Toast.LENGTH_SHORT).show();
                } else {
                    // save details in SharedPreferences
                    userPassword = pass.getText().toString();

                    // saving details
                    new backWork().execute();

                    Intent jmpToentry = new Intent(firstRun.this,entryApp.class);
                    startActivity(jmpToentry);
                }
            }
        });
    }


    private class backWork extends AsyncTask<Void,Void,Void>{

        ProgressDialog mProgressDialog = new ProgressDialog(firstRun.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.setMessage(getResources().getString(R.string.savingData));
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            // saving details
            Log.i(easyShort.TAG,pickQ);
            Log.i(easyShort.TAG,ansE);
            Log.i(easyShort.TAG, userPassword);

            SharedPreferences.Editor editor = getSharedPreferences(easyShort.prefs.PREFS_NAME,MODE_PRIVATE).edit();
            editor.putString(easyShort.prefs.HINT_QUES, pickQ);
            editor.putString(easyShort.prefs.HINT_ANS, ansE);
            editor.putString(easyShort.prefs.PASS, userPassword);
            editor.putBoolean(easyShort.prefs.FIRST_RUN_DONE, true);
            editor.commit();

            SQLiteDatabase db = new dbOpenHelper(firstRun.this).getWritableDatabase();
            new dbOpenHelper(firstRun.this).onCreate(db);

            ContentValues values = new ContentValues();
            values.put(dbOpenHelper.COLUMN_ID,1);
            values.put(dbOpenHelper.COLUMN_TYPE,"Fixed deposit");
            values.put(dbOpenHelper.COLUMN_MAMOUNT,1001);
            values.put(dbOpenHelper.COLUMN_MDATE,"1995-05-15");
            values.put(dbOpenHelper.COLUMN_PAMOUNT,1001);
            values.put(dbOpenHelper.COLUMN_PDATE,"1995-05-15");
            values.put(dbOpenHelper.COLUMN_SPCF,"shivang nagaria");

            db.insert(dbOpenHelper.TABLE_NAME,null,values);
            values.clear();
            db.close();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressDialog.dismiss();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nothing, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(firstRun.this,"Settings",Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_credits) {
            Toast.makeText(firstRun.this,"shivang nagaria",Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.action_bugs) {
            Toast.makeText(firstRun.this,"bug reported",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
