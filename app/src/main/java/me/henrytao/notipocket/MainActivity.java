package me.henrytao.notipocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
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

  private String title = "";

  private DrawerListAdapter drawerListAdapter;

  public MainActivity() {
    this.activityLayoutId = R.layout.activity_main;
    this.drawerLayoutId = R.id.main_drawer_layout;
    this.drawerIconId = R.drawable.ic_drawer;
    this.drawerOpenStringId = R.string.main_drawer_open;
    this.drawerCloseStringId = R.string.main_drawer_close;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // init navigation drawer list
    this.drawerListAdapter = new DrawerListAdapter(this);
    ListView drawerList = (ListView) findViewById(R.id.drawerList);
    drawerList.setAdapter(this.drawerListAdapter);
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
  public boolean onPrepareOptionsMenu(Menu menu) {
    int position = this.activePosition;
    if (this.isDrawerOpen) {
      position = -100;
    }
    switch (position) {
      case 0:
        menu.setGroupVisible(R.id.main_menu, true);
        menu.setGroupVisible(R.id.trash_menu, false);
        break;
      case 1:
        menu.setGroupVisible(R.id.main_menu, false);
        menu.setGroupVisible(R.id.trash_menu, true);
        break;
      default:
        menu.setGroupVisible(R.id.main_menu, false);
        menu.setGroupVisible(R.id.trash_menu, false);
        break;
    }
    return super.onPrepareOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    //if (id == R.id.action_settings) {
    //  return true;
    //}
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void setTitle(CharSequence title) {
    this.getActionBar().setTitle(title);
    super.setTitle(title);
  }

  @Override
  protected void onDrawerOpened(View view) {
    super.onDrawerOpened(view);
    this.setTitle(this.getString(R.string.app_name));
  }

  @Override
  protected void onDrawerClosed(View view) {
    super.onDrawerClosed(view);
    this.setTitle(this.drawerListAdapter.getItem(this.activePosition).toString());
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

    switch (position) {
      case -1:
        break;
      case 0:
        this.activePosition = position;
        MainFragment mainFragment = new MainFragment();
        mainFragment.args.putString(MainFragment.ARG_FRAGMENT_NAME, this.drawerListAdapter.getItem(position).toString());
        getFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
        break;
      case 1:
        this.activePosition = position;
        TrashFragment trashFragment = new TrashFragment();
        trashFragment.args.putString(MainFragment.ARG_FRAGMENT_NAME, this.drawerListAdapter.getItem(position).toString());
        getFragmentManager().beginTransaction().replace(R.id.container, trashFragment).commit();
        break;
      case 2:
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.contact_to)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.contact_subject));
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(intent, ""));
        break;
      default:
        Toast.makeText(this, "Invalid fragment", Toast.LENGTH_SHORT).show();
        break;
    }

    this.closeDrawer();
  }

  /**
   * Drawer List Adapter
   */

  private class DrawerListAdapter extends BaseAdapter {

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
