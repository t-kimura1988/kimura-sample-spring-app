# Daiku Multi Project
Multi-module Spring application for Daiku.

## Daiku-App

## Daiku-Domain

## Daiku-Batch

## Configuration

Database and Firebase settings are supplied through environment variables; do
not commit credentials or local environment files.

The domain module requires `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and
`FIREBASE_PROJECT_ID`. The app and batch modules also require
`FIREBASE_TYPE`, `FIREBASE_PRIVATE_KEY_ID`, `FIREBASE_PRIVATE_KEY`,
`FIREBASE_CLIENT_EMAIL`, `FIREBASE_CLIENT_ID`, and
`FIREBASE_CLIENT_X509_CER_URL`. The app additionally requires
`API_KEY_VALUE_IOS`.
