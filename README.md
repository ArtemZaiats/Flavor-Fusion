# Flavor Fusion

An Android app for discovering cocktail recipes and meal ideas, saving favorites, and personalizing your experience.

---

## About

Flavor Fusion lets you browse and search thousands of drinks and meals powered by public recipe APIs. You can mark items as favorites for quick access, filter drinks by alcohol content, browse meals by category, and switch between light and dark themes. User accounts are handled by Supabase, so your session persists across app launches.

---

## Features

- **Authentication** — Email/password sign-up and login via Supabase
- **Drinks** — Browse cocktails by alcoholic type, search by name, view full recipe details
- **Meals** — Browse recipes by category, search by name, view ingredients and instructions
- **Favorites** — Save and manage favorite drinks and meals with local Room persistence
- **Settings** — Choose light, dark, or system theme stored with DataStore
- **Home** — Fun facts screen shown after login

---

## Architecture

The app uses a custom **MVI (Model–View–Intent)** architecture with unidirectional data flow:

```
UI Event
   │
   ▼
handleEvent()
   │
   ▼
Action ──► Reducer ──► New State ──► UI recomposition
                           │
                           ▼
                        Effect (navigation, toasts, etc.)
```

Each screen defines a **Contract** interface containing four types:

| Type | Role |
|---|---|
| `State` | Immutable snapshot of everything the UI needs to render |
| `Event` | User intents (button clicks, input changes) |
| `Action` | State mutation descriptors consumed by the Reducer |
| `Effect` | One-time side effects (navigate, show toast, hide keyboard) |

The `MviViewModel` base class wires these together via a `StateFlow` for state, a `SharedFlow` for events, and a `Channel` for effects. Reducers are pure functions: `State.reduce(Action) → State`, making state transitions easy to test in isolation.

---

## Module Structure

```
Flavor-Fusion/
├── app/                        # App module — MainActivity, navigation, DI wiring
│
├── build-logic/                # Custom Gradle plugins (Compose, Hilt, feature, library)
│
├── core/
│   ├── core-ui/                # MVI base classes (MviViewModel, Reducer, contracts)
│   └── core-data/              # Network utilities, ResponseHandler, error models
│
├── common/
│   ├── common-domain/          # Domain models, repository interfaces, interactors
│   ├── common-data/            # Retrofit services, Room DB, repositories, DI modules
│   └── common-ui/              # Shared Compose components, theme, design system
│
└── features/
    ├── auth/                   # Login / sign-up
    ├── home/                   # Fun facts home screen
    ├── drinks/                 # Drinks list + detail
    ├── meals/                  # Meals list + detail
    ├── favorites/              # Saved favorites
    └── settings/               # Theme settings
```

---

## APIs

| API | Base URL | Used for |
|---|---|---|
| TheCocktailDB | `https://www.thecocktaildb.com/api/json/v1/1/` | Cocktail search, filtering, details |
| TheMealDB | `https://www.themealdb.com/api/json/v1/1/` | Meal categories, search, details |

Both are free public APIs that require no API key.

---

## Getting Started

### Prerequisites

- Android Studio Ladybug or newer
- JDK 17+
- A Supabase project (free tier is sufficient)
- A Firebase project with `google-services.json`

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/Flavor-Fusion.git
   cd Flavor-Fusion
   ```

2. **Add secrets to `local.properties`**

   Create or open `local.properties` in the project root and add:
   ```properties
   SUPABASE_URL=https://your-project-id.supabase.co
   SUPABASE_ANON_KEY=your-supabase-anon-key
   ```

3. **Add Firebase config**

   Place your `google-services.json` file inside the `app/` directory.

4. **Build and run**

   Open the project in Android Studio and run the `app` configuration on an emulator or device (API 26+).
