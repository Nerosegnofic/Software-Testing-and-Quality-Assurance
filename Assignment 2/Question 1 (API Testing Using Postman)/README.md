# 📬 Postman Collection: API Testing Using Postman

This repository contains a Postman collection to help you test and explore various API endpoints, including **Users**, **Auth**, **Products**, and **Carts** modules.

## 📦 Contents
- `Question 1 (API Testing Using Postman).postman_collection.json`: The main Postman collection file.

---

## 🚀 Getting Started

### 1. Install Postman
- Download and install Postman from [here](https://www.postman.com/downloads/).

### 2. Import the Collection
1. Open Postman.
2. Click on **Import**.
3. Select the file: `Question 1 (API Testing Using Postman).postman_collection.json`.

### 3. Configure Environment
- This collection uses a variable: `{{baseURL}}`.
- By default, it points to: `https://fakestoreapi.com`
- You can change this variable in **Manage Environments** if needed.

---

## 📚 API Endpoints Overview

### 🧑 Users
| Method | Endpoint         | Description             |
|--------|------------------|-------------------------|
| POST   | `/users`         | Create User (Positive)  |
| POST   | `/users`         | Create User (Negative)  |
| GET    | `/users`         | Get All Users (Positive)|
| GET    | `/userss`        | Get All Users (Negative)|

### 🔐 Auth
| Method | Endpoint         | Description             |
|--------|------------------|-------------------------|
| POST   | `/auth/login`    | Login User (Positive)   |
| POST   | `/auth/login`    | Login User (Negative)   |

### 📦 Products
| Method | Endpoint                  | Description             |
|--------|---------------------------|-------------------------|
| GET    | `/products`               | Get All Products (Positive)|
| GET    | `/productss`              | Get All Products (Negative)|
| GET    | `/products/:productID`    | Get Product by ID (Positive & Negative)|

### 🛒 Carts
| Method | Endpoint                  | Description             |
|--------|---------------------------|-------------------------|
| POST   | `/carts`                  | Create Cart (Positive)  |
| POST   | `/carts`                  | Create Cart (Negative)  |
| GET    | `/carts/:cartID`          | Get Cart by ID (Positive & Negative)|
| DELETE | `/carts/:cartID`          | Delete Cart (Positive & Negative)|
| GET    | `/carts`                  | Get All Carts (Positive)|
| GET    | `/cartss`                 | Get All Carts (Negative)|

---

## 🔐 Authentication
- The **Auth** requests use **username** and **password** to generate tokens.
- Example Credentials:
  ```json
  {
      "username": "mor_2314",
      "password": "83r5^_"
  }
  ```

---

## 🛠️ Running Tests
- Open **Collection Runner** in Postman.
- Choose this collection.
- Click **Run** to execute all requests sequentially.

---

## ❓ Troubleshooting
- Make sure `{{baseURL}}` is correctly set (default is `https://fakestoreapi.com`).
- Use valid IDs (e.g., `productID: 1`, `cartID: 1`) when testing positive cases.
- Negative cases intentionally use invalid paths or data to test failure responses.
