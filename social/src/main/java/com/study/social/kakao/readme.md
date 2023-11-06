# Kakao

카카오 소셜 로그인을 위한 패키지입니다.

## KAKAO DEVELOPERS 설정하기

### 애플리케이션 추가

---

[KAKAO DEVELOPERS](https://developers.kakao.com/console/app)홈페이지에 접속해서 애플리케이션을 추가합니다.

<img width="700" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/89d6b26c-b312-42f3-8c9d-e0e51e870ef0">

> 임의 값을 넣어서 하였습니다.

<img width="700" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/006cad2c-072a-4b37-b4bd-2217dbe0eac0">

생성된 애플리케이션을 클릭한 뒤 오른쪽에 보이는 "플랫폼"을 선택합니다.

<img width="700" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/143059dc-ae5e-4405-a9a0-9b3dbd287f19">
<img width="633" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/8a6eeb78-7d4e-46a4-bfc5-41291d5fd6e2">

> 💫 Info
>
> 만약 프론트엔드까지 구현하게 된다면 해당 영역에서 프론트엔드 URL을 넣어줘야 합니다.

여기에서는 로그인 `API` 만 사용할 예정이므로 바로 다음 단계로 넘어가도록 합니다.

### 카카오 로그인 설정

---

<img width="700" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/a35bde56-c1c3-49e5-a451-b5d610fcbe10">

오른쪽에 카카오 로그인을 선택합니다. 이후 아래의 단계를 따라서 설정합니다.

1. 활성화 설정의 기본 설정을 `ON` 으로 변경합니다.
2. `Redirect URI` 를 원하는 `URI`로 설정합니다. 해당 프로젝트는 단순 공부를 하기 위한 프로젝트이므로 단순하게 설정을 하였습니다.

위와 같이 설정하고 다음 단계로 넘어가도록 하겠습니다.

<img width="700" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/6c544f13-62ce-4b89-b306-3b67a16dad37">

다음 사용자의 정보를 어디까지 받을 지 동의하는 항목으로 넘어갑니다.
 
여기에서는 닉네임만 가져오도록 설정을 해보도록 하겠습니다. 위의 사진에 보이는 닉네임 컬럼에서 설정을 클릭합니다.

<img width="669" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/0ba4f6a5-f425-4b28-a9c8-b51a925b67dc">

다음과 같이 "동의 항목 설정"이 나오는데 원하는 동의 단계로 설정하도록 합니다. 저는 "필수 동의"로 설정해보도록 하겠습니다.

<img width="700" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/c9ecbf8b-0afc-4634-a91a-4fd2a7d7ff7c">

다음과 같이 "필수 동의"로 설정된 것을 확인할 수 있습니다.

### REST API

---

이번에는 위에서 설정한 것을 토대로 테스트를 진행해보도록 하겠습니다.

[KAKAO DEVELOPERS REST API](https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-code)사이트에 접속하면 
예제를 확인할 수 있습니다.

```http request
https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}
```

- `REST_API_KEY` : "요약 정보 -> 앱키 -> REST API 키" 에서 확인할 수 있습니다.
- `REDIRECT_URI` : "카카오 로그인 -> REDIRECT_URI" 에서 확인할 수 있습니다.

<img width="700" src="https://github.com/KIMSEI1124/Spring_Study/assets/74192619/32591e9a-b32a-4f35-bf9b-f4b09c0c1840">

다음과 같이 요청하면 정상적으로 나타나는 것을 확인할 수 있습니다.

## Ref

1. [Spring Boot 에서 Kakao, Naver 로그인하기 (OAuth 2.0) ~ 뱀귤 블로그](https://bcp0109.tistory.com/379)
