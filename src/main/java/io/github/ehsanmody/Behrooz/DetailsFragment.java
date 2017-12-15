package io.github.ehsanmody.maktab4ver2;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private Data data;
    private TextView dataTitle;
    private TextView dataText;
    private Button btnDelete;

    //chakerim :D

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        UUID dataId = (UUID) getArguments().getSerializable(DetailsActivity.ARGS_DETAILS_FRAGMENT);
        data = DataLab.getInstance().getDataById(dataId);

        dataTitle = rootView.findViewById(R.id.detailsDataTitle);
        dataText = rootView.findViewById(R.id.detailsDataText);
        btnDelete = rootView.findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLab.getInstance().deleteDataById(data.getId());
                getActivity().finish();
            }
        });

        dataTitle.setText(data.getName());
        dataText.setText(data.getText());

        return rootView;
    }

    public static DetailsFragment newInstance(UUID dataId) {
        DetailsFragment detailsFragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailsActivity.ARGS_DETAILS_FRAGMENT, dataId);
        detailsFragment.setArguments(bundle);

        return detailsFragment;
    }
}
