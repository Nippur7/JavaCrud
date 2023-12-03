/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpjavafinalhiber;

import java.io.IOException;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tonym
 */
public class TpJavaFinalHiber 
{

    /**
     * @param args the command line arguments
     */
 
        static int idequipo;
	static String nombre;
	static int titulares;
	static int suplentes;
	static String director;
	int puntos;
	int partidos;
	int idpartido;
	Date fecha;
	int idequipo1;
	int idequipo2;
	int golesEq1;
	int golesEq2;
	
	public static void main(String[] args) throws IOException 
        {
		
		CRUDOp crud = new CRUDOp();
                Logger.getLogger("org.hibernate").setLevel(Level.OFF);                
			Scanner scan = new Scanner(System.in);
			Scanner scanText = new Scanner(System.in);
			int opcion = 1;			
			while(opcion == 1) 
                        {
				System.out.println("Menú CRUD --Equipos--");
				System.out.println("1) Agregar Equipo\n2) Recuperar datos del Equipo\n3) Actualizar un Equipo\n4) Eliminar un Equipo\n");
				System.out.println("Teclee una opción:");
				scanText.useDelimiter("\\n");			
				int selector = scan.nextInt();			
				switch(selector) 
                                {
					case 1:
						System.out.println("Agregar un Equipo\n");
						System.out.println("Ingrese NOMBRE de equipo\n");
						nombre = scanText.next();
						System.out.println("Ingrese CANTIDAD DE TITULARES\n");
						titulares = scan.nextInt();
						System.out.println("Ingrese CANTIDAD DE SUPLENTES\n");
						suplentes = scan.nextInt();
						System.out.println("Ingrese Nombre del DT\n");
						director = scanText.next();
						crud.agregaEquipo(nombre, titulares, suplentes, director);
						break;
					case 2:
                                            try
                                            {
                                                System.out.println("Recuperar Datos de Equipo\n");
                                                System.out.println("Ingrese el Número de id que desea consultar");
                                                idequipo = scan.nextInt();
                                                crud.verEquipo(idequipo);                                                
                                            }
                                            catch(InputMismatchException e)
                                            {
                                                System.err.println("Sólo números por favor...!!!");
                                                scan.nextLine();
                                            }
						break;
					case 3:
                                            try
                                            {
						System.out.println("Actualizar un Equipo\n");
                                                System.out.println("Ingrese el id del Equipo a actualizar");
                                                idequipo = scan.nextInt();                                                
                                                crud.verEquipo(idequipo);
                                                crud.actualizarEquipo(idequipo);                                                
                                            }
                                            catch(InputMismatchException e)
                                            {
                                                System.err.println("Sólo números por favor...!!!");
                                                scan.nextLine();
                                            }
						break;
					case 4:
                                            try
                                            {
						System.out.println("Eliminar un Equipo\n");
                                                System.out.println("Ingrese el id del Equipo a borrar");
                                                idequipo = scan.nextInt();                                                
                                                crud.borrarEquipo(idequipo);                                                                                                
                                            }
                                            catch(InputMismatchException e)
                                            {
                                                System.err.println("Sólo números por favor...!!!");
                                                scan.nextLine();
                                            }
                                            
						break;
					default:
						System.out.println("Por favor teclee alguna opción válida\n");
			
				}
			System.out.println("Desea ejecutar otra operación? (Si-> 1 / No-> 0)");
                        opcion = scan.nextInt();
			if (opcion > 0 ){                            
                            opcion = 1;
                            
                        }else{
                            System.out.println("See you space cowboy...!!");                            
                            System.exit(0);
                        }
			}
		}	
}
