package media.backlog.medb.adapter;

import java.util.ArrayList;

import media.backlog.medb.R;
import media.backlog.medb.data.MediaList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrgListAdapter extends ArrayAdapter<MediaList> {

    private Context context;

    public OrgListAdapter(Context context, int textViewResourceId, ArrayList<MediaList> items) {
        super(context, textViewResourceId, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }

        MediaList item = getItem(position);
        if (item!= null) {
        	ImageView icon_view = (ImageView) view.findViewById(R.id.list_icon);
            if (icon_view != null) {
            	String path_of_icon = find_icon_index(item);
            	int image_id = context.getResources().getIdentifier(path_of_icon, "drawable", context.getPackageName());
            	icon_view.setImageResource(image_id);
            }
        	
            TextView title_view = (TextView) view.findViewById(R.id.list_title);
            if (title_view != null) {
            	title_view.setText(item.getListName());
            }
            
            TextView number_of_item_view = (TextView) view.findViewById(R.id.num_of_items);
            if (number_of_item_view != null) {
            	number_of_item_view.setText(String.format("%d Items", item.getNumItems()));
            }

        	ImageView drag_button_view = (ImageView) view.findViewById(R.id.list_icon);
            if (drag_button_view != null) {
            	int image_id = context.getResources().getIdentifier("ic_drag_button", "drawable", context.getPackageName());
            	drag_button_view.setImageResource(image_id);
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
			case 11: return "icon_books_music";
			case 100: return "icon_games";
			case 101: return "icon_games_music";
			case 110: return "icon_games_books";
			case 111: return "icon_games_books_music";
			case 1000: return "icon_movies";
			case 1001: return "icon_movies_music";
			case 1010: return "icon_movies_books";
			case 1011: return "icon_movies_books_music";
			case 1100: return "icon_movies_games";
			case 1101: return "icon_movies_games_music";
			case 1110: return "icon_movies_games_books";
			case 1111: return "icon_movies_games_books_music";
			default:break;
		}
		return "";
	}
}