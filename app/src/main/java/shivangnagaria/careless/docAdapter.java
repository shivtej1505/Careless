package shivangnagaria.careless;

import android.content.Context;
import android.util.Log;
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

    public void add(dbData data) {
        mDocs.add(data);
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

        dbData data = getItem(position);

        ImageView docImg;
        TextView docId,docSpf,docMamt,docMdate;
        docImg = (ImageView) convertView.findViewById(R.id.docDataImg);
        docId = (TextView) convertView.findViewById(R.id.docDataId);
        docSpf = (TextView) convertView.findViewById(R.id.docDataSpf);
        docMamt = (TextView) convertView.findViewById(R.id.docDataMamt);
        docMdate = (TextView) convertView.findViewById(R.id.docDataMdate);

        String dataType = data.getDataType();
        switch (dataType) {
            case "Fixed deposit" :
                docImg.setImageResource(R.drawable.doc_type_f);
                break;
            case "Insurance":
                docImg.setImageResource(R.drawable.doc_type_i);
                break;
            case "Vehicle Insurance" :
                docImg.setImageResource(R.drawable.doc_type_v);
                break;
            case "Mutual funds" :
                docImg.setImageResource(R.drawable.doc_type_s);
                break;
            default:
                docImg.setImageResource(R.drawable.doc_type_o);
                break;
        }
        Log.i(easyShort.TAG,""+data.getDataId());

        docId.setText(data.getDataId().toString());
        docSpf.setText(data.getDataSpcf());
        docMamt.setText(data.getDataMamt());
        docMdate.setText(data.getDataMdate());
        return convertView;
    }
}
