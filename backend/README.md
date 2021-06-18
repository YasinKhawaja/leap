# Backend
The backend of this project is made with Java Spring Boot.

## To-Do

### Regexx validation
* Add user input validation in backend

### Add proper error feedback on login error
* Currently this is always username or password was wrong -> add the "Oops something went wrong error" and the "Company is locked error"

### DTO'S EVERYWHERE
* Use DTO in controllers instead of the entity.

### AP
* Cookies are always httponly, send company/username/expirydate and role during authorization in seperated parameters to frontend (replacing jwt).