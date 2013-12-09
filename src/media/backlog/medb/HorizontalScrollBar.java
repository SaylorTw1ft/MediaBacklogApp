package media.backlog.medb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import media.backlog.medb.data.MediaList;
import media.backlog.medb.data.MediaItem;
import media.backlog.medb.database.DatabaseHelper;

/**
 * Created by Arin on 12/2/13.
 */
public class HorizontalScrollBar extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.horizontal_scroll_bar, container, false);
        LinearLayout l = (LinearLayout) v.findViewById(R.id.horizontal_scroll_bar);
        TextView t = new TextView(v.getContext());
        t.setText("  ");
        l.addView(t);
//        DatabaseHelper helper = new DatabaseHelper(getActivity().getApplicationContext());
//        Bundle bundle = new Bundle();
//        int listId = bundle.getInt("listId");
//        MediaList itemList = media.backlog.medb.database.Lists.getList(helper, listId);
        for (int i = 0; i < 9; i++) {
            ImageButton b = new ImageButton(v.getContext());
            b.setBackgroundResource(R.drawable.ic_thor);
            l.addView(b);
            TextView t1 = new TextView(v.getContext());
            t1.setText("  ");
            l.addView(t1);
        }

        return v;
    }

}