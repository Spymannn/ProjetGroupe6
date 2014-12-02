package be.condorcet.projetgroupe6;

import java.sql.Connection;
import java.util.ArrayList;




import android.view.View;
import android.view.View.OnClickListener;
import be.condorcet.projetgroupe.modele.DBConnection;
import be.condorcet.projetgroupe.modele.PreferenceDB;
import be.condorcet.projetgroupe.modele.SportDB;
import be.condorcet.projetgroupe.modele.UtilisateurDB;
import be.condorcet.projetgroupe6.MainActivity.MyAccessDBConnect;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class InscriptionActivity extends ActionBarActivity {
	
	private Spinner listeSport1;
	private Spinner listeSport2;
	private Spinner listeSport3;
	
	private EditText name,fname,pseudo,passe,confpass,mail,dd,mm,yyyy;
	private RadioGroup gender;
	
	
	private Connection con = null;
	
	private Button validate = null;
	//test 
	private String[] test = new String[]{"test1","test2","test3"};
	//fin
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		/**
		 * Test pour remplir les listes déroulantes
		 */
		listeSport1 =(Spinner)findViewById(R.id.spinnerSport1);
		listeSport2 =(Spinner)findViewById(R.id.spinnerSport2);
		listeSport3 =(Spinner)findViewById(R.id.spinnerSport3);
		
		MyAccessDBAfficheListe adb = new MyAccessDBAfficheListe(InscriptionActivity.this);
		adb.execute();
		
		name = (EditText)findViewById(R.id.nameE);
		fname = (EditText)findViewById(R.id.firstnameE);
		pseudo = (EditText)findViewById(R.id.pseudoE);
		passe = (EditText)findViewById(R.id.passE);
		confpass = (EditText)findViewById(R.id.confPassE);
		mail = (EditText)findViewById(R.id.adrEmail);
		dd = (EditText)findViewById(R.id.ddE);
		mm = (EditText)findViewById(R.id.mmE);
		yyyy = (EditText)findViewById(R.id.yyyyE);
		gender = (RadioGroup)findViewById(R.id.radioGender);
		
		validate = (Button)findViewById(R.id.inscriptionOK);
		
		validate.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!(listeSport1.getSelectedItem().toString().equals(listeSport2.getSelectedItem().toString())) 
						&& !(listeSport2.getSelectedItem().toString().equals(listeSport3.getSelectedItem().toString())) 
						&& !(listeSport1.getSelectedItem().toString().equals(listeSport3.getSelectedItem().toString()))){
					
					if(passe.getText().toString().equals(confpass.getText().toString())){
						MyAccessDBInscription adbc = new MyAccessDBInscription(InscriptionActivity.this);
						adbc.execute();
					}
					else{
						Toast.makeText(InscriptionActivity.this,R.string.passNotEqual,Toast.LENGTH_SHORT).show();
					}	
				}
				else{
					Toast.makeText(InscriptionActivity.this,R.string.chooseDifferent,Toast.LENGTH_SHORT).show();
				}
			}
		});
	
		
		/**
		 * Fin du test ici
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscription, menu);
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
		DestroyTask dt= new DestroyTask(InscriptionActivity.this);
		dt.execute();
		
	}
	private class DestroyTask extends AsyncTask<String,Integer,Boolean>{
		
		public DestroyTask(InscriptionActivity pActivity) {
			
			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(InscriptionActivity  pActivity) {
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
		          //Mettre les trucs à traduire
		          Log.d("connexion","deconnexion inscri OK");
		          }
		          catch (Exception e) { 
		        	  Log.d("connexion","deconnexion insci bug"+e);
		          }
			 //Mettre les trucs à traduire
			return true;
		}
		protected void onPostExecute(Boolean result){
			 super.onPostExecute(result);
			 
		}
	
	}
	
	class MyAccessDBAfficheListe extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private ProgressDialog pgd=null;
	    private ArrayList<SportDB> listeSports = new ArrayList<SportDB>();
	    private int posChoix = 0;
	    
							
				public MyAccessDBAfficheListe(InscriptionActivity pActivity) {
				
					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(InscriptionActivity  pActivity) {
					// TODO Auto-generated method stub
				
					
				}
				protected void onPreExecute(){
					 super.onPreExecute();
					 //Log.d("verifdb", "connection ok0");
			         pgd=new ProgressDialog(InscriptionActivity.this);
			         
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
					   UtilisateurDB.setConnection(con);
					   SportDB.setConnection(con);
					   //Log.d("verifdb", "backIn3");
				   }
				   else{
					   UtilisateurDB.setConnection(con);
					   SportDB.setConnection(con);
				   }
				   
				    /**
				     * Cette connexion devra être lancée ici
				     * dans toutes les classesDB, on mets tout ici
				     */
				   //Log.d("pass","test 1 : "+password+ "pseudo : "+ps);
			        try{
			        	listeSports = SportDB.afficheTousSport();
			           		           
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
						  String[] tabSports = new String[listeSports.size()];
							
							for(int i = 0;i<listeSports.size();i++){
								tabSports[i] = listeSports.get(i).getNomSport();
							}
							
							ArrayAdapter<String> adapter = new ArrayAdapter<String>(InscriptionActivity.this,android.R.layout.simple_spinner_item,tabSports);
							adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
							
							listeSport1.setAdapter(adapter);
							listeSport2.setAdapter(adapter);
							listeSport3.setAdapter(adapter);
							/*
							String[] tabSports2 = new String[tabSports.length - 1];
							posChoix = listeSport1.getSelectedItemPosition();
							for(int i = 0;i<tabSports.length - 2;i++){
								if(i!=posChoix)
									tabSports2[i] = tabSports[i];
							}
							
							ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(InscriptionActivity.this,android.R.layout.simple_spinner_item,tabSports);
							adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
							listeSport2.setAdapter(adapter2);
							
							String[] tabSports3 = new String[tabSports2.length - 1];
							posChoix = listeSport2.getSelectedItemPosition();
							for(int i = 0;i<tabSports2.length - 2;i++){
								if(i!=posChoix)
									tabSports3[i] = tabSports2[i];
							}
							
							ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(InscriptionActivity.this,android.R.layout.simple_spinner_item,tabSports);
							adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
							listeSport3.setAdapter(adapter3);
							*/
					  
					  }
					  else{
				        	Toast.makeText(InscriptionActivity.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}
	
	class MyAccessDBInscription extends AsyncTask<String,Integer,Boolean> {
	    private String resultat;
	    private String gend = null;
	    private ProgressDialog pgd=null;
	    private ArrayList<SportDB> listeSports = new ArrayList<SportDB>();
	    private int posChoix = 0;
	    
							
				public MyAccessDBInscription(InscriptionActivity pActivity) {
				
					link(pActivity);
					// TODO Auto-generated constructor stub
				}

				private void link(InscriptionActivity  pActivity) {
					// TODO Auto-generated method stub
				
					
				}
				protected void onPreExecute(){
					 super.onPreExecute();
					 //Log.d("verifdb", "connection ok0");
			         pgd=new ProgressDialog(InscriptionActivity.this);
			         
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
					   UtilisateurDB.setConnection(con);
					   SportDB.setConnection(con);
					   //PreferenceDB.setConnection(con);
					   //Log.d("verifdb", "backIn3");
				   }
				   else{
					   UtilisateurDB.setConnection(con);
					   SportDB.setConnection(con);
				   }
				   
				    /**
				     * Cette connexion devra être lancée ici
				     * dans toutes les classesDB, on mets tout ici
				     */
				   //Log.d("pass","test 1 : "+password+ "pseudo : "+ps);
				   if(gender.getCheckedRadioButtonId() == R.id.radioMale){
	        			
	        			gend = getString(R.string.radioM);
	        			
	        		}
	        		else{
	        			gend = getString(R.string.radioF);
	        		}
			        try{
			        	UtilisateurDB user = new UtilisateurDB(name.getText().toString(),
			        				fname.getText().toString(),passe.getText().toString(),
			        				pseudo.getText().toString(),mail.getText().toString(),
			        				dd.getText().toString()+"/"+mm.getText().toString()+"/"+yyyy.getText().toString(),
			        				gend,listeSport1.getSelectedItem().toString(),listeSport2.getSelectedItem().toString(),
			        				listeSport3.getSelectedItem().toString());
		        		user.create();
			           		           
			        }
			        catch(Exception e){		
			        	//Traduction ici
			        	//Log.d("pass","test 3 : "+password+" erreur"+e.getMessage());
			         //resultat="erreur" +e.getMessage(); 
			        	//Traduction ICI
			        	resultat = "Error during creation! ";
			        	//Toast.makeText(InscriptionActivity.this,listeSport3.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
			         
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
						  Toast.makeText(InscriptionActivity.this,R.string.userCreate,Toast.LENGTH_SHORT).show();
						  Intent i2 = new Intent(InscriptionActivity.this,MainActivity.class);
						  startActivity(i2);
					  
					  }
					  else{
				        	Toast.makeText(InscriptionActivity.this, resultat, Toast.LENGTH_SHORT).show();

					  }		
				}
			}
}
