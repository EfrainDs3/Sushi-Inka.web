package web.Sushi_Inka.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 100L * 356 * 24 * 60 * 60 * 1000;

    public String generarToken(String clienteId) {
        return Jwts.builder()
                .setSubject(clienteId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()
                        + EXPIRATION_TIME))
                .signWith(key).compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
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
