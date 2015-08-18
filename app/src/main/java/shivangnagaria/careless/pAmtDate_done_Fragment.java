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
public class pAmtDate_done_Fragment extends Fragment {

    addNew AddNew = new addNew();
    Button backBtn,doneBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pamtdate_done,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        backBtn = (Button) getActivity().findViewById(R.id.cancelFrag_done);
        doneBtn = (Button) getActivity().findViewById(R.id.doneBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNew.prevFrag(3);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNew.terminateActivity();
            }
        });
    }
}
