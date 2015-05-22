package com.heqinghqocsh.viewpagerandtabhost;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class NoteListFragment extends ListFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		NoteMessageAdapter adapter = new NoteMessageAdapter();
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.note_message_page, container,false);
//		ListView listView = ((ListView)view.findViewById(R.id.noteList));
//		NoteMessageAdapter adapter = new NoteMessageAdapter();
//		listView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		super.onViewCreated(view, savedInstanceState);
	}
	
	private class NoteMessageAdapter extends ArrayAdapter<String>{
		public NoteMessageAdapter() {
			super(getActivity(), 0);
		}
		
		@Override
		public int getCount() {
			return 20;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.note_message_list_item, null);
			}
			return convertView;
		}
	}
}
