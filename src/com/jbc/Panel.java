package com.jbc;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
	private final String TAG = "Panel";
	private GameThread _thread;
	private Paint paint;
	private Bitmap deckBitmap;
	
	private List<CardGraphic> cards = new ArrayList<CardGraphic>();
	
	
	private int r = 0;
	
	private CardGraphic card;
	
	public Panel(Context context) {
		super(context);
		getHolder().addCallback(this);
		Log.d(TAG, "new GameThread() ");
		_thread = new GameThread(getHolder(), this);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setARGB(200, 0xAA, 0xAA, 0xAA);

		setFocusable(true);
		
		initGraphics();
		
		for (int i = 0; i < 10; i += 10){
			cards.add(new CardGraphic(deckBitmap, i, 60, 600, 300));
		}
		
		//initGraphics();
		_thread.start();
	}
	
	
	private void initGraphics() {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;
		//deckBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.deck);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		
		// green color
		canvas.drawColor(0xFF00AA00);
		
		canvas.drawCircle(50, 50, r++, paint);
		if (r > 20){
			r = 1;
		}
		
		for (CardGraphic card : cards) {
			canvas.drawBitmap(card._bitmap, card.x, card.y, null);	
		}
		
		
	}
	
	public void updateLogic(){
		for (CardGraphic card : cards) {
			card.animate();	
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		for (CardGraphic card : cards) {
			card.move(getWidth(), getHeight());	
		}
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		deckBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.deck);
		_thread.resumeThread();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) _thread.pauseThread();
    }

	public GameThread getGameThread() {
		return _thread;
	}
	

	

}
