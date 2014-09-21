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
import android.widget.Toast;

import me.henrytao.notipocket.R;

public class NavigationDrawerActivity extends Activity {

  protected int activityLayoutId;     // need to declare

  protected int drawerLayoutId;       // need to declare

  protected int drawerIconId;         // need to declare

  protected int drawerOpenStringId;   // need to declare

  protected int drawerCloseStringId;  // need to declare

  private DrawerLayout drawerLayout;

  private ActionBarDrawerToggle drawerListener;

  protected boolean isDrawerOpen = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(this.activityLayoutId);

    final NavigationDrawerActivity obj = this;
    this.drawerLayout = (DrawerLayout) findViewById(this.drawerLayoutId);
    this.drawerListener = new ActionBarDrawerToggle(this, drawerLayout, this.drawerIconId, this.drawerOpenStringId, this.drawerCloseStringId) {
      public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        try {
          obj.onDrawerClosed(view);
          obj.invalidateOptionsMenu(); // trigger onPrepareOptionsMenu
        }catch(Exception ex){}
      }
      public void onDrawerOpened(View view) {
        super.onDrawerOpened(view);
        try {
          obj.onDrawerOpened(view);
          obj.invalidateOptionsMenu(); // trigger onPrepareOptionsMenu
        }catch(Exception ex){}
      }
    };
    drawerLayout.setDrawerListener(drawerListener);

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

  protected void closeDrawer(){
    this.drawerLayout.closeDrawers();
  }

  protected void onDrawerClosed(View view) {
    this.isDrawerOpen = false;
  }

  protected void onDrawerOpened(View view) {
    this.isDrawerOpen = true;
  }

}
