package io.github.ehsanmody.maktab4ver2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static final String EXTRA_DETAILS_FRAGMENT = "EXTRA_DETAILS_FRAGMENT";

    private RecyclerView mRecyclerView;
    private boolean mTwoPanel;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private LinearLayout emptyList;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if (findViewById(R.id.itemDetailsTablet) != null) mTwoPanel = true;

        emptyList = (LinearLayout) findViewById(R.id.emptyList);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));

        List<Data> dataList = DataLab.getInstance().getAllData();
        myAdapter = new MyAdapter(dataList);

        mRecyclerView.setAdapter(myAdapter);
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView dataTitle;
        private TextView dataText;

        private Data data;

        public MyHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPanel) {
                        fragmentManager = getSupportFragmentManager();
                        fragment = fragmentManager.findFragmentById(R.id.frameLayout);
                        if (fragment == null) {
                            DetailsFragment detailsFragment = DetailsFragment.newInstance(data.getId());
                            fragmentManager.beginTransaction().replace(R.id.itemDetailsTablet, detailsFragment).commit();
                        }
                    } else {
                        Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                        intent.putExtra(EXTRA_DETAILS_FRAGMENT, data.getId());
                        startActivity(intent);
                    }
                }
            });

            dataTitle = itemView.findViewById(R.id.dataTitle);
            dataText = itemView.findViewById(R.id.dataText);
        }

        public void setUI(Data data) {
            this.data = data;

            dataTitle.setText(data.getName());
            dataText.setText(data.getText());
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        private List<Data> dataList;

        public MyAdapter(List<Data> data) {
            this.dataList = data;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(ListActivity.this);
            View view = inflater.inflate(R.layout.item_layout, parent, false);

            MyHolder myHolder = new MyHolder(view);

            return myHolder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            Data data = dataList.get(position);
            holder.setUI(data);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter.notifyDataSetChanged();
        if (myAdapter.getItemCount() == 0) emptyList.setVisibility(View.VISIBLE);
        else emptyList.setVisibility(View.GONE);
    }
}
