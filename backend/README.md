# Backend
The backend of this project is made with Java Spring Boot.

## To-Do

### Regexx validation
* Add user input validation in backend.

### Code convention
* Use DTO in controllers instead of the entity.

### AP Restrictions
* Cookies are always httponly, send company/username/expirydate and role during authorization in seperated parameters to frontend (replacing JWT).
    * Change entire frontend to not work with data from JWT.
* Google mail service (port) is not allowed by AP.
