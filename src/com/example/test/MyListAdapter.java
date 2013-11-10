package com.example.test;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<Article> {

	public MyListAdapter(Context context, int resource, Article[] objects) {
		super(context, resource, objects);
	}

	/*
	 * public MyListAdapter(Context context, int resource, int
	 * textViewResourceId) { super(context, resource, textViewResourceId); //
	 * TODO Auto-generated constructor stub }
	 * 
	 * public MyListAdapter(Context context, int resource, Article[] objects) {
	 * super(context, resource, objects); // TODO Auto-generated constructor
	 * stub }
	 * 
	 * public MyListAdapter(Context context, int resource, List<Article>
	 * objects) { super(context, resource, objects); // TODO Auto-generated
	 * constructor stub }
	 */

	public MyListAdapter(Context context, int resource, int textViewResourceId,
			Article[] objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	/*
	 * public MyListAdapter(Context context, int resource, int
	 * textViewResourceId, List<Article> objects) { super(context, resource,
	 * textViewResourceId, objects); // TODO Auto-generated constructor stub }
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		Article article = getItem(position);
		textView.setText(article.mTitle);

		new DownloadImageTask((ImageView) imageView)
				.execute(article.mImageUrl);

		return rowView;
	}

}
