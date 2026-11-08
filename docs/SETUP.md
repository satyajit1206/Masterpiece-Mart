# Configuration

## MySQL

Create the database in your MySQL instance:

```sql
CREATE DATABASE mm_product;
```

From the project root, start the backend with your database credentials:

```bash
cd mmBackend-master
export DB_URL='jdbc:mysql://localhost:3306/mm_product'
export DB_USERNAME='root'
export DB_PASSWORD='your-local-password'
bash ./mvnw spring-boot:run
```

This uses MySQL instead of the in-memory database selected by `scripts/start-backend.sh`.

## Frontend

Copy `masterpiecemart-master/.env.example` to `.env` in the same folder to configure the backend URL:

```text
VITE_API_BASE_URL=http://localhost:8080
```

Restart the frontend after changing this value.

## Local settings

- Backend: `http://localhost:8080`
- Frontend: `http://127.0.0.1:5173`
- Health endpoint: `http://localhost:8080/actuator/health`
- `CORS_ORIGINS`: comma-separated frontend origins; defaults to localhost and 127.0.0.1 on port 5173.
- Artwork uploads accept three JPEG files, up to 10 MB each.

The startup scripts support macOS/Linux. Maven is provided through the wrapper; dependencies require internet access on the first run. Stop each server with Ctrl+C.

### Restored frontend pages
FAQ, About, Contact, Login, Sign Up and checkout previews are available from the navigation/footer and shopping flow. Artwork pages show three selectable images. Checkout displays ₹70 delivery up to ₹1,000 and free delivery above ₹1,000; it does not submit orders, collect payment, or persist address details.

The optional account and contact forms require separate services, which are not included in this backend. Set `VITE_AUTH_BASE_URL` (signin/signup under `/api/auth`) and `VITE_CONTACT_URL` (full message POST endpoint) in the frontend environment and restart Vite to enable them. Accounts do not make the shared cart or wishlist private.
