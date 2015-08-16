package shivangnagaria.careless;

import android.content.Context;

/**
 * Created on 12/8/15.
 */
public class dbData {

    private Context mContext;

    // db data specifics
    private Integer dataId;
    private String dataType;
    private String dataMamt;
    private String dataMdate;
    private String dataPamt;
    private String dataPdate;
    private String dataSpcf;

    public dbData(Context context, Integer dataId, String dataType, String dataMamt, String dataMdate, String dataPamt, String dataPdate, String dataSpcf) {
        super();
        this.mContext = context;
        this.dataId = dataId;
        this.dataType = dataType;
        this.dataMamt = dataMamt;
        this.dataMdate = dataMdate;
        this.dataPamt = dataPamt;
        this.dataPdate = dataPdate;
        this.dataSpcf = dataSpcf;
    }


    public String getDataMamt() {
        return dataMamt;
    }

    public void setDataMamt(String dataMamt) {
        this.dataMamt = dataMamt;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataMdate() {
        return dataMdate;
    }

    public void setDataMdate(String dataMdate) {
        this.dataMdate = dataMdate;
    }

    public String getDataPamt() {
        return dataPamt;
    }

    public void setDataPamt(String dataPamt) {
        this.dataPamt = dataPamt;
    }

    public String getDataPdate() {
        return dataPdate;
    }

    public void setDataPdate(String dataPdate) {
        this.dataPdate = dataPdate;
    }

    public String getDataSpcf() {
        return dataSpcf;
    }

    public void setDataSpcf(String dataSpcf) {
        this.dataSpcf = dataSpcf;
    }
}
