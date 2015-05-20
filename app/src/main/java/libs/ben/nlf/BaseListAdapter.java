package libs.ben.nlf;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class BaseListAdapter extends BaseAdapter {

    protected List<Object> listData;
    protected int listCount = 0;
    private boolean isDataPreparing = false;

    public void setListData(List<Object> data) {
        if(data == null)
            return;
        listData = data;
        listCount = data.size();
        isDataPreparing = false;
    }

    public void appendListData(List<Object> data){
        if(data == null)
            return;
        if(listData == null)
            listData = data;
        else listData.addAll(data);
        listCount = listData.size();
        isDataPreparing = false;
    }

    @Override
    public int getCount() {
        return listCount;
    }

    @Override
    public Object getItem(int position) {
        if(listData == null)
            return null;
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView == null){
            v = createListItemView();
        }
        else{
            v = convertView;
        }
        setupListItemView(v, position);

        if(isDataPreparing || isLastPage())
            return v;

        if(position > listCount - 3) {
            isDataPreparing = true;
            loadNextPageData();
        }

        return v;
    }

    abstract protected View createListItemView();
    abstract protected void setupListItemView(View v, int position);
    abstract protected boolean isLastPage();
    abstract protected void loadNextPageData();
}
