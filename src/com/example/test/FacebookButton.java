package com.example.test;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.widget.LoginButton;

public class FacebookButton extends LoginButton {
	
	private List<String> mDefaultPermissions = Arrays.asList("user_likes", "user_status");
	
    public FacebookButton(Context context) {
        super(context);
        this.setDefaultPermissions();
    }

    public FacebookButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setDefaultPermissions();
    }

    public FacebookButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setDefaultPermissions();
    }
    
    private void setDefaultPermissions() {
    	//setReadPermissions(mDefaultPermissions);
    }
}
