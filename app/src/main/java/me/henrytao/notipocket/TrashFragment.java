package me.henrytao.notipocket;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.henrytao.widget.NavigationDrawerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrashFragment extends NavigationDrawerFragment {

  public TrashFragment() {
    super();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.fragment_trash, container, false);
  }

}
