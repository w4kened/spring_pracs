Restful API backend application.
```
User API requests:
GET
        curl -X GET localhost:8080/api/v1/users | json_pp 
        curl  -i -X GET http://localhost:8080/api/v1/users/findByName?name=User%201

POST
        curl  -i -X POST http://localhost:8080/api/v1/users \
    -H "Content-Type: application/json" \
    -d '{"name":"User 1"}'
DELETE
        curl -i -X DELETE http://localhost:8080/api/v1/users/delete?name=User%201
        curl -i -X DELETE localhost:8080/api/v1/users/deleteAll
PUT
        curl -i -X PUT http://localhost:8080/api/v1/users/update?name=User%201 \
    -H "Content-Type: application/json" \
    -d '{"name":"User 2"}'
            
Restaurant API requests:
GET
        curl -X GET localhost:8080/api/v1/restaurants | json_pp
        curl -X GET localhost:8080/api/v1/restaurants/findById/1 | json_pp
        curl -X GET localhost:8080/api/v1/restaurants/findByZipCodeWithSubmittedAllergies?zipcode=2511 | json_pp
POST
        curl  -i -X POST http://localhost:8080/api/v1/restaurants \
    -H "Content-Type: application/json" \
    -d '{"name":"Pizza Roma","zipcode":"2511"}'
      
DinningReview API Requests:
GET
        curl -X GET localhost:8080/api/v1/dinningReviews | json_pp
        curl -X GET localhost:8080/api/v1/dinningReviews/findApprovedReviewsForRestaurant/1 | json_pp
POST
        curl -X POST http://localhost:8080/api/v1/dinningReviews \
    -H "Content-Type: application/json" \
    -d '{"peanutScore": "THREE","eggScore": "FIVE", "dairyScore": "FOUR", 
    "commentary": "Great place! Egg-free options are perfect.",
    "user": { "id": 1 }, "restaurant": { "id": 1 }}'
        
AdminReview API Requests:      
GET
        curl -X GET localhost:8080/api/v1/admin/findPending | json_pp
POST
        curl -X POST http://localhost:8080/api/v1/admin/approve/1
        curl -X POST http://localhost:8080/api/v1/admin/reject/1
```
