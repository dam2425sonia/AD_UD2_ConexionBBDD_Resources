package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class Conexion {

    public static void main(String[] args) {

        Properties props = new Properties();

        // Cargar configuración desde db.properties en el classpath
        try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream("db.properties")) {

            if (input == null) {
                System.out.println("⚠️ No se encontró el fichero db.properties en el classpath.");
                return;
            }

            props.load(input);

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

