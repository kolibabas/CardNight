package com.memeworks.cardnight;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

final class MainView extends SurfaceView implements SurfaceHolder.Callback {

	final class MainThread extends Thread {
		/** Running state of the application */
		private boolean is_running;

        /** Handle to the surface manager object we interact with */
        private SurfaceHolder surface_holder;
   
        /** Time since the last frame event occurred */
        private float time_since_last_frame;
        
        /** Time that the last frame event occurred */
        private long time_of_last_frame;

		public MainThread(SurfaceHolder holder, Context context, Handler handler) {
            surface_holder = holder;
            
            // Load Resources here
            Resources res = context.getResources();
            
            //World Elements
            Util.BMP_OVERLAY_PANEL_LEFT = BitmapFactory.decodeResource(res, R.drawable.overlaypanelleft);
            Util.BMP_OVERLAY_PANEL_RIGHT = BitmapFactory.decodeResource(res, R.drawable.overlaypanelright);
            Util.BMP_OVERLAY_PANEL_GO_BUTTON = BitmapFactory.decodeResource(res, R.drawable.overlaygobutton);
            Util.BMP_OVERLAY_PANEL_GO_BUTTON_PRESSED = BitmapFactory.decodeResource(res, R.drawable.overlaygobuttonpressed);
            Util.BMP_OVERLAY_PANEL_PLAY_BUTTON = BitmapFactory.decodeResource(res, R.drawable.overlayplaybutton);
            Util.BMP_OVERLAY_PANEL_PLAY_BUTTON_PRESSED = BitmapFactory.decodeResource(res, R.drawable.overlayplaybuttonpressed);
            Util.BMP_OVERLAY_PANEL_PASS_BUTTON = BitmapFactory.decodeResource(res, R.drawable.overlaypassbutton);
            Util.BMP_OVERLAY_PANEL_PASS_BUTTON_PRESSED = BitmapFactory.decodeResource(res, R.drawable.overlaypassbuttonpressed);
           
		}

		/**
		 * Main run loop for the application
		 */
        @Override
        public void run() {
            while (is_running) {
            	
                Canvas c = null;
                try {
                	synchronized(TouchEventMutex) {
                		TouchEventMutex.notify();
                	}
                	Thread.yield();
                	
                    c = surface_holder.lockCanvas();
                    synchronized (surface_holder) {
                    	Frame_Started();
                    	Draw(c);
                    }
                }
                catch (Exception e) {
                	Log.e("Exception", e.getStackTrace().toString());
                } 
                finally {
                    if (c != null) {
                        surface_holder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
   
        /**
         * Handles draw events for the application.
         * @param c The canvas to draw on
         */
		private void Draw(Canvas c) {
			//Canvas must be cleared before each draw
			c.drawColor(0, PorterDuff.Mode.CLEAR);

			//Tell the current state to draw itself
			Util.STATE_CURRENT.Draw(c);
		}

		/**
         * Starts the game
         */
        public void Start() {
        	is_running = true;
            Util.Change_State(Util.STATE_PLAY);
        }
        
        /**
         * Called when a frame event occurs. Responsible for updating before a draw event.
         */
        private void Frame_Started() {
        	//Calculate frame time
            long now = System.currentTimeMillis();
            if (time_of_last_frame > now) return;
            time_since_last_frame = (now - time_of_last_frame) / 1000.0f;
            time_of_last_frame = now;
            
            //Handle first frame shenanegans
            if (time_since_last_frame > 100000)
            {
            	time_since_last_frame = 0.0000f;
            }
            
            //Tell the current state to handle a frame event
            Util.STATE_CURRENT.Frame_Started(time_since_last_frame);
        }

		//Input Handlers
        /**
         * 
         * @param evt
         * @return
         */
		public boolean doTouchEvent(MotionEvent evt) {
			//Tell the current state to handle the touch event
			Util.STATE_CURRENT.Touch_Event(evt);			
			return true;
		}

		/**
		 * Callback invoked when the surface dimensions change. Sets up global variables for screen dimensions.
		 * @param width Width of the new surface
		 * @param height Height of the new surface
		 */
		public void setSurfaceSize(int width, int height) {
            synchronized (surface_holder) {
            	Util.SCREEN_WIDTH = width;
            	Util.SCREEN_HEIGHT = height;
            	Util.SCREEN_CENTER_X = width / 2;
            	Util.SCREEN_CENTER_Y = height / 2;
  
                Start();
            }
		}

    };
    
    /** The thread that actually draws the animation */
    private MainThread thread;
    
    /** Mutex object for touch thread */
    private Object TouchEventMutex = new Object();
    
    public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);

        // register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        // create thread only; it's started in surfaceCreated()
        thread = new MainThread(holder, context, new Handler() {
        	@Override
            public void handleMessage(Message m) {
        		
            }
        });

        // make sure we get key events
        setFocusableInTouchMode(true);
        setFocusable(true); 
	}
 
    
    /**
     * Fetches the animation thread corresponding to this MainView.
     * 
     * @return the animation thread
     */
    public MainThread getThread() {
        return thread;
    }

    /**
     * Standard override for touch events
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent evt) {
    	thread.doTouchEvent(evt);
    	
    	synchronized(TouchEventMutex) {
    		try {
    			TouchEventMutex.wait(1000L);
    		}
    		catch (InterruptedException e) {}
    	}
    	return true;
    }

    /**
     * Standard window-focus override. Notice focus lost so we can pause on
     * focus lost. e.g. user switches to take a call.
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) {
        	thread.is_running = false;
        }
    }

    
    /* Callback invoked when the surface dimensions change. */
	public void surfaceChanged(SurfaceHolder arg0, int format, int width, int height) {
		thread.setSurfaceSize(width, height);
	}

	
	/*
     * Callback invoked when the Surface has been created and is ready to be
     * used.
     */
	public void surfaceCreated(SurfaceHolder holder) {
		// Start the thread here so that we don't busy-wait in run()
        // Waiting for the surface to be created
		thread.start();
	}

	
    /*
     * Callback invoked when the Surface has been destroyed and must no longer
     * be touched. WARNING: after this method returns, the Surface/Canvas must
     * never be touched again!
     */
	public void surfaceDestroyed(SurfaceHolder holder) {
		// Tell thread to shut down & wait for it to finish
        boolean retry = true;
        
        while (retry) {
            try {
                thread.join();
                retry = false;
            } 
            catch (InterruptedException e) {}
        }
	}
}

