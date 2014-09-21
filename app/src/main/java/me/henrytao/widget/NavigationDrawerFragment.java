package me.henrytao.widget;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by henrytao on 9/21/14.
 */
public class NavigationDrawerFragment extends Fragment {

  public static final String ARG_FRAGMENT_NAME = "fragment_name";

  public Bundle args = new Bundle();

  public NavigationDrawerFragment() {
    this.setArguments(args);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    String name = getArguments().getString(ARG_FRAGMENT_NAME);
    if (name != null) {
      this.getActivity().setTitle(name);
    }
    return null;
  }

}
