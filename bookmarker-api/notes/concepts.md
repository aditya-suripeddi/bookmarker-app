
#### Notes:

<br>

1.  Prefer construction injection with lombok's ```@RequiredArgsConstructor``` over  field injection with spring's  ```@Autowired```

    [stackoverflow: constructor injection, setter injection and injection by reflection (autowired)](https://stackoverflow.com/a/39892204)    

    [stackoverflow: spring autowire on properties vs constructor](https://stackoverflow.com/a/40620318)

    [why field injection is evil?](https://odrotbohm.de/2013/11/why-field-injection-is-evil/)


    summary:
              1. Construction injection allows you to create dependencies as final <br>
              2. Supports immutable objects
              3. Makes setting up POJOs for unit-tests easier

<br>



2. DTO vs Entities:  Entity should be decoupled from its corresponding request / response payload model

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

                     

                      

3. Pom libraries: 
 
| s.no | artefactId                          | groupId                   | scope   | optional |
|------|-------------------------------------|---------------------------|---------|----------|
| 1.   | spring-boot-starter-data-jpa        | org.springframework.boot  |         |          |              
| 2.   | spring-boot-starter-validation      | org.springframework.boot  |         |          |
| 3.   | spring-boot-starter-web             | org.springframework.boot  |         |          |
| 4.   | flyway-core                         | org.flywaydb              |         |          |           
| 5.   | spring-boot-dev-tools               | org.spring.framework.boot | runtime |  true    |        
| 6.   | h2                                  | com.h2database            | runtime |          |  
| 7.   | postgresql                          | org.postgresql            | runtime |          |  
| 8.   | spring-boot-configuration-processor | org.springframework.boot  |         |  true    |
| 9.   | lombok                              | org.projectlombok         |         |  true    |
| 10.  | spring-boot-starter-test            | org.springframework.boot  | test    |          |
| 11.  | junit-jupiter                       | org.testcontainers        | test    |          |
| 12.  | postgresql                          | org.testcontainers        | test    |          |


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


<br>

4. `spring-boot-starter-test` pulls common dependencies for testing like ```junit5, mockito, json-path``` transitively


<br>

5. Ways to create Docker image of spring boot app: 
      ```
           1. Dockerfile using fat-jar
           2. Mutlistage Dockerfile with layers
           3. Spring boot maven / gradle plugin using Buildpacks
           4. Jib Maven / Gradle plugin 
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


6. [Spring Boot Testing Web Layer Guide](https://spring.io/guides/gs/testing-web/)

   [Spring Boot Tips : Part 4 - How to write Unit, Slice & Integration Tests in SpringBoot Applications](https://www.youtube.com/watch?v=NzMIKpYpiZ4)

   [Spring Boot Tips : Part 5 - Integration Testing using Testcontainers](https://www.youtube.com/watch?v=osw9dz2ZhhQ&list=PLuNxlOYbv61jFFX2ARQKnBgkMF6DvEEic&index=5)


7. Continuous Integration: 
             
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



#### References:

1. [Markdown Extended Syntax](https://www.markdownguide.org/extended-syntax/)

2. [Best way to log SQL statements with Spring Boot](https://vladmihalcea.com/log-sql-spring-boot/)

3. [Spring Data JPA](https://stackoverflow.com/questions/42135114/how-does-spring-jpa-hibernate-ddl-auto-property-exactly-work-in-spring)

4. [Spring Data JPA Projection documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections):
     interface-based, class-based, dynamic projections

5. For hibernate related references refer to end of [Spring Boot + Kubernetes Tutorial Series - Part 5 : Using Spring Data JPA DTO Projections
   ](https://www.youtube.com/watch?v=SMn-YezGkdA&list=PLuNxlOYbv61h66_QlcjCEkVAj6RdeplJJ&index=6)

6. [Spring Boot Tips : Part 4 - How to write Unit, Slice & Integration Tests in SpringBoot Applications](https://www.youtube.com/watch?v=NzMIKpYpiZ4)
   [Spring Boot Tips : Part 5 - Integration Testing using Testcontainers](https://www.youtube.com/watch?v=osw9dz2ZhhQ&list=PLuNxlOYbv61jFFX2ARQKnBgkMF6DvEEic&index=5)

7. [Spring Boot Tips](https://www.youtube.com/playlist?list=PLuNxlOYbv61jFFX2ARQKnBgkMF6DvEEic)

8. [Spring boot + k8s](https://www.youtube.com/playlist?list=PLuNxlOYbv61h66_QlcjCEkVAj6RdeplJJ)

9. [Spring Boot Testing Web Layer Guide](https://spring.io/guides/gs/testing-web/)