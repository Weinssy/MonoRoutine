# MonoRoutine

Native App with Minimalist Monochrome Habit & Task Tracker PWA with built-in synthesized alarms, deadline alerts, offline support, and multi-format backup.

## ✨ Features

- **Minimalist Monochrome Design**: Clean, distraction-free interface focused on productivity
- **Habit & Task Tracking**: Comprehensive tracking system for daily habits and tasks
- **Synthesized Alarms**: Built-in audio alerts (4 preset tones via Web Audio API - no external files)
- **Deadline Alerts**: Smart notification system with H-2 (48hr) and H-1 (24hr) options
- **Offline Support**: Full functionality works without internet connection
- **Multi-Format Backup**: Export and restore data in JSON, CSV, TXT, and PDF formats
- **Progressive Web App (PWA)**: Install on any device and use like a native app
- **Cross-Platform**: Works seamlessly on Android, iOS, and web browsers
- **Consistency Tracker**: Daily streak counter and activity completion summary
- **Journal & Reflection**: Daily evaluation notes with automatic timestamps
- **Screen Wake Lock**: Keep screen active during focus/standby mode
- **Snooze Function**: Flexible snooze options for alarms and reminders

## 🛠️ Technology Stack

- **Frontend**: HTML5, Vanilla JavaScript
- **Mobile**: Kotlin (Android native support)
- **Styling**: Tailwind CSS (via CDN)
- **Icons**: Lucide Icons
- **APIs Used**: 
  - Web Audio API (synthesized sound generation)
  - Notification API (system notifications)
  - Screen Wake Lock API (screen management)
- **Storage**: localStorage (client-side, completely offline)
- **Architecture**: Progressive Web App with offline-first design

## 📋 Prerequisites

### For Web Development
- Modern web browser (Chrome, Firefox, Safari, Edge)
- Code editor (VS Code recommended)
- Optional: Node.js v14+ for local development server

### For Android Development
- Android Studio
- Kotlin SDK
- JDK 8 or higher

## 🚀 Getting Started

### Web Version (Quick Start)

1. **Clone the repository**
   ```bash
   git clone https://github.com/Weinssy/MonoRoutine.git
   cd MonoRoutine
