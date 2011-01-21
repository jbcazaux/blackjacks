package com.jbc;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	
	private final String TAG = "GameThread";
	
	private SurfaceHolder surfaceHolder;
	private Panel game;
	private long sleepTime;
	private final long delay = 20;

	private boolean paused = true;
	private boolean stopped = false;

	public GameThread(SurfaceHolder surfaceHolder, Panel panel) {
		this.surfaceHolder = surfaceHolder;
		game = panel;
	}

	public SurfaceHolder getSurfaceHolder() {
		return surfaceHolder;
	}

	@Override
	public void run() {
		Log.d(TAG, "thread start");
		Canvas c;
		while (!stopped) {
			//Log.d(TAG, "thread running...");
			if (waitForResume()) {
				break;
			}

			long beforeTime = System.nanoTime();

			c = null;
			try {
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					game.updateLogic();
					//game.updateGraphic();
					game.onDraw(c);
				}
			} finally {
				// do this in a finally so that if an exception is thrown
				// during the above, we don't leave the Surface in an
				// inconsistent state
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c);
				}
			}

			this.sleepTime = delay - ((System.nanoTime() - beforeTime) / 1000000L);

			try {
				// actual sleep code
				if (sleepTime > 0) {
					GameThread.sleep(sleepTime);
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	private boolean waitForResume() {
		synchronized (this) {
			if (stopped){
				return false;
			}
			while (paused) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (stopped){
				return true;
			}
			
			return false;
		}
		
	}
	
	public void stopThread() {
	    synchronized(this) {
	      paused = false;
	      stopped = true;
	      Log.d(TAG, "stop thread" );
	      this.notify();
	    }
	    // optionally, code an extra 'join()' statement if the callerneeds
	    // to wait until the thread has completely finished.
	    boolean retry = true;
		while (retry) {
			try {
				join();
				retry = false;
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}
	  }

	  public void pauseThread() {
	    synchronized(this) {
	      paused = true;
	      Log.d(TAG, "pause thread" );
	      this.notify();
	    }
	  }

	  public void resumeThread() {
	    synchronized(this) {
	      paused = false;
	      Log.d(TAG, "resume thread" );
	      this.notify();
	    }
	  }
}
