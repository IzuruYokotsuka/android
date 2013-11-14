package com.example.test;

import com.loopj.android.http.JsonHttpResponseHandler;

import android.widget.ArrayAdapter;

public class JsonAdapterResponseHandler extends JsonHttpResponseHandler {
	public ArrayAdapter mAdapter;
	
	public JsonAdapterResponseHandler(ArrayAdapter adapter) {
		super();
		mAdapter = adapter;
	}
	

}
