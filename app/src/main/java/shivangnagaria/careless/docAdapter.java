package shivangnagaria.careless;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15/8/15.
 */
public class docAdapter extends BaseAdapter {

    private final List<dbData> mDocs = new ArrayList<>();
    private final Context mContext;

    public docAdapter(Context context) {

        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDocs.size();
    }

    @Override
    public dbData getItem(int position) {
        return mDocs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(mContext,R.layout.docshow,null);
        }

        ImageView docImg;
        TextView docId,docSpf,docMamt,docMdate;
        docImg = (ImageView) convertView.findViewById(R.id.docDataImg);
        docId = (TextView) convertView.findViewById(R.id.docDataId);
        docSpf = (TextView) convertView.findViewById(R.id.docDataSpf);
        docMamt = (TextView) convertView.findViewById(R.id.docDataMamt);
        docMdate = (TextView) convertView.findViewById(R.id.docDataMdate);

        return null;
    }
}
