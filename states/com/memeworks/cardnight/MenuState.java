package com.memeworks.cardnight;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.EditText;

public class MenuState implements GameState {

	//Hotseat Button
	private static Rect hotseat_button_rect = new Rect();
	private static boolean hotseat_button_pressed = false;
	//Bluetooth Button
	private static Rect bluetooth_button_rect = new Rect();
	private static boolean bluetooth_button_pressed = false;
	//Set Name Button
	private static Rect set_name_button_rect = new Rect();
	private static boolean set_name_button_pressed = false;
	//Set Card Back Button
	private static Rect set_card_back_button_rect = new Rect();
	private static boolean set_card_back_button_pressed = false;
	
	public void Frame_Started(float frame_time) {
		// TODO Auto-generated method stub

	}

	public boolean Touch_Event(MotionEvent evt) {
		hotseat_button_pressed = false;
		bluetooth_button_pressed = false;
		set_name_button_pressed = false;
		set_card_back_button_pressed = false;
		
		switch(evt.getAction())
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			int x = (int) evt.getX();
			int y = (int) evt.getY();
			if (hotseat_button_rect.contains((int)evt.getX(), (int)evt.getY()))
			{
				hotseat_button_pressed = true;
			}
			else if (bluetooth_button_rect.contains((int)evt.getX(), (int)evt.getY()))
			{
				bluetooth_button_pressed = true;
			}
			else if (set_name_button_rect.contains((int)evt.getX(), (int)evt.getY()))
			{
				set_name_button_pressed = true;
			}
			else if (set_card_back_button_rect.contains((int)evt.getX(), (int)evt.getY()))
			{
				set_card_back_button_pressed = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (hotseat_button_rect.contains((int)evt.getX(), (int)evt.getY()))
			{
				CardNight.Set_Mode(CardNight.MODE_HOTSEAT);
				Util.Change_State(Util.STATE_PLAY);
			}
			else if (bluetooth_button_rect.contains((int)evt.getX(), (int)evt.getY()))
			{
				CardNight.Set_Mode(CardNight.MODE_BLUETOOTH);
				Util.Change_State(Util.STATE_PLAY);
			}
			else if (set_name_button_rect.contains((int)evt.getX(), (int)evt.getY()))
			{
				//Show rename dialog
				AlertDialog.Builder alert = new AlertDialog.Builder(CardNight.Game_Context);

				alert.setTitle("Set Name");
				alert.setMessage("Enter your name:");

				// Set an EditText view to get user input 
				final EditText input = new EditText(CardNight.Game_Context);
				alert.setView(input);

				alert.setPositiveButton("Set Name", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					CardNight.Player_Name = input.getText().toString();	
					//TODO Sanitize text input
				  }
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    //Dialog closes itself on negative.
				  }
				});

				alert.show();
			}
			else if (set_card_back_button_rect.contains((int)evt.getX(), (int)evt.getY()))
			{
				//TODO Show card back select dialog
			}
			break;
		default:
			break;
		}
		return false;
	}

	public void Enter() {
		
		//Set Button Locations
		hotseat_button_rect.set(Util.SCREEN_CENTER_X - Util.BMP_HOTSEAT_BUTTON.getWidth(),
								Util.SCREEN_CENTER_Y - Util.BMP_HOTSEAT_BUTTON.getHeight(),
								Util.SCREEN_CENTER_X,
								Util.SCREEN_CENTER_Y);
		
		bluetooth_button_rect.set(Util.SCREEN_CENTER_X,
								  Util.SCREEN_CENTER_Y - Util.BMP_BLUETOOTH_BUTTON.getHeight(),
								  Util.SCREEN_CENTER_X + Util.BMP_BLUETOOTH_BUTTON.getWidth(),
								  Util.SCREEN_CENTER_Y);
		
		set_name_button_rect.set(Util.SCREEN_CENTER_X - Util.BMP_SET_NAME_BUTTON.getWidth(),
								 Util.SCREEN_CENTER_Y,
								 Util.SCREEN_CENTER_X,
								 Util.SCREEN_CENTER_Y + Util.BMP_SET_NAME_BUTTON.getHeight());
		
		set_card_back_button_rect.set(Util.SCREEN_CENTER_X,
								      Util.SCREEN_CENTER_Y,
									  Util.SCREEN_CENTER_X + Util.BMP_SET_CARD_BACK_BUTTON.getWidth(),
									  Util.SCREEN_CENTER_Y + Util.BMP_SET_CARD_BACK_BUTTON.getHeight());
	}

	public void Leave() {
		// TODO Auto-generated method stub
		
	}

	public Canvas Draw(Canvas c) {
		c.drawBitmap(Util.BMP_MENU_BG, 0, 0, null);
		
		if (hotseat_button_pressed)
		{
			c.drawBitmap(Util.BMP_HOTSEAT_BUTTON_PRESSED, hotseat_button_rect.left, hotseat_button_rect.top, null);
		}
		else
		{
			c.drawBitmap(Util.BMP_HOTSEAT_BUTTON, hotseat_button_rect.left, hotseat_button_rect.top, null);
		}
		
		if (bluetooth_button_pressed)
		{
			c.drawBitmap(Util.BMP_BLUETOOTH_BUTTON_PRESSED, bluetooth_button_rect.left, bluetooth_button_rect.top, null);
		}
		else
		{
			c.drawBitmap(Util.BMP_BLUETOOTH_BUTTON, bluetooth_button_rect.left, bluetooth_button_rect.top, null);
		}
		
		if (set_name_button_pressed)
		{
			c.drawBitmap(Util.BMP_SET_NAME_BUTTON_PRESSED, set_name_button_rect.left, set_name_button_rect.top, null);
		}
		else
		{
			c.drawBitmap(Util.BMP_SET_NAME_BUTTON, set_name_button_rect.left, set_name_button_rect.top, null);
		}
		
		if (set_card_back_button_pressed)
		{
			c.drawBitmap(Util.BMP_SET_CARD_BACK_BUTTON_PRESSED, set_card_back_button_rect.left, set_card_back_button_rect.top, null);
		}
		else
		{
			c.drawBitmap(Util.BMP_SET_CARD_BACK_BUTTON, set_card_back_button_rect.left, set_card_back_button_rect.top, null);
		}
		return null;
	}

}
