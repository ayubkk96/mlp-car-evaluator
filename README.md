Car Evaluation Web App (MLP Decision Support System)

This project is a web-based decision support system that evaluates how good a car is based on its characteristics.

Users enter basic car details (price, safety, capacity, etc.), and the application uses a trained neural network to predict whether the car is:
	•	unacc – Unacceptable
	•	acc – Acceptable
	•	good – Good
	•	vgood – Very Good

The system is powered by a custom-built Multilayer Perceptron (MLP) trained on the UCI Car Evaluation dataset and deployed as a Spring Boot web application.

⸻

What does this app do?

In simple terms:

You describe a car using a small set of attributes, and the app instantly predicts how good that car is based on patterns learned from real data, not hard-coded rules.

This makes the app a decision support tool, similar in spirit to recommendation or scoring systems used in industry.

⸻

Features
	•	Web-based HTML form (no API client required)
	•	Instant prediction using a trained neural network
	•	One-hot encoded feature inputs (21 features total)
	•	Inference-only ML model (fast and lightweight)
	•	Dockerized and deployed as a cloud service
	•	Runs fully on CPU (no Python, no GPU)


⸻

EXAMPLE INPUT
{
  "buying": "med",
  "maint": "low",
  "doors": "4",
  "persons": "more",
  "lugBoot": "small",
  "safety": "high"
}

EXAMPLE OUTPUT
Predicted class: good
Class index: 2

Architecture Overview
Browser (HTML Form)
        ↓
Spring Boot Controller
        ↓
One-Hot Encoder (21 features)
        ↓
Trained MLP (Inference Only)
        ↓
Prediction Result

The neural network is loaded once at startup
	•	No model training occurs in the web app
	•	The ML logic is implemented in pure Java without external ML libraries


Machine Learning Model
	•	Model type: Multilayer Perceptron (MLP)
	•	Input size: 21 features
	•	Hidden layers: 16 → 12 neurons
	•	Output size: 4 classes
	•	Activation: Sigmoid
	•	Training dataset: UCI Car Evaluation Dataset

The full training pipeline and model implementation live in a separate repository and are intentionally kept independent from this web app.


Running Locally

Prerequisites
	•	Java 21
	•	Maven

  ./mvnw clean package
java -jar target/*.jar

http://localhost:8080


Running with Docker
docker build -t car-evaluation-app .
docker run -p 8080:8080 car-evaluation-app

Deployment

The app is deployed as a Dockerized web service on Render.

Deployment characteristics:
	•	Stateless
	•	No database
	•	No external services
	•	Fast cold-start
	•	Suitable for demo and portfolio use

Why this project?

This project demonstrates the full lifecycle of a machine learning system:
	•	Data-driven model training
	•	Custom neural network implementation
	•	Feature engineering (one-hot encoding)
	•	Model serialization and loading
	•	Web integration
	•	Cloud deployment

The focus is on turning machine learning into a usable product, not just training a model.

Future Improvements
	•	Display confidence scores per class
	•	Improve class separation between good and vgood
	•	Add basic model explainability
	•	Persist user evaluations
	•	UI enhancements


License

MIT License

