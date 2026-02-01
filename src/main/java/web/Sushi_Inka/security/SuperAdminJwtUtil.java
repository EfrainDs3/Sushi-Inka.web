package web.Sushi_Inka.security;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;

/**
 * SuperAdminJwtUtil (V2)
 *
 * Utilidad de JWT dedicada para SuperAdmin.
 * Usa una CLAVE FIJA para garantizar persistencia entre reinicios.
 * Separa esta lógica del JwtUtil original (legacy).
 */
@Component
public class SuperAdminJwtUtil {

    // 🔑 CLAVE FIJA para persistencia (Requerimiento V2)
    private static final String SECRET_KEY = "ComidaRegional_Super_Secret_Key_For_Jwt_Security_Fixed_2025";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private final long EXPIRATION_TIME = 100L * 356 * 24 * 60 * 60 * 1000;

    public String generarToken(String clienteId) {
        return Jwts.builder()
                .setSubject(clienteId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key).compact();
    }

    public String generateToken(String email) {
        return generarToken(email);
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("❌ [SuperAdminJwtUtil] Error validando Token: " + e.getMessage());
            return false;
        }
    }

    public String extraerClienteId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}

