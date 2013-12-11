package media.backlog.medb.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import media.backlog.medb.AddActivity;
import media.backlog.medb.R;
import media.backlog.medb.SearchActivity;
import media.backlog.medb.data.MediaItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchResultListAdaptor extends ArrayAdapter<MediaItem> {

	
    private Context context;

    public SearchResultListAdaptor(Context context, int textViewResourceId, ArrayList<MediaItem> items) {
        super(context, textViewResourceId);
        this.context = context;
    }
    private class ViewHolder {
    	Context context;
    	ImageView imageViewRating;
    	Button buttonAdd;
    	ImageView imageViewThumbnail;
    	ImageView imageViewCategory;
    	TextView textViewPeople;
    	TextView textViewItemName;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder = null;
    	final MediaItem mediaItem = getItem(position);
    	LayoutInflater mInflater = (LayoutInflater) context
    			.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    	
    	if(convertView == null) {
    		convertView = mInflater.inflate(R.layout.search_list_display, null);
    		holder = new ViewHolder();
    		holder.imageViewCategory = (ImageView) convertView.findViewById(R.id.category_type);
    		holder.context = convertView.getContext();
    		holder.imageViewRating = (ImageView) convertView.findViewById(R.id.rating_image);
    		holder.imageViewThumbnail = (ImageView) convertView.findViewById(R.id.thumbnail_image);
    		holder.textViewPeople = (TextView) convertView.findViewById(R.id.num_people);
    		holder.textViewItemName = (TextView) convertView.findViewById(R.id.search_item_title);
    		holder.buttonAdd = (Button) convertView.findViewById(R.id.search_add_button);
    		convertView.setTag(holder);
    	}
    	else
    		holder = (ViewHolder) convertView.getTag();
    	holder.textViewItemName.setText(mediaItem.getItemName());
    	holder.textViewPeople.setText("8 people list this...");
    	final Context contexttwo = holder.context;
    	holder.buttonAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        Intent intent = new Intent(context, AddActivity.class);
		        Bundle b = new Bundle();
		        b.putInt("id", mediaItem.getItemID());
		        intent.putExtras(b);
		        context.startActivity(intent);
			}

		});
    	
        String path = mediaItem.getPicture();
        try {

            // get input stream

            InputStream ims = holder.context.getAssets().open(path);

            // load image as Drawable

            Drawable d = Drawable.createFromStream(ims, null);

            // set image to ImageView
            holder.imageViewThumbnail.setImageDrawable(d);

        }

        catch(IOException ex) {


        }
        holder.imageViewCategory.setImageDrawable(holder.context.getResources()
        		.getDrawable(getCategoryResource(mediaItem.getCategory())));
        holder.imageViewRating.setImageDrawable(holder.context.getResources()
        		.getDrawable(getRatingResource(mediaItem.getRating())));
    	return convertView;
       
    }

	private int getCategoryResource(int category) {
			if(category == 1)
				return R.drawable.movies_individual;
			else if(category == 2)
				return R.drawable.games_individual;
			else if(category == 3)
				return R.drawable.music_individual;
			else
				return R.drawable.books_individual;
	}

	private int getRatingResource(int rating) {
		
		switch (rating){
			case 1: return R.drawable.ic_rating_one;
			case 2: return R.drawable.ic_rating_two;
			case 3: return R.drawable.ic_rating_three;
			case 4: return R.drawable.ic_rating_four;
			case 5: return R.drawable.ic_rating_five;
			default:break;
		}
		return R.drawable.ic_rating_two;
	}
	
}