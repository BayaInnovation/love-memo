package love.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class HomeActivity extends  AppCompatActivity  { 
	
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private double sn = 0;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap2 = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private LinearLayout _drawer_linear1;
	private LinearLayout _drawer_linear2;
	private LinearLayout _drawer_linear7;
	private LinearLayout _drawer_linear6;
	private LinearLayout _drawer_linear5;
	private LinearLayout _drawer_linear4;
	private LinearLayout _drawer_linear3;
	private Button _drawer_green;
	private Button _drawer_purple;
	private Button _drawer_white;
	private Button _drawer_yellow;
	private Button _drawer_black;
	private Button _drawer_orange;
	private Button _drawer_red;
	private Button _drawer_blue;
	
	private SharedPreferences sp;
	private Intent i = new Intent();
	private AlertDialog.Builder di;
	private AlertDialog.Builder di2;
	private Intent i2 = new Intent();
	private AlertDialog.Builder tip;
	private SharedPreferences term;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		_drawer = (DrawerLayout) findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		listview1 = (ListView) findViewById(R.id.listview1);
		_drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
		_drawer_linear2 = (LinearLayout) _nav_view.findViewById(R.id.linear2);
		_drawer_linear7 = (LinearLayout) _nav_view.findViewById(R.id.linear7);
		_drawer_linear6 = (LinearLayout) _nav_view.findViewById(R.id.linear6);
		_drawer_linear5 = (LinearLayout) _nav_view.findViewById(R.id.linear5);
		_drawer_linear4 = (LinearLayout) _nav_view.findViewById(R.id.linear4);
		_drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
		_drawer_green = (Button) _nav_view.findViewById(R.id.green);
		_drawer_purple = (Button) _nav_view.findViewById(R.id.purple);
		_drawer_white = (Button) _nav_view.findViewById(R.id.white);
		_drawer_yellow = (Button) _nav_view.findViewById(R.id.yellow);
		_drawer_black = (Button) _nav_view.findViewById(R.id.black);
		_drawer_orange = (Button) _nav_view.findViewById(R.id.orange);
		_drawer_red = (Button) _nav_view.findViewById(R.id.red);
		_drawer_blue = (Button) _nav_view.findViewById(R.id.blue);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		di = new AlertDialog.Builder(this);
		di2 = new AlertDialog.Builder(this);
		tip = new AlertDialog.Builder(this);
		term = getSharedPreferences("term", Activity.MODE_PRIVATE);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				sp.edit().putString("pos", String.valueOf((long)(_position))).commit();
				i.setClass(getApplicationContext(), NotpadActivity.class);
				startActivity(i);
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				di2.setTitle("Do you want to delete this note?");
				di2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						listmap.remove((int)(_position));
						sp.edit().putString("text", new Gson().toJson(listmap)).commit();
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
				});
				di2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				di2.create().show();
				return true;
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), NotpadActivity.class);
				startActivity(i);
			}
		});
		
		_drawer_green.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setBackgroundColor(0xFF4CAF50);
				linear1.setBackgroundColor(0xFF4CAF50);
				sp.edit().putString("color", "green").commit();
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_purple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setBackgroundColor(0xFF9C27B0);
				linear1.setBackgroundColor(0xFF9C27B0);
				sp.edit().putString("color", "purple").commit();
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_white.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setBackgroundColor(0xFFFFFFFF);
				linear1.setBackgroundColor(0xFFFFFFFF);
				sp.edit().putString("color", "white").commit();
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_yellow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setBackgroundColor(0xFFFFEB3B);
				linear1.setBackgroundColor(0xFFFFEB3B);
				sp.edit().putString("color", "yellow").commit();
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_black.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setBackgroundColor(0xFF000000);
				linear1.setBackgroundColor(0xFF000000);
				sp.edit().putString("color", "black").commit();
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_orange.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setBackgroundColor(0xFFFF9800);
				linear1.setBackgroundColor(0xFFFF9800);
				sp.edit().putString("color", "orange").commit();
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_red.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setBackgroundColor(0xFFF44336);
				linear1.setBackgroundColor(0xFFF44336);
				sp.edit().putString("color", "red").commit();
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_blue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setBackgroundColor(0xFF2196F3);
				linear1.setBackgroundColor(0xFF2196F3);
				sp.edit().putString("color", "blue").commit();
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
	}
	
	private void initializeLogic() {
		setTitle("Memo");
		if (sp.getString("color", "").equals("red")) {
			listview1.setBackgroundColor(0xFFF44336);
			linear1.setBackgroundColor(0xFFF44336);
		}
		if (sp.getString("color", "").equals("blue")) {
			linear1.setBackgroundColor(0xFF2196F3);
			listview1.setBackgroundColor(0xFF2196F3);
		}
		if (sp.getString("color", "").equals("white")) {
			linear1.setBackgroundColor(0xFFFFFFFF);
			listview1.setBackgroundColor(0xFFFFFFFF);
		}
		if (sp.getString("color", "").equals("orange")) {
			linear1.setBackgroundColor(0xFFFF9800);
			listview1.setBackgroundColor(0xFFFF9800);
		}
		if (sp.getString("color", "").equals("black")) {
			listview1.setBackgroundColor(0xFF000000);
			linear1.setBackgroundColor(0xFF000000);
		}
		if (sp.getString("color", "").equals("green")) {
			linear1.setBackgroundColor(0xFF4CAF50);
			listview1.setBackgroundColor(0xFF4CAF50);
		}
		if (sp.getString("color", "").equals("yellow")) {
			linear1.setBackgroundColor(0xFFFFEB3B);
			listview1.setBackgroundColor(0xFFFFEB3B);
		}
		if (sp.getString("color", "").equals("purple")) {
			linear1.setBackgroundColor(0xFF9C27B0);
			listview1.setBackgroundColor(0xFF9C27B0);
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
	public void onStart() {
		super.onStart();
		sp.edit().putString("pos", "").commit();
		if (!sp.getString("text", "").equals("")) {
			listmap = new Gson().fromJson(sp.getString("text", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			listview1.setAdapter(new Listview1Adapter(listmap));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		}
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		}
		else {
			super.onBackPressed();
		}
	}
	public void _round (final View _view, final double _num, final String _color) {
		try {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			
			
			float f = (float) _num;
			
			
			gd.setCornerRadius(f);
			_view.setBackground(gd);
			
		} catch(Exception e){ Log.e("Error: ", e.toString()); String error_code = e.toString();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
							builder.setTitle("Error")
								.setMessage(error_code)
								.setCancelable(false)
								.setNegativeButton("ОК", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
												
										}
								});
							AlertDialog alert = builder.create();
							alert.show();
		}
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.list, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final Button img = (Button) _view.findViewById(R.id.img);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			textview1.setText(listmap.get((int)_position).get("message").toString().trim());
			_round(linear1, 25, "#607D8B");
			ObjectAnimator anim = ObjectAnimator.ofFloat(img, "Alpha", 0, 1); 
			anim.setRepeatMode(ObjectAnimator.REVERSE); 
			anim.setRepeatCount(ObjectAnimator.INFINITE);
			anim.setDuration(900); 
			anim.start();
			
			return _view;
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