package org.example.springsecuritylearning2.videotut.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service

public class JwtService {
    private String secretKey="";
    JwtService(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            Encoders.BASE64.encode(sk.getEncoded());
            secretKey = Encoders.BASE64.encode(sk.getEncoded());
        }
        catch(Exception e){
            System.out.println(e);

        }

    }


    public String generateToken(String username){
        Map<String,Object> claims=new HashMap<>();
        return Jwts.builder().addClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*60*60*30))
                .signWith(getKey())
                .compact();
        






    }
    public Key getKey(){
        byte[] byteKey= Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(byteKey);


    }

}
