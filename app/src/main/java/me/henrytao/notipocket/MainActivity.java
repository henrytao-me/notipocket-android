package me.henrytao.notipocket;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import me.henrytao.widget.NavigationDrawerActivity;


public class MainActivity extends NavigationDrawerActivity {

  private int activePosition = -100;

  public MainActivity() {
    this.activityLayoutId = R.layout.activity_main;
    this.drawerLayoutId = R.id.main_drawer_layout;
    this.drawerIcon = R.drawable.ic_drawer;
    this.drawerOpenString = R.string.main_drawer_open;
    this.drawerCloseString = R.string.main_drawer_close;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // init navigation drawer list
    ListView drawerList = (ListView) findViewById(R.id.drawerList);
    DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this);
    drawerList.setAdapter(drawerListAdapter);
    drawerList.setOnItemClickListener(new DrawerItemClickListener());

    if (savedInstanceState == null) {
      this.onDrawerItemClick(0);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * DrawerItemClick listener
   */
  private class DrawerItemClickListener implements ListView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      onDrawerItemClick(position);
    }
  }

  private void onDrawerItemClick(int position) {
    if (this.activePosition == position) {
      this.closeDrawer();
      return;
    }
    this.activePosition = position;

    switch (position) {
      case -1:
        break;
      case 0:
        getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
        break;
      case 1:
        getFragmentManager().beginTransaction().replace(R.id.container, new TrashFragment()).commit();
      default:
        Toast.makeText(this, "Invalid fragment", Toast.LENGTH_SHORT);
        break;
    }

    this.closeDrawer();
  }

  /**
   * Drawer List Adapter
   */

  public class DrawerListAdapter extends BaseAdapter {

    Context context;

    String[] items;

    int[] images = {R.drawable.ic_action_add_to_queue, R.drawable.ic_action_discard, R.drawable.ic_action_help};

    public DrawerListAdapter(Context context) {
      this.context = context;
      this.items = this.context.getResources().getStringArray(R.array.main_drawer_list);
    }

    @Override
    public int getCount() {
      return this.items.length;
    }

    @Override
    public Object getItem(int position) {
      return this.items[position];
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = convertView;

      if (view == null) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.navigation_drawer_item, parent, false);
      }

      ImageView image = (ImageView) view.findViewById(R.id.image);
      TextView title = (TextView) view.findViewById(R.id.title);

      image.setImageResource(this.images[position]);
      title.setText(this.items[position]);

      return view;
    }
  }
}
