<h1 align="center">
  <a id="readme-top"></a>
  CMarket
</h1>
<div align="center">
  <b>Spring Boot</b> approach on fantasy ecommerce-like website <br/>
  <i>! for demonstration purpose !</i>
  <br/><br/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.4.5-grey?logo=springboot&logoColor=fff&labelColor=6DB33F" alt="Spring Boot Badge" />
  <img src="https://img.shields.io/badge/Spring-6.1.6-grey?labelColor=6DB33F" alt="Spring Framework Badge" />
  <img src="https://img.shields.io/badge/MySQL-8.4-grey?logo=mysql&logoColor=white&labelColor=4479A1" alt="MySQL 8.4" />
  <img src="https://img.shields.io/badge/JPA-59666C?logo=hibernate&logoColor=fff" alt="JPA (Hibernate)" />
  <img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff" alt="Docker" />
</div>

<div align="center">
  <br/>
  <a href="" target="_blank">View Demo</a>
  &middot;   
  <a href="" target="_blank">Request Feature</a>
  &middot;   
  <a href="" target="_blank">Ask Me a Question</a>
  </p>
</div>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#features">Features</a></li>
    <li><a href="#concepts">Concepts</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installations">Installations</a></li>
      </ul>
    </li>
  </ol>
</details>

---

<!-- ABOUT THE PROJECT -->
## About The Project

CMarket is my ongoing project where I’m developing the core backend functionality of an e-commerce web application using **_Spring Boot_**. My focus is on _building the essential system logic and backend services_, without the extra layers, so I can deepen my understanding and learn more effectively. Throughout the development, I maintain clean code structure and align with business standards, showcasing my proficiency in **_Spring Boot_**.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With
The project was built using these tech stacks:
* Spring 6.1.6
* Spring Boot 3.4.5
* Docker
* MySQL 8.4
* Redis 7.4
<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Features
**✅ Completed**
- **Spring Environment**
  - Multi-profile environment
- **Spring JPA** 
  - JPA Entity Listener (uuid)
- **Spring Boot MVC (REST API)**
  - Localization
  - Rest Handler
- **Spring Test (Integration - Unit - Mock)**
- **Spring Security**
  - Basic Auth (Validation, Error Handling, etc...)
  - Route Configuration
  - JWT Auth
- **OAuth2 Social Login**
  - Github OAuth w/ PKCE
  - Integration with current existing JWT (OAuth Login / Register)
- **Dockerization**
  - Docker File
  - Docker Compose
- **Redis Caching**
  - Simple Cache / Redis Cache (`spring.cache.type=simple/redis`)
  - Unit Test
- **CICD** _(github actions)_

**🚧 In progress**
- **Order Functionality** - ***(WIP)***
  - **Microservice** approach for Order Service
  - Kafka Messenger
- **Spring AOP**
- **Admin & Permissions**
  - Create Items
  - File Upload Support
- **API Hosting**
- Open API (_Swagger_)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Concepts
The project follows a _Domain-Driven Design (DDD)_ structure within a monolithic architecture as follows:
```plaintext
Domain/
├── Config/ (@Config classes)
├── Contracts/
├── Controller/ (@RestController classes)
├── Handler/ (@RestControllerAdvice classes)
├── Model/ (Jpa @Entity Classes)
├── Property/ (@ConfigurationProperties Classes)
├── Service/ (@Service classes)
└── ... (Enum, Exception, Dto, etc)
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Technicals

<details>
  <summary><strong>👥 Spring Profile Bean Management</strong></summary>
  <small>The main concept is to use different env for different purpose. The profile can be changed through the environment or by modifying <code>application.properties</code> directly.</small> 
  <br/><br/>
  <img src="https://i.imgur.com/2Oki6Dn.png" alt="CMarket Spring Boot Profile Management Concept">
</details>
<br>
<details>
  <summary><strong>🌐 Routing</strong></summary>
  <small>In every domain, we make a route config inside the domain's config folder to configure the route security behavior of the domain</small>

<pre>
Domain/
├── Route/DomainRouteConfig.java
└── ...
</pre>

  <small>Every route config must implement the `RouteConfigInterface` and use `@Component` annotation for its configuration to be loaded and collected into an array in the `RouteConfig` configuration class
  </small> 
  ```java
  // RouteConfig

  private final RouteConfigInterface[] routeConfigs;

  public void initializeRoutes(HttpSecurity http) throws Exception {
    for (RouteConfigInterface routeConfig : routeConfigs) {
      routeConfig.configureRoute(http);
    }
  }
  ```
  <small>_**(For detailed docs, refer to the diagram below / Javadoc inside the mentioned class)**_
  </small>
  
  <small>The `RouteConfig` then is injected into the `SecurityConfig`, and it invokes the `RouteConfig.initializeRoutes(..)` method to initialize all the routes specified. As an addition, the `RouteConfig` will also configure the default behavior to other route that are not configured as it follows the _**Most Specific to Least Specific rule**_</small>

  <img src="https://i.imgur.com/EX96isX.png" alt="CMarket Spring Boot Route Concept">
  
</details>
<br>
<details>
  <summary>
    <strong>🛠️ Specification Builder | <i>Query Builder</i></strong>
  </summary>
  
  <small>The specification builder follows the **builder pattern** to create specifications for querying data from the repository. It encapsulates complexity by keeping all the given specifications and reduce the verbosity by handling null values and chaining them together.
  </small>
  
  <img src="https://i.imgur.com/apYYQuq.png" alt="CMarket Spring Boot Specification Builder Concept">
</details>
<br>
<details>
  <summary><strong>🔑 JWT</strong></summary>
  <small>
  JWT uses a private/public key for signing and verification. In Spring, JWT env values loaded with 
  <code>@ConfigurationProperties</code> and then injected into <code>JwtConfig</code> for use in <code>JwtService</code>. A custom 
  <code>JwtToUserConverter</code> is also needed to convert JWT subject into principal in <code>SecurityConfig</code> 
  </small>
<br/><br/>
<img src="https://i.imgur.com/IizotMp.png" alt="CMarket Spring Boot JWT Concept">

</details>
<br>
<details>
  <summary><strong>🔐 OAuth</strong></summary>

  <small> The OAuth used uses the PKCE concepts to helps authorize the process in exchanging the access code.</small> <br/>

   <small>
  We first define <code>OAuthServiceInterface</code> as a contract for OAuth services, with abstract methods for exchanging access codes and fetching user info. <br> 
  A <code>@ConfigurationProperties</code> class maps necessary environment variables and is injected into the interface’s implementation for authentication. 
  We then create <code>RestClientConfig</code> and an <code>OAuthClient</code> <b>declarative HTTP interface</b> for third-party calls. <br>
  Finally, we register an <code>OAuthController</code> with the callback URL, loading it only when an <code>OAuthServiceInterface</code> bean exists 
  (<code>@ConditionalOnBean</code>), and inject the correct service by name (e.g., <code>@Service("githubOAuth")</code>).
  </small>
  <br/><br/>
<img src="https://i.imgur.com/GMZjn9f.png" alt="CMarket Spring Boot OAuth Concept">
</details>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Getting Started
Follow the steps below to set up and run the project on your local machine.

### Prerequisites
- Java **21** or higher
- Spring Boot **3.4.5** or later
- Spring Framework **6.1.6** or later
- OpenSSL (tested with version _**3.5.0**_) for JWT key generation
- Working `application.properties` configurable file (_explained below_)

#### With Docker
- [Docker](https://docs.docker.com/get-docker/) (Tested with version 28.x or higher)
- Docker Compose V2
- `.env` docker configurable file _(for example, see below)_
- `application.properties` configurable file for Spring Boot with Docker _(for example, see below)_

#### Without Docker
- MySQL **8.4** or compatible
- `application.properties` _(or just set the application profile to be `dev`)_
- Redis **7.4** or just use `spring.cache.type=simple` in the `application.properties` to use simple cache

#### Configuration File
- Example `.env` file for docker
```properties
MYSQL_DATABASE=cmarket
MYSQL_ROOT_USER=root
MYSQL_ROOT_PASSWORD=root
MYSQL_HOST=mysql
```

- Example `application.properties` file for Spring Boot with Docker
```properties
spring.datasource.url=jdbc:mysql://${MYSQL_HOST}:3306/${MYSQL_DATABASE}
spring.datasource.username=${MYSQL_ROOT_USER}
spring.datasource.password=${MYSQL_ROOT_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

- Example `application.properties` file for Spring Boot without Docker
```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/cmarket
spring.datasource.username=root
spring.datasource.password=root
```

- Additional `properties` import for Github OAuth _(Optional)_
```properties
spring.config.import=optional:classpath:configs/auth/github.properties
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Installations
-  Make sure you are in the root project folder
- Run `openssl genpkey -algorithm RSA -out src/main/resources/keys/jwt/private.key -pkeyopt rsa_keygen_bits:2048` to generate the private key in `classpath/keys/jwt`
- Run `openssl rsa -pubout -in src/main/resources/keys/jwt/private.key -out src/main/resources/keys/jwt/public.pem` to generate the public key in same folder
- Run `./gradlew bootJar` to build the executable `.jar` file
- Run `docker compose up` (_if with docker_) 
- Or execute the generated `.jar` generated in `build/libs/cmarket-1.0.jar`(_without docker_)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


