package kkpa.sudoku.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class OperacionesArchivoAPI {

	public static boolean guardarObjeto(Object objGuardar, String nombreArchivo) {
		try {
			FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
			ObjectOutputStream salida = new ObjectOutputStream(fileOut);
			salida.writeObject(objGuardar);
			salida.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Object leerObjetoSerializado(String nombreArchivo) {
		try {
			FileInputStream fileIn = new FileInputStream(nombreArchivo);
			ObjectInputStream entrada = new ObjectInputStream(fileIn);
			Object objReaded = entrada.readObject();
			entrada.close();
			return objReaded;
		} catch (FileNotFoundException e) {
			System.out.println("No existe el archivo. " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<String> leerArchivo(String nombreArchivo) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<String> lstLineas = new ArrayList<String>();

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(nombreArchivo);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null) {
				lstLineas.add(linea);
			}
		} catch (Exception e) {
			System.out.println("No existe el archivo de la solucion del juego");
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lstLineas;
	}
	
}
