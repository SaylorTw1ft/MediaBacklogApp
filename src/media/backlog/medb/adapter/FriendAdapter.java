package media.backlog.medb.adapter;

import media.backlog.medb.R;
import media.backlog.medb.data.MediaFriend;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendAdapter extends ArrayAdapter<MediaFriend>
{
	private Context context;

    public FriendAdapter(Context context, int textViewResourceId) {
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

        MediaFriend friend = getItem(position);
        if (friend != null) {
            holder = new ViewHolder();
            
            holder.icon_view = (ImageView) view.findViewById(R.id.list_icon);
            if (holder.icon_view != null) {
            	String path_of_icon = "friends/" + friend.getPicture();
            	int icon_image_id = context.getResources().getIdentifier(path_of_icon, 
            			"drawable", context.getPackageName());
            	holder.icon_view.setImageResource(icon_image_id);
            }
        	
            holder.name_view = (TextView) view.findViewById(R.id.list_name);
            if (holder.name_view != null) {
            	holder.name_view.setText(friend.getFriendName());
            }
            
            view.setTag(holder);
        }

        return view;
    }

	private static class ViewHolder
	{
		public ImageView icon_view;
		public TextView name_view;
	}
}
