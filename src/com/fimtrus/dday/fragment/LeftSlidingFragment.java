package com.fimtrus.dday.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fimtrus.dday.R;
import com.fimtrus.dday.activity.MainActivity;
import com.fimtrus.dday.adapter.IconTextAdapter;
import com.fimtrus.dday.model.IconTextItem;

public class LeftSlidingFragment extends ListFragment {

	private ListView mRootLayout;
	private IconTextAdapter mIconTextAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootLayout = (ListView) inflater.inflate(R.layout.list, null);
		return mRootLayout;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initialize();
	}

	private void initialize() {

		initializeFields();
		initializeListeners();
		initializeView();

	}

	private void initializeFields() {
		mIconTextAdapter = new IconTextAdapter(getActivity());
		mIconTextAdapter.add(new IconTextItem("자동차보험"));
		mIconTextAdapter.add(new IconTextItem("상해/질병보험"));
		mIconTextAdapter.add(new IconTextItem("보상서비스"));
		mIconTextAdapter.add(new IconTextItem("마이페이지"));
		mIconTextAdapter.add(new IconTextItem("고객센터"));
		mIconTextAdapter.add(new IconTextItem("설정"));
	}

	private void initializeListeners() {

	}

	private void initializeView() {
		setListAdapter(mIconTextAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
//		switch (position) {
//
//		case 5:
//			getActivity().getSupportFragmentManager()
//			.beginTransaction()
//			.replace(R.id.content_frame, new ResponsibleWebFragment())
//			.commit();
//			((MainActivity)getActivity()).getSlidingMenu().toggle();
//			break;
//		default:
//			getActivity().getSupportFragmentManager().beginTransaction()
//					.replace(R.id.content_frame, new MainFragment()).commit();
//			((MainActivity) getActivity()).getSlidingMenu().toggle();
//			break;
//		}

	}
}
