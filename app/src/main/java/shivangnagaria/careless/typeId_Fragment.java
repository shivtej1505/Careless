package shivangnagaria.careless;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created on 18/8/15.
 */
public class typeId_Fragment extends Fragment {

    TextView idHeader;
    String dataType,dataId,dataSpf;
    Spinner docTypeSpin;
    EditText docPolNo,docSpf;
    Button cancelBtn,nextBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_type_id,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final String[] itemTypes = getResources().getStringArray(R.array.docTypes);
        final String[] numHeader = getResources().getStringArray(R.array.policyNum);

        idHeader = (TextView) getActivity().findViewById(R.id.objIdHead);
        docTypeSpin = (Spinner) getActivity().findViewById(R.id.objType);
        docPolNo = (EditText) getActivity().findViewById(R.id.objId);
        docSpf = (EditText) getActivity().findViewById(R.id.objSpf);

        idHeader.setText(numHeader[0]);
        docTypeSpin.setAdapter(
                ArrayAdapter.createFromResource(
                        getActivity().getApplicationContext(),
                        R.array.docTypes,
                        R.layout.dropdown_item
                ));

        docTypeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataType = parent.getItemAtPosition(position).toString();

                if(dataType.equals(itemTypes[3])){
                    idHeader.setText(numHeader[3]);
                } else if(dataType.equals(itemTypes[4])){
                    idHeader.setText(numHeader[2]);
                } else if(dataType.equals(itemTypes[7])){
                    idHeader.setText(numHeader[0]);
                } else {
                    idHeader.setText(numHeader[1]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity().getApplicationContext(), "Please select item type.", Toast.LENGTH_SHORT).show();
            }
        });

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
                dataId = docPolNo.getText().toString();
                dataSpf = docSpf.getText().toString();
                if (!dataId.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.clear();
                    bundle.putString(easyShort.fragsPrefs.FRAGPREFS_TYPE,dataType);
                    bundle.putString(easyShort.fragsPrefs.FRAGPREFS_ID, dataId);
                    bundle.putString(easyShort.fragsPrefs.FRAGPREFS_SPF, dataSpf);
                    addNew.nextFrag(1,bundle);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please provide policy number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
