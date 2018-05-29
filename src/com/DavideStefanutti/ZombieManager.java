package com.DavideStefanutti;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.DavideStefanutti.Zombie;
import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@ApplicationScoped
public class ZombieManager {
	
	
	List<Zombie> zombies = new ArrayList<>();
	
	String[] names = {"Darnell", "Rudy", "Stan", "Sullivan", "Winfield", "Fulbert"};
	String[] imgs = {"z0.png", "z1.jpg", "z2.jpg", "z3.jpg"};
	private static final String img_folder = "imgs/";
	
	boolean win = false;

	public ZombieManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void newZombie() {
		Zombie z = new Zombie();
		Random rnd = new Random();
		int index = rnd.nextInt(names.length);
		int index2 = rnd.nextInt(imgs.length);
		z.setup(100, names[index], img_folder + imgs[index2] );
		
		zombies.add(z);
	}
	
	public List<Zombie> getZombies() {
		return zombies;
	}
	
	public void run() {
		if(zombies.size() == 0) {this.newZombie();} 
		else if(zombies.size() <= 10){
		
			Random rnd = new Random();
			int nGen = 0;
			for(int i=0;i< zombies.size(); i++) {
				Zombie z = zombies.get(i);
				
				int dice = rnd.nextInt(100);
				
				if(dice <10) {
					z.kill();
					zombies.remove(i);
					z.setCommento(z.nome + " - E' stato distrutto");
				}else if(dice < 40) {
					boolean x = z.decrementaVita(34);
					z.setCommento(z.nome + " - Ha perso energia ed è rimasto a " + z.getVita() + "/100");
					if(x) {
						z.setCommento(z.nome + " - Ha perso energia ed  è stato distrutto");
						zombies.remove(i);
					}
					
				}else if(dice < 70) {
					z.incrementaVita(50);
					z.setCommento(z.nome + " - Mangia una persona ed ora ha " + z.getVita() + "/100");
					
				}else {
					z.setCommento(z.nome + " - Ha infettato una persona");
					nGen++;
				}
			}
			
			for(int i=0; i< nGen; i++) {
				if(zombies.size() < 10)
					this.newZombie();
				else {
					this.win = true;
					break;
				}
			}
		}
		
	}
	
	public boolean getWinStatus() {
		return this.win;
	}
	
	// --------Database------------ //
	
    SessionFactory sessionFactory;

	public void DBsetup() {
         // code to load Hibernate Session factory
		 //creating configuration object  
	    Configuration cfg=new Configuration();  
	    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
	    
	    //creating seession factory object  
	    sessionFactory=cfg.buildSessionFactory();  

    }
	
	public void DBexit() {
        // code to close Hibernate Session factory
    	sessionFactory.close();
    }
	
	public void saveDatabase(List<Zombie> zombies) {
		Session session=sessionFactory.openSession();
	
		session.beginTransaction();
		
		session.createNativeQuery("TRUNCATE TABLE zombies").executeUpdate();
		//For SQL ALTER TABLE table_name AUTO_INCREMENT=1;
		session.createNativeQuery("ALTER SEQUENCE zombies_id_seq RESTART WITH 1").executeUpdate();
		
		for(Zombie z: zombies) {
			session.save(z);
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	

}
