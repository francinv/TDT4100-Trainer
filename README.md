# Prosjekt-repo for Trainer

Dette prosjektet er en trenings applikasjon. Her har bruker mulighet til å opprette workouts og melde seg på en annen sin workout.

## Funksjonalitet i applikasjonen 

### 1. Registrere bruker 
Bruker har mulighet til å registrere sin brukerinformasjon, dette blir lagret til allUsers.txt.
Nødvendig kode/resources:\
    - **[Userprofile.java](trainer/src/main/java/core/Userprofile.java)**\
    - **[Users.java](trainer/src/main/java/core/Users.java)**\
    - **[RegisterController.java](trainer/src/main/java/gui/RegisterController.java)**\
    - **[UsersPersistence.java](trainer/src/main/java/persistence/UsersPersistence.java)**\
    - **[allUsers.txt](trainer/src/main/java/persistence/allUsers.txt)**\
    - **[register.fxml](trainer/src/main/resources/register.fxml)**

### 2. Logge inn bruker 
Bruker har mulighet til å logge inn med registrert brukerinformasjon. 
Nødvendig kode/resources:\
    - **[Userprofile.java](trainer/src/main/java/core/Userprofile.java)**\
    - **[LoginController.java](trainer/src/main/java/gui/LoginController.java)**\
    - **[UserProfilePersistence.java](trainer/src/main/java/persistence/UserProfilePersistence.java)**\
    - **[userProfiles.txt](trainer/src/main/java/persistence/userProfiles.txt)**\
    - **[login.fxml](trainer/src/main/resources/login.fxml)**

### 3. Se workouts 
Bruker har mulighet til å se alle workouts på hovedsiden.
Nødvendig kode/resources:\
    - **[Workout.java](trainer/src/main/java/core/Workout.java)**\
    - **[allWorkouts.java](trainer/src/main/java/core/allWorkouts.java)**\
    - **[WorkoutListController.java](trainer/src/main/java/gui/WorkoutListController.java)**\
    - **[allWorkoutsPersistence.java](trainer/src/main/java/persistence/allWorkoutsPersistence.java)**\
    - **[allworkout.txt](trainer/src/main/java/persistence/allworkout.txt)**\
    - **[Workouts.fxml](trainer/src/main/resources/Workouts.fxml)**

### 4. Opprette workout
Bruker har mulighet til å opprette en workout. Denne vises på hovedsiden.
Nødvendig kode/resources:\
    - **[Workout.java](trainer/src/main/java/core/Workout.java)**\
    - **[allWorkouts.java](trainer/src/main/java/core/allWorkouts.java)**\
    - **[CreateWorkoutController.java](trainer/src/main/java/gui/CreateWorkoutController.java)**\
    - **[allWorkoutsPersistence.java](trainer/src/main/java/persistence/allWorkoutsPersistence.java)**\
    - **[allworkout.txt](trainer/src/main/java/persistence/allworkout.txt)**\
    - **[createnewworkout.fxml](trainer/src/main/resources/createnewworkout.fxml)**

### 5. Profilsiden 
Bruker har mulighet til å se sin profilinformasjon, se opprettede workouts og påmeldte workouts.
Nødvendig kode/resources:\
    - **[Workout.java](trainer/src/main/java/core/Workout.java)**\
    - **[Userprofile.java](trainer/src/main/java/core/Userprofile.java)**\
    - **[UserProfilePersistence.java](trainer/src/main/java/persistence/UserProfilePersistence.java)**\
    - **[userProfiles.txt](trainer/src/main/java/persistence/userProfiles.txt)**\
    - **[allWorkoutsPersistence.java](trainer/src/main/java/persistence/allWorkoutsPersistence.java)**\
    - **[allworkout.txt](trainer/src/main/java/persistence/allworkout.txt)**\
    - **[profile.fxml](trainer/src/main/resources/profile.fxml)**

### 6. Mulighet til å melde seg på workout
Bruker har mulighet til å melde seg på workout. 
Nødvendig kode/resources:\
    - **[Workout.java](trainer/src/main/java/core/Workout.java)**\
    - **[Userprofile.java](trainer/src/main/java/core/Userprofile.java)**\
    - **[UserProfilePersistence.java](trainer/src/main/java/persistence/UserProfilePersistence.java)**\
    - **[userProfiles.txt](trainer/src/main/java/persistence/userProfiles.txt)**\
    - **[allWorkoutsPersistence.java](trainer/src/main/java/persistence/allWorkoutsPersistence.java)**\
    - **[allworkout.txt](trainer/src/main/java/persistence/allworkout.txt)**\
    - **[workout_detail.fxml](trainer/src/main/resources/workout_detail.fxml)**




