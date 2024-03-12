# Kakao-Login-Springboot-Api
Kakao Login Springboot Application

### [Kakao Login Rest API](https://developers.kakao.com/)

- REST API is a suitable architectural style to implement Kakao Login both in PC and on the mobile web. To implement Kakao Login using a REST API, you need to get an authorization code, and then get tokens with the obtained authorization code as follows.
  
![image](https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/e38289cb-8f84-4f5f-9f30-dd8f842a250e)

1. If a user clicks `Login with Kakao`, the user is asked to log in to the Kakao login page provided by Kakao.
2. When the user logs in with Kakao Account information on the page, the Getting authorization code API is invoked. This API prompts the Consent screen to a user. If a web browser retains a session cookie that includes the Kakao Account information, the Consent screen is presented to the user without asking a login with Kakao Account.
3. Once the user approves permissions by clicking `Accept and Continue`, the Kakao authorization server validates the user’s credentials and issues an authorization code. The user is redirected back to your app with the authorization code via redirect_uri.
4. Your app requests an access token and a refresh token with the issued authorization code by calling the Getting tokens.
5. The Kakao authorization server validates the request, issues an access token and a refresh token based on the authorization code, and provides authorization. See Token information to learn more about the access token and refresh token.

## [Implementation]()

1. First we code Server Application on Java SpringBoot. Source code is given in the `Kakao-Login-Springboot-Api` folder. Then we run the Spring application to test.

  <img width="1326" alt="image" src="https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/54ddc0c6-5406-4b86-ba33-ae4004719a24">

2. Then we register our application on `developers.kakao.com` site.

![image](https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/6026692b-f4f8-4c17-b87d-6174d0ac1930)

3. Then we register our `Redirect URI` on `developers.kakao.com` site.

![image](https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/0c17931c-65de-4c6d-9032-05f0a447c22f)

4. Then we have to state `Consent Items` on `developers.kakao.com` site.

![image](https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/19f2aeb7-873f-493f-b526-aea7521db05c)

5. Then we go back to Spring application and code `application.properties` and `secure.properties`

```
#developers.kakao.com/My Application/App Settings/APP KEYS/REST API key
kakao.api.key=${kakao.api.key}     --> REST API key
kakao.client.id=${kakao.client.id} --> REST API key
#developers.kakao.com/My Application/Product Settings/Kakao Login/Security/Code
kakao.client.secret=${kakao.client.secret}
```
- App Keys:

![image](https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/9cf2a13a-fc3a-493d-9a79-934d71818313)

- Client secret code:

![image](https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/a85a3ae9-1513-4646-98a9-b35a338100d5)

6. Then we have to make sample `html web page` and define the URL
7. Then when start the Server application and go to page it looks as following:

![image](https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/f73fed46-3252-4786-9db0-2a49760e5170)

8. When we press the Kakao Login button it redirect to the URI link and as the back we get `Authorization code` as following:
   
![image](https://github.com/af4092/Kakao-Login-Springboot-Api/assets/24220136/7f7e73f0-6916-490f-8749-3bedb9028122)

## [Reference]()

- [Kakao Developers Docs](https://developers.kakao.com/docs/latest/en/index) - Official documentation for Kakao application registration on `developers.kakao.com` site.
