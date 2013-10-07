//FILE : PlayGameActivity.java
//PROG : Dave Jones
//PURP : Simulates start of game.  Allows user to update game level
//Returns username and level in Bundle
package wiggins_program4;

import edu.jones.demogamestartarrayadaptor.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.KeyEvent;
import android.view.View;
//import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayGameActivity extends Activity
{
	//Class-wide variables
	private String userName;
	private int gameLevel;
	private EditText mGameLevelET;
	private TextView mUserNameTV;
	
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		//Get user name and game level from Intent passed in
		//First, get the intent that started this activity
		Intent intent = getIntent();
		userName = intent.getStringExtra("bdl_username");
		gameLevel = intent.getIntExtra("bdl_gamelevel", 0);

		//Set up username and game level text/edit boxes
		mUserNameTV = (TextView) findViewById(R.id.users_name);
		mUserNameTV.setText(userName);
		mGameLevelET = (EditText) findViewById(R.id.game_level_entry);
		mGameLevelET.setText(Integer.toString(gameLevel));
		
		//Set up Done button listener
		setDoneButtonListener();
		
		//Create toast if onCreate finishes
		//Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
	}//END onCreate

	//Set up the Done button to initialize intent and finish
	private void setDoneButtonListener()
	{
		Button doneButton = (Button) findViewById(R.id.end_game);
		doneButton.setOnClickListener
		(
			new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					//Retrieve the potentially new game level
					gameLevel = Integer.parseInt(mGameLevelET.getText().toString());
					//Set up Intent to return potentially new values
					Intent i = getIntent();
					i.putExtra("bdl_returnUserName", userName);
					i.putExtra("bdl_returnGameLevel", gameLevel);
					setResult(RESULT_OK, i);
					finish();
				}//END onClick
			}//END OnClickListener
		);//END setOnClickListener
	}//END setDoneButtonListener

}//END PlayGameActivity
