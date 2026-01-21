# 🧠 AI-Powered Text Summarizer Browser Extension

An end-to-end **AI-powered browser extension** that allows users to select any text on a webpage and instantly generate a clean, readable summary using a **Spring Boot backend integrated with Google Gemini LLM API**.

Built to improve research productivity and demonstrate real-world **full-stack AI integration**.

---

## 🚀 Features

- Select any text directly from a webpage
- Summarize content instantly from a browser side panel
- AI-generated summaries using Google Gemini
- Plain-text, readable output (no markdown noise)
- ~60% average content reduction while preserving meaning
- Notes feature with persistent local browser storage
- Supports multiple operations:
  - Text summarization
  - Topic suggestions and further reading
- Lightweight and fast extension UI

---


## 🧰 Tech Stack

### Frontend
- HTML
- CSS
- JavaScript
- Chrome Extension APIs
- Chrome Storage API

### Backend
- Java
- Spring Boot
- REST APIs
- WebClient (Reactive)
- Jackson JSON Parsing

### AI
- Google Gemini API
- Prompt Engineering

---



### Confriguration
  application.properties
 - gemini.api.url=YOUR_GEMINI_API_URL
 - gemini.api.key=YOUR_GEMINI_API_KEY

▶️How to Run Backend
- mvn spring-boot:run
- Runs at:
- http://localhost:8080

### Chrome Extension
- Open Chrome

- Go to chrome ://extensions

- Enable Developer Mode

- Click Load unpacked

- Select the extension/ folder

### Purpose of the project:
Understand real-world AI integration

Learn prompt engineering techniques

Build full-stack systems using Java and JavaScript

Work with LLM APIs beyond simple demos

Apply clean backend architecture principles
