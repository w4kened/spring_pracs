Rest API test

GET
 
 curl -X GET localhost:8080/api/v1/users | json_pp

POST

curl l -X POST -H "Content-Type: application/json" -d '{"name":"User 2", "email":"user1@gmail.com", "city":"New York","isInterestedInPeanutAllergies":false, "isInterestedInEggAllergies": true, "isInterestedInDairyAllergies": true}' http://localhost:8080/api/v1/users | json_pp

