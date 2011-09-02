package com.memeworks.cardnight;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Entry Activity for the application. Holds application-wide variables.
 * @author kolibabas
 *
 */
public final class CardNight extends Activity {

	public static Context Game_Context;
	
	//Mode Enum
	public static int Current_Mode;
	public static final int MODE_HOTSEAT = 0;
	public static final int MODE_BLUETOOTH = 0;
	
	public static String Player_Name;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Game_Context = this;
        
        //TODO Set/Get Player name from memory
        Player_Name = "Player";
        
        //Instantiate all GameStates
        Util.STATE_PLAY = new PlayState();
        Util.STATE_MENU = new MenuState();
        
        //Initialize first state
        Util.STATE_CURRENT = Util.STATE_MENU;
    }
    
    /**
     * Sets the game mode when starting a game
     * @param mode The MODE to set
     */
    public static void Set_Mode(int mode)
    {
    	Current_Mode = mode;
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
        CardNight.this.finish();
    }
}

