# detaQ

DetaQ is an app to help people prevent, treat, and follow up on heart attacks. This app designed for two users which are the patient and the families member. This app also connected to a smart wristband through IOT, where the wristband will used to detect patient's heart rate and send sos to families member.

# Running the app

To run this app, make sure to do these:
1. use the main branch.
2. make sure to enable any permission requests, so every features will be available.

If backend is down (probably), use this backend to run the app:

https://github.com/GSC-Detaq/BE-Detaq.git

To Register:
1. Please make sure to fill every necessary information such as name and email
2. for the phone number and otp, you can use this testing phone number and code we already provide

Number: +628123456789

Code: 123456

# Demo

https://youtu.be/alXOEb2G0DE?si=lXziEznhu3ziQimT

# Schreenshots

![Portfolio - Darren](https://github.com/darrenthiores/detaQ/assets/69592810/441689aa-732e-4068-a33e-6c8901963a93)

# Technologies and Libraries

- Kotlin
- Jetpack Compose
- Ktor (Api Service)
- Room (Local Database)
- Dagger Hilt (Dependency Injection)
- Firebase (Cloud Messaging, Realtime Database, Auth)
- Google Map

# Architecture
This app use modularization, where it is separated by features, then in every module there is clean architecture and MVVM (in module). And for the UI the app use MVI
