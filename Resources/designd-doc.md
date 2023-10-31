# MuscleSync
### Description:
MuscleSync is built to help personal trainers create more personalized workout plans for their clients. The trainer (user) will be able to run physical assessments on their clients by performing a series of tests. The "assessment result" will highlight muscle imbalances and compensatory movement patterns that the trainer will want to target. The trainer can build workout routines made up of individual exercises that specifically target the muscles that are causing the improper movement patterns. The trainer can also track the their client's progress by running new assessments and comparing to older assessments. At MuscleSync we're putting the "personal" back in "personal training"!

---
## User Stories
1. "New Client" - Trainer (user) will be able to create new clients with personal info, available equipment, and any fitness considerations (pregnancy history, disability, etc.)
2. "Client Details" - Trainer can select from their list of clients when they have a training session. This will display helpful information for the trainer when working with the client.
3. "Functional Assessment" - Trainer can run a Functional Assessment on a client. This is a series of physical tests administered by the trainer. Each test will belong to 1 of 5 functional systems. A functional system can have one of 3 states, which included neutral and the two opposing ranges.
4. "Secondary Tests" - Functional Systems optionally may have secondary tests. These will highlight any secondary movement compensation patterns that contradict the primary functional movement that the trainer can then address.
5. "Functional Profile" - The test results will be compiled into a FunctionalProfile, which is the current functional state of the client.
6. "Functional Goals" - Trainer will be able to manually edit the Functional Profile, and select the areas they'd like to address in building their workout. These will become a list of functional goals that Exercises will address.
7. "Select Exercises" - Trainer can select specific exercises to address any Functional Goals the client may have. Functional Goals will have many associated exercises, organized by a difficulty level. This will allows trainer to easily select progressively more challenging versions of an exercises, or regress them to an easier exercise.
8. "Build Workout" - Trainer can build a workout plan, which is a series of exercises.  This may be adding additional comments or highlighting specific cues that the client finds the most helpful.
9. "Workout Program" - Trainer can package one or several "Workouts" into a "Program" and send the program to their client via email.
10. "Delete Client" - Trainer can remove a client from their current list. This will  be a soft delete, so that if a client ever returns, the trainer will have access to old information.
### Stretch Stories
1. "New Exercises" - Trainer can upload their own Exercises and select which functional systems state they apply to.
2. "Tailor Exercise" - Exercises will have an initial default state, like a list of cues or perhaps a video. Trainer can tailor the exercise for the specific client by editing cues or adding a video that speaks directly to them reminding them of their personal cues that help them achieve proper form. These will be stored in a "Tailored Exercise DB" to differentiate from our template exercises.
3. "Exercise Media" - Trainer can upload custom videos for exercises or 3d animations showing movements. In the
4. "Exercise Relationships" - Exercises can have direct progressions and regressions.
5. "Fun[designd-doc.md](designd-doc.md)ctional Progress" - Trainer can view clients progress by comparing past Functional Profiles to a current one to illuminate what's working and what needs more attention.
6. "New Tests" - Trainer can add their own tests
7. "Custom Assessment" - Trainer can change the flow of the tests to better match how they work
8. "Template Workouts" - Trainer can save a workout as a template, so that if a client has a similar functional profile
---

## UML Diagram
### Models
![MuscleSync puml.png](design%20images%2FMuscleSync%20puml.png)

### Activites
![activities.png](design%20images%2Factivities.png)

---
## App Tables
##### Exercises Table
- pk : functionalSystemState (S)
- sk : name (S)
##### Tests Tab le
- pk : functionalSystem
- sk : name (S)
##### FunctionalSystems Table
- pk : name (S)

## User Tables
##### Clients Table
- pk : trainerId (S)
- sk : clientName (S)
- clientInfo (S)
##### AssessmentResults Table
- pk : clientId (S)
- sk : date (S)
- FunctionalSystemResults (S)
- TestResults (S)
##### Programs Table
- pk : clientId (S)
- sk : date (S)
- List<Workouts>
  - List<Exercises>

---

## EndPoints
- A description of each API endpoint that includes:
    - Name
    - A brief description
    - HTTP method
    - Path, including any path parameters
    - Query string parameters (if applicable)
    - Request body (if applicable)
    - Response body
    - A description of any errors the endpoint might return
### 1. NewClient
- Description: User can create a new client 
- HTTP Method: `POST`
- Path: `/client`
- Request Body:
```
{
  "name": "Jane Doe",
  "dob" : "1988-12-27"
  "email": "scott@fakemail.com",
  "phone": "123-456-7890"
  "pregnancyHistory":[
      {
        "status": "pregnant",
        "date": "2024-02-23"
        "gender": "female",
        "notes": ""
      },
      {
        "status": "natural-birth",
        "date": "2021-09-01"
        "gender": "male",
        "notes": ""
      },
      {
        "status": "c-section",
        "date": "2029-02-14"
        "gender": "male",
        "notes": ""
      }       
    ]
  }
```
- Response
```
{
    "status": "success",
    "message": "Client successfully created",
}
```

### 2. UpdateClient
- Description: User can update client info
- HTTP Method: `PUT`
- Path: `/client/{client-id}`
- Request Body:
```
{
  "name": "Jane Doe",
  "dob" : "1988-12-27"
  "email": "scott@fakemail.com",
  "phone": "123-456-7890"
  "pregnancyHistory":[
      {
        "status": "pregnant",
        "date": "2024-02-23"
        "gender": "female",
        "notes": ""
      },
      {
        "status": "natural-birth",
        "date": "2021-09-01"
        "gender": "male",
        "notes": ""
      },
      {
        "status": "c-section",
        "date": "2029-02-14"
        "gender": "male",
        "notes": ""
      }       
    ]
  }
```
- Response
```
{
    "status": "success",
    "message": "Client successfully created",
}
```

### 3. GetClient
- Description: User can get client and all clients details
- HTTP Method: `GET`
- Path: `/client/(client_id}`
- Response
```
  "name": "Jane Doe",
  "dob" : "1988-12-27"
  "email": "jane@fakemail.com",
  "phone": "123-456-7890"
  "pregnancyHistory":[
      {
        "status": "pregnant",
        "date": "2024-02-23"
        "gender": "female",
        "notes": ""
      },
      {
        "status": "natural-birth",
        "date": "2021-09-01"
        "gender": "male",
        "notes": ""
      },
      {
        "status": "c-section",
        "date": "2029-02-14"
        "gender": "male",
        "notes": ""
      }       
    ]
  "functionalProfile": [
        {
          "functionalSystem": "Shoulder ROM",
          "funtionalSystemState": "Limited Interal",
          "secondaryCompensations": "Limted External"
        }, 
        {
          "functionalSystem": "Hip ROM",
          "funtionalSystemState": "Limited Interal",
          "secondaryCompensations": ""
        }
      ] 
  }
```

### 4. DeleteClient
- Description: User can delete client and all clients details
- HTTP Method: `DELETE`
- Path: `/client/(client_id}`
- Response
```
{
    "status": "success",
    "message": "Client successfully deleted",
}
```

### 5. SaveNewAssessment
- Description: User can run a new assessment using a form in the front end and save the results to the database.
- HTTP Method: `POST`
- Path: `/assessments`
- Request Body
```
{
  "clientId": "089732450712",
  "date": "2023-01-01", 
  "functionalSystemTestResults":[
       {
        "functionalSystem": "ISA",
        "notes": " ... ",
        "primaryTestId": "0000",
        "primaryTestMeasurement": "+74",
        "secondaryTests": [
            {
              "testId":"0001",
              "testMeasurement":"+75"      
            },
            {
              "testId":"0002",
              "testMeasurement":"-22"   
            },          
            {
              "testId":"0003",
              "testMeasurement":"-45"      
            }
          ]
        }, 
        
        {
          "functionalSystem": "Shoulder ROM",
          "notes": " ... ",
          "primaryTestId": "1000",
          "primaryTestMeasurement": "-90",
          "secondaryTests": [
            {
              "testId":"1001",
              "testMeasurement":"-60"      
            },
            {
              "testId":"1002",
              "testMeasurement":"-50"   
            }
          ]
        }, 
        
        {
          "functionalSystem": "Hip ROM",
          "notes": " ... ",
          "primaryTestId": "2000",
          "primaryTestMeasurement": "-90",
          "secondaryTests": [
            {
              "testId":"2001",
              "testMeasurement":"-60"      
            },
            {
              "testId":"2002",
              "testMeasurement":"-50"   
            }
          ]
        }
      ]
    }
```
- Response
```
{
    "status": "success",
    "message": "Assessment successfully saved",
}
```

### 6. DeleteAssessment
- Description: User can delete an assessment from database.
- HTTP Method: `DELETE`
- Path: `/assessments/{assessment_id}`
- Response
```
{
    "status": "success",
    "message": "Assessment successfully deleted",
}
```

---

## Mockups

![MuscleSync Overview.png](design%20images%2FMuscleSync%20Overview.png)

### Home Page

![MuscleSync Home.png](design%20images%2FMuscleSync%20Home.png)

### Client Page

![MuscleSync Client.png](design%20images%2FMuscleSync%20Client.png)

### Functional Assessment Input Form 

![MuscleSync Assessment.png](design%20images%2FMuscleSync%20Assessment.png)

### Workout Builder

![MuscleSync Workout.png](design%20images%2FMuscleSync%20Workout.png)

---