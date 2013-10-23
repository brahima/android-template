package fr.leroymerlin.androidapp;

import fr.leroymerlin.androidapp.AndroHelper;
import fr.leroymerlin.androidapp.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Activité principal de l'application.
 * 
 * 
 */
public class AndroMainActivity extends Activity {

	/**
	 * TextView.
	 */
	private TextView helloWorld;

	/**
	 * Création des composants requis à l'affichage de l'écran principal.
	 * 
	 * @param savedInstanceState
	 *            état de l'application.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set up notitle
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_main);
		
		this.helloWorld = (TextView) this.findViewById(R.id.helloWorld);
		this.helloWorld.setText(AndroHelper.getInstance().getHelloWorld());
	}
 

}
