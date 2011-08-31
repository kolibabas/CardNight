package com.memeworks.cardnight;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface GameState {
	
	public void 	Frame_Started(float frame_time);
	public Canvas 	Draw(Canvas c);
	public void		Enter();
	public void		Leave();

	public boolean  Touch_Event(MotionEvent evt);
	
}
