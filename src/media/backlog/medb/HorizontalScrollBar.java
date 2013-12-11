package media.backlog.medb;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import media.backlog.medb.data.MediaItem;
import media.backlog.medb.database.DatabaseHelper;

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
        DatabaseHelper helper = new DatabaseHelper(getActivity().getApplicationContext());
        Bundle bundle = getArguments();
        int id;
        ArrayList<MediaItem> items;
        try{
        id = bundle.getInt("id");
        }catch(NullPointerException e) { id = 1;}
        MediaItem item = media.backlog.medb.database.Items.getItem(helper, id);

        int type;
        try{
            type = bundle.getInt("type");
            if (type == 1)
            {
                items = media.backlog.medb.database.RecentActivity.getRecentActivity(helper);
            }
            else if (type == 2)
            {
                items = media.backlog.medb.database.NewReleases.getNewReleases(helper);
            }
            else if (type == 3)
            {
                items =  media.backlog.medb.database.TrendingAmongFriends.getTrendingAmongFriendsMediaItems(helper);
            }
            else
            {
                items =  media.backlog.medb.database.Items.getSimilarItems(helper, item);
            }
        }
        catch (NullPointerException e)
        {
            items =  media.backlog.medb.database.Items.getSimilarItems(helper, item);
        }
        for (int i = 0; i < 10 && i<items.size(); i++) {
            MediaItem temp = items.get(i);
            LinearLayout scrollingItem = setUpScrollingItem(v, temp);
            l.addView(scrollingItem);
        }
        if (items.size() == 0)
        {
            LinearLayout scrollingItem = new LinearLayout(v.getContext());
            scrollingItem.setLayoutParams(new LinearLayout.LayoutParams(400, 225));
            scrollingItem.setOrientation(LinearLayout.VERTICAL);
            TextView t1 = new TextView(v.getContext());
            t1.setText("No similar items!");
            t1.setTextColor(Color.WHITE);
            t1.setTextSize(18);
            t1.setGravity(Gravity.CENTER);
            scrollingItem.addView(t1);
            scrollingItem.setMinimumHeight(225);
            scrollingItem.setMinimumWidth(200);
            l.addView(scrollingItem);
        }
        v.setBackgroundColor(getResources().getColor(R.color.home_color));
        return v;
    }

    private LinearLayout setUpScrollingItem(View v, MediaItem temp) {
        LinearLayout scrollingItem = new LinearLayout(v.getContext());
        scrollingItem.setLayoutParams(new LinearLayout.LayoutParams(188,225));
        scrollingItem.setOrientation(LinearLayout.VERTICAL);
        ImageButton button = new ImageButton(scrollingItem.getContext());
        final int myId = temp.getItemID();
        button.setLayoutParams(new ViewGroup.LayoutParams(150,200));
        button.setScaleType(ScaleType.FIT_START);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ItemActivity.class);
                Bundle b = new Bundle();
                b.putInt("id",myId);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        setThumbnailPic(temp, button);
        TextView t1 = new TextView(v.getContext());
        t1.setText(temp.getItemName());
        t1.setTextColor(Color.WHITE);
        t1.setTextSize(10);
        t1.setGravity(Gravity.BOTTOM);
        scrollingItem.setMinimumHeight(200);
        scrollingItem.setMinimumWidth(134);
        scrollingItem.addView(button);
        scrollingItem.addView(t1);
        return scrollingItem;
    }

    public void setThumbnailPic(MediaItem item, ImageButton imageButton)
    {
        String path = item.getPicture();
        try {

            // get input stream

            InputStream ims = getActivity().getAssets().open(path);

            // load image as Drawable

            Drawable d = Drawable.createFromStream(ims, null);

            // set image to ImageView
            imageButton.setImageDrawable(d);

        }

        catch(IOException ex) {


        }
    }

}