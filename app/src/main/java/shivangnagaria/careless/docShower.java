package shivangnagaria.careless;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created on 11/8/15.
 */
public class docShower extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_entry,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }
}
