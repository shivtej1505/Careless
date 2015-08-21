package shivangnagaria.careless;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.FragmentManager;
import android.widget.ArrayAdapter;

/**
 * Created on 13/8/15.
 */
public class addNew extends Activity {

    private static final Fragment typeIdFragment = new typeId_Fragment();
    private static final Fragment mAmtDateFragment = new mAmtDate_spf_Fragment();
    private static final Fragment pAmtDateFragment = new pAmtDate_done_Fragment();

    private static FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addnew_doc);
        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,typeIdFragment);
        fragmentTransaction.commit();
    }

    public static void nextFrag(int FragmentNo) {
        if(FragmentNo == 1) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, mAmtDateFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if(FragmentNo == 2) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, pAmtDateFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public static void prevFrag(int FragmentNo) {
        mFragmentManager.popBackStackImmediate();
    }

}
