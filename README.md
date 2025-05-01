# 📱 MXCALLER - Smart Caller ID & Call Log Manager

MXCALLER is an Android application built as a modern alternative to Truecaller. It fetches and displays call logs and contacts, using a red-themed UI with MVVM architecture. Designed to be responsive across phones and tablets.


## 🚀 Features

- 📞 View detailed **Call Logs**
- 👤 List all **Phone Contacts**
- 🔍 Identify call types (incoming, outgoing, missed)
- 🧠 Responsive UI using **ConstraintLayout**
- 📸 Optionally load contact photos
- 🎨 Clean red color scheme throughout the app
- 🔄 Lottie-based **Splash Screen** with app branding
- 🔐 Handles runtime permissions (Call Log, Contacts)

---

## 🧱 Architecture

This project follows the **MVVM pattern**:

- **View** – Activities/Fragments with XML layouts
- **ViewModel** – Business logic + LiveData
- **Model** – Data classes (`CallDetails`, `Contact`)
- **Repository** – Clean data access layer (calls content providers)

1. Clone the repository : git clone https://github.com/yourusername/mxcaller.git
2. Open in **Android Studio**

3. Run on Android 8+ (API 26+)  
- Ensure permissions: `READ_CALL_LOG`, `READ_CONTACTS`

## 🛠️ Built With

- Kotlin
- MVVM Architecture
- Android Jetpack (LiveData, ViewModel)
- ViewBinding
- RecyclerView
- ConstraintLayout
- Lottie for splash animation
