
#### Notes:

<br>

1.  Prefer construction injection with lombok's ```@RequiredArgsConstructor``` over  field injection with spring's  ```@Autowired```



[What is Spring Framework ? An Unorthodox Guide ](https://www.marcobehler.com/guides/spring-framework):

In "Constructor Injection & Autowired Revisited" Section
 
  With newer Spring versions, Spring is actually smart enough to
  inject these dependencies without an explicit @Autowired annotation in the constructor.
  So this would also work.

  ```java

     @Component
     public class UserDao {

         private DataSource dataSource;

         public UserDao(DataSource dataSource) {
             this.dataSource = dataSource;
         }

     }
```
             

 [Stackoverflow: Constructor injection, Setter injection and Injection by reflection (autowired)](https://stackoverflow.com/a/39892204)    

 [Stackoverflow: Spring Autowire on properties vs constructor](https://stackoverflow.com/a/40620318)

 [Why field injection is evil?](https://odrotbohm.de/2013/11/why-field-injection-is-evil/)


 Summary: <br><br>
              1. Construction injection allows you to create dependencies as final <br>
              2. Supports immutable objects <br>
              3. Makes setting up POJOs for unit-tests easier

<br>



2. DTO vs Entities:  "Entity should be decoupled from its corresponding request / response payload model"

                     The key reason for this is that the request / response payload may 
                     may need to present properties differently which can 
                     be constructed from an entity object.

                     In this project BookmarksDTO and Bookmark are not same, the DTO presents additional
                     meta data for paging.

                     So data-model and data-transfer model are related but serve different purposes, and 
                     depending on requirements the properties can look different. 
 
                     Using separate data types / classes decouples them and gives flexibility 

                     Also, reading all columns from a table for an entity to return / present only a
                     small subset of them may not be performant.

                     This discussion is entry point for Spring DTO projections, which spring provides to
                     handle concerns discussed above


<br>                     

3.  Spring Testing
<br>
<br>
 *  The `spring-boot-starter-test`  “Starter” (in the test scope) contains the following provided libraries:
<br><br>

| library                        | remark                                                                  |
|--------------------------------|-------------------------------------------------------------------------|
| JUnit 5                        | The de-facto standard for unit testing Java applications                |
| Spring Test & Spring Boot Test | Utilities and integration test support for Spring Boot applications.    |
| AssertJ                        | A fluent assertion library.                                             |
| Hamcrest                       | A library of matcher objects (also known as constraints or predicates). |
| Mockito                        | A Java mocking framework.                                               |
| JSONassert                     | An assertion library for JSON.                                          |
| JsonPath                       | XPath for JSON.                                                         |

<br>

 *  "You don't need Spring to <em>UNIT TEST</em>" (think @SpringBootTest / @ExtendWith(SpringExtension.class) )
     if you "Create Testable Spring Bean"

    [reflectoring.io/unit-testing-spring-boot](https://reflectoring.io/unit-testing-spring-boot/)

 <br>

 * "The <em>@TestConfiguration</em> annotation is a useful aid for writing unit tests of 
    components in a Spring Boot application. 

    It allows us to define additional beans
    or override existing beans in the Spring application context to add specialized
    configurations for testing." 
    
    <br>[reflectoring.io/spring-boot-testconfiguration](https://reflectoring.io/spring-boot-testconfiguration/)

    [Quirks of @TestConfiguration | Siva Labs ](https://www.sivalabs.in/quirks-of-spring-testconfiguration/)

 <br>

  * "Use Test slices to prevent loading the entire application context and to speedup your tests"

     <br>[reflectoring.io/spring-boot-test/](https://reflectoring.io/spring-boot-test/)

 <br>

  * More Annotations to explore / practice: 
    * ```@PropertySource, @Import, @ActiveProfiles```
    * ```@Configuration(<em>proxyBeanMethod=false</em>), @Value```
    * ```@Mock vs @MockBean vs @InjectMock``` 
        
      <br><b>Note</b>: many annotations can be customized with attributes, this can be handy<br> 
                       for more granularity, better control and faster tests<br>
                 <br>  ex: 
    ```@SpringBootTest(properties = { "example.firstProperty=annotation" })```
                        <br>
    ```@Configuration(<em>proxyBeanMethod=false</em>), @Value ```
     <br><br>
  * Also Refer:
    <br><br>
    [Spring Boot Tips : Part 4 - How to write Unit, Slice & Integration Tests in SpringBoot Applications](https://www.youtube.com/watch?v=NzMIKpYpiZ4)
    <br>
    [Spring Boot Tips : Part 5 - Integration Testing using Testcontainers](https://www.youtube.com/watch?v=osw9dz2ZhhQ&list=PLuNxlOYbv61jFFX2ARQKnBgkMF6DvEEic&index=5)
    <br>
    [Spring Boot Testing Web Layer Guide](https://spring.io/guides/gs/testing-web/)
    <br><br>


4. Pom libraries: 
 
| s.no | artefactId                          | groupId                    | scope   | optional | purpose                                                                                                                             |
|-----|-------------------------------------|----------------------------|---------|----------|-------------------------------------------------------------------------------------------------------------------------------------|
| 1.  | spring-boot-starter-data-jpa        | org.springframework.boot   |         |          | Starter for using Spring Data JPA with Hibernate                                                                                    |     
| 2.  | spring-boot-starter-validation      | org.springframework.boot   |         |          | Bean Validation with Hibernate validator                                                                                            |
| 3.  | spring-boot-starter-web             | org.springframework.boot   |         |          | Build web, including RESTful apps using Spring MVC. Uses Tomcat as default embedded conatiner                                       |
| 4.  | flyway-core                         | org.flywaydb               |         |          | Flyway is an open-source database migration tool that strongly favors simplicity and convention over configuration                  |
| 5.  | spring-boot-dev-tools               | org.spring.framework.boot  | runtime |  true    | Provides fast app restarts, LiveReload and configurations for enhanced developer experience                                         |
| 6.  | h2                                  | com.h2database             | runtime |          |                                                                                                                                     | 
| 7.  | postgresql                          | org.postgresql             | runtime |          |                                                                                                                                     | 
| 8.  | spring-boot-configuration-processor | org.springframework.boot   |         |  true    | Spring Boot Configuration Annotation Processor                                                                                      |
| 9.  | lombok                              | org.projectlombok          |         |  true    | Java annotation library that helps to reduce boilerplate code                                                                       |
| 10. | spring-boot-starter-test            | org.springframework.boot   | test    |          | tarter for testing Spring Boot applications with libraries including </em>JUnit Jupiter, Hamcrest and Mockito</em>                  |  
| 11. | junit-jupiter                       | org.testcontainers         | test    |          | Junit Jupiter Extension (Integration test platform/framework) by Testcontainers (read the blog)                                     |
| 12. | postgresql                          | org.testcontainers         | test    |          | // for loading postgresql container (JDBC support page)                                                                             | 
 | 13. | problem-spring-web                  | org.zalando                |         |          | Problem Spring Web is a set of libraries that makes it easy to produce application/problem+json responses from a Spring application | 

 plugins

| s.no | plugin name       | remarks                           | 
|------|-------------------|-----------------------------------|
| 1.   | spring-boot-maven | build fatjar / build docker image |
| 2 .  | jib               | build docker image                | 

 <br>
 <em>dependency management</em>: 
 <br>
 
 ```xml

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>testcontainers-bom</artifactId>
            <version>${testcontainers.version}</version> // avoid repeating version for each test-container dependancy in pom.xml
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
 ```


<br><br>

5. Ways to create Docker image of spring boot app: 
      ```
       Using Dockefile: 
   
           1. Dockerfile using fat-jar // Dockerfile
           2. Mutlistage Dockerfile with layers // Dockerfile.layered 
   
       Plugins/BuildPacks(without a Dockerfile) make the container for you: 
   
           3. Spring boot maven or gradle plugin using Buildpacks // spring-boot-maven plugin which also uses layering 
           4. Jib Maven / Gradle plugin  
      ```

   Refer:  [Creating Docker Image](https://www.baeldung.com/spring-boot-docker-images#traditional-docker-builds) <br>
           [Reusing Docker Layers](https://www.baeldung.com/docker-layers-spring-boot) for steps 1 and 2 
           [Spring Boot Maven Plugin | Packing OCI Images](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#build-image)
           [What is a BuildPack ?](https://youtu.be/d_L_AZyocWA) for steps 3 and 4
           <br><br>
   
6. Commands to build and run docker containers from <em>Dockerfile</em> and <em>Dockerfile.layered</em>
   ```bash
   
   # generate latest jar file
   bookmarker-app/bookmarker-api $ ./mvnw clean package # -DskipTests
   
   # build
   $ docker build -t bookmarker-dockerfile -f ./Dockerfile  .
   $ docker build -t bookmarker-dockerfile-layered -f ./Dockerfile.layered .
   
   #run
   $ docker run --name bookmarker-api-dockerfile -p 8085:8080 bookmarker-dockerfile
   $ docker run --name bookmarker-api-dockerfile-layered -p 8086:8080 bookmarker-dockerfile-layered
   
   ```
   Command to create docker image using spring boot maven plugin: 
   ```bash
   
     # ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=<DOCKER-HUB-USERNAME>/bookmarker-api 
    
    [/path/to/bookmarker-api]$ ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=aditya0491/bookmarker-api 
   ```  
    
    If  image name is not passed as <em>-Dspring-boot.build-image.imageName</em> in the above command the image
    name `library/project-artefact-id` will be used as image name. Note that the image will be created with 
    `latest` tag
    <br>
    <br>
    If you wish to specify image name in `pom.xml` then
   
    <br>

    ```xml
        
        <build>
                <plugins>
			            <plugin>
				            <groupId>org.springframework.boot</groupId>
				            <artifactId>spring-boot-maven-plugin</artifactId>
				            <configuration>
                                <image>
                                    <name>aditya0491/bookmarker-api</name>
                                </image>
					            <excludes>
						            <exclude>
							            <groupId>org.projectlombok</groupId>
							            <artifactId>lombok</artifactId>
						            </exclude>
					            </excludes>
				           </configuration>
			            </plugin>
                </plugins>
        </build>
    ```

   <br>

Alternatively, you can create docker image using jib plugin, add the plugin to `pom.xml` as shown below

```xml
        <plugin>
				<groupId>com.google.cloud.tools</groupId>
				<artifactId>jib-maven-plugin</artifactId>
				<version>3.2.1</version>
				<configuration>
					<from>
						<image>eclipse-temurin:17-jre-focal</image>
					</from>
					<to>
						<image>aditya0491/bookmarker-api-jib</image>
						<tags>
							<tag>latest</tag>
							<tag>0.0.1</tag>
						</tags>
					</to>
					<container>
						<ports>
							<port>8080</port>
						</ports>
					</container>
				</configuration>
		</plugin>
```

To build the image using jib run

```bash
# builds the image and pushes to dockerhub, so you need to
# login with dockerhub username provided in the image in pom
#
# the following command does not require docker installed
$ ./mvnw jib:build   

# you can override the default image name in pom 
# by provide a custome image name when building via jib
$ ./mvnw jib:build -Dimage=aditya0491/bookmarker-api-jib-customname


# to build the image locally and to skip pushing the image to dockerhub
# 
# the following command requires docker to be installed 
$ ./mvnw jib:dockerBuild

# you can override the default image name in pom 
# by provide a custome image name when building via jib
$ ./mvnw jib:dockerBuild -Dimage=aditya0491/bookmarker-api-jib-customname

```

To run the image as a container

<br>

```bash
  $ docker run -p 8080:8080 aditya0491/bookmarker-api 
```

<br><br>
6. Continuous Integration: 
             
                  1. Run tests                                        }  on build server
                  2. Code Quality Checks (SonarQube, CheckStyle)      }
                  3. Deploy to Dev / QA
                  4. Run E2E tests (Selenium, Cypress e.t.c)
                  5. Deploy to Performance Test Env                   } separate server/environment for perf testing 
                  6. Run perf test using gatling / jmeter             }
                  7. Deploy to production
                  8. Run Smoke / Sanity test
             
    Jenkins is still widely used tool for continuous integration

    For this project, we use `github actions` to:

                1. Run tests
                2. Build docker image and push to docker-hub

    Refer comments in <em>bookmarker-api/.github/workflows/build.yml</em>
   <br><br>

7. Rest API guidelines overview: 
 
   ```
         HTTP METHOD            URL                              FUNCIONALITY
   
     1.    GET               /api/bookmarks                      <- get all
     2.    GET               /api/bookmarks/{id}                 <- get by id
     3.    GET               /api/bookmarks?query=k&page=2       <- search
     4.    POST              /api/bookmarks                      <- create
     5.    *PUT              /api/bookmarks/{id}                 <- replace by id 
     6.    *PATCH            /api/bookmarks/{id}                 <- partial update by id
     7.    DELETE            /api/bookmarks/{id}                 <- delete by id
   ```


8. Spring DATA JPA Projections: 

              More often than not, we don't need all the properties of the returned object 

              In such cases, we might want to retrieve data as objects of customized types.
              These types reflect partial views of the root class, containing only the properties we care about.
              This is where projections come in handy.

              https://www.baeldung.com/spring-data-jpa-projections


9. Docker Compose:

   <em>
    Compose is a tool for defining and running multi-container Docker applications. With Compose, you use
    a YAML file to configure your application’s services. Then, with a single command, you create and start
    all the services from your configuration
   </em>

    <b>Note</b>: You can even use <em> docker compose </em> instead of installing <em>docker-compose</em> 
   ```bash
      
      # 1. (by default) consult docker-compose.yml configuration(s) file,
      # 2. create container images
      # 3. run containers
      $ docker-compose up      
   
      # run in detached mode / in the background
      $ docker-compose up -d   
      
      # to see the logs when you bring up containers 
      # in detached mode (prev command), use the command below
      # it is similar to tail command
      $ docker-compose logs -f 
      
      
      # 1. consult container-configs.yml (instead of default docker-compose.yml),
      # 2. create container images
      # 3. run containers                         
      $ docker-compose -f /path/to/container-services-configs.yml up -d
      # and for logs
      $ docker-compose -f /path/to/container-services-configs.yml logs -f 
     
   
      # Note: if you have your services configured in two files you can 
      #       refer both of them in one command
   
      $ docker-compose -f /path/to/container-services-configs-1.yml  
                        -f /path/to/container-services-configs-2.yml up -d   
      
      # follow the logs 
      $ docker-compose -f /path/to/container-services-configs-1.yml  
                        -f /path/to/container-services-configs-2.yml logs -f   
      
   
      # stop all the containers
       $ docker-compose -f /path/to/container-services-configs-1.yml  
                        -f /path/to/container-services-configs-2.yml stop
      
      
      # remove all the containers
      # -f, --force  Don't ask to confirm removal
      $ docker-compose -f /path/to/container-services-configs-1.yml  
                       -f /path/to/container-services-configs-2.yml rm -f   
      

   
      # even when you stop and remove the containers
      # the built images remain, so if you wish to 
      # rebuild the images after changing source code
      # use --build option (also observe the logs of command on terminal)
      #
      # --build :  Build images before starting containers.
      # 
      # also remember to generate new jar file on source code change
      bookmarker-app/bookmarker-api $ ./mvnw clean package # -DskipTests
   
      $ docker-compose up -f /path/to/cotainer-configs.yml up -d --build 
   
   ```

10.  Use `run.sh` to run the bookmarker-api backend code in docker containers. 
     The above `docker-compose` commands are meant for learning and understanding.

<br><br>

#### References:
<br>

1. [What is Spring Framework ? An Unorthodox guide](https://www.marcobehler.com/guides/spring-framework)
   <br><br>
2. Spring Testing:

   * [reflectoring.io/unit-testing-spring-boot](https://reflectoring.io/unit-testing-spring-boot/)<br>
   * [reflectoring.io/spring-boot-test/](https://reflectoring.io/spring-boot-test/)<br>
   * [reflectoring.io/spring-boot-testconfiguration](https://reflectoring.io/spring-boot-testconfiguration/)<br>
   * [Quirks of @TestConfiguration | Siva Labs ](https://www.sivalabs.in/quirks-of-spring-testconfiguration/)<br>
   * [Spring Boot Tips : Part 4 - How to write Unit, Slice & Integration Tests in SpringBoot Applications](https://www.youtube.com/watch?v=NzMIKpYpiZ4)<br>
   * [Spring Boot Tips : Part 5 - Integration Testing using Testcontainers](https://www.youtube.com/watch?v=osw9dz2ZhhQ&list=PLuNxlOYbv61jFFX2ARQKnBgkMF6DvEEic&index=5)
   * [Spring Boot Testing Web Layer Guide](https://spring.io/guides/gs/testing-web/)
<br><br>
   
3. [Spring Boot Tips](https://www.youtube.com/playlist?list=PLuNxlOYbv61jFFX2ARQKnBgkMF6DvEEic)

4. [Spring boot + k8s](https://www.youtube.com/playlist?list=PLuNxlOYbv61h66_QlcjCEkVAj6RdeplJJ)

<br><br>

5. [Best way to log SQL statements with Spring Boot](https://vladmihalcea.com/log-sql-spring-boot/)

6. [Spring Data JPA](https://stackoverflow.com/questions/42135114/how-does-spring-jpa-hibernate-ddl-auto-property-exactly-work-in-spring)

7. [Spring Data JPA Projection documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections):
   interface-based, class-based, dynamic projections

8. For hibernate related references refer to end of [Spring Boot + Kubernetes Tutorial Series - Part 5 : Using Spring Data JPA DTO Projections
   ](https://www.youtube.com/watch?v=SMn-YezGkdA&list=PLuNxlOYbv61h66_QlcjCEkVAj6RdeplJJ&index=6)
<br>
9. [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)
<br>
10. [Markdown Extended Syntax](https://www.markdownguide.org/extended-syntax/)

