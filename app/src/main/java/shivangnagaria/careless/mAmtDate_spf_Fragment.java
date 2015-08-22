package shivangnagaria.careless;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created on 18/8/15.
 */
public class mAmtDate_spf_Fragment extends Fragment {

    TextView dataType,dataId,dataSpf;
    Button backBtn,nextBtn;
    SharedPreferences preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mamtdate_spf,container,false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preferences = getActivity().getSharedPreferences(easyShort.fragsPrefs.FRAGPREFS_NAME, Context.MODE_PRIVATE);
        backBtn = (Button) getActivity().findViewById(R.id.cancelFrag_m_spf);
        nextBtn = (Button) getActivity().findViewById(R.id.nextFrag);
        dataId = (TextView) getActivity().findViewById(R.id.myDataId);
        dataType = (TextView) getActivity().findViewById(R.id.myDataType);
        dataSpf = (TextView) getActivity().findViewById(R.id.myDataSpc);

        dataId.setText(preferences.getString(easyShort.fragsPrefs.FRAGPREFS_ID,"Policy No."));
        dataType.setText(preferences.getString(easyShort.fragsPrefs.FRAGPREFS_TYPE,"Select Type"));
        dataSpf.setText(preferences.getString(easyShort.fragsPrefs.FRAGPREFS_SPF,"Bank"));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew.prevFrag(2);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew.nextFrag(2);
            }
        });
    }
}
