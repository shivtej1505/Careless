package shivangnagaria.careless;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created on 11/8/15.
 */
public class docShower extends ListActivity {

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(docShower.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new loadData().execute();
        //getListView().setAdapter(adapter);
    }

    private class loadData extends AsyncTask<Void,Void,docAdapter> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(docShower.this);
            progressDialog.setMessage("Reading database");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected docAdapter doInBackground(Void... params) {
            docAdapter adapter = new docAdapter(docShower.this);
            dbOpenHelper myHelper = new dbOpenHelper(docShower.this);
            SQLiteDatabase db = myHelper.getReadableDatabase();
            String[] projection = {
                    myHelper.COLUMN_ID,
                    myHelper.COLUMN_TYPE,
                    myHelper.COLUMN_MAMOUNT,
                    myHelper.COLUMN_MDATE,
                    myHelper.COLUMN_PAMOUNT,
                    myHelper.COLUMN_PDATE,
                    myHelper.COLUMN_SPCF
            };
            Cursor readData = db.query(
                    myHelper.TABLE_NAME, projection,null, null, null, null, null);
            dbData data = new dbData(docShower.this,null,null,null,null,null,null,null);
            if (readData.moveToFirst()) {
                do {
                    data.setDataId(readData.getInt(readData.getColumnIndex(myHelper.COLUMN_ID)));
                    data.setDataType(readData.getString(readData.getColumnIndex(myHelper.COLUMN_TYPE)));
                    data.setDataMamt(readData.getString(readData.getColumnIndex(myHelper.COLUMN_MAMOUNT)));
                    data.setDataMdate(readData.getString(readData.getColumnIndex(myHelper.COLUMN_MDATE)));
                    data.setDataPamt(readData.getString(readData.getColumnIndex(myHelper.COLUMN_PAMOUNT)));
                    data.setDataPdate(readData.getString(readData.getColumnIndex(myHelper.COLUMN_PDATE)));
                    data.setDataSpcf(readData.getString(readData.getColumnIndex(myHelper.COLUMN_SPCF)));
                    adapter.add(data);
                } while (readData.moveToNext());
            }
            readData.close();
            return adapter;
        }

        @Override
        protected void onPostExecute(docAdapter adapter) {
            super.onPostExecute(adapter);
            progressDialog.dismiss();
            if(adapter != null) {
                getListView().setAdapter(adapter);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_entry,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        LinearLayout changePasswordDialog = (LinearLayout) getLayoutInflater().
                inflate(R.layout.change_password_dialog_view, null);
        EditText passwordField = (EditText) changePasswordDialog.findViewById(R.id.passwordField);
        EditText passwordFieldConfirm = (EditText) changePasswordDialog.findViewById(R.id.passwordFieldConfirm);

        int id = item.getItemId();
        if(id == R.id.action_add_new) {
            Intent jmptoAddNew = new Intent(docShower.this,addNew.class);
            startActivity(jmptoAddNew);
        }
        else if(id == R.id.action_settings){
            final AlertDialog.Builder builder = new AlertDialog.Builder(docShower.this);
            builder.setTitle(R.string.changePassword).setView(changePasswordDialog)
                    .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(docShower.this,"Change password",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }).create();
            builder.show();
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
