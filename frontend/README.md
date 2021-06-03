
# Frontend
The frontend of this project is made with Angular. 

## To-Do

### IT-Applications
* CSS on ITApplication & Capability-ITapplication link
* Remove required from Capability-ITapplication link?

### Company 
* Make Company work with a country ENUM that has a search bar

### Capability-Map
* Capability map level 3 capabilities

### Capabilities
* Name isn't unique for capabilities

### Passwords
* Upon signup, login and reset password the passwords are send as plain text to the api -> change this so it's encrypted

### Company
* Enum of countries in list with search option since too many options.

### Security
* Add authinterceptor in frontend that sends the JWT with each request.

### User
After selecting an environment you can press a tab in the navbar to add users to it assuming you're an useradmin (only show for user admins and have routerguard on it so only useradmins can access it)