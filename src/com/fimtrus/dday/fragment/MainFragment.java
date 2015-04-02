package com.fimtrus.dday.fragment;

import com.fimtrus.dday.R;
import com.fimtrus.dday.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class MainFragment extends Fragment {

	private FrameLayout mRootLayout;
	private WebView mWebView;
	private WebSettings mSettings;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootLayout = (FrameLayout) inflater.inflate(R.layout.fragment_picture,
				null);
		return mRootLayout;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
