package libs.ben.nlf;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public abstract class NetworkListFragment extends SwipeRefreshListFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected View progressBar;

    public NetworkListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String emptyHint = getEmptyHint();
        if(!TextUtils.isEmpty(emptyHint))
            setEmptyText(emptyHint);

        progressBar = new ProgressBar(getActivity());
        progressBar.setVisibility(View.GONE);
        getListView().addFooterView(progressBar, null, false);

        setOnRefreshListener(this);
        prepareListData();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private boolean isFirstLoadedData = true;
    protected void onDataLoaded(List<Object> data){
        if(getAdapter()==null)
            return;

        if(isFirstLoadedData){
            getAdapter().setListData(data);
            if(!getAdapter().isLastPage())
                progressBar.setVisibility(View.VISIBLE);
            setListAdapter(getAdapter());
            isFirstLoadedData = false;
        }
        else {
            getAdapter().appendListData(data);
            getAdapter().notifyDataSetChanged();
        }

        if(getAdapter().isLastPage())
            getListView().removeFooterView(progressBar);

        if(isRefreshing())
            setRefreshing(false);
    }

    public void onRefresh() {
        if(getListView().getFooterViewsCount()<1)
            getListView().addFooterView(progressBar, null, false);
        isFirstLoadedData = true;
    }

    public void onListItemClick(ListView l, View v, int position, long id){}

    abstract protected String getEmptyHint();
    abstract protected BaseListAdapter getAdapter();
    abstract public void prepareListData();
}
