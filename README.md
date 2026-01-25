## MICROSERVICE DEMO
# 1. Tech Stack
- Java: JDK17
- Database: mongodb
- Framework: Springboot
# 2. Services
- `eureka-server` – Service discovery server using Netflix Eureka
- `api-gateway` – Handles routing - http://localhost:8000
- `user-service` – Manages user accounts and data - http://localhost:9999
- `task-service` – Manages tasks assigned to users - http://localhost:8888
- `validation` – Shared validation module - http://localhost:7777
- `finace-service` – Handle income/outcome finnance - http://localhost:7777
# 3. Logger
- Each service uses **Logback** to write logs to a file named after the current date.
- 📂 Example Log File Structure:
<pre>
  logs/
├── user-service/
│ └── user-service-2025-07-13.log
├── task-service/
│ └── task-service-2025-07-13.log
</pre>
# 4. How to run
- To access all services through a single entry point (`http://localhost:8000`), start the services in this order:
 1. **`eureka-server`** – Service registry
 2. **`api-gateway`** – Gateway routing via Eureka
 3. All other services like **`user-service`**, **`task-service`**, etc.
- ✅ This ensures:
- All services register correctly with Eureka
- The API Gateway can route traffic using service discovery
- You can access endpoints like:
```
http://localhost:8000/api/users
http://localhost:8000/api/tasks
```
## 🛠️ Running Services Independently (Without Eureka)
- If you want to start a service **without registering it with Eureka**, update its `application.properties` as follows:
```properties
# application.properties
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
