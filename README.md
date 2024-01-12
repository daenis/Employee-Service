# Employee Service

---

### Environment Configuration

#### TLS

For security purposes, the RSA key is not included with version control. Instead, developers must create and manage their own key file. It is recommended to place this key within the *src/main/resources/certs* directory, as Git will ignore this by default.

- Navigate to the project directory and create a directory to hold a self signed certificate

  ```
  >> mkdir src/main/resources/certs && cd src/main/resources/certs
  ```

- Create a self signed certificate

  ```
  >> keytool -genkeypair -alias app-name -keyalg RSA -keysize 4096 -storetype
  PKCS12 -keystore app-name.p12 -validity 3650 -storepass changeit
  ```

- Register the certificate locally

  ```
  >> keytool -export -keystore app-name.p12 -alias app-name -file app-name.crt
  ```

  - Enter the keystore password, *changeit*

  ```
  >> keytool -importcert -file app-name.crt -alias app-name -keystore "C:\Program Files\Java\jdk-version\lib\security\cacerts"
  ```

  - Make sure correct Java version and location are referenced
  - Enter the Java certificate manager password, which is *changeit* by default



#### Database Connection

- Create a Postgres database named *employee-service*

- Run the following baseline script:

  ```sql
  CREATE TABLE job_titles (
      id SERIAL PRIMARY KEY,
      name VARCHAR(255)
  );
  
  CREATE TABLE employees (
      id SERIAL PRIMARY KEY,
      first_name VARCHAR(255),
      last_name VARCHAR(255),
      salary DECIMAL(10, 2),
      job_title_id INT,
      FOREIGN KEY (job_title_id) REFERENCES job_titles(id)
  );
  
  INSERT INTO job_titles (name) VALUES ('Software Engineer');
  INSERT INTO employees (first_name, last_name, salary, job_title_id) VALUES ('Jon', 'Doe', 150000.00, 1);
  ```



---

### Startup Arguments

```
-Dkeystore-path="classpath:certs/app-name.p12"
-Dkeystore-password="changeit"
-Dkeystore-type="pkcs12"
-Dkeystore-alias="app-name"
-Ddatabase-username="postgres"
-Ddatabase-password="changeit"
```

- Supplied as VM options in IntelliJ