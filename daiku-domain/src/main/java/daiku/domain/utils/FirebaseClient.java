package daiku.domain.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FirebaseClient {

    private FirebaseApp app;

    @Value("${firebase.credentials.type}")
    private String type;

    @Value("${firebase.credentials.projectId}")
    private String projectId;

    @Value("${firebase.credentials.privateKeyId}")
    private String privateKeyId;

    @Value("${firebase.credentials.privateKey}")
    private String privateKey;

    @Value("${firebase.credentials.clientEmail}")
    private String clientEmail;

    @Value("${firebase.credentials.clientId}")
    private String clientId;

    @Value("${firebase.credentials.clientX509CertUrl}")
    private String clientX509CertUrl;

    private static final String FIREBASE_CREDENTIALS_PATH = "./credentials.json";
    private static ObjectMapper mapper = new ObjectMapper();


    public synchronized void init() {
        if(app != null) {
            return;
        }

        try {
            FileWriter fw = new FileWriter(FIREBASE_CREDENTIALS_PATH);
            try (PrintWriter pw = new PrintWriter(new BufferedWriter(fw))) {
                String str = mapper.writeValueAsString(getCredentials());
                pw.println(str);
            }

            FileInputStream serviceAccount = new FileInputStream(FIREBASE_CREDENTIALS_PATH);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            app = FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> getCredentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("type", type);
        credentials.put("project_id", projectId);
        credentials.put("private_key_id", privateKeyId);
        credentials.put("private_key", privateKey);
        credentials.put("client_email", clientEmail);
        credentials.put("client_id", clientId);
        credentials.put("auth_uri", "https://accounts.google.com/o/oauth2/auth");
        credentials.put("token_uri", "https://oauth2.googleapis.com/token");
        credentials.put("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
        credentials.put("client_x509_cert_url", clientX509CertUrl);
        return credentials;

    }

    @Bean
    @Scope("prototype")
    public FirebaseAuth auth() {
        init();
        return FirebaseAuth.getInstance();
    }

    @Bean
    @Scope("prototype")
    public Firestore firestore() {
        init();
        return FirestoreClient.getFirestore();
    }
}
