package media.backlog.medb;

import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Lists;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ListEditItemActivity extends Activity
{
	String list_name;
	int list_id;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_edit_item);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        list_name = b.getString("list_name");
        list_id = b.getInt("list_id");
		setTitle("Edit list: " + list_name);
		
		
		findViewById(R.id.edit_list_delete).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view)
			{
				DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
				Lists.deleteList(helper, list_id);

				Intent intent = new Intent(getApplicationContext(), OrganizeActivity.class);
				startActivity(intent);
			}

		});
		
	}
}
