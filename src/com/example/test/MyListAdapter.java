package com.example.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.http.Header;
import org.json.*;

import com.loopj.android.http.*;
import com.squareup.picasso.Picasso;

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
			CopyOnWriteArrayList<Article> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		getFashiolistaResults();
	}
	
    public void getFashiolistaResults() {
    	AsyncHttpClient client = new AsyncHttpClient();
    	client.get("http://iapi.fashiolista.com/api/loves/?user_token=p5xjnqfxt9anjnzpc4rufmqwydjvkw4z94udzb5pcvwkjvggxcj7dv8fkxfc9pc2&version=1&limit=2&api_key=f31g233ncf125ydu87m0&type=everyone", new JsonAdapterResponseHandler(this) {
    	    @Override
    	    public void onSuccess(JSONObject apiResponse) {
    	    	Log.i("NOTIFY", "Got some json");
    	    	CopyOnWriteArrayList<Article> articles = new CopyOnWriteArrayList <Article>();
    	    	try {
    	    		JSONObject objectList = apiResponse.getJSONObject("object_list");
    	    		JSONArray loves = objectList.getJSONArray("items");
					for (int i = 0; i < loves.length(); ++i) {
	            	    JSONObject love = loves.getJSONObject(i);
	            	    String loveId = love.getString("id");
	            	    JSONObject entity = love.getJSONObject("entity");
	            	    String entityId = entity.getString("id");
	            	    String imageUrl = entity.getString("image");
	            	    Article article = new Article(loveId, imageUrl);
	            	    articles.add(article);
					}
					mAdapter.addAll(articles);
					mAdapter.notifyDataSetChanged();
					Log.i("NOTIFY", "Data set changed");
            	} catch (JSONException e) {
					e.printStackTrace();
				}
    	    }
    	});
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
		View view = convertView;
		// Reuse old views for performance
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.rowlayout, parent, false);
	    }
		TextView textView = (TextView) view.findViewById(R.id.label);
		ImageView imageView = (ImageView) view.findViewById(R.id.icon);
		Article article = getItem(position);
		textView.setText(article.mTitle);
		
		Picasso.with(getContext()).load(article.mImageUrl).into(imageView);

		return view;
	}

}
