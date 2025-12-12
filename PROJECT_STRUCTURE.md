# ITSS-NIHONGO Project Structure

## 📁 Complete Project Architecture

```
ITSS2025.1/
├── 📁 .git/                           # Git version control
├── 📁 .idea/                          # IntelliJ IDEA configuration
├── 📁 .mvn/wrapper/                   # Maven wrapper files
├── 📁 src/                            # Source code directory
│   ├── 📁 main/                       # Main application code
│   │   ├── 📁 java/com/ITSS/ITSS_NIHONGO/
│   │   │   ├── 📁 config/             # Configuration classes
│   │   │   │   ├── 🔧 EncoderConfig.java
│   │   │   │   ├── 🔐 JwtAuthFilter.java
│   │   │   │   ├── 🔐 JwtService.java
│   │   │   │   └── 🔐 SecurityConfig.java
│   │   │   │
│   │   │   ├── 📁 controller/         # REST API Controllers
│   │   │   │   ├── 👤 AccountController.java
│   │   │   │   ├── 🍜 DishController.java
│   │   │   │   ├── 🏪 DishRestaurantController.java
│   │   │   │   ├── ⭐ DishReviewController.java
│   │   │   │   ├── ❤️ FavouriteController.java
│   │   │   │   ├── 🏢 RestaurantController.java
│   │   │   │   └── ⭐ RestaurantReviewController.java
│   │   │   │
│   │   │   ├── 📁 dto/                # Data Transfer Objects
│   │   │   │   ├── 📁 request/        # Request DTOs
│   │   │   │   │   ├── 📁 DishReview/
│   │   │   │   │   ├── 📁 Favourite/
│   │   │   │   │   │   └── AddFavourite.java
│   │   │   │   │   ├── 📁 RestaurantReview/
│   │   │   │   │   └── 📁 User/
│   │   │   │   │       ├── LoginRequest.java
│   │   │   │   │       ├── RegisterRequest.java
│   │   │   │   │       ├── UpdatePassword.java
│   │   │   │   │       └── UpdateProfile.java
│   │   │   │   │
│   │   │   │   └── 📁 response/       # Response DTOs
│   │   │   │       ├── 📁 Dishes/
│   │   │   │       ├── 📁 DishRestaurant/
│   │   │   │       ├── 📁 DishReview/
│   │   │   │       ├── 📁 Favourite/
│   │   │   │       │   └── FavouriteResponse.java
│   │   │   │       ├── 📁 Restaurant/
│   │   │   │       │   └── RestaurantResponse.java
│   │   │   │       ├── 📁 RestaurantReview/
│   │   │   │       └── 📁 User/
│   │   │   │           └── Profile.java
│   │   │   │
│   │   │   ├── 📁 Iservice/           # Service Interfaces
│   │   │   │   ├── IDishesService.java
│   │   │   │   ├── IDishRestaurant.java
│   │   │   │   ├── IDishReviewServer.java
│   │   │   │   ├── IFavourite.java
│   │   │   │   ├── IRestaurant.java
│   │   │   │   └── IRestaurantReview.java
│   │   │   │
│   │   │   ├── 📁 model/              # JPA Entity Models
│   │   │   │   ├── 🍜 Dishes.java
│   │   │   │   ├── 🏪 DishRestaurant.java
│   │   │   │   ├── ⭐ DishReview.java
│   │   │   │   ├── ❤️ Favourite.java
│   │   │   │   ├── 🏢 Restaurant.java
│   │   │   │   ├── ⭐ RestaurantReview.java
│   │   │   │   └── 👤 Users.java
│   │   │   │
│   │   │   ├── 📁 repository/         # Data Access Layer
│   │   │   │   ├── DishRepository.java
│   │   │   │   ├── DishRestaurantRepository.java
│   │   │   │   ├── DishReviewRepository.java
│   │   │   │   ├── FavouriteRepository.java
│   │   │   │   ├── RestaurantRepository.java
│   │   │   │   ├── RestaurantReviewRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   │
│   │   │   ├── 📁 service/            # Business Logic Layer
│   │   │   │   ├── DishRestaurantService.java
│   │   │   │   ├── DishReviewService.java
│   │   │   │   ├── DishService.java
│   │   │   │   ├── FavouriteService.java
│   │   │   │   ├── RestaurantReviewService.java
│   │   │   │   ├── RestaurantService.java
│   │   │   │   └── UserService.java
│   │   │   │
│   │   │   └── 🚀 ItssNihongoApplication.java  # Main Application Class
│   │   │
│   │   └── 📁 resources/              # Configuration Resources
│   │       └── ⚙️ application.properties
│   │
│   └── 📁 test/                       # Test code directory
│       └── 📁 java/com/ITSS/ITSS_NIHONGO/
│           └── ItssNihongoApplicationTests.java
│
├── 📁 target/                         # Compiled classes (generated)
├── 📄 .gitattributes                 # Git attributes
├── 📄 .gitignore                     # Git ignore rules
├── 📄 mvnw                           # Maven wrapper (Unix)
├── 📄 mvnw.cmd                       # Maven wrapper (Windows)
├── 📄 pom.xml                        # Maven configuration
├── 📄 README.md                      # Project documentation
│
└── 📄 Generated Documentation Files:
    ├── API_DOCUMENTATION_FOR_FRONTEND.md
    ├── EXISTING_FAVORITES_API_SPECIFICATION.md
    └── TECHNICAL_REPORT.md
```

---

## 🏗️ Architecture Layers

### 1. **Configuration Layer** (`/config/`)
```java
🔐 SecurityConfig.java      // Spring Security + CORS setup
🔐 JwtService.java         // JWT token generation/validation
🔐 JwtAuthFilter.java      // Authentication filter
🔧 EncoderConfig.java      // Password encoding configuration
```

### 2. **Controller Layer** (`/controller/`) - REST API Endpoints
```java
👤 AccountController.java       // /auth/login, /auth/register, /me
🏢 RestaurantController.java    // /restaurant-recently, /restaurant-detail
🍜 DishController.java          // /disharmonious, /disharmonious-all
❤️ FavouriteController.java     // /favourite-top3, /favourites, /favourite
⭐ DishReviewController.java    // Dish rating/review endpoints
⭐ RestaurantReviewController.java // Restaurant rating/review endpoints
🏪 DishRestaurantController.java // Dish-restaurant relationship endpoints
```

### 3. **Service Layer** (`/service/` + `/Iservice/`) - Business Logic
```java
// Interface contracts in /Iservice/
IFavourite.java           // Favorites business logic interface
IRestaurant.java          // Restaurant business logic interface
IDishesService.java       // Dish business logic interface

// Implementations in /service/
FavouriteService.java     // Favorites management logic
RestaurantService.java    // Restaurant discovery + rating calculation
DishService.java          // Dish recommendations + sorting
UserService.java          // User management + authentication
```

### 4. **Repository Layer** (`/repository/`) - Data Access
```java
FavouriteRepository.java     // Custom queries for favorites
RestaurantRepository.java    // Distance-based restaurant queries
DishRepository.java          // Rating-based dish queries
UserRepository.java          // User authentication queries
```

### 5. **Model Layer** (`/model/`) - Database Entities
```java
👤 Users.java              // User accounts
🏢 Restaurant.java         // Restaurant information
🍜 Dishes.java             // Food items
❤️ Favourite.java          // User favorites (dish+restaurant pairs)
🏪 DishRestaurant.java     // Many-to-many with pricing
⭐ RestaurantReview.java   // Restaurant ratings/comments
⭐ DishReview.java         // Dish ratings/comments
```

### 6. **DTO Layer** (`/dto/`) - API Contracts
```java
// Request DTOs (/dto/request/)
📥 AddFavourite.java       // { dishId, restaurantId }
📥 LoginRequest.java       // { username, password }
📥 RegisterRequest.java    // { name, username, password, national }

// Response DTOs (/dto/response/)
📤 FavouriteResponse.java  // Favorite item data
📤 RestaurantResponse.java // Restaurant data with calculated ratings
📤 Profile.java            // User profile data
```

---

## 🔄 Data Flow Architecture

```
HTTP Request → Controller → Service → Repository → Database
     ↓              ↓         ↓          ↓
JWT Filter → Business Logic → JPA Query → PostgreSQL
     ↓              ↓         ↓          ↓
Response ← DTO Mapping ← Entity Mapping ← Result Set
```

---

## 🗄️ Database Schema Relationships

```
Users (1) ──────────── (M) Favourites ──────────── (1) Restaurant
  │                         │                           │
  │ (1)                     │ (1)                       │ (M)
  │                         │                           │
  └── (M) Reviews           └── (1) Dishes ────────── (M) DishRestaurant
      │                                                   │
      ├── RestaurantReview                               │
      └── DishReview                                     │
                                                         │
                                              Restaurant (M) ──┘
```

---

## 📡 API Endpoint Mapping

### Authentication & User Management
```
POST   /auth/login          → AccountController.login()
POST   /auth/register       → AccountController.register()
GET    /me                  → AccountController.getCurrentUser()
PUT    /updatePassword      → AccountController.updatePassword()
PUT    /updateUser          → AccountController.updateUser()
```

### Restaurant Discovery
```
GET    /restaurant-recently → RestaurantController.getRestaurantRecently()
GET    /restaurant-detail   → RestaurantController.getRestaurantDetail()
```

### Dish Management
```
GET    /disharmonious       → DishController.getDishFamousList()
GET    /disharmonious-all   → DishController.getAllDishFamousList()
```

### Favorites Management
```
GET    /favourite-top3      → FavouriteController.get3Favourite()
GET    /favourites          → FavouriteController.getAllFavourite()
POST   /favourite           → FavouriteController.addFavourite()
DELETE /favourite           → FavouriteController.deleteFavouriteById()
```

---

## 🔧 Configuration Files

### Maven Configuration (`pom.xml`)
```xml
Dependencies:
├── Spring Boot 4.0.0 (Web, Security, Data JPA)
├── PostgreSQL Driver
├── JWT Library (io.jsonwebtoken 0.12.6)
├── Lombok (Code generation)
└── Spring Boot DevTools
```

### Application Configuration (`application.properties`)
```properties
🗄️ Database: PostgreSQL @ 14.162.146.23:5432/ITSS
🔐 JWT Secret: b6a4f2319dbde50cf2a1e476ec9f77e3d8c22148790c4e537103c0f77e3a6c42
⏰ JWT Expiration: 14 days (1209600000 ms)
🌐 Server Port: 8081
📊 JPA: Auto-update schema, SQL logging enabled
```

---

## 🎯 Key Business Domains

### 1. **User Management**
- Registration/Login with JWT authentication
- Profile management (name, nationality, avatar)
- Password updates with validation

### 2. **Restaurant Discovery**
- Location-based filtering (within 1000m)
- Pagination support for large datasets
- Real-time rating calculation from reviews

### 3. **Dish Recommendations**
- Rating-based sorting (highest first)
- Top 3 popular dishes endpoint
- Complete dish catalog with descriptions

### 4. **Favorites System**
- **Unique Feature**: Dish+Restaurant combination favorites
- Top 3 recent favorites with restaurant context
- Complete favorites list with dish details
- Duplicate prevention logic

### 5. **Review System**
- Separate reviews for restaurants and dishes
- Rating aggregation for recommendations
- User-generated content with timestamps

---

## 🚀 Deployment Architecture

```
Application Layer:
├── Spring Boot JAR (Port 8081)
├── Embedded Tomcat Server
└── JWT Stateless Authentication

Database Layer:
├── External PostgreSQL (14.162.146.23:5432)
├── HikariCP Connection Pool
└── Hibernate ORM with Auto-DDL

Security Layer:
├── JWT Token Validation
├── BCrypt Password Hashing
├── CORS Configuration (All origins)
└── Stateless Session Management
```

---

## 📊 Project Statistics

```
📁 Total Packages: 8 main packages
📄 Java Files: 56 source files
🎯 Controllers: 7 REST controllers
🔧 Services: 7 business services + 6 interfaces
🗄️ Repositories: 7 data repositories
📦 Models: 7 JPA entities
📋 DTOs: 15+ request/response objects
🔐 Security: 4 configuration classes
```

This structure follows **Spring Boot best practices** with clear separation of concerns, proper layering, and comprehensive API coverage for a Japanese restaurant discovery platform.