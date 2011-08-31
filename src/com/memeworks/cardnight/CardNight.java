package com.memeworks.cardnight;

import android.app.Activity;
import android.os.Bundle;

/**
 * Entry Activity for the application
 * @author Fuego
 *
 */
public class CardNight extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Instantiate all GameStates
        Util.STATE_PLAY = new PlayState();
        Util.STATE_MENU = new MenuState();
        
        //Initialize first state
        Util.STATE_CURRENT = Util.STATE_PLAY;
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
        CardNight.this.finish();
    }
}

