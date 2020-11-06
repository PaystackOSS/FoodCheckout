# FoodCheckout
A simple application that shows how to integrate the Paystack SDK into your Android app. There's an
accompanying guide that explains how you should go about your integration:

<div float="left">
    <img src="https://drive.google.com/uc?export=view&id=1HFfmIO7HQ-9xgde85yt-l_dAvbvJZL2h" width="320" />
    <img src="https://drive.google.com/uc?export=view&id=1Vip9xUkumkNyL1JDWGCwv_M7HAfZwJ_O" width="320" />
</div>


# Project Setup
- Clone project and open on Android Studio
- The Java source code is on the `main` branch while the Kotlin is on the `kotlin` branch:
    - To use the Kotlin source code: `git checkout kotlin`
    - To use the Java source code: `git checkout main`
- Open the `gradle.properties` file and add the following param:
  - PSTK_PUBLIC_KEY="your_paystack_public_key"
- Sync project
- Run the project when sync is successful