package me.henrytao.notipocket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.henrytao.widget.NavigationDrawerFragment;

/**
 * Created by henrytao on 9/21/14.
 */
public class MainFragment extends NavigationDrawerFragment {

  public MainFragment() {
    super();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

}