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
import android.widget.Toast;

/**
 * Created on 18/8/15.
 */
public class typeId_Fragment extends Fragment {

    String dataType,dataId,dataSpf;
    Spinner docTypeSpin;
    EditText docPolNo,docSpf;
    Button cancelBtn,nextBtn;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_type_id,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        docTypeSpin = (Spinner) getActivity().findViewById(R.id.objType);
        docPolNo = (EditText) getActivity().findViewById(R.id.objId);
        docSpf = (EditText) getActivity().findViewById(R.id.objSpf);

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity().getApplicationContext(),"Please select doc type.",Toast.LENGTH_SHORT).show();
            }
        });

        editor = getActivity().getSharedPreferences(easyShort.fragsPrefs.FRAGPREFS_NAME, Context.MODE_PRIVATE).edit();
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
                if(!dataId.equals("")) {
                    editor.putString(easyShort.fragsPrefs.FRAGPREFS_TYPE,dataType);
                    editor.putString(easyShort.fragsPrefs.FRAGPREFS_ID, dataId);
                    editor.putString(easyShort.fragsPrefs.FRAGPREFS_SPF,dataSpf);
                    addNew.nextFrag(1);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),"Please provide policy number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
