package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLoaderActivity extends Activity {

	private static final int GET_TEXT_REQUEST_CODE = 1;

	private static final String URL = "http://www.google.com";
	private static final String TAG = "Lab-Intents";

	// For use with app chooser
	private static final String CHOOSER_TEXT = "Load " + URL + " with:";

	// TextView that displays user-entered text from ExplicitlyLoadedActivity runs
	private TextView mUserTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_activity);

		// Get reference to the textView
		mUserTextView = (TextView) findViewById(R.id.textView1);

		// Declare and setup Explicit Activation button
		Button explicitActivationButton = (Button) findViewById(R.id.explicit_activation_button);
		explicitActivationButton.setOnClickListener(new OnClickListener() {

			// Call startExplicitActivation() when pressed
			@Override
			public void onClick(View v) {

				startExplicitActivation();

			}
		});

		// Declare and setup Implicit Activation button
		Button implicitActivationButton = (Button) findViewById(R.id.implicit_activation_button);
		implicitActivationButton.setOnClickListener(new OnClickListener() {

			// Call startImplicitActivation() when pressed
			@Override
			public void onClick(View v) {

				startImplicitActivation();

			}
		});

	}


	// Start the ExplicitlyLoadedActivity

	private void startExplicitActivation() {

		Log.i(TAG,"Entered startExplicitActivation()");
		Intent explicitActivityIntent = new Intent(this, ExplicitlyLoadedActivity.class);
		startActivityForResult(explicitActivityIntent, GET_TEXT_REQUEST_CODE);
	}

	// Start a Browser Activity to view a web page or its URL

	private void startImplicitActivation() {

		Log.i(TAG, "Entered startImplicitActivation()");

		//create intent to view the URL
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));

		//create intent chooser
		Intent chooserIntent = Intent.createChooser(browserIntent, CHOOSER_TEXT);
		Log.i(TAG,"Chooser Intent Action:" + chooserIntent.getAction());

		//start the browser activity only in case that it can be resolved
		if (browserIntent.resolveActivity(getPackageManager()) != null) {
			startActivity(chooserIntent);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Log.i(TAG, "Entered onActivityResult()");

		if (GET_TEXT_REQUEST_CODE == requestCode &&
				RESULT_OK == resultCode) {
			String userText = data.getExtras().getString(ExplicitlyLoadedActivity.KEY_ACTIVITY_USER_TEXT);
			mUserTextView.setText(null != userText ? userText : getText(R.string.no_text_entered));
		}

	}
}
