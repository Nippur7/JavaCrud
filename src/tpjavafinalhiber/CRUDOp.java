/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjavafinalhiber;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Scanner;
import org.hibernate.HibernateException;
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
	
	public int borrarEquipo(int id) 
        {
            Equipo aux1 = buscarEquipo(id);               
            if(aux1 == null)
            {
                System.out.println("No encuentro ese id...?? ");
                return (-1);
            }
            else
            {
            System.out.println("Borrando el registro: "+id);
            try
            {
                startOp();
                session.delete(aux1);
                session.flush();
                stopOp();
                return (1);
            }
            catch(HibernateException ex)
                    {
                       return (-1);
                       //System.err.println("Error al eliminar...");
                       //break;
                       //throw ex;
                       
                       //session.setRollbackOnly();
                    }
            
            
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
	public void agregarPartido(int idEq1, int idEq2, Date fecha,  int golEq1, int golEq2 ) 
        {
		
		System.out.println("Agregando Partido Nuevo");
		
		try {
			
			Equipo eq1 = buscarEquipo(idEq1);
                        Equipo eq2 = buscarEquipo(idEq2);
                        Partido par = new Partido();
                        if (eq1 != null && eq2 != null)
                        {
                            par.setEquipoByIdequipo1(eq1);
                            par.setEquipoByIdequipo2(eq2);
                            par.setFecha(fecha);
                            par.setGolesEq1(golEq1);
                            par.setGolesEq2(golEq2);
                            if(golEq1>golEq2)
                            {
                                if(eq1.getPuntos()== null)
                                {
                                    eq1.setPuntos(3);
                                }
                                else
                                {
                                    eq1.setPuntos(eq1.getPuntos()+3);
                                }
                            }
                            else
                            {
                            if(golEq2>golEq1)
                            {
                                if(eq2.getPuntos()== null)
                                {
                                    eq2.setPuntos(3);
                                }
                                else
                                {
                                    eq2.setPuntos(eq2.getPuntos()+3);
                                }
                            }
                            else
                            {
                                if(eq1.getPuntos()== null)
                                {
                                    eq1.setPuntos(1);
                                }
                                else
                                {
                                    eq1.setPuntos(eq1.getPuntos()+1);
                                }
                                if(eq2.getPuntos()== null)
                                {
                                    eq2.setPuntos(1);
                                }
                                else
                                {
                                    eq2.setPuntos(eq2.getPuntos()+1);
                                }
                            }
                            }
                                if(eq1.getPartidosJugados()== null)
                                {
                                    eq1.setPartidosJugados(1);
                                }
                                else
                                {
                                    eq1.setPartidosJugados(eq1.getPartidosJugados()+1);
                                }
                                if(eq2.getPartidosJugados()== null)
                                {
                                    eq2.setPartidosJugados(1);
                                }
                                else
                                {
                                    eq2.setPartidosJugados(eq2.getPartidosJugados()+1);
                                }                        
                            startOp();
                            int resPar = (int)session.save(par);
                            stopOp();
                            startOp();
                            session.update(eq1);
                            session.update(eq2);
                            stopOp();
                            System.out.println("Registro del Nuevo Partido Agregado con ÉXITO bajo el id: "+resPar);
                        }
                        else
                        {
                            System.out.println("Id no encontradas, revise que ambos equipos existen...");
                        }
		}catch(Exception e) {
                    
                    System.err.println("Operación CRUD de Partidos con errores: ");
                    e.printStackTrace();
		}
	}
	
	public void verPartido(int id) 
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
	
	public void actualizarPartido(int id ) 
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
	
	public void borrarPartido(int id) 
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
        
        private Equipo buscarPartido(int id)
        {              
            startOp();
            Equipo eq = (Equipo) session.get(Equipo.class,id);            
            stopOp(); 
            return(eq);             
        }        
}
