-- client_secret密码=secret new BCryptPasswordEncoder().encode("secret")
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('fooClientIdPassword', '$2a$10$yvKiEqZECQ2f93.WEttKZeNN5lX1Xt.WghkXvdAcvgcfAU1JaarcG', 'foo,read,write,openid',
	'password,authorization_code,refresh_token,client_credentials,implicit', 'http://localhost:9999/dashboard/login', 'ROLE_USER,ROLE_ANONYMOUS', 36000, 36000, null, true);

INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('sampleClientId', '$2a$10$yvKiEqZECQ2f93.WEttKZeNN5lX1Xt.WghkXvdAcvgcfAU1JaarcG', 'read,write,foo,bar,openid',
	'implicit', 'http://localhost:9999/dashboard/login', 'ROLE_USER,ROLE_ANONYMOUS', 36000, 36000, null, false);

INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('barClientIdPassword', '$2a$10$yvKiEqZECQ2f93.WEttKZeNN5lX1Xt.WghkXvdAcvgcfAU1JaarcG', 'bar,read,write,openid',
	'password,authorization_code,refresh_token,implicit', 'http://localhost:9999/dashboard/login', 'ROLE_USER,ROLE_ANONYMOUS', 36000, 36000, null, true);



