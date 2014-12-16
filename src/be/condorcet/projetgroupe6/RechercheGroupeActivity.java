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
	private  ArrayAdapter<String> adapter= null;
	
	private Connection con = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_recherche_groupe);
		
		Intent i = getIntent();
		idUs = i.getIntExtra("IDUSER",idUs);
		
		texteRech = (EditText)findViewById(R.id.texteRech);
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
						Intent i = new Intent(RechercheGroupeActivity.this,GpeInscription.class);
						i.putExtra("NOMGPE",gpeChoisi);
						i.putExtra("IDUSER",idUs);
						startActivity(i);
						//Toast.makeText(RechercheGroupeActivity.this,gpeChoisi,Toast.LENGTH_SHORT).show();
						//MyAccessDBParticipationGpe part = new MyAccessDBParticipationGpe(RechercheGroupeActivity.this);
						//part.execute();
						
					}
				}
				
		);
		
		
	}
	
	public void rechercheGroupe(View view){
		MyAccessDBAfficheGroupeRech gpeRech = new MyAccessDBAfficheGroupeRech(RechercheGroupeActivity.this);
		gpeRech.execute();
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
					 //Log.d("connexion","la connexion est null");
				 }
		          con.close();
		          con=null;
		          //Log.d("connexion","deconnexion inscri OK");
		          }
		          catch (Exception e) { 
		        	  //Log.d("connexion","deconnexion insci bug"+e);
		          }
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
					 pgd.setMessage(getString(R.string.charging));
					 pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		     		 pgd.show();
												
				}
								
				@Override
				protected Boolean doInBackground(String... arg0) {
					//String..arg0 c'est un tableau d'argument
				   if(con==null){//premier invocation
					   con = new DBConnection().getConnection(); 
				    	if(con==null) {
				    		//Log.d("verifdb", "backIn2");
				    		//à traduire ici
				    		resultat=getString(R.string.echecCo);
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
			        	tabPart = null;
			        	listeGroupesDB2 = GroupeDB.afficheTousGroupe();
			        	try{
				        	ParticipantDB p = new ParticipantDB(idUs);
				        	tabPart = p.listeGroupe();
			        	}
			        	catch(Exception ex){}
			        	
			        	if(tabPart!=null){
			        		for(int i = 0;i<listeGroupesDB2.size();i++){
				        		boolean flag = true;
				        		for(int j = 0;j<tabPart.size();j++){
				        			/**
				        			 * La liste de groupe n'affiche pas les gpes où l'utilisateur est déjà inscrit
				        			 * et/ou où l'utilisateur est administrateur
				        			 */
				        			if(listeGroupesDB2.get(i).getIdGroupe()==tabPart.get(j).getIdGroupe() || listeGroupesDB2.get(i).getAdmin()==idUs){
				        				flag = false;
				        			}
				        		}
				        		if(flag && listeGroupesDB2.get(i).getNbrUser() != listeGroupesDB2.get(i).getMaxUser()){
				        			listeGroupesDB.add(listeGroupesDB2.get(i));
				        		}
				        	}
			        	}
			        	else{
			        		for(int i = 0;i<listeGroupesDB2.size();i++){
			        			if(listeGroupesDB2.get(i).getNbrUser() != listeGroupesDB2.get(i).getMaxUser()){
			        				listeGroupesDB.add(listeGroupesDB2.get(i));
			        			}
			        		}
			        	}	           
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	//Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         //resultat="erreur" +e.getMessage(); 
			        	//Traduction ICI
			        	resultat = getString(R.string.groupsNotFound);
			         
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
							  listeNomGroupe.add(listeGroupesDB.get(i).getNomGroupe()+ "        "+ listeGroupesDB.get(i).getNbrUser()+ "/"+listeGroupesDB.get(i).getMaxUser());
						  }
						  adapter = new ArrayAdapter<String>(RechercheGroupeActivity.this,android.R.layout.simple_selectable_list_item,listeNomGroupe);
						  adapter.setNotifyOnChange(true);
						  listeGroupe.setAdapter(adapter);
					  }
					  else{
				        	Toast.makeText(RechercheGroupeActivity.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}
	
	
	class MyAccessDBAfficheGroupeRech extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private ProgressDialog pgd=null;
	    private ArrayList<GroupeDB> listeGroupesDB = new ArrayList<GroupeDB>();
	    private ArrayList<GroupeDB> listeGroupesDB2 = new ArrayList<GroupeDB>();
	    private ArrayList<ParticipantDB> tabPart = new ArrayList<ParticipantDB>();
	    private int posChoix = 0;
	    
							
				public MyAccessDBAfficheGroupeRech(RechercheGroupeActivity pActivity) {
				
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
					 pgd.setMessage(getString(R.string.charging));
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
				    		resultat=getString(R.string.echecCo);
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
			        	tabPart = null;
			        	listeGroupesDB2 = GroupeDB.afficheTousGroupeRech(texteRech.getText().toString().trim());
			        	try{
				        	ParticipantDB p = new ParticipantDB(idUs);
				        	tabPart = p.listeGroupe();
			        	}
			        	catch(Exception ex){}
			        	
			        	if(tabPart!=null){
			        		for(int i = 0;i<listeGroupesDB2.size();i++){
				        		boolean flag = true;
				        		for(int j = 0;j<tabPart.size();j++){
				        			/**
				        			 * La liste de groupe n'affiche pas les gpes où l'utilisateur est déjà inscrit
				        			 * et/ou où l'utilisateur est administrateur
				        			 */
				        			if(listeGroupesDB2.get(i).getIdGroupe()==tabPart.get(j).getIdGroupe() || listeGroupesDB2.get(i).getAdmin()==idUs){
				        				flag = false;
				        			}
				        		}
				        		if(flag && listeGroupesDB2.get(i).getNbrUser() != listeGroupesDB2.get(i).getMaxUser()){
				        			listeGroupesDB.add(listeGroupesDB2.get(i));
				        		}
				        	}
			        	}
			        	else{
			        		for(int i = 0;i<listeGroupesDB2.size();i++){
			        			if(listeGroupesDB2.get(i).getNbrUser() != listeGroupesDB2.get(i).getMaxUser()){
			        				listeGroupesDB.add(listeGroupesDB2.get(i));
			        			}
			        		}
			        	}	           
			        }
			        catch(Exception e){		
			        	resultat = getString(R.string.noGroups);
			         
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
						  listeNomGroupe.clear();
						  for(int i = 0;i<listeGroupesDB.size();i++){
							  listeNomGroupe.add(listeGroupesDB.get(i).getNomGroupe()+ "        "+ listeGroupesDB.get(i).getNbrUser()+ "/"+listeGroupesDB.get(i).getMaxUser());
						  }
						  adapter = new ArrayAdapter<String>(RechercheGroupeActivity.this,android.R.layout.simple_selectable_list_item,listeNomGroupe);
						  adapter.setNotifyOnChange(true);
						  listeGroupe.setAdapter(adapter);
					  }
					  else{
						  listeNomGroupe.clear();
						  listeNomGroupe.add(resultat);
						  adapter = new ArrayAdapter<String>(RechercheGroupeActivity.this,android.R.layout.simple_selectable_list_item,listeNomGroupe);
						  adapter.setNotifyOnChange(true);
						  listeGroupe.setAdapter(adapter);
						  //Toast.makeText(RechercheGroupeActivity.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}
	
	class MyAccessDBEnvoiGpe extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private ProgressDialog pgd=null;
	    private ArrayList<GroupeDB> listeGroupesDB = new ArrayList<GroupeDB>();
	    private ArrayList<GroupeDB> listeGroupesDB2 = new ArrayList<GroupeDB>();
	    private ArrayList<ParticipantDB> tabPart = new ArrayList<ParticipantDB>();
	    private int posChoix = 0;
	    
							
				public MyAccessDBEnvoiGpe (RechercheGroupeActivity pActivity) {
				
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
					 pgd.setMessage(getString(R.string.charging));
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
				    		resultat=getString(R.string.echecCo);
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
			        	GroupeDB g = new GroupeDB(gpeChoisi);
			        	g.read();
			        	
			        	//traduction ici
			        	resultat = getString(R.string.inscriOui)+ gpeChoisi;
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	//Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         //resultat="erreur" +e.getMessage(); 
			        	//Traduction ICI
			        	resultat = getString(R.string.errorCreation);
			         
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
						  Toast.makeText(RechercheGroupeActivity.this, resultat, Toast.LENGTH_SHORT).show();
						  //test de refresh l'activité
						  finish();
						  startActivity(getIntent());
					  }
					  else{
				        	Toast.makeText(RechercheGroupeActivity.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}
}
