package media.backlog.medb.adapter;

import java.util.ArrayList;

import media.backlog.medb.R;
import media.backlog.medb.data.MediaItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchResultListAdaptor extends ArrayAdapter<MediaItem> {

	
    private Context context;

    public SearchResultListAdaptor(Context context, int textViewResourceId, ArrayList<MediaItem> items) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.search_list_display, null);
        }

        MediaItem item = getItem(position);
        if (item!= null) {
        	ImageView media_View = (ImageView) view.findViewById(R.id.mediaImage);
        	if(media_View!=null){
        		
        	}
        	
        	ImageView icon_view = (ImageView) view.findViewById(R.id.movieType);
            if (icon_view != null) {
            	String path_of_icon = find_icon_index(item);
            	int image_id = context.getResources().getIdentifier(path_of_icon, "drawable", context.getPackageName());
            	icon_view.setImageResource(image_id);
            }
        	
            TextView title_view = (TextView) view.findViewById(R.id.mediaTitle);
            if (title_view != null) {
            	title_view.setText(item.getItemName());
            }
            

        	ImageView add_button_view = (ImageView) view.findViewById(R.id.addButton);
            if (add_button_view != null) {
            	int image_id = context.getResources().getIdentifier("ic_add_button", "drawable", context.getPackageName());
            	add_button_view.setImageResource(image_id);
            }
            
            
        }

        return view;
    }

	private String find_icon_index(MediaItem item) {
		int index = item.getCategory();
		
		switch (index){
			case 1: return "icon_movies";
			case 2: return "icon_games";
			case 3: return "icon_music";
			case 4: return "icon_books";
			default:break;
		}
		return "";
	}
}