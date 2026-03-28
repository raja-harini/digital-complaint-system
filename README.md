# 🚨 Digital Complaint Management System

A full-stack enterprise-grade complaint management system built using **Spring Boot** and **React**, designed to streamline issue reporting, tracking, and resolution with SLA enforcement and real-time notifications.

---

## 📌 Overview

The Digital Complaint Management System enables users to raise complaints and track their progress, while administrators efficiently manage, assign, and resolve issues. The system incorporates **SLA-based escalation**, ensuring accountability and timely resolution—making it suitable for **government portals, IT support systems, and customer service platforms**.

---

## 🎯 Key Features

- 📝 Raise and track complaints in real-time  
- 🔄 Complete complaint lifecycle management  
  `RAISED → IN_PROGRESS → ESCALATED → RESOLVED → CLOSED`  
- ⏱ SLA-based auto escalation (e.g., after 48 hours)  
- 👥 Role-Based Access Control (USER / ADMIN)  
- 📊 Admin dashboard for monitoring and assignment  
- 🔔 Notification system (email/log-based)  
- 📜 Status history tracking for each complaint  
- 🔍 Team-wise complaint handling and workload view  

---

## 🏗 System Architecture

The application follows a layered architecture:

Controller → Service → Repository → Database


- **Frontend:** React (UI + API Integration)  
- **Backend:** Spring Boot (REST APIs)  
- **Database:** MySQL (Relational DB)  

---

## 🧰 Tech Stack

### Backend
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security + JWT Authentication
- MySQL Database
- Scheduler (@Scheduled for SLA)
- Swagger (API Documentation)

### Frontend
- React.js
- Axios (API calls)
- React Router (Navigation)
- Bootstrap / Material UI

---

## 📊 Database Design

The system is designed using an ER model with the following core entities:

- User  
- Complaint  
- ComplaintAssignment  
- StatusHistory  
- Team  
- Notification  

Relationships ensure:
- Complaint lifecycle tracking  
- Assignment audit trail  
- SLA monitoring  
- Notification management  

---

## 🔐 Authentication & Authorization

- JWT-based authentication  
- Secure login & registration  
- Role-based access:
  - **USER:** Raise & track complaints  
  - **ADMIN:** Assign, update, resolve complaints  

---

## ⏱ SLA & Escalation Logic

- Complaints are automatically escalated if not resolved within defined SLA (e.g., 48 hours)  
- Implemented using Spring Scheduler  
- Ensures accountability and timely resolution  

---

## 🔔 Notification System

- Users receive updates on:
  - Complaint creation  
  - Status changes  
  - Escalations  
  - Resolution  
- Notifications stored and viewable in system  

---

## 📡 API Endpoints

Major API modules include:

### User APIs
- Register / Login  
- View profile  
- View complaints  
- View notifications  

### Complaint APIs
- Create complaint  
- Update status  
- Escalate / Resolve  
- View complaint details  

### Admin APIs
- Assign complaints  
- Manage teams  
- View all complaints  

### Notification APIs
- Send notifications  
- Mark as read  

---

## 🧪 Testing

- Tested using Postman  
- End-to-end workflow validation:
  - User registration → complaint creation → admin assignment → SLA escalation → resolution  

---

## 🚀 How to Run

### Backend

1. Configure MySQL in `application.properties`
2. Run Spring Boot application
3. Access APIs via Swagger:
   
   http://localhost:8080/swagger-ui.html


### Frontend

1. Install dependencies  
2. Start React app  
3. Open:

   http://localhost:5173


---

## 📈 Real-World Use Cases

- Government grievance portals  
- IT helpdesk systems  
- Customer support platforms  
- Enterprise ticketing systems  

---

## 🌟 Highlights

- Real-time complaint tracking  
- Enterprise-level SLA implementation  
- Clean layered architecture  
- Beginner-friendly yet industry-relevant project  

---

## 👩‍💻 Author

**Harini R**  
B.E. CSE (Cyber Security)  
Passionate about building secure and scalable systems 🚀  

---

## 📌 Future Enhancements

- Email & SMS notifications  
- File/image upload for complaints  
- Analytics dashboard  
- Mobile application integration  
- AI-based complaint categorization  

---

⭐ If you found this project useful, consider giving it a star!
