package com.telstra.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.telstra.R;
import com.telstra.adapter.ListItemAdapter;
import com.telstra.controller.ServiceController;
import com.telstra.framework.network.INetworkListener;
import com.telstra.framework.network.ResponseInfo;
import com.telstra.model.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements INetworkListener, SwipeRefreshLayout.OnRefreshListener {

    ServiceController serviceController = null;
    SwipeRefreshLayout swipeRefreshLayout = null;
    ListItemAdapter listItemAdapter = null;
    ListView listView;
    List<ServiceResponse.Rows> rowsList = new ArrayList<ServiceResponse.Rows>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) findViewById(R.id.listView);
        serviceController = new ServiceController(MainActivity.this, this);
        listItemAdapter = new ListItemAdapter(MainActivity.this, rowsList);
        listView.setAdapter(listItemAdapter);
        setTitle("");
        getServiceResponse();

    }

    private void getServiceResponse() {
        swipeRefreshLayout.setRefreshing(true);
        rowsList = new ArrayList<ServiceResponse.Rows>();
        listItemAdapter.refreshAdapter(rowsList);
        serviceController.getServiceResponse();

    }

    private void setData(ServiceResponse serviceResponse) {

        //Set the Actionbar Title
        if (serviceResponse.getTitle() != null)
            setTitle(serviceResponse.getTitle());
        else
            setTitle("");

        //Set Adapter content
        if (serviceResponse.getRows() != null && !serviceResponse.getRows().isEmpty())
            rowsList = serviceResponse.getRows();
        else
            rowsList = new ArrayList<ServiceResponse.Rows>();

        listItemAdapter.refreshAdapter(rowsList);
    }

    @Override
    public void onSuccess(ResponseInfo responseObject) {
        swipeRefreshLayout.setRefreshing(false);
        if (responseObject != null && responseObject.getResponseData() != null && responseObject.getResponseData() instanceof ServiceResponse)
            setData((ServiceResponse) responseObject.getResponseData());

    }

    @Override
    public void onError(ResponseInfo responseObject) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        //Reload the service and fetch the content from server
        getServiceResponse();
    }
}
