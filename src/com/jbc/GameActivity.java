package com.jbc;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class GameActivity extends Activity {
    
private final String TAG = "GameActivity";
	
	private Panel p;
	
	public void onDraw(Canvas c){
		
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate()");
        if (p == null){
        	p = new Panel(this);
        }
        setContentView(p);
        if (savedInstanceState == null) {
            // we were just launched: set up a new game
            //mLunarThread.setState(LunarThread.STATE_READY);
            Log.w(this.getClass().getName(), "SIS is null");
        } else {
            // we are being restored: resume a previous game
            p.getGameThread().pauseThread();
            Log.w(this.getClass().getName(), "SIS is nonnull");
        }
        
    }

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy()");
		super.onDestroy();
		//p.getGameThread().stopThread();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause()");
		super.onPause();
		p.getGameThread().pauseThread();
	}

	@Override
	protected void onPostResume() {
		Log.d(TAG, "onPostResume()");
		super.onPostResume();
		
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart()");
		super.onRestart();
		
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume()");
		super.onResume();
		//p.getGameThread().resumeThread();
	}

	@Override
	protected void onStart() {
		Log.d(TAG, "onStart()");
		super.onStart();
		
		/*p.getGameThread().runMe();
		p.getGameThread().pauseThread();
		p.getGameThread().start();*/
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "onStop()");
		super.onStop();
	}
}