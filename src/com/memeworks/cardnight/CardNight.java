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

	/** Pointer to the game's Context reference */
	public static Context Game_Context;
	
	/** Name for the local player */
	public static String Player_Name;
	
	//Mode Enum
	public static int Current_Mode;
	public static final int MODE_HOTSEAT = 0;
	public static final int MODE_BLUETOOTH = 0;

	//Suit Enum
	public static final int SUIT_SPADES = 0;
	public static final int SUIT_CLUBS = 1;
	public static final int SUIT_DIAMONDS = 2;
	public static final int SUIT_HEARTS = 3;
	public static final int SUIT_COUNT = 4;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Game_Context = this;
        
        //TODO Set/Get Player name from device memory
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

