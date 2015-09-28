package shivangnagaria.careless;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created on 18/8/15.
 */
public class pAmtDate_done_Fragment extends Fragment {

    TextView dataType,dataId,dataSpf;
    Button backBtn,doneBtn;
    Bundle bundle;
    private ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pamtdate_done,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        backBtn = (Button) getActivity().findViewById(R.id.cancelFrag_done);
        doneBtn = (Button) getActivity().findViewById(R.id.doneBtn);

        dataId = (TextView) getActivity().findViewById(R.id.myDataId);
        dataType = (TextView) getActivity().findViewById(R.id.myDataType);
        dataSpf = (TextView) getActivity().findViewById(R.id.myDataSpc);
        
        bundle = getArguments();
        dataId.setText(bundle.getString(easyShort.fragsPrefs.FRAGPREFS_ID,"Policy No."));
        dataType.setText(bundle.getString(easyShort.fragsPrefs.FRAGPREFS_TYPE,"Select Type"));
        dataSpf.setText(bundle.getString(easyShort.fragsPrefs.FRAGPREFS_SPF, "Bank"));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew.prevFrag(3);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new saveOnDB().execute();
                Toast.makeText(getActivity().getApplicationContext(), "Closing", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

    }
    private class saveOnDB extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*
            progressDialog = new ProgressDialog(getActivity().getApplicationContext());
            progressDialog.setMessage("Saving data");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected Void doInBackground(Void... params) {
            SQLiteDatabase db = new dbOpenHelper(getActivity().getApplicationContext()).getWritableDatabase();
            dbOpenHelper helper = new dbOpenHelper(getActivity().getApplicationContext());

            ContentValues values = new ContentValues();
            values.put(helper.COLUMN_ID,bundle.getString(easyShort.fragsPrefs.FRAGPREFS_ID,""));
            values.put(helper.COLUMN_TYPE,bundle.getString(easyShort.fragsPrefs.FRAGPREFS_TYPE,""));
            values.put(helper.COLUMN_SPCF,bundle.getString(easyShort.fragsPrefs.FRAGPREFS_SPF,""));
            values.put(helper.COLUMN_MAMOUNT,1000);
            values.put(helper.COLUMN_MDATE,1995-05-15);
            values.put(helper.COLUMN_PAMOUNT,1995-05-15);
            values.put(helper.COLUMN_PDATE, 1000);

            long success = db.insert(helper.TABLE_NAME,null,values);
            Log.i(easyShort.TAG,"Success:"+success);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //progressDialog.dismiss();
        }
    }
}
