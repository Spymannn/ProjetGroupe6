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
import android.widget.AdapterView;
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
	private int idUs = 0;
	private String gpeChoisi = null;
	
	private Connection con = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_recherche_groupe);
		
		Intent i = getIntent();
		idUs = i.getIntExtra("IDUSER",idUs);
		
		texteRech = (EditText)findViewById(R.id.buttonRech);
		rech = (ImageButton)findViewById(R.id.launchRech);
		listeGroupe = (ListView)findViewById(R.id.listViewGroupe);
		
		listeNomGroupe = new ArrayList<String>();
		
		MyAccessDBAfficheGroupe aff = new MyAccessDBAfficheGroupe(RechercheGroupeActivity.this);
		aff.execute();
		
		listeGroupe.setOnItemClickListener(
				new ListView.OnItemClickListener() {
				@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
				         //Toast.makeText(RechercheGroupeActivity.this,""+ listeGroupe.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
						gpeChoisi = listeGroupe.getItemAtPosition(position).toString().trim().substring(0,listeGroupe.getItemAtPosition(position).toString().trim().length()-6).trim();
			         //Toast.makeText(RechercheGroupeActivity.this,gpeChoisi,Toast.LENGTH_SHORT).show();
						MyAccessDBParticipationGpe part = new MyAccessDBParticipationGpe(RechercheGroupeActivity.this);
						part.execute();
						
					}
				}
				
		);
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
	
	public void onDestroy(){
		super.onDestroy();
		DestroyTask dt= new DestroyTask(RechercheGroupeActivity.this);
		dt.execute();
		
	}
	private class DestroyTask extends AsyncTask<String,Integer,Boolean>{
		
		public DestroyTask(RechercheGroupeActivity RechercheGroupeActivity) {
			
			link(RechercheGroupeActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(RechercheGroupeActivity  pActivity) {
			// TODO Auto-generated method stub
		
			
		}
		protected void onPreExecute(){
			 super.onPreExecute();
										
		}
		
		protected Boolean doInBackground(String... arg0){
			 try {
				 if(con == null){
					 Log.d("connexion","la connexion est null");
				 }
		          con.close();
		          con=null;
		          //Mettre les trucs � traduire
		          Log.d("connexion","deconnexion inscri OK");
		          }
		          catch (Exception e) { 
		        	  Log.d("connexion","deconnexion insci bug"+e);
		          }
			 //Mettre les trucs � traduire
			return true;
		}
		protected void onPostExecute(Boolean result){
			 super.onPostExecute(result);
			 
		}
	
	}

	class MyAccessDBAfficheGroupe extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private ProgressDialog pgd=null;
	    private ArrayList<GroupeDB> listeGroupesDB = new ArrayList<GroupeDB>();
	    private ArrayList<GroupeDB> listeGroupesDB2 = new ArrayList<GroupeDB>();
	    private ArrayList<ParticipantDB> tabPart = new ArrayList<ParticipantDB>();
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
				    		//� traduire ici
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
				     * Cette connexion devra �tre lanc�e ici
				     * dans toutes les classesDB, on mets tout ici
				     */
				   //Log.d("pass","test 1 : "+password+ "pseudo : "+ps);
			        try{
			        	tabPart = null;
			        	listeGroupesDB2 = GroupeDB.afficheTousGroupe();
			        	try{
			        	ParticipantDB p = new ParticipantDB(idUs);
			        	tabPart = p.listeGroupe();
			        	}
			        	catch(Exception ex){
			        	}
			        	if(tabPart!=null){
			        		for(int i = 0;i<listeGroupesDB2.size();i++){
				        		boolean flag = true;
				        		for(int j = 0;j<tabPart.size();j++){
				        			if(listeGroupesDB2.get(i).getIdGroupe()==tabPart.get(j).getIdGroupe()){
				        				flag = false;
				        			}
				        		}
				        		if(flag){
				        			listeGroupesDB.add(listeGroupesDB2.get(i));
				        		}
				        	}
			        	}
			        	else{
			        		for(int i = 0;i<listeGroupesDB2.size();i++){
			        			listeGroupesDB.add(listeGroupesDB2.get(i));
			        		}
			        	}	           
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	//Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         //resultat="erreur" +e.getMessage(); 
			        	//Traduction ICI
			        	resultat = "Groups not found!"+e;
			         
			         return false;
			         
			         }
			        return true;
				}
				/**
				 * Ici, c'est apr�s l'execution
				 * on fait disparaitre la progressbar avec dismiss();
				 * @param result
				 */
				protected void onPostExecute(Boolean result){
					 super.onPostExecute(result);
					  pgd.dismiss();
					  if(result){
						  for(int i = 0;i<listeGroupesDB.size();i++){
							  listeNomGroupe.add(listeGroupesDB.get(i).getNomGroupe()+ "        "+ listeGroupesDB.get(i).getNbrUser()+ "/"+listeGroupesDB.get(i).getMaxUser());
						  }
						  ArrayAdapter<String> adapter = new ArrayAdapter<String>(RechercheGroupeActivity.this,android.R.layout.simple_selectable_list_item,listeNomGroupe);
						  listeGroupe.setAdapter(adapter);
					  }
					  else{
				        	Toast.makeText(RechercheGroupeActivity.this, resultat, Toast.LENGTH_SHORT).show();

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
	    
							
				public MyAccessDBParticipationGpe (RechercheGroupeActivity pActivity) {
				
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
				    		//� traduire ici
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
				     * Cette connexion devra �tre lanc�e ici
				     * dans toutes les classesDB, on mets tout ici
				     */
				   //Log.d("pass","test 1 : "+password+ "pseudo : "+ps);
			        try{
			        	GroupeDB g = new GroupeDB(gpeChoisi);
			        	g.read();
			        	ParticipantDB p = new ParticipantDB(idUs,g.getIdGroupe());
			        	p.create();
			        	
			        	//traduction ici
			        	resultat = "Vous �tes inscrit dans le groupe "+ gpeChoisi;
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	//Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         //resultat="erreur" +e.getMessage(); 
			        	//Traduction ICI
			        	resultat = "erreur cr�ation participant!"+e;
			         
			         return false;
			         
			         }
			        return true;
				}
				/**
				 * Ici, c'est apr�s l'execution
				 * on fait disparaitre la progressbar avec dismiss();
				 * @param result
				 */
				protected void onPostExecute(Boolean result){
					 super.onPostExecute(result);
					  pgd.dismiss();
					  if(result){
						  Toast.makeText(RechercheGroupeActivity.this, resultat, Toast.LENGTH_SHORT).show();
						  //test de refresh l'activit�
						  finish();
						  startActivity(getIntent());
					  }
					  else{
				        	Toast.makeText(RechercheGroupeActivity.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}
}
