package love.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class NotpadActivity extends  AppCompatActivity  { 
	
	
	private double position = 0;
	private double sn = 0;
	private String groupstring = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap2 = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private Button button1;
	private EditText edittext1;
	
	private SharedPreferences sp;
	private AlertDialog.Builder di;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.notpad);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		button1 = (Button) findViewById(R.id.button1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		di = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Oops! nothing is saved");
					SketchwareUtil.showMessage(getApplicationContext(), "Oops! nothing is saved");
					finish();
				}
				else {
					if (position == -1) {
						{
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("message", edittext1.getText().toString());
							listmap.add(_item);
						}
						
					}
					else {
						listmap.get((int)position).put("message", edittext1.getText().toString());
					}
					sp.edit().putString("text", new Gson().toJson(listmap)).commit();
					edittext1.setVisibility(View.GONE);
					finish();
				}
			}
		});
	}
	
	private void initializeLogic() {
		setTitle("Edit");
		groupstring = "groupname";
		if (!sp.getString("text", "").equals("")) {
			listmap = new Gson().fromJson(sp.getString("text", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		if (!sp.getString("pos", "").equals("")) {
			position = Double.parseDouble(sp.getString("pos", ""));
			edittext1.setText(listmap.get((int)position).get("message").toString());
		}
		else {
			position = -1;
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (edittext1.getText().toString().equals(listmap.get((int)position).get("message").toString())) {
			finish();
		}
		else {
			di.setTitle("WARNING");
			di.setMessage("There is unsaved note ?");
			di.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					if (position == -1) {
						{
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("message", edittext1.getText().toString());
							listmap.add(_item);
						}
						
					}
					else {
						listmap.get((int)position).put("message", edittext1.getText().toString());
					}
					sp.edit().putString("text", new Gson().toJson(listmap)).commit();
					finish();
				}
			});
			di.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					finish();
				}
			});
			di.create().show();
		}
	}
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}