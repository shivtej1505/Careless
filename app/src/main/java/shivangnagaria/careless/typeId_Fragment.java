package shivangnagaria.careless;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created on 18/8/15.
 */
public class typeId_Fragment extends Fragment {



    Spinner docTypeSpin;
    EditText docPolNo;
    Button cancelBtn,nextBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_type_id,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        docTypeSpin = (Spinner) getActivity().findViewById(R.id.objType);
        docPolNo = (EditText) getActivity().findViewById(R.id.objId);

        docTypeSpin.setAdapter(
                ArrayAdapter.createFromResource(
                        getActivity().getApplicationContext(),
                        R.array.docTypes,
                        R.layout.dropdown_item
                ));

        cancelBtn = (Button) getActivity().findViewById(R.id.cancelFrag_type_id);
        nextBtn = (Button) getActivity().findViewById(R.id.nextFrag);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew.prevFrag(1);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew.nextFrag(1);
            }
        });
    }
}
