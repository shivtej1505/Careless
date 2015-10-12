package shivangnagaria.careless;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created on 18/8/15.
 */
public class mAmtDate_spf_Fragment extends Fragment {

    TextView dataType,dataId,dataSpf;
    Button backBtn,nextBtn,dateBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mamtdate_spf,container,false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        backBtn = (Button) getActivity().findViewById(R.id.cancelFrag_m_spf);
        nextBtn = (Button) getActivity().findViewById(R.id.nextFrag);
        dataId = (TextView) getActivity().findViewById(R.id.myDataId);
        dataType = (TextView) getActivity().findViewById(R.id.myDataType);
        dataSpf = (TextView) getActivity().findViewById(R.id.myDataSpc);
        dateBtn = (Button) getActivity().findViewById(R.id.mDateChoose);

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(),"datePicker");
            }
        });

        final Bundle bundle = getArguments();
        dataId.setText(bundle.getString(easyShort.fragsPrefs.FRAGPREFS_ID, "Policy No."));
        dataType.setText(bundle.getString(easyShort.fragsPrefs.FRAGPREFS_TYPE, "Select Type"));
        dataSpf.setText(bundle.getString(easyShort.fragsPrefs.FRAGPREFS_SPF, "Bank"));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew.prevFrag(2);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew.nextFrag(2,bundle);
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),this,year,month,day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.i(easyShort.TAG,year+":"+monthOfYear+":"+dayOfMonth);
        }
    }
}
