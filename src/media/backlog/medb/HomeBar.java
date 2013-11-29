package media.backlog.medb;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * Created by Arin on 11/26/13.
 */
public class HomeBar extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_bar, container, false);
        ImageButton homeButton = (ImageButton) v.findViewById(R.id.homeButton);
        ImageButton discoverButton = (ImageButton) v.findViewById(R.id.discoverButton);
        ImageButton organizeButton = (ImageButton) v.findViewById(R.id.organizeButton);
        ImageButton searchButton = (ImageButton) v.findViewById(R.id.searchButton);
        homeButton.setOnClickListener(this);
        discoverButton.setOnClickListener(this);
        organizeButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButton:
                homeButton(v);
                break;
            case R.id.organizeButton:
                organizeButton(v);
                break;
            case R.id.discoverButton:
                discoverButton(v);
                break;
            case R.id.searchButton:
                searchButton(v);
                break;
        }
    }

    public void searchButton(View view) {
        Intent intent = new Intent(this.getActivity(), SearchActivity.class);
        startActivityForResult(intent, 1);
    }

    public void organizeButton(View view) {
        Intent intent = new Intent(this.getActivity(), OrganizeActivity.class);
        startActivity(intent);
    }

    public void discoverButton(View view) {
        Intent intent = new Intent(this.getActivity(), DiscoverActivity.class);
        startActivity(intent);
    }

    public void homeButton(View view) {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }

}