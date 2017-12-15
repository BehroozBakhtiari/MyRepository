package io.github.ehsanmody.maktab4ver2;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.UUID;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DETAILS_FRAGMENT = "EXTRA_DETAILS_FRAGMENT";
    public static final String ARGS_DETAILS_FRAGMENT = "ARGS_DETAILS_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        UUID dataId = (UUID) getIntent().getSerializableExtra(EXTRA_DETAILS_FRAGMENT);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.frameLayout);
        if (fragment == null) {
            DetailsFragment detailsFragment = DetailsFragment.newInstance(dataId);
            fragmentManager.beginTransaction().add(R.id.frameLayout, detailsFragment).commit();
        }
    }
}
