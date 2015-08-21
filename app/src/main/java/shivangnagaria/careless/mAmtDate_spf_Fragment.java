package shivangnagaria.careless;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created on 18/8/15.
 */
public class mAmtDate_spf_Fragment extends Fragment {


    Button backBtn,nextBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mamtdate_spf,container,false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        backBtn = (Button) getActivity().findViewById(R.id.cancelFrag_m_spf);
        nextBtn = (Button) getActivity().findViewById(R.id.nextFrag);

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
