package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";

	// Lifecycle counters
	private int mCreate = 0;
	private int mRestart = 0;
	private int mStart = 0;
	private int mResume = 0;


	//Text view to update the counters
	private TextView mTvCreate;
	private TextView mTvStart;
	private TextView mTvResume;
	private TextView mTvRestart;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);

		//extract monitor text views
		mTvCreate = (TextView)findViewById(R.id.create);
		mTvStart = (TextView)findViewById(R.id.start);
		mTvResume = (TextView)findViewById(R.id.resume);
		mTvRestart = (TextView)findViewById(R.id.restart);


		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Launch Activity Two
				Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
				startActivity(intent);
			}
		});

		// Check for previously saved state
		if (savedInstanceState != null) {
			// Restore value of counters from saved state
			mCreate = savedInstanceState.getInt(CREATE_KEY);
			mStart = savedInstanceState.getInt(START_KEY);
			mRestart = savedInstanceState.getInt(RESTART_KEY);
			mResume = savedInstanceState.getInt(RESUME_KEY);
		}

		mCreate++;
		Log.i(TAG, "Entered the onCreate() method");
		displayCounts();
	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();

		mStart++;
		Log.i(TAG, "Entered the onStart() method");
		displayCounts();
	}

	@Override
	public void onResume() {
		super.onResume();

		mResume++;
		Log.i(TAG, "Entered the onResume() method");
		displayCounts();
	}

	@Override
	public void onPause() {
		super.onPause();

		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();

		Log.i(TAG, "Entered the onStop() method");
		displayCounts();
	}

	@Override
	public void onRestart() {
		super.onRestart();

		mRestart++;
		Log.i(TAG, "Entered the onRestart() method");
		displayCounts();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.i(TAG, "Entered the onDestroy() method");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save state information with a collection of key-value pairs
		savedInstanceState.putInt(CREATE_KEY, mCreate);
		savedInstanceState.putInt(START_KEY, mStart);
		savedInstanceState.putInt(RESTART_KEY, mRestart);
		savedInstanceState.putInt(RESUME_KEY, mResume);
	}

	// Updates the displayed counters
	public void displayCounts() {

		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);

	}
}
