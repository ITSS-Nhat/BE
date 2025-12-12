# ITSS-NIHONGO API Documentation for Frontend Developers

**Base URL**: `http://localhost:8081`  
**Authentication**: JWT Bearer Token  
**Content-Type**: `application/json`

---

## Quick Start Guide

### 1. Authentication Flow
```javascript
// 1. Register new user
POST /auth/register
// 2. Login to get JWT token
POST /auth/login
// 3. Use token in all subsequent requests
Authorization: Bearer <your-jwt-token>
```

### 2. Test the API
```bash
# Health check
curl http://localhost:8081/actuator/health

# Register user
curl -X POST http://localhost:8081/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","username":"john@example.com","password":"password123","national":"USA"}'
```

---

## Authentication Endpoints

### Register User
```http
POST /auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "username": "john@example.com", 
  "password": "password123",
  "national": "USA"
}
```

**Response:**
```json
{
  "message": "Registration successful"
}
```

**Error Response:**
```json
{
  "message": "Username already exists"
}
```

### Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "john@example.com",
  "password": "password123"
}
```

**Success Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "message": "Login successful"
}
```

**Error Response:**
```json
{
  "error": "Login failed"
}
```

---

## User Management Endpoints

### Get Current User Profile
```http
GET /me
Authorization: Bearer <jwt-token>
```

**Response:**
```json
{
  "data": {
    "name": "John Doe",
    "national": "USA",
    "email": "john@example.com",
    "avatar": "https://example.com/avatar.jpg"
  }
}
```

### Update User Profile
```http
PUT /updateUser
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "fullname": "John Smith",
  "email": "johnsmith@example.com",
  "avatar": "https://example.com/new-avatar.jpg",
  "nationality": "Canada"
}
```

**Response:**
```json
{
  "message": "User information updated successfully"
}
```

### Update Password
```http
PUT /updatePassword
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "oldPassword": "password123",
  "newPassword": "newpassword456"
}
```

**Response:**
```json
{
  "message": "Password updated successfully"
}
```

---

## Restaurant Endpoints

### Get Nearby Restaurants (Paginated)
```http
GET /restaurant-recently?page=1&size=10
Authorization: Bearer <jwt-token>
```

**Query Parameters:**
- `page` (optional): Page number, default = 1
- `size` (optional): Items per page, default = 10

**Response:**
```json
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "name": "Sakura Sushi",
      "imageUrl": "https://example.com/restaurant1.jpg",
      "distance": 250
    },
    {
      "id": 2,
      "name": "Ramen Ichiban",
      "imageUrl": "https://example.com/restaurant2.jpg", 
      "distance": 500
    }
  ],
  "currentPage": 0,
  "totalItems": 25,
  "totalPages": 3
}
```

### Get Restaurant Details
```http
GET /restaurant-detail?id=1
Authorization: Bearer <jwt-token>
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": 1,
    "name": "Sakura Sushi",
    "distance": 250,
    "minprice": 1000,
    "maxprice": 5000,
    "imageUrl": "https://example.com/restaurant1.jpg",
    "address": "123 Tokyo Street, Shibuya",
    "phone": "+81-3-1234-5678",
    "description": "Authentic Japanese sushi restaurant",
    "rate": 4.5,
    "openTime": "11:00",
    "closeTime": "22:00"
  }
}
```

---

## Dish Endpoints

### Get Top 3 Popular Dishes
```http
GET /disharmonious
Authorization: Bearer <jwt-token>
```

**Response:**
```json
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "name": "Salmon Sashimi",
      "imageUrl": "https://example.com/salmon.jpg",
      "rate": 4.8
    },
    {
      "id": 2,
      "name": "Chicken Teriyaki",
      "imageUrl": "https://example.com/teriyaki.jpg",
      "rate": 4.7
    },
    {
      "id": 3,
      "name": "Miso Ramen",
      "imageUrl": "https://example.com/ramen.jpg",
      "rate": 4.6
    }
  ]
}
```

### Get All Dishes (Sorted by Rating)
```http
GET /disharmonious-all
Authorization: Bearer <jwt-token>
```

**Response:**
```json
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "name": "Salmon Sashimi",
      "imageUrl": "https://example.com/salmon.jpg",
      "rate": 4.8,
      "description": "Fresh salmon sliced thin, served raw"
    },
    {
      "id": 2,
      "name": "Chicken Teriyaki", 
      "imageUrl": "https://example.com/teriyaki.jpg",
      "rate": 4.7,
      "description": "Grilled chicken with sweet teriyaki sauce"
    }
  ]
}
```

---

## Favorites Endpoints

### Get User's Top 3 Favorites
```http
GET /favourite-top3
Authorization: Bearer <jwt-token>
```

**Response:**
```json
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "type": "restaurant",
      "name": "Sakura Sushi",
      "imageUrl": "https://example.com/restaurant1.jpg"
    },
    {
      "id": 2,
      "type": "dish",
      "name": "Salmon Sashimi",
      "imageUrl": "https://example.com/salmon.jpg"
    }
  ]
}
```

### Get All User Favorites
```http
GET /favourites
Authorization: Bearer <jwt-token>
```

**Response:** Same format as top 3, but includes all favorites

### Add to Favorites
```http
POST /favourite
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "restaurantId": 1,    // Optional: for restaurant favorites
  "dishId": 2          // Optional: for dish favorites
}
```

**Note:** Include either `restaurantId` OR `dishId`, not both

**Response:**
```json
{
  "status": "success",
  "message": "Favourite added successfully"
}
```

### Remove from Favorites
```http
DELETE /favourite?favouriteId=5
Authorization: Bearer <jwt-token>
```

**Response:**
```json
{
  "status": "success",
  "message": "Favourite deleted successfully"
}
```

---

## Error Handling

### Standard Error Response Format
```json
{
  "status": "fail|error",
  "message": "Descriptive error message"
}
```

### Common HTTP Status Codes
- **200**: Success
- **400**: Bad Request (invalid data)
- **401**: Unauthorized (invalid/missing JWT token)
- **404**: Not Found (resource doesn't exist)
- **409**: Conflict (username already exists)
- **500**: Internal Server Error

### JWT Token Errors
```json
{
  "error": "Invalid or expired token"
}
```

**Solution:** Re-authenticate to get a new token

---

## Frontend Integration Examples

### React/JavaScript Example

```javascript
// API service class
class ApiService {
  constructor() {
    this.baseURL = 'http://localhost:8081';
    this.token = localStorage.getItem('jwt-token');
  }

  // Set authorization header
  getHeaders() {
    return {
      'Content-Type': 'application/json',
      ...(this.token && { 'Authorization': `Bearer ${this.token}` })
    };
  }

  // Login
  async login(username, password) {
    const response = await fetch(`${this.baseURL}/auth/login`, {
      method: 'POST',
      headers: this.getHeaders(),
      body: JSON.stringify({ username, password })
    });
    
    const data = await response.json();
    if (data.token) {
      this.token = data.token;
      localStorage.setItem('jwt-token', data.token);
    }
    return data;
  }

  // Get nearby restaurants
  async getNearbyRestaurants(page = 1, size = 10) {
    const response = await fetch(
      `${this.baseURL}/restaurant-recently?page=${page}&size=${size}`,
      { headers: this.getHeaders() }
    );
    return response.json();
  }

  // Get user profile
  async getUserProfile() {
    const response = await fetch(`${this.baseURL}/me`, {
      headers: this.getHeaders()
    });
    return response.json();
  }

  // Add to favorites
  async addFavorite(restaurantId = null, dishId = null) {
    const body = {};
    if (restaurantId) body.restaurantId = restaurantId;
    if (dishId) body.dishId = dishId;

    const response = await fetch(`${this.baseURL}/favourite`, {
      method: 'POST',
      headers: this.getHeaders(),
      body: JSON.stringify(body)
    });
    return response.json();
  }
}

// Usage example
const api = new ApiService();

// Login flow
async function handleLogin(username, password) {
  try {
    const result = await api.login(username, password);
    if (result.token) {
      console.log('Login successful');
      // Redirect to dashboard
    }
  } catch (error) {
    console.error('Login failed:', error);
  }
}

// Load restaurants
async function loadRestaurants() {
  try {
    const result = await api.getNearbyRestaurants(1, 10);
    if (result.status === 'success') {
      // Update UI with restaurant data
      displayRestaurants(result.data);
    }
  } catch (error) {
    console.error('Failed to load restaurants:', error);
  }
}
```

### Vue.js Example

```javascript
// Vue component
export default {
  data() {
    return {
      restaurants: [],
      loading: false,
      currentPage: 1,
      totalPages: 1
    }
  },
  
  async mounted() {
    await this.loadRestaurants();
  },
  
  methods: {
    async loadRestaurants() {
      this.loading = true;
      try {
        const response = await this.$http.get('/restaurant-recently', {
          params: { page: this.currentPage, size: 10 }
        });
        
        if (response.data.status === 'success') {
          this.restaurants = response.data.data;
          this.totalPages = response.data.totalPages;
        }
      } catch (error) {
        this.$toast.error('Failed to load restaurants');
      } finally {
        this.loading = false;
      }
    },
    
    async addToFavorites(restaurantId) {
      try {
        const response = await this.$http.post('/favourite', {
          restaurantId: restaurantId
        });
        
        if (response.data.status === 'success') {
          this.$toast.success('Added to favorites!');
        }
      } catch (error) {
        this.$toast.error('Failed to add favorite');
      }
    }
  }
}
```

---

## Authentication State Management

### Token Storage Options

**1. localStorage (Simple)**
```javascript
// Store token
localStorage.setItem('jwt-token', token);

// Retrieve token
const token = localStorage.getItem('jwt-token');

// Remove token (logout)
localStorage.removeItem('jwt-token');
```

**2. Secure httpOnly Cookies (Recommended)**
```javascript
// Set cookie (backend should handle this)
// Frontend just needs to include credentials
fetch('/api/endpoint', {
  credentials: 'include'  // Include cookies
});
```

### Token Expiration Handling

```javascript
// Check if token is expired
function isTokenExpired(token) {
  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.exp * 1000 < Date.now();
  } catch {
    return true;
  }
}

// Auto-logout on token expiration
if (isTokenExpired(token)) {
  // Redirect to login
  window.location.href = '/login';
}
```

---

## Data Models for Frontend

### User Model
```typescript
interface User {
  name: string;
  email: string;
  national: string;
  avatar?: string;
}
```

### Restaurant Model
```typescript
interface Restaurant {
  id: number;
  name: string;
  distance: number;
  minprice: number;
  maxprice: number;
  imageUrl: string;
  address: string;
  phone: string;
  description: string;
  rate: number;
  openTime: string;
  closeTime: string;
}
```

### Dish Model
```typescript
interface Dish {
  id: number;
  name: string;
  imageUrl: string;
  rate: number;
  description?: string;
}
```

### Favorite Model
```typescript
interface Favorite {
  id: number;
  type: 'restaurant' | 'dish';
  name: string;
  imageUrl: string;
  createdAt?: string;
}
```

---

## Testing the API

### Using Postman

1. **Create Environment**
   - Variable: `baseUrl` = `http://localhost:8081`
   - Variable: `token` = `{{jwt-token}}`

2. **Test Authentication**
   ```
   POST {{baseUrl}}/auth/register
   Body: {"name":"Test User","username":"test@example.com","password":"test123","national":"Japan"}
   
   POST {{baseUrl}}/auth/login  
   Body: {"username":"test@example.com","password":"test123"}
   ```

3. **Save Token**
   - In login response, copy the token
   - Set as environment variable or use in Authorization header

4. **Test Protected Endpoints**
   ```
   GET {{baseUrl}}/me
   Authorization: Bearer {{token}}
   ```

### Using curl Commands

```bash
# Register
curl -X POST http://localhost:8081/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","username":"test@example.com","password":"test123","national":"Japan"}'

# Login
curl -X POST http://localhost:8081/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"test@example.com","password":"test123"}'

# Get profile (replace TOKEN with actual JWT)
curl -X GET http://localhost:8081/me \
  -H "Authorization: Bearer TOKEN"

# Get restaurants
curl -X GET "http://localhost:8081/restaurant-recently?page=1&size=5" \
  -H "Authorization: Bearer TOKEN"
```

---

## Common Integration Issues & Solutions

### 1. CORS Issues
**Problem:** Browser blocks requests due to CORS policy
**Solution:** Backend already configured for CORS, ensure you're using correct origin

### 2. 401 Unauthorized
**Problem:** JWT token invalid or expired
**Solution:** 
- Check token format: `Bearer <token>`
- Verify token hasn't expired (14-day limit)
- Re-authenticate if needed

### 3. 404 Not Found
**Problem:** Endpoint doesn't exist
**Solution:** Verify endpoint URLs match this documentation exactly

### 4. Network Errors
**Problem:** Can't connect to backend
**Solution:** 
- Ensure backend is running on port 8081
- Check `http://localhost:8081/actuator/health`

### 5. Empty Response Data
**Problem:** API returns empty arrays
**Solution:** 
- Check if database has sample data
- Verify user has created favorites/reviews
- For restaurants, ensure distance < 1000m

---

## Sample Data for Testing

### Test User Credentials
```json
{
  "username": "demo@itss.com",
  "password": "demo123",
  "name": "Demo User",
  "national": "Japan"
}
```

### Expected Data Structure
- **Restaurants**: Filtered by distance < 1000m
- **Dishes**: Sorted by rating (highest first)  
- **Favorites**: User-specific, ordered by creation date
- **Reviews**: Aggregated for rating calculations

---

## Performance Considerations

### Pagination
- Always use pagination for restaurant lists
- Default page size: 10 items
- Maximum recommended: 50 items per page

### Caching Strategy
```javascript
// Cache restaurant data for 5 minutes
const CACHE_DURATION = 5 * 60 * 1000; // 5 minutes

class CachedApiService {
  constructor() {
    this.cache = new Map();
  }
  
  async getCachedRestaurants(page, size) {
    const key = `restaurants-${page}-${size}`;
    const cached = this.cache.get(key);
    
    if (cached && Date.now() - cached.timestamp < CACHE_DURATION) {
      return cached.data;
    }
    
    const data = await this.getNearbyRestaurants(page, size);
    this.cache.set(key, { data, timestamp: Date.now() });
    return data;
  }
}
```

### Image Loading
- All image URLs are provided by the API
- Implement lazy loading for better performance
- Consider image placeholder while loading

---

## Security Best Practices

### 1. Token Security
```javascript
// Don't log tokens
console.log('Token:', token); // ❌ Never do this

// Store securely
// ✅ Use httpOnly cookies when possible
// ✅ Use localStorage only for development
```

### 2. Input Validation
```javascript
// Validate before sending to API
function validateEmail(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function validatePassword(password) {
  return password.length >= 6;
}
```

### 3. Error Handling
```javascript
// Don't expose sensitive errors to users
try {
  await api.login(username, password);
} catch (error) {
  // ❌ Don't show: "Database connection failed"
  // ✅ Show: "Login failed. Please try again."
  showUserFriendlyError("Login failed. Please try again.");
}
```

This API documentation provides everything a frontend developer needs to integrate with your ITSS-NIHONGO backend successfully!