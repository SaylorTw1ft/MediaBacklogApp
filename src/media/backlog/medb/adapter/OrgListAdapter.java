package media.backlog.medb.adapter;

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

    public OrgListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        
    	View view = convertView;
    	ViewHolder holder = null;
        
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lists_list, null);
        }

        MediaList item = getItem(position);
        if (item!= null) {
            holder = new ViewHolder();
            
            holder.icon_view = (ImageView) view.findViewById(R.id.list_icon);
            if (holder.icon_view != null) {
            	String path_of_icon = find_icon_index(item);
            	int icon_image_id = context.getResources().getIdentifier(path_of_icon, 
            			"drawable", context.getPackageName());
            	holder.icon_view.setImageResource(icon_image_id);
            }
        	
            holder.title_view = (TextView) view.findViewById(R.id.list_title);
            if (holder.title_view != null) {
            	holder.title_view.setText(item.getListName());
            }
            
            holder.number_of_item_view = (TextView) view.findViewById(R.id.num_of_items);
            if (holder.number_of_item_view != null) {
            	holder.number_of_item_view.setText(String.format("%d Items", item.getNumItems()));
            }

        	holder.drag_button_view = (ImageView) view.findViewById(R.id.drag_button);
            if (holder.drag_button_view != null) {
            	int drag_image_id = context.getResources().getIdentifier("ic_drag_button", 
            			"drawable", context.getPackageName());
            	holder.drag_button_view.setImageResource(drag_image_id);
            }
            
            view.setTag(holder);
        }

        return view;
    }

	private String find_icon_index(MediaList item) {
		int index = ((item.getMovie() == true) ? 1000 : 0)
				+ ((item.getGame() == true) ? 100 : 0)
				+ ((item.getBook() == true) ? 10 : 0)
				+ ((item.getMusic() == true) ? 1 : 0);
		
		switch (index){
			case 1: return "ic_music";
			case 10: return "ic_books";
			case 11: return "ic_books_music";
			case 100: return "ic_games";
			case 101: return "ic_games_music";
			case 110: return "ic_games_books";
			case 111: return "ic_games_books_music";
			case 1000: return "ic_movies";
			case 1001: return "ic_movies_music";
			case 1010: return "ic_movies_books";
			case 1011: return "ic_movies_books_music";
			case 1100: return "ic_movies_games";
			case 1101: return "ic_movies_games_music";
			case 1110: return "ic_movies_games_books";
			case 1111: return "ic_movies_games_books_music";
			default:break;
		}
		return "";
	}
	
	/**
	 * ViewHolder allows us to avoid re-looking up view references
	 * Since views are recycled, these references will never change
	 */
	private static class ViewHolder {
		public ImageView icon_view;
		public TextView title_view;
		public TextView number_of_item_view;
		public ImageView drag_button_view;
	}
}