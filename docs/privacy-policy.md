# Privacy Policy

**Effective date:** [EFFECTIVE_DATE]

This Privacy Policy describes how information is collected, used, and shared when you use the **Flavor Fusion** Android application (package `com.flavorfusion.flavorfusion`, "the App"). The App is provided by **[DEVELOPER_NAME]** ("we", "us", or "our"). If you have questions about this policy, contact **[CONTACT_EMAIL]**.

By using the App, you agree to the collection and use of information in accordance with this policy. If you do not agree, please stop using the App and uninstall it.

---

## 1. Information we collect

### 1.1 Account information

When you create an account or sign in, we collect:

- **Email and password** when you sign up or sign in with email. Authentication and password storage are handled by our authentication provider, Supabase. Your password is never stored in plaintext and is not accessible to us.
- **Google account information** when you choose to sign in with Google: Google ID token, email address, first name, last name, and avatar URL. These fields are provided by Google through the Android Credential Manager.

A copy of your basic profile (user id, email, first name, last name, avatar URL) is stored locally on your device so that the App can show your account in the Settings screen. This local copy is cleared when you log out.

### 1.2 Data stored locally on your device

The following data is stored only on your device and is **not transmitted to us**:

- Your **favorite drinks and meals** (recipe id, name, image URL, and whether the item is a drink or a meal), persisted in a local Room database.
- Your **theme preference** (Light, Dark, or System), stored in DataStore.
- Your **alcoholic drinks filter** preference, stored in DataStore.
- The **cached copy of your user profile** described in section 1.1.

Uninstalling the App or logging out erases this local data.

### 1.3 Automatic diagnostics and usage data

The App integrates **Firebase Analytics** and **Firebase Crashlytics** (both provided by Google). These services automatically collect:

- App usage events (screens opened, sessions, in-app actions logged by the Firebase SDKs).
- Device and operating-system information (device model, Android version, language, country, app version).
- A **Firebase Installation ID** — a pseudonymous identifier for your app installation.
- **Crash reports** containing stack traces and the state of your device at the moment of a crash.

This data is processed by Google under its own privacy terms (see section 3). We use it to understand how the App is used and to diagnose and fix bugs.

### 1.4 What we do NOT collect

For clarity, the App does **not** request or collect any of the following:

- Precise or approximate location
- Contacts, calendar, SMS, or call logs
- Camera or microphone access
- Photos or media from your device
- Health, fitness, or biometric data
- Advertising ID or data for advertising personalization
- Push notification tokens (the App does not send push notifications)

The only Android permission requested is `INTERNET`, which is required for the App to communicate with its backends.

---

## 2. How we use your information

We use the information described above to:

- Authenticate you and keep you signed in across app launches.
- Show your account details in the Settings screen.
- Remember your theme and filter preferences.
- Fetch recipe content you request (see section 3.3).
- Diagnose crashes and improve the stability and quality of the App.

We do not use your personal data for advertising, profiling, or automated decision-making.

---

## 3. Third parties we share data with

We do not sell your personal information. The App relies on the following third-party providers ("sub-processors"):

### 3.1 Supabase (authentication)

Supabase processes your email and password (or your Google ID token) to authenticate you and maintain your session. See Supabase's privacy policy at <https://supabase.com/privacy>.

### 3.2 Google / Firebase

Google provides the sign-in flow (Google Identity / Credential Manager) and the Firebase services used by the App (Analytics, Crashlytics, Firebase Installations). Data processed by these services is governed by the Google Privacy Policy at <https://policies.google.com/privacy> and the Firebase Data Processing and Security Terms.

### 3.3 Recipe content APIs

When you browse drinks or meals, the App sends query strings (category, search term, or recipe id) to:

- **TheCocktailDB** — `https://www.thecocktaildb.com/api/json/v1/1/`
- **TheMealDB** — `https://www.themealdb.com/api/json/v1/1/`

No account data is sent to these services. Their operators may log standard request metadata (such as IP address) as is typical for public web APIs.

We may also disclose information if required to do so by law, or to protect our rights or the rights and safety of others.

---

## 4. International transfers

Supabase and Google operate global infrastructure and may process your data in countries other than your country of residence. Where required by law, these providers rely on appropriate safeguards (such as Standard Contractual Clauses) for cross-border transfers.

---

## 5. Data retention

- **Account data** stored by Supabase is retained until you request account deletion (see section 6).
- **Local data** (favorites, preferences, cached profile) is retained on your device until you log out or uninstall the App.
- **Analytics and crash data** is retained according to Google's default Firebase retention settings.

---

## 6. Your rights and choices

Depending on where you live, you may have rights to:

- Access the personal data we hold about you
- Correct inaccurate data
- Delete your account and associated data
- Object to or restrict certain processing
- Withdraw consent at any time
- Lodge a complaint with your local data protection authority

To exercise any of these rights, email **[CONTACT_EMAIL]**. You can also:

- **Log out** at any time from the Settings screen. Logout clears your locally-stored profile, favorites, and preferences.
- **Uninstall the App** to remove all local data from your device.
- **Request account deletion** by emailing us; we will delete your Supabase account and associated records within a reasonable period.

---

## 7. Children's privacy

The App is not directed to children under the age of 13 (or under 16 in the European Economic Area and the United Kingdom). We do not knowingly collect personal information from children. If you believe a child has provided us with personal information, please contact **[CONTACT_EMAIL]** and we will take steps to delete it.

---

## 8. Security

We rely on Supabase's password hashing and access controls for account credentials, and all network traffic from the App is transmitted over TLS (HTTPS). No method of transmission or storage is perfectly secure, and we cannot guarantee absolute security.

---

## 9. Changes to this policy

We may update this Privacy Policy from time to time. When we do, we will change the "Effective date" at the top of this page. Material changes will also be announced in the App or on the distribution page where reasonable.

---

## 10. Contact

If you have any questions about this Privacy Policy or how your data is handled, contact:

**[DEVELOPER_NAME]**
Email: **[CONTACT_EMAIL]**
