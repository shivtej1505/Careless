package shivangnagaria.careless;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created on 11/8/15.
 */
public class docShower extends ListActivity {

    ProgressDialog progressDialog;
    docAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(docShower.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new loadData().execute();
        getListView().setAdapter(adapter);
    }

    private class loadData extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*
            progressDialog.setMessage("Reading database");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected Void doInBackground(Void... params) {
            adapter = new docAdapter(docShower.this);
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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //progressDialog.dismiss();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_entry,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_add_new) {
            Intent jmptoAddNew = new Intent(docShower.this,addNew.class);
            startActivity(jmptoAddNew);
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
