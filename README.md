# Spring Boot Flight Search API

## Açıklama

Bu proje, bir uçuş arama uygulamasının backend API'sini geliştirmeyi amaçlamaktadır.

### Data Modeling

Veritabanında saklanacak bilgilerin tasarlanması ve modellenmesi gerekiyor. 

- Flight
    - ID
    - Departure Date
    - Return Date
    - Departure Airport
    - Arrival Airport
    - Price
    - Return Flight
- Airport
    - ID
    - CityName
- User
    - ID
    - Account Expired
    - Account Locked
    - Name
    - Password
    - Email
    - Username
    - Token
    - Is Enabled
- Token
    - Id
    - Expired
    - Revoked
    - Usertoken
    - User_id

Veritabanı olarak herhangi bir relational (SQL) ya da NoSQL database kullanılabilir.

### CRUD Yapısı

CRUD (Create, Read, Update, Delete) yapısı, bir veritabanı üzerinde en yaygın olarak gerçekleştirilen temel işlemleri temsil eder. Bu yapının kurgulanması, verilerin tutarlı ve organize bir şekilde yönetilmesini sağlar. CRUD yapısının uygulanacağı kaynaklar:

- Uçuşlar
- Havaalanları

### Search API

Verilen kalkış yeri, varış yeri, kalkış tarihi ve dönüş tarihine uygun uçuşları listeleyen bir API endpoint yapılmalı. Dönüş tarihi verilmediyse tek yönlü uçuş, verildiyse çift yönlü uçuştur. Tek yönlü uçuş için tek uçuş bilgisi, çift yönlü uçuş için iki uçuş bilgisi verilmeli.

### REST ile Dışarıya Servis Sunulmalı

REST mimarisi ile dışarıya servis sunmak, uygulamanın diğer sistemlerle etkileşim kurabilmesini sağlar. Bu, veri alışverişini kolaylaştırır ve genel olarak sistemler arası entegrasyonu mümkün kılar.

### Java Kullanılmalı (Spring/Spring Boot vb.)

### Authentication Yapısı

Authentication yapısı, kullanıcının kimliğini doğrulamak ve yetki vermek için kullanılır. Bu yapı sistemlerin daha güvenli olmasını sağlar. İstenilen authentication mimarisi kullanılabilir.

### Scheduled Background Jobları

Her gün third-party bir API’a istek atarak uçuş bilgilerini alan ve database’e kaydeden bir scheduled job yapılmalı. Gerçek bir third-party API kullanılmayacak. Mock bir API isteği yapılıp yapay veri üretilerek çalışılabilir.

### Git Versiyon Sistemi

Git versiyon sistemi kullanılmalı ve proje GitHub’a yüklenmelidir.

### Dokümantasyon

Swagger ile API dokümantasyonu yapılmalı.

---

### Kurulum

1. Repository'yi klonlayın:
   ```bash
   git clone https://github.com/eraykisabacak/Spring-Boot-Flight-Search-API
   ```
2. Proje dizinine gidin:
    ```bash
    cd spring-boot-flight-search-api
    ```
3. Docker Compose kullanarak MySQL veri tabanını oluşturun.
    ```bash
        docker-compose up -d
     ```
4. Spring Boot uygulamasını başlatın:
    ```bash
    ./mvnw spring-boot:run
    ```
   
----
## Kullanım

### Auth/Register

**Endpoint:** `/auth/register` (POST)

**Açıklama:** Kullanıcı kaydı oluşturur ve RabbitMQ ile welcome emaili gönderilir.

**Parametreler:**
- `username` (zorunlu)
- `password` (zorunlu)
- `name` (zorunlu)
- `authorities` (zorunlu)

### Auth/Login

**Endpoint:** `/auth/login` (POST)

**Açıklama:** Kullanıcı girişi yapar.

**Parametreler:**
- `username` (zorunlu)
- `password` (zorunlu)

----------

### Flight

**Endpoint:** `/flight` (GET)

**Açıklama:** Bütün uçuşları görebilirsiniz.

**Authorization:** Bearer *****

### Flight

**Endpoint:** `/flight/{id}` (GET)

**Açıklama:** Verilen id'nin uçuş bilgilerini gösterir.

**Authorization:** Bearer *****

### Flight

**Endpoint:** `/flight` (POST)

**Açıklama:** Yeni bir uçuş eklenir.

**Authorization:** Bearer *****

**Parametreler:**
- `arrivalAirportId` (zorunlu)
- `departureAirportId` (zorunlu)
- `price` (zorunlu)
- `departureDate` (zorunlu)
- `returnDate`

### Flight

**Endpoint:** `/flight/{id}` (DELETE)

**Açıklama:** Verilen id'nin uçuşunu siler.

**Authorization:** Bearer *****

### Flight

**Endpoint:** `/flight/{id}` (PUT)

**Açıklama:** Uçuş güncellenir.

**Authorization:** Bearer *****

**Parametreler:**
- `arrivalAirportId` 
- `departureAirportId` 
- `price` 
- `departureDate` 
- `returnDate` 

### Flight

**Endpoint:** `/flight/search` (POST)

**Açıklama:** Verilen parametrelere göre arama işlemi gerçekleştirir.

**Authorization:** Bearer *****

**Parametreler:**
- `arrivalAirportId` (zorunlu)
- `departureAirportId` (zorunlu)
- `departureDate` (zorunlu)
- `returnDate`

-------

### Airport

**Endpoint:** `/airport` (GET)

**Açıklama:** Bütün havaalanlarını gösterir.

**Authorization:** Bearer *****

### Airport

**Endpoint:** `/airport/{id}` (GET)

**Açıklama:** Verilen id'nin havaalanı bilgilerini gösterir.

**Authorization:** Bearer *****

### Airport

**Endpoint:** `/airport` (POST)

**Açıklama:** Yeni bir havaalanı eklenir.

**Authorization:** Bearer *****

**Parametreler:**
- `cityName` (zorunlu)

### Airport

**Endpoint:** `/airport/{id}` (DELETE)

**Açıklama:** Verilen id'nin havaalanını siler.

**Authorization:** Bearer *****

### Airport

**Endpoint:** `/airport/{id}` (PUT)

**Açıklama:** Havaalanı güncellenir.

**Authorization:** Bearer *****

**Parametreler:**
- `cityName`

-------

### Swagger UI

**Endpoint:** `/swagger-ui/index.html` (GET)

**Açıklama:** Swagger UI gösterir.