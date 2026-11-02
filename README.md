# Daiku

目標の設定や進捗の管理をサポートする、Spring Boot製のマルチモジュールアプリケーションです。
アプリケーション、ドメイン、バッチの役割ごとにモジュールを分けて構成しています。

## モジュール構成

### daiku-app

アプリケーション層のソースを管理します。HTTPリクエストを受け付けるController、
ユースケースを実行するService、認証・認可を担うSpring Securityなどを含みます。

### daiku-domain

ドメイン層のソースを管理します。アプリケーションのデータや業務ルールを表す
エンティティ、データアクセス処理などを含み、`daiku-app`と`daiku-batch`から利用されます。

### daiku-batch

定期実行や一括処理を行うバッチアプリケーションを管理します。
バッチ処理から`daiku-domain`の機能を利用します。

専用のConfigurationモジュールはありません。各アプリケーションの設定は、
それぞれのモジュールのリソースと環境変数で管理します。

## 主な技術

- Java 25（LTS）
- Spring Boot 4.1
- Gradle 9
- Spring Security / OAuth 2.0 Resource Server
- Spring Batch
- Doma
- PostgreSQL
- Firebase Admin SDK

## ビルドとテスト

リポジトリのルートディレクトリで実行します。

```bash
./gradlew build
./gradlew test
```

アプリケーションを起動する場合は、必要な環境変数を設定したうえで実行します。

```bash
./gradlew :daiku-app:bootRun
```

## 実行時の環境変数

データベース接続情報やFirebase認証情報は、ソースコードに直接記載せず環境変数から渡します。
認証情報を含むファイルや値をリポジトリに登録しないでください。

| 環境変数 | 用途 |
| --- | --- |
| `DB_URL` | PostgreSQLの接続先 |
| `DB_USERNAME` | データベースのユーザー名 |
| `DB_PASSWORD` | データベースのパスワード |
| `FIREBASE_PROJECT_ID` | FirebaseプロジェクトID |
| `FIREBASE_TYPE` | Firebase認証情報の種類 |
| `FIREBASE_PRIVATE_KEY_ID` | Firebase秘密鍵ID |
| `FIREBASE_PRIVATE_KEY` | Firebase秘密鍵 |
| `FIREBASE_CLIENT_EMAIL` | Firebaseサービスアカウントのメールアドレス |
| `FIREBASE_CLIENT_ID` | FirebaseクライアントID |
| `FIREBASE_CLIENT_X509_CER_URL` | Firebaseクライアント証明書URL |
| `API_KEY_VALUE_IOS` | アプリケーションで使用するiOS向けAPIキー |

利用する環境変数は起動するモジュールによって異なります。各モジュールの
`src/main/resources/application.yml`を参照してください。
