# Existing Favorites API Specification

**Based on Current Backend Implementation**

This document describes the **actual favorites functionality** that already exists in your ITSS-NIHONGO backend, so you can build the frontend to match it exactly.

---

## 🔍 What Your Backend Actually Does

### Important Discovery: **DISH-RESTAURANT FAVORITES ONLY**

Your backend is designed for a **specific type of favorite**: 
- ✅ **Dish + Restaurant combinations** (not standalone restaurants or dishes)
- ✅ Users favorite a specific dish **at a specific restaurant**
- ✅ Both `dishId` and `restaurantId` are **required** when adding favorites

---

## 📡 Available API Endpoints

### 1. Get Top 3 Recent Favorites
```http
GET /favourite-top3
Authorization: Bearer <jwt-token>
```

**Response (Success):**
```json
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "dishesname": "Salmon Sashimi",
      "restaurantname": "Sakura Sushi",
      "distance": 250,
      "imageUrl": "https://example.com/salmon.jpg"
    },
    {
      "id": 2,
      "dishesname": "Chicken Teriyaki",
      "restaurantname": "Tokyo Kitchen",
      "distance": 500,
      "imageUrl": "https://example.com/teriyaki.jpg"
    }
  ]
}
```

**Response (No Favorites):**
```json
{
  "status": "fail",
  "message": "No favourite items found"
}
```

### 2. Get All User Favorites
```http
GET /favourites
Authorization: Bearer <jwt-token>
```

**Response (Success):**
```json
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "dishesname": "Salmon Sashimi",
      "imageUrl": "https://example.com/salmon.jpg",
      "rate": 4.8,
      "description": "Fresh salmon sliced thin, served raw"
    },
    {
      "id": 2,
      "dishesname": "Chicken Teriyaki",
      "imageUrl": "https://example.com/teriyaki.jpg",
      "rate": 4.5,
      "description": "Grilled chicken with sweet teriyaki sauce"
    }
  ]
}
```

### 3. Add Favorite (Dish + Restaurant)
```http
POST /favourite
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "dishId": 5,
  "restaurantId": 3
}
```

**Response (Success):**
```json
{
  "status": "success",
  "message": "Favourite added successfully"
}
```

**Response (Already Exists):**
```json
{
  "status": "fail",
  "message": "Failed to add favourite (it may already exist)"
}
```

### 4. Remove Favorite
```http
DELETE /favourite?favouriteId=1
Authorization: Bearer <jwt-token>
```

**Response (Success):**
```json
{
  "status": "success",
  "message": "Favourite deleted successfully"
}
```

**Response (Not Found):**
```json
{
  "status": "fail",
  "message": "Failed to delete favourite (it may not exist)"
}
```

---

## 🎯 Frontend Implementation Guide

### Data Models for Frontend

```typescript
// What you'll receive from the API
interface FavoriteItem {
  id: number;
  dishesname: string;        // Note: "dishesname" not "dishName"
  restaurantname?: string;   // Only in top3 response
  distance?: number;         // Only in top3 response  
  imageUrl: string;
  rate?: number;            // Only in all favorites response
  description?: string;     // Only in all favorites response
}

// What you need to send when adding
interface AddFavoriteRequest {
  dishId: number;
  restaurantId: number;
}

// Standard API response wrapper
interface ApiResponse<T> {
  status: 'success' | 'fail' | 'error';
  data?: T;
  message?: string;
}
```

### Key Implementation Notes

#### 1. **Two Different Response Formats**
Your backend returns **different data** for top3 vs all favorites:

**Top 3 Favorites:**
- ✅ Includes `restaurantname` and `distance`
- ❌ No `rate` or `description`

**All Favorites:**
- ✅ Includes `rate` and `description`
- ❌ No `restaurantname` or `distance`

#### 2. **Adding Favorites Requires Both IDs**
```javascript
// ✅ Correct - Both required
const addFavorite = {
  dishId: 5,
  restaurantId: 3
};

// ❌ Wrong - Will fail
const addFavorite = {
  dishId: 5  // Missing restaurantId
};
```

#### 3. **Deletion Uses Favorite ID (Not Dish/Restaurant ID)**
```javascript
// ✅ Correct - Use the favorite record ID
DELETE /favourite?favouriteId=1

// ❌ Wrong - Don't use dish or restaurant ID
DELETE /favourite?dishId=5
```

---

## 🎨 Suggested Frontend Screen Design

Based on your backend's actual behavior:

### Screen Layout
```
┌─────────────────────────────────────┐
│ Favorites                           │
├─────────────────────────────────────┤
│ Recent Favorites (Top 3)            │
│ ┌─────┐ ┌─────┐ ┌─────┐            │
│ │Dish │ │Dish │ │Dish │ →          │
│ │@Rest│ │@Rest│ │@Rest│            │
│ │250m │ │500m │ │750m │            │
│ └─────┘ └─────┘ └─────┘            │
├─────────────────────────────────────┤
│ All Favorites                       │
│ ┌─────────────────────────────────┐ │
│ │ [Image] Salmon Sashimi    [❤️] │ │
│ │         ⭐ 4.8                 │ │
│ │         Fresh salmon sliced... │ │
│ └─────────────────────────────────┘ │
│ ┌─────────────────────────────────┐ │
│ │ [Image] Chicken Teriyaki  [❤️] │ │
│ │         ⭐ 4.5                 │ │
│ │         Grilled chicken with...│ │
│ └─────────────────────────────────┘ │
└─────────────────────────────────────┘
```

### Component Structure
```typescript
const FavoritesScreen = () => {
  const [recentFavorites, setRecentFavorites] = useState<FavoriteItem[]>([]);
  const [allFavorites, setAllFavorites] = useState<FavoriteItem[]>([]);
  
  // Load both datasets
  useEffect(() => {
    loadRecentFavorites();
    loadAllFavorites();
  }, []);
  
  const loadRecentFavorites = async () => {
    const response = await fetch('/favourite-top3', {
      headers: { Authorization: `Bearer ${token}` }
    });
    const data = await response.json();
    if (data.status === 'success') {
      setRecentFavorites(data.data);
    }
  };
  
  const loadAllFavorites = async () => {
    const response = await fetch('/favourites', {
      headers: { Authorization: `Bearer ${token}` }
    });
    const data = await response.json();
    if (data.status === 'success') {
      setAllFavorites(data.data);
    }
  };
  
  return (
    <View>
      <RecentFavoritesSection items={recentFavorites} />
      <AllFavoritesSection items={allFavorites} onRemove={removeFavorite} />
    </View>
  );
};
```

---

## ⚠️ Important Backend Limitations

### 1. **No Standalone Restaurant/Dish Favorites**
- You **cannot** favorite just a restaurant
- You **cannot** favorite just a dish
- You **must** favorite a dish **at a specific restaurant**

### 2. **Inconsistent Response Data**
- Top 3 and All favorites return different fields
- You'll need to handle this in your UI logic

### 3. **No Search/Filter Built-in**
- Backend doesn't provide search functionality
- You'll need to implement client-side filtering

### 4. **No Pagination**
- All favorites are returned at once
- Could be performance issue with many favorites

---

## 🚀 Quick Start Implementation

### 1. **API Service Class**
```typescript
class FavoritesApi {
  private baseUrl = 'http://localhost:8081';
  private token = localStorage.getItem('jwt-token');
  
  async getRecentFavorites(): Promise<FavoriteItem[]> {
    const response = await fetch(`${this.baseUrl}/favourite-top3`, {
      headers: { Authorization: `Bearer ${this.token}` }
    });
    const data = await response.json();
    return data.status === 'success' ? data.data : [];
  }
  
  async getAllFavorites(): Promise<FavoriteItem[]> {
    const response = await fetch(`${this.baseUrl}/favourites`, {
      headers: { Authorization: `Bearer ${this.token}` }
    });
    const data = await response.json();
    return data.status === 'success' ? data.data : [];
  }
  
  async addFavorite(dishId: number, restaurantId: number): Promise<boolean> {
    const response = await fetch(`${this.baseUrl}/favourite`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${this.token}`
      },
      body: JSON.stringify({ dishId, restaurantId })
    });
    const data = await response.json();
    return data.status === 'success';
  }
  
  async removeFavorite(favouriteId: number): Promise<boolean> {
    const response = await fetch(`${this.baseUrl}/favourite?favouriteId=${favouriteId}`, {
      method: 'DELETE',
      headers: { Authorization: `Bearer ${this.token}` }
    });
    const data = await response.json();
    return data.status === 'success';
  }
}
```

### 2. **Error Handling**
```typescript
const handleApiError = (error: any) => {
  if (error.status === 404) {
    // No favorites found - show empty state
    return 'No favorites yet. Start exploring restaurants!';
  } else if (error.status === 500) {
    // Server error
    return 'Something went wrong. Please try again.';
  } else {
    // Network error
    return 'Check your internet connection.';
  }
};
```

---

## 📝 Summary

Your backend implements a **dish-restaurant combination favorites system** with these key characteristics:

✅ **What Works:**
- Get top 3 recent favorites (with restaurant info)
- Get all favorites (with ratings/descriptions)
- Add dish+restaurant combinations
- Remove favorites by ID

⚠️ **What to Handle in Frontend:**
- Different response formats for different endpoints
- Client-side search/filtering
- Empty state handling
- Error state management

🎯 **Perfect for:**
- "Favorite dishes at restaurants" use case
- Food discovery apps
- Restaurant recommendation systems

This specification gives you everything you need to build a frontend that works perfectly with your existing backend!