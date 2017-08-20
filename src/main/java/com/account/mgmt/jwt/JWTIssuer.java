package com.account.mgmt.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.account.mgmt.exception.JWTIssuerException;
import com.account.mgmt.exception.UnAuthorizedAccessException;
import com.account.mgmt.util.AccountMgmtConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTIssuer {

	/**
	 * 
	 * @param subject
	 * @return
	 * @throws JWTIssuerException
	 */
	public static String buildJWToken(String subject) throws JWTIssuerException {
		// We will sign our JWT with our ApiKey secret
		System.out.println("jwt token generation for " + subject);
		String jwt = "";
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(AccountMgmtConstant.JWT_SIGNING_KEY);
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			long expMillis = nowMillis + 900000; // 15 mins expiry
			Date exp = new Date(expMillis);

			jwt = Jwts.builder().setSubject(subject).setIssuedAt(now).setIssuer(AccountMgmtConstant.JWT_ISSUER)
					.setExpiration(exp).compressWith(CompressionCodecs.GZIP).signWith(signatureAlgorithm, signingKey)
					.compact();
		} catch (Exception e) {
			throw new JWTIssuerException(AccountMgmtConstant.JWT_TOKEN_CREATION_FAILED_ERR_MSG);
		}

		System.out.println("jwt token" + jwt);
		return jwt;
	}

	/**
	 * 
	 * @param jwt
	 * @return
	 * @throws UnAuthorizedAccessException
	 */
	public static String parseJWT(String jwt) throws UnAuthorizedAccessException {
		Claims claims = null;
		try {
			claims = Jwts.parser().requireIssuer(AccountMgmtConstant.JWT_ISSUER)
					.setSigningKey(DatatypeConverter.parseBase64Binary(AccountMgmtConstant.JWT_SIGNING_KEY))
					.parseClaimsJws(jwt).getBody();
			if (claims == null) {
				throw new UnAuthorizedAccessException(AccountMgmtConstant.INVALID_JWT_TOKEN_ERR_MSG);

			}
			System.out.println("ID: " + claims.getId());
			System.out.println("Subject: " + claims.getSubject());
			System.out.println("Issuer: " + claims.getIssuer());
			System.out.println("Expiration: " + claims.getExpiration());

		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			throw new UnAuthorizedAccessException(e.getMessage());
		} catch (Exception e) {

			// we get here if the required claim is not present
			e.printStackTrace(); // on purpose
			throw new UnAuthorizedAccessException(AccountMgmtConstant.INVALID_JWT_TOKEN_ERR_MSG);

		}

		return claims.getSubject();

	}

}
