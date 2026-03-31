# Multiple Disease Prediction System

A Streamlit-based web application for predicting multiple diseases using machine learning. Enter clinical or symptom-based inputs and get instant risk predictions across 9 different conditions.

---

## Diseases Covered

| Disease | Input Type |
|---|---|
| General Disease (130+ diseases) | Symptom selection |
| Diabetes | Clinical blood test values |
| Heart Disease | ECG & blood panel values |
| Parkinson's Disease | Voice measurement features |
| Liver Disease | Liver function test values |
| Hepatitis | Blood biochemistry markers |
| Lung Cancer | Lifestyle & symptom indicators |
| Chronic Kidney Disease | Urine & blood markers |
| Breast Cancer | Cell nuclei measurements (30 features) |

---

## Project Structure

```
.
├── Frontend/
│   ├── app.py                  # Main Streamlit application
│   ├── requirements.txt        # Frontend dependencies
│   ├── models/                 # Pre-trained .sav model files
│   │   ├── diabetes_model.sav
│   │   ├── heart_disease_model.sav
│   │   ├── parkinsons_model.sav
│   │   ├── liver_model.sav
│   │   ├── hepititisc_model.sav
│   │   ├── lung_cancer_model.sav
│   │   ├── chronic_model.sav
│   │   └── breast_cancer.sav
│   ├── model/
│   │   └── xgboost_model.json  # XGBoost model for general disease prediction
│   ├── data/                   # CSV datasets for symptom-based prediction
│   │   ├── dataset.csv
│   │   ├── clean_dataset.tsv
│   │   ├── lung_cancer.csv
│   │   ├── symptom_Description.csv
│   │   ├── symptom_precaution.csv
│   │   └── Symptom-severity.csv
│   └── code/
│       ├── DiseaseModel.py     # XGBoost wrapper for general disease prediction
│       ├── helper.py           # Utility to prepare symptom arrays
│       └── train.py            # Training script for XGBoost disease model
│
└── code/                       # ML pipeline (PIMA Diabetes focused)
    ├── config.yml              # Pipeline configuration
    ├── run_pipeline.py         # Entry point
    ├── training.py             # Full training pipeline (ensemble + stacking)
    ├── models.py               # Model factory (11 classifiers)
    ├── data_prep.py            # KFold target encoding, imputation, scaling
    ├── feature_engineer.py     # Feature engineering
    ├── evaluation.py           # Metrics + PDF report generation
    ├── config_utils.py         # YAML config loader
    ├── pima_diabetes.csv       # PIMA Indians Diabetes Dataset
    └── artifacts/              # Saved models, preprocessors, reports
```

---

## Setup & Installation

### Prerequisites
- Python 3.8+
- pip

### Install dependencies

```bash
cd Frontend
pip install -r requirements.txt
```

Additional packages needed for the ML pipeline:

```bash
pip install xgboost catboost imbalanced-learn optuna reportlab
```

### Run the app

```bash
cd Frontend
streamlit run app.py
```

The app will open at `http://localhost:8501`.

---

## ML Pipeline (PIMA Diabetes)

The `code/` directory contains a production-grade ML pipeline for the PIMA Indians Diabetes dataset demonstrating the methodology used across all models.

### Pipeline Steps

1. **Feature Engineering** — derived features from raw inputs
2. **Preprocessing** — median imputation + standard scaling
3. **KFold Target Encoding** — leak-free encoding for categorical features
4. **Feature Selection** — `SelectKBest` with ANOVA F-test (top 20 features)
5. **Resampling** — SMOTE-Tomek to handle class imbalance
6. **Cross-Validation** — StratifiedKFold across 11 classifiers
7. **Ensemble** — soft `VotingClassifier` with numerically optimized weights
8. **Stacking** — `StackingClassifier` with Logistic Regression meta-learner
9. **Calibration** — `CalibratedClassifierCV` for reliable probability estimates
10. **Report** — PDF with metrics, ROC curves, and confusion matrices

### Classifiers Used

- RandomForest, ExtraTrees, HistGradientBoosting, GradientBoosting
- SVC (RBF kernel), KNN, MLP (Neural Net)
- GaussianNB, DecisionTree
- XGBoost, CatBoost

### Run the pipeline

```bash
cd code
python run_pipeline.py
```

Artifacts (model, preprocessors, CV scores, PDF report) are saved to `code/artifacts/`.

---

## General Disease Prediction Module

Uses an **XGBoost** classifier trained on a symptom–disease dataset covering 130+ diseases. The user selects symptoms from a multiselect dropdown; the model returns the most likely disease with a probability score, description, and 4 recommended precautions.

---

## Tech Stack

| Category | Libraries |
|---|---|
| Frontend | Streamlit, streamlit-option-menu |
| Visualization | Plotly, Matplotlib, Seaborn |
| ML | scikit-learn, XGBoost, CatBoost |
| Data | Pandas, NumPy |
| Imbalance handling | imbalanced-learn (SMOTE-Tomek) |
| Model persistence | joblib, pickle |

---

## Screenshots

> Launch the app and navigate the sidebar to explore each disease prediction module.

---

## Notes

- All models are pre-trained and loaded as `.sav` / `.json` files. No retraining is needed to run the app.
- The general disease prediction module uses `data/clean_dataset.tsv` for symptom list and `data/symptom_Description.csv` / `data/symptom_precaution.csv` for descriptions and precautions.
- This tool is intended for educational and research purposes only and should not replace professional medical diagnosis.
