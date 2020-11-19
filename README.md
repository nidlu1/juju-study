application-oauth.properties 내용

#google registration
spring.security.oauth2.client.registration.google.client-id=142288441997-d44hu3gr9ohops6p94o6d3247l7mcbd3.apps.googleusercontent.com
spring.security.oauth2.client.registration.google.client-secret=aNr-2gLZYzeZCwjkJl0t_3MX
spring.security.oauth2.client.registration.google.scope=profile,email
# 나머지 설정은 Common-OAuth2Provider에서 해줌


#naver registration
spring.security.oauth2.client.registration.naver.client-id=ubheh65qRTMbFtXQdvji
spring.security.oauth2.client.registration.naver.client-secret=Z5Iw7DkjjF
#spring.security.oauth2.client.registration.naver.redirect-uri={baseUrl}/{action}/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.naver.redirect-uri={baseUrl}/oauth2/authorization/{registrationId}
spring.security.oauth2.client.registration.naver.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.naver.scope=name,email,profile_image
spring.security.oauth2.client.registration.naver.client-name=naver

#naver provider
spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token-uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
spring.security.oauth2.client.provider.naver.user-name-attribute=response
#user_name_attribute=response: 기준이 되는 user_name의 이름을 네이버에선 회원조회시 JSON으로 반환되기 때문에 response로 해야함.
#스프링 시큐리티에선 하위필드를 명시 할 수 없어 최상위필드만 user_name으로 지정 가능. 하지만 네이버 응답값 최상위 필드는 resultCode, message, respons 에서 택해야함.
#본문에서 담고 있는 response를 user_name으로 지정하고 이후 자바코드로 response의 id를 user_name으로 지정하겠음.
# 네이버 오픈 api 로그인 회원결과
#{
#    "resultcode": "00",
#    "message": "success",
#    "response": {
#        "email": "openapi@naver.com",
#        "nickname": "OpenAPI",
#        "profile_image": "https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif",
#        "age": "40-49",
#        "gender": "F",
#        "id": "32742776",
#        "name": "오픈 API",
#        "birthday": "10-01"
#    }
#}
