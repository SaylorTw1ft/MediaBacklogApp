package media.backlog.medb.adapter;

import media.backlog.medb.R;
import media.backlog.medb.data.MediaItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemsListAdapter extends ArrayAdapter<MediaItem> {

    private Context context;

    public ItemsListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        
    	View view = convertView;
    	ViewHolder holder = null;
        
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.items_list, null);
        }

        MediaItem item = getItem(position);
        
        if (item!= null) {
        	
//    		Toast.makeText(context, 
//    				item.getItemName() + " worked ", Toast.LENGTH_SHORT).show();
    		
            holder = new ViewHolder();
            
            holder.item_title_view = (TextView) view.findViewById(R.id.item_title);
            if (holder.item_title_view != null) {
            	holder.item_title_view.setText(item.getItemName());
            }

        	holder.rating_view = (ImageView) view.findViewById(R.id.rating_icon);
            if (holder.rating_view != null) {
            	String icon_path = find_rating_icon_path(item);
            	int rating_image_id = context.getResources().getIdentifier(icon_path, 
            			"drawable", context.getPackageName());
            	holder.rating_view.setImageResource(rating_image_id);
            }
            
            view.setTag(holder);
        }

        return view;
    }

	private String find_rating_icon_path(MediaItem item) {
		
		switch (item.getRating()){
			case 1: return "ic_rating_one";
			case 2: return "ic_rating_two";
			case 3: return "ic_rating_three";
			case 4: return "ic_rating_four";
			case 5: return "ic_rating_five";
			default:break;
		}
		return "";
	}
	
	/**
	 * ViewHolder allows us to avoid re-looking up view references
	 * Since views are recycled, these references will never change
	 */
	private static class ViewHolder {
		public TextView item_title_view;
		public ImageView rating_view;
	}
}