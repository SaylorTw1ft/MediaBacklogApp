package media.backlog.medb;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Arin on 12/2/13.
 */
public class HorizontalScrollBar extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.horizontal_scroll_bar, container, false);
        LinearLayout l = (LinearLayout) v.findViewById(R.id.horizontal_scroll_bar);
        TextView t = new TextView(v.getContext());
        t.setText("  ");
        l.addView(t);

        for (int i = 0; i < 9; i++)
        {
            LinearLayout scrollingItem = new LinearLayout(v.getContext());
        	scrollingItem.setOrientation(LinearLayout.VERTICAL);
            ImageButton b = new ImageButton(v.getContext());
            b.setBackgroundResource(R.drawable.ic_thor);
            scrollingItem.addView(b);
            TextView t1 = new TextView(v.getContext());
            t1.setText("Thor");
            t1.setTextColor(Color.WHITE);
            t1.setGravity(Gravity.CENTER_HORIZONTAL);
            scrollingItem.addView(t1);
            
            l.addView(scrollingItem);
        }

        return v;
    }

}