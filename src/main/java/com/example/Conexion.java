package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class Conexion {
    // Método principal para probar la conexión a la base de datos  
    public static void main(String[] args) {
        // Crear un objeto Properties para cargar la configuración de la base de datos
        Properties props = new Properties();

        // Cargar configuración desde db.properties en el classpath
        // Usamos try-with-resources para asegurar que el InputStream se cierre automáticamente
        try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream("db.properties")) {
            // Verificar si el archivo db.properties se encontró en el classpath
            if (input == null) {
                System.out.println("⚠️ No se encontró el fichero db.properties en el classpath.");
                return;
            }
            
            // Cargar las propiedades desde el archivo
            props.load(input);
            // Obtener los valores de las propiedades
            String url = props.getProperty("db.url");
            String usuario = props.getProperty("db.user");
            String contrasena = props.getProperty("db.password");

            // Conexión a MySQL usando try-with-resources
            try (Connection conn = DriverManager.getConnection(url, usuario, contrasena)) {
                System.out.println("✅ Conexión realizada correctamente.");
            } catch (SQLException e) {
                System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error al cargar configuración: " + e.getMessage());
        }
    }
}

