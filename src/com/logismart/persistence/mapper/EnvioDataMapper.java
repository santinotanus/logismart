package com.logismart.persistence.mapper;

import com.logismart.domain.model.Envio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnvioDataMapper {
    
    // Configuración para SQL Server
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LogiSmartDB;encrypt=false;";
    private static final String USER = "sa";
    private static final String PASS = "password123"; // Reemplazar con tu config real

    public void mapToDatabase(Envio envio) {
        String sql = "INSERT INTO Envios (id, peso, destino, estado) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, envio.getId());
            stmt.setDouble(2, envio.getPeso());
            stmt.setString(3, envio.getDestino());
            stmt.setString(4, envio.getEstadoActual().getNombre());
            
            stmt.executeUpdate();
            System.out.println("[DataMapper] SQL Server: Registro insertado para " + envio.getId());
            
        } catch (Exception e) {
            System.out.println("[DataMapper] (Simulado) Error de conexión DB: " + e.getMessage());
            System.out.println("[DataMapper] Fallback: Mapeando en memoria el Envio -> SQL Row");
        }
    }

    public Envio mapToDomain(String id) {
        String sql = "SELECT * FROM Envios WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("[DataMapper] SQL Server: Registro recuperado.");
                return new Envio(rs.getString("id"), rs.getDouble("peso"), rs.getString("destino"));
            }
        } catch (Exception e) {
            System.out.println("[DataMapper] (Simulado) Fallback: Retornando dummy porque no hay conexión a SQL Server.");
        }
        return new Envio(id, 0.0, "Destino Dummy");
    }
}