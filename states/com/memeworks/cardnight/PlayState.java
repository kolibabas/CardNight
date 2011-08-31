package com.memeworks.cardnight;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class PlayState implements GameState {

	public PlayState() {
		// TODO Auto-generated method stub
	}

	public Canvas Draw(Canvas c) {
		OverlayPanel.Draw(c);
		return c;
	}

	public void Frame_Started(float frame_time) {
		OverlayPanel.Frame_Started(frame_time);
	}	

	public boolean Touch_Event(MotionEvent evt) {
		if (evt.getAction() == MotionEvent.ACTION_UP)
		{
			if (!OverlayPanel.Visible)
			{
				OverlayPanel.Show(OverlayPanel.UI_TURN_NOTIFY);
			}
		}
		
		if (OverlayPanel.Visible)
		{
			OverlayPanel.Touch_Event(evt);
		}
		return false;
	}

	public void Enter() {
		// TODO Auto-generated method stub
	}

	public void Leave() {
		// TODO Auto-generated method stub
	}

}
