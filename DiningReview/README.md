Restful API backend application.

User API requests:
GET
    curl -X GET localhost:8080/api/v1/users | json_pp 
    curl  -i -X GET -H "Content-Type: application/json"  \ 
            -d '{"name":"User 5"}' \ 
            http://localhost:8080/api/v1/users/findByName
POST
    curl  -i -X POST -H "Content-Type: application/json" \ 
            -d '{"name":"User 5"}' \ 
            http://localhost:8080/api/v1/users
DELETE
    curl -i -X DELETE http://localhost:8080/api/v1/users/delete?id=1
    curl -i -X DELETE localhost:8080/api/v1/users/deleteAll
PUT
    curl -i -X PUT -H "Content-Type: application/json"  \ 
            -d '{"name":"User 1"}' \ 
            http://localhost:8080/api/v1/users/update/1

