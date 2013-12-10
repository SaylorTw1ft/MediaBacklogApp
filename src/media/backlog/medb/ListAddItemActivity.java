package media.backlog.medb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
	 
public class ListAddItemActivity extends Activity {
	Button btnsecond_activity;

	String list_name;
	int list_id;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_add_item);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        list_name = b.getString("list_name");
        list_id = b.getInt("list_id");
		setTitle("Add New Item To " + list_name);
		
		
		findViewById(R.id.add_item_save).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				add_new_item_to_db();
				
				Bundle b = new Bundle();
				b.putString("list_name", list_name);
				b.putInt("list_id", list_id);
				Intent intent = new Intent(getApplicationContext(), ListActivity.class);
				intent.putExtras(b);
				startActivity(intent);
			}

		});
		
	}

	private void add_new_item_to_db() {
		// TODO Auto-generated method stub
		EditText mEdit   = (EditText)findViewById(R.id.add_item_name);
		String item_name = mEdit.getText().toString();
		
		
	}
	 
}
