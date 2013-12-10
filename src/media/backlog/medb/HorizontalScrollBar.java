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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
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
        try{
            id = bundle.getInt("id");
        }
        catch (NullPointerException e)
        {
            id = 1;
        }
            MediaItem item = media.backlog.medb.database.Items.getItem(helper, id);
        ArrayList<MediaItem> items =  media.backlog.medb.database.Items.getSimilarItems(helper, item);

        for (int i = 0; i < 10 && i<items.size(); i++) {
            MediaItem temp = items.get(i);
            LinearLayout scrollingItem = setUpScrollingItem(v, temp);
            l.addView(scrollingItem);
        }

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
        t1.setTextSize(8);
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