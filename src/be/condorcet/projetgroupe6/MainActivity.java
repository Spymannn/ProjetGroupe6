package be.condorcet.projetgroupe6;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	TextView signup = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		signup = (TextView)findViewById(R.id.signup);
		signup.setOnClickListener(
					new OnClickListener(){
						public void onClick(View v){
						
						//Intent i1 = new Intent(Activite1.this,Activite4.class);
						Intent i2 = new Intent(MainActivity.this,InscriptionActivity.class);
						//i1.putExtra("donneur", don);
						
						startActivity(i2);
						//test changement Corentin
						
					}
				}
				
				
				);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
