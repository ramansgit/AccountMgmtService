package com.account.mgmt.jwt;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.SecurityContext;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import com.account.mgmt.exception.JWTIssuerException;
import com.account.mgmt.exception.UnAuthorizedAccessException;
import com.account.mgmt.util.AccountMgmtConstant;

public class JWTIssuer {

	/**
	 * 
	 * @param jwt
	 * @return
	 * @throws InvalidJwtException
	 */
	public  static String validateJwtToken(String jwt) throws UnAuthorizedAccessException {
		String subject = null;
		RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();

		System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());

		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireSubject()
				.setVerificationKey(rsaJsonWebKey.getKey())
				// verify the
				// signature with
				// the public key
				.build(); // create the JwtConsumer instance

		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
			subject = (String) jwtClaims.getClaimValue("sub");
			System.out.println("JWT validation succeeded! " + jwtClaims);
		} catch (InvalidJwtException e) {
			e.printStackTrace(); // on purpose
			throw new UnAuthorizedAccessException(e.getMessage());
		}

		return subject;
	}

	/**
	 * 
	 * @param subject
	 * @return
	 * @throws JWTIssuerException
	 */
	public static String buildJWT(String subject) throws JWTIssuerException {
		RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();
		System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());

		JwtClaims claims = new JwtClaims();
		claims.setSubject(subject); // the subject/principal is whom the token
		// claims.setIssuedAt(issuedAt);
		// claims.setExpirationTime(expirationTime);
		// claims.setIssuer("http://localhost:9095");// is about

		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toJson());
		jws.setKey(rsaJsonWebKey.getPrivateKey());
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

		String jwt = null;

		try {
			jwt = jws.getCompactSerialization();
		} catch (JoseException e) {
			// TODO Auto-generated catch block
			throw new JWTIssuerException(AccountMgmtConstant.JWT_TOKEN_CREATION_FAILED_ERR_MSG);

		}
		System.out.println("Claim:\n" + claims);
		System.out.println("JWS:\n" + jws);
		System.out.println("JWT:\n" + jwt);

		return jwt;
	}

	/**
	 * 
	 * @param requestContext
	 * @throws IOException
	 */
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("request filter invoked...");

		String authHeaderVal = requestContext.getHeaderString("Authorization");

		// consume JWT i.e. execute signature validation
		if (authHeaderVal.startsWith("Bearer")) {
			try {
				System.out.println("JWT based Auth in action... time to verify th signature");
				System.out.println("JWT being tested:\n" + authHeaderVal.split(" ")[1]);
				final String subject = validateJwtToken(authHeaderVal.split(" ")[1]);
				final SecurityContext securityContext = requestContext.getSecurityContext();
				if (subject != null) {
					requestContext.setSecurityContext(new SecurityContext() {
						@Override
						public Principal getUserPrincipal() {
							return new Principal() {
								@Override
								public String getName() {
									System.out.println("Returning custom Principal - " + subject);
									return subject;
								}
							};
						}

						@Override
						public boolean isUserInRole(String role) {
							return securityContext.isUserInRole(role);
						}

						@Override
						public boolean isSecure() {
							return securityContext.isSecure();
						}

						@Override
						public String getAuthenticationScheme() {
							return securityContext.getAuthenticationScheme();
						}
					});
				}
			} catch (UnAuthorizedAccessException ex) {
				System.out.println("JWT validation failed");

				requestContext.setProperty("auth-failed", true);

			}

		} else {
			System.out.println("No JWT token !");

		}

	}

}
