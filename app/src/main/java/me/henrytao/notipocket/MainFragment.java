package me.henrytao.notipocket;

import com.etsy.android.grid.StaggeredGridView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    View view = inflater.inflate(R.layout.fragment_main, container, false);

    GridDataAdapter gridDataAdapter = new GridDataAdapter(this.getActivity(), this.getSampleData());
    StaggeredGridView gridView = (StaggeredGridView) view.findViewById(R.id.grid_view);
    gridView.setAdapter(gridDataAdapter);

    return view;
  }

  public class Data {

    public String title;

    public String description;
  }

  private ArrayList<Data> getSampleData() {
    ArrayList<Data> res = new ArrayList<Data>();
    for (int i = 0; i < 30; i++) {
      Data data = new Data();
      data.title = "Pinterest Card";
      data.description = "Super awesome description " + i;
      res.add(data);
    }
    return res;
  }

  /**
   *
   */
  public class GridDataAdapter extends BaseAdapter {

    private Context context;

    private List<Data> list;

    public GridDataAdapter(Context context, List<Data> list) {
      this.context = context;
      this.list = list;
    }

    @Override
    public int getCount() {
      return this.list.size();
    }

    @Override
    public Object getItem(int position) {
      return this.list.get(position);
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
        view = inflater.inflate(R.layout.grid_view_item_main, parent, false);
      }

      TextView title = (TextView) view.findViewById(R.id.title);
      TextView description = (TextView) view.findViewById(R.id.description);
      Data data = (Data) this.getItem(position);

      title.setText(data.title);
      description.setText(data.description);

      return view;
    }
  }


}