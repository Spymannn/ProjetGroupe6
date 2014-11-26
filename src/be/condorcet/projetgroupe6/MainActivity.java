package be.condorcet.projetgroupe6;



import java.sql.Connection;

import be.condorcet.projetgroupe.modele.DBConnection;
import be.condorcet.projetgroupe.modele.UtilisateurDB;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	//Variables
	private TextView signup = null;
	private EditText pseudo,mdp;
	private Button login;
	private Connection con = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		signup = (TextView)findViewById(R.id.signup);
		pseudo = (EditText)findViewById(R.id.loginE);
		mdp = (EditText)findViewById(R.id.passwordE);
		login = (Button)findViewById(R.id.connectB);
		
		signup.setOnClickListener(
					new OnClickListener(){
						public void onClick(View v){
						//Intent i1 = new Intent(Activite1.this,Activite4.class);
						Intent i2 = new Intent(MainActivity.this,InscriptionActivity.class);
						//i1.putExtra("donneur", don);
						
						startActivity(i2);
						//test changement Corentin
						
					}
				});
		login.setOnClickListener(
				new OnClickListener(){
					public void onClick(View v){
						MyAccessDBConnect adb = new MyAccessDBConnect(MainActivity.this);
						adb.execute();
					
				}
			});
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
	
	/**
	 * Ici c'est ce qu'on fait quand on ferme l'application
	 * on ferme la connexion et on la mets à null
	 * on met le message dans le log, dans D c'est comme debut
	 * log.d ça permet de indiquer un petit message de type connexion, qui est 
	 * deconnexion ok
	 */
	@Override
	public void onDestroy(){
		super.onDestroy();
		 try {
	          con.close();
	          con=null;
	          //Mettre les trucs à traduire
	          //Log.d("connexion","deconnexion OK");
	          }
	          catch (Exception e) { 
	          }
		 //Mettre les trucs à traduire
		 //Log.d("connexion","deconnexion OK");
		
	}
	
	class MyAccessDBConnect extends AsyncTask<String,Integer,Boolean> {
	    private String resultat,password;
	    private ProgressDialog pgd=null;
	    
							
				public MyAccessDBConnect(MainActivity pActivity) {
				
					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(MainActivity pActivity) {
					// TODO Auto-generated method stub
				
					
				}
				/**
				 * avant, on mets une petite fenêtre surgissante qui tourne
				 * c'est le truc de chargement
				 */
				protected void onPreExecute(){
					 super.onPreExecute();
					 //Log.d("verifdb", "connection ok0");
			         pgd=new ProgressDialog(MainActivity.this);
			         //Faire la traduction ICI !
					 pgd.setMessage("chargement en cours");
					 pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		     		 pgd.show();
												
				}
				/**
				 * dans la méthode là, on a une variable con (connexion) qui est une variable
				 * globale, ici l'activité asynchrone se lance
				 * ici il va regarder si la connexion existe déjà, si c'est le 1er ou le 2éme lancement
				 * il va créer une nouvelle connexion si la connexion est null
				 * si pas il va récupérer l'id du client, il va créer un client qui a cet id
				 * et demander de le lire en base de données, et va mettre dans resultat
				 * le toString du client
				 * et si personne n'a ce résultat, on va mettre le message d'exception
				 * qui est dans la classe db, erreur, client introuvable
				 * on ne peut pas changer le contenu d'un texte dans le doInBackground !
				 * @param arg0
				 * @return
				 */
				
				@Override
				protected Boolean doInBackground(String... arg0) {
					//String..arg0 c'est un tableau d'argument
					Log.d("verifdb", "backIn");
										
				   if(con==null){//premier invocation
					   Log.d("verifdb", "backIn1");
					   con = new DBConnection().getConnection(); 
				    	if(con==null) {
				    		Log.d("verifdb", "backIn2");
				    		//à traduire ici
				    		resultat="echec de la connexion";
				    		return false;//avec le return on sort, si pas on poursuit
					    }
				       //Log.d("verifdb", "connection ok1");
					   UtilisateurDB.setConnection(con);
					   Log.d("verifdb", "backIn3");
				   }
				   String ps = pseudo.getText().toString();
				   String pass = mdp.getText().toString();	
				    /**
				     * Cette connexion devra être lancée ici
				     * dans toutes les classesDB, on mets tout ici
				     */
				   Log.d("pass","test 1 : "+password+ "pseudo : "+ps);
			        try{
			        	
			           UtilisateurDB user=new UtilisateurDB(ps);
			           user.read();
			           resultat=user.toString();
			           password = user.getMdp();
			           Log.d("pass","test 2 : "+password);
			           		           
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         resultat="erreur" +e.getMessage(); 
			         
			         return false;
			         
			         }
			        if(!pass.equals(password)){
			        	   return false;
			           }
			        else
			        	return true;
				}
				/**
				 * Ici, c'est après l'execution
				 * on fait disparaitre la progressbar avec dismiss();
				 * @param result
				 */
				protected void onPostExecute(Boolean result){
					 super.onPostExecute(result);
					  pgd.dismiss();
					  if(result){
						  Intent i2 = new Intent(MainActivity.this,InscriptionActivity.class);
						  startActivity(i2);
					  
					  }
					  else{
						  //test
					  }		
				}
			}
}
