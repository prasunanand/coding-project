# Harbor Take Home Project

Welcome to the Harbor take home project. We hope this is a good opportunity for you to showcase your skills.

## The Challenge

Build us a REST API for calendly. Remember to support

- Setting own availability
- Showing own availability
- Finding overlap in schedule between 2 users

It is up to you what else to support.

### Build

Download the project

System Requirements
1. Java 22
2. MySQL 9.1.0

Edit `src/main/resources/application.properties`

Set
```
spring.datasource.username=root
spring.datasource.password=password
```

Start the application via Intellij 






## API Requests

### Health Check

```shell
curl --location 'http://localhost:8080/api/health'
```

```shell
{
    "status": "success",
    "message": "Server Running",
    "data": {
        "status": "Up"
    }
}
```

### Create User

```shell
curl --location 'http://localhost:8080/api/users/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "prasun",
    "email": "prasaun@anand.com"
}'
```

```shell
{
    "status": "success",
    "message": "User Created Successfully",
    "data": {
        "email": "prasaun@anand.com",
        "name": "prasun",
        "id": 1
    }
}
```

### Add availability

```shell
curl --location 'http://localhost:8080/api/users/1/availability' \
--header 'Content-Type: application/json' \
--data '{
  "availability": [
    {
      "dayOfWeek": "MONDAY",
      "startTime": "09:00:00",
      "endTime": "12:00:00"
    },
    {
      "dayOfWeek": "WEDNESDAY",
      "startTime": "14:00:00",
      "endTime": "18:00:00"
    }
  ]
}'
```

```shell

{
    "status": "success",
    "message": "Availability Created Successfully",
    "data": {
        "availability": [
            {
                "id": 9,
                "dayOfWeek": "MONDAY",
                "startTime": "09:00:00",
                "endTime": "12:00:00",
                "userId": 1
            },
            {
                "id": 10,
                "dayOfWeek": "WEDNESDAY",
                "startTime": "14:00:00",
                "endTime": "18:00:00",
                "userId": 1
            }
        ]
    }
}
```

### Check Availibility

```shell
curl --location 'http://localhost:8080/api/users/1/availability'
```

```shell
{
    "status": "success",
    "message": "Availability Fetched Successfully",
    "data": [
        {
            "id": 1,
            "dayOfWeek": "Monday",
            "startTime": "09:00:00",
            "endTime": "12:00:00",
            "userId": 1
        },
        {
            "id": 2,
            "dayOfWeek": "Tuesday",
            "startTime": "14:00:00",
            "endTime": "18:00:00",
            "userId": 1
        },
        {
            "id": 8,
            "dayOfWeek": "WEDNESDAY",
            "startTime": "14:00:00",
            "endTime": "18:00:00",
            "userId": 1
        }
    ]
}
```

### Find Overlap

```shell
curl --location 'http://localhost:8080/api/users/overlap' \
--header 'Content-Type: application/json' \
--data '{
  "user1Id": "1",
  "user2Id": "2"
}'
```

```shell
{
    "status": "success",
    "message": "Overlaps found",
    "data": [
        {
            "id": 7,
            "dayOfWeek": "MONDAY",
            "startTime": "09:00:00",
            "endTime": "12:00:00",
            "userId": 1
        },
        {
            "id": 9,
            "dayOfWeek": "MONDAY",
            "startTime": "09:00:00",
            "endTime": "12:00:00",
            "userId": 1
        }
    ]
}
```

### Book Event

```shell
curl --location 'http://localhost:8080/api/users/bookEvent' \
--header 'Content-Type: application/json' \
--data '{
    "userId": 2,
    "eventDate": "2024-01-29",
    "availabilityId": 3,
    "title": "TeamMeeting"
}'
```

* Success

```shell
{
    "status": "success",
    "message": "Event Booked Successfully",
    "data": {
        "id": 4,
        "title": "TeamMeeting",
        "startTime": "2024-01-08T09:00:00",
        "endTime": "2024-01-08T12:00:00",
        "userId": 2,
        "availabilityId": 3
    }
}
```

* Failure

```shell
{
    "status": 400,
    "message": "The time slot is already booked."
}
```

### Get Events

```shell
curl --location 'http://localhost:8080/api/users/2/events'
```

```shell
{
    "status": "success",
    "message": "Events Fetched Successfully",
    "data": [
        {
            "id": 2,
            "title": "TeamMeeting",
            "startTime": "2024-01-22T09:00:00",
            "endTime": "2024-01-22T12:00:00",
            "userId": 2,
            "availabilityId": 3
        },
        {
            "id": 3,
            "title": "TeamMeeting",
            "startTime": "2024-01-29T09:00:00",
            "endTime": "2024-01-29T12:00:00",
            "userId": 2,
            "availabilityId": 3
        }
    ]
}
```