package me.henrytao.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import me.henrytao.notipocket.R;

public class NavigationDrawerActivity extends Activity {

  protected int activityLayoutId;

  protected int drawerLayoutId;

  protected int drawerListId;

  protected int drawerIcon;

  protected int drawerOpenString;

  protected int drawerCloseString;

  private ActionBarDrawerToggle drawerListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(this.activityLayoutId);

    DrawerLayout drawerLayout = (DrawerLayout) findViewById(this.drawerLayoutId);
    this.drawerListener = new ActionBarDrawerToggle(this, drawerLayout, this.drawerIcon, this.drawerOpenString, this.drawerCloseString);
    drawerLayout.setDrawerListener(drawerListener);

    ListView drawerList = (ListView) findViewById(this.drawerListId);
    DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this);
    drawerList.setAdapter(drawerListAdapter);
    drawerList.setOnItemClickListener(new DrawerItemClickListener());

    getActionBar().setHomeButtonEnabled(true);
    getActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (this.drawerListener.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    this.drawerListener.onConfigurationChanged(newConfig);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    this.drawerListener.syncState();
  }

  protected void onDrawerItemClick(int position) {

  }

  /**
   * Customize drawer item click listener
   */
  class DrawerItemClickListener implements ListView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      onDrawerItemClick(position);
    }
  }

  /**
   * Customize adapter for navigation drawer
   */
  class DrawerListAdapter extends BaseAdapter {

    private Context context;

    public DrawerListAdapter(Context context) {
      this.context = context;
    }

    @Override
    public int getCount() {
      return 0;
    }

    @Override
    public Object getItem(int position) {
      return null;
    }

    @Override
    public long getItemId(int position) {
      return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      return null;
    }
  }

}
