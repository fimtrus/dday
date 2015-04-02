package com.fimtrus.dday.model.provider;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * @author jong-hyun.jeong
 *
 */
public class Hs25QueryHandler extends AsyncQueryHandler {

	private OnResultListener mOnResultListener = null;
	private Context mContext = null;
	private ContentResolver mContentResolver = null;
	
	public Hs25QueryHandler(Context context) {
		super(context.getContentResolver());
		this.mContentResolver = context.getContentResolver();
		this.mContext = context;
	}

	public Hs25QueryHandler(Context context, OnResultListener listener) {
		super(context.getContentResolver());
		this.mContext = context;
		this.mContentResolver = context.getContentResolver();
		this.mOnResultListener = listener;
	}
	/*
	 * Override Method
	 */
	@Override
	protected void onDeleteComplete(int token, Object cookie, int result) {
		super.onDeleteComplete(token, cookie, result);
		sendResult(Type.DELETE, token, cookie, result, null, null);
	}

	@Override
	protected void onInsertComplete(int token, Object cookie, Uri uri) {
		super.onInsertComplete(token, cookie, uri);
		sendResult(Type.INSERT, token, cookie, -1, uri, null);
	}

	@Override
	protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
		super.onQueryComplete(token, cookie, cursor);
		sendResult(Type.QUERY, token, cookie, -1, null, (cursor != null && cursor.getCount() > 0) ? cursor : null );
	}

	@Override
	protected void onUpdateComplete(int token, Object cookie, int result) {
		super.onUpdateComplete(token, cookie, result);
		sendResult(Type.UPDATE, token, cookie, result, null, null);
	}
	
	
	/**
	 * callback을 위한 리스너..
	 * callback을 받기 위해선, setOnResultListener 를 등록해주어야함!
	 * @param listener
	 */
	public void setOnResultListener(OnResultListener listener) {
		mOnResultListener = listener;
	}
	
	public OnResultListener getOnResultListener() {
		return mOnResultListener;
	}
	
	
	/**
	 * 결과 값을 하위 클래스로 넘겨준다..단, 리스너가 등록되어있지 않다면 넘겨주지 않는다. </br>
	 * implements 한 클래스에서.. switch로 구현해주면 편하다.</br>
	 * switch(type) {</br>
	 * case QUERY :</br>
	 * ...</br>
	 * }</br>
	 * </br>
	 * @param type 		: Query, Insert, Delete, Update
	 * @param token 	: 요청시 유저가 지정한 토큰값.
	 * @param cookie 	: ?
	 * @param result 	: delete, update 시에 발생하는 return 값.
	 * @param uri 		: Insert 실행시 리턴..
	 * @param cursor	: Query 실행시 리턴되는 cursor
	 */
	private void sendResult(Type type, int token, Object cookie, int result, Uri uri, Cursor cursor) {
		if (mOnResultListener != null) {
			mOnResultListener.onResult(type, token, cookie, result, uri, cursor);
		}
	}
	
	public interface OnResultListener {
		void onResult(Type type, int token, Object cookie, int result, Uri uri, Cursor cursor);
	}
	
	/**
	 * @author jong-hyun.jeong
	 * Query, Create, Insert, Delete 에 관한 타입 정의 
	 */
	public enum Type {
		QUERY(1, "QUERY"),
		INSERT(2, "INSERT"),
		DELETE(3, "DELETE"),
		UPDATE(4, "UPDATE");
		
		private int id;
		private String type;
		
		private Type (final int id, final String type) {
			this.id = id;
			this.type = type;
		}
		public static int getId(Type type) {
			
			for(Type c : Type.values()) {
				if (c == type) {
					return c.id;
				}
			}
			return -1;
		}
		public static String getType(Type type) {
			
			for(Type c : Type.values()) {
				if (c == type) {
					return c.type;
				}
			}
			return "";
		}
		@Override
		public String toString() {
			return type;
		}
	}
}
