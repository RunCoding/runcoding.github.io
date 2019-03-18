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


/**
  http://www.iocoder.cn/Spring-Security/OAuth2-learning/
  - 客户端模式： curl -X POST "http://localhost:8080/oauth/token" --user fooClientIdPassword:secret -d "grant_type=client_credentials&scope=openid"
  - 密码模式：   curl -X POST -u 'fooClientIdPassword:secret' http://localhost:8080/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=runcoding&password=runcoding&scope=openid"
  - 简化模式(implicit)： http://localhost:8080/oauth/authorize?client_id=fooClientIdPassword&redirect_uri=http://localhost:9999/dashboard/login&response_type=token&scope=openid
  - 授权码模式(authorization_code):
    - 获取code：  http://localhost:8080/oauth/authorize?client_id=fooClientIdPassword&redirect_uri=http://localhost:9999/dashboard/login&response_type=code&scope=openid
    - 获取token： curl -X POST --user fooClientIdPassword:secret http://localhost:8080/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "code=8iiDIL&grant_type=authorization_code&redirect_uri=http://localhost:9999/dashboard/login&scope=openid"
 */
