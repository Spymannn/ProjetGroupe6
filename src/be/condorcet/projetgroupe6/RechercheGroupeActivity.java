package be.condorcet.projetgroupe6;

import java.sql.Connection;
import java.util.ArrayList;

import be.condorcet.projetgroupe.modele.DBConnection;
import be.condorcet.projetgroupe.modele.GroupeDB;
import be.condorcet.projetgroupe.modele.SportDB;
import be.condorcet.projetgroupe.modele.UtilisateurDB;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class RechercheGroupeActivity extends ActionBarActivity {
	
	private EditText texteRech = null;
	private ImageButton rech = null;
	private ListView listeGroupe = null;
	private ArrayList<String> listeNomGroupe = null;
	
	private Connection con = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recherche_groupe);
		
		texteRech = (EditText)findViewById(R.id.buttonRech);
		rech = (ImageButton)findViewById(R.id.launchRech);
		listeGroupe = (ListView)findViewById(R.id.listViewGroupe);
		
		listeNomGroupe = new ArrayList<String>();
		
		MyAccessDBAfficheGroupe aff = new MyAccessDBAfficheGroupe(RechercheGroupeActivity.this);
		aff.execute();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recherche_groupe, menu);
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


	class MyAccessDBAfficheGroupe extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private ProgressDialog pgd=null;
	    private ArrayList<GroupeDB> listeGroupesDB = new ArrayList<GroupeDB>();
	    private int posChoix = 0;
	    
							
				public MyAccessDBAfficheGroupe(RechercheGroupeActivity pActivity) {
				
					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(RechercheGroupeActivity  pActivity) {
					// TODO Auto-generated method stub
				
					
				}
				protected void onPreExecute(){
					 super.onPreExecute();
					 //Log.d("verifdb", "connection ok0");
			         pgd=new ProgressDialog(RechercheGroupeActivity.this);
			         
			         //Faire la traduction ICI !
					 pgd.setMessage("chargement en cours");
					 pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		     		 pgd.show();
												
				}
								
				@Override
				protected Boolean doInBackground(String... arg0) {
					//String..arg0 c'est un tableau d'argument
					//Log.d("verifdb", "backIn");
										
				   if(con==null){//premier invocation
					   //Log.d("verifdb", "backIn1");
					   con = new DBConnection().getConnection(); 
				    	if(con==null) {
				    		//Log.d("verifdb", "backIn2");
				    		//à traduire ici
				    		resultat="echec de la connexion";
				    		return false;//avec le return on sort, si pas on poursuit
					    }
				       //Log.d("verifdb", "connection ok1");
				    	GroupeDB.setConnection(con);
				    	UtilisateurDB.setConnection(con);
					   SportDB.setConnection(con);
					   //Log.d("verifdb", "backIn3");
				   }
				   else{
					   GroupeDB.setConnection(con);
					   UtilisateurDB.setConnection(con);
					   SportDB.setConnection(con);
				   }
				   
				    /**
				     * Cette connexion devra être lancée ici
				     * dans toutes les classesDB, on mets tout ici
				     */
				   //Log.d("pass","test 1 : "+password+ "pseudo : "+ps);
			        try{
			        	listeGroupesDB = GroupeDB.afficheTousGroupe();
			           		           
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	//Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         //resultat="erreur" +e.getMessage(); 
			        	//Traduction ICI
			        	resultat = "Sports not found!";
			         
			         return false;
			         
			         }
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
						  for(int i = 0;i<listeGroupesDB.size();i++){
							  listeNomGroupe.add(listeGroupesDB.get(i).getNomGroupe());
						  }
						  ArrayAdapter<String> adapter = new ArrayAdapter<String>(RechercheGroupeActivity.this,android.R.layout.simple_selectable_list_item,listeNomGroupe);
					      listeGroupe.setAdapter(adapter);
					  }
					  else{
				        	Toast.makeText(RechercheGroupeActivity.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}

}
