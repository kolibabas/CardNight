package com.memeworks.cardnight;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class PlayState implements GameState {
	
	private GameManager game;

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
		//Pass along events to the overlay
		if (OverlayPanel.Visible)
		{
			OverlayPanel.Touch_Event(evt);
		}
		return false;
	}

	public void Enter() {
		OverlayPanel.Show(OverlayPanel.UI_PLAYER_COUNT);
		// TODO Instantiate Game Manager. Overlay panel must block to get player and deck counts.
	}

	public void Leave() {
		// TODO Auto-generated method stub
	}

}
