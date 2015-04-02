package com.fimtrus.dday.fragment;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fimtrus.dday.R;
import com.fimtrus.dday.constant.Key;
import com.fimtrus.dday.util.DDay;
import com.fimtrus.dday.util.Util;

public class TimerFragment extends android.support.v4.app.Fragment implements  DatePickerDialog.OnDateSetListener, OnTimeSetListener {

	private FrameLayout mRootLayout;
	
	private TextView mDDayTextView;

	private int mYear;

	private Integer mMonth;

	private Integer mDay;

	private Integer mHour;

	private Integer mMinute;

	private DatePickerDialog mDatePickerDialog;

	private TimePickerDialog mTimePickerDialog;

	private Button mTimeTextView;

	private Button mDateTextView;

	private Button mStartButton;
	private Button mEndButton;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootLayout = (FrameLayout) inflater.inflate(R.layout.fragment_timer,
				null);
		initialize();
		return mRootLayout;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initialize() {
		initializeFields();
		initializeListeners();
		initializeView();

		
	}

	private void initializeFields() {
		if (Util.getPreference(getActivity(), Key.YEAR) == null) {
			Calendar cal = Calendar.getInstance();
			Util.setPreference(getActivity(), Key.YEAR, cal.get(Calendar.YEAR));
			Util.setPreference(getActivity(), Key.MONTH, cal.get(Calendar.MONTH) + 1);
			Util.setPreference(getActivity(), Key.DAY, cal.get(Calendar.DAY_OF_MONTH));
			Util.setPreference(getActivity(), Key.HOUR, cal.get(Calendar.HOUR_OF_DAY));
			Util.setPreference(getActivity(), Key.MINUTE, cal.get(Calendar.MINUTE));
		} else {

		}
		mYear = (Integer) Util.getPreference(getActivity(), Key.YEAR);
		mMonth = (Integer) Util.getPreference(getActivity(), Key.MONTH);
		mDay = (Integer) Util.getPreference(getActivity(), Key.DAY);
		mHour = (Integer) Util.getPreference(getActivity(), Key.HOUR);
		mMinute = (Integer) Util.getPreference(getActivity(), Key.MINUTE);

		mDatePickerDialog = new DatePickerDialog(getActivity(), this, mYear, mMonth, mDay);
		mTimePickerDialog = new TimePickerDialog(getActivity(), this, mHour, mMinute, false);

		mDateTextView = (Button) mRootLayout.findViewById(R.id.textview_date);
		mTimeTextView = (Button) mRootLayout.findViewById(R.id.textview_time);
		mDDayTextView = (TextView) mRootLayout.findViewById(R.id.fullscreen_content);
		mStartButton = (Button) mRootLayout.findViewById(R.id.button_start);
		mEndButton = (Button) mRootLayout.findViewById(R.id.button_end);
	}

	private void initializeListeners() {

		mStartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startTask();
			}
		});
		mEndButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopTask();
			}
		});

		mDateTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mDatePickerDialog.show();
			}
		});
		mTimeTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mTimePickerDialog.show();
			}
		});

	}

	private void initializeView() {
		setDateTextView(mYear, mMonth, mDay);
		setTimeTextView(mHour, mMinute);
	}

//	@Override
//	protected void onPostCreate(Bundle savedInstanceState) {
//		super.onPostCreate(savedInstanceState);
//
//		// Trigger the initial hide() shortly after the activity has been
//		// created, to briefly hint to the user that UI controls
//		// are available.
//	}

	private GetDDayTask mTask;

	class GetDDayTask extends AsyncTask<Void, String, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {

			while (!isCancelled()) {
				String text = DDay.getDDay(mYear, mMonth, mDay, mHour, mMinute);
				if (text.equals("")) {
					this.cancel(true);
				}
				publishProgress(text);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			mDDayTextView.setText(values[0]);
		}

	}

	@Override
	public void onPause() {
		super.onPause();
		if (mTask == null) return;
		mTask.cancel(true);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mTask != null) {
			if (mTask.getStatus() == AsyncTask.Status.FINISHED) {
				mTask = null;
				startTask();
			}
		}
			
	}

	private void startTask() {
		if (mTask == null) {
			mTask = new GetDDayTask();
			mTask.execute();
		}
	}

	private void stopTask() {
		if (mTask == null) return;
		mTask.cancel(true);
		mTask = null;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		Util.setPreference(getActivity(), Key.YEAR, year);
		Util.setPreference(getActivity(), Key.MONTH, monthOfYear);
		Util.setPreference(getActivity(), Key.DAY, dayOfMonth);
		mYear = year;
		mMonth = monthOfYear;
		mDay = dayOfMonth;
		setDateTextView(year, monthOfYear, dayOfMonth);
	}

	@Override
	public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
		Util.setPreference(getActivity(), Key.HOUR, arg1);
		Util.setPreference(getActivity(), Key.MINUTE, arg2);
		mHour = arg1;
		mMinute = arg2;
		setTimeTextView(arg1, arg2);
	}

	private void setDateTextView(int year, int month, int day) {
		mDateTextView.setText(Util.getDate(year, month, day));
	}

	private void setTimeTextView(int hour, int minute) {
		mTimeTextView.setText(Util.getTime(hour, minute));
	}
}
