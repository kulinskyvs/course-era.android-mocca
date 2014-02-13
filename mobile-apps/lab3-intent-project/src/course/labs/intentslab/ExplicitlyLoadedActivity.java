package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitlyLoadedActivity extends Activity {

	public static final String KEY_ACTIVITY_USER_TEXT = ExplicitlyLoadedActivity.class.getName()+".USERTEXT";

	private static final String TAG = "Lab-Intents";

	private EditText mEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.explicitly_loaded_activity);

		// Get a reference to the EditText field
		mEditText = (EditText) findViewById(R.id.editText);

		// Declare and setup "Enter" button
		Button enterButton = (Button) findViewById(R.id.enter_button);
		enterButton.setOnClickListener(new OnClickListener() {

			// Call enterClicked() when pressed

			@Override
			public void onClick(View v) {

				enterClicked();

			}
		});

	}

	// Sets result to send back to calling Activity and finishes

	private void enterClicked() {

		Log.i(TAG,"Entered enterClicked()");
		String userText = mEditText.getText().toString();


		//returning the result intent from the activity
		Intent resultIntent = new Intent();
		resultIntent.putExtra(KEY_ACTIVITY_USER_TEXT, userText);

		//store the result code and the result data to be passed to previous activity
		setResult(RESULT_OK, resultIntent);

		//finish the activity at this point
		finish();
	}
}
