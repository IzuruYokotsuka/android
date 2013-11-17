package com.example.test;
import android.util.Log;

import com.loopj.android.http.*;


public class ApiClient {
	  private static final String BASE_URL = "http://iapi.fashiolista.com/api/loves/?user_token=p5xjnqfxt9anjnzpc4rufmqwydjvkw4z94udzb5pcvwkjvggxcj7dv8fkxfc9pc2&version=1&limit=20&api_key=f31g233ncf125ydu87m0&type=everyone";

	  private static AsyncHttpClient client = new AsyncHttpClient();

	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		  Log.v("APICLIENT", "requesting:" + getAbsoluteUrl(url));
	      client.get(getAbsoluteUrl(url), params, responseHandler);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(getAbsoluteUrl(url), params, responseHandler);
	  }

	  private static String getAbsoluteUrl(String relativeUrl) {
	      return BASE_URL + relativeUrl;
	  }
}
