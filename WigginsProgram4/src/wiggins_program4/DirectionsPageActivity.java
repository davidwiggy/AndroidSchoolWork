//FILE : DirectionsPageActivity.java
//PROG : Timothy Wiggins
//PURP : Shows "page of directions" in a ScrollView with a return button at the bottom
package wiggins_program4;

import edu.jones.demogamestartarrayadaptor.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DirectionsPageActivity extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directions);
        
        setDoneButtonListener();
    }//END onCreate

	//Set up the Done button to initialize intent and finish
		private void setDoneButtonListener()
		{
			Button doneButton = (Button) findViewById(R.id.doneBTN);
			doneButton.setOnClickListener
			(
				new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						finish();
					}
				}
			);//END setOnClickListener
		}//END setDoneButtonListener
		
}//END DirectionsPageActivity
