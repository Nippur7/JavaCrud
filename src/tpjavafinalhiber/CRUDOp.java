/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjavafinalhiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author tonym
 */
public class CRUDOp {

    //Configuration config;
    SessionFactory factory;
    private Session session;
    Transaction trx;
	
	public void startOp() {

		factory = NewHibernateUtil.getSessionFactory();
		session = factory.openSession();
                trx = session.getTransaction();
                trx.begin();
                
	}
        
        public void stopOp(){
            session.getTransaction().commit();
            session.close();
        }
	
	public void agregaEquipo(String nombre, int titulares, int suplentes, String director ) {
		
		System.out.println("Agregando Registro Nuevo");
		
		try {
			
			Equipo eq = new Equipo();
			eq.setNombre(nombre);
			eq.setTitulares(titulares);
			eq.setSuplentes(suplentes);
			eq.setDirectorTecnico(director);
                        startOp();
                        int resultado = (int)session.save(eq);
			System.out.println("Registro Nuevo Agregado con ÉXITO bajo el id: "+resultado);
			stopOp();
		}catch(Exception e) {
                    
                    System.err.println("Operación CRUD con errores: ");
                    e.printStackTrace();
		}
	}
	
	public Equipo verEquipo(int id) {
		Equipo eq = new Equipo();
                startOp();
                eq = (Equipo) session.get(Equipo.class,id);
		System.out.println("Recuperando el Registro: "+id);
		stopOp();
                return eq;
	}
	
	public void actualizarEquipo(Equipo eq ) {
		//Equipo eq = new Equipo();
                System.out.println("Actualizando registro: "+eq.getIdequipo());
                startOp();
                session.update(eq);
                stopOp();
                
		
	}
	
	public void deleteRecord(int id) {
		System.out.println("Borrando el registro: "+id);
	}
}
