/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjavafinalhiber;

import java.util.Scanner;
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
	
	public void startOp() 
        {
            factory = NewHibernateUtil.getSessionFactory();
            session = factory.openSession();
            trx = session.getTransaction();
            trx.begin();              
	}
        
        public void stopOp()
        {
            session.getTransaction().commit();
            session.close();
        }
	//Operaciones Sobre EQUIPO
        //---------------------------------------------------------------------------------------
	public void agregaEquipo(String nombre, int titulares, int suplentes, String director ) 
        {
		
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
	
	public void verEquipo(int id) 
        {
		Equipo eq = buscarEquipo(id);                
		System.out.println("Recuperando el Registro: "+id);
		if (eq == null)
                {
                    System.out.println("Registro no encontrado...!!!");
                }
                else
                {                    
                    System.out.println("El NOMBRE de equipo es:\n"+eq.getNombre());                                                    
                    System.out.println("La CANTIDAD DE TITULARES es:\n"+eq.getTitulares());                                                    
                    System.out.println("La CANTIDAD DE SUPLENTES es:\n"+eq.getSuplentes());                                                    
                    System.out.println("El Nombre del DT es:\n"+eq.getDirectorTecnico());                                                    
                };                    
	}
	
	public void actualizarEquipo(int id ) 
        {
                Scanner scan = new Scanner(System.in);
		Scanner scanText = new Scanner(System.in);
                scanText.useDelimiter("\\n");
                Equipo aux1 = buscarEquipo(id);               
                if(aux1 == null)
                {
                    System.out.println("No encuentro ese id...?? ");
                }
                else
                {
                    System.out.println("El NOMBRE de equipo es:\n"+aux1.getNombre());                                                    
                    System.out.println("La CANTIDAD DE TITULARES es:\n"+aux1.getTitulares());                                                    
                    System.out.println("La CANTIDAD DE SUPLENTES es:\n"+aux1.getSuplentes());                                                    
                    System.out.println("El Nombre del DT es:\n"+aux1.getDirectorTecnico());                                                    
                    System.out.println("------------------------------");                                
                    System.out.println("Actualizando registro: "+id);                                
                    System.out.println("Ingrese NOMBRE de equipo\n");                    
                    aux1.setNombre(scanText.next());
                    System.out.println("Ingrese CANTIDAD DE TITULARES\n");                    
                    aux1.setTitulares(scan.nextInt());
                    System.out.println("Ingrese CANTIDAD DE SUPLENTES\n");
                    aux1.setSuplentes(scan.nextInt());
                    System.out.println("Ingrese Nombre del DT\n");
                    aux1.setDirectorTecnico(scanText.next());                                    
                    startOp();
                    session.update(aux1);
                    System.out.println("Equipo Actualizado");
                    stopOp();
                }
	}
	
	public void borrarEquipo(int id) 
        {
            Equipo aux1 = buscarEquipo(id);               
            if(aux1 == null)
            {
                System.out.println("No encuentro ese id...?? ");
            }
            else
            {
            System.out.println("Borrando el registro: "+id);
            startOp();
            session.delete(aux1);
            stopOp();
            }
	}
        
        private Equipo buscarEquipo(int id)
        {              
            startOp();
            Equipo eq = (Equipo) session.get(Equipo.class,id);            
            stopOp(); 
            return(eq);             
        }
        //Operaciones sobre PARTIDO
        //--------------------------------------------------------------------
}
