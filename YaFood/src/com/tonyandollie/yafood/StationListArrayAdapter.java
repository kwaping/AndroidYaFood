package com.tonyandollie.yafood;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StationListArrayAdapter extends ArrayAdapter<Station> {

	public StationListArrayAdapter(Context context, ArrayList<Station> stations) {
		super(context, R.layout.fragment_station, stations);
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null ) {
			LayoutInflater inflater  = LayoutInflater.from(getContext());
			view = (View) inflater.inflate(
					R.layout.fragment_station,
					parent,
					false
					);
		} else  {
			view = (View) convertView;
		}
		
		Station s = getItem(position);

		TextView name = (TextView) view.findViewById(R.id.tvStation);
		name.setText(s.getName());
		
		ArrayList<Item> items = s.getItems();
		ItemListArrayAdapter itemsAdapter = new ItemListArrayAdapter(getContext(), items);
		
		ListView lvItems = (ListView) view.findViewById(R.id.lvItems);
		lvItems.setAdapter(itemsAdapter);

		return view;
	}

}
