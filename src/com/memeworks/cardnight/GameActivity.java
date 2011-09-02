package com.memeworks.cardnight;

import com.memeworks.cardnight.R;
import com.memeworks.cardnight.MainView.MainThread;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

public final class GameActivity extends Activity {

    /** A handle to the thread that's actually running the animation. */
    private MainThread main_thread;

    /** A handle to the View in which the game is running. */
    @SuppressWarnings("unused")
	private MainView main_view;
    
    /** A dialog to allow the user to set their name */
    private final int DIALOG_SET_NAME = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // tell system to use the layout defined in the XML file
        setContentView(R.layout.main);

        // get handles to the MainView from XML, and its MainThread
        main_view = (MainView) findViewById(R.id.game);
        main_thread.Start();
    } 
	
    /**
     * Invoked when the Activity loses user focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        //TODO Pause thread
    }
    
    /**
     * Standard override to get key-press events.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        
        return true;
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }

}
