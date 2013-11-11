package com.example.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

import com.loopj.android.http.*;

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
		try {
			getFashiolisatResults();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
    public void getFashiolisatResults() throws JSONException {
        ApiClient.get("", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject apiResponse) {
                // Pull out the first event on the public timeline
            	//apiResponse["items"]["image"]
        		//apiResponse["items"]["id"]
        		JSONArray items;
        		List<Article> articles = new ArrayList<Article>();
				try {
					items = apiResponse.getJSONArray("items");
					for (int i = 0; i < items.length(); ++i) {
	            	    JSONObject item = items.getJSONObject(i);
	            	    String id = item.getString("id");
	            	    String imageUrl = item.getString("image");
	            	    Article article = new Article(id, imageUrl);
	            	    articles.add(article);
	            	    //Log.i("Article", "index=" + i);
	            	    System.out.println("test" + i);
	            	    add(article);
	            	    
	            	}
				} catch (JSONException e) {
					e.printStackTrace();
				}

            	
                
                //String tweetText = firstEvent.getString("text");

                // Do something with the response
                System.out.println("test");
            }
            
            @Override
            public void onFailure(String responseBody, Throwable error) {
            	// TODO Auto-generated method stub
            	
            	super.onFailure(responseBody, error);
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
