package com.memeworks.cardnight;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * General Utility class
 * @author kolibabas
 *
 */
public class Util {
	
	//Global Variables
    public static int SCREEN_WIDTH;
	public static int SCREEN_CENTER_X;
	public static int SCREEN_HEIGHT;
	public static int SCREEN_CENTER_Y;
	
	public static GameState STATE_CURRENT;
	public static PlayState STATE_PLAY;
	public static MenuState	STATE_MENU;
	
	//Bitmaps
	
	//Global Constants

		
	//Enums
	
	
	//Functions
	
	/**
	 * Loads the given Bitmap resource into memory.
	 * @param resources The Application's resources
	 * @param bitmap The Bitmap to store the loaded data in 
	 * @param id The ID of the resource to load. Usage: R.drawable.X
	 */
	public static void Load_Bitmap(Resources resources, Bitmap bitmap, int id)
	{
		bitmap = BitmapFactory.decodeResource(resources, id);
	}
	
	/**
	 * Changes the current state of the game.
	 * @param state The GameState to change to
	 */
    public static void Change_State(GameState state)
    {
    	Util.STATE_CURRENT.Leave();
    	Util.STATE_CURRENT = state;
    	Util.STATE_CURRENT.Enter();
    }


}
