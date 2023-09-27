package com.compny.config;


import com.compny.base.enums.Role;
import com.compny.component.JwtDecode;
import com.compny.exception.AppBadRequestException;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {

    private final static String secretKey = "IAmWritingThisWordBecauseWeNeedSecretKeyButItWillBeLongerThanOtherWords";

    public static String encode(String id, Role role) {

        // todo open the comment before upload project to server
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .setExpiration(new Date(System.currentTimeMillis()+(60*60*1000*24)))
                .setIssuer("Shifo 24 for test")
                .claim("role", role)
                .compact();
    }

    public static JwtDecode decode(String jwt) {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            Jws<Claims> jws = jwtParser.parseClaimsJws(jwt);

            Claims claims = jws.getBody();

            String id = claims.getSubject();
            String  role = (String) claims.get("role");

            return new JwtDecode(id, Role.valueOf(role));
        } catch (JwtException e) {
            throw new AppBadRequestException("JWT invalid!");
        }
    }

}
