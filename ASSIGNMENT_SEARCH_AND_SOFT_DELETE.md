# Assignment: Product and Category Search with Soft Delete

## Objective

Implement filtered search endpoints for Category and Product, and update the delete behavior so records are soft deleted instead of permanently removed from the database.

## Requirements

### Category Search

Create a working search endpoint:

```http
POST /api/v1/categories/search
```

Request body example:

```json
{
  "name": "Electronics",
  "code": "ELEC",
  "page": 0,
  "size": 10
}
```

Guidelines:

- `name` and `code` are optional filters.
- Search results must include only active categories.
- Active categories are records where `isActive = true`.
- Support pagination using `page` and `size`.

### Product Search

Create a working search endpoint:

```http
POST /api/v1/products/search
```

Request body example:

```json
{
  "name": "Smartphone",
  "sku": "PHONE",
  "categoryId": 1,
  "minPrice": 100.00,
  "maxPrice": 700.00,
  "page": 0,
  "size": 10
}
```

Guidelines:

- `name`, `sku`, `categoryId`, `minPrice`, and `maxPrice` are optional filters.
- Search results must include only active products.
- Active products are records where `isActive = true`.
- Support pagination using `page` and `size`.

### Soft Delete

Currently, the delete endpoints remove rows from the database. Change this behavior to soft delete.

Soft delete means:

- The row remains in the database.
- The record is marked inactive by setting `isActive = false`.
- Future search results should not show inactive products or categories.

Update these endpoints:

```http
DELETE /api/v1/categories/{id}
DELETE /api/v1/products/{id}
```

## Student Tasks

1. Update the controller methods to call proper service methods.
2. Add service methods for category search and product search.
3. Add repository queries or specifications for optional filters.
4. Ensure search queries always filter by `isActive = true`.
5. Change delete logic so it sets `isActive = false` instead of deleting the row.
6. Keep response formats consistent with the existing API style.

_Note: Category and product search controller methods are already created. You need to replace the todos with your implementation._

## Project Setup Guide

### Step 1: Clone the Repository

Clone the starter project from GitHub:

```powershell
git clone https://github.com/PialKanti/ecommerce-backend
```

### Step 2: Navigate to Project Directory

Move into the cloned project folder:

```powershell
cd ecommerce-backend
```

### Step 3: Checkout Assignment Branch

Switch to the dedicated assignment branch:

```powershell
git checkout module-21-assignment
```

## Run the Project

### Prerequisites

Install:

- JDK 25
- Docker Desktop
- IntelliJ IDEA

### Start PostgreSQL with Docker Desktop

Open Docker Desktop and make sure it is running.

From the project root, start PostgreSQL:

```powershell
docker compose up -d
```

Verify that the database container is running:

```powershell
docker compose ps
```

### Run from IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Wait for Gradle sync to finish.
3. Open `EcommerceBackendApplication`.
4. Click the green Run button.
5. After the application starts, open Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

## Expected Outcome

After completing the assignment:

- Category search returns only active categories matching the provided filters.
- Product search returns only active products matching the provided filters.
- Delete endpoints no longer remove database rows.
- Deleted products and categories are marked with `isActive = false`.