# online_store_appn
### **Complete Project Explanation: Online Store with Spring Boot**

This project is a **full-stack web application** for an online store, built using **Spring Boot** (backend) and **Thymeleaf** (frontend templates). It includes user authentication, product management, shopping cart functionality, and a payment processing flow. Below is a detailed breakdown:

---

### **1. Technologies Used**
- **Backend**:
  - **Spring Boot 3.x**: Core framework for building the application.
  - **Spring Security**: Handles authentication and role-based access control.
  - **Spring Data JPA**: Manages database operations with MySQL.
  - **Hibernate**: ORM for mapping Java objects to database tables.
- **Frontend**:
  - **Thymeleaf**: Server-side templating engine for dynamic HTML.
  - **Bootstrap 5**: Styling and responsive design.
- **Database**:
  - **MySQL**: Relational database for storing users, products, and cart items.
- **Tools**:
  - **Maven**: Dependency management.
  - **Spring DevTools**: Hot-reloading for development.

---

### **2. Key Features**
#### **A. User Authentication & Authorization**
- **Roles**:
  - **Admin**: Manages products (create, edit, delete).
  - **User**: Browses products, adds to cart, and checks out.
- **Security Workflow**:
  - Users log in via `/login` with Spring Security.
  - Admins are redirected to `/admin/products`, users to `/`.
  - **Password Encryption**: BCrypt password encoder.

#### **B. Product Management (Admin)**
- **CRUD Operations**:
  - **Create**: `/admin/products/new` → Renders a form to add new products.
  - **Read**: `/admin/products` → Lists all products in a table.
  - **Update**: `/admin/products/edit/{id}` → Pre-filled form for editing.
  - **Delete**: `/admin/products/delete/{id}` → Removes a product (cascades to cart entries).
- **Templates**:
  - `admin/products.html`: Main product listing.
  - `admin/create_product.html` & `admin/edit_product.html`: Forms.

#### **C. Shopping Cart (User)**
- **Functionality**:
  - **Add to Cart**: Users can add products with a specified quantity.
  - **View Cart**: Displays cart items, quantities, subtotals, and total.
  - **Remove Items**: Delete individual cart entries.
- **Templates**:
  - `cart/view.html`: Cart summary with remove buttons and checkout.

#### **D. Payment Processing (User)**
- **Checkout Flow**:
  1. **Proceed to Payment**: From cart page (`/payment/checkout`).
  2. **Payment Form**: Collects card details (placeholder for real payment gateway).
  3. **Confirmation Page**: After successful "payment".
- **Validation**:
  - Card number, expiry date (MM/YY), and CVV are validated.
- **Templates**:
  - `payment/checkout.html`: Payment form.
  - `payment/confirmation.html`: Order confirmation.

---

### **3. Database Schema**
#### **Entities**:
1. **User**:
   - `id`, `username` (unique), `password`, `role` (ADMIN/USER).
   - Implements `UserDetails` for Spring Security.
2. **Product**:
   - `id`, `name`, `price`, `description`.
3. **Cart**:
   - `id`, `user_id` (foreign key), `product_id` (foreign key), `quantity`.

#### **Relationships**:
- **User ↔ Cart**: One-to-Many (a user can have multiple cart items).
- **Product ↔ Cart**: One-to-Many (a product can be in multiple carts).
- **Cascading**: Deleting a product removes related cart entries.

---

### **4. Code Structure**
#### **A. Controllers**:
1. **HomeController**:
   - Handles `/` → Displays all products for users.
2. **AdminController**:
   - Manages product CRUD operations under `/admin/**`.
3. **CartController**:
   - Manages cart operations (`/cart/**`).
4. **PaymentController**:
   - Handles payment flow (`/payment/**`).

#### **B. Services**:
1. **ProductService**:
   - Implements product CRUD logic.
2. **CartService**:
   - Adds/removes cart items and calculates totals.
3. **UserDetailsService**:
   - Loads user data for Spring Security.

#### **C. Repositories**:
- **JPA Interfaces**:
  - `ProductRepository`, `CartRepository`, `UserRepository`.
  - Extend `JpaRepository` for database operations.

---

### **5. Setup Instructions**
#### **A. Prerequisites**:
- Java 17, MySQL, Maven.

#### **B. Steps**:
1. **Database Setup**:
   ```sql
   CREATE DATABASE onlinestore;
   ```
2. **Configure `application.properties`**:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/onlinestore
   spring.datasource.username=root
   spring.datasource.password=12345
   spring.jpa.hibernate.ddl-auto=update
   ```
3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
4. **Default Users**:
   - **Admin**: `admin/admin`
   - **User**: `user/user`

---

### **6. Testing the Application**
1. **Admin Flow**:
   - Log in as `admin` → Manage products at `/admin/products`.
2. **User Flow**:
   - Log in as `user` → Add products to cart → Checkout → Payment.

---

### **7. Future Enhancements**
1. **Real Payment Gateway**: Integrate Stripe/PayPal.
2. **Order History**: Track user orders.
3. **Search & Filters**: For products.
4. **Email Notifications**: Order confirmations.
5. 
**Conclusion**
This project demonstrates a **complete e-commerce application** with role-based access, cart management, and payment workflows. It serves as a foundation for building more complex features like inventory management, user reviews, or recommendation systems.
