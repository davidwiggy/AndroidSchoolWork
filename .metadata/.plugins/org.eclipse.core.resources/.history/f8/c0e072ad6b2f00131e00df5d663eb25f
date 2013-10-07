//FILE : GameControlMainActivity.java
//PROG : Timothy Wiggins
//PURP : Allows user to read page of directions, also test to make sure user enters
//PURP : name before moving to another activity.
//and to start game on different screen
//Based loosely on Recipe text, pp43-44 & pp46 -49

package wiggins_program4;

import edu.jones.demogamestartarrayadaptor.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.KeyEvent;
import android.view.View;
//import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

public class GameControlMainActivity extends ListActivity
{
    //Class-wide variables for data passed/returned
	private String userName = "";
	//Use an int for gameLevel,naturally...but, this requires
	//use of various methods to convert to String and back!
	private int gameLevel = 1;
	private EditText nameEntryET;
	private TextView gameLevelAnnouncerTV;
	private TextView gameLevelTV;
	Button doneButton;
	//This TV prompts user to enter name in the EditText
	//Then, it is made invisible
	private TextView namePromptTV;
	//These two start out invisible and then show the name
	private TextView nameSetTV;
	private TextView nameEntTV;
	
	//Array of choices for user
	static final String[] CHOICES = new String[]
	{
		"Read directions",
		"Highest Scores",
		"Play Game",
		"Quit"
	};
	 
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Set up View ids
        nameEntryET = (EditText)findViewById(R.id.enter_nameET);
        gameLevelAnnouncerTV = (TextView) findViewById(R.id.game_level_announcer_TV);
        gameLevelTV = (TextView) findViewById(R.id.game_level_TV);
        //Set the game level in the TextView
        gameLevelTV.setText(Integer.toString(gameLevel));
        namePromptTV = (TextView)findViewById(R.id.name_prompt_tv);
		nameSetTV = (TextView)findViewById(R.id.name_set_tv);
		nameEntTV = (TextView)findViewById(R.id.name_entered_tv);
        
        //Set Done button listener to get user's name
        doneButton = (Button) findViewById(R.id.doneBtn);
        setDoneButtonListener();
        
        //Set up ArrayAdaptor for the options
        setListAdapter(new ArrayAdapter<String>
        	(this, android.R.layout.simple_list_item_1, CHOICES));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setTextFilterEnabled(true);
        
        //Set up the listener for user clicks on the list
        setListClickListener();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }//END onCreate
    
    private void setDoneButtonListener()
    {
    	doneButton.setOnClickListener
    	(
    		new View.OnClickListener()
    		{
				@Override
				public void onClick(View v)
				{
					//This is Update 1 testing to see if the user enters a name
					//If the user doesn't a brief message appears
					if(nameEntryET.getText().toString().length() == 0)
			    		Toast.makeText(GameControlMainActivity.this, "You must enter your name", Toast.LENGTH_SHORT).show();
					else
						setUserNameAndHideButton();	
				}					
			}
    	);//END setOnClickListener
    }//END setDoneButtonListener
    
    //Sets up username in its TextView, and game level Views,
    //then hides the other Views & button
    private void setUserNameAndHideButton()
    {
    	userName = nameEntryET.getText().toString();
		doneButton.setVisibility(View.GONE);
		//After getting the input, hide the EditText
		//VISIBLE(0), INVISIBLE(4) or GONE(8)
		nameEntryET.setVisibility(View.INVISIBLE);
		namePromptTV.setVisibility(View.GONE);
		nameEntTV.setText(userName);
		nameSetTV.setVisibility(View.VISIBLE);
		nameEntTV.setVisibility(View.VISIBLE);
		gameLevelAnnouncerTV.setVisibility(View.VISIBLE);
		gameLevelTV.setVisibility(View.VISIBLE);
    }//END setUserNameAndHideButton
    
    //Set up the listener for the ListView to interpret user clicks
    private void setListClickListener()
    {
		//Set up the click listener for the options
    	getListView().setOnItemClickListener
    	(
    		new OnItemClickListener()
    		{
    			//@Override
    			public void onItemClick(AdapterView<?> arg0, View arg1,
    								int arg2, long arg3)
    			{
    				switch(arg2)
    				{
    					case 0:	launchDirectionsPage();
    							break;
    					case 1: launchHighScores();
    							break;
    					case 2: startGame();
    							break;
    					case 3: finish();
    							break;
    					default: break;
    				}
    			}
    		}//END OnItemClickListener
    	);//END setOnItemClickListener
	}//END setListClickListener
    
    //Launches the high score activity 
    protected void launchHighScores()
    {
    	Intent launchScores = new Intent(this, HighScoresActivity.class);
    	launchScores.putExtra("bdl_username", userName);
    	
    	startActivity(launchScores);  	
    }
    
    //Launch a simple activity to show a scroll view of directions
    protected void launchDirectionsPage()
    {
    	//Set up Intent
    	Intent launchDirections = new Intent(this, DirectionsPageActivity.class);
    	startActivity(launchDirections);
    }//END launchDirectionsPage
    
    //Launch the activity that allows user to input new game value
    //Upon return the onActivityResult method is called
	protected void startGame()
	{
		//Set up Intent to launch other activity:  PlayGame
		Intent launchGame = new Intent(this, PlayGameActivity.class);
		//Info added to the Intent's Bundle to pass to PlayGameActivity
		launchGame.putExtra("bdl_username", userName);
		launchGame.putExtra("bdl_gamelevel", gameLevel);
		startActivityForResult(launchGame, 0);
	}//END startGame

	//This method will be called when the startGame activity terminates
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == 0 && resultCode == RESULT_OK)
		{
			//Reset the views to possibly updated info returned in the Intent
			//First, access the Bundle's values
			userName = data.getExtras().getString("bdl_returnUserName");
			gameLevel = data.getExtras().getInt("bdl_returnGameLevel");
			//Update the user name & game level with values from other activity
			nameEntTV.setText(userName);
			gameLevelTV.setText(Integer.toString(gameLevel));
		}
	}//END onActivityResult
	
	@Override
	protected void onSaveInstanceState (Bundle outState)
	{
		super.onSaveInstanceState(outState);
		//Add the username and game level to the Bundle
		outState.putString("bdl_savedusername", userName);
		outState.putInt("bdl_savedgamelevel", gameLevel);
	}//END onSaveInstanceState
	
	@Override
	public void onRestoreInstanceState (Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		//Restore the username and game level from the Bundle
		userName = savedInstanceState.getString("bdl_savedusername");
		gameLevel = savedInstanceState.getInt("bdl_savedgamelevel");
	}//END onRestoreInstanceState
	
}//END GameControlMainActivity