package com.memeworks.cardnight;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;



/**
 * Manages all Bluetooth devices, connections, and data 
 * TODO SRK This may need to be an Activity class to receive callbacks
 * http://developer.android.com/guide/topics/wireless/bluetooth.html
 * @author kolibabas
 *
 */
public final class BluetoothManager {
	
	public final static BluetoothAdapter Bluetooth_Adapter = BluetoothAdapter.getDefaultAdapter();
	
	private static Activity activity;
	private static boolean is_host;
	
	// Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;


    /**
     * Initializes all Bluetooth support. Called when attempting to start a Bluetooth game
     * @param main_activity Pointer to the main activity for this application
     * @param is_host Tells the manager whether it should act as a server or a client
     */
	public static void Init(boolean is_host, Activity main_activity)
	{
		activity = main_activity;
		BluetoothManager.is_host = is_host;
		
		//Check for Bluetooth adapter
		if (Bluetooth_Adapter == null) {
		    //TODO Device does not support Bluetooth
			return;
		}
		
		//Enable Bluetooth adapter
		if (!Bluetooth_Adapter.isEnabled()) {
		    Intent enable_bt_intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    activity.startActivityForResult(enable_bt_intent, REQUEST_ENABLE_BT);
		}
	}
	
	/**
	 * Called once Bluetooth is enabled on the device. Makes the device discoverable for pairing.
	 */
	public static void Bluetooth_Enabled()
	{
		//Make device discoverable
		Intent discoverable_intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverable_intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		activity.startActivity(discoverable_intent);
	}
	
	public void onActivityResult(int request_code, int result_code, Intent data) {
        switch (request_code) {
        case REQUEST_CONNECT_DEVICE:
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (result_code == Activity.RESULT_OK) {
                // Bluetooth is now enabled
            } else {
                // User did not enable Bluetooth or an error occurred
            }
        }
    }
	
	/**
	 * Gets a player name
	 * @param player_index Which player name to get
	 * @return The name
	 */
	public static String Get_Player_Name(int player_index)
	{
		return "Placeholder"; //TODO Get player name from other device, should be pre-populated before this call.
	}

}
