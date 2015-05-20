package libs.ben.sample;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import libs.ben.nlf.BaseListAdapter;
import libs.ben.nlf.NetworkListFragment;

public class SampleListFragment extends NetworkListFragment {

    public static SampleListFragment newInstance(){
        return new SampleListFragment();
    }

    public SampleListFragment() {}

    @Override
    protected String getEmptyHint() {
        return "no data";
    }

    private BaseListAdapter adapter;
    @Override
    protected BaseListAdapter getAdapter() {
        if(adapter == null)
            adapter = new BaseListAdapter() {

                @Override
                public String getItem(int position) {
                    return listData.get(position).toString();
                }

                @Override
                protected View createListItemView() {
                    return View.inflate(getActivity(), android.R.layout.simple_list_item_1, null);
                }

                @Override
                protected void setupListItemView(View v, int position) {
                    ((TextView)v).setText(getItem(position));
                }

                @Override
                protected boolean isLastPage() {
                    return false;
                }

                @Override
                protected void loadNextPageData() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<Object> data=new ArrayList<>();
                            int i=0, len=8;
                            while (i<len){
                                data.add(String.valueOf(i));
                                i++;
                            }
                            onDataLoaded(data);
                        }
                    }, 1000);
                }
            };
        return adapter;
    }

    @Override
    public void prepareListData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Object> data=new ArrayList<>();
                int i=0, len=16;
                while (i<len){
                    data.add(String.valueOf(i));
                    i++;
                }
                onDataLoaded(data);
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        prepareListData();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("D", "onListItemClick:"+position);
    }
}
