package media.backlog.medb;

import media.backlog.medb.data.MediaList;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Lists;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OrganizeAddListActivity extends Activity
{
	Button btnsecond_activity;

	String list_name;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_organize_add_list);
		setTitle("Create new list");
		
		
		findViewById(R.id.add_list_save).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view)
			{
				add_new_list_to_db();
				
				Intent intent = new Intent(getApplicationContext(), OrganizeActivity.class);
//				Bundle b = new Bundle();
//				b.putString("list_name", list_name);
//				b.putInt("list_id", list_id);
//				intent.putExtras(b);
				startActivity(intent);
			}

		});
		
	}

	private MediaList add_new_list_to_db()
	{
		EditText mEdit = (EditText)findViewById(R.id.add_list_name);
		String list_name = mEdit.getText().toString();
		DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
		helper.close();
		MediaList list = Lists.addList(helper, list_name);
		
		Toast.makeText(getApplicationContext(), 
				"The list " + list_name + " has been created! ", 
				Toast.LENGTH_SHORT).show();
		
		return list;
	}
}
