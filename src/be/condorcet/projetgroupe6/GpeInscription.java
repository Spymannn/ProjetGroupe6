package be.condorcet.projetgroupe6;

import java.sql.Connection;
import java.util.ArrayList;

import be.condorcet.projetgroupe.modele.DBConnection;
import be.condorcet.projetgroupe.modele.GroupeDB;
import be.condorcet.projetgroupe.modele.ParticipantDB;
import be.condorcet.projetgroupe.modele.SportDB;
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

public class GpeInscription extends ActionBarActivity {
	private TextView nomGpe = null,nomAdmin = null, textePass = null;
	private EditText passWord = null;
	private Button valid = null;
	private String nameAdmin = "",passGpe = "",nomG = "";
	private int idUser = 0,idGpe = 0;
	private boolean flag = true;
	
	private GroupeDB gpe = null;
	
	private Connection con = null;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpe_inscription);
		
		MyAccessDBAdminEtMdp ad = new MyAccessDBAdminEtMdp(GpeInscription.this);
		ad.execute();
		
		nomGpe = (TextView)findViewById(R.id.nomGpe);
		nomAdmin = (TextView)findViewById(R.id.nomAdmin);
		textePass = (TextView)findViewById(R.id.enterPass);
		passWord = (EditText)findViewById(R.id.passEnter);
		valid = (Button)findViewById(R.id.validationGpe);
		
		Intent i = getIntent();
		
		nomG = i.getStringExtra("NOMGPE");
		idUser = i.getIntExtra("IDUSER",idUser);
		
		nomGpe.setText(nomG);
		
		/*
		 if(!flag){
			  //Traduction à faire ici
			  	textePass.setText("No password for this group");
			  	passWord.setHint("no password here !");
	 			//textePass.setVisibility(View.INVISIBLE);
	 			//passWord.setVisibility(View.INVISIBLE);
	 		}
		*/
		//Toast.makeText(GpeInscription.this,"2"+passGpe, Toast.LENGTH_LONG).show();

		
	}
	public void validInscription(View view){
		MyAccessDBParticipationGpe adb = new MyAccessDBParticipationGpe(GpeInscription.this);

		if(passGpe == null){
			adb.execute();
		}
		else{
			if(passWord.getText().toString().trim().equals(passGpe)){
				
				adb.execute();
			}
			else{
				Toast.makeText(GpeInscription.this,"Wrong password", Toast.LENGTH_LONG).show();
			}
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gpe_inscription, menu);
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
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		 try {
	          con.close();
	          con=null;
	          //Mettre les trucs à traduire
	          Log.d("connexion","deconnexion OK ?");
	          }
	          catch (Exception e) { 
	        	  Log.d("connexion","decconnexion bug"+e);
	          }
		 //Mettre les trucs à traduire
		 Log.d("connexion","deconnexion entre dans le destroy OK");
		
	}
	
	class MyAccessDBAdminEtMdp extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private ProgressDialog pgd=null;
							
				public MyAccessDBAdminEtMdp(GpeInscription pActivity) {
				
					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(GpeInscription  pActivity) {
					// TODO Auto-generated method stub
				
					
				}
				protected void onPreExecute(){
					 super.onPreExecute();
					 //Log.d("verifdb", "connection ok0");
			         pgd=new ProgressDialog(GpeInscription.this);
			         
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
					   ParticipantDB.setConnection(con);
					   //Log.d("verifdb", "backIn3");
				   }
				   else{
					   GroupeDB.setConnection(con);
					   UtilisateurDB.setConnection(con);
					   SportDB.setConnection(con);
					   ParticipantDB.setConnection(con);
				   }
				   
				    /**
				     * Cette connexion devra être lancée ici
				     * dans toutes les classesDB, on mets tout ici
				     */
			        try{
			        	gpe = new GroupeDB(nomG);
			        	gpe.read();
			        	idGpe = gpe.getIdGroupe();
			        	passGpe = gpe.getMdpGroupe();
			        	
			        	UtilisateurDB use = UtilisateurDB.afficheUserParId(gpe.getAdmin());
			        	nameAdmin = use.getPseudoUser();
			        	
			        	
			        	/**/
			        	           
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	//Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         //resultat="erreur" +e.getMessage(); 
			        	//Traduction ICI
			        	resultat = "Group not found!";
			         
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
						  nomAdmin.setText(nameAdmin);
						 
					  }
					  else{
				        	Toast.makeText(GpeInscription.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}
	
	class MyAccessDBParticipationGpe extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private ProgressDialog pgd=null;
	    private ArrayList<GroupeDB> listeGroupesDB = new ArrayList<GroupeDB>();
	    private ArrayList<GroupeDB> listeGroupesDB2 = new ArrayList<GroupeDB>();
	    private ArrayList<ParticipantDB> tabPart = new ArrayList<ParticipantDB>();
	    private int posChoix = 0;
	    
							
				public MyAccessDBParticipationGpe (GpeInscription pActivity) {
				
					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(GpeInscription  pActivity) {
					// TODO Auto-generated method stub
				
					
				}
				protected void onPreExecute(){
					 super.onPreExecute();
					 //Log.d("verifdb", "connection ok0");
			         pgd=new ProgressDialog(GpeInscription.this);
			         
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
					   ParticipantDB.setConnection(con);
					   //Log.d("verifdb", "backIn3");
				   }
				   else{
					   GroupeDB.setConnection(con);
					   UtilisateurDB.setConnection(con);
					   SportDB.setConnection(con);
					   ParticipantDB.setConnection(con);
				   }
				   
				    /**
				     * Cette connexion devra être lancée ici
				     * dans toutes les classesDB, on mets tout ici
				     */
				   //Log.d("pass","test 1 : "+password+ "pseudo : "+ps);
			        try{
			        	
			        	ParticipantDB p = new ParticipantDB(idUser,idGpe);
			        	p.create();
			        	
			        	//traduction ici
			        	resultat = "Vous êtes inscrit dans le groupe ";
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	//Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         //resultat="erreur" +e.getMessage(); 
			        	//Traduction ICI
			        	resultat = "erreur création participant!"+e;
			         
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
						  Toast.makeText(GpeInscription.this, resultat, Toast.LENGTH_SHORT).show();
						  //test de refresh l'activité
						  valid.setVisibility(View.INVISIBLE);
						  
						  Intent i2 = new Intent(GpeInscription.this,RechercheGroupeActivity.class);
						  i2.putExtra("IDUSER",idUser);
						  startActivity(i2);
						  
					  }
					  else{
				        	Toast.makeText(GpeInscription.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}
}
