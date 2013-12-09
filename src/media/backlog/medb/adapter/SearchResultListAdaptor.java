package media.backlog.medb.adapter;

import java.util.ArrayList;

import media.backlog.medb.R;
import media.backlog.medb.data.MediaItem;
import media.backlog.medb.data.MediaList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchResultListAdaptor extends ArrayAdapter<MediaList> {

	
    private Context context;

    public SearchResultListAdaptor(Context context, int textViewResourceId, ArrayList<MediaItem> items) {
        super(context, textViewResourceId, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.search_list_display, null);
        }

        MediaList item = getItem(position);
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
            	title_view.setText(item.getListName());
            }
            
            TextView number_of_item_view = (TextView) view.findViewById(R.id.num_of_items);
            if (number_of_item_view != null) {
            	number_of_item_view.setText(String.format("%d Items", item.getNumItems()));
            }

        	ImageView add_button_view = (ImageView) view.findViewById(R.id.addButton);
            if (add_button_view != null) {
            	int image_id = context.getResources().getIdentifier("ic_drag_button", "drawable", context.getPackageName());
            	add_button_view.setImageResource(image_id);
            }
            
            
        }

        return view;
    }

	private String find_icon_index(MediaList item) {
		int index = ((item.getMovie() == true) ? 1000 : 0)
				+ ((item.getGame() == true) ? 100 : 0)
				+ ((item.getBook() == true) ? 10 : 0)
				+ ((item.getMusic() == true) ? 1 : 0);
		
		switch (index){
			case 1: return "icon_music";
			case 10: return "icon_books";
			case 100: return "icon_games";
			case 1000: return "icon_movies";
			default:break;
		}
		return "";
	}
}